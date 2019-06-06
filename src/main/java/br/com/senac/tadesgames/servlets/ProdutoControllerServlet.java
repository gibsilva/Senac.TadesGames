/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.servlets;

import br.com.senac.tadesgames.helpers.Notificacao;
import br.com.senac.tadesgames.helpers.Utils;
import br.com.senac.tadesgames.models.FilialModel;
import br.com.senac.tadesgames.models.ProdutoModel;
import br.com.senac.tadesgames.models.UsuarioModel;
import br.com.senac.tadesgames.services.CategoriaService;
import br.com.senac.tadesgames.services.FilialService;
import br.com.senac.tadesgames.services.GeneroService;
import br.com.senac.tadesgames.services.PlataformaService;
import br.com.senac.tadesgames.services.ProdutoService;
import java.io.IOException;
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
@WebServlet(name = "ProdutoControllerServlet", urlPatterns = {"/autenticado/Produtos"})
public class ProdutoControllerServlet extends HttpServlet {

    private final ProdutoService produtoService;
    private final PlataformaService plataformaService;
    private final CategoriaService categoriaService;
    private final GeneroService generoService;
    private final FilialService filialService;

    public ProdutoControllerServlet() {
        this.produtoService = new ProdutoService();
        this.plataformaService = new PlataformaService();
        this.categoriaService = new CategoriaService();
        this.generoService = new GeneroService();
        this.filialService = new FilialService();
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
                    break;
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
        UsuarioModel usuario = (UsuarioModel) request.getSession().getAttribute("usuarioLogado");
        List<ProdutoModel> produtos = null;
        
        if (usuario.getCargo().equals("Gerente Global") || usuario.getCargo().equals("Diretor") || usuario.getLogin().equals("admin")
                || usuario.getFilial().getCnpj().equals("70752763000174")) {
            produtos = produtoService.obterTodos();
        } else{
            produtos = produtoService.obterTodosPorIdFilial(usuario.getIdFilial());
        }

        request.setAttribute("produtos", produtos);
        request.getRequestDispatcher("/WEB-INF/jsp/autenticado/consultaProduto.jsp").forward(request, response);
    }

    protected void criarProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("plataformas", plataformaService.obterListaPlataforma());
        request.setAttribute("generos", generoService.obterListaGenero());
        request.setAttribute("categorias", categoriaService.obterListaCategoria());
        List<FilialModel> filiais = null;

        UsuarioModel usuario = (UsuarioModel) request.getSession().getAttribute("usuarioLogado");
        if (usuario.getCargo().equals("Gerente Global") || usuario.getCargo().equals("Diretor") || usuario.getLogin().equals("admin")
                || usuario.getFilial().getCnpj().equals("70752763000174")) {
            filiais = filialService.obterListaFiliaisAtivas();
        }

        request.setAttribute("filiais", filiais);
        request.getRequestDispatcher("/WEB-INF/jsp/autenticado/cadastroProduto.jsp").forward(request, response);
    }

    protected void carregarProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("idProduto"));
        ProdutoModel produto = produtoService.obterPorId(id);
        List<FilialModel> filiais = null;

        UsuarioModel usuario = (UsuarioModel) request.getSession().getAttribute("usuarioLogado");
        if (usuario.getCargo().equals("Gerente Global") || usuario.getCargo().equals("Diretor") || usuario.getLogin().equals("admin")
                || usuario.getFilial().getCnpj().equals("70752763000174")) {
            filiais = filialService.obterListaFiliaisAtivas();
        }

        request.setAttribute("produto", produto);
        request.setAttribute("plataformas", plataformaService.obterListaPlataforma());
        request.setAttribute("generos", generoService.obterListaGenero());
        request.setAttribute("categorias", categoriaService.obterListaCategoria());
        request.setAttribute("filiais", filiais);
        request.getRequestDispatcher("/WEB-INF/jsp/autenticado/alterarProduto.jsp").forward(request, response);
    }

    protected void incluirProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        Utils utils = new Utils();
        UsuarioModel usuario = (UsuarioModel) request.getSession().getAttribute("usuarioLogado");

        String nome = request.getParameter("nome");
        int idPlataforma = Integer.parseInt(request.getParameter("plataforma"));
        int idCategoria = Integer.parseInt(request.getParameter("categoria"));
        double valorCompra = Double.parseDouble(utils.formatarValor(request.getParameter("valorCompra")));
        double valorVenda = Double.parseDouble(utils.formatarValor(request.getParameter("valorVenda")));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        boolean ativo = Boolean.parseBoolean(request.getParameter("ativo"));
        int idGenero = Integer.parseInt(request.getParameter("genero"));
        String descricao = utils.removeQuebraLinha(request.getParameter("descricao"));
        int idFilial = request.getParameter("filial") == null ? usuario.getIdFilial() : Integer.parseInt(request.getParameter("filial"));

        ProdutoModel produto = new ProdutoModel(0, nome, descricao, valorCompra, valorVenda, idCategoria, idGenero, ativo, idFilial, idPlataforma, quantidade);

        try {
            List<Notificacao> notificacoes = produtoService.incluirOuAlterarProduto(produto);
            if (notificacoes.isEmpty()) {
                request.setAttribute("statusSalvo", true);
                listarProdutos(request, response);
            } else {
                request.setAttribute("notificacoes", notificacoes);
                criarProduto(request, response);
            }
        } catch (Exception e) {
            request.getRequestDispatcher("erro.jsp").forward(request, response);
            throw new Exception(e.getMessage());
        } finally {
            this.produtoService.limparNotificacoes();
        }
    }

    protected void alterarProduto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        Utils utils = new Utils();
        UsuarioModel usuario = (UsuarioModel) request.getSession().getAttribute("usuarioLogado");

        int id = Integer.parseInt(request.getParameter("idProduto"));
        String nome = request.getParameter("nome");
        int idPlataforma = Integer.parseInt(request.getParameter("plataforma"));
        int idCategoria = Integer.parseInt(request.getParameter("categoria"));
        double valorCompra = Double.parseDouble(utils.formatarValor(request.getParameter("valorCompra")));
        double valorVenda = Double.parseDouble(utils.formatarValor(request.getParameter("valorVenda")));
        int quantidade = Integer.parseInt(request.getParameter("quantidade"));
        boolean ativo = Boolean.parseBoolean(request.getParameter("ativo"));
        int idGenero = Integer.parseInt(request.getParameter("genero"));
        String descricao = utils.removeQuebraLinha(request.getParameter("descricao"));
        int idFilial = request.getParameter("filial") == null ? usuario.getIdFilial() : Integer.parseInt(request.getParameter("filial"));

        ProdutoModel produto = new ProdutoModel(id, nome, descricao, valorCompra, valorVenda, idCategoria, idGenero, ativo, idFilial, idPlataforma, quantidade);

        try {
            List<Notificacao> notificacoes = produtoService.incluirOuAlterarProduto(produto);
            if (notificacoes.isEmpty()) {
                request.setAttribute("statusAlterado", true);
                listarProdutos(request, response);
            } else {
                request.setAttribute("notificacoes", notificacoes);
                carregarProduto(request, response);
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        } finally {
            this.produtoService.limparNotificacoes();
        }
    }

}
