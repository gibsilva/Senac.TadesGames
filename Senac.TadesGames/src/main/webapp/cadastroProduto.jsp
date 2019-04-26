<%-- 
    Document   : cadastroProduto
    Created on : 21/04/2019, 04:45:49
    Author     : Marcel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Cadastro de Produtos</title>


        <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="resources/css/estilo.css"/>

    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="container">
            <div class="page-header">
                <h1>Cadastro de Produto</h1>
            </div>
            <form>
                <div class="row justify-content-around">
                    <div class="form-group col-md-2">
                        <label for="IdProduto">ID do protuto<h11 class="text-danger">*</h11></label>
                        <input type="text" class="form-control" id="IdProduto" placeholder="0000" readonly>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputTitulo">Nome/Título<h11 class="text-danger">*</h11></label>
                        <input type="text" class="form-control" maxlength="80"  id="inputTitulo" placeholder="Nome do Produto" required>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="inputPlataforma">Plataforma<h11 class="text-danger">*</h11></label>
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
                    </div>
                    
                    <div class="form-group col-md-3">
                        <label for="inputCategoria">Categoria<h11 class="text-danger">*</h11></label>
                        <select id="inputCategoria" class="custom-select" required>
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
                            <input type="text" class="form-control" id="inputValorCompra" placeholder="00,00" required>
                        </div>
                    </div>

                    <div class="form-group col-md-3">
                        <label for="inputValorVenda">Valor de Venda<h11 class="text-danger">*</h11></label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">R$</span>
                            </div>
                            <input type="text" class="form-control" id="inputValorVenda" placeholder="00,00" required>
                        </div>
                    </div>

                    <div class="form-group col-md-3">
                        <label for="inputQuantidade">Quantidade<h11 class="text-danger">*</h11></label>
                        <input type="text" class="form-control" id="inputQuantidade" placeholder="00" required>
                    </div>
                    <div class="form-group col-md-2">
                        <label for="inputSexo">Status</label>
                        <select id="inputCategoria" class="custom-select" required>
                            <option selected value="A">Ativo</option>
                            <option value="I">Inativo</option>                           
                        </select>
                </div>
                    
                    <div class="form-group col-md-3">
                        <label for="exampleFormControlSelect2">Gêneros<h11 class="text-danger">*</h11></label>
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
                        <label for="input Descrição">Descrição<h11 class="text-danger">*</h11></label>
                        <textarea class="form-control" rows="4" placeholder="Descrição..." required></textarea>
                    </div>
                </div>
                <div class="page-header"></div>
                <div class="row">
                    <div class="form-group">
                        <input type="submit" class="btn btn-success" id="inputSalvar" value="Salvar">
                    </div>
                    <div class="form-group col-auto">
                        <a href="home.jsp" class="btn btn-light "  type="submit">Cancelar</a>
                    </div>
                
            </form>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script src="bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>
