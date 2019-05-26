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
                <a href="${pageContext.request.contextPath}/autenticado/Produtos?acao=salvar" class="btn btn-primary" data-toggle="tooltip" data-placement="left" title="Novo Produto"><i class='fas fa-plus-circle'></i> Novo Produto</a>
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
                <th class="text-center" scope="col">Preço de Compra</th>
                <th class="text-center" scope="col">Preço de Venda</th>
                <th class="text-center" scope="col">Plataforma</th>
                <th class="text-center" scope="col">Categoria</th>
                <th class="text-center" scope="col">Gênero</th>
                <th class="text-center" scope="col">Descrição</th>
                <th class="text-center" scope="col">Qtde. Estoque</th>
                <th class="text-center" scope="col">Status</th>
                <th class="text-center" scope="col">#</th>
            </tr>
        </thead>
        <tbody id="tabela" name="tabela">
            <c:forEach var="p" items="${produtos}">
                <tr>
                    <td class="text-center">${p.idProduto}</td>
                    <td class="text-center">${p.nome}</td>
                    <td class="text-center">${p.precoCompra}</td>
                    <td class="text-center">${p.precoVenda}</td>
                    <td class="text-center">${p.plataforma.nome}</td>
                    <td class="text-center">${p.categoria.nome}</td>
                    <td class="text-center">${p.genero.nome}</td>
                    <td class="text-center">${p.descricao}</td>
                    <td class="text-center">${p.quantidadeEstoque}</td>
                    <c:if test="${p.ativo == true}">
                        <td class="text-center">Ativo</td>
                    </c:if>
                    <c:if test="${p.ativo == false}">
                        <td class="text-center">Inativo</td>
                    </c:if>

                    <td class="text-center">
                        <c:url var="alterarProduto" value="/autenticado/Produtos">
                            <c:param name="acao" value="alterar" />
                            <c:param name="idProduto" value="${p.idProduto}" />
                        </c:url>
                        <a href="${alterarProduto}" class="btn btn-md btn-warning" data-toggle="tooltip" data-placement="right" title="Alterar Produto"><i class="fas fa-edit"></i></a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<script>
    $(document).ready(function () {
        var statusSalvo = '${statusSalvo}';
        var statusAlterado = '${statusAlterado}';
        if (statusSalvo === 'true') {
            toastr.success('Produto salvo com sucesso', 'Sucesso');
        } else if (statusAlterado === 'true') {
            toastr.success('Produto alterado com sucesso', 'Sucesso');
        }

    });


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
