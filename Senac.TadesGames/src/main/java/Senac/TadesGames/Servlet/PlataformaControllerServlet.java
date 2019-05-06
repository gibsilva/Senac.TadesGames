/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Servlet;

import Senac.TadesGames.Helpers.Notificacao;
import Senac.TadesGames.Helpers.Utils;
import Senac.TadesGames.Models.PlataformaModel;
import Senac.TadesGames.Service.PlataformaService;
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
 * @author Marcel
 */

@WebServlet("/Plataformas")
public class PlataformaControllerServlet extends HttpServlet {

    private final PlataformaService service = new PlataformaService();

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
                    listarPlataformas(request, response);
                    break;
                case "alterar":
                    carregarPlataforma(request, response);
                    break;
                default:
                    listarPlataformas(request, response);
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
                    incluirPlataforma(request, response);
                    break;
//                case "alterar":
//                    alterarPlataforma(request, response);
//                    break;
            }
        } catch (ServletException | IOException e) {
            throw new ServletException(e);
        }
    }

    protected void incluirPlataforma(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Utils util = new Utils();
        String nome = request.getParameter("nome");

        PlataformaModel plataforma = new PlataformaModel(0, nome);

        try {
            List<Notificacao> notificacoes = service.incluirPlataforma(plataforma);
            if (notificacoes.isEmpty()) {
                request.setAttribute("plataforma", service.obterListaPlataformas());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/consultaPlataforma.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("notificacoes", notificacoes);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroPlataforma.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
            dispatcher.forward(request, response);
        } finally {
            service.limparNotificacoes();
        }
    }

    protected void listarPlataformas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("plataforma", service.obterListaPlataformas());
        request.getRequestDispatcher("consultaPlataforma.jsp").forward(request, response);
    }

    protected void carregarPlataforma(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idPlataforma"));
        PlataformaModel plataforma = service.obterPorId(id);

        request.setAttribute("plataforma", plataforma);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/alterarPlataforma.jsp");
        dispatcher.forward(request, response);
    }

    protected void alterarPlataforma(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        Utils util = new Utils();
        int id = Integer.parseInt(request.getParameter("idPlataforma"));
        String nome = request.getParameter("nome");

        PlataformaModel plataforma = new PlataformaModel(id, nome);

        try {
            List<Notificacao> notificacoes = service.alterarPlataforma(plataforma);
            if (notificacoes.isEmpty()) {
                request.setAttribute("plataformas", service.obterListaPlataformas());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/consultaPlataforma.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("notificacoes", notificacoes);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/alterarPlataforma.jsp");
                dispatcher.forward(request, response);
            }

        } catch (ServletException | IOException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
            dispatcher.forward(request, response);
        } finally {
            service.limparNotificacoes();
        }
    }
}
