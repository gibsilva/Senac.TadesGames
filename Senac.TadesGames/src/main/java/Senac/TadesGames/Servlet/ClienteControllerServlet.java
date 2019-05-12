/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Servlet;

import Senac.TadesGames.Helpers.Notificacao;
import Senac.TadesGames.Helpers.Utils;
import Senac.TadesGames.Models.ClienteModel;
import Senac.TadesGames.Service.ClienteService;
import java.io.IOException;
import java.util.Date;
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
@WebServlet(name = "ClienteControllerServlet", urlPatterns = {"/Clientes"})
public class ClienteControllerServlet extends HttpServlet {

    private final ClienteService service = new ClienteService();

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
                    listarClientes(request, response);
                    break;
                case "alterar":
                    carregarCliente(request, response);
                    break;
                case "salvar":
                    criarCliente(request, response);
                    break;
                default:
                    listarClientes(request, response);
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
                    incluirCliente(request, response);
                    break;
                case "alterar":
                    alterarCliente(request, response);
                    break;
            }
        } catch (ServletException | IOException e) {
            throw new ServletException(e);
        }
    }
    
    protected void criarCliente(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException{
        request.getRequestDispatcher("cadastroCliente.jsp").forward(request, response);
    }

    protected void incluirCliente(HttpServletRequest request, HttpServletResponse response)
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

        ClienteModel cliente = new ClienteModel(0, nome, cpf, cnpj, date, email, telefone, celular, sexo, true);

        try {
            List<Notificacao> notificacoes = service.incluirCliente(cliente);
            if (notificacoes.isEmpty()) {
                request.setAttribute("clientes", service.obterListaClientes());
                RequestDispatcher dispatcher = request.getRequestDispatcher("/consultaCliente.jsp");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("notificacoes", notificacoes);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroCliente.jsp");
                dispatcher.forward(request, response);
            }

        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
            dispatcher.forward(request, response);
        } finally {
            service.limparNotificacoes();
        }
    }

    protected void listarClientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("clientes", service.obterListaClientes());
        request.getRequestDispatcher("consultaCliente.jsp").forward(request, response);
    }

    protected void carregarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idCliente"));
        ClienteModel cliente = service.obterClientePorId(id);

        request.setAttribute("cliente", cliente);
        request.setAttribute("dataNascFormatada", Utils.converteDateParaStrBR(cliente.getDataNasc()));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/alterarCliente.jsp");
        dispatcher.forward(request, response);
    }

    protected void alterarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Utils util = new Utils();

        int id = Integer.parseInt(request.getParameter("idCliente"));
        String nome = util.removePontosBarraStr(request.getParameter("nome"));
        String cpf = util.removePontosBarraStr(request.getParameter("cpf"));
        String cnpj = util.removePontosBarraStr(request.getParameter("cnpj"));
        Date date = Utils.converteStrParaDate(request.getParameter("dataNasc"));
        String email = request.getParameter("email");
        String telefone = util.removePontosBarraStr(request.getParameter("telefone")).replace(" ", "");
        String celular = util.removePontosBarraStr(request.getParameter("celular")).replace(" ", "");
        String sexo = request.getParameter("sexo");
        boolean ativo = Boolean.parseBoolean(request.getParameter("ativo"));

        ClienteModel cliente = new ClienteModel(id, nome, cpf, cnpj, date, email, telefone, celular, sexo, ativo);

        try {
            List<Notificacao> notificacoes = service.alterarCliente(cliente);
            if (notificacoes.isEmpty()) {
                request.setAttribute("statusOk", true);
                listarClientes(request, response);
            } else {
                request.setAttribute("notificacoes", notificacoes);
                request.setAttribute("cliente", cliente);
                request.setAttribute("dataNascFormatada", Utils.converteDateParaStrBR(cliente.getDataNasc()));
                RequestDispatcher dispatcher = request.getRequestDispatcher("/alterarCliente.jsp");
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
