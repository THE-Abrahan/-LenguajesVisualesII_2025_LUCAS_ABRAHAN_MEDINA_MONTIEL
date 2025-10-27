-- =========================================================
-- SCRIPT DE INSERCIÓN DE DATOS MASIVOS (10 REGISTROS POR TABLA)
-- Base de Datos: gestion_empresa
-- =========================================================

SET FOREIGN_KEY_CHECKS = 0;

-- Limpieza de tablas en orden inverso de dependencias para evitar errores.
TRUNCATE TABLE auditoria;
TRUNCATE TABLE devo_prov_deta;
TRUNCATE TABLE devolucion_a_proveedor;
TRUNCATE TABLE ajustes_detalle;
TRUNCATE TABLE ajustes;
TRUNCATE TABLE pago;
TRUNCATE TABLE orden_de_pago_detalle;
TRUNCATE TABLE orden_de_pago;
TRUNCATE TABLE cuenta_a_pagar;
TRUNCATE TABLE compra_detalle;
TRUNCATE TABLE compra;
TRUNCATE TABLE orden_de_compra_detalle;
TRUNCATE TABLE orden_de_compra;
TRUNCATE TABLE pedido_proveedor_detalle;
TRUNCATE TABLE pedido_proveedor;
TRUNCATE TABLE cobro_por;
TRUNCATE TABLE cobro_cotiza;
TRUNCATE TABLE cobro_detalle;
TRUNCATE TABLE cuenta_a_cobrar;
TRUNCATE TABLE cobro;
TRUNCATE TABLE pedido_cliente_has_venta;
TRUNCATE TABLE venta_detalle;
TRUNCATE TABLE venta;
TRUNCATE TABLE pedido_cliente_detalle;
TRUNCATE TABLE presu_pedi;
TRUNCATE TABLE pedido_cliente;
TRUNCATE TABLE presupuesto_detalle;
TRUNCATE TABLE presupuesto;
TRUNCATE TABLE arqueo;
TRUNCATE TABLE cotizacion;
TRUNCATE TABLE proveedor;
TRUNCATE TABLE stock;
TRUNCATE TABLE producto;
TRUNCATE TABLE cliente;
TRUNCATE TABLE usuario;
TRUNCATE TABLE empleado;
TRUNCATE TABLE ciudad;
TRUNCATE TABLE ajustes_tipo;
TRUNCATE TABLE cobro_tipo;
TRUNCATE TABLE caja;
TRUNCATE TABLE moneda;
TRUNCATE TABLE marca;
TRUNCATE TABLE departamento;

-- =========================================================
-- 1. TABLAS MAESTRAS (SIN DEPENDENCIAS)
-- =========================================================

-- departamento
INSERT INTO `departamento` (`dep_id`, `dep_nombre`, `dep_capital`) VALUES
(1, 'Central', 'Areguá'),
(2, 'Alto Paraná', 'Ciudad del Este'),
(3, 'Itapúa', 'Encarnación'),
(4, 'Guairá', 'Villarrica'),
(5, 'Caaguazú', 'Coronel Oviedo'),
(6, 'San Pedro', 'San Pedro de Ycuamandiyú'),
(7, 'Cordillera', 'Caacupé'),
(8, 'Paraguarí', 'Paraguarí'),
(9, 'Concepción', 'Concepción'),
(10, 'Amambay', 'Pedro Juan Caballero');

-- marca
INSERT INTO `marca` (`mar_id`, `mar_descripcion`) VALUES
(1, 'Nike'),
(2, 'Adidas'),
(3, 'Samsung'),
(4, 'Apple'),
(5, 'Coca-Cola'),
(6, 'Pepsi'),
(7, 'HP'),
(8, 'Dell'),
(9, 'Toyota'),
(10, 'Ford');

-- moneda
INSERT INTO `moneda` (`mon_id`, `mon_descripcion`, `mon_sigla`) VALUES
(1, 'Guaraní', 'PYG'),
(2, 'Dólar Americano', 'USD'),
(3, 'Real Brasileño', 'BRL'),
(4, 'Peso Argentino', 'ARS'),
(5, 'Euro', 'EUR'),
(6, 'Yen Japonés', 'JPY'),
(7, 'Libra Esterlina', 'GBP'),
(8, 'Franco Suizo', 'CHF'),
(9, 'Dólar Canadiense', 'CAD'),
(10, 'Dólar Australiano', 'AUD');

-- caja
INSERT INTO `caja` (`caj_id`, `caj_descripcion`) VALUES
(1, 'Caja Principal'),
(2, 'Caja Secundaria'),
(3, 'Caja 3 (Ventas)'),
(4, 'Caja 4 (Cobranzas)'),
(5, 'Caja Móvil 1'),
(6, 'Caja Móvil 2'),
(7, 'Caja Gerencia'),
(8, 'Caja Depósito'),
(9, 'Caja de Ahorro'),
(10, 'Caja Chica');

-- cobro_tipo
INSERT INTO `cobro_tipo` (`cobtip_id`, `cobtip_descripcion`, `cobtip_nombre`) VALUES
(1, 'Cobro en efectivo', 'Efectivo'),
(2, 'Cobro con tarjeta de crédito', 'Tarjeta Crédito'),
(3, 'Cobro con tarjeta de débito', 'Tarjeta Débito'),
(4, 'Transferencia bancaria', 'Transferencia'),
(5, 'Cheque al día', 'Cheque'),
(6, 'Cheque diferido', 'Cheque Diferido'),
(7, 'Billetera digital', 'Billetera'),
(8, 'Pago con QR', 'QR'),
(9, 'Depósito en cuenta', 'Depósito'),
(10, 'Nota de crédito', 'Nota Crédito');

