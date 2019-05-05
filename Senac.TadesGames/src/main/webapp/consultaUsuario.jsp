<%-- 
    Document   : consultaUsuario
    Created on : 24/04/2019, 16:15:48
    Author     : adrianne
--%>

<%@include file="header.jsp" %>
<%@ taglib prefix = "u" uri = "http://java.sun.com/jsp/jstl/core" %>
<title>Consulta de Usuarios</title>

<div class="col-md-10 container">
    <br>
    <h2>Consulta de Usuarios</h2>
    <br>
    <div class="btn-toolbar justify-content-between" role="toolbar" aria-label="Toolbar with button groups">

        <div class="input-group-append col-md-6">
            <input type="text" class="form-control " placeholder="Pesquisar" id="filtro" name="filtro">
            
            <button class="btn btn-dark" type="button">
                <i class="fa fa-search"></i> Pesquisar
            </button>
           
        </div>
        <div class="input-group">
            <div>
                <a href="cadastroUsuario.jsp" class="btn btn-outline-primary">Novo Usuario</a>
            </div>

        </div>
    </div>
    <br>
    <br>
    <table class="table table-hover">
        <thead> 
            <tr>
                <th class="text-center" scope="col">Id</th>
                <th class="text-center" scope="col">Nome</th>
                <th class="text-center" scope="col">CPF</th>
                <th class="text-center" scope="col">Sexo</th>
                <th class="text-center" scope="col">Filial</th>
                <th class="text-center" scope="col">Setor</th>
                <th class="text-center" scope="col">Cargo</th>
                <th class="text-center" scope="col">Email</th>
                <th class="text-center" scope="col">Login</th>
                <th class="text-center" scope="col">Status</th>
                <th class="text-center" scope="col">#</th>
            </tr>
        </thead>
        <tbody id="tabela" name="tabela">
            <c:forEach var="u" items="${usuarios}">
                <tr>
                    <td class="text-center">${u.idUsuario}</td>
                    <td class="text-center">${u.nome}</td>
                    <td class="text-center">${u.cpf}</td>
                    <td class="text-center">${u.sexo}</td>
                    <td class="text-center">${u.filial}</td>
                    <td class="text-center">${u.setor}</td>
                    <td class="text-center">${u.cargo}</td>
                    <td class="text-center">${u.email}</td>
                    <td class="text-center">${u.login}</td>
                    <td class="text-center">${u.ativo}</td>
                                    
                    <td class="text-center">
                        <c:url var="alterarUsuario" value="/Usuarios">
                            <c:param name="acao" value="alterar" />
                            <c:param name="idUsuario" value="${u.idUsuario}" />
                        </c:url>
                        <a href="alterarUsuario.jsp" class="btn btn-outline-warning">Editar</a>
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
