<%-- 
    Document   : naoAutorizado.jsp
    Created on : 26/05/2019, 19:47:18
    Author     : Christian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<title>Login</title>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="resources/css/bootsWacth/bootstrap.min.css"/>
    <link rel="stylesheet" href="resources/css/toastr.min.css"/>
</head>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
    <a class="navbar-brand" href="#">
        <img alt="Brand" src="resources/img/LogoTadesGamesLaranja55x55.png">
    </a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
</nav>

<br>
<br>

<div class="container">
    <h4 class="text-danger">Usuário não autorizado</h4>
    <hr>
    <a class="btn btn-link" href="${pageContext.request.contextPath}/autenticado/Home">Voltar</a>
</div>
