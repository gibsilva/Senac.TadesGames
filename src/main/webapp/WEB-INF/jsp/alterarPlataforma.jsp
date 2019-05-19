<%-- 
    Document   : alterarPlataforma
    Created on : 05/05/2019, 16:18:07
    Author     : Gi
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<title>Alteração de Plataforma</title>

<div class=" container">
    <br>
    <h2>Alteração de Plataforma</h2>
    <hr>
    <!-- notificacoes caso houver erros nas validações -->
    <div class="form-group">
        <ul class="text-danger">
            <c:forEach var = "n" items = "${notificacoes}">
                <li>${n.valor}</li>
                </c:forEach>
        </ul>
    </div>

    <form action="Plataformas" method="post">
        <input type="hidden" value="${plataforma.idPlataforma}" id="idPlataforma" name="idPlataforma">
        <input type="hidden" value="alterar" id="acao" name="acao">

        <div class="row">
            <div class="col-md-5">
                <label for="nome">Nome<h11 class="text-danger">*</h11></label>
                <input type="text" id="nome" name="nome" class="form-control" placeholder="Digite o nome da plataforma" 
                       required value="${plataforma.nome}">
            </div>
        </div>

        <hr>    

        <div class="row">
            <div class=" form-group col-md-2"> 
                <button type="submit" class="btn btn-success" value="Salvar">Salvar</button>
                <a href="Plataformas?acao=listar" class="btn btn-light" >Cancelar</a>
            </div>
        </div>
    </form>
</div>


