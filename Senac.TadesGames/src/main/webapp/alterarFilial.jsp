<%-- 
    Document   : cadastroFilial
    Created on : 04/05/2019, 14:11:19
    Author     : Gi
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<title>Alteração de Filial</title>

<div class="container">
    <br>
    <h2>Alteração de Filial</h2>
    <hr>

    <form action="Filiais" method="post">
        <input type="hidden" value="${filial.idFilial}" id="idFilial" name="idFilial">
        <input type="hidden" value="alterar" id="acao" name="acao">
        
        <div class="row">
            <div class="form-group col-md-5">
                <label for="nome">Nome da Filial<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" id="nomeFilial" name="nome" placeholder="Digite o nome da Filial" required>
            </div>

            <div class="form-group col-md-3">
                <label for="cnpj">CNPJ <h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control cnpj" id="CNPJFilial" name="cnpj" placeholder="Digite o CNPJ da Filial" readonly> 
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-3">
                <label for="cep">CEP<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control cep" id="cep" name="cep" placeholder="Digite o cep" required>
            </div>

            <div class="form-group col-md-4">
                <label for="longradouro">Longradouro<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" id="longradouro" name="longradouro" placeholder="Digite o longradouro" required>
            </div>

            <div class="form-group col-md-2">
                <label for="numero">Numero<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" id="numero" name="numero" placeholder="Digite o numero" required>
            </div>

            <div class="form-group col-md-3">
                <label for="complemento">Complemento</label>
                <input type="text" class="form-control" id="complemento" name="complemento" placeholder="Digite o complemento" required>
            </div>

        </div>

        <div class="row">
            <div class="form-group col-md-4">
                <label for="bairro">Bairro<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" id="bairro" name="bairro" placeholder="Digite o bairro" required>
            </div>

            <div class="form-group col-md-4">
                <label for="cidade">Cidade<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" id="cidade" name="cidade" placeholder="Digite a cidade" required>
            </div>

            <div class="form-group col-md-2">
                <label for="estado">Estado<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" id="estado" name="estado" placeholder="Digite estado" required>
            </div>

        </div>

        <hr>
        <div class="row">
            <div class="container form-group-inline">
                <input type="submit" class="btn btn-success" value="Salvar">
                <a href="home.jsp" class="btn btn-light" >Cancelar</a>
            </div>
        </div>

    </form>
</div>

<script>
    $(document).ready(function () {

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