CREATE DATABASE  IF NOT EXISTS `tadesgames` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `tadesgames`;
-- MySQL dump 10.13  Distrib 5.7.26, for Win64 (x86_64)
--
-- Host: 35.247.220.39    Database: tadesgames
-- ------------------------------------------------------
-- Server version	5.7.14-google-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
SET @MYSQLDUMP_TEMP_LOG_BIN = @@SESSION.SQL_LOG_BIN;
SET @@SESSION.SQL_LOG_BIN= 0;

--
-- GTID state at the beginning of the backup 
--

SET @@GLOBAL.GTID_PURGED='e82a3ae8-7e37-11e9-ab46-42010a9e0fdd:1-473175';

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categoria` (
  `IdCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(50) NOT NULL,
  `DataHoraCriacao` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`IdCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'Jogos','2019-05-24 16:20:18'),(2,'Consoles','2019-06-03 04:10:23'),(3,'Acessórios','2019-06-03 04:10:30'),(4,'Outros','2019-06-03 04:10:35');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `IdCliente` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(80) NOT NULL,
  `Cpf` varchar(11) NOT NULL,
  `Cnpj` varchar(14) NOT NULL,
  `DataNasc` date NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Telefone` varchar(10) DEFAULT NULL,
  `Celular` varchar(11) NOT NULL,
  `DataHoraCriacao` datetime DEFAULT CURRENT_TIMESTAMP,
  `sexo` varchar(50) DEFAULT NULL,
  `ativo` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`IdCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (1,'Consumidor','12345678910','','1900-01-01','consumidor@tadesgames.com.br',NULL,'11955887722','2019-06-06 20:51:25','O',1),(2,'Danilo Morais','41962883086','','1980-10-12','danilo@morais.com.br','','11985845332','2019-06-06 20:53:23','M',1),(3,'Maria Carvalho','53183168081','','1975-11-01','maria@carvalho.com.br','','11985632012','2019-06-06 20:54:06','F',1),(4,'Leticia Novaes','14763331060','','2000-04-04','leticia@novaes.com.br','','11974523001','2019-06-06 20:54:44','O',1),(5,'New Era Games','','71161111000128','1999-08-15','newera@gamges.com.br','','11985320123','2019-06-06 20:55:30','M',1),(6,'Roger Machado ME','','18793536000177','1998-01-02','roger@machado.com.br','1155223366','11953625477','2019-06-06 20:56:21','O',1),(7,'Renata Alencar','75626986006','','1995-07-22','renata@alencar.com.br','','11487563202','2019-06-06 20:57:12','F',1);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `filial`
--

DROP TABLE IF EXISTS `filial`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `filial` (
  `IdFilial` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(50) NOT NULL,
  `Cnpj` varchar(14) NOT NULL,
  `DataHoraCriacao` datetime DEFAULT CURRENT_TIMESTAMP,
  `CEP` varchar(8) NOT NULL,
  `logradouro` varchar(50) NOT NULL,
  `numero` int(11) NOT NULL,
  `complemento` varchar(50) DEFAULT NULL,
  `bairro` varchar(50) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `estado` varchar(60) NOT NULL,
  `ativo` tinyint(4) NOT NULL,
  PRIMARY KEY (`IdFilial`),
  UNIQUE KEY `Cnpj` (`Cnpj`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `filial`
--

LOCK TABLES `filial` WRITE;
/*!40000 ALTER TABLE `filial` DISABLE KEYS */;
INSERT INTO `filial` VALUES (1,'Filial Matriz','70752763000174','2019-05-24 15:52:56','01310200','Avenida Paulista',1039,'','Bela Vista','São Paulo','SP',1),(2,'Filial Campina Grande','24591363000107','2019-05-24 15:52:56','58410410','Rua Luiza Bezerra Motta',80,'','Catolé','Campina Grande','PB',1),(3,'Filial Joinvile','94379031000149','2019-05-24 15:53:53','89201400','Rua Nove de Março',16,'','Centro','Joinville','SC',1),(4,'Filial Brasília','11682322000184','2019-05-24 15:53:53','70092900','SBS Quadra',25,'','Asa Sul','Brasília','DF',1),(5,'Filial Belo Horizonte','56340748000198','2019-05-24 15:53:53','30190922','Avenida Augusto de Lima',744,'','Centro','Belo Horizonte','MG',0),(6,'Filial Santa Catarina','03582711000100','2019-05-24 16:45:47','88512530','Rua Antônio Caldas',126,'','Santa Catarina','Lages','SC',0),(7,'Filial Rio de Janeiro','70345456000179','2019-06-05 17:16:36','20530350','Rua Agostinho',4,NULL,'Tijuca','Rio de Janeiro','RJ',0);
/*!40000 ALTER TABLE `filial` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genero`
--

