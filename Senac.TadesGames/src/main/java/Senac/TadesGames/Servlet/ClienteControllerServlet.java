/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Servlet;

import Senac.TadesGames.Helpers.Utils;
import Senac.TadesGames.Models.ClienteModel;
import Senac.TadesGames.Service.ClienteService;
import java.io.IOException;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author gi
 */
@WebServlet("/Clientes")
public class ClienteControllerServlet extends HttpServlet {

    private final ClienteService service = new ClienteService();

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
        request.getRequestDispatcher("cadastroCliente.jsp").forward(request, response);
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
        Utils util = new Utils();
        String nome = util.removePontosBarraStr(request.getParameter("nome"));
        String cpf = util.removePontosBarraStr(request.getParameter("cpf"));
        String cnpj = util.removePontosBarraStr(request.getParameter("cnpj"));
        Date date = Utils.converteStrParaDate(request.getParameter("dataNasc"));
        String email = request.getParameter("email");
        String telefone = util.removePontosBarraStr(request.getParameter("telefone")).replace(" ", "");
        String celular = util.removePontosBarraStr(request.getParameter("celular")).replace(" ", "");
        String sexo = util.removePontosBarraStr(request.getParameter("sexo"));

        ClienteModel cliente = new ClienteModel(0, nome, cpf, cnpj, date, email, telefone, celular, sexo);

        try {
            service.incluirCliente(cliente);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/consultaCliente.jsp");
            dispatcher.forward(request, response);
        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
            dispatcher.forward(request, response);
        }
    }

}
