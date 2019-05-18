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
    <!-- notificacoes caso houver erros nas validações -->
    <div class="form-group">
        <ul class="text-danger">
            <c:forEach var = "n" items = "${notificacoes}">
                <li>${n.valor}</li>
                </c:forEach>
        </ul>

    </div>
    <form action="Filiais" method="post">
        <input type="hidden" value="${filial.idFilial}" id="idFilial" name="idFilial">
        <input type="hidden" value="alterar" id="acao" name="acao">
        
        <div class="row">
            <div class="form-group col-md-5">
                <label for="nome">Nome da Filial<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" id="nomeFilial" name="nome" 
                       placeholder="Digite o nome da Filial" required value="${filial.nome}">
            </div>

            <div class="form-group col-md-3">
                <label for="cnpj">CNPJ <h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control cnpj" id="CNPJFilial" name="cnpj" 
                       placeholder="Digite o CNPJ da Filial" readonly value="${filial.cnpj}"> 
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-3">
                <label for="cep">CEP<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control cep" id="cep" name="cep" 
                       placeholder="Digite o cep" required value="${filial.cep}">
            </div>

            <div class="form-group col-md-4">
                <label for="longradouro">Logradouro<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" id="logradouro" name="logradouro" 
                       placeholder="Digite o logradouro" required value="${filial.logradouro}" readonly>
            </div>

            <div class="form-group col-md-2">
                <label for="numero">Numero<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" id="numero" name="numero" 
                       placeholder="Digite o numero" required value="${filial.numero}">
            </div>

            <div class="form-group col-md-3">
                <label for="complemento">Complemento</label>
                <input type="text" class="form-control" id="complemento" name="complemento" 
                       placeholder="Digite o complemento" value="${filial.complemento}">
            </div>

        </div>

        <div class="row">
            <div class="form-group col-md-4">
                <label for="bairro">Bairro<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" id="bairro" name="bairro" 
                       placeholder="Digite o bairro" required value="${filial.bairro}" readonly>
            </div>

            <div class="form-group col-md-4">
                <label for="cidade">Cidade<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" id="cidade" name="cidade" 
                       placeholder="Digite a cidade" required value="${filial.cidade}" readonly>
            </div>

            <div class="form-group col-md-2">
                <label for="estado">Estado<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" id="uf" name="uf" 
                       placeholder="Digite estado" required value="${filial.estado}" readonly>
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
            <div class="container form-group-inline">
                <input type="submit" class="btn btn-success" value="Salvar">
                <a href="Filiais" class="btn btn-light" >Cancelar</a>
            </div>
        </div>

    </form>
</div>

<script>
    $(document).ready(function () {
        document.getElementById('ativo').value = '${filial.ativo}';
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
    
        //estamos usando uma api gratuita para a consulta de CEPs
    $("#cep").focusout(function(){
        if($("#cep").val() === '00000-000' || $("#cep").val() === '11111-111'){
            cepInvalido();
        } else{
            		//Início do Comando AJAX
		$.ajax({
			//O campo URL diz o caminho de onde virá os dados
			//É importante concatenar o valor digitado no CEP
			url: 'https://viacep.com.br/ws/'+$(this).val()+'/json/unicode/',
			//Aqui você deve preencher o tipo de dados que será lido,
			//no caso, estamos lendo JSON.
			dataType: 'json',
			//SUCESS é referente a função que será executada caso
			//ele consiga ler a fonte de dados com sucesso.
			//O parâmetro dentro da função se refere ao nome da variável
			//que você vai dar para ler esse objeto.
			success: function(resposta){
				//Agora basta definir os valores que você deseja preencher
				//automaticamente nos campos acima.
				$("#logradouro").val(resposta.logradouro);
				$("#complemento").val(resposta.complemento);
				$("#bairro").val(resposta.bairro);
				$("#cidade").val(resposta.localidade);
				$("#uf").val(resposta.uf);
				//Vamos incluir para que o Número seja focado automaticamente
				//melhorando a experiência do usuário
				$("#numero").focus();
			},
                        error: function(resposta){
                            cepInvalido();
                        }
		});
            }
	});
        
        function cepInvalido(){
            toastr.warning('Cep inválido', 'Aviso');
            $('#cep').val('');
            $("#logradouro").val('');
            $("#complemento").val('');
            $("#bairro").val('');
            $("#cidade").val('');
            $("#uf").val('');
            $("#cep").focus();
        }

</script>