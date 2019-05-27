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
    <form action="Vendas" method="post" id="vendas" name="vendas">
        <input type="hidden" value="salvar" name="acao">
        <input type="hidden" value="" name="listaDeItens" id="listaDeItens">
        <div class="form-row">
            <div class="form-group col-md-2">
                <label for="inputVendedor">Vendedor<h11 class="text-danger">*</h11></label>
                <select id="vendedor" name="vendedor" class="custom-select" required>
                    <option selected value="">Selecionar</option>
                    <c:forEach var="u" items="${vendedores}">
                        <option value="${u.idUsuario}">${u.nome}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group col-md-3">
                <label for="inputCPFFCliente">CPF/CNPJ do Cliente</label>
                <input type="text" class="form-control document" id="cpfCliente" name="cpfCliente" minlength="11" maxlength="14" placeholder="Digite o CPF do cliente">
            </div>

            <div class="form-group col-md-4">
                <label for="inputNomeCliente">Cliente</label>
                <input type="text" class="form-control" name="nomeCliente" id="nomeCliente" minlength="2" placeholder="Digite o nome do cliente" readonly>
            </div>
            <div class="form-inline">
                <button type="button" style="margin-top: 14px" class="btn btn-primary" onclick="obterCliente()"><i class="fas fa-search"></i></button>
            </div>
        </div>

        <div class="form-row">
            <div class="form-group col-md-2">
                <label for="IdProduto">ID do Produto<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control number" id="idProduto" name="idProduto" placeholder="Digite o ID" onblur="obterProduto()">
            </div>

            <div class="form-group col-md-4">
                <label for="inputNomeFunc">Nome/Título<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" id="nomeProduto" name="nomeProduto" maxlength="80" placeholder="Nome" required readonly>
            </div>


            <div class="form-group col-md-2">
                <label for="inputValorUnitario">Valor Unitário</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">R$</span>
                    </div>
                    <input type="text" class="form-control money" id="valorUnitario" name="valorUnitario" placeholder="00,00" readonly>
                </div>
            </div>

            <div class="form-group col-md-1">
                <label for="inputQuantidade">Qtde<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control number" id="quantidade" name="quantidade" placeholder="0">
            </div>
            <div class="form-inline">
                <button type="button" style="margin-top: 11px" class="btn btn-primary" onclick="adicionarItem()"><i class="fas fa-cart-plus"></i> Adicionar</button>
            </div> 
        </div>

        <span id="result">

        </span>
        <br>

        <table class="table table-hover">
            <thead>
                <tr>
                    <th class="text-center" scope="col">ID Produto</th>
                    <th class="text-center" scope="col">Nome/Título</th>
                    <th class="text-center" scope="col">Categoria</th>
                    <th class="text-center" scope="col">Plataforma</th>
                    <th class="text-center" scope="col">Gênero</th>
                    <th class="text-center" scope="col">Quantidade</th>
                    <th class="text-center" scope="col">Preço Unitário</th>
                    <th class="text-center" scope="col">Preço Total (R$)</th>
                    <th class="text-center" scope="cool">#</th>

                </tr>
            </thead>

            <tbody id="tabela" name="tabela">
                <!--preenchido conforme for adicionando produtos-->
            </tbody>
        </table>

        <hr>
        <br>
        <div class="btn-toolbar justify-content-between">
            <div class="form-row">
                <div class="col-md-5">
                    <div class="form-group">
                        <label for="inputFormaPagamento">Forma de Pagamento<h11 class="text-danger">*</h11></label>
                        <select id="formaPagamento" name="formaPagamento" class="custom-select" required>
                            <option selected value="">Selecionar</option>
                            <option value="1">Cartão Debito</option>
                            <option value="2">Cartão de Credito</option>
                            <option value="3">Dinheiro</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="inputParcelas">Parcelas</label>
                        <select id="parcelas" class="custom-select" name="parcelas" disabled>
                            <option selected value="" >Selecionar</option>
                            <option value="1">1x</option>
                            <option value="2">2x</option>
                            <option value="3">3x</option>
                            <option value="4">4x</option>
                            <option value="5">5x</option>
                        </select>
                    </div>    
                </div>

                <div class="col-md-5">
                    <div class="form-group">
                        <label for="inputValorUnitario">Valor a receber</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">R$</span>
                            </div>
                            <input type="text" class="form-control" id="valorRecebido" name="valorRecebido" placeholder="00,00" readonly>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="inputValorUnitario">Troco</label>
                        <div class="input-group">
                            <div class="input-group-prepend">
                                <span class="input-group-text">R$</span>
                            </div>
                            <input type="text" class="form-control" id="troco" name="troco" placeholder="00,00" readonly>
                        </div>
                    </div>

                </div>
            </div>

            <div class="form-group col-md-3">
                <label  class="col-form-label col-form-label-lg" for="inputTotal">TOTAL</label>
                <div class="input-group">
                    <div class="input-group-prepend">
                        <span class="input-group-text">R$</span>
                    </div>
                    <input type="text" class="form-control form-control-lg" id="totalPedido" name="totalPedido" placeholder="00,00" readonly>
                </div>
            </div>
        </div>
        <hr>

        <div class="btn-toolbar justify-content-between" role="toolbar" >
            <div class="form-group">
                <button type="reset" class="btn-lg btn-warning"><i class="fas fa-ban"></i> Cancelar</button>
            </div>

            <div class="form-group">
                <button type="submit" class="btn-lg btn-success" onclick="salvarPedido()"><i class="fas fa-check"></i> Finalizar venda</button>
            </div>  
        </div>
    </form>

