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
                <th scope="col">Id</th>
                <th scope="col">Cliente</th>
                <th scope="col">Qtd Produto</th>
                <th scope="col">Valor Total</th>
                <th scope="col">Forma de Pagamento</th>
                <th scope="col">Data e Hora</th>
                <th scope="col">Vendedor</th>
                <th scope="col">Status</th>
                <th scope="col">#</th>
            </tr>
        </thead>

        <tbody id="tabela" name="tabela">

            <tr class="table-light">
                <td>01</td>
                <td>The Last of Us</td>
                <td>Playstation 4</td>
                <td>1</td>
                <td>150,00</td>
                <td>150,00</td>

                <td>
                    <button type="button" class="btn btn-outline-warning btn-sm">Detalhes</button>
                </td>
            </tr>

        </tbody>
    </table>
</div>
