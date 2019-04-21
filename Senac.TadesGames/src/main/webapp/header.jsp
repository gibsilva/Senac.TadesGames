<%-- 
    Document   : header
    Created on : 20/04/2019, 03:57:17
    Author     : Gi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tades Games</title>
        <!--<link rel="stylesheet" href="resources/css/bootstrap.min.css"/>-->
        <link rel="stylesheet" href="resources/css/bootsWacth/bootstrap.min.css"/>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <a class="navbar-brand" href="#">
                <img alt="Brand" src="resources/img/LogoTadesGamesBranco55x55_1.png">
            </a>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarColor01">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="home.jsp">Home <span class="sr-only">(current)</span></a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="#">Vendas</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="cadastroCliente.jsp">Cadastro de Cliente</a>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Consultas</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="consultaCliente.jsp">Cliente</a>
                            <a class="dropdown-item" href="#">Produto</a>
                            <a class="dropdown-item" href="#">Vendas</a>  
                        </div>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Relatorio</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="#">Cliente</a>
                            <a class="dropdown-item" href="#">Produto</a>
                            <a class="dropdown-item" href="#">Vendas</a>  
                        </div>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Administrador</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="#">Cadastro de Usuario</a>

                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="#">Cadastro de Plataforma</a>
                            <a class="dropdown-item" href="#">Cadastro de Categoria</a>
                            <a class="dropdown-item" href="#">Cadastro de Genero</a>
                        </div>
                </ul>
                <form class="form-inline my-2 my-lg-0">
                    <button class="btn btn-secondary my-2 my-sm-0" type="submit">Login</button>
                </form>
            </div>
        </nav>

        <script type="text/javascript" src="resources/js/jquery.min.js"></script>  
        <script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="resources/js/jquery.mask.js"></script> 
    </body>
</html>
