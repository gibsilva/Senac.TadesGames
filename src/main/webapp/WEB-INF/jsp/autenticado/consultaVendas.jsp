<%-- 
    Document   : ConsultaVendas
    Created on : 28/04/2019, 02:34:00
    Author     : Gi
--%>
<%@include file="header.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<title>Consulta de Vendas</title>

<div class="container">
    <br>
    <h2>Consulta de Vendas</h2> 
    <br>

    <form method="post" action="Vendas" id="pesquisa">
        <input type="hidden" value="pesquisar" name="acao" id="acao">
        
        <div class="row">
            <div class="form-group col-md-2">
                <label for="filtroId">Pesquisa por ID</label>
                <input type="text" class="form-control" id="filtroId" name="filtroId">
            </div>

            <div class="form-group col-md-2">
                <label for="filtroDataIni">De:</label>
                <input type="date" class="form-control"  id="filtroDataIni" name="filtroDataIni">
            </div>

            <div class="form-group col-md-2">
                <label for="filtroDataFim">Até:</label>
                <input type="date" class="form-control" id="filtroDataFim" name="filtroDataFim">
            </div>

            <div class="form-group col-md-2">
                <button id="btnPesquisa" style="margin-top: 30px" class="btn btn-primary">Pesquisar</button>
            </div>
            <div class="form-group col-md-2">
                <a href="Vendas?acao=listar" id="btnPesquisa" style="margin-top: 30px; margin-left: 260px;" class="btn btn-outline-primary">Limpar</a>
            </div>
        </div>  
    </form>



    <table class="table table-hover">
        <thead> 
            <tr>
                <th class="text-center" scope="col">Id</th>
                <th class="text-center" scope="col">Cliente</th>
                <th class="text-center" scope="col">CPF/CNPJ</th>
                <th class="text-center" scope="col">Qtde. de Produtos</th>
                <th class="text-center" scope="col">Valor Total (R$)</th>
                <th class="text-center" scope="col">Forma de Pagamento</th>
                <th class="text-center" scope="col">Data da Venda</th>
                <th class="text-center" scope="col">Vendedor</th>
                <th class="text-center" scope="col">Status</th>
                <th class="text-center" scope="col">#</th>
            </tr>
        </thead>

        <tbody id="tabela" name="tabela">
            <c:forEach var="p" items="${pedidos}">
                <tr class="table-light">
                    <td class=" text-center">${p.idPedido}</td>
                    <td class=" text-center">${p.cliente.nome}</td>
                    <td class=" text-center">${p.cliente.documento.toString()}</td>
                    <td class=" text-center">${p.itensPedido.size()}</td>
                    <td class=" text-center">${p.valorTotal}</td>
                    <td class=" text-center">${p.descFormaPagamento}</td>
                    <td class=" text-center">${p.dataPedido}</td>
                    <td class=" text-center">${p.usuario.nome}</td>
                    <c:if test="${p.status == 1}">
                        <td class="text-center">Concluído</td>
                    </c:if>
                    <c:if test="${p.status == 0}">
                        <td class="text-center">Cancelado</td>
                    </c:if>
                    <td class="text-center">
                        <c:url var="detalhesVenda" value="/Vendas">
                            <c:param name="acao" value="detalhes" />
                            <c:param name="idPedido" value="${p.idPedido}" />
                        </c:url>
                        <a href="${detalhesVenda}"  class="btn btn-outline-warning btn-sm">Detalhes</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<script>
    $(document).ready(function () {
        $('#pesquisa').submit(function (e) {
            if ($('#filtroId').val() === '' && $('#filtroDataIni').val() === '' && $('#filtroDataFim').val() === '') {
                toastr.warning('Preencha algum parametro antes de pesquisar', 'Atenção');
                e.preventDefault(e);
            }
            else {
                this.submit;
            }
        });

        $('#filtroId').blur(function () {
            if ($('#filtroId').val() !== '') {
                $('#filtroDataIni').prop('readonly', true);
                $('#filtroDataFim').prop('readonly', true);
            } else {
                $('#filtroDataIni').prop('readonly', false);
                $('#filtroDataFim').prop('readonly', false);
            }
        });

        $('#filtroDataIni').blur(function () {
            if ($('#filtroDataIni').val() !== '' || $('#filtroDataFim').val() !== '') {
                $('#filtroId').prop('readonly', true);
            } else {
                $('#filtroId').prop('readonly', false);
            }
        });

        $('#filtroDataFim').blur(function () {
            if ($('#filtroDataFim').val() !== '' || $('#filtroDataIni').val() !== '') {
                $('#filtroId').prop('readonly', true);
            } else {
                $('#filtroId').prop('readonly', false);
            }
        });
    });
</script>