-- ajustes_tipo
INSERT INTO `ajustes_tipo` (`ajustip_id`, `ajustip_tipo`, `ajustip_motivo`) VALUES
(1, 'ENT', 'Entrada por inventario inicial'),
(2, 'SAL', 'Salida por merma'),
(3, 'ENT', 'Entrada por donación'),
(4, 'SAL', 'Salida por vencimiento'),
(5, 'ENT', 'Ajuste positivo de stock'),
(6, 'SAL', 'Ajuste negativo de stock'),
(7, 'SAL', 'Salida por rotura'),
(8, 'ENT', 'Devolución de cliente'),
(9, 'SAL', 'Uso interno'),
(10, 'ENT', 'Muestra de proveedor');

-- =========================================================
-- 2. TABLAS CON DEPENDENCIAS BÁSICAS
-- =========================================================

-- ciudad (depende de departamento)
INSERT INTO `ciudad` (`ciu_id`, `ciu_nombre`, `departamento_dep_id`) VALUES
(1, 'Luque', 1),
(2, 'Hernandarias', 2),
(3, 'Cambyretá', 3),
(4, 'Independencia', 4),
(5, 'Caaguazú', 5),
(6, 'San Estanislao', 6),
(7, 'Piribebuy', 7),
(8, 'Yaguarón', 8),
(9, 'Horqueta', 9),
(10, 'Bella Vista Norte', 10);

-- empleado (depende de ciudad)
INSERT INTO `empleado` (`emp_id`, `emp_nombre`, `emp_apellido`, `emp_ci`, `emp_sueldo`, `emp_direccion`, `emp_telefono`, `emp_fechanacimiento`, `emp_email`, `emp_sexo`, `emp_estado`, `Ciudad_ciu_id`, `emp_observacion`) VALUES
(1, 'LUCAS', 'MEDINA', '111111', 4500000.00, 'Av. Aviadores del Chaco 123', '0981123456', '1990-05-15', 'lucas.medina@empresa.com', 'M', 1, 1, 'Desarrollador principal del sistema.'),
(2, 'Ana', 'Perez', '222222', 4200000.00, 'Calle Palma 456', '0982234567', '1992-08-20', 'ana.perez@empresa.com', 'F', 1, 2, 'Encargada de ventas en CDE.'),
(3, 'Luis', 'Martinez', '333333', 5000000.00, 'Av. San Roque 789', '0983345678', '1988-11-10', 'luis.martinez@empresa.com', 'M', 1, 3, 'Gerente de sucursal Encarnación.'),
(4, 'Maria', 'Rodriguez', '444444', 3800000.00, 'Ruta 8 Blas Garay', '0984456789', '1995-02-25', 'maria.rodriguez@empresa.com', 'F', 1, 4, 'Asistente administrativa.'),
(5, 'Juan', 'Lopez', '555555', 6000000.00, 'Mcal. Lopez 101', '0985567890', '1985-07-30', 'juan.lopez@empresa.com', 'M', 1, 5, 'Jefe de logística.'),
(6, 'Laura', 'Fernandez', '666666', 3500000.00, 'Calle 14 de Mayo', '0971678901', '1998-01-12', 'laura.fernandez@empresa.com', 'F', 0, 6, 'Empleado inactivo.'),
(7, 'Pedro', 'Gonzalez', '777777', 7000000.00, 'Av. Eusebio Ayala 202', '0972789012', '1980-03-05', 'pedro.gonzalez@empresa.com', 'M', 1, 7, 'Supervisor de área.'),
(8, 'Sofia', 'Diaz', '888888', 4800000.00, 'Av. Artigas 303', '0973890123', '1993-09-18', 'sofia.diaz@empresa.com', 'F', 1, 8, 'Contadora.'),
(9, 'Diego', 'Sanchez', '999999', 5500000.00, 'Defensores del Chaco 404', '0974901234', '1991-12-22', 'diego.sanchez@empresa.com', 'M', 1, 9, 'Encargado de compras.'),
(10, 'Elena', 'Torres', '101010', 4000000.00, 'Calle Ultima 505', '0975012345', '1996-06-08', 'elena.torres@empresa.com', 'F', 0, 10, 'Ex-empleada, renunció.');

-- usuario (depende de empleado)
-- Contraseña para todos es '1234' (texto plano)
INSERT INTO `usuario` (`usu_id`, `usu_nombre`, `usu_contrasena`, `usu_estado`, `usu_tipo`, `Empleado_emp_id`) VALUES
(1, 'LUCAS.MEDINA', '1234', 1, 'ADMIN', 1),
(2, 'ana.perez', '1234', 1, 'USER', 2),
(3, 'luis.martinez', '1234', 1, 'USER', 3),
(4, 'maria.rodriguez', '1234', 1, 'USER', 4),
(5, 'juan.lopez', '1234', 1, 'ADMIN', 5),
(6, 'laura.fernandez', '1234', 0, 'USER', 6),
(7, 'pedro.gonzalez', '1234', 1, 'SUPERVISOR', 7),
(8, 'sofia.diaz', '1234', 1, 'USER', 8),
(9, 'diego.sanchez', '1234', 1, 'USER', 9),
(10, 'elena.torres', '1234', 0, 'USER', 10);

-- cliente (depende de ciudad)
INSERT INTO `cliente` (`cli_id`, `ciu_id`, `cli_nombre`, `cli_apellido`, `cli_sexo`, `cli_ci`, `cli_telefono`) VALUES
(1, 1, 'Andres', 'Benitez', 'M', '1234567', '0981111222'),
(2, 2, 'Beatriz', 'Caceres', 'F', '2345678', '0982222333'),
(3, 3, 'Carlos', 'Duarte', 'M', '3456789', '0983333444'),
(4, 4, 'Diana', 'Espinoza', 'F', '4567890', '0984444555'),
(5, 5, 'Esteban', 'Ferreira', 'M', '5678901', '0985555666'),
(6, 6, 'Gabriela', 'Gimenez', 'F', '6789012', '0971666777'),
(7, 7, 'Hugo', 'Insfran', 'M', '7890123', '0972777888'),
(8, 8, 'Irma', 'Jara', 'F', '8901234', '0973888999'),
(9, 9, 'Javier', 'Lugo', 'M', '9012345', '0974999000'),
(10, 10, 'Karina', 'Mendez', 'F', '1029384', '0975000111');

