/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Servlet;

import Senac.TadesGames.Helpers.Notificacao;
import Senac.TadesGames.Models.GeneroModel;
import Senac.TadesGames.Service.GeneroService;
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
 * @author Marcel
 */
@WebServlet("/Generos")
public class GeneroControllerServlet extends HttpServlet {

    private final GeneroService service = new GeneroService();

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
                    listarGenero(request, response);
                    break;
                case "alterar":
                    carregarGenero(request, response);
                    break;
                default:
                    listarGenero(request, response);
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
                    incluirGenero(request, response);
                    break;
                case "alterar":
                    alterarGenero(request, response);
                    break;
                case "excluir":
                    excluirGenero(request, response);
                    break;
            }

        } catch (ServletException | IOException e) {
            throw new ServletException(e);
        }

    }

    protected void incluirGenero(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");

        GeneroModel genero = new GeneroModel(0, nome);

        try {
            List<Notificacao> notificacoes = service.incluirGenero(genero);
            if (notificacoes.isEmpty()) {
                request.setAttribute("generos", service.obterListaGenero());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/consultaGenero.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("notificacoes", notificacoes);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroGenero.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
            dispatcher.forward(request, response);
        } finally {
            service.limparNotificacoes();
        }
    }

    protected void listarGenero(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("generos", service.obterListaGenero());
        request.getRequestDispatcher("consultaGenero.jsp").forward(request, response);
    }

    protected void carregarGenero(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idGenero"));
        GeneroModel genero = service.obterGeneroPorId(id);

        request.setAttribute("genero", genero);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/alterarGenero.jsp");
        dispatcher.forward(request, response);
    }

    protected void alterarGenero(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("idGenero"));
        String nome = request.getParameter("nome");

        GeneroModel genero = new GeneroModel(id, nome);

        try {
            List<Notificacao> notificacoes = service.alterarGenero(genero);
            if (notificacoes.isEmpty()) {
                request.setAttribute("generos", service.obterListaGenero());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/consultaGenero.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("notificacoes", notificacoes);
                request.setAttribute("genero", genero);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/alterarGenero.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
            dispatcher.forward(request, response);
        } finally {
            service.limparNotificacoes();
        }
    }

    protected void excluirGenero(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idGenero"));
        GeneroModel genero = service.obterGeneroPorId(id);

        service.excluirGenero(genero);

        request.setAttribute("generos", service.obterListaGenero());
        request.getRequestDispatcher("consultaGenero.jsp").forward(request, response);
    }

}
