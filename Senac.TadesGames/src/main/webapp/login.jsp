<%-- 
    Document   : login
    Created on : 21/04/2019, 20:29:26
    Author     : Gi
--%>


<!doctype html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">


        <title>Signin</title>

        <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="resources/css/estilo.css"/>

    </head>

    <body class="text-center">
        
        <br>
        <br>
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
            
            <a href="home.jsp" class="btn btn-lg btn-primary btn-block"  type="submit">Sign in</a>
            <p class="mt-5 mb-3 text-muted">&copy; TadesGames</p>
        </form>
        </div>
    </body>
</html>
