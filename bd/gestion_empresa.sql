-- MySQL dump 10.13  Distrib 8.0.43, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: gestion_empresa
-- ------------------------------------------------------
-- Server version	9.4.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ajustes`
--

DROP TABLE IF EXISTS `ajustes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ajustes` (
  `ajus_estado` varchar(3) DEFAULT NULL,
  `ajus_fecha` date NOT NULL,
  `ajus_hora` time(6) NOT NULL,
  `ajus_id` int NOT NULL AUTO_INCREMENT,
  `usuario_usu_id` int DEFAULT NULL,
  PRIMARY KEY (`ajus_id`),
  KEY `FKi2iym8lvasjit3bvfgif30w4f` (`usuario_usu_id`),
  CONSTRAINT `FKi2iym8lvasjit3bvfgif30w4f` FOREIGN KEY (`usuario_usu_id`) REFERENCES `usuario` (`usu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ajustes`
--

LOCK TABLES `ajustes` WRITE;
/*!40000 ALTER TABLE `ajustes` DISABLE KEYS */;
/*!40000 ALTER TABLE `ajustes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ajustes_detalle`
--

DROP TABLE IF EXISTS `ajustes_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ajustes_detalle` (
  `ajustes_ajus_id` int NOT NULL,
  `ajustip_id` int NOT NULL,
  `detajustes_cantidad` decimal(5,2) DEFAULT NULL,
  `prod_id` varchar(255) NOT NULL,
  PRIMARY KEY (`ajustes_ajus_id`,`ajustip_id`,`prod_id`),
  KEY `FKmewo011uf9egmlr0tw94ew308` (`prod_id`),
  KEY `FKqa40uy8od2ql6ktfp4j6vf6v8` (`ajustip_id`),
  CONSTRAINT `FKmewo011uf9egmlr0tw94ew308` FOREIGN KEY (`prod_id`) REFERENCES `producto` (`prod_id`),
  CONSTRAINT `FKqa40uy8od2ql6ktfp4j6vf6v8` FOREIGN KEY (`ajustip_id`) REFERENCES `ajustes_tipo` (`ajustip_id`),
  CONSTRAINT `FKrt6k30xtma3vdefmvc8k5gch0` FOREIGN KEY (`ajustes_ajus_id`) REFERENCES `ajustes` (`ajus_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ajustes_detalle`
--

LOCK TABLES `ajustes_detalle` WRITE;
/*!40000 ALTER TABLE `ajustes_detalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `ajustes_detalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ajustes_tipo`
--

