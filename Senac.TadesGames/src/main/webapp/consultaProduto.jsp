<%-- 
    Document   : consultaProduto
    Created on : 21/04/2019, 00:26:08
    Author     : Gi
--%>

<%@include file="header.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<title>Consulta de Produto</title>

<div class="col-md-10 container">
    <br>
    <h2>Consulta de Produtos</h2>
    <hr>
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
                <th class="text-center" scope="col">Id</th>
                <th class="text-center" scope="col">Nome</th>
                <th class="text-center" scope="col">Preço Compra</th>
                <th class="text-center" scope="col">Preço Venda</th>
                <th class="text-center" scope="col">Plataforma</th>
                <th class="text-center" scope="col">Categoria</th>
                <th class="text-center" scope="col">Genero</th>
                <th class="text-center" scope="col">Descrição</th>
                <th class="text-center" scope="col">Qtd Estoque</th>
                <th class="text-center" scope="col">Status</th>
                <th class="text-center" scope="col">#</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="p" items="${produtos}">
            <tr>
                <td class="text-center">${p.idProduto}</td>
                <td class="text-center">${p.nome}</td>
                <td class="text-center">${p.precoCompra}</td>
                <td class="text-center">${p.precoVenda}</td>
                <td class="text-center">${p.plataforma}</td>
                <td class="text-center">${p.categoria}</td>
                <td class="text-center">${p.genero}</td>
                <td class="text-center">${p.descricao}</td>
                <td class="text-center">${p.qtdEstoque}</td>
                <td class="text-center">${p.status}</td>
       
                <td class="text-center">
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
