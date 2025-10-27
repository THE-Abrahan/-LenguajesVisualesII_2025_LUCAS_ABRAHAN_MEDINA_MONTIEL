# WhatsApp Bot (Python / FastAPI)

Asistente de WhatsApp que consulta tu base de datos MySQL `gestion_empresa` para informar a un cliente si tiene cuentas por pagar (cuentas a cobrar del lado de la empresa) o está al día.

## Requisitos

- Python 3.10+
- MySQL 8.0+ (recomendado) o 5.7
- Cuenta de WhatsApp Cloud API (Meta), con número, `PHONE_NUMBER_ID`, `WHATSAPP_TOKEN` y un webhook verificable

## Instalación

1) Copia el ejemplo de variables y complétalo:

```
cd whatsapp-bot
cp .env.example .env
```

Edita `.env` con tus datos:
- `WHATSAPP_VERIFY_TOKEN`: token que usarás para verificar el webhook en Meta Developers
- `WHATSAPP_TOKEN`: token de acceso de WhatsApp Cloud API
- `WHATSAPP_PHONE_NUMBER_ID`: ID del número de WhatsApp Cloud
- `DB_URL`: conexión MySQL, ejemplo `mysql+pymysql://user:pass@localhost:3306/gestion_empresa?charset=utf8mb4`

2) Instala dependencias:

```
python -m venv .venv
. .venv/bin/activate   # Windows PowerShell: .venv\Scripts\Activate.ps1
pip install -r requirements.txt
```

3) Ejecuta el servidor local:

```
uvicorn app:app --reload --port 8000
```

4) Expone tu servidor con una URL pública (para pruebas):
- ngrok: `ngrok http 8000`
- Configura el Webhook en Meta Developers apuntando a `https://<tu-ngrok>/webhook` y usa el `WHATSAPP_VERIFY_TOKEN` de tu `.env`.

## Endpoints

- `GET /webhook`: verificación del webhook (Meta challenge)
- `POST /webhook`: recepción de mensajes entrantes
- `GET /health`: simple health check

## Flujo de uso

- El cliente escribe en WhatsApp: "SALDO" o "ESTADO". Si es primera vez, puede anexar su CI o RUC, p. ej.: `SALDO 1234567`.
- También puede solicitar reportes básicos, como `reporte clientes`.
- O puede pedir los últimos registros con `ultimos 5 clientes`.
- El bot intenta identificar al cliente por el número de WhatsApp (normalizado) contra `cliente.cli_telefono`. Si no hay coincidencia, intenta por `cli_ci` o `cli_ruc` extraídos del texto.
- Si lo identifica, consulta en `cuenta_a_cobrar` las cuotas no pagadas de las `venta` del cliente y responde:
  - Monto total pendiente
  - Monto vencido
  - Próximo vencimiento
  - Si no hay saldo pendiente, indica "Estás al día" ✅
- Si recibe un comando de reporte, cuenta los registros en la tabla solicitada (si está en la lista de tablas permitidas).
- Si recibe un comando `ultimos`, muestra los N registros más recientes de la tabla solicitada.

## SQL y compatibilidad MySQL

- El código intenta primero usar `REGEXP_REPLACE` (MySQL 8) para normalizar `cli_telefono`. Si tu servidor no soporta esa función, cae a una alternativa con `REPLACE()` anidados que remueve espacios, guiones, paréntesis y puntos.
- Si lo prefieres, puedes editar la consulta en `app.py` para agregar más `REPLACE()` según el formato de tus teléfonos.

Consultas clave (resumen):

- Búsqueda de cliente por teléfono/CI/RUC (MySQL 8):

```
SELECT cli_id, cli_nombre, cli_apellido
FROM cliente
WHERE REGEXP_REPLACE(COALESCE(cli_telefono,''), '[^0-9]', '') = :tel
   OR COALESCE(cli_ci,'') = :doc
   OR COALESCE(cli_ruc,'') = :doc
ORDER BY cli_id DESC
LIMIT 1;
```

- Resumen de deuda:

```
SELECT
  COALESCE(SUM(c.cuentcob_monto),0) AS total_pendiente,
  COALESCE(SUM(CASE
    WHEN c.cuentcob_fechavencimiento < CURDATE()
         AND COALESCE(c.cuentcob_estado,'PEN') <> 'PAG'
    THEN c.cuentcob_monto ELSE 0 END),0) AS total_vencido,
  MIN(CASE WHEN COALESCE(c.cuentcob_estado,'PEN') <> 'PAG'
           THEN c.cuentcob_fechavencimiento END) AS proximo_venc
FROM cuenta_a_cobrar c
JOIN venta v ON v.ven_id = c.Venta_ven_id
WHERE v.Cliente_cli_id = :cliId
  AND COALESCE(c.cuentcob_estado,'PEN') <> 'PAG';
```

> Nota: el esquema que compartiste usa `venta.Cliente_cli_id` y `cuenta_a_cobrar.Venta_ven_id` como llaves foráneas, y `cuentcob_estado` con códigos tipo `PAG` para pagado. El bot asume que cualquier estado distinto de `PAG` está pendiente.

## Pruebas rápidas

- Envía desde tu número de prueba (sandbox de WhatsApp Cloud) un mensaje al bot: `SALDO`.
- Si tu teléfono no coincide con `cli_telefono`, envía `SALDO 1234567` (reemplaza con tu CI o RUC cargado en la tabla `cliente`).

## Producción

- Implementa validación de cabecera `X-Hub-Signature-256` en `/webhook`.
- Guarda auditoría de consultas si es necesario (tabla `auditoria`).
- Asegura que guardas los teléfonos de clientes en formato E.164 (+595..., +54...) o normalízalos consistentemente.
- **Genera un token de acceso permanente**: El token temporal que se usa en desarrollo expira cada 24 horas. Para producción, debes generar un token de acceso de usuario del sistema (System User Access Token) que es de larga duración. Puedes seguir la guía oficial de Meta.
- Maneja la ventana de 24 horas de WhatsApp y plantillas para mensajes iniciados por la empresa.

## Ejecutar como script

También puedes correrlo directamente:

```
python app.py
```

Esto levanta `uvicorn` en `0.0.0.0:8000` (con `reload` en desarrollo).