-- producto (depende de marca)
INSERT INTO `producto` (`prod_id`, `Marca_mar_id`, `prod_nombre`, `prod_descripcion`, `prod_preciocompra`, `prod_iva`) VALUES
('784001', 1, 'Zapatilla Runner', 'Calzado deportivo para correr', 350000.00, '10'),
('784002', 2, 'Camiseta Sport', 'Prenda de algodón para entrenamiento', 150000.00, '10'),
('784003', 3, 'TV 55" QLED', 'Televisor inteligente con pantalla QLED', 4500000.00, '10'),
('784004', 4, 'iPhone 15 Pro', 'Teléfono móvil de alta gama', 8000000.00, '10'),
('784005', 5, 'Coca-Cola 2L', 'Bebida gaseosa sabor cola', 8000.00, '10'),
('784006', 6, 'Pepsi 1.5L', 'Bebida gaseosa alternativa', 7000.00, '10'),
('784007', 7, 'Laptop Pavilion', 'Portátil para trabajo y estudio', 5500000.00, '10'),
('784008', 8, 'Monitor 24"', 'Monitor Full HD para PC', 1200000.00, '10'),
('784009', 9, 'Aceite Motor 1L', 'Lubricante sintético para motor', 90000.00, '10'),
('784010', 10, 'Limpia Parabrisas', 'Líquido para limpieza de vidrios', 45000.00, '5');

-- stock (depende de producto)
INSERT INTO `stock` (`Producto_prod_id`, `stock_cantidadmin`, `stock_cantidadmax`, `stock_cantidadactual`) VALUES
('784001', 10.00, 50.00, 25.00),
('784002', 20.00, 100.00, 60.00),
('784003', 5.00, 20.00, 8.00),
('784004', 5.00, 15.00, 10.00),
('784005', 50.00, 500.00, 250.00),
('784006', 50.00, 400.00, 200.00),
('784007', 3.00, 10.00, 7.00),
('784008', 10.00, 40.00, 15.00),
('784009', 30.00, 150.00, 80.00),
('784010', 25.00, 100.00, 50.00);

-- proveedor (depende de ciudad)
INSERT INTO `proveedor` (`prov_id`, `Ciudad_ciu_id`, `prov_nombre`, `prov_ruc`, `prov_direccion`, `prov_telefono`, `prov_email`, `prov_pag_web`, `prov_estado`, `prov_observacion`) VALUES
(1, 1, 'Distribuidora ABC', '80012345-1', 'Av. Principal 123', '021-123456', 'ventas@abc.com.py', 'www.abc.com.py', 1, 'Proveedor de productos varios.'),
(2, 2, 'Tecno Import S.A.', '80054321-2', 'Supercarretera Km 4', '061-555111', 'info@tecnoimport.com.py', 'www.tecnoimport.com.py', 1, 'Importador de electrónicos.'),
(3, 3, 'Logística Guaraní', '80098765-3', 'Ruta 1 Km 360', '071-200300', 'contacto@logistica.com.py', 'www.logistica.com.py', 1, 'Servicios de transporte.'),
(4, 4, 'Agro-Insumos del Sur', '80011223-4', 'Calle Rural 5', '0541-444555', 'agro@delsur.com', 'www.delsur.com', 1, 'Insumos para el agro.'),
(5, 5, 'Central de Bebidas', '80044556-5', 'Av. de las Américas 500', '0521-222333', 'pedidos@bebidas.com.py', 'www.bebidas.com.py', 1, 'Distribuidor mayorista de bebidas.'),
(6, 6, 'Repuestos y Accesorios SRL', '80077889-6', 'Taller Central 890', '0342-654321', 'repuestos@accesorios.com', 'www.accesorios.com', 1, 'Autopartes y repuestos.'),
(7, 7, 'Mundo Textil', '80022334-7', 'Galería Central, Local 10', '0511-987654', 'textil@mundo.com', 'www.mundo-textil.com', 0, 'Proveedor de telas. Inactivo.'),
(8, 8, 'Office Supply S.A.', '80055667-8', 'Edificio Corporativo, Piso 3', '021-888777', 'supply@office.com.py', 'www.office-supply.com', 1, 'Artículos de oficina.'),
(9, 9, 'Constructora del Norte', '80088990-9', 'Obrador Principal', '0331-234567', 'constructora@norte.com.py', 'www.norte-constructora.com', 1, 'Materiales de construcción.'),
(10, 10, 'Salud y Bienestar', '80012121-0', 'Av. Salud 100', '0336-111222', 'bienestar@salud.com.py', 'www.salud-bienestar.com', 1, 'Productos farmacéuticos.');

-- cotizacion (depende de moneda)
INSERT INTO `cotizacion` (`cot_codigo`, `Moneda_mon_codigo`, `cot_precompra`, `cot_preventa`, `cot_fecha`, `cot_hora`) VALUES
(1, 2, 7300.00, 7400.00, CURDATE(), CURTIME()),
(2, 3, 1400.00, 1500.00, CURDATE(), CURTIME()),
(3, 4, 7.00, 9.00, CURDATE(), CURTIME()),
(4, 5, 8000.00, 8200.00, CURDATE(), CURTIME()),
(5, 2, 7310.00, 7410.00, DATE_SUB(CURDATE(), INTERVAL 1 DAY), '09:00:00'),
(6, 3, 1405.00, 1505.00, DATE_SUB(CURDATE(), INTERVAL 1 DAY), '09:00:00'),
(7, 4, 7.10, 9.10, DATE_SUB(CURDATE(), INTERVAL 1 DAY), '09:00:00'),
(8, 5, 8010.00, 8210.00, DATE_SUB(CURDATE(), INTERVAL 1 DAY), '09:00:00'),
(9, 2, 7290.00, 7390.00, DATE_SUB(CURDATE(), INTERVAL 2 DAY), '15:00:00'),
(10, 3, 1390.00, 1490.00, DATE_SUB(CURDATE(), INTERVAL 2 DAY), '15:00:00');

