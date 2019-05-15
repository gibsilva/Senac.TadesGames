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
    <h2> Relatorio de Vendas</h2>
    <hr>
    <br>
    <div class="row">
        <div class="form-group-inline col-md-2">
            <label>De:</label>
            <input type="date" class="form-control"  id="filtroDataIni" name="filtroDataIni">
        </div>

        <div class="form-group col-md-2">
            <label>Até:</label>
            <input type="date" class="form-control" id="filtroDataFim" name="filtroDataFim">
        </div>

        <div class="form-group col-md-2">
            <button id="btnPesquisa" style="margin-top: 30px" class="btn btn-primary">Gerar</button>
        </div>

    </div>

    <br>

    <table class="table table-hover">
        <thead>
            <tr>
                <th scope="col">ID Venda</th>
                <th scope="col">Nome do Cliente</th>
                <th scope="col">CPF/CNPJ Cliente</th>
                <th scope="col">Data da Venda</th>
                <th scope="col">Filial</th>
                <th scope="col">Valor Total</th>
            </tr>
        </thead>

        <tbody id="tabela" name="tabela">

            <tr class="table-dark">
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td></td>      
            </tr>

        </tbody>
    </table>

    <hr>
</div>