/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Servlet;

import Senac.TadesGames.Helpers.Notificacao;
import Senac.TadesGames.Models.ProdutoModel;
import Senac.TadesGames.Service.CategoriaService;
import Senac.TadesGames.Service.GeneroService;
import Senac.TadesGames.Service.PlataformaService;
import Senac.TadesGames.Service.ProdutoService;
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
 * @author Gi
 */
@WebServlet(name = "ProdutoControllerServlet", urlPatterns = {"/Produtos"})
public class ProdutoControllerServlet extends HttpServlet {

    private final ProdutoService produtoService;
    private final PlataformaService plataformaService;
    private final CategoriaService categoriaService;
    private final GeneroService generoService;

    public ProdutoControllerServlet() {
        this.produtoService = new ProdutoService();
        this.plataformaService = new PlataformaService();
        this.categoriaService = new CategoriaService();
        this.generoService = new GeneroService();
    }

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
                    listarProdutos(request, response);
                    break;
                case "alterar":
                    carregarProduto(request, response);
                    break;
                case "salvar":
                    criarProduto(request, response);
                default:
                    listarProdutos(request, response);
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
                    incluirProduto(request, response);
                    break;
                case "alterar":
                    alterarProduto(request, response);
                    break;
            }
        } catch (ServletException | IOException e) {
            throw new ServletException(e);
        } catch (Exception ex) {
            Logger.getLogger(ProdutoControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void listarProdutos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("produtos", produtoService.obterTodos());
        request.getRequestDispatcher("consultaProduto.jsp").forward(request, response);
    }

    protected void criarProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("plataformas", plataformaService.obterListaPlataforma());
        request.setAttribute("generos", generoService.obterListaGenero());
        request.setAttribute("categorias", categoriaService.obterListaCategoria());
        request.getRequestDispatcher("cadastroProduto.jsp").forward(request, response);
    }

    protected void carregarProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idProduto"));
        ProdutoModel produto = produtoService.obterPorId(id);

        request.setAttribute("produto", produto);
        request.setAttribute("plataformas", plataformaService.obterListaPlataforma());
        request.setAttribute("generos", generoService.obterListaGenero());
        request.setAttribute("categorias", categoriaService.obterListaCategoria());

        request.getRequestDispatcher("alterarProduto.jsp").forward(request, response);
    }

    protected void incluirProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        String nome = request.getParameter("nome");
        int idPlataforma = Integer.parseInt(request.getParameter("plataforma"));
        int idCategoria = Integer.parseInt(request.getParameter("categoria"));
        double valorCompra = Double.parseDouble(request.getParameter("valorCompra"));
        double valorVenda = Double.parseDouble(request.getParameter("valorVenda"));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        boolean ativo = Boolean.parseBoolean(request.getParameter("ativo"));
        int idGenero = Integer.parseInt(request.getParameter("genero"));
        String descricao = request.getParameter("descricao");
        int idFilial = 1;

        ProdutoModel produto = new ProdutoModel(0, nome, descricao, valorCompra, valorVenda, idCategoria, idGenero, ativo, idFilial, idPlataforma, quantidade);

        try {
            List<Notificacao> notificacoes = produtoService.incluirOuAlterarProduto(produto);
            if (notificacoes.isEmpty()) {
                listarProdutos(request, response);
            } else {
                request.setAttribute("notificacoes", notificacoes);
                request.setAttribute("produto", produto);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/cadastroProduto.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            this.produtoService.limparNotificacoes();
        }
    }

    protected void alterarProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        int id = Integer.parseInt(request.getParameter("idProduto"));
        String nome = request.getParameter("nome");
        int idPlataforma = Integer.parseInt(request.getParameter("plataforma"));
        int idCategoria = Integer.parseInt(request.getParameter("categoria"));
        double valorCompra = Double.parseDouble(request.getParameter("valorCompra"));
        double valorVenda = Double.parseDouble(request.getParameter("valorVenda"));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        boolean ativo = Boolean.parseBoolean(request.getParameter("ativo"));
        int idGenero = Integer.parseInt(request.getParameter("genero"));
        String descricao = request.getParameter("descricao");
        int idFilial = 1;

        ProdutoModel produto = new ProdutoModel(id, nome, descricao, valorCompra, valorVenda, idCategoria, idGenero, ativo, idFilial, idPlataforma, quantidade);

        try {
            List<Notificacao> notificacoes = produtoService.incluirOuAlterarProduto(produto);
            if (notificacoes.isEmpty()) {
                listarProdutos(request, response);
            } else {
                request.setAttribute("notificacoes", notificacoes);
                request.setAttribute("produto", produto);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/alterarProduto.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            this.produtoService.limparNotificacoes();
        }
    }

}
