/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.servlets;

import br.com.senac.tadesgames.helpers.Notificacao;
import br.com.senac.tadesgames.helpers.Utils;
import br.com.senac.tadesgames.models.ClienteModel;
import br.com.senac.tadesgames.models.ItensPedidoModel;
import br.com.senac.tadesgames.models.PedidoModel;
import br.com.senac.tadesgames.models.ProdutoModel;
import br.com.senac.tadesgames.models.UsuarioModel;
import br.com.senac.tadesgames.services.ClienteService;
import br.com.senac.tadesgames.services.PedidoService;
import br.com.senac.tadesgames.services.ProdutoService;
import br.com.senac.tadesgames.services.UsuarioService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Gi
 */
@WebServlet(name = "VendasControllerServlet", urlPatterns = {"/autenticado/Vendas"})
public class VendasControllerServlet extends HttpServlet {

    private final ProdutoService produtoService = new ProdutoService();
    private final ClienteService clienteService = new ClienteService();
    private final UsuarioService usuarioService = new UsuarioService();
    private final PedidoService pedidoService = new PedidoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acao = request.getParameter("acao");

        if (acao == null || acao.equals("")) {
            acao = "";
        }

        switch (acao) {
            case "obterCliente":
                String cpf = request.getParameter("cpfCliente");
                obterCliente(cpf, response);
                break;
            case "obterProduto":
                int idProduto = Integer.parseInt(request.getParameter("idProduto"));
                obterProduto(idProduto, request, response);
                break;
            case "listar":
                listarPedidos(request, response);
                break;
            case "detalhes":
                detalhesPedido(request, response);
                break;
            case "":
                criarVenda(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String acao = request.getParameter("acao");

            switch (acao) {
                case "salvar":
                    salvarPedido(request, response);
                    break;
                case "pesquisar":
                    pesquisarPedidos(request, response);
                    break;
                case "cancelar":
                    cancelarPedido(request, response);
                    break;
            }
        } catch (ServletException | IOException | JSONException e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(VendasControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void pesquisarPedidos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //condicional ternario, uma forma compacta de fazer um if
        int id = request.getParameter("filtroId").equals("") ? 0 : Integer.parseInt(request.getParameter("filtroId"));
        String dataInicio = request.getParameter("filtroDataIni");
        String dataFim = request.getParameter("filtroDataFim");

        UsuarioModel usuario = (UsuarioModel) request.getSession().getAttribute("usuarioLogado");
        if (usuario.getCargo().equals("Gerente Global") || usuario.getCargo().equals("Diretor") || usuario.getLogin().equals("admin")
                || usuario.getFilial().getCnpj().equals("70752763000174")) {
            request.setAttribute("pedidos", pedidoService.pesquisarPedidos(id, dataInicio, dataFim));
        } else{
            request.setAttribute("pedidos", pedidoService.pesquisarPedidos(id, dataInicio, dataFim, usuario.getIdFilial()));
        }

        request.getRequestDispatcher("/WEB-INF/jsp/autenticado/consultaVendas.jsp").forward(request, response);
    }

    protected void cancelarPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        int id = Integer.parseInt(request.getParameter("idPedido"));
        PedidoModel pedido = pedidoService.obterPorId(id);
        try {
            pedidoService.cancelarPedido(pedido);
        } catch (ServletException e) {
            throw new Exception(e.getMessage());
        }
    }

    protected void detalhesPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idPedido = Integer.parseInt(request.getParameter("idPedido"));

        request.setAttribute("pedido", pedidoService.obterPorId(idPedido));
        request.getRequestDispatcher("/WEB-INF/jsp/autenticado/detalhesVenda.jsp").forward(request, response);
    }

    protected void listarPedidos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsuarioModel usuario = (UsuarioModel) request.getSession().getAttribute("usuarioLogado");
        List<PedidoModel> pedidos = null;

