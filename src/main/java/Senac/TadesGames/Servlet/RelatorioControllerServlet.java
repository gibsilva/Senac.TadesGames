/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Servlet;

import Senac.TadesGames.Helpers.Utils;
import Senac.TadesGames.Models.RelatorioClienteModel;
import Senac.TadesGames.Models.RelatorioProdutoModel;
import Senac.TadesGames.Models.RelatorioVendasModel;
import Senac.TadesGames.Service.RelatorioService;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Giovanni.Carignato
 */
@WebServlet(name = "RelatorioControllerServlet", urlPatterns = {"/autenticado/Relatorios"})
public class RelatorioControllerServlet extends HttpServlet {

    private final RelatorioService relatorioService;

    public RelatorioControllerServlet() {
        this.relatorioService = new RelatorioService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String acao = request.getParameter("acao");

            switch (acao) {
                case "vendas":
                    relatorioVendas(request, response);
                    break;
                case "relatorioVendas":
                    gerarRelatorioVendas(request, response);
                    break;
                case "produtos":
                    relatorioProduto(request, response);
                    break;
                case "relatorioProdutos":
                    gerarRelatorioProdutos(request, response);
                    break;
                case "clientes":
                    relatorioCliente(request, response);
                    break;
                case "relatorioClientes":
                    gerarRelatorioClientes(request, response);
                    break;
            }
        } catch (IOException e) {
            throw new ServletException(e);

        } catch (ParseException ex) {
            Logger.getLogger(RelatorioControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void relatorioVendas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/autenticado/relatorioVendas.jsp").forward(request, response);
    }

    protected void relatorioProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/autenticado/relatorioProduto.jsp").forward(request, response);
    }

    protected void relatorioCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/autenticado/relatorioCliente.jsp").forward(request, response);
    }

    protected void gerarRelatorioVendas(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Gson gson = new Gson();
            Date dataInicio = Utils.converteStrParaDate(request.getParameter("dataInicio"));
            Date dataFim = Utils.converteStrParaDate(request.getParameter("dataFim"));

            List<RelatorioVendasModel> lista = relatorioService.obterPorDataRelatorioVendas(dataInicio, dataFim);
            out.print(gson.toJson(lista));
            out.flush();
        }
    }

    protected void gerarRelatorioProdutos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Gson gson = new Gson();
            Date dataInicio = Utils.converteStrParaDate(request.getParameter("dataInicio"));
            Date dataFim = Utils.converteStrParaDate(request.getParameter("dataFim"));

            List<RelatorioProdutoModel> lista = relatorioService.obterPorDataRelatorioProduto(dataInicio, dataFim);
            out.print(gson.toJson(lista));
            out.flush();
        }
    }

    protected void gerarRelatorioClientes(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Gson gson = new Gson();
            Date dataInicio = Utils.converteStrParaDate(request.getParameter("dataInicio"));
            Date dataFim = Utils.converteStrParaDate(request.getParameter("dataFim"));

            List<RelatorioClienteModel> lista = relatorioService.obterPorDataRelatorioCliente(dataInicio, dataFim);
            out.print(gson.toJson(lista));
            out.flush();
        }
    }
}
