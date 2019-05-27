/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Servlet;

import Senac.TadesGames.Models.HomeModel;
import Senac.TadesGames.Models.PedidoModel;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Senac.TadesGames.Models.ProdutoModel;
import Senac.TadesGames.Service.HomeService;
import Senac.TadesGames.Service.ItensPedidoService;
import Senac.TadesGames.Service.PedidoService;
import Senac.TadesGames.Service.ProdutoService;

/**
 *
 * @author Gi
 */
@WebServlet(name = "HomeControllerServlet", urlPatterns = {"/autenticado/Home"})
public class HomeControllerServlet extends HttpServlet {

    private final ProdutoService produtoService;
    private final PedidoService pedidoService;
    private final ItensPedidoService itensPedidoService;
    private final HomeService homeService;

    public HomeControllerServlet() {
        this.produtoService = new ProdutoService();
        this.pedidoService = new PedidoService();
        this.itensPedidoService = new ItensPedidoService();
        this.homeService = new HomeService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<ProdutoModel> produtos = produtoService.obterTodos();
        List<PedidoModel> pedidos = pedidoService.obterTodosConcluidos();       
        
        int totalVendidoProduto = 0;
        int totalEstoque = 0;
        int qtdVendas = pedidos.size();
        double totalVendas = 0.0;

        for (ProdutoModel p : produtos) {
            totalEstoque += p.getQuantidadeEstoque();
        }
        
        for(PedidoModel p : pedidos){
            totalVendas += p.getValorTotal();
            totalVendidoProduto += p.getQtdProdutos();
        }
        
        HomeModel homeVendedor = homeService.obterHomeVendedor();      
        
        request.setAttribute("totalEstoque", totalEstoque);
        request.setAttribute("totalVendido", totalVendidoProduto);
        request.setAttribute("qtdVendas", qtdVendas);
        request.setAttribute("totalVendas", totalVendas);
        request.setAttribute("homeVendedor", homeVendedor);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/autenticado/home.jsp");
        rd.forward(request, response);
    }

}
