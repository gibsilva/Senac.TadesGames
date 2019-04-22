<%-- 
    Document   : consultaCliente
    Created on : 20/04/2019, 23:54:54
    Author     : Gi
--%>
<%@include file="header.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<div class="col-md-10 container">
    <h2>Consulta de Clientes</h2>

    <br>
    <div class="btn-toolbar justify-content-between" role="toolbar" aria-label="Toolbar with button groups">

        <div class="input-group-append col-md-6">
            <input type="text" class="form-control " placeholder="Pesquisar" id="filtro" name="filtro">
            <!--
            <button class="btn btn-dark" type="button">
                <i class="fa fa-search"></i> Pesquisar
            </button>
            -->
        </div>
        <div class="input-group">
            <div>
                <a href="cadastroCliente.jsp" class="btn btn-outline-primary">Novo Cliente</a>
            </div>

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
                <th scope="col">Sexo</th>
                <th scope="col">E-mail</th>
                <th scope="col">Telefone</th>
                <th scope="col">Celular</th>
                <th scope="col">#</th>

            </tr>
        </thead>
        <tbody id="tabela" name="tabela">
            <c:forEach var="c" items="${clientes}">
                <tr>
                    <td>${c.idCliente}</td>
                    <td>${c.nome}</td>
                    <td>${c.cpf}</td>
                    <td>${c.cnpj}</td>
                    <td>${c.dataNasc}</td>
                    <td>${c.sexo}</td>
                    <td>${c.email}</td>
                    <td>${c.telefone}</td>
                    <td>${c.celular}</td>

                    <td>
                        <c:url var="alterarCliente" value="/Clientes">
                            <c:param name="acao" value="alterar" />
                            <c:param name="idCliente" value="${c.idCliente}" />
                        </c:url>
                        <a href="${alterarCliente}" class="btn btn-outline-warning">Editar</a>

                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

</div>

<script>
    $('#filtro').on('keyup', function () {
        var value = $(this).val();
        var patt = new RegExp(value, "i");

        $('#tabela').find('tr').each(function () {
            if (!($(this).find('td').text().search(patt) >= 0)) {
                $(this).not('.myHead').hide();
            }
            if (($(this).find('td').text().search(patt) >= 0)) {
                $(this).show();
            }

        });

    });
</script>
