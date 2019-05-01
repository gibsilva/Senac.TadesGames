<%-- 
    Document   : Vendas
    Created on : 28/04/2019, 02:33:50
    Author     : Gi
--%>
<%@include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Realizar Venda</title>

<div class=" container">
    <br>
    <h2>Realizar Venda</h2>
    <hr>
    <br>
    <form>
        <div class="form-row">
            <div class="form-group col-md-2">
                <label for="inputVendedor">Vendedor<h11 class="text-danger">*</h11></label>
                <select id="vendedor" class="custom-select" required>
                    <option selected value="">Selecionar</option>
                    <option value="">Fulano</option>
                    <option value="">Cicrano</option>
                    <option value="">Beltrano</option>
                </select>
            </div>
            
            <div class="form-group col-md-3">
                <label for="inputCPFFCliente">CPF Cliente</label>
                <input type="text" class="form-control" name="cpf" id="cpfCliente" minlength="11" maxlength="11" placeholder="Digite o CPF do cliente" required>
            </div>

            <div class="form-group col-md-4">
                <label for="inputNomeCliente">Cliente</label>
                <input type="text" class="form-control" name="nome" id="nome" minlength="2" placeholder="Digite o nome do cliente" required>
            </div>
            <div class="form-inline">
                <button style="margin-top: 11px" class="btn btn-primary">Procurar</button>
            </div>
        </div>

        <div class="form-row">
            <div class="form-group col-md-2">
                <label for="IdProduto">ID do protuto<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" id="IdProduto" placeholder="0000">
            </div>

            <div class="form-group col-md-4">
                <label for="inputNomeFunc">Nome/Titulo<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" id="inputNomeFunc" maxlength="80" placeholder="Nome" required>
            </div>


            <div class="form-group col-md-2">
                <label for="inputValorUnitario">Valor Unitario</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">R$</span>
                    </div>
                    <input type="text" class="form-control" id="ValorUnitario" placeholder="00,00" readonly>
                </div>
            </div>

            <div class="form-group col-md-1">
                <label for="inputQuantidade">Qtde<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" id="inputQuantidade" placeholder="00" required>
            </div>
            <div class="form-inline">
                <button style="margin-top: 11px" class="btn btn-primary">Adicionar</button>
            </div> 
        </div>

        <br>
        <br>

        <table class="table table-hover">
            <thead>
                <tr>
                    <th scope="col">ID Produto</th>
                    <th scope="col">Nome/Titulo</th>
                    <th scope="col">Plataforma</th>
                    <th scope="col">Quantidade</th>
                    <th scope="col">Preço Unitario</th>
                    <th scope="col">Preço Total</th>
                    <th scope="cool">#</th>

                </tr>
            </thead>

            <tbody id="tabela" name="tabela">

                <tr class="table-light">
                    <td>01</td>
                    <td>The Last of Us</td>
                    <td>Playstation 4</td>
                    <td>1</td>
                    <td>150,00</td>
                    <td>150,00</td>

                    <td>
                        <button type="button" class="btn btn-danger btn-sm">excluir</button>
                    </td>
                </tr>

            </tbody>
        </table>
        <hr>
        <br>
        <div class="btn-toolbar justify-content-between">
            <div class="form-row">
                <div class="col-md-5">
                    <div class="form-group">
                        <label for="inputFormaPagamento">Forma de Pagamento<h11 class="text-danger">*</h11></label>
                        <select id="formaPagamento" class="custom-select" required>
                            <option selected value="">Selecionar</option>
                            <option value="">Cartão Debito</option>
                            <option value="">Cartão de Credito</option>
                            <option value="">Dinheiro</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="inputParcelas">Parcelas</label>
                        <select id="Parcelas" class="custom-select" readonly>
                            <option selected value="">Selecionar</option>
                            <option value="">1x</option>
                            <option value="">2x</option>
                            <option value="">3x</option>
                            <option value="">4x</option>
                            <option value="">5x</option>
                        </select>
                    </div>    
                </div>

                <div class="col-md-5">
                    <div class="form-group">
                        <label for="inputValorUnitario">Dinheiro</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">R$</span>
                            </div>
                            <input type="text" class="form-control" id="dinheiro" placeholder="00,00">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputValorUnitario">Troco</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">R$</span>
                            </div>
                            <input type="text" class="form-control" id="troco" placeholder="00,00" readonly>
                        </div>
                    </div>
                </div>
            </div>

            <div class="form-group col-md-3">
                <label  class="col-form-label col-form-label-lg" for="inputTotal">TOTAL:</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">R$</span>
                    </div>
                    <input type="text" class="form-control form-control-lg" id="inputValorVenda" placeholder="00,00" readonly>
                </div>
            </div>
        </div>
        <hr>

        <div class="btn-toolbar justify-content-between" role="toolbar" >
            <div class="form-group">
                <button type="reset" class="btn-lg btn-warning">Cancelar</button>
            </div>

            <div class="form-group">
                <button type="button" class="btn-lg btn-success">Finalizar Venda</button>
            </div>  
        </div>
    </form>
</div>

