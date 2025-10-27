import pymysql
conn = pymysql.connect(host='localhost', port=3306, user='root', password='1234', database='gestion_empresa')
cur = conn.cursor()
cur.execute("SHOW COLUMNS FROM compra_detalle")
cols = cur.fetchall()
print('Columns for compra_detalle:')
for c in cols:
    print(c)
conn.close()
