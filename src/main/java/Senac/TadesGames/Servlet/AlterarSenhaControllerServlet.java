/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Servlet;

import Senac.TadesGames.Helpers.Notificacao;
import Senac.TadesGames.Service.UsuarioService;
import java.io.IOException;
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
 * @author Gi
 */
@WebServlet(name = "AlterarSenhaControllerServlet", urlPatterns = {"/autenticado/alterar-senha"})
public class AlterarSenhaControllerServlet extends HttpServlet {
    
    private final UsuarioService usuarioService;
    
    public AlterarSenhaControllerServlet(){
        this.usuarioService = new UsuarioService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/autenticado/alterarSenha.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
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
                RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/autenticado/alterarSenha.jsp");
                dispatcher.forward(request, response);
            }
        } catch (IOException | ServletException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/erro.jsp");
            dispatcher.forward(request, response);
        } finally {
            this.usuarioService.limparNotificacoes();
        }
    }

}