-- arqueo (depende de caja, usuario)
INSERT INTO `arqueo` (`arq_id`, `Caja_caj_id`, `arq_fecha`, `arq_horadesde`, `arq_horahasta`, `arq_cheque`, `arq_totalefectivo`, `arq_estado`, `Usuario_usu_id`, `arq_montoinicio`) VALUES
(1, 1, CURDATE(), '08:00:00', NULL, 0, NULL, 'A', 1, 500000),
(2, 2, CURDATE(), '08:00:00', NULL, 0, NULL, 'A', 2, 300000),
(3, 1, DATE_SUB(CURDATE(), INTERVAL 1 DAY), '08:00:00', '18:00:00', 150000, 1200000, 'C', 1, 500000),
(4, 2, DATE_SUB(CURDATE(), INTERVAL 1 DAY), '08:00:00', '17:30:00', 0, 850000, 'C', 2, 300000),
(5, 3, CURDATE(), '09:00:00', NULL, 0, NULL, 'A', 3, 1000000),
(6, 4, CURDATE(), '09:00:00', NULL, 0, NULL, 'A', 4, 200000),
(7, 1, DATE_SUB(CURDATE(), INTERVAL 2 DAY), '08:00:00', '18:05:00', 0, 950000, 'C', 5, 450000),
(8, 5, CURDATE(), '10:00:00', NULL, 0, NULL, 'A', 8, 150000),
(9, 3, DATE_SUB(CURDATE(), INTERVAL 1 DAY), '09:00:00', '18:30:00', 500000, 2500000, 'C', 3, 1000000),
(10, 7, CURDATE(), '08:30:00', NULL, 0, NULL, 'A', 7, 0);

-- =========================================================
-- 3. MÓDULOS DE PROCESOS (VENTAS, COMPRAS, ETC.)
-- =========================================================

-- presupuesto (depende de cliente, usuario)
INSERT INTO `presupuesto` (`pres_id`, `Cliente_cli_id`, `pres_fecha`, `pres_estado`, `Usuario_usu_id`) VALUES
(1, 1, CURDATE(), 'VIG', 2),
(2, 2, CURDATE(), 'VIG', 2),
(3, 3, DATE_SUB(CURDATE(), INTERVAL 5 DAY), 'ANU', 3),
(4, 4, DATE_SUB(CURDATE(), INTERVAL 10 DAY), 'APR', 4),
(5, 5, DATE_SUB(CURDATE(), INTERVAL 15 DAY), 'VEN', 5),
(6, 1, DATE_SUB(CURDATE(), INTERVAL 2 DAY), 'VIG', 2),
(7, 6, CURDATE(), 'VIG', 8),
(8, 7, DATE_SUB(CURDATE(), INTERVAL 1 DAY), 'VIG', 8),
(9, 8, DATE_SUB(CURDATE(), INTERVAL 30 DAY), 'VEN', 9),
(10, 10, CURDATE(), 'VIG', 1);

-- presupuesto_detalle (depende de presupuesto, producto)
INSERT INTO `presupuesto_detalle` (`pres_id`, `prod_id`, `presdet_cantidad`, `presdet_preciounitario`) VALUES
(1, '784001', 1.00, 400000.00),
(1, '784002', 2.00, 180000.00),
(2, '784003', 1.00, 5000000.00),
(4, '784007', 1.00, 6000000.00),
(4, '784008', 1.00, 1300000.00),
(6, '784005', 10.00, 12000.00),
(7, '784009', 5.00, 95000.00),
(8, '784010', 4.00, 50000.00),
(9, '784004', 1.00, 9000000.00),
(10, '784006', 12.00, 10000.00);

-- pedido_cliente (depende de cliente, usuario)
INSERT INTO `pedido_cliente` (`pedcli_id`, `Cliente_cli_id`, `pedcli_fechapedido`, `pedcli_estado`, `Usuario_usu_id`) VALUES
(1, 1, CURDATE(), 'PEN', 2),
(2, 2, CURDATE(), 'PEN', 2),
(3, 4, DATE_SUB(CURDATE(), INTERVAL 9 DAY), 'ENT', 4),
(4, 5, DATE_SUB(CURDATE(), INTERVAL 12 DAY), 'CAN', 5),
(5, 6, CURDATE(), 'PEN', 8),
(6, 7, DATE_SUB(CURDATE(), INTERVAL 1 DAY), 'PRO', 8),
(7, 8, DATE_SUB(CURDATE(), INTERVAL 3 DAY), 'ENT', 9),
(8, 9, DATE_SUB(CURDATE(), INTERVAL 5 DAY), 'ENT', 9),
(9, 10, CURDATE(), 'PEN', 1),
(10, 3, DATE_SUB(CURDATE(), INTERVAL 2 DAY), 'CAN', 3);

-- presu_pedi (depende de presupuesto, pedido_cliente)
INSERT INTO `presu_pedi` (`presupuesto_pres_id`, `pedido_cliente_pedcli_id`) VALUES
(4, 3); -- Solo un ejemplo, esta tabla vincula un presupuesto a un pedido

-- pedido_cliente_detalle (depende de pedido_cliente, producto)
INSERT INTO `pedido_cliente_detalle` (`Pedido_cliente_pedcli_id`, `Stock_Producto_prod_id`, `pedclidet_preciounitario`, `pedclidet_cantidad`) VALUES
(1, '784001', 400000.00, 1.00),
(1, '784002', 180000.00, 1.00),
(2, '784003', 5000000.00, 1.00),
(3, '784007', 6000000.00, 1.00),
(3, '784008', 1300000.00, 1.00),
(5, '784009', 95000.00, 2.00),
(6, '784010', 50000.00, 3.00),
(7, '784004', 9000000.00, 1.00),
(8, '784005', 12000.00, 24.00),
(9, '784006', 10000.00, 12.00);

