<%-- 
    Document   : alterarUsuario
    Created on : 24/04/2019, 16:21:52
    Author     : adrianne
--%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- As 3 meta tags acima *devem* vir em primeiro lugar dentro do `head`; qualquer outro conteúdo deve vir *após* essas tags -->
        <title>Alteração de Usuários</title>

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
                <h1>Cadastro de Funcionários</h1>
            </div>
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


                <div class="row justify-content-around">
                    <div class="form-group col-md-4">
                        <label for="inputFilial">Filial<h11 class="text-danger">*</h11></label>
                        <select id="inputFilial" class="custom-select" required>
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
                        <select id="inputSetor" class="custom-select" required>
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
                        <select id="inputCargo" class="custom-select" required>
                            <option value="">Selecione</option>
                            <option value="1">Diretor</option>
                            <option value="2">Gerente Global</option>
                            <option value="3">Gerente Regional</option>
                            <option value="4">Suporte Técnico</option>
                            <option value="5">Funcionário</option>
                        </select>
                        <div class="invalid-feedback">Escolha um Cargo Válido</div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-4">
                        <label for="inputLogin">Login<h11 class="text-danger">*</h11></label>
                        <input type="text" class="form-control" id="inputNomeFunc" minlength="6" maxlength="15" placeholder="Login" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputSenha">Senha<h11 class="text-danger">*</h11></label>
                        <input type="password" class="form-control" id="inputSenha" minlength="4" maxlength="8" placeholder="Digite sua Senha" required>
                    </div>
                    
                     <div class="form-group col-md-2">
                        <label for="inputSexo">Status</label>
                        <select id="inputCategoria" class="custom-select" required>
                            <option selected value="A">Ativo</option>
                            <option value="I">Inativo</option>                           
                        </select>
                </div>
                </div>

               

                <div class="page-header"></div>
                
                <div class="row">
                    <div class="container form-group-inline">
                        <input type="submit" class="btn btn-success" value="Salvar">
                        <a href="home.jsp" class="btn btn-light "  type="submit">Cancelar</a>
                    </div>
                </div>
                
            <form>
        </div>
        
        <!-- JQuery (obrigatório para plugins JavaScript do Bootstrap) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Inclui todos os plugins compilados (abaixo), ou inclua arquivos separadados se necessário -->
        <script src="bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>


