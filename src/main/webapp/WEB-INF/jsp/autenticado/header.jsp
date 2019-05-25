<%-- 
    Document   : header
    Created on : 20/04/2019, 03:57:17
    Author     : Gi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Cache-Control: max-age=3600, must-revalidate" />
        <!-- Add to homescreen for Safari on iOS -->
        <meta name="apple-mobile-web-app-capable" content="yes">
        <meta name="apple-mobile-web-app-status-bar-style" content="black">
        <meta name="apple-mobile-web-app-title" content="Tades Games">
        <!-- CSS -->
        <link rel="stylesheet" href="../resources/css/bootsWacth/bootstrap.min.css"/>
        <link rel="stylesheet" href="../resources/css/toastr.min.css"/>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
            <a class="navbar-brand" href="#">
                <img alt="Brand" src="../resources/img/LogoTadesGamesLaranja55x55.png">
            </a>

            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarColor01">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath}/autenticado/Home">Home <span class="sr-only">(current)</span></a>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Vendas</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="Vendas">Realizar Venda</a>
                            <a class="dropdown-item" href="Vendas?acao=listar">Consulta de Vendas</a>
                        </div>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Cliente</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="Clientes?acao=salvar">Cadastro de Cliente</a>
                            <a class="dropdown-item" href="Clientes"> Consulta de Cliente</a>
                        </div>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Produto</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="Produtos?acao=salvar">Cadastro de Produto</a>
                            <a class="dropdown-item" href="Produtos">Consulta de Produto</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="Plataformas?acao=salvar">Cadastro de Plataforma</a>
                            <a class="dropdown-item" href="Plataformas">Consulta de Plataforma</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="Categorias?acao=salvar">Cadastro de Categoria</a>
                            <a class="dropdown-item" href="Categorias?acao=listar">Consulta de Categoria</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="Generos?acao=salvar">Cadastro de Gênero</a>
                            <a class="dropdown-item" href="Generos">Consulta de Gênero</a>
                        </div>
                    </li>


                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Relatórios</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/autenticado/Relatorios?acao=clientes">Cliente</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/autenticado/Relatorios?acao=produtos">Produto</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/autenticado/Relatorios?acao=vendas">Vendas</a>  
                        </div>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Usuário</a>
                        <div class="dropdown-menu">
                            <c:url var="salvarUsuario" value="/autenticado/Usuarios">
                                <c:param name="acao" value="salvar" />
                            </c:url>
                            <a href="${salvarUsuario}" class="dropdown-item">Cadastrar Usuário</a>
                            <a class="dropdown-item" href="Usuarios">Consulta de Usuário</a>
                        </div>
                    </li>

                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">Filiais</a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" href="Filiais?acao=salvar">Cadastro de Filial</a>
                            <a class="dropdown-item" href="Filiais">Consulta de Filial</a>
                        </div>
                    </li>



                </ul>


                <form class="form-inline my-2 my-lg-0">
                    <a href="${pageContext.request.contextPath}/autenticado/Logout" style="color:white" class="btn btn-warning my-2 my-sm-0" type="submit" >Sair</a>
                </form>
            </div>
        </nav>

        <script type="text/javascript" src="../resources/js/jquery.min.js"></script>  
        <script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../resources/js/jquery.mask.js"></script> 
        <script type="text/javascript" src="../resources/js/toastr.min.js"></script> 
        <script type="text/javascript" src="../resources/js/jquery.table2excel.js"></script> 
    </body>
</html>