DROP TABLE IF EXISTS `genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `genero` (
  `IdGenero` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(50) NOT NULL,
  `DataHoraCriacao` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`IdGenero`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genero`
--

LOCK TABLES `genero` WRITE;
/*!40000 ALTER TABLE `genero` DISABLE KEYS */;
INSERT INTO `genero` VALUES (1,'RPG','2019-05-24 16:20:30'),(2,'Ação','2019-06-03 04:10:46'),(3,'Aventura','2019-06-03 04:10:51'),(4,'Fantasia','2019-06-03 04:10:59'),(5,'Esportes','2019-06-03 04:11:04'),(6,'FPS','2019-06-03 04:11:19'),(7,'Infantil','2019-06-06 06:50:46'),(8,'Corrida','2019-06-06 06:51:02'),(9,'Terror','2019-06-06 06:52:16'),(10,'Luta','2019-06-06 06:52:20');
/*!40000 ALTER TABLE `genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `itenspedido`
--

DROP TABLE IF EXISTS `itenspedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `itenspedido` (
  `IdItensPedido` int(11) NOT NULL AUTO_INCREMENT,
  `IdProduto` int(11) NOT NULL,
  `ValorUnitario` decimal(18,2) DEFAULT NULL,
  `Quantidade` int(11) NOT NULL,
  `DataHoraCriacao` datetime DEFAULT CURRENT_TIMESTAMP,
  `IdPedido` int(11) NOT NULL,
  PRIMARY KEY (`IdItensPedido`),
  KEY `IdProduto` (`IdProduto`),
  KEY `fk_itensPedido_pedido` (`IdPedido`),
  CONSTRAINT `fk_itensPedido_pedido` FOREIGN KEY (`IdPedido`) REFERENCES `pedido` (`IdPedido`),
  CONSTRAINT `itenspedido_ibfk_1` FOREIGN KEY (`IdProduto`) REFERENCES `produto` (`IdProduto`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itenspedido`
--

LOCK TABLES `itenspedido` WRITE;
/*!40000 ALTER TABLE `itenspedido` DISABLE KEYS */;
INSERT INTO `itenspedido` VALUES (1,2,69.00,1,'2019-06-06 21:09:40',1),(2,2,69.00,1,'2019-06-06 21:10:27',2),(3,1,94.00,2,'2019-06-06 21:10:29',2),(4,7,139.00,1,'2019-06-06 21:32:11',3),(5,5,70.00,1,'2019-06-06 21:32:13',3),(6,5,70.00,4,'2019-06-06 21:33:19',4),(7,6,110.00,5,'2019-06-06 21:33:21',4),(8,5,70.00,1,'2019-06-06 21:34:46',5),(9,8,75.00,2,'2019-06-06 21:35:31',6),(10,10,110.00,3,'2019-06-06 21:36:30',7),(11,9,135.00,1,'2019-06-06 21:36:33',7);
/*!40000 ALTER TABLE `itenspedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido` (
  `IdPedido` int(11) NOT NULL AUTO_INCREMENT,
  `FormaPagamento` int(11) NOT NULL,
  `StatusPedido` int(11) NOT NULL,
  `DataPedido` date NOT NULL,
  `IdCliente` int(11) NOT NULL,
  `IdFilial` int(11) NOT NULL,
  `IdUsuario` int(11) NOT NULL,
  `DataHoraCriacao` datetime DEFAULT CURRENT_TIMESTAMP,
  `Parcela` int(11) DEFAULT NULL,
  `ValorRecebido` decimal(18,2) DEFAULT NULL,
  PRIMARY KEY (`IdPedido`),
  KEY `IdCliente` (`IdCliente`),
  KEY `IdFilial` (`IdFilial`),
  KEY `IdUsuario` (`IdUsuario`),
  CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`IdCliente`) REFERENCES `cliente` (`IdCliente`),
  CONSTRAINT `pedido_ibfk_2` FOREIGN KEY (`IdFilial`) REFERENCES `filial` (`IdFilial`),
  CONSTRAINT `pedido_ibfk_3` FOREIGN KEY (`IdUsuario`) REFERENCES `usuario` (`IdUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (1,1,1,'2019-06-06',2,2,14,'2019-06-06 21:09:40',0,69.00),(2,2,1,'2019-06-06',7,2,15,'2019-06-06 21:10:27',2,257.00),(3,3,1,'2019-06-06',4,3,16,'2019-06-06 21:32:11',0,250.00),(4,2,1,'2019-06-06',6,3,16,'2019-06-06 21:33:19',3,830.00),(5,1,1,'2019-06-06',4,3,19,'2019-06-06 21:34:46',0,70.00),(6,1,1,'2019-06-06',7,4,18,'2019-06-06 21:35:31',0,150.00),(7,3,1,'2019-06-06',3,4,20,'2019-06-06 21:36:30',0,500.00);
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plataforma`
--

DROP TABLE IF EXISTS `plataforma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plataforma` (
  `IdPlataforma` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(50) NOT NULL,
  `DataHoraCriacao` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`IdPlataforma`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plataforma`
--

LOCK TABLES `plataforma` WRITE;
/*!40000 ALTER TABLE `plataforma` DISABLE KEYS */;
INSERT INTO `plataforma` VALUES (1,'PlayStation 4','2019-05-24 16:02:34'),(3,'Xbox One','2019-05-24 19:17:27'),(4,'PlayStation 3','2019-06-03 04:09:33'),(5,'Nintendo','2019-06-03 04:09:42'),(6,'Xbox 360','2019-06-03 04:09:50'),(7,'PS Vita','2019-06-03 04:10:05'),(8,'Wii','2019-06-06 06:47:57');
/*!40000 ALTER TABLE `plataforma` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `produto` (
  `IdProduto` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(80) NOT NULL,
  `Descricao` varchar(450) DEFAULT NULL,
  `PrecoCompra` decimal(18,2) NOT NULL,
  `PrecoVenda` decimal(18,2) NOT NULL,
  `IdCategoria` int(11) NOT NULL,
  `IdGenero` int(11) NOT NULL,
  `Ativo` tinyint(1) DEFAULT '1',
  `IdFilial` int(11) NOT NULL,
  `DataHoraCriacao` datetime DEFAULT CURRENT_TIMESTAMP,
  `IdPlataforma` int(11) NOT NULL,
  `QuantidadeEstoque` int(11) NOT NULL,
  PRIMARY KEY (`IdProduto`),
  KEY `IdCategoria` (`IdCategoria`),
  KEY `IdGenero` (`IdGenero`),
  KEY `IdFilial` (`IdFilial`),
  KEY `fk_produto_plataforma` (`IdPlataforma`),
  CONSTRAINT `fk_produto_plataforma` FOREIGN KEY (`IdPlataforma`) REFERENCES `plataforma` (`IdPlataforma`),
  CONSTRAINT `produto_ibfk_1` FOREIGN KEY (`IdCategoria`) REFERENCES `categoria` (`IdCategoria`),
  CONSTRAINT `produto_ibfk_2` FOREIGN KEY (`IdGenero`) REFERENCES `genero` (`IdGenero`),
  CONSTRAINT `produto_ibfk_3` FOREIGN KEY (`IdFilial`) REFERENCES `filial` (`IdFilial`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES (1,'The Witcher 3','Jogo de rpg',50.00,94.90,1,1,1,2,'2019-06-06 09:06:31',1,26),(2,'Dragon Age Inquisition','Jogo de rpg',40.00,69.90,1,1,1,2,'2019-06-06 09:13:39',1,28),(3,'Fifa 19','Simulador de futebol',60.00,100.00,1,5,1,2,'2019-06-06 09:15:59',3,30),(4,'UFC 3','Simulador de lutas',50.00,85.90,1,5,1,2,'2019-06-06 09:16:30',3,30),(5,'The Division','jogo de fps',35.00,70.00,1,6,1,3,'2019-06-06 09:21:47',3,14),(6,'Spider Man','jogo de aventura',55.00,110.00,1,3,1,3,'2019-06-06 09:23:14',1,30),(7,'Resident Evil 7','jogo de suvirvor horror',80.00,139.90,1,9,1,3,'2019-06-06 09:24:42',1,24),(8,'Need For Speed','jogo de corrida',42.00,75.90,1,8,1,4,'2019-06-06 09:26:01',3,13),(9,'Tomb Raider','jogo de ação',74.00,135.00,1,2,1,4,'2019-06-06 09:27:05',1,38),(10,'Metal Gear Solid V','jogo de stealth',65.00,110.00,1,2,1,4,'2019-06-06 09:28:16',4,21);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `IdUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(70) NOT NULL,
  `Cpf` varchar(11) NOT NULL,
  `Email` varchar(70) NOT NULL,
  `IdFilial` int(11) NOT NULL,
  `Cargo` varchar(50) DEFAULT NULL,
  `Login` varchar(30) NOT NULL,
  `Senha` varchar(100) NOT NULL,
  `DataHoraCriacao` datetime DEFAULT CURRENT_TIMESTAMP,
  `ativo` tinyint(1) DEFAULT '1',
  `sexo` varchar(50) DEFAULT NULL,
  `Setor` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`IdUsuario`),
  UNIQUE KEY `Cpf` (`Cpf`),
  UNIQUE KEY `Email` (`Email`),
  UNIQUE KEY `Login` (`Login`),
  KEY `IdFilial` (`IdFilial`),
  CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`IdFilial`) REFERENCES `filial` (`IdFilial`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Administrador','12345678910','admin@tadesgames.com',1,'Suporte Técnico','admin','admin','2019-06-06 06:56:58',1,'O','T.I'),(2,'Milla Trodel','70394555015','milla.trodel@tadesgames.com.br',1,'Diretor','mtrodel','$2a$12$QBxDx7MKz7cXzEVCdx4lS.kQCj3ppoUQskMjyIs1vum8uWh4rfSbS','2019-06-06 07:14:29',1,'F','Diretoria'),(3,'Aline Martins','39669320089','aline.martins@tadesgames.com.br',1,'Gerente Global','amartins','$2a$12$AgDeFsC05GE.OklzFknuj.G1MqjAWZwv0c5sJ7rY8pT18JQZrI5ca','2019-06-06 07:15:44',1,'F','Produtos/Serviços/Marketing'),(4,'Luan Cavalcante','53693063036','luan.cavalcante@tadesgames.com.br',1,'Gerente Global','lcavalcante','$2a$12$I6NRO9p48uA1qVd0SuDViO1V/KvifBrRIAWhudhfVbQnByYp5DiV.','2019-06-06 07:17:38',1,'M','Vendas'),(5,'Kenji Hamada','04576761080','kenji.hamada@tadesgames.com.br',1,'Gerente Global','khamada','$2a$12$HZbcHHDb6lrvKpQmuzYA9u8ekTvLKJCBg2Itb0hcT.qMnAfy44HIS','2019-06-06 07:18:30',1,'M','T.I'),(6,'Gabriel Escobar','35241448020','gabriel.escobar@tadesgames.com.br',1,'Gerente Regional','gescobar','$2a$12$pfgUOfGCM1yUUducIaMjquJXycCbgKvKJnIj740nZaqBbQbAYPMkO','2019-06-06 07:21:07',1,'M','Vendas'),(7,'Girlaine Silva','77278903084','girlaine.nx@gmail.com',2,'Gerente Regional','gsilva','$2a$12$.jCbFziqR8tum5l3Mjy4yeYIYzdM/ApcymFW6ednEqCVB9LqimUa2','2019-06-06 07:24:48',1,'F','Vendas'),(8,'Carlos Rodrigues','37215110001','carlos.rodrigues@tadesgames.com.br',3,'Gerente Regional','crodrigues','$2a$12$CSUr91L6DEGfDxaKHLCJA.OMWQIcFgFujT.atazT.q58gxYvOUTU.','2019-06-06 07:27:05',1,'M','Vendas'),(9,'Marcel Cardoso','19283050088','mar.cel.bruno@hotmail.com',4,'Gerente Regional','mcardoso','$2a$12$aKegJ7.MqiyGt3/wg0YzkOqi.hSaPxWfc4mq.zJkCx2pSt.zvIHI6','2019-06-06 07:27:52',1,'M','Vendas'),(10,'Jessica Martel','41987545001','jessica.martel@tadesgames.com.br',1,'Funcionário','jmartel','$2a$12$M4Aa/4E40sBnUy95xbsgouwtCzKj0XgSYkKE93PESFsbeHclL1yc.','2019-06-06 07:39:18',1,'F','Produtos/Serviços/Marketing'),(11,'Cersei Trali','45972558009','cersei.trali@tadesgames.com.br',2,'Funcionário','ctrali','$2a$12$fE5JRNT.b5wh0wOIHd0R1u/BLKLO16.oc5kK.5x4qw4B7ZRPhNcY2','2019-06-06 07:44:43',1,'O','Produtos/Serviços/Marketing'),(12,'Bruce Wayne','31485838096','bruce.wayne@tadesgames.com.br',3,'Funcionário','bwayne','$2a$12$XveEjC3wY03AGXHGX.4k8OelPz/00Zd92qCP6iv8zVKxA50UYW5Jy','2019-06-06 08:14:19',1,'M','Produtos/Serviços/Marketing'),(13,'Diana Prince','34284380001','diana.prince@tadesgames.com.br',4,'Funcionário','dprince','$2a$12$5/OBEvToIC.XPFWlXhxN5eBqDCxtgMLxgX68LeujaGScyMo7PF6US','2019-06-06 08:16:32',1,'F','Produtos/Serviços/Marketing'),(14,'Sam Wilson','26488042009','sam.wilson@tadesgames.com.br',2,'Vendedor (a)','swilson','$2a$12$wQQgh3kDTOBlPpyng0CmgeTFNnOSn260dzj74wu2TOC.Ga8d09Ykq','2019-06-06 08:18:15',1,'M','Vendas'),(15,'Osmar Lopes','37153417001','osmar.lopes@tadesgames.com.br',2,'Vendedor (a)','olopes','$2a$12$pA.z93kxzwLmbxbL6bHx/e6oVm/PK4P.Epv9jUeOh0GC6MEILH1H2','2019-06-06 08:20:14',1,'M','Vendas'),(16,'Carol Danvers','52924704065','carol.danvers@tadesgames.com.br',3,'Vendedor (a)','cdanvers','$2a$12$Hpf5GURNkWye1dtZVu7JCOWBMKNww2.FrV3xntrtR2wDIPyT6NyTG','2019-06-06 08:25:10',1,'F','Vendas'),(17,'Ada Lovelace','08630970065','ada.lovelace@tadesgames.com.br',3,'Vendedor (a)','alovelace','$2a$12$pJFeyoOGVZkFNXen2cqOt.dKeB5ow/z5WVynIET2vxxGLG0J83X7a','2019-06-06 08:26:52',1,'F','Vendas'),(18,'Jessica Jones','50883253046','jessica.jones@tadesgames.com.br',4,'Vendedor (a)','jjones','$2a$12$TDETgIZyY.81yLiiMpj/2uI0DhfFEUHHfk.PrerhS3US6/ynOta9.','2019-06-06 08:28:25',1,'F','Vendas'),(19,'Thor Odinson','90071496017','thor.odinson@tadesgames.com.br',3,'Vendedor (a)','todinson','$2a$12$a2Aw53oT0PwPuxlnx2LkTOhqNu9shRmeFHGCncLzaqk/TXKWuCR06','2019-06-06 08:32:40',1,'M','Vendas'),(20,'Steve Rogers','40593119010','steve.rogers@tadesgames.com.br',4,'Vendedor (a)','srogers','$2a$12$EkLdF6JSywZVO9BQwnq5oekXnu5IfbZJFHIHAVxtcSkxNprpWPcAe','2019-06-06 08:33:55',1,'M','Vendas'),(21,'Bruce Banner','28549275085','bruce.banner@tadesgames.com.br',4,'Vendedor (a)','bbanner','$2a$12$MScLPGbHyD2eVlnMHJbOXeGcZrh4rvg0CxEjvseZw2ax2VHsYfAXy','2019-06-06 08:34:54',1,'M','Vendas'),(22,'Mary Jane Watson','42438933062','mary.jane@tadesgames.com.br',2,'Vendedor (a)','mwatson','$2a$12$/qySDiKWeyFcmONJB5Of9elajWTgZwN8NbwAyRl2jajrXaUrLGSbC','2019-06-06 08:48:03',1,'F','Vendas');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
SET @@SESSION.SQL_LOG_BIN = @MYSQLDUMP_TEMP_LOG_BIN;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-06-06 18:38:45
