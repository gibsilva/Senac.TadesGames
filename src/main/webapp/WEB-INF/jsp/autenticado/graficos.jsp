<%-- 
    Document   : graficoVendasPorFilial
    Created on : 31/05/2019, 23:15:50
    Author     : Gi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>

<div class="container">
    <br>
    <h3>Gráficos</h3>
    <div class="row">
        <div class="form-group col-md-3" id="filiais">
            <label for="grafico">Selecione o relatório<h11 class="text-danger">*</h11></label>
            <select id="grafico" name="grafico" class="custom-select">
                <option value="">Selecione</option>
                <option value="VF">Vendas por filial</option>
                <option value="MV">Melhores vendedores por filial</option>
                <option value="PV">Produtos mais vendidos por filial</option>
            </select>
        </div>

        <div class="form-group-inline col-md-2">
            <label>De:</label>
            <input type="date" class="form-control"  id="dataInicio" name="dataInicio" id="dataInicio" required>
        </div>

        <div class="form-group col-md-2">
            <label>Até:</label>
            <input type="date" class="form-control" id="dataFim" name="dataFim" id="dataFim" required>
        </div>

        <div class="form-group col-md-2">
            <button type="button" id="btnPesquisa" style="margin-top: 30px" class="btn btn-primary" onclick="gerarRelatorio($('#grafico').val())" data-toggle="tooltip" data-placement="right" title="Gerar Relatorio" disabled><i class="fas fa-check" ></i> Gerar</button>
        </div>
    </div>


    <div id="piechart" style="width: 900px; height: 500px;"></div>
</div>



<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript" src="../resources/js/jsapi.js"></script>
<script type="text/javascript" src="../resources/js/jsapiAutoLoad.js"></script>
<script type="text/javascript">
                $(document).ready(function () {
                    $('#btnPesquisa').prop('disabled', false);
                });

                function gerarRelatorio(tipo) {
                    if (tipo === '') {
                        toastr.warning('Por favor selecione o tipo de gráfico antes de gerar', 'Atenção');
                        $('#grafico').focus();
                        $('#piechart').html('');
                    } else if ($('#dataInicio').val() === '' || $('#dataFim').val() === '') {
                        toastr.warning('Preencha os filtros com as datas antes de gerar', 'Atenção');
                    } else if (validarDatas()) {
                        switch (tipo) {
                            case 'VF':
                                vendasPorFilial();
                                break;
                            case "MV":
                                vendasPorVendedor();
                                break;
                            case "PV":
                                produtosVendidos();
                                break;
                            default:
                                toastr.warning('tipo invalido', 'Atenção');
                                break;
                        }
                    }
                }

                function vendasPorFilial() {
                    $.ajax({
                        type: 'GET',
                        contentType: 'application/json; charset=utf-8',
                        url: 'Relatorios?acao=gerarGraficos',
                        dataType: 'json',
                        data: {'tipo': 'vendasPorFilial', 'dataInicio': $('#dataInicio').val(), 'dataFim': $('#dataFim').val()},
                        success: function (browsersData) {
                            var data = new google.visualization.DataTable();
                            data.addColumn('string', 'word');
                            data.addColumn('number', 'count');
                            $.each(browsersData, function (i, d) {
                                data.addRow([browsersData[i].filial + " - R$ " + browsersData[i].total.toLocaleString('pt-br', {minimumFractionDigits: 2}), browsersData[i].total]);
                            });
                            var options = {
                                title: '% Vendas por Filiais'
                            };
                            var chart = new google.visualization.PieChart(document.getElementById('piechart'));
                            chart.draw(data, options);
                        },
                        error: function () {
                            toastr.error('Ocorreu um erro ao gerar o relatório', 'Erro');
                        }
                    });
                }
                function vendasPorVendedor() {
                    $.ajax({
                        type: 'GET',
                        contentType: 'application/json; charset=utf-8',
                        url: 'Relatorios?acao=gerarGraficos',
                        dataType: 'json',
                        data: {'tipo': 'vendasPorVendedor', 'dataInicio': $('#dataInicio').val(), 'dataFim': $('#dataFim').val()},
                        success: function (browsersData) {
                            var data = new google.visualization.DataTable();
                            data.addColumn('string', 'word');
                            data.addColumn('number', 'count');
                            $.each(browsersData, function (i, d) {
                                data.addRow([browsersData[i].vendedor + " - " + browsersData[i].filial, browsersData[i].qtdVendas]);
                            });
                            var options = {
                                title: 'Desempenho por vendedor'
                            };
                            var chart = new google.visualization.PieChart(document.getElementById('piechart'));
                            chart.draw(data, options);
                        },
                        error: function () {
                            toastr.error('Ocorreu um erro ao gerar o relatório', 'Erro');
                        }
                    });
                }
                function produtosVendidos() {
                    $.ajax({
                        type: 'GET',
                        contentType: 'application/json; charset=utf-8',
                        url: 'Relatorios?acao=gerarGraficos',
                        dataType: 'json',
                        data: {'tipo': 'produtosVendidos', 'dataInicio': $('#dataInicio').val(), 'dataFim': $('#dataFim').val()},
                        success: function (browsersData) {
                            var data = new google.visualization.DataTable();
                            data.addColumn('string', 'word');
                            data.addColumn('number', 'count');
                            $.each(browsersData, function (i, d) {
                                data.addRow([browsersData[i].produto, browsersData[i].qtdVendido]);
                            });
                            var options = {
                                title: 'Produtos mais vendidos'
                            };
                            var chart = new google.visualization.PieChart(document.getElementById('piechart'));
                            chart.draw(data, options);
                        },
                        error: function () {
                            toastr.error('Ocorreu um erro ao gerar o relatório', 'Erro');
                        }
                    });
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
</script>