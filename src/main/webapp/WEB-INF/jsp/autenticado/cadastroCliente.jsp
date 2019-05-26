<%-- 
    Document   : CadastroCliente
    Created on : 20/04/2019, 17:36:01
    Author     : Gi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<title>Cadastro de Clientes</title>

<div class="container">
    <br>
    <h2>Novo Cliente</h2>
    <hr/>
    <!-- notificacoes caso houver erros nas valida��es -->
    <div class="form-group">
        <ul class="text-danger">
            <c:forEach var = "n" items = "${notificacoes}">
                <li>${n.valor}</li>
                </c:forEach>
        </ul>

    </div>
    <form action="Clientes" method="post">
        <input type="hidden" value="salvar" id="acao" name="acao">
        <fieldset>
            <div class="row">
                <div class="form-group col-md-5">
                    <label for="name">Nome <h11 class="text-danger">*</h11></label>
                    <input type="text" class="form-control" name="nome" id="nome" placeholder="Digite seu nome completo"
                           maxlength="80" required>   
                </div>
                <div class="form-group col-md-2">
                    <label for="sexo">Tipo Cliente<h11 class="text-danger">*</h11></label>
                    <select class="custom-select" id="tipoCliente" name="tipoCliente" required>
                        <option value="">Selecione </option>
                        <option value="F">F�sico</option>
                        <option value="J">Jur�dico</option> 
                    </select>
                </div>
                <div class="form-group col-md-2">
                    <label for="cpf">CPF<h11 class="text-danger">*</h11> </label>
                    <input type="text" class="form-control cpf" name="cpf" id="cpf" minlength="11" maxlength="11" placeholder="Digite seu CPF" readonly>
                </div>
                <div class="form-group col-md-3">
                    <label for="cpf">CNPJ</label>
                    <input type="text" class="form-control cnpj" name="cnpj" id="cnpj" minlength="14" maxlength="14" placeholder="Digite seu CNPJ" readonly>
                </div>
            </div>

            <div class="row"> 
                <div class="form-group col-md-2">
                    <label for="Celular">Celular<h11 class="text-danger">*</h11></label>
                    <input type="text" class="form-control sp_celphones" name="celular" id="celular" minlength="11" placeholder="Digite seu n�mero" required>
                </div>
                <div class="form-group col-md-2">
                    <label for="Telefone">Telefone</label>
                    <input type="text" class="form-control phone_with_ddd" name="telefone" id="telefone" minlength="11" placeholder="Digite seu n�mero">
                </div>

                <div class="form-group col-md-2">
                    <label for="sexo">Sexo<h11 class="text-danger">*</h11></label>
                    <select class="custom-select" id="sexo" name="sexo" required>
                        <option value="">Selecione </option>
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
                           placeholder="Digite seu e-mail" required="">
                </div>

                <div class="form-group col-md-3">
                    <label for="Data">Data de Nascimento<h11 class="text-danger">*</h11></label>
                    <input class="form-control" type="date" value="dataAtual()" id="dataNasc" name="dataNasc" required>
                </div>

            </div>

            <hr>
            <div class="row">
                <div class="container form-group-inline">
                    <input type="submit" class="btn btn-success" value="Salvar">
                    <button type="reset" class="btn btn-light" >Cancelar</button>
                </div>
            </div>

        </fieldset>
    </form>
</div>




<script>
    $(document).ready(function () {

        $('#tipoCliente').change(function () {
            if ($('#tipoCliente').val() === 'F') {
                $('#cpf').attr('required', true);
                $('#cpf').attr('readonly', false);
                $('#cnpj').attr('required', false);
                $('#cnpj').attr('readonly', true);
                $('#cnpj').val('');
            } else if ($('#tipoCliente').val() === 'J') {
                $('#cpf').attr('required', false);
                $('#cpf').attr('readonly', true);
                $('#cnpj').attr('required', true);
                $('#cnpj').attr('readonly', false);
                $('#cpf').val('');
            } else {
                $('#cnpj').attr('required', false);
                $('#cpf').attr('required', false);
                $('#cnpj').attr('readonly', true);
                $('#cpf').attr('readonly', true);
                $('#cpf').val('');
                $('#cnpj').val('');
            }
        });

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

</script>
