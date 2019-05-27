<%-- 
    Document   : trocaSenha
    Created on : 25/05/2019, 14:52:39
    Author     : Gi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<title>Alteração de Senha</title>

<div class=" container">
    <br>
    <h2>Alteração de Senha</h2>
    <h11><small>Ao alterar a senha será efetuado o logout e você deverá logar novamente com a nova senha</small></h11>
    <hr>
    <div class="form-group">
        <ul class="text-danger">
            <c:forEach var = "n" items = "${notificacoes}">
                <li>${n.valor}</li>
                </c:forEach>
        </ul>
    </div>
    <form action="alterar-senha" method="post" id="form">
        <input type="hidden" value="${sessionScope.usuarioLogado.idUsuario}" id="idUsuario" name="idUsuario">
        <input type="hidden" value="alterarSenha" id="acao" name="acao">

        <div class="">
            <div class="form-group col-md-5">
                <label for="senhaAtual">Senha Atual<h11 class="text-danger">*</h11></label>
                <input type="password" id="senhaAtual" name="senhaAtual" value="" class="form-control" placeholder="Senha Atual" required>
            </div>
            <div class="form-group col-md-5">
                <label for="novaSenha">Nova Senha<h11 class="text-danger">*</h11></label>
                <input type="password" id="novaSenha" name="novaSenha" value="" class="form-control" placeholder="Nova Senha" required>
            </div>
            <div class="form-group col-md-5">
                <label for="confirmaSenha">Confirmar Senha<h11 class="text-danger">*</h11></label>
                <input type="password" id="confirmaSenha" name="confirmaSenha" value="" class="form-control" placeholder="Confirmar Senha" required>
            </div>


        </div>

        <hr> 

        <div class="row">
            <div class=" form-group col-md-2"> 
                <button type="submit" class="btn btn-success" value="Salvar">Salvar</button>
                <a href="${pageContext.request.contextPath}/autenticado/Home" class="btn btn-light">Cancelar</a>
            </div>
        </div>

    </form>  
</div>

<script>
    $('#form').submit(function (e) {
        if ($('#novaSenha').val() !== $('#confirmaSenha').val()) {
            toastr.warning('As senhas não conferem, por favor verificar', 'Atenção');
            e.preventDefault();
        } else {
            this.submit;
        }
    });
</script>



