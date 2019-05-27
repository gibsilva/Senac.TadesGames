<%-- 
    Document   : detalhesVenda
    Created on : 04/05/2019, 19:13:23
    Author     : Gi
--%>
<%@include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Detalhes da Venda</title>

<div class="container">
    <br>
    <h2>Detalhes da Venda</h2>
    <hr>
    <br>

    <div class="row">
        <div class="form-group col-md-2">
            <label for="name">Id Venda</label>
            <input type="text" class="form-control" name="idVenda" id="idVenda" readonly value="${pedido.idPedido}">   
        </div>

        <div class="form-group col-md-5">
            <label for="name">Cliente</label>
            <input type="text" class="form-control" name="nome" id="nome" readonly value="${pedido.cliente.nome}">   
        </div>

        <div class="form-group col-md-3">
            <label for="cpf">CPF/CNPJ</label>
            <input type="text" class="form-control " name="cpf" id="cpf" readonly value="${pedido.cliente.documento.toString()}">
        </div>
    </div>

    <div class="row">
        <div class="form-group col-md-2">
            <label for="FormaPagamento">Forma de Pagamento</label>
            <input type="text" class="form-control " name="formaPagemento" id="formaPagemento" readonly value="${pedido.descFormaPagamento}">
        </div>

        <div class="form-group col-md-2">
            <label for="Data">Data</label>
            <input type="date" class="form-control"  id="data" name="data" readonly value="${pedido.dataPedido}">
        </div>

        <div class="form-group col-md-3">
            <label  for="inputTotal">Total:</label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text">R$</span>
                </div>
                <input type="text" class="form-control" id="inputValorVenda" placeholder="00,00" readonly value="${pedido.valorTotal}">
            </div>
        </div>

        <div class="form-group col-md-2">
            <label for="name">Status</label>
            <input type="text" class="form-control" name="status" id="status" readonly value="">   
        </div>
        <button type="button" id="btnCancelar" name="btnCancelar" class="btn btn-md btn-danger" style="margin-left: 1040px">Cancelar</button>
    </div>

    <br>
    <table class="table table-hover">
        <thead> 
            <tr>
                <th class="text-center" scope="col">Id</th>
                <th class="text-center" scope="col">Produto</th>
                <th class="text-center" scope="col">Categoria</th>
                <th class="text-center" scope="col">Plataforma</th>
                <th class="text-center" scope="col">Genero</th>
                <th class="text-center" scope="col">Quantidade</th>
                <th class="text-center" scope="col">Valor Un. R$</th>
            </tr>
        </thead>
        <tbody id="tabela" name="tabela">
            <c:forEach var="p" items="${pedido.itensPedido}">
                <tr>
                    <td class=" text-center">${p.produto.idProduto}</td>
                    <td class=" text-center">${p.produto.nome}</td>
                    <td class=" text-center">${p.produto.categoria.nome}</td>
                    <td class=" text-center">${p.produto.plataforma.nome}</td>
                    <td class="text-center">${p.produto.genero.nome}</td>
                    <td class=" text-center">${p.quantidade}</td>
                    <td class=" text-center">${p.valorUnitario}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table> 
    <hr>
    <a href="Vendas?acao=listar" class="btn btn-secondary">Voltar</a>
</div>


<!-- Modal -->
<div class="modal fade" id="modalCancelar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Cancelamento da Venda</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Tem certeza que deseja <strong>cancelar</strong> essa venda?</p>
                <small class="text-warning">As quantidades dos itens serão colocadas de volta no estoque.</small>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Fechar</button>
                <button type="button" class="btn btn-outline-danger" onclick="cancelarPedido()">Cancelar Pedido</button>
            </div>
        </div>
    </div>
</div>

<script>
    $(document).ready(function () {

        if (${pedido.status} === 1) {
            $('#status').val('Concluído');
            if ('${sessionScope.usuarioLogado.cargo}' === 'Vendedor (a)') {
                $('#btnCancelar').prop('disabled', true);
            } else {
                $('#btnCancelar').prop('disabled', false);
            }
            if (getDataAtual() > $('#data').val()) {
                $('#btnCancelar').prop('disabled', true);
            } else {
                $('#btnCancelar').prop('disabled', false);
            }
        } else {
            $('#status').val('Cancelado');
            $('#btnCancelar').prop('disabled', true);
        }

        $('#btnCancelar').click(function () {
            $('#modalCancelar').modal();
        });
    });

    function cancelarPedido() {
        $.ajax({
            url: 'Vendas?acao=cancelar',
            type: 'POST',
            data: {'idPedido': $('#idVenda').val()},
            success: function (data) {
                $('#modalCancelar').modal('hide');
                toastr.success('Venda cancelada com sucesso.', 'Sucesso');
                $('#status').val('Cancelado');
                $('#btnCancelar').prop('disabled', true);
            },
            error: function () {
                $('#modalCancelar').modal('hide');
                toastr.error('Ocorreu um erro ao cancelar a venda.', 'Erro');
            }
        });
    }

    function getDataAtual() {
        var dataAtual = new Date().toISOString().slice(0, 10);
        return dataAtual;
    }

</script>