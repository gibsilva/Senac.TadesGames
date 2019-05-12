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
    <h4>Bem-Vindo(a), ${login}</h4> <!--troca o nome pelo nome do usuario -->
    <h3 class="text-center" ><img src="resources/img/LogoTadesGames.png" class="img-fluid" alt="Responsive image"></h3>
    <hr>
<div class="row justify-content-around">
        <div class="card-deck ">
            <div class="card border-primary mb-3" style="max-width: 20rem;">
                <div class="card-header bg-primary text-white">Produtos</div>
                <div class="card-body">
                    <h4 class="card-title">Controle de Produtos</h4>
                    <p class="card-text">Total Vendidos: </p>
                    <p class="card-text">Total Estoque: </p>
                </div>
            </div>

            <div class="card border-info mb-3" style="max-width: 20rem;">
                <div class="card-header bg-info text-white">Vendas</div>
                <div class="card-body">
                    <h4 class="card-title">Controle de Vendas</h4>
                    <p class="card-text">Total Vendas: </p>
                    <p class="card-text">Total Vendidos R$: </p>
                </div>
            </div>

            <div class="card border-warning mb-3" style="max-width: 20rem;">
                <div class="card-header bg-warning text-white">Produtos</div>
                <div class="card-body">
                    <h4 class="card-title">Controle de Produtos</h4>
                    <p class="card-text">Total Vendidos: </p>
                    <p class="card-text">Total Estoque: </p>
                </div>
            </div>
        </div>
</div>
    </div>


