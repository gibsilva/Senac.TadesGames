<%-- 
    Document   : alterarUsuario
    Created on : 24/04/2019, 16:21:52
    Author     : adrianne
--%>
<%@include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>Alteração de Usuario</title>


<div class="container">
    <br>
    <h2>Alteração de Usuario</h2>
    <hr>

    <div class="form-group">
        <ul class="text-danger">
            <c:forEach var = "n" items = "${notificacoes}">
                <li>${n.valor}</li>
                </c:forEach>
        </ul>
    </div>

    <form action="Usuarios" method="post">
        <input type="hidden" value="${usuario.idUsuario}" id="idUsuario" name="idUsuario">
        <input type="hidden" value="alterar" id="acao" name="acao">
        <div class="row">

            <div class="form-group col-md-5">
                <label for="inputNomeFunc">Nome<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" id="nome" name="nome" maxlength="80" placeholder="Nome Completo" 
                       required value="${usuario.nome}">
            </div>

            <div class="form-group col-md-3">
                <label for="inputCPFFunc">CPF<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" name="cpf" id="cpf" minlength="11" 
                       maxlength="11" readonly value="${usuario.cpf}">
            </div>


        </div>

        <div class="row">
            <div class="form-group col-md-5">
                <label for="Email">Email<h11 class="text-danger">*</h11></label>
                <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp"
                       placeholder="Digite seu Email" required value="${usuario.email}">
            </div>

            <div class="form-group col-md-2">
                <label for="inputSexo">Sexo<h11 class="text-danger">*</h11></label>
                <select id="sexo" name="sexo" class="custom-select" required>
                    <option selected value="">Selecione</option>
                    <option value="M">Masculino</option>
                    <option value="F">Feminino</option>
                    <option value="O">Outros</option>

                </select>
            </div>

        </div>

        <div class="row">
            <div class="form-group col-md-2">
                <label for="inputFilial">Filial<h11 class="text-danger">*</h11></label>
                <select id="filial" name="filial" class="custom-select" required>
                    <option value="">Selecione</option>
                    <c:forEach var="f" items="${filiais}">
                        <option value="${f.idFilial}">${f.nome}</option>
                    </c:forEach>
                </select>
            </div>


            <div class="form-group col-md-2">
                <label for="inputSetor">Setor<h11 class="text-danger">*</h11></label>
                <select id="setor" name="setor" class="custom-select" required>
                    <option value="">Selecione</option>
                    <option value="Diretoria">Diretoria</option>
                    <option value="Produtos/Serviços/Marketing">Produtos/Serviços/Marketing</option>
                    <option value="Vendas">Vendas</option>
                    <option value="T.I">T.I</option>
                </select>
            </div>

            <div class="form-group col-md-2">
                <label for="inputCargo">Cargo<h11 class="text-danger">*</h11></label>
                <select id="cargo" name="cargo" class="custom-select" required>
                    <option value="">Selecione</option>
                </select>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-2">
                <label for="inputSexo">Status</label>
                <select id="ativo" name="ativo" class="custom-select" required>
                    <option value="true">Ativo</option>
                    <option value="false">Inativo</option>                           
                </select>
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="container form-group-inline">
                <input type="submit" class="btn btn-success" value="Salvar">
                <a href="Usuarios" class="btn btn-light" >Cancelar</a>
            </div>
        </div>
    </form>     


</div>

<script>
    var lista = '';
    $(document).ready(function () {

        document.getElementById('sexo').value = '${usuario.sexo}';
        document.getElementById('filial').value = '${usuario.idFilial}';
        document.getElementById('setor').value = '${usuario.setor}';
        document.getElementById("cargo").options.length = 0;
        var option = new Option('${usuario.cargo}', '${usuario.cargo}'); $('#cargo').append($(option));
        document.getElementById('ativo').value = '${usuario.ativo}';

        $('#setor').blur(function () {
            if ($('#setor').val() !== '') {
                obterCargos($('#setor').val());
            } else {
                document.getElementById("cargo").options.length = 0;
                $('#cargo').append($('<option>', {
                    value: '',
                    text: 'Selecione'
                }));
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

    function obterCargos(setor) {
        $.ajax({
            url: 'Usuarios?acao=cargosPorSetor',
            type: "GET",
            contentType: 'application/json',
            data: {'setor': setor},
            success: function (data, textStatus, jqXHR) {
                lista = $.parseJSON(data);
                console.log(lista);
                carregarCargos(lista);
            },
            error: function () {
                toastr.error('Ocorreu um erro ao buscar os cargos', 'Erro');
            }
        });
    }

    function carregarCargos(lista) {
        document.getElementById("cargo").options.length = 0;
        $('#cargo').append($('<option>', {
            value: '',
            text: 'Selecione'
        }));

        for (var i = 0; i < lista.length; i++) {
            $('#cargo').append($('<option>', {
                value: lista[i].toString(),
                text: lista[i].toString()
            }));
        }
    }

</script>
