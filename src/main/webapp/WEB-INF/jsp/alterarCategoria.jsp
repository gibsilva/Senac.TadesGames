<%-- 
    Document   : alterarCategoria
    Created on : 05/05/2019, 16:17:57
    Author     : Gi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<title>Alteração de Categoria</title>

<div class=" container">
    <br>
    <h2>Alteração de Categoria</h2>
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
        <input type="hidden" value="${categoria.idCategoria}" id="idCategoria" name="idCategoria">
        <input type="hidden" value="alterar" id="acao" name="acao">

        <div class="row">
            <div class="col-md-5">
                <label for="nome">Nome<h11 class="text-danger">*</h11></label>
                <input type="text" id="nome" name="nome" value="${categoria.nome}" class="form-control" placeholder="digite o nome da categoria" required>
            </div>
        </div>

        <hr> 

        <div class="row">
            <div class=" form-group col-md-2"> 
                <button type="submit" class="btn btn-success" value="Salvar">Salvar</button>
                <a href="Categorias?acao=listar" class="btn btn-light">Cancelar</a>
            </div>
        </div>
    </form>
</div>

