<%-- 
    Document   : alterarCliente
    Created on : 20/04/2019, 23:57:57
    Author     : Gi
--%>

<%@include file="header.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<title>Alteração de Cliente</title>

<div class="container">
    <br>
    <h2>Alteração de Cliente</h2>
    <hr/>
    <!-- notificacoes caso houver erros nas validações -->
    <div class="form-group">
        <ul class="text-danger">
            <c:forEach var = "n" items = "${notificacoes}">
                <li>${n.valor}</li>
                </c:forEach>
        </ul>

    </div>
    <form action="Clientes" method="post">
        <input type="hidden" value="${cliente.idCliente}" id="idCliente" name="idCliente">
        <input type="hidden" value="alterar" id="acao" name="acao">

        <fieldset>
            <div class="row">
                <div class="form-group col-md-5">
                    <label for="name">Nome <h11 class="text-danger">*</h11></label>
                    <input type="text" class="form-control" name="nome" id="nome" placeholder="Digite seu Nome" maxlength="80" 
                           required value="${cliente.nome}">
                </div>
                <div class="form-group col-md-2">
                    <label for="cpf">CPF<h11 class="text-danger">*</h11> </label>
                    <input type="text" class="form-control" name="cpf" id="cpf" maxlength="11" placeholder="" 
                           readonly value="${cliente.documento.cpf}">
                </div>
                <div class="form-group col-md-3">
                    <label for="cnpj">CNPJ </label>
                    <input type="text" class="form-control" name="cnpj" id="cnpj" maxlength="14" placeholder="" 
                           readonly value="${cliente.documento.cnpj}"/>
                </div>
            </div>

            <div class="row"> 
                <div class="form-group col-md-2">
                    <label for="Celular">Celular<h11 class="text-danger">*</h11></label>
                    <input type="text" class="form-control sp_celphones" name="celular" id="celular" placeholder="Digite seu numero" 
                           required value="${cliente.celular}"/>
                </div>

                <div class="form-group col-md-2">
                    <label for="Celular">Telefone</label>
                    <input type="text" class="form-control phone_with_ddd" name="telefone" id="telefone" 
                           placeholder="Digite seu numero" value="${cliente.telefone}"/>
                </div>

                <div class="form-group col-md-2">
                    <label for="sexo">Sexo<h11 class="text-danger">*</h11></label>
                    <select class="custom-select" id="sexo" name="sexo" required>
                        <option value="M">Masculino</option>
                        <option value="F">Feminino</option>
                        <option value="O">Outros</option>
                    </select>
                </div>
            </div>

            <div class="row">
                <div class="form-group col-md-5">
                    <label for="Email">Email<h11 class="text-danger">*</h11></label>
                    <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp"
                           placeholder="Digite seu Email" required="" value="${cliente.email}">
                </div>

                <div class="form-group col-md-2">
                    <label for="Data">Data de Nascimento<h11 class="text-danger">*</h11></label>
                    <input type="date"  class="form-control" id="dataNasc" name="dataNasc" placeholder="DD/MM/AAAA" 
                           maxlength="8" required value=${cliente.dataNasc}>
                </div>

                <div class="form-group col-md-2">
                    <label for="inputAtivo">Status</label>
                    <select id="ativo" name="ativo" class="custom-select" required >
                        <option value="true">Ativo</option>
                        <option value="false">Inativo</option>                           
                    </select>
                </div>
            </div>

            <hr>
            <div class="row">
                <div class="form-group-inline">
                    <input type="submit" class="btn btn-success" value="Salvar">
                    <a href="Clientes" class="btn btn-light">Cancelar</a>
                </div>
            </div>

        </fieldset>
    </form>
</div>




<script>

    $(document).ready(function () {
        document.getElementById('sexo').value = '${cliente.sexo}';
        document.getElementById('ativo').value = '${cliente.ativo}';
        //preencheData();

        $('.date').mask('00/00/0000');
        $('.time').mask('00:00:00');
        $('.date_time').mask('00/00/0000 00:00:00');
        $('.cep').mask('00000-000');
        $('.phone').mask('0000-0000');
        $('.phone_with_ddd').mask('(00) 0000-0000');
        $('.phone_us').mask('(000) 000-0000');
        $('.sp_celphones').mask('(00) 00000-0000');
        $('.mixed').mask('AAA 000-S0S');
        $('.cpf').mask('000.000.000-00', {reverse: true});
        $('.cnpj').mask('00.000.000/0000-00', {reverse: true});
        $('.money').mask('000.000.000.000.000,00', {reverse: true});
        $('.money2').mask("#.##0,00", {reverse: true});
        $('.ip_address').mask('0ZZ.0ZZ.0ZZ.0ZZ', {
            translation: {
                'Z': {
                    pattern: /[0-9]/, optional: true
                }
            }
        });
        $('.ip_address').mask('099.099.099.099');
        $('.percent').mask('##0,00%', {reverse: true});
        $('.clear-if-not-match').mask("00/00/0000", {clearIfNotMatch: true});
        $('.placeholder').mask("00/00/0000", {placeholder: "__/__/____"});
        $('.fallback').mask("00r00r0000", {
            translation: {
                'r': {
                    pattern: /[\/]/,
                    fallback: '/'
                },
                placeholder: "__/__/____"
            }
        });
        $('.selectonfocus').mask("00/00/0000", {selectOnFocus: true});

    });

    function preencheData() {
        var date = ${cliente.dataNasc};
        var currentDate = date.toISOString().slice(0, 10);

        document.getElementById('dataNasc').value = currentDate;
    }

</script>
