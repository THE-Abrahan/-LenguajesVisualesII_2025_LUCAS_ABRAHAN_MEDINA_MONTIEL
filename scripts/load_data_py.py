#!/usr/bin/env python3
"""Ejecuta src/main/resources/data.sql linea a linea usando PyMySQL.

ADVERTENCIA: este script ejecuta TRUNCATE/INSERT y modificará la base de datos destino.
Usa las credenciales por defecto de `application.properties` (localhost:3306, root/1234).
"""
import pymysql
import os
import sys

SQL_PATH = os.path.join(os.path.dirname(__file__), '..', 'src', 'main', 'resources', 'data.sql')
SQL_PATH = os.path.normpath(SQL_PATH)

HOST = 'localhost'
PORT = 3306
DB = 'gestion_empresa'
USER = 'root'
PASS = '1234'

def load_sql_file(path):
    with open(path, 'r', encoding='utf-8') as f:
        text = f.read()
    # Remove /* ... */ comments
    import re
    text = re.sub(r'/\*.*?\*/', '', text, flags=re.S)
    # Remove lines that start with --
    lines = []
    for line in text.splitlines():
        stripped = line.strip()
        if stripped.startswith('--') or stripped == '':
            continue
        lines.append(line)
    cleaned = '\n'.join(lines)
    # Split on semicolon followed by newline (heuristic)
    parts = [s.strip() for s in cleaned.split(';')]
    # drop empty
    statements = [p for p in parts if p]
    return statements

def main():
    if not os.path.exists(SQL_PATH):
        print(f"ERROR: no se encontró {SQL_PATH}")
        sys.exit(1)

    stmts = load_sql_file(SQL_PATH)
    print(f"Cargado {len(stmts)} statements desde {SQL_PATH}")

    try:
        conn = pymysql.connect(host=HOST, port=PORT, user=USER, password=PASS, database=DB, autocommit=True)
    except Exception as e:
        print(f"ERROR: no se pudo conectar a MySQL en {HOST}:{PORT} -> {e}")
        sys.exit(2)

    executed = 0
    errors = []
    try:
        with conn.cursor() as cur:
            for i, s in enumerate(stmts, start=1):
                try:
                    cur.execute(s)
                    executed += 1
                    if i % 50 == 0 or i == len(stmts):
                        print(f"Ejecutadas {executed}/{len(stmts)} statements...")
                except Exception as ex:
                    errors.append((i, ex))
                    print(f"ERROR en statement {i}: {ex}")
    finally:
        conn.close()

    print('\nResumen:')
    print(f'Statements ejecutadas: {executed}')
    if errors:
        print(f'Errores: {len(errors)} (mostrando primeros 5)')
        for i, e in errors[:5]:
            print(f'  - #{i}: {e}')
    else:
        print('Sin errores detectados.')

if __name__ == '__main__':
    main()
