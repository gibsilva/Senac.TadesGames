/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Servlet;

import Senac.TadesGames.Helpers.Notificacao;
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
@WebServlet(name = "PlataformaControllerServlet", urlPatterns = {"/Plataformas"})
public class PlataformaControllerServlet extends HttpServlet {

    private final PlataformaService service = new PlataformaService();

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
                    listarPlataforma(request, response);
                    break;
                case "alterar":
                    carregarPlataforma(request, response);
                    break;
                case "salvar":
                    criarPlataforma(request, response);
                    break;
                default:
                    listarPlataforma(request, response);
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
                case "alterar":
                    alterarPlataforma(request, response);
                    break;
                case "excluir":
                    excluirPlataforma(request, response);
                    break;
            }
        } catch (ServletException | IOException e) {
            throw new ServletException(e);
        }

    }

    protected void criarPlataforma(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/cadastroPlataforma.jsp").forward(request, response);
    }

    protected void incluirPlataforma(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");

        PlataformaModel plataforma = new PlataformaModel(0, nome);

        try {
            List<Notificacao> notificacoes = service.incluirPlataforma(plataforma);
            if (notificacoes.isEmpty()) {
                request.setAttribute("statusSalvo", true);
                listarPlataforma(request, response);
            } else {
                request.setAttribute("notificacoes", notificacoes);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/cadastroPlataforma.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
            dispatcher.forward(request, response);
        } finally {
            service.limparNotificacoes();
        }
    }

    protected void listarPlataforma(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("plataformas", service.obterListaPlataforma());
        request.getRequestDispatcher("/WEB-INF/jsp/consultaPlataforma.jsp").forward(request, response);
    }

    protected void carregarPlataforma(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idPlataforma"));
        PlataformaModel plataforma = service.obterPlataformaPorId(id);

        request.setAttribute("plataforma", plataforma);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/alterarPlataforma.jsp");
        dispatcher.forward(request, response);
    }

    protected void alterarPlataforma(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("idPlataforma"));
        String nome = request.getParameter("nome");

        PlataformaModel plataforma = new PlataformaModel(id, nome);

        try {
            List<Notificacao> notificacoes = service.alterarPlataforma(plataforma);
            if (notificacoes.isEmpty()) {
                request.setAttribute("statusAlterado", true);
                listarPlataforma(request, response);
            } else {
                request.setAttribute("notificacoes", notificacoes);
                request.setAttribute("plataforma", plataforma);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/alterarPlataforma.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
            dispatcher.forward(request, response);
        } finally {
            service.limparNotificacoes();
        }
    }

    protected void excluirPlataforma(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idPlataforma"));
        PlataformaModel plataforma = service.obterPlataformaPorId(id);

        service.excluirPlataforma(plataforma);

        request.setAttribute("plataformas", service.obterListaPlataforma());
        request.getRequestDispatcher("/WEB-INF/jsp/consultaPlataforma.jsp").forward(request, response);
    }

}
