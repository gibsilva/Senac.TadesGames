<%-- 
    Document   : RelatorioVendas
    Created on : 28/04/2019, 02:33:39
    Author     : Gi
--%>
<%@include file="header.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<title>Relatorio de Vendas</title>

<div class="container">
    <br>
    <h2> Relatório de Vendas</h2>
    <hr>
    <br>
    <form action="Relatorios" method="post">
        <div class="row">
            <div class="form-group-inline col-md-2">
                <label>De:</label>
                <input type="date" class="form-control"  id="dataInicio" name="dataInicio" id="dataInicio" required>
            </div>

            <div class="form-group col-md-2">
                <label>Até:</label>
                <input type="date" class="form-control" id="dataFim" name="dataFim" id="dataFim" required>
            </div>

            <div class="form-group col-md-2">
                <button type="button" id="btnPesquisa" style="margin-top: 30px" class="btn btn-primary" onclick="gerarRelatorio()" data-toggle="tooltip" data-placement="right" title="Gerar Relatorio">Gerar</button>
            </div>
            <div class="form-group col-md-2">
                <button type="button" id="btnExportar" style="margin-top: 30px" class="btn btn-primary" onclick="exportarExcel()" data-toggle="tooltip" data-placement="right" title="Exportar Relatorio">Exportar</button>
            </div>
        </div>
    </form>

    <br>

    <table class="table table-hover" id="table">
        <thead>
            <tr>
                <th scope="col">ID Venda</th>
                <th scope="col">Nome do Cliente</th>
                <th scope="col">CPF/CNPJ Cliente</th>
                <th scope="col">Data da Venda</th>
                <th scope="col">Forma de Pagto.</th>
                <th scope="col">Filial</th>
                <th scope="col">Valor Total R$</th>
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
        if($('#dataInicio').val() === '' || $('#dataFim').val() === ''){
            toastr.warning('Preencha os parâmetros antes de gerar o relatório', 'Atenção');
        } else {
            $.ajax({
                url: 'Relatorios?acao=relatorioVendas',
                type: 'GET',
                contentType: 'application/json',
                data: {'dataInicio': $('#dataInicio').val(), 'dataFim': $('#dataFim').val()},
                success: function (data) {
                    lista = $.parseJSON(data);
                    carregarTabela(lista);
                },
                error: function () {
                    toastr.error('Ocorreu um erro ao gerar o relatório', 'Erro');
                }
            });
        }
    }

    function carregarTabela(lista) {
        var s = '';
        var total = 0;
        for (var i = 0; i < lista.length; i++) {
            s += '<tr class="table-light text-center">';
            s += '<td class="text-center">' + lista[i].pedido.idPedido + '</td>';
            s += '<td class="text-center">' + lista[i].pedido.cliente.nome + '</td>';
            s += '<td class="text-center">' + lista[i].pedido.cliente.cpf + '</td>';
            s += '<td class="text-center">' + lista[i].dataFormatada + '</td>';
            s += '<td class="text-center">' + lista[i].pedido.descFormaPagamento + '</td>';
            s += '<td class="text-center">' + lista[i].pedido.filial.nome + '</td>';
            s += '<td class="text-center">' + lista[i].valorTotal + '</td>';           
            $('#tabela').html(s);
            total += lista[i].valorTotal;
        }
        var foot = '';
        foot += '<tr> <td><strong>Total do Período:</strong></td><td>R$ ' + total + '</td> </tr>';
        $('#totalTabela').html(foot);
    }

    function exportarExcel() {
        if (lista.length === 0) {
            toastr.warning('Não é possível exportar um relatório vazio', 'Atenção');
        } else {
            $('#table').table2excel({
                exclude: '.noExl',
                name: 'dados',
                filename: 'Relatório de Vendas'
            });
        }
    }
    
</script>