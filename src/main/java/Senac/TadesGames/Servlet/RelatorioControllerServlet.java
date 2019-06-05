/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Servlet;

import Senac.TadesGames.Helpers.Utils;
import Senac.TadesGames.Models.FilialModel;
import Senac.TadesGames.Models.GraficoMelhoresVendedoresModel;
import Senac.TadesGames.Models.GraficoProdutosModel;
import Senac.TadesGames.Models.GraficoVendasFilialModel;
import Senac.TadesGames.Models.RelatorioClienteModel;
import Senac.TadesGames.Models.RelatorioProdutoModel;
import Senac.TadesGames.Models.RelatorioVendasModel;
import Senac.TadesGames.Models.UsuarioModel;
import Senac.TadesGames.Service.FilialService;
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
 * @author Gi
 */
@WebServlet(name = "RelatorioControllerServlet", urlPatterns = {"/autenticado/Relatorios"})
public class RelatorioControllerServlet extends HttpServlet {

    private final RelatorioService relatorioService;
    private final FilialService filialService;

    public RelatorioControllerServlet() {
        this.relatorioService = new RelatorioService();
        this.filialService = new FilialService();
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
                case "graficos":
                    graficos(request, response);
                    break;
                case "gerarGraficos":
                    gerarGraficos(request, response);
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
        List<FilialModel> filiais = null;
        UsuarioModel usuario = (UsuarioModel) request.getSession().getAttribute("usuarioLogado");
        if (usuario.getCargo().equals("Gerente Global") || usuario.getCargo().equals("Diretor") || usuario.getLogin().equals("admin") || usuario.getFilial().getCnpj().equals("70752763000174")) {
            filiais = filialService.obterListaFiliais();
        }

        request.setAttribute("filiais", filiais);
        request.getRequestDispatcher("/WEB-INF/jsp/autenticado/relatorioVendas.jsp").forward(request, response);
    }

    protected void relatorioProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<FilialModel> filiais = null;
        UsuarioModel usuario = (UsuarioModel) request.getSession().getAttribute("usuarioLogado");
        if (usuario.getCargo().equals("Gerente Global") || usuario.getCargo().equals("Diretor") || usuario.getLogin().equals("admin")
                || usuario.getFilial().getCnpj().equals("70752763000174")) {
            filiais = filialService.obterListaFiliais();
        }

        request.setAttribute("filiais", filiais);
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
            int idFilial = Integer.parseInt(request.getParameter("filial"));
            Date dataInicio = Utils.converteStrParaDate(request.getParameter("dataInicio"));
            Date dataFim = Utils.converteStrParaDate(request.getParameter("dataFim"));

            List<RelatorioVendasModel> lista;
            if (idFilial != 0) {
                lista = relatorioService.obterPorDataRelatorioVendas(dataInicio, dataFim, idFilial);
            } else {
                lista = relatorioService.obterPorDataRelatorioVendas(dataInicio, dataFim);
            }

            out.print(gson.toJson(lista));
            out.flush();
        }
    }

    protected void gerarRelatorioProdutos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Gson gson = new Gson();
            int idFilial = Integer.parseInt(request.getParameter("filial"));
            Date dataInicio = Utils.converteStrParaDate(request.getParameter("dataInicio"));
            Date dataFim = Utils.converteStrParaDate(request.getParameter("dataFim"));

            List<RelatorioProdutoModel> lista;
            if (idFilial != 0) {
                lista = relatorioService.obterPorDataRelatorioProduto(dataInicio, dataFim, idFilial);
            } else {
                lista = relatorioService.obterPorDataRelatorioProduto(dataInicio, dataFim);
            }
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

    protected void graficos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/jsp/autenticado/graficos.jsp").forward(request, response);
    }

    protected void gerarGraficos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ParseException {
        response.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Gson gson = new Gson();
            String tipo = request.getParameter("tipo");
            Date dataInicio = Utils.converteStrParaDate(request.getParameter("dataInicio"));
            Date dataFim = Utils.converteStrParaDate(request.getParameter("dataFim"));

            switch (tipo) {
                case "vendasPorFilial":
                    List<GraficoVendasFilialModel> vendas = relatorioService.vendasPorFilial(dataInicio, dataFim);
                    out.print(gson.toJson(vendas));
                    break;
                case "vendasPorVendedor":
                    List<GraficoMelhoresVendedoresModel> vendedores = relatorioService.vendasPorVendedor(dataInicio, dataFim);
                    out.print(gson.toJson(vendedores));
                    break;
                case "produtosVendidos":
                    List<GraficoProdutosModel> produtos = relatorioService.obterProdutosVendidos(dataInicio, dataFim);
                    out.print(gson.toJson(produtos));
                    break;
            }

            out.flush();
        }
    }
}
