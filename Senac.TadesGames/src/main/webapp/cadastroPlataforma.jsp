<%-- 
    Document   : cadastroPlataforma
    Created on : 03/05/2019, 04:45:14
    Author     : Marcel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Plataforma</title>
    </head>
    <body>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@include file="header.jsp" %>
        <div class=" container">
            <br>
            <h2>Cadastro de Plataforma</h2>
            <hr>
            <form>
                <div class="row">
                    <div class="col-3">
                        <label for="idPlataforma">Id<h11 class="text-danger">*</h11></label>
                    </div>
                    <div class="col-6">
                        <label for="nome">Plataforma<h11 class="text-danger">*</h11></label>
                    </div>
                </div>
                <div class="row">
                    <div class="col-3">
                        <input id="idPlataforma" type="text" class="form-control" placeholder="0000" readonly>
                    </div>
                    <div class="col-6">
                        <input id="nome" type="text" class="form-control" placeholder="Plataforma">
                    </div>
                    <div class="col-2"> 
                        <input type="submit" class="btn btn-success" value="Salvar">
                    </div>
                </div>
            </form>
            <br>
            <table class="table table-hover">
                <caption>Lista de Plataforma</caption>
                <thead> 
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Plataforma</th>
                    </tr>
                </thead>
                <tbody id="tabela" name="tabela">
                    <c:forEach var="p" items="${plataforma}">
                        <tr>
                            <td>${g.idPlataforma}</td>
                            <td>${g.nome}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
