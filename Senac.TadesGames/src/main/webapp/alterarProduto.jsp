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

    <form action="Clientes" method="post">
        <input type="hidden" value="${produto.idProduto}" id="idProduto" name="idProduto">
        <input type="hidden" value="alterar" id="acao" name="acao">

        <div class="row justify-content-around">

            <div class="form-group col-md-4">
                <label for="inputTitulo">Nome/Título*</label>
                <input type="text" class="form-control" id="inputTitulo" placeholder="Nome do Produto" required>
            </div>

            <div class="form-group col-md-3">
                <label for="inputPlataforma">Plataforma*</label>
                <select id="inputPlataforma" class="custom-select" required>
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
                <div class="invalid-feedback">Escolha uma Plataforma</div>
            </div>

            <div class="form-group col-md-3">
                <label for="inputCategoria">Categoria*</label>
                <select id="inputCategoria" class="custom-select" required>
                    <option value="">Selecione </option>
                    <option value="1">Jogo 		</option>
                    <option value="2">Console 	</option>
                    <option value="3">Acessório 	</option>
                    <option value="4">Skin	    </option>
                    <option value="5">Controle	</option>
                    <option value="6">Créditos    </option>
                </select>
                <div class="invalid-feedback">Escolha uma catêgoria</div>
            </div>

            <div class="form-group col-md-3">
                <label for="inputValorCompra">Valor de Compra*</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">R$</span>
                    </div>
                    <input type="text" class="form-control" id="inputValorCompra" placeholder="00,00" required>
                </div>
            </div>

            <div class="form-group col-md-3">
                <label for="inputValorVenda">Valor de Venda*</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">R$</span>
                    </div>
                    <input type="text" class="form-control" id="inputValorVenda" placeholder="00,00" required>
                </div>
            </div>

            <div class="form-group col-md-3">
                <label for="inputQuantidade">Quantidade*</label>
                <input type="text" class="form-control" id="inputQuantidade" placeholder="00" required>
            </div>

            <div class="col-md-3">
                <label>Status</label>
                <div class="form-check col-md-3">
                    <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
                    <label class="form-check-label" for="defaultCheck1" >
                        Desativado
                    </label>
                </div>
            </div>

            <div class="form-group col-md-3">
                <label for="exampleFormControlSelect2">Gêneros*</label>
                <select multiple class="form-control" id="exampleFormControlSelect2" required>
                    <option>FPS</option>
                    <option>Aventura</option>
                    <option>RPG</option>
                    <option>Arcade</option>
                    <option>Luta</option>
                    <option>Outros</option>
                </select>
            </div>
            <div class="form-group col-md-9">
                <label for="input Descrição">Descrição</label>
                <textarea class="form-control" rows="4" placeholder="Descrição..." required></textarea>
            </div>
        </div>

        <hr>
        <div class="row">
            <div class="container form-group-inline">
                <input type="submit" class="btn btn-success" id="inputSalvar" value="Salvar">
                <a href="consultaProduto.jsp" class="btn btn-light">Cancelar</a>
            </div>
        </div>

    </form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>