<%-- 
    Document   : consultaCategoria
    Created on : 05/05/2019, 15:28:02
    Author     : Gi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>

<title>Consulta de Categoria</title>

<div class="container">
    <br>
    <h2>Consulta de Categoria</h2>
    <hr>
    <br>

    <div class="btn-toolbar justify-content-between" role="toolbar" aria-label="Toolbar with button groups">

        <div class="input-group-append col-md-6">
            <input type="text" class="form-control " placeholder="Pesquisar" id="filtro" name="filtro">

        </div>
        <div class="input-group">
            <div>
                <a href="cadastroCategoria.jsp" class="btn btn-outline-primary">Nova Categoria</a>
            </div>
        </div>
    </div>
    <br>
    <br>
    <table class="table table-hover">
        <thead> 
            <tr>
                <th scope="col">Id</th>
                <th class="text-center" scope="col">Nome</th>
                <th class="text-center" scope="col">#</th>
            </tr>
        </thead>
        <tbody id="tabela" name="tabela">
            <c:forEach var="cat" items="${categorias}">
                <tr>
                    <td>${cat.idCategoria}</td>
                    <td class="text-center" >${cat.nome}</td>

                    <td class="text-center"> 
                        <c:url var="alterarCategoria" value="/Categorias">
                            <c:param name="acao" value="alterar" />
                            <c:param name="idCategoria" value="${cat.idCategoria}" />
                        </c:url>
                        <a href="${alterarCategoria}" class="btn btn-sm btn-outline-warning">Editar</a>

                        <c:url var="excluir" value="/Categorias">
                            <c:param name="excluir" value="excluir" />
                            <c:param name="idCategoria" value="${cat.idCategoria}" />
                        </c:url>
                        <a href="${excluir}" class="btn btn-sm btn-outline-danger">Excluir</a>
                    </td>

                </tr>
            </c:forEach>
        </tbody>
    </table>   
</div>
