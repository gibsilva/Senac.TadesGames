<%-- 
    Document   : CadastroCliente
    Created on : 20/04/2019, 17:36:01
    Author     : Gi
--%>
<%@include file="header.jsp" %>
<div class="container">
    <br>
    <h2>Novo Cliente</h2>
    <hr/>
    <form>
        <fieldset>
            <div class="row">
                <div class="form-group col-md-5">
                    <label for="name">Nome <h11>*</h11></label>
                    <input type="text" class="form-control" name="nome" id="nome" placeholder="Digite seu Nome Completo"
                           maxlength="80" required>   
                </div>
                <div class="form-group col-md-2">
                    <label for="cpf">CPF<h11>*</h11> </label>
                    <input type="text" class="form-control " name="cpf" id="cpf" minlength="11" maxlength="11" placeholder="Digite seu CPF" required>
                </div>
                <div class="form-group col-md-3">
                    <label for="cpf">CNPJ</label>
                    <input type="text" class="form-control cnpj" name="cnpj" id="cnpj" minlength="14" maxlength="14" placeholder="Digite seu CNPJ">
                </div>
            </div>

            <div class="row"> 
                <div class="form-group col-md-2"
                     <label for="Celular">Celular<h11>*</h11></label>
                    <input type="text" class="form-control sp_celphones" name="celular" id="celular" minlength="11" placeholder="Digite seu numero" required>
                </div>
                <div class="form-group col-md-2"
                     <label for="Telefone">Telefone</label>
                    <input type="text" class="form-control phone_with_ddd" name="telefone" id="telefone" minlength="11" placeholder="Digite seu numero">
                </div>

                <div class="form-group col-md-2"
                     <label for="sexo">Sexo<h11>*</h11></label>
                    <select class="custom-select" id="sexo" name="sexo" required>
                        <option value="">Selecione</option>
                        <option value="M">Masculino</option>
                        <option value="F">Feminino</option>
                        <option value="O">Outros</option>
                    </select>
                </div>
                
            </div>

            <div class="row">
                <div class="form-group col-md-5">
                    <label for="Email">Email<h11>*</h11></label>
                    <input type="email" class="form-control" id="email" aria-describedby="emailHelp"
                           placeholder="Digite seu Email" required="">
                </div>

               <div class="form-group col-md-3">
                    <label for="Data">Data de Nascimento</label>
                    <input class="form-control" type="date" value="dataAtual()" id="dataNasc" name="dataNasc">
                </div>

            </div>
            
            <hr>
            <div class="row">
                <div class="container form-group-inline">
                    <input type="submit" class="btn btn-success" value="Salvar">
                    <input type="reset" class="btn btn-light" value="Cancelar">
                </div>
            </div>

        </fieldset>
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
