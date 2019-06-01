<%-- 
    Document   : home
    Created on : 20/04/2019, 17:36:01
    Author     : Gi
--%>

<!-- teste de execução de página jsp com o servlet-->
<%@include file="header.jsp" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<title>Home</title>

<div class="container">
    <br>
    <h4>Bem-Vindo(a), ${sessionScope.usuarioLogado.nome}</h4> <!--troca o nome pelo nome do usuario -->
    <h3 class="text-center" ><img src="../resources/img/LogoTadesGames.png" class="img-fluid" alt="Responsive image"></h3>
    <hr>
    
    <div class="row justify-content-around">
        <div class="card-deck ">
            <div class="card border-primary mb-3" style="max-width: 20rem;">
                <div class="card-header bg-primary text-white">Produtos</div>
                <div class="card-body">
                    <h4 class="card-title">Controle Mensal de Produtos</h4>
                    <p class="card-text">Total Vendidos: ${homeProduto.quantidadeVendida}</p>
                    <p class="card-text">Total Estoque: ${homeProduto.quantidadeTotalEstoque}</p>
                </div>
            </div>

            <div class="card border-info mb-3" style="max-width: 20rem;">
                <div class="card-header bg-info text-white">Vendas</div>
                <div class="card-body">
                    <h4 class="card-title">Controle Mensal de Vendas</h4>
                    <p class="card-text">Total Vendas: ${homeVenda.qtdVendas}</p>
                    <p class="card-text" id="totalVendas">Total Vendido R$: ${homeVenda.totalVendido}</p>
                </div>
            </div>

            <div class="card border-warning mb-3" style="max-width: 20rem;">
                <div class="card-header bg-warning text-white">Vendedores</div>
                <div class="card-body">
                    <h4 class="card-title">Melhor vendedor do mês: <small><strong>${homeVendedor.nomeVendedor}</strong></small></h4>
                    <p class="card-text" id="totalVendido">Total vendidos: R$ ${homeVendedor.totalVendido}</p>
                    <p class="card-text">Total de vendas: ${homeVendedor.qtdvendas}</p>
                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="rodape.jsp" %>

<script>
    $(document).ready(function(){
       formatarValor(); 
    });
    
    function formatarValor(){
        var totalVendido = parseFloat('${homeVendedor.totalVendido}');
        if(totalVendido === 'NaN' || '${homeVendedor.totalVendido}' === ''){
            totalVendido = 0;
        }
        var totalVendas = parseFloat('${homeVenda.totalVendido}');
        if(totalVendas === 'NaN' || '${homeVenda.totalVendido}' === ''){
            totalVendas = 0;
        }
        
        $('#totalVendido').html('Total vendido: R$ ' + totalVendido.toLocaleString('pt-br', {minimumFractionDigits: 2}));
        $('#totalVendas').html('Total vendido: R$ ' + totalVendas.toLocaleString('pt-br', {minimumFractionDigits: 2}));
    }
</script>
