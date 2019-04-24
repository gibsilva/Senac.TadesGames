<%-- 
    Document   : consultaUsuario
    Created on : 24/04/2019, 16:15:48
    Author     : adrianne
--%>

<%@include file="header.jsp" %>
<div class="col-md-10 container">
    <h2>Consulta de Usuarios</h2>

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
                <a href="cadastroUsuario.jsp" class="btn btn-outline-primary">Novo Usuario</a>
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
                <th scope="col">Sexo</th>
                <th scope="col">Filial</th>
                <th scope="col">Setor</th>
                <th scope="col">Cargo</th>
                <th scope="col">Email</th>
                <th scope="col">Login</th>
                <th scope="col">Status</th>
                <th scope="col">#</th>
            </tr>
        </thead>
        <tbody id="tabela" name="tabela">
            <c:forEach var="c" items="${usuarios}">
                <tr>
                    <td>${u.idUsuario}</td>
                    <td>${u.nome}</td>
                    <td>${u.cpf}</td>
                    <td>${u.sexo}</td>
                    <td>${u.filial}</td>
                    <td>${u.setor}</td>
                    <td>${u.cargo}</td>
                    <td>${u.email}</td>
                    <td>${u.login}</td>
                    <td>${c.status}</td>
                   

                    <td>
                       
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