-- venta (depende de cliente, usuario)
INSERT INTO `venta` (`ven_id`, `Cliente_cli_id`, `ven_fechafacturacion`, `ven_hora`, `ven_estado`, `ven_tipo`, `Usuario_usu_id`) VALUES
(1, 1, CURDATE(), CURTIME(), 'ACT', 'CRD', 2),
(2, 2, CURDATE(), CURTIME(), 'ACT', 'CON', 2),
(3, 3, DATE_SUB(CURDATE(), INTERVAL 1 DAY), '14:30:00', 'ACT', 'CON', 3),
(4, 4, DATE_SUB(CURDATE(), INTERVAL 8 DAY), '15:00:00', 'ACT', 'CRD', 4),
(5, 5, DATE_SUB(CURDATE(), INTERVAL 10 DAY), '10:15:00', 'ANU', 'CON', 5),
(6, 6, CURDATE(), CURTIME(), 'ACT', 'CRD', 8),
(7, 7, DATE_SUB(CURDATE(), INTERVAL 2 DAY), '10:00:00', 'ACT', 'CON', 8),
(8, 8, DATE_SUB(CURDATE(), INTERVAL 4 DAY), '16:00:00', 'ACT', 'CRD', 9),
(9, 9, DATE_SUB(CURDATE(), INTERVAL 6 DAY), '11:00:00', 'ANU', 'CRD', 9),
(10, 10, CURDATE(), CURTIME(), 'ACT', 'CON', 1);

-- venta_detalle (depende de producto, venta)
INSERT INTO `venta_detalle` (`Stock_Producto_prod_id`, `Venta_ven_id`, `detventa_preciounitario`, `detventa_cantidad`) VALUES
('784001', 1, 420000.00, 1.00),
('784005', 2, 12000.00, 5.00),
('784006', 2, 10000.00, 5.00),
('784007', 3, 6200000.00, 1.00),
('784003', 4, 5100000.00, 1.00),
('784009', 6, 100000.00, 4.00),
('784002', 7, 190000.00, 2.00),
('784004', 8, 9500000.00, 1.00),
('784008', 10, 1350000.00, 2.00),
('784010', 10, 55000.00, 2.00);

-- pedido_cliente_has_venta (depende de pedido_cliente, venta)
INSERT INTO `pedido_cliente_has_venta` (`Pedido_cliente_pedcli_id`, `Venta_ven_id`) VALUES
(3, 4); -- Solo un ejemplo

-- cuenta_a_cobrar (depende de venta)
INSERT INTO `cuenta_a_cobrar` (`Venta_ven_id`, `cuentcob_cuotanumero`, `cuentcob_fechavencimiento`, `cuentcob_monto`, `cuentcob_estado`) VALUES
(1, 1, DATE_ADD(CURDATE(), INTERVAL 30 DAY), 210000.00, 'PEN'),
(1, 2, DATE_ADD(CURDATE(), INTERVAL 60 DAY), 210000.00, 'PEN'),
(4, 1, DATE_ADD(DATE_SUB(CURDATE(), INTERVAL 8 DAY), INTERVAL 30 DAY), 1700000.00, 'PEN'),
(4, 2, DATE_ADD(DATE_SUB(CURDATE(), INTERVAL 8 DAY), INTERVAL 60 DAY), 1700000.00, 'PEN'),
(4, 3, DATE_ADD(DATE_SUB(CURDATE(), INTERVAL 8 DAY), INTERVAL 90 DAY), 1700000.00, 'PEN'),
(6, 1, DATE_ADD(CURDATE(), INTERVAL 15 DAY), 400000.00, 'PEN'),
(8, 1, DATE_ADD(DATE_SUB(CURDATE(), INTERVAL 4 DAY), INTERVAL 30 DAY), 4750000.00, 'PEN'),
(8, 2, DATE_ADD(DATE_SUB(CURDATE(), INTERVAL 4 DAY), INTERVAL 60 DAY), 4750000.00, 'PEN'),
(1, 3, DATE_ADD(CURDATE(), INTERVAL 90 DAY), 0.00, 'CAN'), -- Cuota cancelada
(4, 4, DATE_ADD(DATE_SUB(CURDATE(), INTERVAL 8 DAY), INTERVAL 120 DAY), 0.00, 'PAG'); -- Cuota pagada

-- cobro (depende de arqueo, usuario)
INSERT INTO `cobro` (`cob_id`, `Arqueo_arq_id`, `cob_fecha`, `cob_hora`, `Usuario_usu_id`, `cob_estado`) VALUES
(1, 1, CURDATE(), CURTIME(), 2, 'VAL'),
(2, 2, CURDATE(), CURTIME(), 2, 'VAL'),
(3, 3, DATE_SUB(CURDATE(), INTERVAL 1 DAY), '15:00:00', 3, 'VAL'),
(4, 4, DATE_SUB(CURDATE(), INTERVAL 1 DAY), '16:00:00', 4, 'VAL'),
(5, 5, CURDATE(), CURTIME(), 5, 'VAL'),
(6, 1, CURDATE(), '11:00:00', 1, 'PEN'),
(7, 2, CURDATE(), '11:30:00', 2, 'PEN'),
(8, 3, DATE_SUB(CURDATE(), INTERVAL 1 DAY), '10:00:00', 1, 'ANU'),
(9, 5, CURDATE(), '14:00:00', 3, 'PEN'),
(10, 6, CURDATE(), '15:00:00', 8, 'VAL');

-- cobro_detalle (depende de cobro, cuenta_a_cobrar)
INSERT INTO `cobro_detalle` (`Cobro_cob_id`, `Cuenta_a_cobrar_Venta_ven_id`, `Cuenta_a_cobrar_cuentcob_cuotanumero`) VALUES
(3, 1, 1), -- Cobro 3 paga cuota 1 de venta 1
(4, 4, 1), -- Cobro 4 paga cuota 1 de venta 4
(5, 6, 1), -- Cobro 5 paga cuota 1 de venta 6
(1, 8, 1),
(2, 1, 2),
(6, 4, 2),
(7, 8, 2),
(10, 4, 3),
(10, 4, 4); -- Ejemplo de pago de cuota ya pagada (debería ser validado por lógica de negocio)

