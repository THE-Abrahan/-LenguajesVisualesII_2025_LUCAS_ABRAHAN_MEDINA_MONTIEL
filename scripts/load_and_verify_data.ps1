# PowerShell script para ejecutar src/main/resources/data.sql y verificar conteos por tabla
# Uso: Ejecutar desde PowerShell en la máquina local. El script pedirá credenciales.
# ADVERTENCIA: por simplicidad el script convierte la contraseña a texto plano temporalmente para pasársela al cliente mysql.
#           Úsalo en entorno local de desarrollo únicamente.

$scriptPath = Join-Path $PSScriptRoot "..\src\main\resources\data.sql"
$scriptPath = (Resolve-Path $scriptPath).ProviderPath

Write-Host "Script a ejecutar: $scriptPath"

$DbHost = Read-Host "MySQL host (enter para localhost)"
if ([string]::IsNullOrWhiteSpace($DbHost)) { $DbHost = 'localhost' }
$Port = Read-Host "MySQL port (enter para 3306)"
if ([string]::IsNullOrWhiteSpace($Port)) { $Port = '3306' }
$Database = Read-Host "Database name (enter para gestion_empresa)"
if ([string]::IsNullOrWhiteSpace($Database)) { $Database = 'gestion_empresa' }
$User = Read-Host "MySQL user (enter para root)"
if ([string]::IsNullOrWhiteSpace($User)) { $User = 'root' }
$SecurePwd = Read-Host "MySQL password (input oculto)" -AsSecureString

# convertir SecureString a texto plano (temporal)
$BSTR = [System.Runtime.InteropServices.Marshal]::SecureStringToBSTR($SecurePwd)
$PlainPwd = [System.Runtime.InteropServices.Marshal]::PtrToStringAuto($BSTR)
# No liberar aún hasta usar

# localizar mysql.exe
$mysqlExe = 'mysql'
if (-not (Get-Command $mysqlExe -ErrorAction SilentlyContinue)) {
    Write-Error "El cliente 'mysql' no está en PATH. Instala MySQL client o añade su carpeta bin a PATH."
    exit 1
}

Write-Host "Ejecutando script de datos en ${Database}@${Host}:${Port} como $User..."
try {
        # Ejecutar el script (usar cmd.exe /c para que la redirección '<' funcione sin que PowerShell la interprete)
        # Usar Start-Process con RedirectStandardInput para pasar el archivo SQL al stdin del cliente
        Write-Host "Lanzando cliente mysql y redirigiendo '$scriptPath' a su entrada estándar..."
        Start-Process -FilePath $mysqlExe -ArgumentList @('-h',$DbHost,'-P',$Port,'-u',$User,"-p$PlainPwd",'-D',$Database) -RedirectStandardInput $scriptPath -NoNewWindow -Wait
        Write-Host "\nEjecución completada. Ahora se consultarán los recuentos por tabla...\n"

        $tables = @(
      'departamento','marca','moneda','caja','cobro_tipo','ajustes_tipo','ciudad','empleado','usuario','cliente',
      'producto','stock','proveedor','cotizacion','arqueo','presupuesto','presupuesto_detalle','pedido_cliente',
      'presu_pedi','pedido_cliente_detalle','venta','venta_detalle','pedido_cliente_has_venta','cuenta_a_cobrar',
      'cobro','cobro_detalle','cobro_cotiza','cobro_por','pedido_proveedor','pedido_proveedor_detalle',
      'orden_de_compra','orden_de_compra_detalle','compra','compra_detalle','cuenta_a_pagar','orden_de_pago',
      'orden_de_pago_detalle','pago','ajustes','ajustes_detalle','devolucion_a_proveedor','devo_prov_deta','auditoria'
    )

    foreach ($t in $tables) {
        Write-Host "Consultando $t ..."
        $select = "SELECT '$t' AS tabla, COUNT(*) AS filas FROM $t;"
        & $mysqlExe -h $DbHost -P $Port -u $User -p$PlainPwd -D $Database -e $select
    }

    Write-Host "\nVerificación completada. Si hay tablas con 0 filas revisa permisos/TRUNCATE o errores durante la ejecución del script."
}
catch {
    Write-Error "Error al ejecutar el script o consultas: $_"
    exit 1
}
finally {
    # limpiar contraseña en memoria
    if ($PlainPwd) { $PlainPwd = $null }
    if ($BSTR) { [System.Runtime.InteropServices.Marshal]::ZeroFreeBSTR($BSTR) }
}

Write-Host "Listo."
