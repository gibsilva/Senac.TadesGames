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
    <form action="Clientes" method="post">
        <input type="hidden" value="salvar" id="acao" name="acao">

        <div class="row">
            <div class="form-group col-md-4">
                <label for="inputTitulo">Nome/Título<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" maxlength="80"  id="nome" name="nome" 
                       placeholder="Nome/Titulo do Produto" required>
            </div>

            <div class="form-group col-md-3">
                <label for="inputPlataforma">Plataforma<h11 class="text-danger">*</h11></label>
                <select id="plataforma" name="plataforma" class="custom-select" required>
                    <option value="">Selecione </option>
                    <option value="1">PlayStation 4 </option>
                    <option value="2">PlayStation 3 </option>
                    <option value="3">PlayStation 2 </option>
                    <option value="4">PSP			  </option>
                    <option value="5">PS Vita		  </option>
                    <option value="6">XBOX 360      </option>
                    <option value="7">XBOX ONE	  </option>
                    <option value="8">Nitendo Wii	  </option>
                    <option value="9">Nitendo Switch</option>
                    <option value="10">Nitendo DS    </option>
                    <option value="11">Nitendo 3DS	  </option>
                    <option value="12">PC/Computador </option>
                </select>
            </div>

            <div class="form-group col-md-3">
                <label for="inputCategoria">Categoria<h11 class="text-danger">*</h11></label>
                <select id="categoria" name="categoria" class="custom-select" required>
                    <option value="">Selecione </option>
                    <option value="1">Jogo 		</option>
                    <option value="2">Console 	</option>
                    <option value="3">Acessório 	</option>
                    <option value="4">Skin	    </option>
                    <option value="5">Controle	</option>
                    <option value="6">Créditos    </option>
                </select>
            </div>

            <div class="form-group col-md-3">
                <label for="inputValorCompra">Valor de Compra<h11 class="text-danger">*</h11></label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">R$</span>
                    </div>
                    <input type="text" class="form-control" id="valorCompra" name="valorCompra" placeholder="00,00" required>
                </div>
            </div>

            <div class="form-group col-md-3">
                <label for="inputValorVenda">Valor de Venda<h11 class="text-danger">*</h11></label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">R$</span>
                    </div>
                    <input type="text" class="form-control" id="valorVenda" name="valorVenda" placeholder="00,00" required>
                </div>
            </div>

            <div class="form-group col-md-3">
                <label for="inputQuantidade">Quantidade<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" id="quantidade" name="quantidade" placeholder="00" required>
            </div>

            <div class="form-group col-md-2">
                <label for="inputSexo">Status</label>
                <select id="ativo" name="ativo" class="custom-select" required>
                    <option selected value="A">Ativo</option>
                    <option value="I">Inativo</option>                           
                </select>
            </div>

            <div class="form-group col-md-3">
                <label for="exampleFormControlSelect2">Gêneros<h11 class="text-danger">*</h11></label>
                <select multiple class="form-control" id="genero" name="genero" required>
                    <option>FPS</option>
                    <option>Aventura</option>
                    <option>RPG</option>
                    <option>Arcade</option>
                    <option>Luta</option>
                    <option>Outros</option>
                </select>
            </div>

            <div class="form-group col-md-9">
                <label for="input Descrição">Descrição<h11 class="text-danger">*</h11></label>
                <textarea class="form-control" id="descricao" name="descricao" rows="4" placeholder="Descrição..." required></textarea>
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

