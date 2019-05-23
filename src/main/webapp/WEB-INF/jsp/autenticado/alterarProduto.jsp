<%-- 
    Document   : cadastroProduto
    Created on : 21/04/2019, 04:45:49
    Author     : Marcel
--%>
<%@include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>Alteração de Produto</title>

<div class="container">
    <br>
    <h2>Alteração de Produto</h2>
    <hr>

    <form action="Produtos" method="post">
        <input type="hidden" value="${produto.idProduto}" id="idProduto" name="idProduto">
        <input type="hidden" value="alterar" id="acao" name="acao">

        <div class="row">

            <div class="form-group col-md-4">
                <label for="inputTitulo">Nome/Título<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" maxlength="80"  id="nome" name="nome" 
                       placeholder="Nome/Titulo do Produto" required value="${produto.nome}">
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
                <label for="genero">Genero<h11 class="text-danger">*</h11></label>
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
                    <input type="text" class="form-control" id="valorCompra" name="valorCompra" placeholder="00,00" required value="${produto.precoCompra}">
                </div>
            </div>

            <div class="form-group col-md-3">
                <label for="inputValorVenda">Valor de Venda<h11 class="text-danger">*</h11></label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">R$</span>
                    </div>
                    <input type="text" class="form-control" id="valorVenda" name="valorVenda" placeholder="00,00" required value="${produto.precoVenda}">
                </div>
            </div>

            <div class="form-group col-md-3">
                <label for="inputQuantidade">Quantidade<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" id="quantidade" name="quantidade" placeholder="00" required value="${produto.quantidadeEstoque}">
            </div>

            <div class="form-group col-md-2">
                <label for="inputSexo">Status</label>
                <select id="ativo" name="ativo" class="custom-select" required>
                    <option selected value="true">Ativo</option>
                    <option value="false">Inativo</option>                           
                </select>
            </div>

            
            
            <div class="form-group col-md-2">
                <label for="inputFilial">Filial<h11 class="text-danger">*</h11></label>
                <select id="filial" name="filial" class="custom-select">
                    <option value="">Selecione</option>
                    <c:forEach var="f" items="${filiais}">
                        <option value="${f.idFilial}">${f.nome}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group col-md-7">
                <label for="input Descrição">Descrição<h11 class="text-danger">*</h11></label>
                <textarea class="form-control" id="descricao" name="descricao" maxlength="450" rows="4" placeholder="Descrição..." required ></textarea>
            </div>
        </div>

        <hr>
        <div class="row">
            <div class="container form-group-inline">
                <input type="submit" class="btn btn-success" id="inputSalvar" value="Salvar">
                <a href="Produtos?acao=listar" class="btn btn-light">Cancelar</a>
            </div>
        </div>

    </form>
</div>
        
<script>
    $(document).ready(function(){
        document.getElementById('filial').value = '${produto.idFilial}';        
        document.getElementById('plataforma').value = '${produto.idPlataforma}';
        document.getElementById('categoria').value = '${produto.idCategoria}';
        document.getElementById('genero').value = '${produto.idGenero}';
        document.getElementById('ativo').value = '${produto.ativo}';
        document.getElementById('descricao').value = '${produto.descricao}';
    });
</script>