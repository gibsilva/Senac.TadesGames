<%-- 
    Document   : consultaCliente
    Created on : 20/04/2019, 23:54:54
    Author     : Gi
--%>
<%@include file="header.jsp" %>
<div class="col-md-10">
    <h2>Cadastro de Clientes</h2>
    <div>
        <a href="cadastroCliente.jsp" class="btn btn-primary">Novo Cliente</a>
    </div>
    <br>
    <div class="input-group col-md-6">
        <input type="text" class="form-control" placeholder="Pesquisar">
        <div class="input-group-append">
            <button class="btn btn-dark" type="button">
                <i class="fa fa-search"></i> Pesquisar
            </button>
        </div>
    </div>
    <br>

    <table class="table table-hover">
        <thead> 
            <tr>
                <th scope="col">Id</th>
                <th scope="col">Nome</th>
                <th scope="col">CPF</th>
                <th scope="col">CNPJ</th>
                <th scope="col">Data Nasc.</th>
                <th scope="col">E-mail</th>
                <th scope="col">Telefone</th>
                <th scope="col">Celular</th>
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
                        <a href="alterarCliente.jsp" class="btn btn-warning">Editar</a>
                        <a href="#" class="btn btn-danger">Inativar</a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
