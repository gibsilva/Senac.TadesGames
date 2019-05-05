<%-- 
    Document   : detalhesVenda
    Created on : 04/05/2019, 19:13:23
    Author     : Gi
--%>
<%@include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Detalhes da Venda</title>

<div class="container">
    <br>
    <h2>Detalhes da Venda</h2>
    <hr>
    <br>

    <div class="row">
        <div class="form-group col-md-2">
            <label for="name">Id Venda</label>
            <input type="text" class="form-control" name="idVenda" id="IdVenda" readonly>   
        </div>
        
        <div class="form-group col-md-5">
            <label for="name">Cliente</label>
            <input type="text" class="form-control" name="nome" id="nome" readonly>   
        </div>
        
        <div class="form-group col-md-3">
            <label for="cpf">CPF/CNPJ</label>
            <input type="text" class="form-control " name="cpf" id="cpf" readonly>
        </div>
    </div>

    <div class="row">
        <div class="form-group col-md-2">
            <label for="FormaPagamento">Forma de Pagamento</label>
            <input type="text" class="form-control " name="formaPagemento" id="formaPagemento" readonly>
        </div>
        
        <div class="form-group col-md-2">
            <label for="Data">Data</label>
            <input type="date" class="form-control"  id="Data" name="Data" readonly>
        </div>
        
        <div class="form-group col-md-3">
            <label  for="inputTotal">Total:</label>
            <div class="input-group">
                <div class="input-group-prepend">
                    <span class="input-group-text">R$</span>
                </div>
                <input type="text" class="form-control" id="inputValorVenda" placeholder="00,00" readonly>
            </div>
        </div>

        <div class="form-group col-md-2">
            <label for="name">Status</label>
            <input type="text" class="form-control" name="nome" id="status" readonly>   
        </div>
    </div>

    <div class="row">
        

    </div>
    <br>
    <br>
    <table class="table table-hover">
        <thead> 
            <tr>
                <th class="text-center" scope="col">Id</th>
                <th class="text-center" scope="col">Produto</th>
                <th class="text-center" scope="col">Categoria</th>
                <th class="text-center" scope="col">Plataforma</th>
                <th class="text-center" scope="col">Genero</th>
                <th class="text-center" scope="col">Quantidade</th>
                <th class="text-center" scope="col">Valor Unit</th>
            </tr>
        </thead>
        <tbody id="tabela" name="tabela">
            <tr>
                <td class=" text-center">001</td>
                <td class=" text-center">Playstation 4</td>
                <td class=" text-center">Console</td>
                <td class=" text-center">-</td>
                <td class="text-center">Jogos</td>
                <td class=" text-center">1</td>
                <td class=" text-center">1.500</td>

        </tbody>
    </table> 
    <hr>
    <a href="consultaVendas.jsp" class="btn btn-secondary">Voltar</a>
</div>
