<%-- 
    Document   : RelatorioCliente
    Created on : 28/04/2019, 02:32:56
    Author     : Gi
--%>
<%@include file="header.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<title>Relatorio de Cliente</title>

<div class="container">
    <br>
    <h2> Relatório de Clientes</h2>
    <hr>
    <br>

    <div class="row">
        <div class="form-group-inline col-md-2">
            <label>De:</label>
            <input type="date" class="form-control"  id="dataInicio" name="dataInicio">
        </div>

        <div class="form-group col-md-2">
            <label>Até:</label>
            <input type="date" class="form-control" id="dataFim" name="dataFim">
        </div>

        <div class="form-group col-md-2">
            <button type="button" id="btnPesquisa" style="margin-top: 30px" class="btn btn-primary" onclick="gerarRelatorio()" data-toggle="tooltip" data-placement="right" title="Gerar Relatorio">Gerar</button>
        </div>
        <div class="form-group col-md-2">
            <button type="button" id="btnExportar" style="margin-top: 30px" class="btn btn-primary" onclick="exportarExcel()" data-toggle="tooltip" data-placement="right" title="Exportar Relatorio">Exportar</button>
        </div>

    </div>

    <br>

    <table class="table table-hover" id="table">
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Nome</th>
                <th scope="col">CPF</th>
                <th scope="col">CNPJ</th>
                <th scope="col">Qtd de Compras</th>
                <th scope="col">Ultima Compra</th>
                <th scope="col">Total Comprado</th>
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
        if ($('#dataInicio').val() === '' || $('#dataFim').val() === '') {
            toastr.warning('Preencha os parâmetros antes de gerar o relatório', 'Atenção');
        } else {
            $.ajax({
                url: 'Relatorios?acao=relatorioClientes',
                type: 'GET',
                contentType: 'application/json',
                data: {'dataInicio': $('#dataInicio').val(), 'dataFim': $('#dataFim').val()},
                success: function (data) {
                    lista = $.parseJSON(data);
                    console.log(lista);
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
            s += '<td class="text-center">' + lista[i].idCliente + '</td>';
            s += '<td class="text-center">' + lista[i].nomeCliente + '</td>';
            s += '<td class="text-center">' + lista[i].cpf + '</td>';
            s += '<td class="text-center">' + lista[i].cnpj + '</td>';
            s += '<td class="text-center">' + lista[i].qtdPedidos + '</td>';
            s += '<td class="text-center">' + lista[i].dataUltimoPedido + '</td>';
            s += '<td class="text-center">' + lista[i].totalComprado + '</td>';
            if(lista[i].ativo === true){
                s += '<td class="text-center">' + 'Ativo' + '</td>';
            } else{
                s += '<td class="text-center">' + 'Inativo' + '</td>';
            }
            $('#tabela').html(s);
            total += lista[i].totalComprado;
        }
        var foot = '';
        foot += '<tr> <td><strong>Total:</strong></td><td>R$ ' + total + '</td> </tr>';
        $('#totalTabela').html(foot);
    }

    function exportarExcel() {
        if (lista.length === 0) {
            toastr.warning('Não é possível exportar um relatório vazio', 'Atenção');
        } else {
            $('#table').table2excel({
                exclude: '.noExl',
                name: 'dados',
                filename: 'Relatório de Clientes'
            });
        }
    }
</script>