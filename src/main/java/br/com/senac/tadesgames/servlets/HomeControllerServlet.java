/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.servlets;

import br.com.senac.tadesgames.models.HomeProdutoModel;
import br.com.senac.tadesgames.models.HomeVendaModel;
import br.com.senac.tadesgames.models.HomeVendedorModel;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.senac.tadesgames.models.UsuarioModel;
import br.com.senac.tadesgames.services.HomeService;

/**
 *
 * @author Gi
 */
@WebServlet(name = "HomeControllerServlet", urlPatterns = {"/autenticado/Home"})
public class HomeControllerServlet extends HttpServlet {

    private final HomeService homeService;

    public HomeControllerServlet() {
        this.homeService = new HomeService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsuarioModel usuario = (UsuarioModel) request.getSession().getAttribute("usuarioLogado");
        HomeProdutoModel homeProduto;
        HomeVendaModel homeVenda;
        HomeVendedorModel homeVendedor;

        if (usuario.getCargo().equals("Gerente Global") || usuario.getCargo().equals("Diretor") || usuario.getLogin().equals("admin")
                || usuario.getFilial().getCnpj().equals("70752763000174")) {
            homeProduto = homeService.obterHomeProduto();
            homeVenda = homeService.obterHomeVendas();
            homeVendedor = homeService.obterHomeVendedor();
        } else {
            homeProduto = homeService.obterHomeProdutoPorIdFilial(usuario.getIdFilial());
            homeVenda = homeService.obterHomeVendasPorIdFilial(usuario.getIdFilial());
            homeVendedor = homeService.obterHomeVendedorPorIdFilial(usuario.getIdFilial());
        }

        request.setAttribute("homeProduto", homeProduto);
        request.setAttribute("homeVenda", homeVenda);
        request.setAttribute("homeVendedor", homeVendedor);
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/autenticado/home.jsp");
        rd.forward(request, response);
    }

}
