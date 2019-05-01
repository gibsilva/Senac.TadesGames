<%-- 
    Document   : CadastroUsuario
    Created on : 21/04/2019, 13:14:09
    Author     : Marcel
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="header.jsp" %>
<title>Cadastro de Usuarios</title>

<div class="container">
    <br>
    <h2>Novo Usuários</h2>
    <hr>
    <form>
        <div class="row">
            <div class="form-group col-md-2">
                <label for="IdFuncionario">ID do Funcionário<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" id="IdFuncionario" placeholder="0000" readonly>
            </div>

            <div class="form-group col-md-5">
                <label for="inputNomeFunc">Nome<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" id="inputNomeFunc" maxlength="80" placeholder="Nome Comleto" required>
            </div>

            <div class="form-group col-md-2">
                <label for="inputSexo">Sexo<h11 class="text-danger">*</h11></label>
                <select id="inputCategoria" class="custom-select" required>
                    <option selected value="M">Masculino</option>
                    <option value="F">Feminino</option>
                    <option value="O">Outros</option>
                </select>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-5">
                <label for="Email">Email<h11 class="text-danger">*</h11></label>
                <input type="email" class="form-control" id="email" name="email" aria-describedby="emailHelp"
                       placeholder="Digite seu Email" required="">
            </div>

            <div class="form-group col-md-4">
                <label for="inputCPFFunc">CPF<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" name="cpf" id="cpf" minlength="11" maxlength="11" placeholder="Digite seu CPF" required>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-2">
                <label for="inputFilial">Filial<h11 class="text-danger">*</h11></label>
                <select id="inputFilial" class="custom-select" required>
                    <option value="">Selecione</option>
                    <option value="1">Matriz</option>
                    <option value="2">Filial 1</option>
                    <option value="3">Filial 2</option>
                    <option value="4">Filial 3</option>
                </select>
            </div>


            <div class="form-group col-md-3">
                <label for="inputSetor">Setor<h11 class="text-danger">*</h11></label>
                <select id="setor" class="custom-select" required>
                    <option value="">Selecione</option>
                    <option value="1">Diretoria</option>
                    <option value="2">Produtos/Serviços/Marketing</option>
                    <option value="5">Vendas</option>
                    <option value="6">T.I</option>
                    <option value="7">Administrativo</option>
                </select>
            </div>

            <div class="form-group col-md-2">
                <label for="inputCargo">Cargo<h11 class="text-danger">*</h11></label>
                <select id="cargo" class="custom-select" required>
                    <option value="">Selecione</option>
                    <option value="D">Diretor</option>
                    <option value="GG">Gerente Global</option>
                    <option value="GR">Gerente Regional</option>
                    <option value="ST">Suporte Técnico</option>
                    <option value="F">Funcionário</option>
                    <option value="V">Vendedor (a)</option>
                </select>
            </div>
        </div>

        <div class="row ">
            <div class="form-group col-md-4">
                <label for="inputLogin">Login:<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" id="inputNomeFunc" minlength="6" maxlength="15" placeholder="Login" required>
            </div>

            <div class="form-group col-md-4">
                <label for="inputSenha">Senha<h11 class="text-danger">*</h11></label>
                <input type="password" class="form-control" id="inputSenha" minlength="4" maxlength="8" placeholder="Digite sua Senha" required>
            </div>  
        </div>

        <hr>
        <div class="row">
            <div class="container form-group-inline">
                <input type="submit" class="btn btn-success" value="Salvar">
                <a href="home.jsp" class="btn btn-light">Cancelar</a>
            </div>
        </div>

    </form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.min.js"></script>

