<%-- 
    Document   : sobre
    Created on : 30/05/2019, 05:24:28
    Author     : Gi
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${sessionScope.usuarioLogado != null}">
    <%@include file="header.jsp" %>
</c:if>
<title>Sobre</title>
<div class="container">
    <br>
    <h3>The Umbrella Academy</h3>
    <h5>Nossos Desenvolvedores</h5>
    <hr>

    <div class="row">
        <div class="col-sm-6">
            <div class="card border-primary">
                <div class="card-header bg-primary text-white">Girlaine Silva</div>
                <div class="card-body">
                    <p class="card-text">23 anos, atualmente cursando o terceiro semestre de Análise e 
                        Desenvolvimento de Sistemas no Centro Universitario Senac. Trabalha com testes mobile e de sistemas na empresa Martoni's Sistemas Inteligentes.</p>
                </div>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="card border-primary">
                <div class="card-header bg-primary text-white">Giovani Carignato</div>
                <div class="card-body">
                    <p class="card-text">20 anos, atualmente cursando o terceiro semestre de Análise e 
                        Desenvolvimento de Sistemas no Centro Universitario Senac. Trabalha com desenvolvimento de software no Banco Santander.</p>
                </div>
            </div>
        </div>
    </div>
    <br>
    <div class="row">
        <div class="col-sm-6">
            <div class="card border-primary ">
                <div class="card-header bg-primary text-white">Marcel Cardoso</div>
                <div class="card-body">
                    <p class="card-text">20 anos, atualmente cursando o terceiro semestre de Análise e 
                        Desenvolvimento de Sistemas no Centro Universitario Senac. Trabalha como Atendente em uma empresa de Telemarketing até 
                        conseguir um estágio na área de Tecnologia da informação.</p>
                </div>
            </div>
        </div>
        <div class="col-sm-6">
            <div class="card border-primary">
                <div class="card-header bg-primary text-white">Adriane Alexandre</div>
                <div class="card-body">
                    <p class="card-text">24 anos, atualmente cursando o terceiro semestre de  Análise e 
                        Desenvolvimento de Sistemas no Centro Universitario Senac. trabalha  como Analista de Sistemas na IBM.</p>
                </div>
            </div>
        </div>
    </div>
</div>

