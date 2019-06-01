<%-- 
    Document   : cadastroProduto
    Created on : 21/04/2019, 04:45:49
    Author     : Marcel
--%>
<%@include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>Cadastro de Produtos</title>

<div class="container">
    <br>
    <h2>Novo Produto</h2>
    <hr>
    <form action="Produtos" method="post">
        <input type="hidden" value="salvar" id="acao" name="acao">

        <div class="row">
            <div class="form-group col-md-4">
                <label for="inputTitulo">Nome/Título<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" maxlength="80"  id="nome" name="nome" 
                       placeholder="Nome/Título do Produto" required>
            </div>

            <div class="form-group col-md-3">
                <label for="inputPlataforma">Plataforma<h11 class="text-danger">*</h11></label>
                <select id="plataforma" name="plataforma" class="custom-select" required>
                    <option value="">Selecione </option>
                    <c:forEach var="p" items="${plataformas}">
                        <option value="${p.idPlataforma}">${p.nome}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group col-md-3">
                <label for="inputCategoria">Categoria<h11 class="text-danger">*</h11></label>
                <select id="categoria" name="categoria" class="custom-select" required>
                    <option value="">Selecione </option>
                    <c:forEach var="c" items="${categorias}">
                        <option value="${c.idCategoria}">${c.nome}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group col-md-3">
                <label for="genero">Gênero<h11 class="text-danger">*</h11></label>
                <select id="genero" name="genero" class="custom-select" required>
                    <option value="">Selecione </option>
                    <c:forEach var="g" items="${generos}">
                        <option value="${g.idGenero}">${g.nome}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group col-md-3">
                <label for="inputValorCompra">Valor de Compra<h11 class="text-danger">*</h11></label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">R$</span>
                    </div>
                    <input type="text" class="form-control money" id="valorCompra" name="valorCompra" placeholder="00,00" required>
                </div>
            </div>

            <div class="form-group col-md-3">
                <label for="inputValorVenda">Valor de Venda<h11 class="text-danger">*</h11></label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">R$</span>
                    </div>
                    <input type="text" class="form-control money" id="valorVenda" name="valorVenda" placeholder="00,00" required>
                </div>
            </div>

            <div class="form-group col-md-2">
                <label for="inputQuantidade">Quantidade<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control number" id="quantidade" name="quantidade" placeholder="00" required>
            </div>

            <div class="form-group col-md-2">
                <label for="inputSexo">Status</label>
                <select id="ativo" name="ativo" class="custom-select" required>
                    <option selected value="true">Ativo</option>
                    <option value="false">Inativo</option>                           
                </select>
            </div>

            <c:if test="${filiais != null}">
                <div class="form-group col-md-2">
                    <label for="inputFilial">Filial<h11 class="text-danger">*</h11></label>
                    <select id="filial" name="filial" class="custom-select" required>
                        <option value="">Selecione</option>
                        <c:forEach var="f" items="${filiais}">
                            <option value="${f.idFilial}">${f.nome}</option>
                        </c:forEach>
                    </select>
                </div>
            </c:if>

            <div class="form-group col-md-7">
                <label for="input Descrição">Descrição<h11 class="text-danger">*</h11></label>
                <textarea class="form-control" id="descricao" name="descricao" maxlength="450" rows="4" placeholder="Descrição..." required></textarea>
            </div>
        </div>

        <hr>
        <div class="row">
            <div class="container form-group-inline">
                <input type="submit" class="btn btn-success" value="Salvar">
                <button type="reset" class="btn btn-light" >Cancelar</button>
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
        $('.number').mask("0000000000", {reverse: true});
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