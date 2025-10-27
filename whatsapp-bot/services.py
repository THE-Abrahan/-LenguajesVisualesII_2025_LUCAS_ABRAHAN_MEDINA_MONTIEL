# -*- coding: utf-8 -*-
import logging
import re
import locale
import hmac
import hashlib
from typing import Optional, Dict, Any

from fastapi import Request, HTTPException
from sqlalchemy import text
from sqlalchemy.exc import SQLAlchemyError

from database import engine
from whatsapp import send_message
from config import APP_SECRET


async def verify_signature(request: Request):
    """Verifica la firma del webhook para seguridad."""
    signature = request.headers.get("X-Hub-Signature-256", "").replace("sha256=", "")
    if not signature:
        raise HTTPException(status_code=403, detail="Firma de webhook ausente.")
    
    body = await request.body()
    expected_signature = hmac.new(APP_SECRET.encode(), body, hashlib.sha256).hexdigest()

    if not hmac.compare_digest(expected_signature, signature):
        logging.warning(f"⚠️ Firma de webhook inválida. Recibida: {signature}")
        raise HTTPException(status_code=403, detail="Firma de webhook inválida.")


def get_client_by_phone_or_doc(phone: str, doc: str) -> Optional[Dict]:
    """Busca un cliente por teléfono normalizado o documento (CI/RUC)."""
    if not engine: return None
    
    clean_phone = "".join(filter(str.isdigit, phone or ""))
    clean_doc = re.sub(r'[^a-zA-Z0-9]', '', doc or "").strip()

    query_mysql8 = text("""
        SELECT cli_id, cli_nombre, cli_apellido FROM cliente
        WHERE REGEXP_REPLACE(COALESCE(cli_telefono, ''), '[^0-9]', '') = :phone
           OR COALESCE(cli_ci, '') = :doc OR COALESCE(cli_ruc, '') = :doc
        ORDER BY cli_id DESC LIMIT 1
    """)
    query_mysql57 = text("""
        SELECT cli_id, cli_nombre, cli_apellido FROM cliente
        WHERE REPLACE(REPLACE(REPLACE(COALESCE(cli_telefono, ''), ' ', ''), '-', ''), '()', '') = :phone
           OR COALESCE(cli_ci, '') = :doc OR COALESCE(cli_ruc, '') = :doc
        ORDER BY cli_id DESC LIMIT 1
    """)
    
    with engine.connect() as conn:
        try:
            result = conn.execute(query_mysql8, {"phone": clean_phone, "doc": clean_doc}).mappings().first()
        except SQLAlchemyError:
            logging.info("REGEXP_REPLACE falló, usando fallback para MySQL 5.7.")
            result = conn.execute(query_mysql57, {"phone": clean_phone, "doc": clean_doc}).mappings().first()
        
        return dict(result) if result else None


def get_account_status(client_id: int) -> Optional[Dict]:
    """Obtiene el estado de cuenta (deuda total, vencida, etc.) para un cliente."""
    if not engine: return None
    query = text("""
        SELECT
          COALESCE(SUM(c.cuentcob_monto), 0) AS total_pendiente,
          COALESCE(SUM(CASE WHEN c.cuentcob_fechavencimiento < CURDATE() AND COALESCE(c.cuentcob_estado, 'PEN') <> 'PAG' THEN c.cuentcob_monto ELSE 0 END), 0) AS total_vencido,
          MIN(CASE WHEN COALESCE(c.cuentcob_estado, 'PEN') <> 'PAG' THEN c.cuentcob_fechavencimiento END) AS proximo_venc
        FROM cuenta_a_cobrar c
        JOIN venta v ON v.ven_id = c.Venta_ven_id
        WHERE v.Cliente_cli_id = :client_id AND COALESCE(c.cuentcob_estado, 'PEN') <> 'PAG'
    """)
    with engine.connect() as conn:
        result = conn.execute(query, {"client_id": client_id}).mappings().first()
        return dict(result) if result else None


