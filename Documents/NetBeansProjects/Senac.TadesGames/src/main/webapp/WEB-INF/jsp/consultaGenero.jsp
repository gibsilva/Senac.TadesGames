<%-- 
    Document   : consultaCategoria
    Created on : 05/05/2019, 15:28:02
    Author     : Gi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>

<title>Consulta de Gênero</title>

<div class="container">
    <br>
    <h2>Consulta de Gênero</h2>
    <hr>
    <br>
    <div class="btn-toolbar justify-content-between" role="toolbar" aria-label="Toolbar with button groups">

        <div class="input-group-append col-md-6">
            <input type="text" class="form-control " placeholder="Pesquisar" id="filtro" name="filtro">

        </div>
        <div class="input-group">
            <div>
                <a href="cadastroGenero.jsp" class="btn btn-outline-primary">Novo Gênero</a>
            </div>
        </div>
    </div>

    <br>
    <br>

    <table id="tabelaGeneros" class="table table-hover">
        <thead> 
            <tr>
                <th class="text-center" scope="col">Id</th>
                <th class="text-center" scope="col">Nome</th>
                <th class="text-center" scope="col">#</th>

            </tr>
        </thead>
        <tbody id="tabela" name="tabela">
            <c:forEach var="g" items="${generos}">
                <tr>
                    <td class="text-center">${g.idGenero}</td>
                    <td class="text-center">${g.nome}</td>

                    <td class="text-center">
                        <c:url var="alterarGenero" value="/Generos">
                            <c:param name="acao" value="alterar" />
                            <c:param name="idGenero" value="${g.idGenero}" />
                        </c:url>
                        <a href="${alterarGenero}" class="btn btn-sm btn-outline-warning">Editar</a>

                        <c:url var="excluir" value="/Generos">
                            <c:param name="excluir" value="excluir" />
                            <c:param name="idGenero" value="${g.idGenero}" />
                        </c:url>
                        <button type="button" class="btn btn-sm btn-outline-danger" onclick="confirmaExclusao(${g.idGenero})">Excluir</button>
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
                <h5 class="modal-title" id="exampleModalLabel">Excluir Gênero</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <input type="hidden" value="" name="idGeneroModal" id="idGeneroModal">
                Deseja realmente excluir a plataforma de Id <strong id="idTextGeneroModal"></strong>?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" onclick="excluir($('#idGeneroModal').val())">Excluir</button>
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
            toastr.success('Gênero salvo com sucesso', 'Sucesso');
        } else if (statusAlterado === 'true') {
            toastr.success('Gênero alterado com sucesso', 'Sucesso');
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
        $('#idTextGeneroModal').text(id);
        $('#idGeneroModal').val(id);
    }

    function excluir(id) {
        $.ajax({
            url: 'Generos?acao=excluir',
            type: 'POST',
            data: {'idGenero': id},
            success: function (data) {
                $('#modalExemplo').modal('hide');
                $("#tabelaGeneros").load("Generos #tabelaGeneros");
                toastr.success('Gênero removido', 'Sucesso');
            },
            error: function (jqXHR, textStatus, errorThrown) {
                toastr.error('Ocorreu um erro ao remover', 'Erro');
            }
        });
    }
</script>

