<%-- 
    Document   : consultaCategoria
    Created on : 05/05/2019, 15:28:02
    Author     : Gi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>

<title>Consulta de Plataforma</title>

<div class="container">
    <br>
    <h2>Consulta de Plataforma</h2>
    <hr>
    <br>
    <div class="btn-toolbar justify-content-between" role="toolbar" aria-label="Toolbar with button groups">

        <div class="input-group-append col-md-6">
            <input type="text" class="form-control " placeholder="Pesquisar" id="filtro" name="filtro">

        </div>
        <div class="input-group">
            <div>
                <a href="cadastroPlataforma.jsp" class="btn btn-outline-primary">Nova Plataforma</a>
            </div>
        </div>
    </div>

    <br>
    <br>

    <table id="tabelaPlataformas" class="table table-hover">
        <thead> 
            <tr>
                <th scope="col">Id</th>
                <th class="text-center" scope="col">Nome</th>
                <th class="text-center" scope="col">#</th>

            </tr>
        </thead>
        <tbody id="tabela" name="tabela">
            <c:forEach var="p" items="${plataformas}">
                <tr>
                    <td>${p.idPlataforma}</td>
                    <td class="text-center">${p.nome}</td>

                    <td class="text-center">
                        <c:url var="alterarPlataforma" value="/Plataformas">
                            <c:param name="acao" value="alterar" />
                            <c:param name="idPlataforma" value="${p.idPlataforma}" />
                        </c:url>
                        <a href="${alterarPlataforma}" class="btn btn-sm btn-outline-warning">Editar</a>

                        <c:url var="excluir" value="/Plataformas">
                            <c:param name="excluir" value="excluir" />
                            <c:param name="idPlataforma" value="${p.idPlataforma}" />
                        </c:url>
                        <button type="button" class="btn btn-sm btn-outline-danger" onclick="confirmaExclusao(${p.idPlataforma})">Excluir</button>
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
                <h5 class="modal-title" id="exampleModalLabel">Excluir Plataforma</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <input type="hidden" value="" name="idPlataformaModal" id="idPlataformaModal">
                Deseja realmente excluir a plataforma de Id <strong id="idTextPlataformaModal"></strong>?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" onclick="excluir($('#idPlataformaModal').val())">Excluir</button>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Fechar</button>
            </div>
        </div>
    </div>
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

    function confirmaExclusao(id) {
        $('#modalExemplo').modal('show');
        $('#idTextPlataformaModal').text(id);
        $('#idPlataformaModal').val(id);
    }

    function excluir(id) {
        $.ajax({
            url: 'Plataformas?acao=excluir',
            type: 'POST',
            data: {'idPlataforma': id},
            success: function (data) {
                $('#modalExemplo').modal('hide');
                $("#tabelaPlataformas").load("Plataformas #tabelaPlataformas");
                toastr.success('Plataforma removida', 'Info');
            },
            error: function (jqXHR, textStatus, errorThrown) {
                toastr.error('Ocorreu um erro ao remover', 'Erro');
            }
        });
    }
</script>
