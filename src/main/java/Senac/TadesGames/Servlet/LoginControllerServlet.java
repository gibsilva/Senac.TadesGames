/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Servlet;

import Senac.TadesGames.Models.UsuarioModel;
import Senac.TadesGames.Service.UsuarioService;
import java.io.IOException;
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
@WebServlet(name = "LoginControllerServlet", urlPatterns = {"/Login"})
public class LoginControllerServlet extends HttpServlet {

    private final UsuarioService usuarioService;

    public LoginControllerServlet() {
        this.usuarioService = new UsuarioService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        UsuarioModel usuario = usuarioService.autenticar(login, senha);
        if (usuario != null) {
            request.getSession().setAttribute("usuarioLogado", usuario);
            response.sendRedirect("autenticado/Home");
            return;
        } else {
            request.getSession().invalidate();
            request.setAttribute("erro", "Nome de usuário ou senha incorretos");
        }

        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/login.jsp");
        rd.forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
