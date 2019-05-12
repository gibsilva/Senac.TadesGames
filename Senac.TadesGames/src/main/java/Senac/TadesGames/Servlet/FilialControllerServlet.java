/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Servlet;

import Senac.TadesGames.Helpers.Notificacao;
import Senac.TadesGames.Helpers.Utils;
import Senac.TadesGames.Models.FilialModel;
import Senac.TadesGames.Service.FilialService;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Gih
 */
@WebServlet("/Filiais")
public class FilialControllerServlet extends HttpServlet {

    private final FilialService service = new FilialService();

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
                    listarFiliais(request, response);
                    break;
                case "alterar":
                    carregarFilial(request, response);
                    break;
                case "salvar":
                    criarFilial(request, response);
                    break;
                default:
                    listarFiliais(request, response);
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
                    incluirFilial(request, response);
                    break;
                case "alterar":
                    alterarFilial(request, response);
                    break;
            }
        } catch (ServletException | IOException e) {
            throw new ServletException(e);
        } catch (Exception ex) {
            Logger.getLogger(FilialControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void criarFilial(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        request.getRequestDispatcher("cadastroFilial.jsp").forward(request, response);
    }   

    protected void incluirFilial(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        Utils util = new Utils();
        String nome = request.getParameter("nome");
        String cnpj = util.removePontosBarraStr(request.getParameter("cnpj"));
        String cep = util.removePontosBarraStr(request.getParameter("cep"));
        String logradouro = request.getParameter("logradouro");
        int numero = Integer.parseInt(request.getParameter("numero"));
        String complemento = request.getParameter("complemento");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("uf");

        FilialModel filial = new FilialModel(0, cnpj, nome, cep, logradouro, numero, complemento, bairro, cidade, estado);

        try {
            List<Notificacao> notificacoes = service.incluirFilial(filial);
            if (notificacoes.isEmpty()) {
                request.setAttribute("statusSalvo", true);
                listarFiliais(request, response);
            } else {
                request.setAttribute("notificacoes", notificacoes);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroFilial.jsp");
                dispatcher.forward(request, response);
            }

        } catch (ServletException | IOException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
            dispatcher.forward(request, response);
        } finally {
            service.limparNotificacoes();
        }
    }

    protected void listarFiliais(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("filiais", service.obterListaFiliais());
        request.getRequestDispatcher("consultaFilial.jsp").forward(request, response);
    }

    protected void alterarFilial(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        Utils util = new Utils();
        int id = Integer.parseInt(request.getParameter("idFilial"));
        String nome = request.getParameter("nome");
        String cnpj = util.removePontosBarraStr(request.getParameter("cnpj"));
        String cep = util.removePontosBarraStr(request.getParameter("cep"));
        String logradouro = request.getParameter("logradouro");
        int numero = Integer.parseInt(request.getParameter("numero"));
        String complemento = request.getParameter("complemento");
        String bairro = request.getParameter("bairro");
        String cidade = request.getParameter("cidade");
        String estado = request.getParameter("uf");

        FilialModel filial = new FilialModel(id, cnpj, nome, cep, logradouro, numero, complemento, bairro, cidade, estado);

        try {
            List<Notificacao> notificacoes = service.alterarFilial(filial);
            if (notificacoes.isEmpty()) {
                request.setAttribute("statusAlterado", true);
                listarFiliais(request, response);
            } else {
                request.setAttribute("notificacoes", notificacoes);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/alterarFilial.jsp");
                dispatcher.forward(request, response);
            }

        } catch (ServletException | IOException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
            dispatcher.forward(request, response);
        } finally {
            service.limparNotificacoes();
        }
    }

    protected void carregarFilial(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idFilial"));
        FilialModel filial = service.obterPorId(id);

        request.setAttribute("filial", filial);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/alterarFilial.jsp");
        dispatcher.forward(request, response);
    }

}
