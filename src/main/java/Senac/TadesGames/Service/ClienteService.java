/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Service;

import Senac.TadesGames.DAO.ClienteDAO;
import Senac.TadesGames.Helpers.Notificacao;
import Senac.TadesGames.Models.ClienteModel;
import java.util.InputMismatchException;
import java.util.List;

/**
 *
 * @author Gi
 */
public class ClienteService {

    private final ClienteDAO clienteDao;
    private final Notificacao notificacao;

    public ClienteService() {
        this.clienteDao = new ClienteDAO();
        this.notificacao = new Notificacao();
    }

    private void validaEmailExistente(String email) {
        if (clienteDao.obterPorEmail(email) != null) {
            this.notificacao.adicionaNotificacao("email", "E-mail já está cadastrado");
        }
    }

    private void validaEmailExistente(String email, int id) {
        if (clienteDao.obterPorEmail(email, id) != null) {
            this.notificacao.adicionaNotificacao("email", "E-mail já está cadastrado");
        }
    }

    private void validaCpfExistente(String cpf) {
        if (clienteDao.obterPorCpf(cpf) != null) {
            this.notificacao.adicionaNotificacao("cpf", "Esse CPF já está cadastrado");
        }
    }

    private boolean validarClienteInclusao(ClienteModel cliente) {
        if (!validarCpf(cliente.getCpf()) && !cliente.getCpf().equals("")) {
            this.notificacao.adicionaNotificacao("cpf", "CPF inválido, por favor digite um CPF válido");
        }

        validaCpfExistente(cliente.getCpf());
        validaEmailExistente(cliente.getEmail());

        return this.notificacao.quantidadeNotificacoes() == 0;
    }

    private boolean validarClienteAlteracao(ClienteModel cliente) {
        validaEmailExistente(cliente.getEmail(), cliente.getIdCliente());
        return this.notificacao.quantidadeNotificacoes() == 0;
    }

    public List<Notificacao> incluirCliente(ClienteModel cliente) throws Exception {
        try {
            if (validarClienteInclusao(cliente)) {
                clienteDao.inserir(cliente);
            }
            return this.notificacao.listaNotificacoes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Notificacao> alterarCliente(ClienteModel cliente) throws Exception {
        try {
            if (validarClienteAlteracao(cliente)) {
                clienteDao.alterar(cliente);
            }
            return this.notificacao.listaNotificacoes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ClienteModel obterClientePorId(int id) {
        return clienteDao.obterPorId(id);
    }

    public ClienteModel obterClientePorCpf(String cpf) {
        return clienteDao.obterPorCpf(cpf);
    }

    public ClienteModel obterClientePorCnpj(String cnpj) {
        return clienteDao.obterPorCnpj(cnpj);
    }

    public List<ClienteModel> obterListaClientes() {
        return clienteDao.obterTodas();
    }

    public void limparNotificacoes() {
        this.notificacao.limparLista();
    }

    private boolean validarCpf(String cpf) {
        // considera-se erro cpf's formados por uma sequencia de numeros iguais
        if (cpf.equals("00000000000")
                || cpf.equals("11111111111")
                || cpf.equals("22222222222") || cpf.equals("33333333333")
                || cpf.equals("44444444444") || cpf.equals("55555555555")
                || cpf.equals("66666666666") || cpf.equals("77777777777")
                || cpf.equals("88888888888") || cpf.equals("99999999999")
                || (cpf.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do cpf em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0         
                // (48 eh a posicao de '0' na tabela ASCII)         
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }
            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }
}
