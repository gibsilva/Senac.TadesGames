<%-- 
    Document   : alterarUsuario
    Created on : 24/04/2019, 16:21:52
    Author     : adrianne
--%>
<%@include file="header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<title>Alterar Usuario</title>


<div class="container">
        <br>
        <h2>Alterar Usuario</h2>
        <hr>
    <form>      
        <div class="row">
            <div class="form-group col-md-2">
                <label for="IdFuncionario">ID do Funcionário<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" id="IdFuncionario" placeholder="0000" readonly>
            </div>

            <div class="form-group col-md-5">
                <label for="inputNomeFunc">Nome<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" id="NomeFunc" maxlength="80" placeholder="Nome Comleto" required>
            </div>

            <div class="form-group col-md-2">
                <label for="inputSexo">Sexo<h11 class="text-danger">*</h11></label>
                <select id="sexo" class="custom-select" required>
                    <option selected value="">Selecione</option>
                    <option value="M">Masculino</option>
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

        <div class="row justify-content-around">
            <div class="form-group col-md-4">
                <label for="inputFilial">Filial<h11 class="text-danger">*</h11></label>
                <select id="filial" class="custom-select" required>
                    <option value="">Selecione </option>
                    <option value="1">Matriz  </option>
                    <option value="2">Filial 1   </option>
                    <option value="3">Filial 2  </option>
                    <option value="4">Filial 3      </option>
                </select>
                <div class="invalid-feedback">Escolha a Filial/Matriz Válida</div>
            </div>


            <div class="form-group col-md-4">
                <label for="inputSetor">Setor<h11 class="text-danger">*</h11></label>
                <select id="setor" class="custom-select" required>
                    <option value="">Selecione </option>
                    <option value="1">Diretoria    </option>
                    <option value="2">Produtos  </option>
                    <option value="3">Serviços   </option>
                    <option value="4">Marketing  </option>
                    <option value="5">Vendas    </option>
                    <option value="6">T.I      </option>
                    <option value="7">Administrativo      </option>
                </select>
                <div class="invalid-feedback">Escolha Setor Válido</div>
            </div>

            <div class="form-group col-md-4">
                <label for="inputCargo">Cargo<h11 class="text-danger">*</h11></label>
                <select id="cargo" class="custom-select" required>
                    <option value="">Selecione</option>
                    <option value="1">Diretor</option>
                    <option value="2">Gerente Global</option>
                    <option value="3">Gerente Regional</option>
                    <option value="4">Suporte Técnico</option>
                    <option value="5">Funcionário</option>
                </select>
        </div>

        <div class="row">
            <div class="form-group col-md-4">
                <label for="inputLogin">Login<h11 class="text-danger">*</h11></label>
                <input type="text" class="form-control" id="login" minlength="6" maxlength="15" placeholder="Login" required>
            </div>

            <div class="form-group col-md-4">
                <label for="inputSenha">Senha<h11 class="text-danger">*</h11></label>
                <input type="password" class="form-control" id="senha" minlength="4" maxlength="8" placeholder="Digite sua Senha" required>
            </div>

            <div class="form-group col-md-2">
                <label for="inputSexo">Status</label>
                <select id="ativo" name="ativo" class="custom-select" required>
                    <option value="true">Ativo</option>
                    <option value="false">Inativo</option>                           
                </select>
            </div>
        </div>

        <hr>
        <div class="row">
            <div class="container form-group-inline">
                <input type="submit" class="btn btn-success" value="Salvar">
                <a href="consultaUsuario.jsp" class="btn btn-light" >Cancelar</a>
            </div>
        </div>
    <form>
</div>


            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
            <script src="bootstrap/js/bootstrap.min.js"></script>

