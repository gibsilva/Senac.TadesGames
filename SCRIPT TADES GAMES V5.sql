CREATE DATABASE tadesgames;

use tadesgames;

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

CREATE TABLE `categoria` (
  `IdCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(50) NOT NULL,
  `DataHoraCriacao` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`IdCategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=latin1;

CREATE TABLE `plataforma` (
  `IdPlataforma` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(50) NOT NULL,
  `DataHoraCriacao` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`IdPlataforma`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

CREATE TABLE `genero` (
  `IdGenero` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(50) NOT NULL,
  `DataHoraCriacao` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`IdGenero`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

CREATE TABLE `filial` (
  `IdFilial` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(50) NOT NULL,
  `Cnpj` varchar(14) NOT NULL,
  `DataHoraCriacao` datetime DEFAULT CURRENT_TIMESTAMP,
  `CEP` varchar(8) NOT NULL,
  `longradouro` varchar(50) NOT NULL,
  `numero` int(11) NOT NULL,
  `complemento` varchar(50) DEFAULT NULL,
  `bairro` varchar(50) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `estado` varchar(60) NOT NULL,
  PRIMARY KEY (`IdFilial`),
  UNIQUE KEY `Cnpj` (`Cnpj`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

CREATE TABLE `usuario` (
  `IdUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(70) NOT NULL,
  `Cpf` varchar(11) NOT NULL,
  `Email` varchar(70) NOT NULL,
  `IdFilial` int(11) NOT NULL,
  `Cargo` varchar(50) DEFAULT NULL,
  `Login` varchar(30) NOT NULL,
  `Senha` varchar(16) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

CREATE TABLE `permissao` (
  `IdPermissao` int(11) NOT NULL AUTO_INCREMENT,
  `IdUsuario` int(11) NOT NULL,
  `CadastroProduto` tinyint(4) DEFAULT NULL,
  `CadastroCliente` tinyint(4) DEFAULT NULL,
  `CadastroFilial` tinyint(4) DEFAULT NULL,
  `RealizaVenda` tinyint(4) DEFAULT NULL,
  `RelatorioProduto` tinyint(4) DEFAULT NULL,
  `RelatorioCliente` tinyint(4) DEFAULT NULL,
  `RelatorioVenda` tinyint(4) DEFAULT NULL,
  `DataHoraCriacao` datetime DEFAULT CURRENT_TIMESTAMP,
  `CadastroUsuario` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`IdPermissao`),
  KEY `IdUsuario` (`IdUsuario`),
  CONSTRAINT `permissao_ibfk_1` FOREIGN KEY (`IdUsuario`) REFERENCES `usuario` (`IdUsuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `produto` (
  `IdProduto` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(80) NOT NULL,
  `Descricao` varchar(250) NOT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

INSERT INTO filial VALUES ('1','filialteste','1111111111111','','1111111','teste',11111111,'teste','teste','teste','teste');

INSERT INTO usuario VALUES ('1','USUARIOROOT','12345678901','root@root.com',1,'root','root123','1234','',1,'Masculino','Vendas');

