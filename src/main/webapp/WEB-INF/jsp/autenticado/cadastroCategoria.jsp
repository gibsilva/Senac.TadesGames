<%-- 
    Document   : cadastroCategoria
    Created on : 03/05/2019, 02:44:06
    Author     : Marcel
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<head>
    <title>Cadastro de Categoria</title>
</head>

<div class=" container">
    <br>
    <h2>Cadastro de Categoria</h2>
    <hr>

    <!-- notificacoes caso houver erros nas validações -->
    <div class="form-group">
        <ul class="text-danger">
            <c:forEach var = "n" items = "${notificacoes}">
                <li>${n.valor}</li>
                </c:forEach>
        </ul>
    </div>

    <form action="Categorias" method="post">
        <input type="hidden" value="salvar" id="acao" name="acao">

        <div class="row">
            <div class="col-md-5">
                <label for="nome">Nome<h11 class="text-danger">*</h11></label>
                <input type="text" id="nome" name="nome" class="form-control" placeholder="Digite o nome da categoria" required>
            </div>
        </div>

        <hr> 

        <div class="row">
            <div class=" form-group col-md-2"> 
                <button type="submit" class="btn btn-success" value="Salvar">Salvar</button>
                <button type="reset" class="btn btn-light" value="cancelar">Cancelar</button>
            </div>
        </div>
    </form>
</div>
