package Senac.TadesGames.Servlet;

import Senac.TadesGames.DAO.UsuarioDAO;
import Senac.TadesGames.Models.UsuarioModel;
import Senac.TadesGames.Service.UsuarioService;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Giovanni.Carignato
 */
@WebServlet(name = "LoginControllerServlet", urlPatterns = {"/login"})
public class LoginControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        UsuarioDAO dao = new UsuarioDAO();
        UsuarioModel usuario = dao.obterPorLogin(login);
        if (usuario != null && usuario.validarSenha(senha)) {
            HttpSession sessao = request.getSession();
            sessao.setAttribute("usuario", usuario);
            response.sendRedirect("home.jsp");
        } else {
            request.setAttribute("msgErro", "Usuario ou senha inválido");
            request.getRequestDispatcher("login.jsp")
                    .forward(request, response);
        }

    }

}
