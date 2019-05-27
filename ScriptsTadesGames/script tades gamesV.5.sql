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
);

CREATE TABLE `categoria` (
  `IdCategoria` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(50) NOT NULL,
  `DataHoraCriacao` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`IdCategoria`)
);

CREATE TABLE `plataforma` (
  `IdPlataforma` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(50) NOT NULL,
  `DataHoraCriacao` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`IdPlataforma`)
);

CREATE TABLE `genero` (
  `IdGenero` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(50) NOT NULL,
  `DataHoraCriacao` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`IdGenero`)
);

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
   `ativo` tinyint NOT NULL,
  PRIMARY KEY (`IdFilial`),
  UNIQUE KEY `Cnpj` (`Cnpj`)
);

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
);


CREATE TABLE `produto` (
  `IdProduto` int(11) NOT NULL AUTO_INCREMENT,
  `Nome` varchar(80) NOT NULL,
  `Descricao` varchar(450)NULL,
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
);

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
);

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
);


INSERT INTO filial (nome, Cnpj, CEP, logradouro, numero, complemento, bairro, cidade, estado) 
VALUES ('Matriz','70752763000174','01310200','Avenida Paulista',1035,'','Bela Vista','São Paulo','SP');

INSERT INTO filial (nome, Cnpj, CEP, logradouro, numero, complemento, bairro, cidade, estado) 
VALUES ('Filial Campina Grande','24591363000107','58410410','Rua Luiza Bezerra Motta',80,'','Catolé','Campina Grande','PB');

INSERT INTO filial (nome, Cnpj, CEP, logradouro, numero, complemento, bairro, cidade, estado) 
VALUES ('Filial Joinvile','94379031000149','89201400','Rua Nove de Março',16,'','Centro','Joinville','SC');

INSERT INTO filial (nome, Cnpj, CEP, logradouro, numero, complemento, bairro, cidade, estado) 
VALUES ('Filial Brasília','11682322000184','70092900','SBS Quadra',25,'','Asa Sul','Brasília','DF');

INSERT INTO filial (nome, Cnpj, CEP, logradouro, numero, complemento, bairro, cidade, estado) 
VALUES ('Filial Belo Horizonte','56340748000198','30190922','Avenida Augusto de Lima',744,'','Centro','Belo Horizonte','MG');

INSERT INTO usuario (nome, Cpf, email, idFilial, cargo, setor, login, senha, ativo, sexo) 
VALUES ('admin','12345678910','admin@admin.com',1,'Suporte Tecnico','TI','admin','admin',1, 'outros');

insert into cliente (nome, cpf, cnpj, datanasc, email, celular, sexo, ativo)
values('Fantasma', '12345678910', '', '1900-01-01', 'fantasma@tadesgames.com.br', '11955887722', 'M', 1);
