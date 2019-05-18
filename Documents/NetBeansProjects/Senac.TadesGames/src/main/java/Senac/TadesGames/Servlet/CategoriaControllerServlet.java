/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Servlet;

import Senac.TadesGames.Helpers.Notificacao;
import Senac.TadesGames.Models.CategoriaModel;
import Senac.TadesGames.Service.CategoriaService;
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

/**
 *
 * @author Gi
 */
@WebServlet(name = "CategoriaControllerServlet", urlPatterns = {"/Categorias"})
public class CategoriaControllerServlet extends HttpServlet {

    private final CategoriaService service = new CategoriaService();

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
                    listarCategoria(request, response);
                    break;
                case "alterar":
                    carregarCategoria(request, response);
                    break;
                case "salvar":
                    criarCategoria(request, response);
                    break;
                default:
                    listarCategoria(request, response);
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
                case "alterar":
                    alterarCategoria(request, response);
                    break;
                case "excluir":
                    excluirCategoria(request, response);
                    break;
            }
        } catch (ServletException | IOException e) {
            throw new ServletException(e);
        }

    }

    protected void criarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/cadastroCategoria.jsp").forward(request, response);
    }

    protected void incluirCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");

        CategoriaModel categoria = new CategoriaModel(0, nome);

        try {
            List<Notificacao> notificacoes = service.incluirCategoria(categoria);
            if (notificacoes.isEmpty()) {
                request.setAttribute("statusSalvo", true);
                listarCategoria(request, response);
            } else {
                request.setAttribute("notificacoes", notificacoes);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cadastroCategoria.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
            dispatcher.forward(request, response);
        } finally {
            service.limparNotificacoes();
        }
    }

    protected void listarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("categorias", service.obterListaCategoria());
        request.getRequestDispatcher("/WEB-INF/jsp/consultaCategoria.jsp").forward(request, response);
    }

    protected void carregarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idCategoria"));
        CategoriaModel categoria = service.obterCategoriaPorId(id);

        request.setAttribute("categoria", categoria);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/alterarCategoria.jsp");
        dispatcher.forward(request, response);
    }

    protected void excluirCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();

        int id = Integer.parseInt(request.getParameter("idCategoria"));
        CategoriaModel categoria = service.obterCategoriaPorId(id);

        try {
            service.excluirCategoria(categoria);
            out.print(gson.toJson(categoria));
            out.flush();
            out.close();
        } catch (Exception e) {
            out.print(gson.toJson(categoria));
            out.flush();
            out.close();
        }
    }

    protected void alterarCategoria(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("idCategoria"));
        String nome = request.getParameter("nome");

        CategoriaModel categoria = new CategoriaModel(id, nome);

        try {
            List<Notificacao> notificacoes = service.alterarCategoria(categoria);
            if (notificacoes.isEmpty()) {
                request.setAttribute("statusAlterado", true);
                listarCategoria(request, response);
            } else {
                request.setAttribute("notificacoes", notificacoes);
                request.setAttribute("categoria", categoria);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/alterarCategoria.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
            dispatcher.forward(request, response);
        } finally {
            service.limparNotificacoes();
        }
    }

}
