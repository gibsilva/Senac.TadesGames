<%-- 
    Document   : consultaFilial
    Created on : 04/05/2019, 16:32:56
    Author     : Gi
--%>
<%@include file="header.jsp" %>
<%@taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<title>Consulta de Filiais</title>

<div class="container">
    <br>
    <h2>Consulta de Filiais</h2>
    <br>
    <div class="btn-toolbar justify-content-between" role="toolbar" aria-label="Toolbar with button groups">

        <div class="input-group-append col-md-6">
            <input type="text" class="form-control " placeholder="Pesquisar" id="filtro" name="filtro">
        </div>
        
        <div class="input-group">
            <div>
                <a href="cadastroFilial.jsp" class="btn btn-outline-primary">Nova Filial</a>
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
                <th class="text-center" scope="col">CNPJ</th>
                <th class="text-center" scope="col">CEP</th>
                <th class="text-center" scope="col">Logradouro</th>
                <th class="text-center" scope="col">Nº</th>
                <th class="text-center" scope="col">Complemento</th>
                <th class="text-center" scope="col">Bairro</th>
                <th class="text-center" scope="col">Cidade</th>
                <th class="text-center" scope="col">Estado</th>
                <th class="text-center" scope="col">#</th>
            </tr>
        </thead>
        <tbody id="tabela" name="tabela">
            <c:forEach var="f" items="${filiais}">
                <tr>
                    <td class="text-center">${f.idFilial}</td>
                    <td class="text-center">${f.nome}</td>
                    <td class="text-center">${f.cnpj}</td>
                    <td class="text-center">${f.cep}</td>
                    <td class="text-center">${f.logradouro}</td>
                    <td class="text-center">${f.numero}</td>
                    <td class="text-center">${f.complemento}</td>
                    <td class="text-center">${f.bairro}</td>
                    <td class="text-center">${f.cidade}</td>
                    <td class="text-center">${f.estado}</td>                 
                    <td class="text-center">
                        <c:url var="alterarFilial" value="/Filiais">
                            <c:param name="acao" value="alterar" />
                            <c:param name="idFilial" value="${f.idFilial}" />
                        </c:url>
                        <a href="${alterarFilial}" class="btn btn-outline-warning">Editar</a>
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