def handle_status_command(from_number: str, text_body: str) -> str:
    """Maneja los comandos de consulta de saldo/estado."""
    doc = re.sub(r'(saldo|estado|cuenta)', '', text_body, flags=re.IGNORECASE).strip()
    client = get_client_by_phone_or_doc(from_number, doc)

    if not client:
        return "No pude encontrarte en nuestros registros. Por favor, responde con tu número de CI o RUC junto al comando (ej: `saldo 1234567`)."

    client_name = f"{client['cli_nombre']} {client['cli_apellido']}".strip()
    debt_summary = get_account_status(client['cli_id'])

    if not debt_summary:
        return f"Hola {client_name}. No pude consultar tu estado de cuenta en este momento. Intenta más tarde."

    total_due = float(debt_summary['total_pendiente'] or 0)
    if total_due < 0.01:
        return f"¡Hola {client_name}! ✅ Estás al día. No registramos saldos pendientes en tu cuenta."

    overdue = float(debt_summary['total_vencido'] or 0)
    next_due_date = debt_summary['proximo_venc'].strftime('%d/%m/%Y') if debt_summary['proximo_venc'] else "-"
    
    return (
        f"Hola {client_name}, este es el estado de tu cuenta:\n\n"
        f"💵 *Total pendiente:* {locale.currency(total_due, symbol=False, grouping=True)}\n"
        f"🗓️ *Total vencido:* {locale.currency(overdue, symbol=False, grouping=True)}\n"
        f"▫️ *Próximo vencimiento:* {next_due_date}"
    )


def get_default_reply() -> str:
    """Respuesta por defecto cuando no se reconoce un comando."""
    return (
        "Hola 👋 Soy tu asistente virtual. Puedes usar estos comandos:\n\n"
        "▫️ *SALDO* o *ESTADO*: para consultar tu cuenta.\n"
        # "▫️ *REPORTE [tabla]*: para obtener un resumen.\n" # Descomentar si se implementa
        # "▫️ *ULTIMOS [N] [tabla]*: para ver los registros más recientes." # Descomentar si se implementa
    )


# --- PROCESADOR PRINCIPAL DE MENSAJES ---

# Mapeo de palabras clave a funciones que manejan el comando
COMMAND_HANDLERS = {
    ("saldo", "estado", "cuenta"): handle_status_command,
    # Puedes agregar más comandos aquí. Ejemplo:
    # ("reporte",): handle_report_command,
    # ("ultimos",): handle_ultimos_command,
}

async def process_message(message_data: Dict[str, Any]):
    """
    Punto de entrada para procesar un mensaje de WhatsApp.
    Extrae la información relevante y delega al manejador de comandos apropiado.
    """
    from_number = message_data.get("from")
    text_body = (message_data.get("text") or {}).get("body", "").strip()

    if not (from_number and text_body):
        logging.warning("Mensaje sin 'from' o 'text', ignorando.")
        return

    logging.info(f"Procesando mensaje de {from_number}: '{text_body}'")
    
    command_lower = text_body.lower()
    reply_text = ""

    # Busca el manejador de comandos apropiado
    handler_found = False
    for keywords, handler_func in COMMAND_HANDLERS.items():
        if any(keyword in command_lower for keyword in keywords):
            try:
                reply_text = handler_func(from_number, text_body)
                handler_found = True
                break
            except Exception as e:
                logging.exception(f"Error ejecutando el comando '{keywords[0]}': {e}")
                reply_text = "Lo siento, ocurrió un error al procesar tu solicitud. Por favor, intenta de nuevo más tarde."
                handler_found = True
                break
    
    # Si no se encontró un manejador, usa la respuesta por defecto
    if not handler_found:
        reply_text = get_default_reply()

    # Envía la respuesta
    if reply_text:
        send_message(from_number, reply_text)