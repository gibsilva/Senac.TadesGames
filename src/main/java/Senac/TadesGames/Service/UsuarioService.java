package Senac.TadesGames.Service;

import Senac.TadesGames.DAO.UsuarioDAO;
import Senac.TadesGames.Helpers.GeradorSenha;
import Senac.TadesGames.Helpers.JavaMail;
import Senac.TadesGames.Helpers.Notificacao;
import Senac.TadesGames.Models.UsuarioModel;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 *
 * @author adrianne
 */
public class UsuarioService {

    private final UsuarioDAO usuarioDao;
    private final Notificacao notificacao;

    public UsuarioService() {
        this.usuarioDao = new UsuarioDAO();
        this.notificacao = new Notificacao();
    }

    private void validaEmailExistente(String email) {
        if (usuarioDao.obterPorEmail(email) != null) {
            this.notificacao.adicionaNotificacao("email", "Email já está cadastrado");
        }
    }

    private void validaEmailExistente(String email, int id) {
        if (usuarioDao.obterPorEmail(email, id) != null) {
            this.notificacao.adicionaNotificacao("email", "Email já cadastrado");
        }
    }

    private void validaCpfExistente(String cpf) {
        if (usuarioDao.obterPorCpf(cpf) != null) {
            this.notificacao.adicionaNotificacao("cpf", "Esse CPF já está cadastrado");
        }
    }

    private void validaLoginExistente(String login) {
        if (usuarioDao.obterPorLogin(login) != null) {
            this.notificacao.adicionaNotificacao("login", "Login já existente");
        }
    }

    private void validaLoginExistente(String login, int id) {
        if (usuarioDao.obterPorLogin(login, id) != null) {
            this.notificacao.adicionaNotificacao("login", "Login já existente");
        }
    }

    public void validaQuantidadeCaracteresCredenciais(String login, String senha) {
        if (login.length() > 15 || login.length() < 6) {
            this.notificacao.adicionaNotificacao("login", "Login deve ter a quantidade de caracteres entre 6 e 15");
        }

        if (senha.length() > 8 || senha.length() < 4) {
            this.notificacao.adicionaNotificacao("senha", "Senha deve ter a quantidade de caracteres entre 4 e 8");
        }
    }

    private boolean validarUsuarioInclusao(UsuarioModel usuario) {
        if (!validarCpf(usuario.getCpf())) {
            this.notificacao.adicionaNotificacao("cpf", "CPF inválido, por favor digite um CPF válido");
        }

        validaCpfExistente(usuario.getCpf());
        validaEmailExistente(usuario.getEmail());
        validaLoginExistente(usuario.getLogin());

        return this.notificacao.quantidadeNotificacoes() == 0;
    }

    private boolean validarUsuarioAlteracao(UsuarioModel usuario) {
        validaEmailExistente(usuario.getEmail(), usuario.getIdUsuario());
        validaLoginExistente(usuario.getLogin(), usuario.getIdUsuario());

        return this.notificacao.quantidadeNotificacoes() == 0;
    }

    public List<Notificacao> incluirUsuario(UsuarioModel usuario) throws Exception {
        try {
            if (validarUsuarioInclusao(usuario)) {
                usuarioDao.inserir(usuario);
            }
            return this.notificacao.listaNotificacoes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Notificacao> alterarUsuario(UsuarioModel usuario) throws Exception {
        try {
            if (validarUsuarioAlteracao(usuario)) {
                usuarioDao.alterar(usuario);
            }
            return this.notificacao.listaNotificacoes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Notificacao> alterarSenha(String senhaAtual, String novaSenha, String confirmaSenha, int idUsuario) {
        UsuarioModel usuario = usuarioDao.obterPorId(idUsuario);

        if (usuario != null && !usuario.validarSenha(senhaAtual)) {
            this.notificacao.adicionaNotificacao("senha", "A senha atual está incorreta");
        }

        if (!novaSenha.equals(confirmaSenha)) {
            this.notificacao.adicionaNotificacao("senha", "As senhas não conferem");
        }

        if (this.notificacao.listaNotificacoes().isEmpty()) {
            usuario.setSenhaEncriptada(novaSenha);
            usuarioDao.alterarSenha(usuario);
        }

        return this.notificacao.listaNotificacoes();
    }

    public void resetarSenha(UsuarioModel usuario) {
        String novaSenha = GeradorSenha.getRandomPassword();
        usuario.setSenhaEncriptada(novaSenha);
        usuarioDao.alterarSenha(usuario);
        JavaMail.enviarEmail(usuario, novaSenha);
    }

    public UsuarioModel obterUsuarioPorId(int id) {
        return usuarioDao.obterPorId(id);
    }

    public UsuarioModel obterUsuarioPorEmail(String email) {
        return usuarioDao.obterPorEmail(email);
    }

    public List<UsuarioModel> obterListaUsuarios() {
        return usuarioDao.obterTodas();
    }

    public List<UsuarioModel> obterTodosPorCargo(String cargo) {
        return usuarioDao.obterTodosPorCargo(cargo);
    }

    public void limparNotificacoes() {
        this.notificacao.limparLista();
    }

    private boolean validarCpf(String cpf) {
        // considera-se erro cpf's formados por uma sequencia de numeros iguais
        if (cpf.equals("00000000000")
                || cpf.equals("11111111111")
                || cpf.equals("22222222222") || cpf.equals("33333333333")
                || cpf.equals("44444444444") || cpf.equals("55555555555")
                || cpf.equals("66666666666") || cpf.equals("77777777777")
                || cpf.equals("88888888888") || cpf.equals("99999999999")
                || (cpf.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do cpf em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0         
                // (48 eh a posicao de '0' na tabela ASCII)         
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }
            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public UsuarioModel autenticar(String login, String senha) {
        return validarAutenticacao(login, senha);
    }

    private UsuarioModel validarAutenticacao(String login, String senha) {
        if (login.equals("") || senha.equals("")) {
            this.notificacao.adicionaNotificacao("LoginSenha", "Nome de usuário ou senha não podem ser vazios");
            return null;
        } else {
            UsuarioModel usuario = usuarioDao.obterPorLogin(login);
            if (usuario != null && usuario.validarSenha(senha)) {
                return usuario;
            } else {
                this.notificacao.adicionaNotificacao("Invalido", "Nome de usuário ou senha incorreto, verifique os dados digitados");
                return null;
            }
        }
    }

    public List<String> cargosPorSetor(String setor) {
        List<String> cargos = new ArrayList<>();
        switch (setor) {
            case "Diretoria":
                cargos.add("Diretor");
                break;
            case "Produtos/Serviços/Marketing":
                cargos.add("Gerente Global");
                cargos.add("Gerente Regional");
                cargos.add("Funcionário");
                break;
            case "Vendas":
                cargos.add("Gerente Global");
                cargos.add("Gerente Regional");
                cargos.add("Vendedor (a)");
                break;
            case "T.I":
                cargos.add("Gerente Global");
                cargos.add("Gerente Regional");
                cargos.add("Suporte Técnico");
                break;
        }
        return cargos;
    }

}
