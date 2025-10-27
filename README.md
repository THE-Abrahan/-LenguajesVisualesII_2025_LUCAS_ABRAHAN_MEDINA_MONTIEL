# Proyecto Gestión Empresarial

Aplicación web en Java (Spring Boot) con interfaz HTML/CSS pensada como punto de partida para administrar las **tablas fuertes** del modelo mostrado en el diagrama. La capa de persistencia usa JPA/Hibernate y está lista para conectarse a MySQL (Workbench).

## Requisitos

- Java 17
- Maven 3.9+
- MySQL 8.x (Workbench u otra herramienta de administración)

## Configuración

1. Crea la base de datos en MySQL:
   ```sql
   CREATE DATABASE gestion_empresa CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```
2. Ajusta las credenciales en `src/main/resources/application.properties`.
3. Si quieres que Hibernate cree/actualice las tablas automáticamente mientras diseñas el esquema, cambia temporalmente:
   ```properties
   spring.jpa.hibernate.ddl-auto=update
   ```
   Después de estabilizar el modelo vuelve a `validate`.

## Ejecución

```bash
mvn spring-boot:run
```

La aplicación expone un panel en `http://localhost:8080/` que consulta y lista las entidades principales (clientes, productos, proveedores, stock, ventas, compras, pedidos, cuentas, pagos, cobros y cotizaciones).

## Cargar datos de ejemplo (Compras / Cuentas / Pagos)

En el proyecto hay un script de inserción de datos de ejemplo que NO se ejecuta automáticamente contra bases de datos externas por seguridad. Si ves secciones vacías en el dashboard (por ejemplo Compras, Cuentas por pagar o Pagos), debes poblar la base de datos manualmente.

Opciones para cargar los datos:

- Opción recomendada (PowerShell, incluido script de verificación):

   Ejecuta desde la raíz del proyecto:

   ```powershell
   .\scripts\load_and_verify_data.ps1
   ```

   El script pedirá host/puerto/usuario/contraseña y ejecutará `src/main/resources/data.sql`, luego mostrará recuentos por tabla para verificar la carga.

- Opción alternativa (cliente mysql):

   ```powershell
   mysql -u root -p gestion_empresa --execute="SOURCE C:/Users/lucas/Desktop/proyecto_gestion_empresa/src/main/resources/data.sql;"
   ```

   Reemplaza la ruta y credenciales según tu entorno. Puedes también abrir el cliente mysql (`mysql -u root -p`) y ejecutar `SOURCE C:/ruta/al/archivo/data.sql;` desde allí.

Advertencia: `src/main/resources/data.sql` contiene instrucciones TRUNCATE/INSERT para poblar tablas de ejemplo — úsalo sólo en entornos de desarrollo o en una base de datos de pruebas.

## Estructura destacada

- `com.gestionempresa.model`: entidades JPA alineadas con las tablas fuertes del modelo ER.
- `com.gestionempresa.repository`: repositorios Spring Data para cada entidad.
- `com.gestionempresa.controller.DashboardController`: renderiza la vista principal con Thymeleaf.
- `src/main/resources/templates/dashboard.html`: interfaz HTML.
- `src/main/resources/static/css/styles.css`: estilos base.

## Próximos pasos sugeridos

1. Completar las entidades restantes (tablas débiles y tablas puente) siguiendo los ejemplos existentes.
2. Crear DTOs y controladores REST si necesitas exponer datos a un frontend SPA.
3. Añadir validaciones y casos de uso (altas, bajas, modificaciones) con formularios o APIs.
4. Configurar pruebas automáticas (por ejemplo con `@DataJpaTest`) para asegurar la integridad del modelo.

> Nota: los nombres de campos y relaciones fueron inferidos del diagrama proporcionado; revisa y ajusta los `@Column` y `@JoinColumn` de acuerdo a tu esquema final en MySQL.
