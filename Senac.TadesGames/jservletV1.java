

import senacpi.hospitaltades.service.clienteDbUtil;
import java.io.IOException;
import java.util.*;
import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import senacpi.hospitaltades.model.cliente;

/**
 *
 * @author Caio Gonçalves
 */
public class clienteControllerServlet extends HttpServlet {

    private clienteDbUtil clienteDbUtil;

    @Resource(name = "Umbrella Academy / Loja de Games")
    private DataSource dataSource;

    @Override
    public void init() throws ServletException {

        super.init();

        try {
            clienteDbUtil = new clienteDbUtil(dataSource);
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            // Lê o Comando
            String comando = request.getParameter("comando");

            // Caso não tenha comando, o default é listar os clientes
            if (comando == null) {
                comando = "READ";
            }

            // Rotear para o método apropria com um SWITCH CASE 
            switch (comando) {

                // Lendo clientes (R)
                case "READ":
                    listarclientes(request, response);
                    break;

                //Atualizar um cliente(U)
                case "LOAD":
                    carregarcliente(request, response);
                    break;

                // Deletar um cliente (D)
                case "DELETE":
                    excluircliente(request, response);
                    break;

                default:
                    listarclientes(request, response);
            }

            listarclientes(request, response);
        } catch (Exception e) {
            throw new ServletException(e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {

            // Lê "comando"
            String comando = request.getParameter("comando");

            // Rotear para o método apropria com um SWITCH CASE 
            switch (comando) {

                // Cadastrando cliente (C) 
                case "CREATE":
                    cadastrarcliente(request, response);
                    break;

                case "UPDATE":
                    editarcliente(request, response);
                    break;
            }
        } catch (Exception e) {
            throw new ServletException(e);
        }

    }

    private void cadastrarcliente(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // Lendo informação do FORMULÁRIO de cliente
        String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");
        String dataNasc = request.getParameter("dataNasc");
        String cpf = request.getParameter("cpf");
        String sexo = request.getParameter("sexo");
        String contato = request.getParameter("contato");
        String email = request.getParameter("email");
        String codFilial = request.getParameter("codFilial");
        Boolean ativo = true;

        // Criar um objeto do cliente
        cliente cliente = new cliente(nome, sobrenome, dataNasc, cpf, sexo, contato, email, codFilial, ativo);

        // Adicionar esse cliente no banco de Dados
        clienteDbUtil.addPatient(cliente);

        // Envia o usuário de volta a página de listagem de clientes
        response.sendRedirect(request.getContextPath() + "/clienteControllerServlet?command=READ");
    }

    private void listarclientes(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession();
        String usuarioCodFilial = (String) session.getAttribute("usuarioCodFilial"); //Precisará criar codigo de filial por usuario.

        List<cliente> clientes = clienteDbUtil.getclientes(usuarioCodFilial);
        // Buscando clientes usando o clienteDbUtil
        // Settando o atributo clienteS com o valor que buscamos
        request.setAttribute("clienteS", clientes);

        // Atribuindo valor para o dispatcher com o endereço da página que queremos
        RequestDispatcher dispatcher = request.getRequestDispatcher("lista-clientes.jsp");

        // Executando o dispatcher
        dispatcher.forward(request, response);
    }

    private void carregarcliente(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // Ler o ID do cliente
        String theIdCliente = request.getParameter("IdCliente");

        // Achar o cliente pelo banco de dados
        cliente cliente = clienteDbUtil.getcliente(theIdCliente);

        // Colocar o cliente no atributo request
        request.setAttribute("cliente", cliente);

        // Enviar o cliente para a página JSP (cliente-editar.jsp)
        RequestDispatcher dispatcher = request.getRequestDispatcher("cliente-editar.jsp");

        // Executando o dispatcher
        dispatcher.forward(request, response);
    }

    private void editarcliente(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        // Lendo os dados do form
        int id = Integer.parseInt(request.getParameter("IdCliente"));
        String nome = request.getParameter("nome");
       // String sobrenome = request.getParameter("sobrenome");
        String cnpj = request.getParameter("CNPJ");
        String cpf = request.getParameter("cpf");
        String dataNasc = request.getParameter("dataNasc");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        String celular = request.getParameter("celular");
        String dataHoraCriação = request.getParameter("dataHoraCriação");
       // String sexo = request.getParameter("sexo");
        //String contato = request.getParameter("contato");
        //String codFilial = request.getParameter("codFilial");
        Boolean ativo = true;

        cliente cliente = new cliente (id, nome, cpf, cnpj, dataNasc, email, telefone, celular, dataHoraCriação);
        //(id, nome, sobrenome, dataNasc, cpf, sexo, contato, email, codFilial, ativo);
        clienteDbUtil.updatecliente(cliente);

        response.sendRedirect(request.getContextPath() + "/clienteControllerServlet?command=READ");

    }

    private void excluircliente(HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String theIdCliente = request.getParameter("IdCliente");

        clienteDbUtil.deletePatient(theIdCliente);

        listarclientes(request, response);

    }
}
