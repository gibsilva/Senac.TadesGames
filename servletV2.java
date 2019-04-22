    
package Senac.TadesGames.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "cadastrarCliente", urlPatterns = {"/cadastrar-cliente"})
public class CadastrarClienteServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String cliente = "";
        
        RequestDispatcher dispatcher = request.getRequestDispatcher(cliente);
        dispatcher.forward(request, response);
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        request.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("IdCliente"));
        String nome = request.getParameter("nome");
        // String sobrenome = request.getParameter("sobrenome");
        String cnpj = request.getParameter("CNPJ");
        String cpf = request.getParameter("cpf");
        String dataNasc = request.getParameter("dataNasc");
        String email = request.getParameter("email");
        String telefone = request.getParameter("telefone");
        String celular = request.getParameter("celular");
        String dataHoraCriacao = request.getParameter("dataHoraCriação");
        String sexo = request.getParameter("sexo");
                PrintWriter tc = response.getWriter();

        Cliente newClient = new Cliente();
            newClient.setNome(nome);
            newClient.setCNPJ(cnpj);
            newClient.setCpf(cpf);
            newClient.setDataNasc(dataNasc);
            newClient.setEmail(email);
            newClient.setTelefone(telefone);
            newClient.setCelular(celular);
            newClient.setDataHoraCriacao(dataHoraCriacao);
            newClient.setSexo(sexo);
            newClient.setAtivo(true);
        ServicoCliente servicoCliente = new ServicoCliente();
        try {
            servicoCliente.cadastrarCliente(newClient);  
            tc.println("deu bom");
        } catch(Exception e) {
            tc.println("deu ruim");
        }

//        response.sendRedirect(request.getContextPath() + "/cadastrar-cliente");
    }
    // deletar
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp); //To change body of generated methods, choose Tools | Templates.
    }
    
    // atualizar
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp); //To change body of generated methods, choose Tools | Templates.
    }
    
}
