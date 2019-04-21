<%-- 
    Document   : consultaProduto
    Created on : 21/04/2019, 00:26:08
    Author     : Gi
--%>

<%@include file="header.jsp" %>
<div class="col-md-10 container">
    <h2>Consulta de Produto</h2>
    
    <br>
    
    <div class="btn-toolbar justify-content-between" role="toolbar" >

        <div class="input-group-append col-md-6">
            
            <input type="text" class="form-control" placeholder="Pesquisar">
            
            <button class="btn btn-dark" type="button">
                <i class="fa fa-search"></i> Pesquisar
            </button>
            
        </div>
        <div class="input-group">
            <div>
                <a href="cadastroCliente.jsp" class="btn btn-primary">Novo Produto</a>
            </div>

        </div>
    </div>
    <br>

    <table class="table table-hover">
        <thead> 
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Nome</th>
                <th scope="col">Preço Compra</th>
                <th scope="col">Preço Venda</th>
                <th scope="col">Plataforma</th>
                <th scope="col">Categoria</th>
                <th scope="col">Genero</th>
                <th scope="col">Status</th>
                <th scope="col">#</th>
            </tr>
        </thead>
        <tbody>
        <c:forEach var="c" items="${clientes}">
            <tr>
                <td>${c.idCliente}</td>
                <td>${c.nome}</td>
                <td>${c.cpf}</td>
                <td>${c.cnpj}</td>
                <td>${c.dataNasc}</td>
                <td>${c.email}</td>
                <td>${c.telefone}</td>
                <td>${c.celular}</td>
                <td>
                    <a href="#" class="btn btn-primary">Detalhes</a>
                    <a href="#" class="btn btn-warning">Editar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