        if (usuario.getCargo().equals("Gerente Global") || usuario.getCargo().equals("Diretor") || usuario.getLogin().equals("admin")
                || usuario.getFilial().getCnpj().equals("70752763000174")) {
            pedidos = pedidoService.obterTodos();
        } else {
            pedidos = pedidoService.obterTodosPorIdFilial(usuario.getIdFilial());
        }

        request.setAttribute("pedidos", pedidos);
        request.getRequestDispatcher("/WEB-INF/jsp/autenticado/consultaVendas.jsp").forward(request, response);
    }

    protected void salvarPedido(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, JSONException {
        Utils utils = new Utils();
        
        String dados = request.getParameter("listaDeItens");
        String documento = request.getParameter("cpfCliente");
        int idUsuario = Integer.parseInt(request.getParameter("vendedor"));
        Date dataPedido = new Date(System.currentTimeMillis());
        int formaPagamento = Integer.parseInt(request.getParameter("formaPagamento"));
        int parcela = request.getParameter("parcelas") == null ? 0 : Integer.parseInt(request.getParameter("parcelas"));
        double valorRecebido = Double.parseDouble(utils.formatarValor(request.getParameter("valorRecebido")));

        //convertando o json de string para um json object
        JSONObject jsonObject = new JSONObject(dados.trim());

        List<ItensPedidoModel> itensDePedidos = new ArrayList<ItensPedidoModel>();

        JSONArray jArray = jsonObject.getJSONArray("itens");
        /*
         percorrendo o array de json para passar os dados para uma lista
         de itens de pedido
         */
        for (int i = 0; i < jArray.length(); i++) {
            JSONObject item = jArray.getJSONObject(i);

            ItensPedidoModel itens = new ItensPedidoModel(
                    0,
                    item.getInt("idProduto"),
                    item.getDouble("valorUnitario"),
                    item.getInt("quantidade"),
                    0
            );
            itensDePedidos.add(itens);
        }

        ClienteModel cliente;
        if (documento.length() == 11) {
            cliente = documento.equals("") ? clienteService.obterClientePorCpf("12345678910") : clienteService.obterClientePorCpf(documento);
        } else {
            cliente = clienteService.obterClientePorCnpj(documento);
        }

        UsuarioModel usuario = usuarioService.obterUsuarioPorId(idUsuario);

        PedidoModel pedido = new PedidoModel(0, 1, dataPedido, cliente.getIdCliente(), usuario.getIdFilial(), usuario.getIdUsuario(), formaPagamento, parcela, valorRecebido);
        pedido.setItensPedido(itensDePedidos);

        try {
            List<Notificacao> notificacoes = pedidoService.incluirPedido(pedido);
            if (notificacoes.isEmpty()) {
                request.setAttribute("statusOk", true);
                criarVenda(request, response);
            } else {
                request.setAttribute("notificacoes", notificacoes);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/autenticado/vendas.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/erro.jsp");
            dispatcher.forward(request, response);
        } finally {
            pedidoService.limparNotificacoes();
        }

    }

    protected void criarVenda(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UsuarioModel usuario = (UsuarioModel) request.getSession().getAttribute("usuarioLogado");
        request.setAttribute("vendedores", usuarioService.obterTodosPorCargo("Vendedor (a)", usuario.getIdFilial()));

        request.getRequestDispatcher("/WEB-INF/jsp/autenticado/vendas.jsp").forward(request, response);
    }

    protected void obterCliente(String doc, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            Gson gson = new Gson();
            ClienteModel cliente = doc.length() == 11 ? clienteService.obterClientePorCpf(doc) : clienteService.obterClientePorCnpj(doc);

            out.print(gson.toJson(cliente));
            out.flush();
        }
    }

    protected void obterProduto(int idProduto, HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html; charset=UTF-8");
        UsuarioModel usuario = (UsuarioModel) request.getSession().getAttribute("usuarioLogado");
        try (PrintWriter out = response.getWriter()) {
            Gson gson = new Gson();
            ProdutoModel produto = produtoService.obterPorId(idProduto, usuario.getIdFilial());

            out.print(gson.toJson(produto));
            out.flush();
        }
    }

}
