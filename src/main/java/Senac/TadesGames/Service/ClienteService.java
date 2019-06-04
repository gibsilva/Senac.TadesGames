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

    private void validaDocumentoExistente(String documento) {
        if (documento.length() == 11) {
            if (clienteDao.obterPorCpf(documento) != null) {
                this.notificacao.adicionaNotificacao("cpf", "Esse CPF já está cadastrado");
            }
        } else {
            if (clienteDao.obterPorCnpj(documento) != null) {
                this.notificacao.adicionaNotificacao("cnpj", "Esse CNPJ já está cadastrado");
            }
        }
    }

    public ClienteModel obterClientePorDocumento(String documento) {
        return clienteDao.obterPorDocumento(documento);
    }

    private boolean validarClienteInclusao(ClienteModel cliente) {
        if (cliente.getDocumento().toString().length() == 11) {
            if (!validarCpf(cliente.getCpf()) && !cliente.getCpf().equals("")) {
                this.notificacao.adicionaNotificacao("cpf", "CPF inválido, por favor digite um CPF válido");
            }

            validaDocumentoExistente(cliente.getCpf());
        } else {
            if (!validarCnpj(cliente.getDocumento().getCnpj()) && !cliente.getDocumento().getCnpj().equals("")) {
                this.notificacao.adicionaNotificacao("cnpj", "CNPJ inválido, por favor digite um CNPJ válido");
            }

            validaDocumentoExistente(cliente.getDocumento().getCnpj());
        }

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

    private boolean validarCnpj(String cnpj) {
        // considera-se erro CNPJ's formados por uma sequência de números iguais
        if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111")
                || cnpj.equals("22222222222222") || cnpj.equals("33333333333333")
                || cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
                || cnpj.equals("66666666666666") || cnpj.equals("77777777777777")
                || cnpj.equals("88888888888888") || cnpj.equals("99999999999999")
                || (cnpj.length() != 14)) {
            return (false);
        }

        char dig13, dig14;
        int sm, i, r, num, peso;

        // "try" - protege o código para eventuais erros de conversão de tipo (int)
        try {
            // Cálculo do 1º. Dígito Verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                // converte o i-ésimo caractere do CNPJ em um número:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posição de '0' na tabela ASCII)
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig13 = '0';
            } else {
                dig13 = (char) ((11 - r) + 48);
            }

            // Calculo do 2º. Dígito Verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10) {
                    peso = 2;
                }
            }

            r = sm % 11;
            if ((r == 0) || (r == 1)) {
                dig14 = '0';
            } else {
                dig14 = (char) ((11 - r) + 48);
            }

            // Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }
}
