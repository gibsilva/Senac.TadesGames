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
                    <input type="password" id="senha" name="senha" value="${Usuario.senha}" class="form-control" placeholder="Senha" required>
                    <br>
                    <button type="button" class="btn btn-link" onclick="abreModal()">Esqueceu sua senha? Clique Aqui</button>
                    <input class="btn btn-lg btn-primary btn-block" type="submit" value="Entrar" onsubmit="limparForm()">
                    <br>
                    
                    <p class="mt-5 mb-3 text-muted">&copy; TadesGames</p>
                </td>
            </tr>
        </form>
    </div>

    <!-- Modal esqueci minha senha -->
    <div class="modal fade" id="modalEsqueciSenha" name="modalEsqueciSenha" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Esqueci minha senha</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div id="erros" class="text-danger">
                        
                    </div>
                    <div class="form-group pull-left">
                        <label for="email">Digite seu e-mail cadastrado para que sua senha seja alterada:</label>
                        <input type="email" id="email" name="email" value="" class="form-control" placeholder="Digite seu e-mail cadastrado" required>
                        <small>A nova senha será enviada no seu endereço de e-mail</small>
                    </div>
                </div>
                <div class="modal-footer">
                    <a href="#" class="btn btn-success" id="linkResetSenha" onclick="alterarSenha($('#email').val())">Enviar</a>
                    <button type="button" class="btn btn-primary" data-dismiss="modal">Fechar</button>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript" src="resources/js/jquery.min.js"></script>  
    <script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="resources/js/toastr.min.js"></script> 

<script>
    $(document).ready(function () {
        var statusAlterado = '${statusAlterado}';
            if (statusAlterado === 'true') {
                toastr.success('Senha alterada com sucesso', 'Sucesso');
            }

        $('#login').focus();

        var msgErro = '${erro}';
        if (msgErro !== '') {
            toastr.error('Usuário ou senha inválido(s)', 'Atenção');
            $('#login').val('');
            $('#senha').val('');
            $('#login').focus();
        }
    });

    function limparForm() {
        $('#login').val('');
        $('#senha').val('');
    }
    
    function abreModal(){
        $('#modalEsqueciSenha').modal();
    }
    
    function alterarSenha(email){
        if(email === ''){
            toastr.warning('E-mail não pode estar vazio', 'Atenção');
            $('#email').focus();
        } else if(!isEmail(email)){
            toastr.warning('E-mail inválido, verifique por favor', 'Atenção');
            $('#email').focus();
        } else {
            $.ajax({
                url: 'ResetSenha',
                type: 'POST',
                data: {'email': email.toString()},
                success: function(data){
                    var usuario = $.parseJSON(data);
                    if(usuario !== null){                      
                        console.log(usuario);
                        $('#email').val('');
                        $('#modalEsqueciSenha').modal('hide');
                        toastr.success('A senha foi alterada e enviada para o e-mail ' + email + ' com sucesso', 'Sucesso');
                        console.log(data);
                    } else{
                        toastr.warning('E-mail não está cadastrado', 'Atenção');
                        $('#erros').html('E-mail não está cadastrado');
                        $('#email').val('');
                    }
                },
                error: function(){
                    $('#email').val('');
                    $('#modalEsqueciSenha').modal('hide');
                    toastr.error('Ocorreu um erro ao alterar a senha');
                }
            });
        }
    }
    
    function isEmail(email) {
        var regex = /^([a-zA-Z0-9_.+-])+\@(([a-zA-Z0-9-])+\.)+([a-zA-Z0-9]{2,4})+$/;
        return regex.test(email);
    }
    
</script>
</body>
</html>
