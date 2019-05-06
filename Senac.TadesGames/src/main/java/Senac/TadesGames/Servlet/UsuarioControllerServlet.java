package Senac.TadesGames.Servlet;

import Senac.TadesGames.Helpers.Notificacao;
import Senac.TadesGames.Helpers.Utils;
import Senac.TadesGames.Models.UsuarioModel;
import Senac.TadesGames.Service.FilialService;
import Senac.TadesGames.Service.UsuarioService;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author adrianne
 */
@WebServlet("/Usuarios")
public class UsuarioControllerServlet extends HttpServlet {

    private final UsuarioService usuarioService = new UsuarioService();
    private final FilialService filialService = new FilialService();

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
                    carregarInclusao(request, response);
                    break;
                default:
                    listarUsuarios(request, response);
            }
        } catch (ServletException | IOException e) {
            throw new ServletException(e);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

        UsuarioModel usuario = new UsuarioModel(0, nome, cpf, email, setor, cargo, login, senha, idFilial, sexo, true);

        try {
            List<Notificacao> notificacoes = usuarioService.incluirUsuario(usuario);
            if (notificacoes.isEmpty()) {
                request.setAttribute("usuarios", usuarioService.obterListaUsuarios());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/consultaUsuario.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("notificacoes", notificacoes);
                request.setAttribute("filiais", filialService.obterListaFiliais());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroUsuario.jsp");
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
        request.getRequestDispatcher("consultaUsuario.jsp").forward(request, response);
    }

    protected void carregarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idUsuario"));
        UsuarioModel usuario = usuarioService.obterUsuarioPorId(id);

        request.setAttribute("usuario", usuario);
        request.setAttribute("filiais", filialService.obterListaFiliais());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/alterarUsuario.jsp");
        dispatcher.forward(request, response);
    }

    protected void carregarInclusao(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("filiais", filialService.obterListaFiliais());
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroUsuario.jsp");
        dispatcher.forward(request, response);
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

        UsuarioModel usuario = new UsuarioModel(id, nome, cpf, email, setor, cargo, login, senha, idFilial, sexo, ativo);

        try {
            List<Notificacao> notificacoes = usuarioService.alterarUsuario(usuario);
            if (notificacoes.isEmpty()) {
                request.setAttribute("usuarios", usuarioService.obterListaUsuarios());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/consultaUsuario.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("notificacoes", notificacoes);
                request.setAttribute("usuario", usuario);
                request.setAttribute("filiais", filialService.obterListaFiliais());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/alterarUsuario.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
            dispatcher.forward(request, response);
        } finally {
            usuarioService.limparNotificacoes();
        }
    }
}
