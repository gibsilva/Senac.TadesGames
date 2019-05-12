package Senac.TadesGames.Servlet;

import Senac.TadesGames.DAO.UsuarioDAO;
import Senac.TadesGames.Service.UsuarioService;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Giovanni.Carignato
 */
@WebServlet(name = "doLogin", urlPatterns = {"/doLogin"})
public class LoginControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher
                = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        request.setAttribute("metodoHttp", "POST");
        request.setAttribute("login", login);
        request.setAttribute("senha", senha);

        boolean condicao = UsuarioService.validarLogin(login, senha);

        request.setAttribute("doLogin", condicao);

        if (condicao == true) {
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("home.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("msgErro", "Usuário ou senha inválido!");
            RequestDispatcher dispatcher
                    = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }

    }

}
