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
            <button type="button" id="btnPesquisa" style="margin-top: 30px" class="btn btn-primary" onclick="gerarRelatorio()">Gerar</button>
        </div>
        <div class="form-group col-md-2">
            <button type="button" id="btnExportar" style="margin-top: 30px" class="btn btn-primary" onclick="exportarExcel()">Exportar</button>
        </div>

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
        if ($('#dataInicio').val() === '' || $('#dataFim').val() === '') {
            toastr.warning('Preencha os parâmetros antes de gerar o relatório', 'Atenção');
        } else {
            $.ajax({
                url: 'Relatorios?acao=relatorioProdutos',
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
            s += '<td class="text-center">' + lista[i].idProduto + '</td>';
            s += '<td class="text-center">' + lista[i].nomeProduto + '</td>';
            s += '<td class="text-center">' + lista[i].qtdProduto + '</td>';
            s += '<td class="text-center">' + lista[i].totalVendido + '</td>';
            s += '<td class="text-center">' + lista[i].categoria + '</td>';
            s += '<td class="text-center">' + lista[i].dataUltimaVenda + '</td>';
            if(lista[i].ativo === true){
                s += '<td class="text-center">' + 'Ativo' + '</td>';
            } else{
                s += '<td class="text-center">' + 'Inativo' + '</td>';
            }
            $('#tabela').html(s);
            total += lista[i].totalVendido;
        }
        var foot = '';
        foot += '<tr> <td><strong>Total vendido:</strong></td><td>R$ ' + total + '</td> </tr>';
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