DROP TABLE IF EXISTS `ajustes_tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ajustes_tipo` (
  `ajustip_id` int NOT NULL AUTO_INCREMENT,
  `ajustip_tipo` varchar(3) DEFAULT NULL,
  `ajustip_motivo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`ajustip_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ajustes_tipo`
--

LOCK TABLES `ajustes_tipo` WRITE;
/*!40000 ALTER TABLE `ajustes_tipo` DISABLE KEYS */;
/*!40000 ALTER TABLE `ajustes_tipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `arqueo`
--

DROP TABLE IF EXISTS `arqueo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `arqueo` (
  `arq_cheque` int DEFAULT NULL,
  `arq_estado` varchar(1) DEFAULT NULL,
  `arq_fecha` date DEFAULT NULL,
  `arq_horadesde` time(6) DEFAULT NULL,
  `arq_horahasta` time(6) DEFAULT NULL,
  `arq_id` int NOT NULL AUTO_INCREMENT,
  `arq_montoinicio` int DEFAULT NULL,
  `arq_totalefectivo` int DEFAULT NULL,
  `caja_caj_id` int DEFAULT NULL,
  `usuario_usu_id` int DEFAULT NULL,
  PRIMARY KEY (`arq_id`),
  KEY `FK7y29xwcbcrk72o5q3giioq0rk` (`caja_caj_id`),
  KEY `FKl0mu1antce2tnxt07r4gkb114` (`usuario_usu_id`),
  CONSTRAINT `FK7y29xwcbcrk72o5q3giioq0rk` FOREIGN KEY (`caja_caj_id`) REFERENCES `caja` (`caj_id`),
  CONSTRAINT `FKl0mu1antce2tnxt07r4gkb114` FOREIGN KEY (`usuario_usu_id`) REFERENCES `usuario` (`usu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arqueo`
--

LOCK TABLES `arqueo` WRITE;
/*!40000 ALTER TABLE `arqueo` DISABLE KEYS */;
/*!40000 ALTER TABLE `arqueo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auditoria`
--

DROP TABLE IF EXISTS `auditoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auditoria` (
  `aud_cod` int NOT NULL AUTO_INCREMENT,
  `aud_fecha` date NOT NULL,
  `aud_hora` time(6) NOT NULL,
  `usuario_usu_id` int DEFAULT NULL,
  `aud_operacion` varchar(10) DEFAULT NULL,
  `aud_tabla` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`aud_cod`),
  KEY `FKbyt09klug9uqpe62fjeo3tyuw` (`usuario_usu_id`),
  CONSTRAINT `FKbyt09klug9uqpe62fjeo3tyuw` FOREIGN KEY (`usuario_usu_id`) REFERENCES `usuario` (`usu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auditoria`
--

LOCK TABLES `auditoria` WRITE;
/*!40000 ALTER TABLE `auditoria` DISABLE KEYS */;
/*!40000 ALTER TABLE `auditoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `caja`
--

DROP TABLE IF EXISTS `caja`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `caja` (
  `caj_id` int NOT NULL AUTO_INCREMENT,
  `caj_descripcion` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`caj_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `caja`
--

LOCK TABLES `caja` WRITE;
/*!40000 ALTER TABLE `caja` DISABLE KEYS */;
/*!40000 ALTER TABLE `caja` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ciudad`
--

DROP TABLE IF EXISTS `ciudad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ciudad` (
  `ciu_id` int NOT NULL AUTO_INCREMENT,
  `ciu_nombre` varchar(30) NOT NULL,
  `departamento_dep_id` int NOT NULL,
  PRIMARY KEY (`ciu_id`),
  KEY `FKm5kux5txw5wnqsk3nr8e2931y` (`departamento_dep_id`),
  CONSTRAINT `fk_ciudad_departamento` FOREIGN KEY (`departamento_dep_id`) REFERENCES `departamento` (`dep_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `FKm5kux5txw5wnqsk3nr8e2931y` FOREIGN KEY (`departamento_dep_id`) REFERENCES `departamento` (`dep_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ciudad`
--

LOCK TABLES `ciudad` WRITE;
/*!40000 ALTER TABLE `ciudad` DISABLE KEYS */;
INSERT INTO `ciudad` VALUES (1,'Luque',1),(2,'Hernandarias',2),(3,'Cambyretá',3),(4,'Independencia',4),(5,'Caaguazú',5),(6,'San Estanislao',6),(7,'Piribebuy',7),(8,'Yaguarón',8),(9,'Horqueta',9),(10,'Bella Vista Norte',10);
/*!40000 ALTER TABLE `ciudad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `ciu_id` int DEFAULT NULL,
  `cli_id` int NOT NULL AUTO_INCREMENT,
  `cli_sexo` varchar(1) NOT NULL,
  `cli_ci` varchar(10) DEFAULT NULL,
  `cli_ruc` varchar(10) DEFAULT NULL,
  `cli_telefono` varchar(15) DEFAULT NULL,
  `cli_apellido` varchar(25) NOT NULL,
  `cli_nombre` varchar(25) NOT NULL,
  `cli_email` varchar(35) DEFAULT NULL,
  `cli_direccion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cli_id`),
  KEY `FKgepwugbq77i0d3lu9t20c4dj6` (`ciu_id`),
  CONSTRAINT `FKgepwugbq77i0d3lu9t20c4dj6` FOREIGN KEY (`ciu_id`) REFERENCES `ciudad` (`ciu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cobro`
--

DROP TABLE IF EXISTS `cobro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cobro` (
  `arqueo_arq_id` int NOT NULL,
  `cob_estado` varchar(3) DEFAULT NULL,
  `cob_fecha` date DEFAULT NULL,
  `cob_hora` time(6) DEFAULT NULL,
  `cob_id` int NOT NULL AUTO_INCREMENT,
  `usuario_usu_id` int DEFAULT NULL,
  PRIMARY KEY (`cob_id`),
  KEY `FKo5jur4fsh13si2s82usjgbsqw` (`arqueo_arq_id`),
  KEY `FKnhj6h501xtyporix6df4kfrbm` (`usuario_usu_id`),
  CONSTRAINT `FKnhj6h501xtyporix6df4kfrbm` FOREIGN KEY (`usuario_usu_id`) REFERENCES `usuario` (`usu_id`),
  CONSTRAINT `FKo5jur4fsh13si2s82usjgbsqw` FOREIGN KEY (`arqueo_arq_id`) REFERENCES `arqueo` (`arq_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cobro`
--

LOCK TABLES `cobro` WRITE;
/*!40000 ALTER TABLE `cobro` DISABLE KEYS */;
/*!40000 ALTER TABLE `cobro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cobro_cotiza`
--

DROP TABLE IF EXISTS `cobro_cotiza`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cobro_cotiza` (
  `cob_id` int NOT NULL,
  `cot_codigo` int NOT NULL,
  `cuentcob_cuotanumero` int NOT NULL,
  `ven_id` int NOT NULL,
  PRIMARY KEY (`cob_id`,`cot_codigo`,`cuentcob_cuotanumero`,`ven_id`),
  KEY `FKj0cs5x34k41pl3vx3xhhcsqap` (`cot_codigo`),
  KEY `FK7qdg1iq83e87hqnyxnejajvuy` (`cuentcob_cuotanumero`,`ven_id`),
  CONSTRAINT `FK7qdg1iq83e87hqnyxnejajvuy` FOREIGN KEY (`cuentcob_cuotanumero`, `ven_id`) REFERENCES `cuenta_a_cobrar` (`cuentcob_cuotanumero`, `venta_ven_id`),
  CONSTRAINT `FKav2g4m54dk4boe5isa9x43dax` FOREIGN KEY (`cob_id`) REFERENCES `cobro` (`cob_id`),
  CONSTRAINT `FKj0cs5x34k41pl3vx3xhhcsqap` FOREIGN KEY (`cot_codigo`) REFERENCES `cotizacion` (`cot_codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cobro_cotiza`
--

LOCK TABLES `cobro_cotiza` WRITE;
/*!40000 ALTER TABLE `cobro_cotiza` DISABLE KEYS */;
/*!40000 ALTER TABLE `cobro_cotiza` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cobro_detalle`
--

DROP TABLE IF EXISTS `cobro_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cobro_detalle` (
  `cobro_cob_id` int NOT NULL,
  `cuenta_a_cobrar_cuentcob_cuotanumero` int NOT NULL,
  `cuenta_a_cobrar_venta_ven_id` int NOT NULL,
  PRIMARY KEY (`cobro_cob_id`,`cuenta_a_cobrar_cuentcob_cuotanumero`,`cuenta_a_cobrar_venta_ven_id`),
  CONSTRAINT `FK1rkw1c8t0w2c1k6q5hvrmhd8e` FOREIGN KEY (`cobro_cob_id`) REFERENCES `cobro` (`cob_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cobro_detalle`
--

LOCK TABLES `cobro_detalle` WRITE;
/*!40000 ALTER TABLE `cobro_detalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `cobro_detalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cobro_por`
--

DROP TABLE IF EXISTS `cobro_por`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cobro_por` (
  `cobro_detalle_cobro_cob_id` int NOT NULL,
  `cobro_detalle_cuenta_a_cobrar_cuentcob_cuotanumero` int NOT NULL,
  `cobro_detalle_cuenta_a_cobrar_venta_ven_id` int NOT NULL,
  `cobro_tipo_cobtip_id` int NOT NULL,
  `monto` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cobro_detalle_cobro_cob_id`,`cobro_detalle_cuenta_a_cobrar_cuentcob_cuotanumero`,`cobro_detalle_cuenta_a_cobrar_venta_ven_id`,`cobro_tipo_cobtip_id`),
  KEY `FK3q6tuex8db7y989071t9019un` (`cobro_tipo_cobtip_id`),
  CONSTRAINT `FK3q6tuex8db7y989071t9019un` FOREIGN KEY (`cobro_tipo_cobtip_id`) REFERENCES `cobro_tipo` (`cobtip_id`),
  CONSTRAINT `FKidtel9rk34em2b45q4rfbi1u3` FOREIGN KEY (`cobro_detalle_cobro_cob_id`) REFERENCES `cobro` (`cob_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cobro_por`
--

LOCK TABLES `cobro_por` WRITE;
/*!40000 ALTER TABLE `cobro_por` DISABLE KEYS */;
/*!40000 ALTER TABLE `cobro_por` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cobro_tipo`
--

DROP TABLE IF EXISTS `cobro_tipo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cobro_tipo` (
  `cobtip_id` int NOT NULL AUTO_INCREMENT,
  `cobtip_nombre` varchar(20) DEFAULT NULL,
  `cobtip_descripcion` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`cobtip_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cobro_tipo`
--

LOCK TABLES `cobro_tipo` WRITE;
/*!40000 ALTER TABLE `cobro_tipo` DISABLE KEYS */;
/*!40000 ALTER TABLE `cobro_tipo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra`
--

DROP TABLE IF EXISTS `compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compra` (
  `comp_estado` varchar(3) DEFAULT NULL,
  `comp_fecha` date DEFAULT NULL,
  `comp_fecharegistro` date DEFAULT NULL,
  `comp_horacompra` time(6) DEFAULT NULL,
  `comp_tipo` varchar(3) DEFAULT NULL,
  `orden_de_compra_ordcomp_id` int DEFAULT NULL,
  `proveedor_prov_id` int DEFAULT NULL,
  `usuario_usu_id` int DEFAULT NULL,
  `comp_numerofactura` varchar(255) NOT NULL,
  PRIMARY KEY (`comp_numerofactura`),
  KEY `FKef7v03h9hfq53txg6f4527m3l` (`orden_de_compra_ordcomp_id`),
  KEY `FK39ono7hft5j0q3jlhr42rmsf` (`proveedor_prov_id`),
  KEY `FKfxhvax005q3si8gdujtdg4fq9` (`usuario_usu_id`),
  CONSTRAINT `FK39ono7hft5j0q3jlhr42rmsf` FOREIGN KEY (`proveedor_prov_id`) REFERENCES `proveedor` (`prov_id`),
  CONSTRAINT `FKef7v03h9hfq53txg6f4527m3l` FOREIGN KEY (`orden_de_compra_ordcomp_id`) REFERENCES `orden_de_compra` (`ordcomp_id`),
  CONSTRAINT `FKfxhvax005q3si8gdujtdg4fq9` FOREIGN KEY (`usuario_usu_id`) REFERENCES `usuario` (`usu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra`
--

LOCK TABLES `compra` WRITE;
/*!40000 ALTER TABLE `compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compra_detalle`
--

DROP TABLE IF EXISTS `compra_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compra_detalle` (
  `compdet_cantidad` decimal(5,2) DEFAULT NULL,
  `compdet_preciounitario` decimal(12,2) DEFAULT NULL,
  `comp_numerofactura` varchar(255) NOT NULL,
  `stock_producto_prod_id` varchar(255) NOT NULL,
  PRIMARY KEY (`comp_numerofactura`,`stock_producto_prod_id`),
  KEY `FK9kiws2c6ym0t41ilpqd18xv3n` (`stock_producto_prod_id`),
  CONSTRAINT `FK7iit9315v85tih7t1nstv32hc` FOREIGN KEY (`comp_numerofactura`) REFERENCES `compra` (`comp_numerofactura`),
  CONSTRAINT `FK9kiws2c6ym0t41ilpqd18xv3n` FOREIGN KEY (`stock_producto_prod_id`) REFERENCES `producto` (`prod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compra_detalle`
--

LOCK TABLES `compra_detalle` WRITE;
/*!40000 ALTER TABLE `compra_detalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `compra_detalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cotizacion`
--

DROP TABLE IF EXISTS `cotizacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cotizacion` (
  `cot_codigo` int NOT NULL AUTO_INCREMENT,
  `cot_fecha` date DEFAULT NULL,
  `cot_hora` time(6) DEFAULT NULL,
  `cot_precompra` decimal(12,2) DEFAULT NULL,
  `cot_preventa` decimal(12,2) DEFAULT NULL,
  `moneda_mon_codigo` int DEFAULT NULL,
  PRIMARY KEY (`cot_codigo`),
  KEY `FKme0d43bu8ippfuv7w7q7jar5x` (`moneda_mon_codigo`),
  CONSTRAINT `FKme0d43bu8ippfuv7w7q7jar5x` FOREIGN KEY (`moneda_mon_codigo`) REFERENCES `moneda` (`mon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cotizacion`
--

LOCK TABLES `cotizacion` WRITE;
/*!40000 ALTER TABLE `cotizacion` DISABLE KEYS */;
/*!40000 ALTER TABLE `cotizacion` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta_a_cobrar`
--

DROP TABLE IF EXISTS `cuenta_a_cobrar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuenta_a_cobrar` (
  `cuentcob_cuotanumero` int NOT NULL,
  `cuentcob_fechavencimiento` date DEFAULT NULL,
  `cuentcob_monto` decimal(12,2) DEFAULT NULL,
  `venta_ven_id` int NOT NULL,
  `cuentcob_estado` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cuentcob_cuotanumero`,`venta_ven_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta_a_cobrar`
--

LOCK TABLES `cuenta_a_cobrar` WRITE;
/*!40000 ALTER TABLE `cuenta_a_cobrar` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuenta_a_cobrar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta_a_pagar`
--

DROP TABLE IF EXISTS `cuenta_a_pagar`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuenta_a_pagar` (
  `cuenpag_monto` decimal(12,2) DEFAULT NULL,
  `cuenpag_numerocuota` int NOT NULL,
  `compra_numerofactura` varchar(255) NOT NULL,
  `cuenpag_estado` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cuenpag_numerocuota`,`compra_numerofactura`),
  KEY `FKlp0s29ojxsbmlmb06y69pv4n0` (`compra_numerofactura`),
  CONSTRAINT `FKlp0s29ojxsbmlmb06y69pv4n0` FOREIGN KEY (`compra_numerofactura`) REFERENCES `compra` (`comp_numerofactura`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta_a_pagar`
--

LOCK TABLES `cuenta_a_pagar` WRITE;
/*!40000 ALTER TABLE `cuenta_a_pagar` DISABLE KEYS */;
/*!40000 ALTER TABLE `cuenta_a_pagar` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `departamento`
--

DROP TABLE IF EXISTS `departamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `departamento` (
  `dep_id` int NOT NULL AUTO_INCREMENT,
  `dep_nombre` varchar(30) NOT NULL,
  `dep_capital` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`dep_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `departamento`
--

LOCK TABLES `departamento` WRITE;
/*!40000 ALTER TABLE `departamento` DISABLE KEYS */;
INSERT INTO `departamento` VALUES (1,'Central','Areguá'),(2,'Alto Paraná','Ciudad del Este'),(3,'Itapúa','Encarnación'),(4,'Guairá','Villarrica'),(5,'Caaguazú','Coronel Oviedo'),(6,'San Pedro','San Pedro de Ycuamandiyú'),(7,'Cordillera','Caacupé'),(8,'Paraguarí','Paraguarí'),(9,'Concepción','Concepción'),(10,'Amambay','Pedro Juan Caballero');
/*!40000 ALTER TABLE `departamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `devo_prov_deta`
--

DROP TABLE IF EXISTS `devo_prov_deta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `devo_prov_deta` (
  `devolucion_a_proveedor_devprov_id` int NOT NULL,
  `devprov_cantidad` decimal(5,2) DEFAULT NULL,
  `devprov_precio` decimal(12,2) DEFAULT NULL,
  `stock_producto_prod_id` varchar(255) NOT NULL,
  PRIMARY KEY (`devolucion_a_proveedor_devprov_id`,`stock_producto_prod_id`),
  KEY `FK684wvysqho1uikq2khbhb6d5p` (`stock_producto_prod_id`),
  CONSTRAINT `FK684wvysqho1uikq2khbhb6d5p` FOREIGN KEY (`stock_producto_prod_id`) REFERENCES `producto` (`prod_id`),
  CONSTRAINT `FKhic1hgj3h6jye7m6dbpb8uof8` FOREIGN KEY (`devolucion_a_proveedor_devprov_id`) REFERENCES `devolucion_a_proveedor` (`devprov_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devo_prov_deta`
--

LOCK TABLES `devo_prov_deta` WRITE;
/*!40000 ALTER TABLE `devo_prov_deta` DISABLE KEYS */;
/*!40000 ALTER TABLE `devo_prov_deta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `devolucion_a_proveedor`
--

DROP TABLE IF EXISTS `devolucion_a_proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `devolucion_a_proveedor` (
  `dev_prov_estado` varchar(3) DEFAULT NULL,
  `devprov_fecha` date DEFAULT NULL,
  `devprov_id` int NOT NULL AUTO_INCREMENT,
  `proveedor_prov_id` int DEFAULT NULL,
  `usuario_usu_id` int DEFAULT NULL,
  PRIMARY KEY (`devprov_id`),
  KEY `FKq7q7sh59oyf6v8rxeebf1p2k2` (`proveedor_prov_id`),
  KEY `FKsnykojsi87tijvgr8exilo8op` (`usuario_usu_id`),
  CONSTRAINT `FKq7q7sh59oyf6v8rxeebf1p2k2` FOREIGN KEY (`proveedor_prov_id`) REFERENCES `proveedor` (`prov_id`),
  CONSTRAINT `FKsnykojsi87tijvgr8exilo8op` FOREIGN KEY (`usuario_usu_id`) REFERENCES `usuario` (`usu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `devolucion_a_proveedor`
--

LOCK TABLES `devolucion_a_proveedor` WRITE;
/*!40000 ALTER TABLE `devolucion_a_proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `devolucion_a_proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empleado`
--

DROP TABLE IF EXISTS `empleado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empleado` (
  `emp_id` int NOT NULL AUTO_INCREMENT,
  `emp_nombre` varchar(20) DEFAULT NULL,
  `emp_apellido` varchar(20) DEFAULT NULL,
  `emp_ci` varchar(10) DEFAULT NULL,
  `emp_sueldo` double(12,2) DEFAULT NULL,
  `emp_direccion` varchar(45) DEFAULT NULL,
  `emp_telefono` varchar(15) DEFAULT NULL,
  `emp_fechanacimiento` date DEFAULT NULL,
  `emp_observacion` varchar(100) DEFAULT NULL,
  `emp_email` varchar(35) DEFAULT NULL,
  `emp_sexo` varchar(1) DEFAULT NULL,
  `emp_estado` int DEFAULT NULL,
  `Ciudad_ciu_id` int DEFAULT NULL,
  PRIMARY KEY (`emp_id`),
  KEY `FKoamwykyijc3fp9g70g6mooq49` (`Ciudad_ciu_id`),
  CONSTRAINT `fk_empleado_ciudad` FOREIGN KEY (`Ciudad_ciu_id`) REFERENCES `ciudad` (`ciu_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKoamwykyijc3fp9g70g6mooq49` FOREIGN KEY (`Ciudad_ciu_id`) REFERENCES `ciudad` (`ciu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empleado`
--

LOCK TABLES `empleado` WRITE;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` VALUES (1,'LUCAS','MEDINA','111111',4500000.00,'Av. Aviadores del Chaco 123','0981123456','1990-05-15','Desarrollador principal del sistema.','lucas.medina@empresa.com','M',1,1),(2,'Ana','Perez','222222',4200000.00,'Calle Palma 456','0982234567','1992-08-20','Encargada de ventas en CDE.','ana.perez@empresa.com','F',1,2),(3,'Luis','Martinez','333333',5000000.00,'Av. San Roque 789','0983345678','1988-11-10','Gerente de sucursal Encarnación.','luis.martinez@empresa.com','M',1,3),(4,'Maria','Rodriguez','444444',3800000.00,'Ruta 8 Blas Garay','0984456789','1995-02-25','Asistente administrativa.','maria.rodriguez@empresa.com','F',1,4),(5,'Juan','Lopez','555555',6000000.00,'Mcal. Lopez 101','0985567890','1985-07-30','Jefe de logística.','juan.lopez@empresa.com','M',1,5),(6,'Laura','Fernandez','666666',3500000.00,'Calle 14 de Mayo','0971678901','1998-01-12','Empleado inactivo.','laura.fernandez@empresa.com','F',0,6),(7,'Pedro','Gonzalez','777777',7000000.00,'Av. Eusebio Ayala 202','0972789012','1980-03-05','Supervisor de área.','pedro.gonzalez@empresa.com','M',1,7),(8,'Sofia','Diaz','888888',4800000.00,'Av. Artigas 303','0973890123','1993-09-18','Contadora.','sofia.diaz@empresa.com','F',1,8),(9,'Diego','Sanchez','999999',5500000.00,'Defensores del Chaco 404','0974901234','1991-12-22','Encargado de compras.','diego.sanchez@empresa.com','M',1,9),(10,'Elena','Torres','101010',4000000.00,'Calle Ultima 505','0975012345','1996-06-08','Ex-empleada, renunció.','elena.torres@empresa.com','F',0,10);
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marca`
--

DROP TABLE IF EXISTS `marca`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marca` (
  `mar_id` int NOT NULL AUTO_INCREMENT,
  `mar_descripcion` varchar(45) NOT NULL,
  PRIMARY KEY (`mar_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marca`
--

LOCK TABLES `marca` WRITE;
/*!40000 ALTER TABLE `marca` DISABLE KEYS */;
INSERT INTO `marca` VALUES (1,'Nike'),(2,'Adidas'),(3,'Samsung'),(4,'Apple'),(5,'Coca-Cola'),(6,'Pepsi'),(7,'HP'),(8,'Dell'),(9,'Toyota'),(10,'Ford');
/*!40000 ALTER TABLE `marca` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `moneda`
--

DROP TABLE IF EXISTS `moneda`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `moneda` (
  `mon_id` int NOT NULL AUTO_INCREMENT,
  `mon_sigla` varchar(10) NOT NULL,
  `mon_descripcion` varchar(20) NOT NULL,
  PRIMARY KEY (`mon_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `moneda`
--

LOCK TABLES `moneda` WRITE;
/*!40000 ALTER TABLE `moneda` DISABLE KEYS */;
/*!40000 ALTER TABLE `moneda` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orden_de_compra`
--

DROP TABLE IF EXISTS `orden_de_compra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orden_de_compra` (
  `ordcomp_estado` varchar(3) DEFAULT NULL,
  `ordcomp_fecha` date DEFAULT NULL,
  `ordcomp_hora` time(6) DEFAULT NULL,
  `ordcomp_id` int NOT NULL AUTO_INCREMENT,
  `pedprov_id` int DEFAULT NULL,
  `usuario_usu_id` int DEFAULT NULL,
  PRIMARY KEY (`ordcomp_id`),
  KEY `FKci0jvgkfh27xpymba16eldmt5` (`pedprov_id`),
  KEY `FKhm8s058cvjvvw9nfpplkjm16f` (`usuario_usu_id`),
  CONSTRAINT `FKci0jvgkfh27xpymba16eldmt5` FOREIGN KEY (`pedprov_id`) REFERENCES `pedido_proveedor` (`pedprov_id`),
  CONSTRAINT `FKhm8s058cvjvvw9nfpplkjm16f` FOREIGN KEY (`usuario_usu_id`) REFERENCES `usuario` (`usu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orden_de_compra`
--

LOCK TABLES `orden_de_compra` WRITE;
/*!40000 ALTER TABLE `orden_de_compra` DISABLE KEYS */;
/*!40000 ALTER TABLE `orden_de_compra` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orden_de_compra_detalle`
--

DROP TABLE IF EXISTS `orden_de_compra_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orden_de_compra_detalle` (
  `ordcompdet_cantidad` decimal(5,2) NOT NULL,
  `ordcompdet_preciounitario` decimal(12,2) NOT NULL,
  `orddetitem` int NOT NULL AUTO_INCREMENT,
  `orden_de_compra_ordcomp_id` int DEFAULT NULL,
  `producto_prod_id` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`orddetitem`),
  KEY `FKaygnas29boqtx9vuv1qtd4v4x` (`orden_de_compra_ordcomp_id`),
  KEY `FKhy8aaaqycp89i5jmxctuwl55h` (`producto_prod_id`),
  CONSTRAINT `FKaygnas29boqtx9vuv1qtd4v4x` FOREIGN KEY (`orden_de_compra_ordcomp_id`) REFERENCES `orden_de_compra` (`ordcomp_id`),
  CONSTRAINT `FKhy8aaaqycp89i5jmxctuwl55h` FOREIGN KEY (`producto_prod_id`) REFERENCES `producto` (`prod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orden_de_compra_detalle`
--

LOCK TABLES `orden_de_compra_detalle` WRITE;
/*!40000 ALTER TABLE `orden_de_compra_detalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `orden_de_compra_detalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orden_de_pago`
--

DROP TABLE IF EXISTS `orden_de_pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orden_de_pago` (
  `ordpag_estado` varchar(3) DEFAULT NULL,
  `ordpag_fecha` date DEFAULT NULL,
  `ordpag_numero` int NOT NULL AUTO_INCREMENT,
  `usuario_usu_id` int DEFAULT NULL,
  PRIMARY KEY (`ordpag_numero`),
  KEY `FKk1o8f4mo1pvjvqmfqqdwtjvu2` (`usuario_usu_id`),
  CONSTRAINT `FKk1o8f4mo1pvjvqmfqqdwtjvu2` FOREIGN KEY (`usuario_usu_id`) REFERENCES `usuario` (`usu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orden_de_pago`
--

LOCK TABLES `orden_de_pago` WRITE;
/*!40000 ALTER TABLE `orden_de_pago` DISABLE KEYS */;
/*!40000 ALTER TABLE `orden_de_pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orden_de_pago_detalle`
--

DROP TABLE IF EXISTS `orden_de_pago_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orden_de_pago_detalle` (
  `cuenpag_numerocuota` int NOT NULL,
  `orden_de_pago_ordpag_numero` int NOT NULL,
  `ordpagdet_monto` decimal(12,2) DEFAULT NULL,
  `comp_numerofactura` varchar(255) NOT NULL,
  PRIMARY KEY (`cuenpag_numerocuota`,`orden_de_pago_ordpag_numero`,`comp_numerofactura`),
  KEY `FK1thrslwv39ewvjx7cmcuk3wp8` (`cuenpag_numerocuota`,`comp_numerofactura`),
  KEY `FKfjbwkqng9983wpg7int1r07dk` (`orden_de_pago_ordpag_numero`),
  CONSTRAINT `FK1thrslwv39ewvjx7cmcuk3wp8` FOREIGN KEY (`cuenpag_numerocuota`, `comp_numerofactura`) REFERENCES `cuenta_a_pagar` (`cuenpag_numerocuota`, `compra_numerofactura`),
  CONSTRAINT `FKfjbwkqng9983wpg7int1r07dk` FOREIGN KEY (`orden_de_pago_ordpag_numero`) REFERENCES `orden_de_pago` (`ordpag_numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orden_de_pago_detalle`
--

LOCK TABLES `orden_de_pago_detalle` WRITE;
/*!40000 ALTER TABLE `orden_de_pago_detalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `orden_de_pago_detalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pago`
--

DROP TABLE IF EXISTS `pago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pago` (
  `orden_de_pago_ordpag_numero` int DEFAULT NULL,
  `pag_estado` varchar(1) DEFAULT NULL,
  `pag_fecha` date DEFAULT NULL,
  `pag_id` int NOT NULL AUTO_INCREMENT,
  `pag_monto` decimal(12,2) DEFAULT NULL,
  `usuario_usu_id` int DEFAULT NULL,
  PRIMARY KEY (`pag_id`),
  KEY `FKe8boh6bjc80lnj8c8xn8kk8pa` (`orden_de_pago_ordpag_numero`),
  KEY `FKbxlyb02l17aii0ieg2xy5g8k4` (`usuario_usu_id`),
  CONSTRAINT `FKbxlyb02l17aii0ieg2xy5g8k4` FOREIGN KEY (`usuario_usu_id`) REFERENCES `usuario` (`usu_id`),
  CONSTRAINT `FKe8boh6bjc80lnj8c8xn8kk8pa` FOREIGN KEY (`orden_de_pago_ordpag_numero`) REFERENCES `orden_de_pago` (`ordpag_numero`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pago`
--

LOCK TABLES `pago` WRITE;
/*!40000 ALTER TABLE `pago` DISABLE KEYS */;
/*!40000 ALTER TABLE `pago` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_a_proveedor`
--

DROP TABLE IF EXISTS `pedido_a_proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_a_proveedor` (
  `pedprov_id` int NOT NULL AUTO_INCREMENT,
  `Proveedor_prov_id` int DEFAULT NULL,
  `pedprov_fecha` date DEFAULT NULL,
  `pedprov_estado` varchar(3) DEFAULT NULL,
  `Usuario_usu_id` int DEFAULT NULL,
  PRIMARY KEY (`pedprov_id`),
  KEY `fk_pedidoprov_proveedor` (`Proveedor_prov_id`),
  KEY `fk_pedidoprov_usuario` (`Usuario_usu_id`),
  CONSTRAINT `fk_pedidoprov_proveedor` FOREIGN KEY (`Proveedor_prov_id`) REFERENCES `proveedor` (`prov_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_pedidoprov_usuario` FOREIGN KEY (`Usuario_usu_id`) REFERENCES `usuario` (`usu_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_a_proveedor`
--

LOCK TABLES `pedido_a_proveedor` WRITE;
/*!40000 ALTER TABLE `pedido_a_proveedor` DISABLE KEYS */;
INSERT INTO `pedido_a_proveedor` VALUES (1,1,'2025-10-26','PEN',5),(2,2,'2025-10-26','PEN',5),(3,3,'2025-10-21','REC',7),(4,4,'2025-10-16','CAN',7),(5,5,'2025-10-24','REC',5),(6,6,'2025-10-26','PEN',9),(7,7,'2025-10-25','PRO',9),(8,8,'2025-10-23','REC',1),(9,9,'2025-10-11','REC',1),(10,10,'2025-10-26','PEN',5);
/*!40000 ALTER TABLE `pedido_a_proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_a_proveedor_detalle`
--

DROP TABLE IF EXISTS `pedido_a_proveedor_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_a_proveedor_detalle` (
  `peddet_item` int NOT NULL AUTO_INCREMENT,
  `pedprov_id` int NOT NULL,
  `pedprovdet_cantidad` double(5,2) DEFAULT NULL,
  `pedprovdet_preciounitario` double(12,2) DEFAULT NULL,
  `Producto_prod_id` varchar(13) NOT NULL,
  PRIMARY KEY (`peddet_item`),
  KEY `fk_pedproddet_pedido` (`pedprov_id`),
  KEY `fk_pedproddet_producto` (`Producto_prod_id`),
  CONSTRAINT `fk_pedproddet_pedido` FOREIGN KEY (`pedprov_id`) REFERENCES `pedido_a_proveedor` (`pedprov_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_pedproddet_producto` FOREIGN KEY (`Producto_prod_id`) REFERENCES `producto` (`prod_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_a_proveedor_detalle`
--

LOCK TABLES `pedido_a_proveedor_detalle` WRITE;
/*!40000 ALTER TABLE `pedido_a_proveedor_detalle` DISABLE KEYS */;
INSERT INTO `pedido_a_proveedor_detalle` VALUES (1,1,10.00,340000.00,'784001'),(2,1,20.00,140000.00,'784002'),(3,2,5.00,4400000.00,'784003'),(4,3,50.00,85000.00,'784009'),(5,5,200.00,7800.00,'784005'),(6,5,150.00,6800.00,'784006'),(7,6,30.00,43000.00,'784010'),(8,7,50.00,135000.00,'784002'),(9,8,10.00,1100000.00,'784008'),(10,10,5.00,7800000.00,'784004');
/*!40000 ALTER TABLE `pedido_a_proveedor_detalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_cliente`
--

DROP TABLE IF EXISTS `pedido_cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_cliente` (
  `cliente_cli_id` int DEFAULT NULL,
  `pedcli_estado` varchar(3) DEFAULT NULL,
  `pedcli_fechapedido` date DEFAULT NULL,
  `pedcli_id` int NOT NULL AUTO_INCREMENT,
  `usuario_usu_id` int DEFAULT NULL,
  PRIMARY KEY (`pedcli_id`),
  KEY `FKcpitqwrk3bhnb5vb9gc57aq6n` (`cliente_cli_id`),
  KEY `FK1abrlxu9f9v5h58g65nesaaow` (`usuario_usu_id`),
  CONSTRAINT `FK1abrlxu9f9v5h58g65nesaaow` FOREIGN KEY (`usuario_usu_id`) REFERENCES `usuario` (`usu_id`),
  CONSTRAINT `FKcpitqwrk3bhnb5vb9gc57aq6n` FOREIGN KEY (`cliente_cli_id`) REFERENCES `cliente` (`cli_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_cliente`
--

LOCK TABLES `pedido_cliente` WRITE;
/*!40000 ALTER TABLE `pedido_cliente` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido_cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_cliente_detalle`
--

DROP TABLE IF EXISTS `pedido_cliente_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_cliente_detalle` (
  `pedclidet_cantidad` decimal(5,2) DEFAULT NULL,
  `pedclidet_preciounitario` decimal(12,2) DEFAULT NULL,
  `pedido_cliente_pedcli_id` int NOT NULL,
  `stock_producto_prod_id` varchar(255) NOT NULL,
  PRIMARY KEY (`pedido_cliente_pedcli_id`,`stock_producto_prod_id`),
  KEY `FK6a0kn5qxi6kod0cxfai3kx5r2` (`stock_producto_prod_id`),
  CONSTRAINT `FK6a0kn5qxi6kod0cxfai3kx5r2` FOREIGN KEY (`stock_producto_prod_id`) REFERENCES `producto` (`prod_id`),
  CONSTRAINT `FKt9p64v5lty0t5wfc2n8c7xgfj` FOREIGN KEY (`pedido_cliente_pedcli_id`) REFERENCES `pedido_cliente` (`pedcli_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_cliente_detalle`
--

LOCK TABLES `pedido_cliente_detalle` WRITE;
/*!40000 ALTER TABLE `pedido_cliente_detalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido_cliente_detalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_cliente_has_venta`
--

DROP TABLE IF EXISTS `pedido_cliente_has_venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_cliente_has_venta` (
  `pedido_cliente_pedcli_id` int NOT NULL,
  `venta_ven_id` int NOT NULL,
  PRIMARY KEY (`pedido_cliente_pedcli_id`,`venta_ven_id`),
  KEY `FKqqtm2gr11i205ftgh2mxi6jyd` (`venta_ven_id`),
  CONSTRAINT `FKlin86luocf8qgy0lhnrw2k53o` FOREIGN KEY (`pedido_cliente_pedcli_id`) REFERENCES `pedido_cliente` (`pedcli_id`),
  CONSTRAINT `FKqqtm2gr11i205ftgh2mxi6jyd` FOREIGN KEY (`venta_ven_id`) REFERENCES `venta` (`ven_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_cliente_has_venta`
--

LOCK TABLES `pedido_cliente_has_venta` WRITE;
/*!40000 ALTER TABLE `pedido_cliente_has_venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido_cliente_has_venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_proveedor`
--

DROP TABLE IF EXISTS `pedido_proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_proveedor` (
  `pedprov_estado` varchar(3) DEFAULT NULL,
  `pedprov_fecha` date DEFAULT NULL,
  `pedprov_id` int NOT NULL AUTO_INCREMENT,
  `proveedor_prov_id` int DEFAULT NULL,
  `usuario_usu_id` int DEFAULT NULL,
  PRIMARY KEY (`pedprov_id`),
  KEY `FKsmbmklindsb8ic42hywrg5ufi` (`proveedor_prov_id`),
  KEY `FK4bdo8os7ay976yqxfatytfc3m` (`usuario_usu_id`),
  CONSTRAINT `FK4bdo8os7ay976yqxfatytfc3m` FOREIGN KEY (`usuario_usu_id`) REFERENCES `usuario` (`usu_id`),
  CONSTRAINT `FKsmbmklindsb8ic42hywrg5ufi` FOREIGN KEY (`proveedor_prov_id`) REFERENCES `proveedor` (`prov_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_proveedor`
--

LOCK TABLES `pedido_proveedor` WRITE;
/*!40000 ALTER TABLE `pedido_proveedor` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido_proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido_proveedor_detalle`
--

DROP TABLE IF EXISTS `pedido_proveedor_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido_proveedor_detalle` (
  `peddet_item` int NOT NULL AUTO_INCREMENT,
  `pedprov_id` int DEFAULT NULL,
  `pedprovdet_cantidad` decimal(5,2) DEFAULT NULL,
  `pedprovdet_preciounitario` decimal(12,2) DEFAULT NULL,
  `producto_prod_id` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`peddet_item`),
  KEY `FKjbfpx6obt05ob53kypxgglkw4` (`pedprov_id`),
  KEY `FKb2vakarxyc4nnlp4bsbjcdlml` (`producto_prod_id`),
  CONSTRAINT `FKb2vakarxyc4nnlp4bsbjcdlml` FOREIGN KEY (`producto_prod_id`) REFERENCES `producto` (`prod_id`),
  CONSTRAINT `FKjbfpx6obt05ob53kypxgglkw4` FOREIGN KEY (`pedprov_id`) REFERENCES `pedido_proveedor` (`pedprov_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido_proveedor_detalle`
--

LOCK TABLES `pedido_proveedor_detalle` WRITE;
/*!40000 ALTER TABLE `pedido_proveedor_detalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `pedido_proveedor_detalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presu_pedi`
--

DROP TABLE IF EXISTS `presu_pedi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `presu_pedi` (
  `pedido_cliente_pedcli_id` int NOT NULL,
  `presupuesto_pres_id` int NOT NULL,
  PRIMARY KEY (`pedido_cliente_pedcli_id`,`presupuesto_pres_id`),
  KEY `FKcjlj9b9uegq2vx7svemf8qhdj` (`presupuesto_pres_id`),
  CONSTRAINT `FK32f328i0u7yl61a7v3cg8lv3w` FOREIGN KEY (`pedido_cliente_pedcli_id`) REFERENCES `pedido_cliente` (`pedcli_id`),
  CONSTRAINT `FKcjlj9b9uegq2vx7svemf8qhdj` FOREIGN KEY (`presupuesto_pres_id`) REFERENCES `presupuesto` (`pres_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presu_pedi`
--

LOCK TABLES `presu_pedi` WRITE;
/*!40000 ALTER TABLE `presu_pedi` DISABLE KEYS */;
/*!40000 ALTER TABLE `presu_pedi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presupuesto`
--

DROP TABLE IF EXISTS `presupuesto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `presupuesto` (
  `cliente_cli_id` int NOT NULL,
  `pres_estado` varchar(3) DEFAULT NULL,
  `pres_fecha` date DEFAULT NULL,
  `pres_id` int NOT NULL AUTO_INCREMENT,
  `usuario_usu_id` int NOT NULL,
  PRIMARY KEY (`pres_id`),
  KEY `FKqd5rx1363ao3ha233hwpqnamd` (`cliente_cli_id`),
  KEY `FKpfx7qaqmsf2gs3ff28fn71v64` (`usuario_usu_id`),
  CONSTRAINT `FKpfx7qaqmsf2gs3ff28fn71v64` FOREIGN KEY (`usuario_usu_id`) REFERENCES `usuario` (`usu_id`),
  CONSTRAINT `FKqd5rx1363ao3ha233hwpqnamd` FOREIGN KEY (`cliente_cli_id`) REFERENCES `cliente` (`cli_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presupuesto`
--

LOCK TABLES `presupuesto` WRITE;
/*!40000 ALTER TABLE `presupuesto` DISABLE KEYS */;
/*!40000 ALTER TABLE `presupuesto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `presupuesto_detalle`
--

DROP TABLE IF EXISTS `presupuesto_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `presupuesto_detalle` (
  `pres_id` int NOT NULL,
  `presdet_cantidad` decimal(5,2) DEFAULT NULL,
  `presdet_preciounitario` decimal(12,2) DEFAULT NULL,
  `prod_id` varchar(255) NOT NULL,
  PRIMARY KEY (`pres_id`,`prod_id`),
  KEY `FKejyi6lq434vj4oisrg696jou3` (`prod_id`),
  CONSTRAINT `FK89bs5osiacq97eimum631ahlu` FOREIGN KEY (`pres_id`) REFERENCES `presupuesto` (`pres_id`),
  CONSTRAINT `FKejyi6lq434vj4oisrg696jou3` FOREIGN KEY (`prod_id`) REFERENCES `producto` (`prod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `presupuesto_detalle`
--

LOCK TABLES `presupuesto_detalle` WRITE;
/*!40000 ALTER TABLE `presupuesto_detalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `presupuesto_detalle` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `prod_id` varchar(13) NOT NULL,
  `Marca_mar_id` int NOT NULL,
  `prod_nombre` varchar(30) NOT NULL,
  `prod_descripcion` varchar(50) DEFAULT NULL,
  `prod_preciocompra` double(12,2) DEFAULT NULL,
  `prod_iva` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`prod_id`),
  KEY `FK46sodqk35ctshylioigcu65b2` (`Marca_mar_id`),
  CONSTRAINT `FK46sodqk35ctshylioigcu65b2` FOREIGN KEY (`Marca_mar_id`) REFERENCES `marca` (`mar_id`),
  CONSTRAINT `fk_producto_marca` FOREIGN KEY (`Marca_mar_id`) REFERENCES `marca` (`mar_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES ('784001',1,'Zapatilla Runner','Calzado deportivo para correr',350000.00,'10'),('784002',2,'Camiseta Sport','Prenda de algodón para entrenamiento',150000.00,'10'),('784003',3,'TV 55\" QLED','Televisor inteligente con pantalla QLED',4500000.00,'10'),('784004',4,'iPhone 15 Pro','Teléfono móvil de alta gama',8000000.00,'10'),('784005',5,'Coca-Cola 2L','Bebida gaseosa sabor cola',8000.00,'10'),('784006',6,'Pepsi 1.5L','Bebida gaseosa alternativa',7000.00,'10'),('784007',7,'Laptop Pavilion','Portátil para trabajo y estudio',5500000.00,'10'),('784008',8,'Monitor 24\"','Monitor Full HD para PC',1200000.00,'10'),('784009',9,'Aceite Motor 1L','Lubricante sintético para motor',90000.00,'10'),('784010',10,'Limpia Parabrisas','Líquido para limpieza de vidrios',45000.00,'5');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `proveedor` (
  `prov_id` int NOT NULL AUTO_INCREMENT,
  `Ciudad_ciu_id` int DEFAULT NULL,
  `prov_nombre` varchar(45) DEFAULT NULL,
  `prov_direccion` varchar(45) DEFAULT NULL,
  `prov_telefono` varchar(15) DEFAULT NULL,
  `prov_pag_web` varchar(35) DEFAULT NULL,
  `prov_email` varchar(35) DEFAULT NULL,
  `prov_estado` int DEFAULT NULL,
  `prov_observacion` varchar(100) DEFAULT NULL,
  `prov_ruc` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`prov_id`),
  KEY `FK9bu9rjvico2uiwukpsdl2vupi` (`Ciudad_ciu_id`),
  CONSTRAINT `FK9bu9rjvico2uiwukpsdl2vupi` FOREIGN KEY (`Ciudad_ciu_id`) REFERENCES `ciudad` (`ciu_id`),
  CONSTRAINT `fk_proveedor_ciudad` FOREIGN KEY (`Ciudad_ciu_id`) REFERENCES `ciudad` (`ciu_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `proveedor`
--

LOCK TABLES `proveedor` WRITE;
/*!40000 ALTER TABLE `proveedor` DISABLE KEYS */;
INSERT INTO `proveedor` VALUES (1,1,'Distribuidora ABC','Av. Principal 123','021-123456','www.abc.com.py','ventas@abc.com.py',1,'Proveedor de productos varios.','80012345-1'),(2,2,'Tecno Import S.A.','Supercarretera Km 4','061-555111','www.tecnoimport.com.py','info@tecnoimport.com.py',1,'Importador de electrónicos.','80054321-2'),(3,3,'Logística Guaraní','Ruta 1 Km 360','071-200300','www.logistica.com.py','contacto@logistica.com.py',1,'Servicios de transporte.','80098765-3'),(4,4,'Agro-Insumos del Sur','Calle Rural 5','0541-444555','www.delsur.com','agro@delsur.com',1,'Insumos para el agro.','80011223-4'),(5,5,'Central de Bebidas','Av. de las Américas 500','0521-222333','www.bebidas.com.py','pedidos@bebidas.com.py',1,'Distribuidor mayorista de bebidas.','80044556-5'),(6,6,'Repuestos y Accesorios SRL','Taller Central 890','0342-654321','www.accesorios.com','repuestos@accesorios.com',1,'Autopartes y repuestos.','80077889-6'),(7,7,'Mundo Textil','Galería Central, Local 10','0511-987654','www.mundo-textil.com','textil@mundo.com',0,'Proveedor de telas. Inactivo.','80022334-7'),(8,8,'Office Supply S.A.','Edificio Corporativo, Piso 3','021-888777','www.office-supply.com','supply@office.com.py',1,'Artículos de oficina.','80055667-8'),(9,9,'Constructora del Norte','Obrador Principal','0331-234567','www.norte-constructora.com','constructora@norte.com.py',1,'Materiales de construcción.','80088990-9'),(10,10,'Salud y Bienestar','Av. Salud 100','0336-111222','www.salud-bienestar.com','bienestar@salud.com.py',1,'Productos farmacéuticos.','80012121-0');
/*!40000 ALTER TABLE `proveedor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock` (
  `stock_cantidadactual` decimal(5,2) DEFAULT NULL,
  `stock_cantidadmax` decimal(5,2) DEFAULT NULL,
  `stock_cantidadmin` decimal(5,2) DEFAULT NULL,
  `producto_prod_id` varchar(13) NOT NULL,
  PRIMARY KEY (`producto_prod_id`),
  CONSTRAINT `FKo61yc43l17hvammogtj85hn08` FOREIGN KEY (`producto_prod_id`) REFERENCES `producto` (`prod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `usu_id` int NOT NULL AUTO_INCREMENT,
  `usu_nombre` varchar(25) DEFAULT NULL,
  `usu_contrasena` varchar(255) DEFAULT NULL,
  `usu_estado` int DEFAULT NULL,
  `usu_tipo` varchar(20) DEFAULT NULL,
  `Empleado_emp_id` int DEFAULT NULL,
  PRIMARY KEY (`usu_id`),
  KEY `FKe2bs3t4j3wcausgn4098kxwpp` (`Empleado_emp_id`),
  CONSTRAINT `fk_usuario_empleado` FOREIGN KEY (`Empleado_emp_id`) REFERENCES `empleado` (`emp_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `FKe2bs3t4j3wcausgn4098kxwpp` FOREIGN KEY (`Empleado_emp_id`) REFERENCES `empleado` (`emp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'LUCAS.MEDINA','1234',1,'ADMIN',1),(2,'ana.perez','1234',1,'USER',2),(3,'luis.martinez','1234',1,'USER',3),(4,'maria.rodriguez','1234',1,'USER',4),(5,'juan.lopez','1234',1,'ADMIN',5),(6,'laura.fernandez','1234',0,'USER',6),(7,'pedro.gonzalez','1234',1,'SUPERVISOR',7),(8,'sofia.diaz','1234',1,'USER',8),(9,'diego.sanchez','1234',1,'USER',9),(10,'elena.torres','1234',0,'USER',10);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta`
--

DROP TABLE IF EXISTS `venta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venta` (
  `cliente_cli_id` int DEFAULT NULL,
  `usuario_usu_id` int DEFAULT NULL,
  `ven_descuento` decimal(12,2) DEFAULT NULL,
  `ven_estado` varchar(3) DEFAULT NULL,
  `ven_fechafacturacion` date DEFAULT NULL,
  `ven_hora` time(6) DEFAULT NULL,
  `ven_id` int NOT NULL AUTO_INCREMENT,
  `ven_tipo` varchar(3) DEFAULT NULL,
  PRIMARY KEY (`ven_id`),
  KEY `FKexpsob41uyrkxyp6cfv78uf26` (`cliente_cli_id`),
  KEY `FKi2yc270099hkqh6skn8pu1vqx` (`usuario_usu_id`),
  CONSTRAINT `FKexpsob41uyrkxyp6cfv78uf26` FOREIGN KEY (`cliente_cli_id`) REFERENCES `cliente` (`cli_id`),
  CONSTRAINT `FKi2yc270099hkqh6skn8pu1vqx` FOREIGN KEY (`usuario_usu_id`) REFERENCES `usuario` (`usu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta`
--

LOCK TABLES `venta` WRITE;
/*!40000 ALTER TABLE `venta` DISABLE KEYS */;
/*!40000 ALTER TABLE `venta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `venta_detalle`
--

DROP TABLE IF EXISTS `venta_detalle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `venta_detalle` (
  `detventa_cantidad` decimal(5,2) DEFAULT NULL,
  `detventa_preciounitario` decimal(12,2) DEFAULT NULL,
  `venta_ven_id` int NOT NULL,
  `stock_producto_prod_id` varchar(255) NOT NULL,
  PRIMARY KEY (`venta_ven_id`,`stock_producto_prod_id`),
  KEY `FKaddp8quw1eew0attalv3q8d79` (`stock_producto_prod_id`),
  CONSTRAINT `FK2oawyah88nhjojllcs22ynv8w` FOREIGN KEY (`venta_ven_id`) REFERENCES `venta` (`ven_id`),
  CONSTRAINT `FKaddp8quw1eew0attalv3q8d79` FOREIGN KEY (`stock_producto_prod_id`) REFERENCES `producto` (`prod_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `venta_detalle`
--

LOCK TABLES `venta_detalle` WRITE;
/*!40000 ALTER TABLE `venta_detalle` DISABLE KEYS */;
/*!40000 ALTER TABLE `venta_detalle` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-10-26 18:32:11
