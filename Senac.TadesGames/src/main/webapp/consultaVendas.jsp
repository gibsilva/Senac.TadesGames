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

    <form method="post">
        <div class="row">
            <div class="form-group col-md-2">
                <label for="filtroId">Pesquisa por ID</label>
                <input type="text" class="form-control" id="filtroDataFim" name="filtroId">
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
                <button style="margin-top: 30px" class="btn btn-primary">Pesquisar</button>
            </div>
        </div>  
    </form>

    <br>
    <br>

    <table class="table table-hover">
        <thead> 
            <tr>
                <th class="text-center" scope="col">Id</th>
                <th class="text-center" scope="col">Cliente</th>
                <th class="text-center" scope="col">CPF/CNPJ</th>
                <th class="text-center" scope="col">Qtd de Produtos</th>
                <th class="text-center" scope="col">Valor Total</th>
                <th class="text-center" scope="col">Forma de Pagamento</th>
                <th class="text-center" scope="col">Data e Hora</th>
                <th class="text-center" scope="col">Vendedor</th>
                <th class="text-center" scope="col">Status</th>
                <th class="text-center" scope="col">#</th>
            </tr>
        </thead>

        <tbody id="tabela" name="tabela">
            <tr class="table-light">
                <td class=" text-center">001</td>
                <td class=" text-center">Fulano</td>
                <td class=" text-center">617647627131</td>
                <td class=" text-center">4</td>
                <td class=" text-center">150,00</td>
                <td class=" text-center">Credito</td>
                <td class=" text-center">25/01/2019 16h30</td>
                <td class=" text-center">Cicrano</td>
                <td class=" text-center">concluida</td>

                <td class="text-center">
                    <a href="detalhesVenda.jsp"  class="btn btn-outline-warning btn-sm">Detalhes</a>
                </td>
            </tr>
        </tbody>
    </table>
</div>