-- cobro_cotiza (depende de cobro, cuenta_a_cobrar, cotizacion)
-- Asumiendo que el cobro 1 se hizo en USD
INSERT INTO `cobro_cotiza` (`cob_id`, `ven_id`, `cuentcob_cuotanumero`, `cot_codigo`) VALUES
(1, 8, 1, 1);

-- cobro_por (depende de cobro_tipo, cobro_detalle)
INSERT INTO `cobro_por` (`Cobro_tipo_cobtip_id`, `Cobro_detalle_Cobro_cob_id`, `Cobro_detalle_Cuenta_a_cobrar_Venta_ven_id`, `Cobro_detalle_Cuenta_a_cobrar_cuentcob_cuotanumero`, `monto`) VALUES
(1, 3, 1, 1, '210000'), -- Efectivo
(2, 4, 4, 1, '1700000'), -- Tarjeta de crédito
(4, 5, 6, 1, '400000'), -- Transferencia
(1, 1, 8, 1, '4750000'), -- Efectivo
(3, 2, 1, 2, '210000'); -- Tarjeta de débito

-- pedido_proveedor (depende de proveedor, usuario)
INSERT INTO `pedido_proveedor` (`pedprov_id`, `Proveedor_prov_id`, `pedprov_fecha`, `pedprov_estado`, `Usuario_usu_id`) VALUES
(1, 1, CURDATE(), 'PEN', 5),
(2, 2, CURDATE(), 'PEN', 5),
(3, 3, DATE_SUB(CURDATE(), INTERVAL 5 DAY), 'REC', 7),
(4, 4, DATE_SUB(CURDATE(), INTERVAL 10 DAY), 'CAN', 7),
(5, 5, DATE_SUB(CURDATE(), INTERVAL 2 DAY), 'REC', 5),
(6, 6, CURDATE(), 'PEN', 9),
(7, 7, DATE_SUB(CURDATE(), INTERVAL 1 DAY), 'PRO', 9),
(8, 8, DATE_SUB(CURDATE(), INTERVAL 3 DAY), 'REC', 1),
(9, 9, DATE_SUB(CURDATE(), INTERVAL 15 DAY), 'REC', 1),
(10, 10, CURDATE(), 'PEN', 5);

-- pedido_proveedor_detalle (depende de pedido_proveedor, producto)
-- Usar los nombres de columna que mapea la entidad JPA: Producto_prod_id, pedprovdet_cantidad, pedprovdet_preciounitario
INSERT INTO `pedido_proveedor_detalle` (`pedprov_id`, `Producto_prod_id`, `pedprovdet_cantidad`, `pedprovdet_preciounitario`) VALUES
(1, '784001', 10.00, 340000.00),
(1, '784002', 20.00, 140000.00),
(2, '784003', 5.00, 4400000.00),
(3, '784009', 50.00, 85000.00),
(5, '784005', 200.00, 7800.00),
(5, '784006', 150.00, 6800.00),
(6, '784010', 30.00, 43000.00),
(7, '784002', 50.00, 135000.00),
(8, '784008', 10.00, 1100000.00),
(10, '784004', 5.00, 7800000.00);

-- orden_de_compra (depende de pedido_a_proveedor, usuario)
INSERT INTO `orden_de_compra` (`ordcomp_id`, `pedprov_id`, `ordcomp_fecha`, `ordcomp_hora`, `ordcomp_estado`, `Usuario_usu_id`) VALUES
(1, 3, DATE_SUB(CURDATE(), INTERVAL 4 DAY), '10:00:00', 'APR', 7),
(2, 5, DATE_SUB(CURDATE(), INTERVAL 1 DAY), '11:00:00', 'APR', 5),
(3, 8, DATE_SUB(CURDATE(), INTERVAL 2 DAY), '09:30:00', 'APR', 1),
(4, 9, DATE_SUB(CURDATE(), INTERVAL 14 DAY), '14:00:00', 'APR', 1),
(5, 1, CURDATE(), '15:00:00', 'PEN', 5),
(6, 2, CURDATE(), '16:00:00', 'PEN', 5),
(7, 4, DATE_SUB(CURDATE(), INTERVAL 9 DAY), '08:00:00', 'CAN', 7),
(8, 6, CURDATE(), '17:00:00', 'PEN', 9),
(9, 7, DATE_SUB(CURDATE(), INTERVAL 1 DAY), '18:00:00', 'PEN', 9),
(10, 10, CURDATE(), '10:30:00', 'PEN', 5);

-- orden_de_compra_detalle (depende de orden_de_compra, producto)
INSERT INTO `orden_de_compra_detalle` (`orddetitem`, `Orden_de_compra_ordcomp_id`, `Producto_prod_id`, `ordcompdet_cantidad`, `ordcompdet_preciounitario`) VALUES
(1, 1, '784009', 50.00, 85000.00),
(2, 2, '784005', 200.00, 7800.00),
(3, 2, '784006', 150.00, 6800.00),
(4, 3, '784008', 10.00, 1100000.00),
(5, 4, '784007', 2.00, 5300000.00),
(6, 5, '784001', 10.00, 340000.00),
(7, 5, '784002', 20.00, 140000.00),
(8, 6, '784003', 5.00, 4400000.00),
(9, 8, '784010', 30.00, 43000.00),
(10, 9, '784002', 50.00, 135000.00);

