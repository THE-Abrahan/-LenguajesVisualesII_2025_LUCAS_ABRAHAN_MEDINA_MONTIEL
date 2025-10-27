# -*- coding: utf-8 -*-
import logging

from fastapi import FastAPI, Request, HTTPException, Query

from config import VERIFY_TOKEN, APP_SECRET, DEBUG_LOCAL, setup_logging
from services import process_message, verify_signature

# --- 1. CONFIGURACIÓN INICIAL ---
setup_logging()

# --- 2. ENDPOINTS DE LA API (FastAPI) ---

app = FastAPI(title="WhatsApp Bot - Gestion Empresa")

@app.on_event("startup")
async def startup_event():
    """Se ejecuta al iniciar el servidor."""
    logging.info("🚀 Servidor FastAPI iniciado.")
    if DEBUG_LOCAL:
        logging.warning(" MODO DEBUG LOCAL ESTÁ ACTIVO. Los mensajes no se enviarán a WhatsApp.")

@app.get("/health", tags=["Monitoring"])
def health_check():
    """Endpoint para verificar que el servicio está vivo."""
    from database import engine
    return {"status": "ok", "database_connected": engine is not None}

@app.get("/webhook", tags=["WhatsApp"])
def verify_webhook(
    hub_mode: str = Query(..., alias="hub.mode"),
    hub_challenge: int = Query(..., alias="hub.challenge"),
    hub_verify_token: str = Query(..., alias="hub.verify_token"),
):
    """Verificación del webhook de Meta (challenge)."""
    if hub_mode == "subscribe" and hub_verify_token == VERIFY_TOKEN:
        logging.info("✅ Webhook verificado correctamente por Meta.")
        return hub_challenge
    logging.warning("⚠️ Fallo en la verificación del webhook. Token o modo incorrecto.")
    raise HTTPException(status_code=403, detail="Token de verificación inválido.")

@app.post("/webhook", tags=["WhatsApp"])
async def handle_webhook(request: Request):
    """Recibe los mensajes entrantes de WhatsApp."""
    if APP_SECRET:
        await verify_signature(request)

    data = await request.json()
    logging.info(f"Payload recibido: {data}")

    try:
        if data.get("object") == "whatsapp_business_account":
            for entry in data.get("entry", []):
                for change in entry.get("changes", []):
                    value = change.get("value", {})
                    if "messages" in value:
                        for msg in value["messages"]:
                            await process_message(msg)
    except Exception as e:
        logging.exception(f"❌ Error procesando el webhook: {e}")

    return {"status": "ok"}

# --- 3. EJECUCIÓN DEL SERVIDOR ---

if __name__ == "__main__":
    import uvicorn
    from config import PORT, RELOAD_ENABLED
    
    logging.info(f"Iniciando servidor Uvicorn en http://0.0.0.0:{PORT}")
    uvicorn.run("app:app", host="0.0.0.0", port=PORT, reload=RELOAD_ENABLED)
