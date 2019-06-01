<%-- 
    Document   : RelatorioProduto
    Created on : 28/04/2019, 02:33:26
    Author     : Gi
--%>
<%@include file="header.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<title>Relatorio de Produto</title>
<div class="container">
    <br>
    <h2> Relatorio de Produtos</h2>
    <hr>
    <br>
    <form action="Relatorios" method="post">
        <div class="row">
            <div class="form-group-inline col-md-2">
                <label>De:</label>
                <input type="date" class="form-control"  id="dataInicio" name="dataInicio">
            </div>

            <div class="form-group col-md-2">
                <label>Até:</label>
                <input type="date" class="form-control" id="dataFim" name="dataFim">
            </div>

            <c:if test="${filiais != null}">
                <div class="form-group col-md-2" id="filiais">
                    <label for="inputFilial">Filial<h11 class="text-danger">*</h11></label>
                    <select id="filial" name="filial" class="custom-select">
                        <option value="0">Todas</option>
                        <c:forEach var="f" items="${filiais}">
                            <option value="${f.idFilial}">${f.nome}</option>
                        </c:forEach>
                    </select>
                </div>
            </c:if>

            <div class="form-group col-md-2">
                <button type="button" id="btnPesquisa" style="margin-top: 30px" class="btn btn-primary" onclick="gerarRelatorio()" data-toggle="tooltip" data-placement="right" title="Gerar Relatorio"><i class="fas fa-check"></i> Gerar</button>
            </div>
            <div class="form-group col-md-2">
                <button type="button" id="btnExportar" style="margin-top: 30px" class="btn btn-primary" onclick="exportarExcel()" data-toggle="tooltip" data-placement="right" title="Exportar Relatorio"><i class="fas fa-file-export"></i> Exportar</button>
            </div>

        </div>
    </form>

    <div id="aguarde" class="text-center">

    </div>

    <br>

    <table class="table table-hover" id ="table">
        <thead>
            <tr>
                <th scope="col">ID Produto</th>
                <th scope="col">Nome</th>
                <th scope="col">Qtd Vendido</th>
                <th scope="col">Total Vendido</th>
                <th scope="col">Categoria</th>
                <th scope="col">Plataforma</th>
                <th scope="col">Gênero</th>
                <th scope="col">Preço de compra</th>
                <th scope="col">Preço de venda</th>
                <th scope="col">Qtd. Estoque</th>
                <th scope="col">Filial</th>
                <th scope="col">Ultima Venda</th>
                <th scope="col">Status</th>
            </tr>
        </thead>

        <tbody id="tabela" name="tabela">
            <!--preenchido conforme a consulta -->
        </tbody>
        <tfoot id="totalTabela">
            <!--total das vendas do período -->
        </tfoot>
    </table>

    <hr>

</div>

<script>
    var lista = '';

    function gerarRelatorio() {
        if (validarDatas()) {
            var filial = $('#filial').val();
            filial = typeof filial === 'undefined' ? '${sessionScope.usuarioLogado.idFilial}' : $('#filial').val();
            if ($('#dataInicio').val() === '' || $('#dataFim').val() === '') {
                toastr.warning('Preencha os parâmetros antes de gerar o relatório', 'Atenção');
            } else {
                $.ajax({
                    url: 'Relatorios?acao=relatorioProdutos',
                    type: 'GET',
                    contentType: 'application/json',
                    data: {'dataInicio': $('#dataInicio').val(), 'dataFim': $('#dataFim').val(), 'filial': filial},
                    beforeSend: function () {
                        $('#aguarde').html("<img id='loader' src='../resources/img/ajax-loader.gif'/><br /> <p id='adicionando'>Gerando relatório... Por favor aguarde.</p>");
                        $('#btnPesquisa').prop("disabled", true);
                        $('#btnExportar').prop("disabled", true);
                    },
                    success: function (data) {
                        lista = $.parseJSON(data);
                        carregarTabela(lista);
                    },
                    error: function () {
                        toastr.error('Ocorreu um erro ao gerar o relatório', 'Erro');
                    },
                    complete: function () {
                        $("aguarde").prop("hidden", true);
                        $("#loader").prop("hidden", true);
                        $("#adicionando").remove();
                        $('#btnPesquisa').prop("disabled", false);
                        $('#btnExportar').prop("disabled", false);
                    }
                });
            }
        }
    }

    function validarDatas() {
        if ($('#dataInicio').val() > $('#dataFim').val()) {
            toastr.warning('Data de início não pode ser maior que a data final', 'Atenção');
            $('#dataInicio').val('');
            $('#dataFim').val('');
            return false;
        }
        return true;
    }

    function carregarTabela(lista) {
        var s = '';
        $('#tabela').html(s);
        var total = 0;
        for (var i = 0; i < lista.length; i++) {
            s += '<tr class="table-light text-center">';
            s += '<td class="text-center">' + lista[i].idProduto + '</td>';
            s += '<td class="text-center">' + lista[i].nomeProduto + '</td>';
            s += '<td class="text-center">' + lista[i].qtdProduto + '</td>';
            s += '<td class="text-center">' + lista[i].totalVendido.toLocaleString('pt-br', {minimumFractionDigits: 2}) + '</td>';
            s += '<td class="text-center">' + lista[i].categoria + '</td>';
            s += '<td class="text-center">' + lista[i].produto.plataforma.nome + '</td>';
            s += '<td class="text-center">' + lista[i].produto.genero.nome + '</td>';
            s += '<td class="text-center">' + lista[i].produto.precoCompra.toLocaleString('pt-br', {minimumFractionDigits: 2}) + '</td>';
            s += '<td class="text-center">' + lista[i].produto.precoVenda.toLocaleString('pt-br', {minimumFractionDigits: 2}) + '</td>';
            s += '<td class="text-center">' + lista[i].produto.quantidadeEstoque + '</td>';
            s += '<td class="text-center">' + lista[i].produto.filial.nome + '</td>';
            s += '<td class="text-center">' + lista[i].dataUltimaVenda + '</td>';
            if (lista[i].ativo === true) {
                s += '<td class="text-center">' + 'Ativo' + '</td>';
            } else {
                s += '<td class="text-center">' + 'Inativo' + '</td>';
            }
            $('#tabela').html(s);
            total += lista[i].totalVendido;
        }
        var foot = '';
        foot += '<tr> <td><strong>Total vendido:</strong></td><td>R$ ' + total.toLocaleString('pt-br', {minimumFractionDigits: 2}) + '</td> </tr>';
        $('#totalTabela').html(foot);
    }

    function exportarExcel() {
        if (lista.length === 0) {
            toastr.warning('Não é possível exportar um relatório vazio', 'Atenção');
        } else {
            $('#table').table2excel({
                exclude: '.noExl',
                name: 'dados',
                filename: 'Relatório de Produtos'
            });
        }
    }
</script>