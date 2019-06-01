package Senac.TadesGames.Servlet;

import Senac.TadesGames.Helpers.Notificacao;
import Senac.TadesGames.Helpers.Utils;
import Senac.TadesGames.Models.UsuarioModel;
import Senac.TadesGames.Service.FilialService;
import Senac.TadesGames.Service.UsuarioService;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author adrianne
 */
@WebServlet(name = "UsuarioControllerServlet", urlPatterns = {"/autenticado/Usuarios"})
public class UsuarioControllerServlet extends HttpServlet {

    private final UsuarioService usuarioService = new UsuarioService();
    private final FilialService filialService = new FilialService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String acao = request.getParameter("acao");

            if (acao == null || acao.equals("")) {
                acao = "listar";
            }

            switch (acao) {
                case "listar":
                    listarUsuarios(request, response);
                    break;
                case "alterar":
                    carregarUsuario(request, response);
                    break;
                case "salvar":
                    criarUsuario(request, response);
                    break;
                case "alterarSenha":
                    alterarSenha(request, response);
                    break;
                case "cargosPorSetor":
                    cargosPorSetor(request, response);
                    break;
                default:
                    listarUsuarios(request, response);
            }
        } catch (ServletException | IOException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String acao = request.getParameter("acao");

            switch (acao) {
                case "salvar":
                    incluirUsuario(request, response);
                    break;
                case "alterar":
                    alterarUsuario(request, response);
                    break;
                case "alterarSenha":
                    alterarSenhaPost(request, response);
                    break;
            }
        } catch (ServletException | IOException e) {
            throw new ServletException(e);
        }
    }

    protected void incluirUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Utils util = new Utils();
        String nome = request.getParameter("nome");
        String cpf = util.removePontosBarraStr(request.getParameter("cpf"));
        String email = request.getParameter("email");
        String sexo = request.getParameter("sexo");
        int idFilial = Integer.parseInt(request.getParameter("filial"));
        String setor = request.getParameter("setor");
        String cargo = request.getParameter("cargo");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        usuarioService.validaQuantidadeCaracteresCredenciais(login, senha);

        UsuarioModel usuario = new UsuarioModel(0, nome, cpf, email, setor, cargo, login, senha, idFilial, sexo, true);

        try {
            List<Notificacao> notificacoes = usuarioService.incluirUsuario(usuario);
            if (notificacoes.isEmpty()) {
                request.setAttribute("statusSalvo", true);
                listarUsuarios(request, response);
            } else {
                request.setAttribute("notificacoes", notificacoes);
                request.setAttribute("filiais", filialService.obterListaFiliaisAtivas());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/autenticado/cadastroUsuario.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
            dispatcher.forward(request, response);
        } finally {
            usuarioService.limparNotificacoes();
        }
    }

    protected void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<UsuarioModel> usuarios = usuarioService.obterListaUsuarios();
        request.setAttribute("usuarios", usuarios);
        request.getRequestDispatcher("/WEB-INF/jsp/autenticado/consultaUsuario.jsp").forward(request, response);
    }

    protected void alterarSenha(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/autenticado/alterarSenha.jsp").forward(request, response);
    }

    protected void alterarSenhaPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String senhaAtual = request.getParameter("senhaAtual");
        String novaSenha = request.getParameter("novaSenha");
        String confirmaSenha = request.getParameter("confirmaSenha");
        int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));

        try {
            List<Notificacao> notificacoes = usuarioService.alterarSenha(senhaAtual, novaSenha, confirmaSenha, idUsuario);
            if (notificacoes.isEmpty()) {
                request.setAttribute("statusAlterado", true);
                HttpSession sessao = request.getSession();
                sessao.invalidate();
                response.sendRedirect("WEB-INF/jsp/login.jsp");
            } else {
                request.setAttribute("notificacoes", notificacoes);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/autenticado/alterarSenha.jsp");
                dispatcher.forward(request, response);
            }
        } catch (IOException | ServletException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/erro.jsp");
            dispatcher.forward(request, response);
        } finally {
            this.usuarioService.limparNotificacoes();
        }
    }

    protected void carregarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idUsuario"));
        UsuarioModel usuario = usuarioService.obterUsuarioPorId(id);

        request.setAttribute("usuario", usuario);
        request.setAttribute("filiais", filialService.obterListaFiliaisAtivas());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/autenticado/alterarUsuario.jsp");
        dispatcher.forward(request, response);
    }

    protected void criarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("filiais", filialService.obterListaFiliaisAtivas());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/autenticado/cadastroUsuario.jsp");
        dispatcher.forward(request, response);
    }

    protected void cargosPorSetor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String setor = request.getParameter("setor");

        try (PrintWriter out = response.getWriter()) {
            List<String> cargos = usuarioService.cargosPorSetor(setor);
            Gson gson = new Gson();
            out.print(gson.toJson(cargos));
            out.flush();
        }
    }

    protected void alterarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Utils util = new Utils();

        int id = Integer.parseInt(request.getParameter("idUsuario"));
        String nome = request.getParameter("nome");
        String cpf = util.removePontosBarraStr(request.getParameter("cpf"));
        String email = request.getParameter("email");
        String sexo = request.getParameter("sexo");
        int idFilial = Integer.parseInt(request.getParameter("filial"));
        String setor = request.getParameter("setor");
        String cargo = request.getParameter("cargo");
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        boolean ativo = Boolean.parseBoolean(request.getParameter("ativo"));

        UsuarioModel usuario = new UsuarioModel(id, nome, cpf, email, setor, cargo, idFilial, sexo, ativo);

        try {
            List<Notificacao> notificacoes = usuarioService.alterarUsuario(usuario);
            if (notificacoes.isEmpty()) {
                request.setAttribute("statusAlterado", true);
                listarUsuarios(request, response);
            } else {
                request.setAttribute("notificacoes", notificacoes);
                request.setAttribute("usuario", usuario);
                request.setAttribute("filiais", filialService.obterListaFiliaisAtivas());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/autenticado/alterarUsuario.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/erro.jsp");
            dispatcher.forward(request, response);
        } finally {
            usuarioService.limparNotificacoes();
        }
    }
}
