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
    <link rel="stylesheet" href="resources/css/toastr.min.css"/>
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
        <form action="${pageContext.request.contextPath}/Login" method="post" class="form-signin co" autocomplete="off">
            <img class="mb-4" src="resources/img/LogoTadesGames.png" alt="" width="72" height="72">
            <h3 class="h3 mb-3 font-weight-normal">Login</h3>
            <label class="sr-only">Login</label>
            <tr>
                <td>
                    <label for="inputLogin" class="sr-only">Login</label>
                    <input type="text" id="login" name="login" value="${Usuario.login}" class="form-control" placeholder="Login" required autofocus>
                </td>

                <td>
                    <label for="inputPassword" class="sr-only">Senha</label>
                    <input type="password" id="inputPassword" name="senha" value="${Usuario.senha}" class="form-control" placeholder="Senha" required>
                    <a href="página para redefinir a senha.jsp" class="btn btn-link">Esqueceu sua senha? Clique Aqui</a>
                    <br>
                    <hr>
                    <br>
                    <input class="btn btn-lg btn-primary btn-block" type="submit" value="Sign in">
                    <p class="mt-5 mb-3 text-muted">&copy; TadesGames</p>
                </td>
            </tr>
        </form>
    </div>

    <script type="text/javascript" src="resources/js/jquery.min.js"></script>  
    <script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="resources/js/toastr.min.js"></script> 

    <script>
        $(document).ready(function(){
            $('#login').focus();
            
            var msgErro = '${msgErro}';
            if(msgErro !== ''){
                toastr.error('Usuário ou senha inválido(s)','Atenção');
            }
        })
    </script>
</body>
</html>
