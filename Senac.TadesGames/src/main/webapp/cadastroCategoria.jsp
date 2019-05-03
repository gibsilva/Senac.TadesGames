<%-- 
    Document   : cadastroCategoria
    Created on : 03/05/2019, 02:44:06
    Author     : Marcel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>


        <title>Cadastro de Catêgoria</title>



    </head>
    <body>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@include file="header.jsp" %>
        <div class=" container">
            <br>
            <h2>Cadastro de Categoria</h2>
            <hr>
            <form>
                <div class="row">
                    <div class="col-3">
                        <label for="idCategoria">Id<h11 class="text-danger">*</h11></label>
                    </div>
                    <div class="col-6">
                        <label for="nome">Categoria<h11 class="text-danger">*</h11></label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-3">
                        <input type="text" id="idCategoria" class="form-control" placeholder="0000" readonly>
                    </div>
                    <div class="col-6">
                        <input type="text" id="nome" class="form-control" placeholder="Categoria">
                    </div>
                    <div class="col-2"> 
                        <input type="submit" class="btn btn-success" value="Salvar">
                    </div>
                </div>
            </form>
            <br>
            <table class="table table-hover">
                <caption>Lista de Categorias</caption>
                <thead> 
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Categoria</th>
                    </tr>
                </thead>
                <tbody id="tabela" name="tabela">
                    <c:forEach var="c" items="${categoria}">
                        <tr>
                            <td>${c.idCategoria}</td>
                            <td>${c.nome}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
