#!/usr/bin/env python3
"""Comprueba conteos y muestra filas de ejemplo para tablas relacionadas con Compras/Cuentas/Pagos.

Usa las credenciales por defecto que están en `src/main/resources/application.properties`.
Si tu BD está en otra máquina, ajusta HOST/PORT/USER/PASS abajo.
"""
import pymysql
import sys

HOST = 'localhost'
PORT = 3306
DB = 'gestion_empresa'
USER = 'root'
PASS = '1234'

tables = [
    'compra', 'compra_detalle', 'cuenta_a_pagar', 'orden_de_pago', 'orden_de_pago_detalle', 'pago'
]

def main():
    try:
        conn = pymysql.connect(host=HOST, port=PORT, user=USER, password=PASS, database=DB, cursorclass=pymysql.cursors.DictCursor)
    except Exception as e:
        print(f"ERROR: no se pudo conectar a MySQL en {HOST}:{PORT} -> {e}")
        sys.exit(2)

    try:
        with conn.cursor() as cur:
            print(f"Conectado a {DB} en {HOST}:{PORT} como {USER}\n")
            for t in tables:
                try:
                    cur.execute(f"SELECT COUNT(*) AS c FROM `{t}`")
                    r = cur.fetchone()
                    cnt = r['c'] if r else 0
                except Exception as ex:
                    cnt = f"ERROR: {ex}"
                print(f"{t:25} -> {cnt}")

            print('\nFilas ejemplo (hasta 5) de tablas clave:')
            for t in ['compra','cuenta_a_pagar','pago']:
                print(f"\n-- {t} --")
                try:
                    cur.execute(f"SELECT * FROM `{t}` LIMIT 5")
                    rows = cur.fetchall()
                    if not rows:
                        print("(sin filas)")
                    else:
                        for row in rows:
                            # print compact
                            print(row)
                except Exception as ex:
                    print(f"ERROR al leer {t}: {ex}")
    finally:
        conn.close()

if __name__ == '__main__':
    main()
