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

    <table class="table table-hover border border-primary" id="table">
        <thead>
            <tr class="table-primary">
                <th scope="col">ID Venda</th>
                <th scope="col">Nome do Cliente</th>
                <th scope="col">CPF/CNPJ Cliente</th>
                <th scope="col">Data da Venda</th>
                <th scope="col">Forma de Pagto.</th>
                <th scope="col">Nº parcelas</th>
                <th scope="col">Valor parcela</th>
                <th scope="col">Vendedor(a)</th>
                <th scope="col">Filial</th>
                <th scope="col">Valor Total R$</th>
                <th scope="col">Status</th>
            </tr>
        </thead>

        <tbody class="table" id="tabela" name="tabela">
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
                    url: 'Relatorios?acao=relatorioVendas',
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
                        $('aguarde').prop('hidden', true);
                        $('#loader').prop('hidden', true);
                        $("#adicionando").remove();
                        $('#btnPesquisa').prop('disabled', false);
                        $('#btnExportar').prop('disabled', false);
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
            var documento = lista[i].pedido.cliente.documento.cpf === '' ? lista[i].pedido.cliente.documento.cnpj : lista[i].pedido.cliente.documento.cpf;
            var valorParcela = lista[i].pedido.parcela === 0 ? 0 : (lista[i].valorTotal / lista[i].pedido.parcela).toLocaleString('pt-br', {minimumFractionDigits: 2});
            var status = lista[i].pedido.status === 1 ? 'Concluído' : 'Cancelado';
            if(lista[i].pedido.status === 1){
                s += '<tr class=" text-center">';
            } else{
                s += '<tr class=" text-center text-danger">';
            }          
            s += '<td class="text-center">' + lista[i].pedido.idPedido + '</td>';
            s += '<td class="text-center">' + lista[i].pedido.cliente.nome + '</td>';
            s += '<td class="text-center">' + documento + '</td>';
            s += '<td class="text-center">' + lista[i].dataFormatada + '</td>';
            s += '<td class="text-center">' + lista[i].pedido.descFormaPagamento + '</td>';
            s += '<td class="text-center">' + lista[i].pedido.parcela + '</td>';
            s += '<td class="text-center">' + valorParcela + '</td>';
            s += '<td class="text-center">' + lista[i].pedido.usuario.nome + '</td>';
            s += '<td class="text-center">' + lista[i].pedido.filial.nome + '</td>';
            s += '<td class="text-center">' + lista[i].valorTotal.toLocaleString('pt-br', {minimumFractionDigits: 2}) + '</td>';
            s += '<td class="text-center">' + status + '</td>';
            $('#tabela').html(s);
            total += lista[i].pedido.status === 0 ? 0 : lista[i].valorTotal;
        }
        var foot = '';
        foot += '<tr class="blockquote"> <td colspan="2"><strong>Total Líquido:</strong></td><td>R$ ' + total.toLocaleString('pt-br', {minimumFractionDigits: 2}) + '</td> </tr>';
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