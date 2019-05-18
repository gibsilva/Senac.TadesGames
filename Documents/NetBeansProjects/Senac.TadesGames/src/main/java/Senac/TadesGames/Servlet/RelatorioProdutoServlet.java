/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Servlet;

import Senac.TadesGames.DAO.RelatorioProdutoDAO;
import Senac.TadesGames.Models.RelatorioProdutoModel;
import Senac.TadesGames.Service.RelatorioProdutoService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author caio_
 */
@WebServlet(name = "RelatorioProdutoServlet", urlPatterns = {"/Relatorio"})
public class RelatorioProdutoServlet extends HttpServlet{
        private RelatorioProdutoService geraRelatorio;
            private DataSource dataSource;
public void init() throws ServletException {

        super.init();

        try {
            geraRelatorio = new RelatorioProdutoService(dataSource);
        } catch (Exception e) {
            throw new ServletException(e);
        }
}
    
    private RelatorioProdutoDAO relatorioProdutoDAO; 
    
        @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String theCommand = request.getParameter("command");

            if (theCommand == null) {
                theCommand = "REPORT";
            }

            switch (theCommand) {

                case "REPORT":
                    relatorioForm(request, response);
                    break;

                case "GENERATE REPORT":
                    criaRelatorio(request, response);
                    break;
            }

            relatorioForm(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
    private void criaRelatorio(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // Lendo informação do FORMULÁRIO de produto
        Date dataInicio = formatStringToDate(request.getParameter("dataInicio"));
        Date dataFim = formatStringToDate(request.getParameter("fim"));
                                
        List<RelatorioProdutoModel> relatorioProduto = geraRelatorio.criaRelatorio(dataInicio, dataFim);
        
        // Settando o atributo produto com o valor que buscamos
        request.setAttribute("Relatorio", relatorioProduto);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/relatorioProduto.jsp");

        // Executando o dispatcher
        dispatcher.forward(request, response);
    }
    
    private void relatorioForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/relatorioProduto.jsp");

        // Executando o dispatcher
        dispatcher.forward(request, response);
    }

    public static Date formatStringToDate(String data) throws Exception {
        if (data == null || data.equals("")) {
            return null;
        }
        Date date = null;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = formatter.parse(data);
        } catch (ParseException e) {
            throw e;
        }
        return date;
    }

    public static String formatDateToString(Date data) throws Exception {
        if (data == null) {
            return null;
        }

        String date = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        date = formatter.format(data);

        return date;
    }
     
}
