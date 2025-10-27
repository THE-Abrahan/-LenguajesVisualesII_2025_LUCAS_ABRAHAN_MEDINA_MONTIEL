# -*- coding: utf-8 -*-
import logging
import requests
from requests.adapters import HTTPAdapter
from urllib3.util.retry import Retry

from config import WHATSAPP_TOKEN, PHONE_NUMBER_ID, DEBUG_LOCAL, API_VERSION

def create_requests_session() -> requests.Session:
    """Crea una sesión de requests con reintentos automáticos para errores de red."""
    session = requests.Session()
    retry_strategy = Retry( # Increased retries and backoff for better resilience
        total=5, # Total attempts: 1 initial + 5 retries = 6 attempts
        connect=5, # Retries for connection errors
        allowed_methods=["HEAD", "GET", "POST"],
        status_forcelist=[429, 500, 502, 503, 504],
        backoff_factor=2, # Delays: 2s, 4s, 8s, 16s, 32s
    )
    adapter = HTTPAdapter(max_retries=retry_strategy)
    session.mount("https://", adapter)
    logging.info("✅ Sesión de requests configurada con reintentos automáticos.")
    return session

http_session = create_requests_session()

def send_message(to: str, text_body: str):
    """Envía un mensaje de texto a través de la API de WhatsApp."""
    if DEBUG_LOCAL:
        logging.info("--- MODO DEBUG LOCAL ACTIVADO ---")
        logging.info(f"Para: {to}")
        logging.info(f"Mensaje: {text_body}")
        return

    if not all([WHATSAPP_TOKEN, PHONE_NUMBER_ID]):
        logging.error("❌ Faltan WHATSAPP_TOKEN o PHONE_NUMBER_ID. No se puede enviar el mensaje.")
        return

    url = f"https://graph.facebook.com/{API_VERSION}/{PHONE_NUMBER_ID}/messages"
    headers = {"Authorization": f"Bearer {WHATSAPP_TOKEN}", "Content-Type": "application/json"}
    payload = {"messaging_product": "whatsapp", "to": to, "text": {"body": text_body[:4096]}}

    try:
        response = http_session.post(url, json=payload, headers=headers, timeout=30)
        response.raise_for_status()
        logging.info(f"✅ Mensaje enviado a {to}.")
    except requests.exceptions.RequestException as e:
        logging.error(f"❌ Fallo al enviar mensaje a WhatsApp. Error: {e}")
        if e.response is not None:
            logging.error(f"Detalle del error de la API: {e.response.text}")