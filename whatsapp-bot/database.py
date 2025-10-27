# -*- coding: utf-8 -*-
import logging
from sqlalchemy import create_engine
from sqlalchemy.exc import SQLAlchemyError
from config import DB_URL

engine = None

if DB_URL:
    try:
        # pool_pre_ping=True: verifica la conexión antes de usarla
        # future=True: habilita el modo 2.0 de SQLAlchemy
        engine = create_engine(DB_URL, pool_pre_ping=True, future=True)
        
        # Prueba la conexión al iniciar
        with engine.connect() as connection:
            logging.info("✅ Conexión a la base de datos establecida correctamente.")
    except SQLAlchemyError as e:
        logging.error(f"❌ Error al conectar con la base de datos: {e}")
else:
    logging.warning("⚠️ La variable de entorno DB_URL no está configurada. El bot no podrá usar la base de datos.")