<%-- 
    Document   : consultaProduto
    Created on : 21/04/2019, 00:26:08
    Author     : Gi
--%>

<%@include file="header.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<title>Consulta de Produto</title>

<div class="col-md-10 container">
    <br
    <h2>Consulta de Produto</h2> 
    <br>
    
    <div class="btn-toolbar justify-content-between" role="toolbar" >

        <div class="input-group-append col-md-6">
            <input type="text" class="form-control " placeholder="Pesquisar" id="filtro" name="filtro">
 
        </div>
        <div class="input-group">
            <div>
                <a href="cadastroProduto.jsp" class="btn btn-outline-primary">Novo Produto</a>
            </div>

        </div>
    </div>
    <br>
    <br>

    <table class="table table-hover">
        <thead> 
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Nome</th>
                <th scope="col">Preço Compra</th>
                <th scope="col">Preço Venda</th>
                <th scope="col">Plataforma</th>
                <th scope="col">Categoria</th>
                <th scope="col">Genero</th>
                <th scope="col">Status</th>
                <th scope="col">#</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${produtos}">
            <tr>
                <td>${p.idProduto}</td>
                <td>${p.nome}</td>
                <td>${p.precoCompra}</td>
                <td>${p.precoVenda}</td>
                <td>${p.plataforma}</td>
                <td>${p.categoria}</td>
                <td>${p.genero}</td>
                <td>${p.status}</td>
       
                <td>
                    <c:url var="alterarProduto" value="/Produtos">
                            <c:param name="acao" value="alterar" />
                            <c:param name="idProduto" value="${p.idProduto}" />
                        </c:url>
                    <a href="alterarProduto.jsp" class="btn btn-outline-warning">Editar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<script>
    $('#filtro').on('keyup', function () {
        var value = $(this).val();
        var patt = new RegExp(value, "i");

        $('#tabela').find('tr').each(function () {
            if (!($(this).find('td').text().search(patt) >= 0)) {
                $(this).not('.myHead').hide();
            }
            if (($(this).find('td').text().search(patt) >= 0)) {
                $(this).show();
            }

        });

    });
</script>
