/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Service;

import Senac.TadesGames.DAO.ClienteDAO;
import Senac.TadesGames.Helpers.Notificacao;
import Senac.TadesGames.Models.ClienteModel;
import Senac.TadesGames.Models.ClienteFisicoModel;
import Senac.TadesGames.Models.ClienteJuridicoModel;
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

    private void validaEmailFisicoExistente(String email) {
        if (clienteDao.obterPorEmailFisico(email) != null) {
            this.notificacao.adicionaNotificacao("email", "E-mail já está cadastrado");
        }
    }
        private void validaEmailJuridicoExistente(String email) {
        if (clienteDao.obterPorEmailJuridico(email) != null) {
            this.notificacao.adicionaNotificacao("email", "E-mail já está cadastrado");
        }
    }

    private void validaEmailJuridicoExistente(String email, int id) {
        if (clienteDao.obterPorEmailJuridico(email, id) != null) {
            this.notificacao.adicionaNotificacao("email", "E-mail já está cadastrado");
        }
    }
    
        private void validaEmailFisicoExistente(String email, int id) {
        if (clienteDao.obterPorEmailFisico(email, id) != null) {
            this.notificacao.adicionaNotificacao("email", "E-mail já está cadastrado");
        }
    }
        
    private void validaCpfExistente(String cpf) {
        if (clienteDao.obterPorCpf(cpf) != null) {
            this.notificacao.adicionaNotificacao("cpf", "Esse CPF já está cadastrado");
        }
    }
        private void validaCnpjExistente(String cnpj) {
        if (clienteDao.obterPorCnpj(cnpj) != null) {
            this.notificacao.adicionaNotificacao("cnpj", "Esse CNPJ já está cadastrado");
        }
    }

    private boolean validarClienteFisicoInclusao(ClienteFisicoModel cliente) {
        if (!validarCpf(cliente.getCpf())) {
            this.notificacao.adicionaNotificacao("cpf", "CPF inválido, por favor digite um CPF válido");
        }

        validaCpfExistente(cliente.getCpf());
        validaEmailFisicoExistente(cliente.getEmail());

        return this.notificacao.quantidadeNotificacoes() == 0;
    }
        private boolean validarClienteJuridicoInclusao(ClienteJuridicoModel cliente) {
        if (!validarCnpj(cliente.getCnpj())) {
            this.notificacao.adicionaNotificacao("cnpj", "CNPJ inválido, por favor digite um CNPJ válido");
        }

        validaCnpjExistente(cliente.getCnpj());
        validaEmailJuridicoExistente(cliente.getEmail());

        return this.notificacao.quantidadeNotificacoes() == 0;
    }

    private boolean validarClienteFisicoAlteracao(ClienteFisicoModel cliente) {
        validaEmailFisicoExistente(cliente.getEmail(), cliente.getIdCliente());
        return this.notificacao.quantidadeNotificacoes() == 0;
    }
        private boolean validarClienteJuridicoAlteracao(ClienteModel cliente) {
        validaEmailJuridicoExistente(cliente.getEmail(), cliente.getIdCliente());
        return this.notificacao.quantidadeNotificacoes() == 0;
    }

    public List<Notificacao> incluirClienteFisico(ClienteFisicoModel cliente) throws Exception {
        try {
            if (validarClienteFisicoInclusao(cliente)) {
                clienteDao.inserirFisico(cliente);
            }
            return this.notificacao.listaNotificacoes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
        public List<Notificacao> incluirClienteJuridico(ClienteJuridicoModel cliente) throws Exception {
        try {
            if (validarClienteJuridicoInclusao(cliente)) {
                clienteDao.inserirJuridico(cliente);
            }
            return this.notificacao.listaNotificacoes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Notificacao> alterarClienteFisico(ClienteFisicoModel cliente) throws Exception {
        try {
            if (validarClienteFisicoAlteracao(cliente)) {
                clienteDao.alterar(cliente);
            }
            return this.notificacao.listaNotificacoes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
        public List<Notificacao> alterarClienteJuridico(ClienteJuridicoModel cliente) throws Exception {
        try {
            if (validarClienteJuridicoAlteracao(cliente)) {
                clienteDao.alterar(cliente);
            }
            return this.notificacao.listaNotificacoes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ClienteModel obterClientePorIdFisico(int id) {
        return clienteDao.obterPorIdFisico(id);
    }
        public ClienteModel obterClientePorIdJuridico(int id) {
        return clienteDao.obterPorIdJuridico(id);
    }

    public ClienteModel obterClientePorCpf(String cpf) {
        return clienteDao.obterPorCpf(cpf);
    }
    public ClienteModel obterClientePorCnpj(String cnpj) {
        return clienteDao.obterPorCnpj(cnpj);
    }

    public List<ClienteModel> obterListaClientesFisico() {
        return clienteDao.obterTodasFisico();
    }
        public List<ClienteModel> obterListaClientesJuridico() {
        return clienteDao.obterTodasJuridico();
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
