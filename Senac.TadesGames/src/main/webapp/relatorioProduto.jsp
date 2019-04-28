<%-- 
    Document   : RelatorioProduto
    Created on : 28/04/2019, 02:33:26
    Author     : Gi
--%>
<%@include file="header.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<title>Relatorio de Produto</title>
    <div class="container">
    <h2> Relatorio de Produtos</h2>
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
        
    </div>
   
    <br>

    <table class="table table-hover">
        <thead>
            <tr>
                <th scope="col">ID</th>
                <th scope="col">Nome</th>
                <th scope="col">Qtd Vendido</th>
                <th scope="col">Total Vendido</th>
                <th scope="col">Categoria</th>
                <th scope="col">Ultima Venda</th>
                <th scope="col">Status</th>
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
                <td></td>      
            </tr>
           
        </tbody>
    </table>
    
    <hr>
         <div class="float-right">
             <div>
                <button class="btn btn-primary">Exportar</button>
            </div>

        </div>

</div>