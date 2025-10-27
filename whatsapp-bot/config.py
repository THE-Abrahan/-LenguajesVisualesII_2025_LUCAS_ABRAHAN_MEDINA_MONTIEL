# -*- coding: utf-8 -*-
import os
import logging
import locale
from dotenv import load_dotenv

# Carga las variables de entorno desde el archivo .env
load_dotenv()

# --- CONFIGURACIÓN GENERAL ---

def setup_logging():
    """Configura el sistema de logging para la aplicación."""
    logging.basicConfig(
        level=logging.INFO,
        format="%(asctime)s - %(levelname)s - %(message)s",
        datefmt="%Y-%m-%d %H:%M:%S"
    )

def setup_locale():
    """Configura el locale a español para formato de moneda."""
    try:
        # Para Windows
        locale.setlocale(locale.LC_ALL, 'es-PY')
    except locale.Error:
        try:
            # Para Linux/macOS
            locale.setlocale(locale.LC_ALL, 'es_ES.UTF-8')
        except locale.Error:
            logging.warning("Locale 'es-PY' o 'es_ES.UTF-8' no disponible. El formato de moneda puede ser incorrecto.")

setup_locale()

# --- VARIABLES DE WHATSAPP ---
VERIFY_TOKEN = os.getenv("miverifytoken123")
WHATSAPP_TOKEN = os.getenv("EAAWpJZAwg444BP7MvNbRbruJfK3q2MORq6pFS5EeMbfCBJKRgXlKyCvcZCLDrQWF2rAjBffeXiQqLhITnpEUMdI9PkbpAOgPq1gN95tLXZCf1Tx4ekjNf4R2EFAJPgZAX6zpWUpmabyFzuqpeoiW5mWHPGMy8gY1L7khvZCucI8yKkcitzcw3A4hBjjQRqwZDZD")
PHONE_NUMBER_ID = os.getenv("779355565269181")
APP_SECRET = os.getenv("29f947160a7f992db5cfebbf11cc7ead")

# --- VARIABLES DE BASE DE DATOS ---
DB_URL = os.getenv("DB_URL")

# --- VARIABLES DE EJECUCIÓN ---
DEBUG_LOCAL = os.getenv("DEBUG_LOCAL", "false").lower() in ("true", "1", "t", "y", "yes")
PORT = int(os.getenv("PORT", 8000))
RELOAD_ENABLED = os.getenv("UVICORN_RELOAD", "true").lower() in ("true", "1", "t", "y", "yes")

API_VERSION = "v20.0" # Versión de la Graph API de Facebook