-- compra (depende de proveedor, orden_de_compra, usuario)
INSERT INTO `compra` (`comp_numerofactura`, `Proveedor_prov_id`, `Orden_de_compra_ordcomp_id`, `comp_fecha`, `comp_horacompra`, `comp_estado`, `comp_fecharegistro`, `comp_tipo`, `Usuario_usu_id`) VALUES
('F001-001-0001234', 3, 1, DATE_SUB(CURDATE(), INTERVAL 3 DAY), '11:00:00', 'FIN', DATE_SUB(CURDATE(), INTERVAL 3 DAY), 'CRD', 7),
('F001-002-0005678', 5, 2, DATE_SUB(CURDATE(), INTERVAL 1 DAY), '14:30:00', 'FIN', DATE_SUB(CURDATE(), INTERVAL 1 DAY), 'CRD', 5),
('F002-001-0009012', 8, 3, DATE_SUB(CURDATE(), INTERVAL 1 DAY), '16:00:00', 'FIN', DATE_SUB(CURDATE(), INTERVAL 1 DAY), 'CON', 1),
('F001-001-0003344', 9, 4, DATE_SUB(CURDATE(), INTERVAL 13 DAY), '10:00:00', 'FIN', DATE_SUB(CURDATE(), INTERVAL 13 DAY), 'CON', 1),
('F001-003-0001111', 1, 5, CURDATE(), '09:00:00', 'PEN', CURDATE(), 'CRD', 5),
('F001-004-0002222', 2, 6, CURDATE(), '10:30:00', 'PEN', CURDATE(), 'CRD', 5),
('F001-005-0003333', 6, 8, CURDATE(), '11:45:00', 'PEN', CURDATE(), 'CON', 9),
('F001-006-0004444', 7, 9, DATE_SUB(CURDATE(), INTERVAL 1 DAY), '17:00:00', 'ANU', DATE_SUB(CURDATE(), INTERVAL 1 DAY), 'CRD', 9),
('F001-007-0005555', 10, 10, CURDATE(), '12:00:00', 'PEN', CURDATE(), 'CON', 5),
('F001-008-0006666', 1, NULL, CURDATE(), '15:00:00', 'FIN', CURDATE(), 'CON', 5); -- Compra directa sin orden

-- compra_detalle (depende de compra, producto)
INSERT INTO `compra_detalle` (`compdet_item`, `comp_numerofactura`, `Stock_Producto_prod_id`, `compdet_cantidad`, `compdet_preciounitario`) VALUES
(1, 'F001-001-0001234', '784009', 50.00, 85000.00),
(2, 'F001-002-0005678', '784005', 200.00, 7800.00),
(3, 'F001-002-0005678', '784006', 150.00, 6800.00),
(4, 'F002-001-0009012', '784008', 10.00, 1100000.00),
(5, 'F001-001-0003344', '784007', 2.00, 5300000.00),
(6, 'F001-003-0001111', '784001', 10.00, 340000.00),
(7, 'F001-003-0001111', '784002', 20.00, 140000.00),
(8, 'F001-004-0002222', '784003', 5.00, 4400000.00),
(9, 'F001-005-0003333', '784010', 30.00, 43000.00),
(10, 'F001-006-0004444', '784002', 50.00, 135000.00);

-- cuenta_a_pagar (depende de compra, orden_de_compra)
INSERT INTO `cuenta_a_pagar` (`cuenpag_numerocuota`, `compra_numerofactura`, `cuenpag_monto`, `cuenpag_estado`) VALUES
(1, 'F001-001-0001234', 2125000.00, 'PEN'),
(2, 'F001-001-0001234', 2125000.00, 'PEN'),
(1, 'F001-002-0005678', 1290000.00, 'PEN'),
(2, 'F001-002-0005678', 1290000.00, 'PEN'),
(1, 'F001-003-0001111', 3100000.00, 'PEN'),
(2, 'F001-003-0001111', 3100000.00, 'PEN'),
(1, 'F001-004-0002222', 11000000.00, 'PEN'),
(1, 'F001-006-0004444', 3375000.00, 'PAG'),
(2, 'F001-006-0004444', 3375000.00, 'PEN');

-- orden_de_pago (depende de usuario)
INSERT INTO `orden_de_pago` (`ordpag_numero`, `ordpag_fecha`, `Usuario_usu_id`, `ordpag_estado`) VALUES
(1, CURDATE(), 7, 'PEN'),
(2, CURDATE(), 7, 'PEN'),
(3, DATE_SUB(CURDATE(), INTERVAL 5 DAY), 1, 'PAG'),
(4, DATE_SUB(CURDATE(), INTERVAL 10 DAY), 1, 'PAG'),
(5, DATE_SUB(CURDATE(), INTERVAL 15 DAY), 7, 'ANU'),
(6, CURDATE(), 5, 'PEN'),
(7, CURDATE(), 5, 'PEN'),
(8, DATE_SUB(CURDATE(), INTERVAL 2 DAY), 7, 'PAG'),
(9, DATE_SUB(CURDATE(), INTERVAL 3 DAY), 1, 'PAG'),
(10, CURDATE(), 7, 'PEN');

-- orden_de_pago_detalle (depende de orden_de_pago, cuenta_a_pagar)
INSERT INTO `orden_de_pago_detalle` (`Orden_de_pago_ordpag_numero`, `cuenpag_numerocuota`, `comp_numerofactura`, `ordpagdet_monto`) VALUES
(1, 1, 'F001-001-0001234', 2125000.00),
(2, 1, 'F001-002-0005678', 1290000.00),
(3, 1, 'F001-006-0004444', 3375000.00),
(4, 2, 'F001-001-0001234', 2125000.00),
(6, 2, 'F001-002-0005678', 1290000.00),
(7, 1, 'F001-003-0001111', 3100000.00),
(8, 2, 'F001-003-0001111', 3100000.00),
(9, 1, 'F001-004-0002222', 22000000.00),
(10, 2, 'F001-006-0004444', 3375000.00);

