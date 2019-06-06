/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.servlets;

import br.com.senac.tadesgames.models.UsuarioModel;
import br.com.senac.tadesgames.services.UsuarioService;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gi
 */
@WebServlet(name = "ResetSenhaControllerServlet", urlPatterns = {"/ResetSenha"})
public class ResetSenhaControllerServlet extends HttpServlet {

    private final UsuarioService usuarioService;

    public ResetSenhaControllerServlet() {
        this.usuarioService = new UsuarioService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/resetSenha.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        String email = request.getParameter("email");
        UsuarioModel usuario = usuarioService.obterUsuarioPorEmail(email);
        
        if (usuario != null) {
            usuarioService.resetarSenha(usuario);
        }
        
        out.print(gson.toJson(usuario));
        out.flush();
        out.close();
    }
}