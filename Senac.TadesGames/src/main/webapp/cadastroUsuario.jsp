<%-- 
    Document   : CadastroUsuario
    Created on : 21/04/2019, 13:14:09
    Author     : Marcel
--%>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- As 3 meta tags acima *devem* vir em primeiro lugar dentro do `head`; qualquer outro conteúdo deve vir *após* essas tags -->
        <title>Cadastro de Usuários</title>

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
                <div class="row justify-content-around">
                    <div class="form-group col-md-2">
                        <label for="IdFuncionario">ID do Funcionário*</label>
                        <input type="text" class="form-control" id="IdFuncionario" placeholder="0000" readonly>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputNomeFunc">Nome Completo*</label>
                        <input type="text" class="form-control" id="inputNomeFunc" maxlength="80" placeholder="Nome Comleto" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputCPFFunc">CPF*</label>
                        <input type="text" class="form-control" name="cpf" id="cpf" minlength="11" maxlength="11" placeholder="Digite seu CPF" required>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputFilial">Filial*</label>
                        <select id="inputFilial" class="custom-select" required>
                            <option value="">Selecione </option>
                            <option value="1">Matriz  </option>
                            <option value="2">Filial 1   </option>
                            <option value="3">AFilial 2  </option>
                            <option value="4">Filial 3      </option>
                        </select>
                        <div class="invalid-feedback">Escolha Filial/Matriz Válida</div>
                    </div>
                    <div class="form-group col-md-4">
                        <label for="inputSetor">Setor*</label>
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
                        <label for="inputCargo">Cargo*</label>
                        <select id="inputCargo" class="custom-select" required>
                            <option value="">Selecione</option>
                            <option value="1">Diretor</option>
                            <option value="2">Gêrente Global</option>
                            <option value="3">Gerênte Regional</option>
                            <option value="4">Suporte Técnico</option>
                            <option value="5">Funcionário</option>
                        </select>
                        <div class="invalid-feedback">Escolha um Cargo Válido</div>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputLogin">Login:*</label>
                        <input type="text" class="form-control" id="inputNomeFunc" minlength="6" maxlength="15" placeholder="Login" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="inputSenha">Senha:*</label>
                        <input type="password" class="form-control" id="inputSenha" minlength="4" maxlength="8" placeholder="Digite sua Senha" required>
                    </div>
                </div>

                <div class="page-header"></div>
                <div class="row">
                    <div class="form-group">
                        <input type="submit" class="btn btn-success" id="inputSalvar" value="Salvar">
                    </div>
                    <div class="form-group col-auto">
                        <input type="reset" class="btn btn-secondary" id="inputCancelar" value="Cancelar">
                    </div>










                </div>





            </form>


        </div>
        <!-- jQuery (obrigatório para plugins JavaScript do Bootstrap) -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <!-- Inclui todos os plugins compilados (abaixo), ou inclua arquivos separadados se necessário -->
        <script src="bootstrap/js/bootstrap.min.js"></script>
    </body>
</html>
