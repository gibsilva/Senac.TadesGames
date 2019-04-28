<%-- 
    Document   : login
    Created on : 21/04/2019, 20:29:26
    Author     : Gi
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<title>Login</title>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="resources/css/bootsWacth/bootstrap.min.css"/>
</head>

<body class="text-center">

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
    <br>

    <div class="container col-md-3">
        <form class="form-signin co">
            <img class="mb-4" src="resources/img/LogoTadesGames.png" alt="" width="72" height="72">
            <h3 class="h3 mb-3 font-weight-normal">Login</h3>

            <label for="inputEmail" class="sr-only">Login</label>
            <input type="email" id="inputEmail" class="form-control" placeholder="Login" required autofocus>

            <label for="inputPassword" class="sr-only">Senha</label>
            <input type="password" id="inputPassword" class="form-control" placeholder="Senha" required>

            <a href="#" class="btn btn-link">Esqueceu sua senha? Clique Aqui</a>
            <br>
            <hr>

            <a href="home.jsp" class="btn btn-lg btn-primary btn-block"  type="submit">Sign in</a>
            <p class="mt-5 mb-3 text-muted">&copy; TadesGames</p>
        </form>
    </div>
</body>
</html>