</div>


<!-- Modal cadastro cliente -->
<div class="modal fade" id="modalCliente" name="modalCliente" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Cliente Inativo</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                Cliente não cadastrado, deseja realizar o cadastro desse cliente?
            </div>
            <div class="modal-footer">
                <a href="${pageContext.request.contextPath}/autenticado/Clientes?acao=salvar" class="btn btn-warning">Cadastrar</a>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Fechar</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal cadastro cliente -->
<div class="modal fade" id="modalEditarCliente" name="modalEditarCliente" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Cadastrar Cliente</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <input type="hidden" value="" name="idEditarCliente" id="idEditarCliente">
                O cliente está <strong>inativo</strong>, deseja editar o cadastro desse cliente?
            </div>
            <div class="modal-footer">
                <a href="#" class="btn btn-warning" id="linkEditarCliente" onclick="editarClienteInativo($('#idEditarCliente').val())">Editar Cadastro</a>
                <button type="button" class="btn btn-primary" data-dismiss="modal">Fechar</button>
            </div>
        </div>
    </div>
</div>

<script>
    var lista = {
        itens: []
    };
    var produto;
    var produtos = [];

    $(document).ready(function () {
        
        //mascaras
        $('.money').mask('000.000.000.000.000,00', {reverse: true});
        $('.money2').mask("#.##0,00", {reverse: true});
        $('.number').mask("0000000000", {reverse: true});
        $('.document').mask("00000000000000", {reverse: true});
        //fim mascaras
        
        pedidoSalvo();

        $('#vendas').submit(function (e) {
            if (lista.itens.length === 0) {
                toastr.warning('Adicione itens antes de finalizar a venda.', 'Atenção');
                e.preventDefault(e);
            } else if ($('#valorRecebido').val() < $('#totalPedido').val()) {
                toastr.warning('Valor recebido não pode ser menor que o Valor total', 'Atenção');
                $('#valorRecebido').focus();
                e.preventDefault(e);
            } else {
                this.submit;
            }
        });

        $('#formaPagamento').change(function () {
            var formaPagto = $('#formaPagamento').val();
            switch (formaPagto) {
                case '1':
                    $('#valorRecebido').val($('#totalPedido').val());
                    $("#valorRecebido").prop('readonly', true);
                    $("#parcelas").val('');
                    $("#parcelas").prop('required', false);
                    $("#parcelas").prop('disabled', true);
                    break;
                case '2':
                    $('#valorRecebido').val($('#totalPedido').val());
                    $("#valorRecebido").prop('readonly', true);
                    $("#parcelas").prop('disabled', false);
                    $("#parcelas").prop('required', true);
                    break;
                case '3':
                    $("#valorRecebido").prop('readonly', false);
                    $("#valorRecebido").prop('required', true);
                    $("#parcelas").val('');
                    $("#parcelas").prop('required', false);
                    $("#parcelas").prop('disabled', true);
                    break;
                default :
                    $('#formaPagamento').val('');
                    $('#valorRecebido').val('');
                    $('#parcelas').val('');
                    $('#troco').val('');
            }
        });

        $('#valorRecebido').blur(function () {
            $("#troco").val($('#valorRecebido').val() - $('#totalPedido').val());
        });
    });

    function editarClienteInativo(id) {
        $('#linkEditarCliente').attr('href', '${pageContext.request.contextPath}/autenticado/Clientes?acao=alterar&idCliente=' + id);
    }

    function obterCliente() {
        if ($('#cpfCliente').val() === '') {
            toastr.warning('Preencha o documento do cliente antes de pesquisar', 'Atenção');
        } else {
            $.ajax({
                url: "Vendas?acao=obterCliente",
                type: "GET",
                contentType: 'application/json',
                data: {'cpfCliente': $("#cpfCliente").val()},
                success: function (data) {
                    var cliente = $.parseJSON(data);
                    if (cliente !== null) {
                        if (cliente.ativo === false) {
                            $('#idEditarCliente').val(cliente.idCliente);
                            $('#modalEditarCliente').modal();
                        } else {
                            toastr.success('Cliente encontrado', 'Aviso');
                            $('#nomeCliente').val(cliente.nome);
                        }
                    }
                    else {
                        //toastr.warning('Cliente inválido ou não cadastrado no sistema', 'Aviso');
                        $('#modalCliente').modal();
                        $('#nomeCliente').val('');
                        $('#cpfCliente').val('');
                        $('#cpfCliente').focus();
                    }
                },
                error: function (data) {
                    toastr.error('Ocorreu um erro durante a requisição: ' + data, 'Erro');
                }
            });
        }
    }

    function obterProduto() {
        if ($('#idProduto').val() === '') {
            toastr.warning('Preencha o campo antes de pesquisar', 'Info');
        } else {
            $.ajax({
                url: "Vendas?acao=obterProduto",
                type: "GET",
                contentType: 'application/json',
                data: {'idProduto': $('#idProduto').val()},
                success: function (data) {
                    produto = $.parseJSON(data);
                    console.log(produto);
                    if (produto !== null) {
                        if (produto.ativo === false) {
                            toastr.warning('O produto ' + produto.nome + ' está inativo, não é possível adicionar', 'Atenção');
                            $('#nomeProduto').val('');
                            $('#idProduto').val('');
                            $('#idProduto').focus();
                        } else {
                            $('#nomeProduto').val(produto.nome);
                            $('#valorUnitario').val(produto.precoVenda);
                        }
                    } else {
                        toastr.warning('Produto não encontrado, verifique o código digitado', 'Atenção');
                        $('#nomeProduto').val('');
                        $('#idProduto').val('');
                        $('#idProduto').focus();
                    }
                },
                error: function (errorThrown) {
                    toastr.error('Ocorreu um erro ao consultar: ' + errorThrown, 'Erro');
                }
            });
        }
    }

    function adicionarItem() {
        for (var i = 0; i < lista.itens.length; i++) {
            if ($('#idProduto').val() === lista.itens[i].idProduto) {
                toastr.warning('O produto já está na lista, remova e edite a quantidade para adicionar mais unidades', 'Atenção');
                return;
            }
        }
        if ($('#quantidade').val() > produto.quantidadeEstoque) {
            toastr.warning('Estoque insuficiente. \nQuantidade disponível: ' + produto.quantidadeEstoque + ' unidades', 'Atenção');
            return;
        }
        if ($('#idProduto').val() === '') {
            toastr.warning('Preencha o ID do produto', 'Atenção');
            return;
        }
        if ($('#quantidade').val() === '' || $('#quantidade').val() === '0' || $('#quantidade').val() < 0) {
            toastr.warning('Não é possível adicionar com quantidade zero, negativa ou vazia', 'Atenção');
            $('#quantidade').val('');
            $('#quantidade').focus();
        } else {
            var item = {
                idItensPedido: lista.itens.length + 1,
                idProduto: $('#idProduto').val(),
                nome: produto.nome,
                categoria: produto.categoria.nome,
                plataforma: produto.plataforma.nome,
                genero: produto.genero.nome,
                quantidade: parseInt($('#quantidade').val()),
                valorUnitario: parseFloat($('#valorUnitario').val()),
                precoTotal: 0
            };
            
            item.precoTotal.toFixed(2);         
            item.precoTotal = item.quantidade * item.valorUnitario;
            this.produto.quantidadeEstoque = this.produto.quantidadeEstoque - item.quantidade;

            lista.itens.push(item);

            var s = '';
            for (var i = 0; i < lista.itens.length; i++) {
                s += '<tr class="table-light">';
                s += '<td class="text-center">' + lista.itens[i].idProduto + '</td>';
                s += '<td class="text-center">' + lista.itens[i].nome + '</td>';
                s += '<td class="text-center">' + lista.itens[i].categoria + '</td>';
                s += '<td class="text-center">' + lista.itens[i].plataforma + '</td>';
                s += '<td class="text-center">' + lista.itens[i].genero + '</td>';
                s += '<td class="text-center">' + lista.itens[i].quantidade + '</td>';
                s += '<td class="text-center">' + lista.itens[i].valorUnitario + '</td>';
                s += '<td class="text-center">' + parseFloat(lista.itens[i].precoTotal) + '</td>';
                s += '<td class="text-center">' + '<button type="button" class="btn btn-danger btn-sm" onclick="removerItem(' + i + ')"><i class="fas fa-times"></i></button>' + '</td>';
            }
            $('#tabela').html(s);

            $('#quantidade').val('');
            $('#idProduto').val('');
            $('#nomeProduto').val('');
            $('#valorUnitario').val('');
            calculaTotalPedido();
        }
    }

    function removerItem(id) {
        lista.itens.splice(id, 1);
        $('#tabela').html('');
        var s = '';
        for (var i = 0; i < lista.itens.length; i++) {
            s += '<tr class="table-light">';
            s += '<td class="text-center">' + lista.itens[i].idProduto + '</td>';
            s += '<td class="text-center">' + lista.itens[i].nome + '</td>';
            s += '<td class="text-center">' + lista.itens[i].categoria + '</td>';
            s += '<td class="text-center">' + lista.itens[i].plataforma + '</td>';
            s += '<td class="text-center">' + lista.itens[i].genero + '</td>';
            s += '<td class="text-center">' + lista.itens[i].quantidade + '</td>';
            s += '<td class="text-center">' + lista.itens[i].valorUnitario + '</td>';
            s += '<td class="text-center">' + parseFloat(lista.itens[i].precoTotal) + '</td>';
            s += '<td class="text-center">' + '<button type="button" class="btn btn-danger btn-sm" onclick="removerItem(' + i + ')">excluir</button>' + '</td>';
        }
        $('#tabela').html(s);
        toastr.error('Item removido da lista', 'Aviso');
        //this.produto.quantidadeEstoque = this.produto.quantidadeEstoque + lista.itens[id].quantidade;
        calculaTotalPedido();
    }

    function calculaTotalPedido() {
        if (lista.itens.length >= 0) {
            var total = 0;
            for (var i = 0; i < lista.itens.length; i++) {
                total += lista.itens[i].precoTotal;
            }
            total.toFixed(2);
            $('#totalPedido').val(total);
            $('#valorRecebido').val(total);
        }
    }

    function salvarPedido() {
        var json = JSON.stringify(lista);
        $('#listaDeItens').val(json);

    }

    function pedidoSalvo() {
        var statusOk = '${statusOk}';
        if (statusOk === 'true') {
            toastr.success('Venda realizada com sucesso', 'Sucesso');
        }
    }

</script>

