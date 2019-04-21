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
        <!-- As 3 meta tags acima *devem* vir em primeiro lugar dentro do `head`; qualquer outro conteúdo deve vir *após* essas tags -->
        <title>Cadastro Produto</title>

        <!-- Bootstrap -->
        <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css"/>
        <link rel="stylesheet" type="text/css" href="resources/css/estilo.css"/>

        <!-- HTML5 shim e Respond.js para suporte no IE8 de elementos HTML5 e media queries -->
        <!-- ALERTA: Respond.js não funciona se você visualizar uma página file:// -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
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
                        <label for="IdProduto">ID do protuto*</label>
                        <input type="text" class="form-control" id="IdProduto" placeholder="0000">
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputTitulo">Nome/Título*</label>
                        <input type="text" class="form-control" id="inputTitulo" placeholder="Nome do Produto">
                    </div>
                    <div class="form-group col-md-3">
                        <label for="inputPlataforma">Plataforma*</label>
                        <select id="inputPlataforma" class="form-control">
                            <option selected>Escolha</option>
                            <option>PlayStation 4 </option>
                            <option>PlayStation 3 </option>
                            <option>PlayStation 2 </option>
                            <option>PSP			  </option>
                            <option>PS Vita		  </option>
                            <option>XBOX 360      </option>
                            <option>XBOX ONE	  </option>
                            <option>Nitendo Wii	  </option>
                            <option>Nitendo Switch</option>
                            <option>Nitendo DS    </option>
                            <option>Nitendo 3DS	  </option>
                            <option>PC/Computador </option>
                        </select>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="inputCategoria">Categoria*</label>
                        <select id="inputCategoria" class="form-control">
                            <option selected>Escolha</option>
                            <option>Jogo 		</option>
                            <option>Console 	</option>
                            <option>Acessório 	</option>
                            <option>Skin	    </option>
                            <option>Controle	</option>
                            <option>Créditos    </option>
                        </select>
                    </div>
                    <div class="form-group col-md-3">
                        <label for="inputValorCompra">Valor de Compra*</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">R$</span>
                            </div>
                            <input type="text" class="form-control" id="inputValorCompra" placeholder="00,00">
                        </div>
                    </div>

                    <div class="form-group col-md-3">
                        <label for="inputValorVenda">Valor de Venda*</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">R$</span>
                            </div>
                            <input type="text" class="form-control" id="inputValorVenda" placeholder="00,00">
                        </div>
                    </div>

                    <div class="form-group col-md-3">
                        <label for="inputQuantidade">Quantidade*</label>
                        <input type="text" class="form-control" id="inputQuantidade" placeholder="00">
                    </div>
                    <div class="col-md-3">
                        <label>Temporariamente Desativado</label>
                        <div class="form-check col-md-3">
                            <input class="form-check-input" type="checkbox" value="" id="defaultCheck1">
                            <label class="form-check-label" for="defaultCheck1" >
                                Desativado
                            </label>
                        </div>
                    </div>

                    <div class="form-group col-md-3">
                        <label for="exampleFormControlSelect2">Gêneros</label>
                        <select multiple class="form-control" id="exampleFormControlSelect2">
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
                        <textarea class="form-control" rows="4" placeholder="Descrição..."></textarea>
                    </div>
                </div>
                <div class="page-header"> </div>
                <div class="row">
                    <div class="form-group col-auto">
                        <input type="submit" class="btn btn-success" value="Salvar">
                        <input type="reset" class="btn btn-secondary" id="inputCancelar" value="Cancelar">
                    </div>
            </form>

        </div>
        <!-- jQuery (obrigatório para plugins JavaScript do Bootstrap) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Inclui todos os plugins compilados (abaixo), ou inclua arquivos separadados se necessário -->
        <script src="bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>
