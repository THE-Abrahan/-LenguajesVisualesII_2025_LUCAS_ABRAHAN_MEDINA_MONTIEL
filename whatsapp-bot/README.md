WhatsApp Bot 

Estructura:
- main.py            # punto de entrada
- app/               # aplicación modular
  - core/config.py   # configuración
  - api/routes/      # endpoints (whatsapp, n8n, health)
  - services/        # lógica para enviar mensajes

Requisitos
```
python 3.10+
pip install -r requirements.txt
```

Uso (Windows PowerShell):
```powershell
cd whatsapp-bot
python -m venv venv
.\venv\Scripts\activate
pip install -r requirements.txt
copy .env.template .env
# editar .env con tus valores (VERIFY_TOKEN, WHATSAPP_TOKEN, PHONE_NUMBER_ID, APP_SECRET)
python main.py
```

Exponer localmente con ngrok (en otra terminal):
```powershell
# WhatsApp-bot — Guía rápida de instalación y ejecución

Este directorio contiene el bot de WhatsApp y utilidades para desarrollo local.
Estas instrucciones están pensadas para que el proyecto funcione "out of the box" en cualquier máquina.

Requisitos
- Python 3.10+ (recomendado 3.11/3.13)
- Git (opcional)

Preparar el entorno (pasos mínimos)

1. Clona o copia el proyecto y entra en la carpeta del bot:

   git clone <tu-repo-url>
   cd proyecto_gestion_empresa/whatsapp-bot

2. Crear y activar un entorno virtual:

   # PowerShell (Windows)
   python -m venv .venv
   .\.venv\Scripts\Activate.ps1

   # Bash / macOS / Linux
   python -m venv .venv
   source .venv/bin/activate

3. Instalar dependencias:

   pip install -r requirements.txt

4. Copiar el archivo de ejemplo de entorno y editar variables:

   copy .env.template .env    # PowerShell
   # o
   cp .env.template .env     # Bash

   - Revisa al menos `DB_URL`, `WHATSAPP_TOKEN`, `PHONE_NUMBER_ID` y `VERIFY_TOKEN`.
   - Por defecto el proyecto usará una base SQLite local (no necesitas configurar `DB_URL`).

Crear usuario local

- Crear el usuario de forma interactiva (recomendado):

  python create_user.py --username lucas --file data/users.json

  Cuando se solicite, introduce la contraseña (por ejemplo, `1234` para pruebas locales).

- Forzar actualización sin interacción (script auxiliar incluido):

  python scripts/set_user_password.py lucas 1234

  Este script genera salt + hash usando PBKDF2 y actualiza `data/users.json`.

Iniciar la interfaz de login (Tkinter)

  python .\tk_login.py

  - Usuario: lucas
  - Contraseña: 1234

Iniciar el servidor API (FastAPI / uvicorn)

  python -m uvicorn main:app --reload --host 0.0.0.0 --port 8000

Exponer localmente (opcional)

  ngrok http 8000

Configurar webhook en Meta (ejemplo)

  - URL callback: https://<tu-ngrok>.ngrok-free.dev/api/whatsapp/webhook
  - Token de verificación: el valor `VERIFY_TOKEN` en `.env`

Probar la funcionalidad del bot

  - Envía en la conversación: `1` (Consultar Cliente por CI/RUC)
  - Luego envía: `1234567`

  Si todo está correcto, el bot debería devolver los datos del cliente (en los datos de ejemplo `1234567` corresponde a "Andres Benitez").

Notas de portabilidad y seguridad

  - `app/core/config.py` usa por defecto una SQLite local dentro del proyecto para evitar tener que configurar una base remota.
  - Si quieres usar una base remota (MySQL/Postgres), configura `DB_URL` en `.env` (ej: `mysql+pymysql://user:pwd@host/db`).
  - `data/users.json` contiene usuarios con `salt` y `pwdhash` (PBKDF2). No subir contraseñas en texto plano.

Docker: iniciar MySQL + app (recomendado para reproducir fácilmente)

1) Desde la carpeta `whatsapp-bot` levanta el entorno con MySQL pre-inicializado:

   docker-compose -f docker-compose.mysql.yml up --build

   - Esto arranca un servicio `db` (MySQL 8) que inicializa la base `gestion_empresa`
     usando `data/gestion_empresa.sql` (incluye el cliente con CI `1234567`).
   - Arranca también el servicio `app` (builda la imagen con el Dockerfile del proyecto)
     y exporta el puerto 8000.

2) Acceder al servicio web / API:

   - Local: http://localhost:8000
   - Si usas la interfaz Tkinter, puedes abrir `tk_login.py` y usar las credenciales locales.

3) Probar la búsqueda por CI desde la conversación del bot:

   - Envía: `1` (Consultar Cliente por CI/RUC) y luego `1234567` — el bot debe encontrar
     a "Andres Benitez" porque la base se creó desde `data/gestion_empresa.sql`.

Notas:
 - Las credenciales por defecto para el contenedor MySQL en `docker-compose.mysql.yml` son:
     - usuario: `gestion`
     - password: `gestionpwd`
     - root password: `rootpwd`
  


