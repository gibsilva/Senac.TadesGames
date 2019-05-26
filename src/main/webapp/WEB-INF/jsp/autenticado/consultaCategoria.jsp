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
                <a href="${pageContext.request.contextPath}/autenticado/Categorias?acao=salvar" class="btn btn-primary" data-toggle="tooltip" data-placement="left" title="Nova Categoria"><i class='fas fa-plus-circle'></i> Nova Categoria</a>
            </div>
        </div>
    </div>
    <br>
    <br>
    <table id="tabelaCategorias" class="table table-hover">
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
                        <c:url var="alterarCategoria" value="/autenticado/Categorias">
                            <c:param name="acao" value="alterar" />
                            <c:param name="idCategoria" value="${cat.idCategoria}" />
                        </c:url>
                        <a href="${alterarCategoria}" class="btn btn-md btn-warning" data-toggle="tooltip" data-placement="right" title="Alterar Categoria"><i class="fas fa-edit"></i></a>

                        <c:url var="excluir" value="/autenticado/Categorias">
                            <c:param name="excluir" value="excluir" />
                            <c:param name="c" value="${cat.idCategoria}" />
                            <c:param name="nomeCategoria" value="${cat.nome}" />
                        </c:url>
                        <button type="button" class="btn btn-md btn-danger" onclick="confirmaExclusao(${cat.idCategoria})" data-toggle="tooltip" data-placement="right" title="Excluir Categoria"><i class="far fa-trash-alt"></i></button>
                    </td>

                </tr>
            </c:forEach>
        </tbody>
    </table>   
</div>

<!-- Modal -->
<div class="modal fade" id="modalExemplo" name="modalExemplo" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Excluir Categoria</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <input type="hidden" value="" name="idCategoriaModal" id="idCategoriaModal">
                Deseja realmente excluir a categoria de Id <strong id="idTextCategoriaModal"></strong>?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" onclick="excluir($('#idCategoriaModal').val())">Excluir</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Fechar</button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {
        var statusSalvo = '${statusSalvo}';
        var statusAlterado = '${statusAlterado}';
        if (statusSalvo === 'true') {
            toastr.success('Categoria salva com sucesso', 'Sucesso');
        } else if (statusAlterado === 'true') {
            toastr.success('Categoria alterada com sucesso', 'Sucesso');
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

    function confirmaExclusao(id) {
        $('#modalExemplo').modal('show');
        $('#idTextCategoriaModal').text(id);
        $('#idCategoriaModal').val(id);
    }

    function excluir(id) {
        $.ajax({
            url: 'Categorias?acao=excluir',
            type: 'POST',
            data: {'idCategoria': id},
            success: function (data) {
                var categoria = $.parseJSON(data);
                if (categoria.produtos.length !== 0) {
                    toastr.error('Não é possível excluir a categoria, pois existem ' + categoria.produtos.length + ' produto(s) atribuídos a ela', 'Erro');
                    $('#modalExemplo').modal('hide');
                } else {
                    $('#modalExemplo').modal('hide');
                    $("#tabelaCategorias").load("Categorias #tabelaCategorias");
                    toastr.success('Categoria removida', 'Info');
                }

            },
            error: function (data) {
                toastr.error('Ocorreu um erro ao remover', 'Erro');
            }
        });
    }
</script>