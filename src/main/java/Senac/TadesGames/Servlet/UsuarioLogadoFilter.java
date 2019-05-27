/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Servlet;

import Senac.TadesGames.Models.UsuarioModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Giovanni.Carignato
 */
@WebFilter(filterName = "UsuarioLogadoFilter", urlPatterns = {"/autenticado/*"})
public class UsuarioLogadoFilter implements Filter {

    private String contextPath;

    public UsuarioLogadoFilter() {}
    
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession();

        if (session.getAttribute("usuarioLogado") == null) {
            session.invalidate();
            res.sendRedirect(contextPath + "/Login");
            return;
        }

        UsuarioModel usuario = (UsuarioModel) session.getAttribute("usuarioLogado");

        if (verificarAcesso(usuario, req, res)) {
            res.setHeader("Cache-Control", "no-cache, no-store");
            res.setHeader("Pragma", "no-cache");
            res.setHeader("Expires", "-1");
            chain.doFilter(request, response);
        } else {
            res.sendRedirect(req.getContextPath() + "/nao-autorizado.jsp");
        }
    }

    private boolean verificarAcesso(UsuarioModel usuario, HttpServletRequest request, HttpServletResponse response) {
        String paginaAcessada = request.getRequestURI();
        if (paginaAcessada.endsWith("/Home") || paginaAcessada.endsWith("/Logout") || paginaAcessada.endsWith("/ResetSenha") || paginaAcessada.endsWith("/alterar-senha")) {
            return true;
        } else if (usuario.getLogin().equals("admin")) {
            return true;
        } else if (usuario.getCargo().equals("Diretor") && usuario.getSetor().equals("Diretoria")) {
            return true;
        } else if(paginaAcessada.endsWith("/Vendas") && usuario.getSetor().equals("Vendas")){
            return true;
        } else if((paginaAcessada.endsWith("/Usuarios") || paginaAcessada.endsWith("/Filiais")) && usuario.getSetor().equals("T.I")){
            return true;
        } else if((paginaAcessada.endsWith("/Produtos") || paginaAcessada.endsWith("/Plataformas") || paginaAcessada.endsWith("/Generos") || 
                paginaAcessada.endsWith("/Categorias") || paginaAcessada.endsWith("/Clientes")) && usuario.getSetor().equals("Produtos/Serviços/Marketing")){
            return true;
        } else if(paginaAcessada.endsWith("/Clientes") && usuario.getSetor().equals("Vendas")){
            return true;
        } else if (paginaAcessada.endsWith("/Relatorios") && (usuario.getCargo().contains("Gerente") || usuario.getCargo().contains("Diretor"))){
            return true;
        }
        return false;
    }

    @Override
    public void destroy() {
    }

    public static String getFullURL(HttpServletRequest request) {
        StringBuilder requestURL = new StringBuilder(request.getRequestURL().toString());
        String queryString = request.getQueryString();

        if (queryString == null) {
            return requestURL.toString();
        } else {
            return requestURL.append('?').append(queryString).toString();
        }
    }

    /**
     * Init method for this filter
     *
     * @param filterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
        this.contextPath = filterConfig.getServletContext().getContextPath();
    }

    public static String getStackTrace(Throwable t) {
        String stackTrace = null;
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            t.printStackTrace(pw);
            pw.close();
            sw.close();
            stackTrace = sw.getBuffer().toString();
        } catch (Exception ex) {
        }
        return stackTrace;
    }

}