-- pago (depende de orden_de_pago, usuario)
INSERT INTO `pago` (`pag_id`, `Orden_de_pago_ordpag_numero`, `pag_monto`, `pag_fecha`, `Usuario_usu_id`, `pag_estado`) VALUES
(1, 3, 3375000.00, DATE_SUB(CURDATE(), INTERVAL 4 DAY), 1, 'A'),
(2, 4, 2125000.00, DATE_SUB(CURDATE(), INTERVAL 9 DAY), 1, 'A'),
(3, 8, 3100000.00, DATE_SUB(CURDATE(), INTERVAL 1 DAY), 7, 'A'),
(4, 9, 22000000.00, DATE_SUB(CURDATE(), INTERVAL 2 DAY), 1, 'A'),
(5, 1, 2125000.00, CURDATE(), 7, 'P'),
(6, 2, 1290000.00, CURDATE(), 7, 'P'),
(7, 5, 0.00, DATE_SUB(CURDATE(), INTERVAL 15 DAY), 7, 'X'),
(8, 6, 1290000.00, CURDATE(), 5, 'P'),
(9, 7, 3100000.00, CURDATE(), 5, 'P'),
(10, 10, 3375000.00, CURDATE(), 7, 'P');

-- ajustes (depende de usuario)
INSERT INTO `ajustes` (`ajus_id`, `ajus_fecha`, `ajus_hora`, `Usuario_usu_id`, `ajus_estado`) VALUES
(1, CURDATE(), '09:00:00', 1, 'FIN'),
(2, DATE_SUB(CURDATE(), INTERVAL 1 DAY), '10:00:00', 5, 'FIN'),
(3, DATE_SUB(CURDATE(), INTERVAL 2 DAY), '11:00:00', 7, 'ANU'),
(4, DATE_SUB(CURDATE(), INTERVAL 3 DAY), '12:00:00', 9, 'FIN'),
(5, CURDATE(), '13:00:00', 1, 'PRO'),
(6, CURDATE(), '14:00:00', 5, 'PRO'),
(7, DATE_SUB(CURDATE(), INTERVAL 5 DAY), '15:00:00', 7, 'FIN'),
(8, DATE_SUB(CURDATE(), INTERVAL 6 DAY), '16:00:00', 9, 'FIN'),
(9, CURDATE(), '17:00:00', 1, 'PRO'),
(10, DATE_SUB(CURDATE(), INTERVAL 10 DAY), '18:00:00', 5, 'FIN');

-- ajustes_detalle (depende de ajustes, producto, ajustes_tipo)
INSERT INTO `ajustes_detalle` (`Ajustes_ajus_id`, `prod_id`, `ajustip_id`, `detajustes_cantidad`) VALUES
(1, '784005', 2, 5.00), -- Salida por merma
(2, '784001', 8, 1.00), -- Entrada por devolución
(4, '784002', 4, 2.00), -- Salida por vencimiento
(5, '784007', 6, 1.00), -- Ajuste negativo
(6, '784008', 5, 2.00), -- Ajuste positivo
(7, '784003', 7, 1.00), -- Salida por rotura
(8, '784010', 9, 3.00), -- Uso interno
(9, '784009', 1, 10.00), -- Entrada por inventario inicial
(10, '784004', 3, 1.00); -- Entrada por donación

-- devolucion_a_proveedor (depende de proveedor, usuario)
INSERT INTO `devolucion_a_proveedor` (`devprov_id`, `Proveedor_prov_id`, `devprov_fecha`, `Usuario_usu_id`, `dev_prov_estado`) VALUES
(1, 1, CURDATE(), 5, 'PEN'),
(2, 2, DATE_SUB(CURDATE(), INTERVAL 5 DAY), 7, 'FIN'),
(3, 5, DATE_SUB(CURDATE(), INTERVAL 10 DAY), 1, 'FIN'),
(4, 6, DATE_SUB(CURDATE(), INTERVAL 15 DAY), 9, 'ANU'),
(5, 7, CURDATE(), 5, 'PEN'),
(6, 3, DATE_SUB(CURDATE(), INTERVAL 1 DAY), 7, 'FIN'),
(7, 4, DATE_SUB(CURDATE(), INTERVAL 2 DAY), 1, 'FIN'),
(8, 8, CURDATE(), 9, 'PEN'),
(9, 9, DATE_SUB(CURDATE(), INTERVAL 3 DAY), 5, 'FIN'),
(10, 10, DATE_SUB(CURDATE(), INTERVAL 4 DAY), 7, 'ANU');

-- devo_prov_deta (depende de devolucion_a_proveedor, producto)
INSERT INTO `devo_prov_deta` (`Devolucion_a_proveedor_devprov_id`, `Stock_Producto_prod_id`, `devprov_cantidad`, `devprov_precio`) VALUES
(1, '784001', 1.00, 340000.00),
(2, '784003', 1.00, 4400000.00),
(3, '784005', 10.00, 7800.00),
(5, '784002', 5.00, 135000.00),
(6, '784009', 2.00, 85000.00),
(7, '784007', 1.00, 5300000.00),
(8, '784008', 1.00, 1100000.00),
(9, '784007', 1.00, 5300000.00);

-- auditoria (depende de usuario)
INSERT INTO `auditoria` (`aud_cod`, `Usuario_usu_id`, `aud_fecha`, `aud_hora`, `aud_operacion`, `aud_tabla`) VALUES
(1, 1, CURDATE(), CURTIME(), 'INSERT', 'cliente'),
(2, 1, CURDATE(), CURTIME(), 'UPDATE', 'cliente'),
(3, 2, CURDATE(), CURTIME(), 'INSERT', 'venta'),
(4, 5, CURDATE(), CURTIME(), 'DELETE', 'producto'),
(5, 7, CURDATE(), CURTIME(), 'INSERT', 'compra'),
(6, 9, CURDATE(), CURTIME(), 'LOGIN', 'usuario'),
(7, 1, DATE_SUB(CURDATE(), INTERVAL 1 DAY), '10:00:00', 'INSERT', 'proveedor'),
(8, 2, DATE_SUB(CURDATE(), INTERVAL 1 DAY), '11:00:00', 'UPDATE', 'stock'),
(9, 5, DATE_SUB(CURDATE(), INTERVAL 1 DAY), '12:00:00', 'DELETE', 'venta'),
(10, 7, DATE_SUB(CURDATE(), INTERVAL 1 DAY), '13:00:00', 'INSERT', 'arqueo');

SET FOREIGN_KEY_CHECKS = 1;