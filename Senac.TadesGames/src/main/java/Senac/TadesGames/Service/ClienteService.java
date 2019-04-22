/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Service;

import Senac.TadesGames.DAO.ClienteDAO;
import Senac.TadesGames.Models.ClienteModel;
import java.util.InputMismatchException;

/**
 *
 * @author Gi
 */
public class ClienteService {

    private final ClienteDAO clienteDao;

    public ClienteService() {
        this.clienteDao = new ClienteDAO();
    }

    private boolean validaEmailExistente(String email) {
        return this.clienteDao.obterPorEmail(email) != null;
    }

    private boolean validaCpfExistente(String cpf) {
        return this.clienteDao.obterPorCpf(cpf) != null;
    }

    public boolean validar(ClienteModel cliente) {
        return !(validaEmailExistente(cliente.getEmail()) || validarCamposNulosOuVazios(cliente.getNome(), cliente.getCpf(), cliente.getCelular(), cliente.getSexo())
                || validaCpfExistente(cliente.getCpf()) || validarCpf(cliente.getCpf()));
    }

    public boolean incluirCliente(ClienteModel cliente) throws Exception {
        try {
            if (validar(cliente)) {
                clienteDao.inserir(cliente);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private boolean validarCamposNulosOuVazios(String nome, String cpf, String celular, String sexo) {
        return !(nome.equals("") || cpf.equals("") || celular.equals("") || sexo.equals(""));
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
