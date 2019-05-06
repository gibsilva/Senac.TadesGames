/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Servlet;

import Senac.TadesGames.Helpers.Notificacao;
import Senac.TadesGames.Helpers.Utils;
import Senac.TadesGames.Models.CategoriaModel;
import Senac.TadesGames.Service.CategoriaService;
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
@WebServlet("/Categorias")
public class CategoriaControllerServlet extends HttpServlet {

    private final CategoriaService service = new CategoriaService();

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
                    listarCategorias(request, response);
                    break;
                case "alterar":
                    carregarCategoria(request, response);
                    break;
                default:
                    listarCategorias(request, response);
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
                    incluirCategoria(request, response);
                    break;
//                case "alterar":
//                    alterarCategoria(request, response);
//                    break;
            }
        } catch (ServletException | IOException e) {
            throw new ServletException(e);
        }
    }

    protected void incluirCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Utils util = new Utils();
        String nome = request.getParameter("nome");

        CategoriaModel categoria = new CategoriaModel(0, nome);

        try {
            List<Notificacao> notificacoes = service.incluirCategoria(categoria);
            if (notificacoes.isEmpty()) {
                request.setAttribute("categorias", service.obterListaCategorias());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/consultaCategoria.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("notificacoes", notificacoes);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroCategoria.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
            dispatcher.forward(request, response);
        } finally {
            service.limparNotificacoes();
        }
    }

    protected void listarCategorias(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("categorias", service.obterListaCategorias());
        request.getRequestDispatcher("consultaCategoria.jsp").forward(request, response);
    }

    protected void carregarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idCategoria"));
        CategoriaModel categoria = service.obterPorId(id);

        request.setAttribute("categoria", categoria);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/alterarCategoria.jsp");
        dispatcher.forward(request, response);
    }

    protected void alterarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        Utils util = new Utils();
        int id = Integer.parseInt(request.getParameter("idCategoria"));
        String nome = request.getParameter("nome");

        CategoriaModel categoria = new CategoriaModel(id, nome);

        try {
            List<Notificacao> notificacoes = service.alterarCategoria(categoria);
            if (notificacoes.isEmpty()) {
                request.setAttribute("cateogiras", service.obterListaCategorias());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/consultaCategoria.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("notificacoes", notificacoes);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/alterarCategoria.jsp");
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
