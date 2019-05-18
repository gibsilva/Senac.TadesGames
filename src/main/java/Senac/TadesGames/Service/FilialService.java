/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Service;

import Senac.TadesGames.DAO.FilialDAO;
import Senac.TadesGames.Helpers.Notificacao;
import Senac.TadesGames.Models.FilialModel;
import java.util.InputMismatchException;
import java.util.List;

/**
 *
 * @author Gi
 */
public class FilialService {

    private final FilialDAO filialDao;
    private final Notificacao notificacao;

    public FilialService() {
        this.filialDao = new FilialDAO();
        this.notificacao = new Notificacao();
    }

    private void validarCnpjExistente(String cnpj) {
        if (filialDao.obterPorCnpj(cnpj) != null) {
            this.notificacao.adicionaNotificacao("cnpj", "CNPJ já cadastrado");
        }
    }

    private void validarCnpjExistente(String cnpj, int id) {
        if (filialDao.obterPorCnpj(cnpj, id) != null) {
            this.notificacao.adicionaNotificacao("cnpj", "CNPJ já cadastrado");
        }
    }

    public List<Notificacao> incluirFilial(FilialModel filial) throws Exception {
        try {
            if (validarInclusaoFilial(filial)) {
                filialDao.inserir(filial);
            }
            return this.notificacao.listaNotificacoes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Notificacao> alterarFilial(FilialModel filial) throws Exception {
        try {
            if (validarAlteracaoFilial(filial)) {
                filialDao.alterar(filial);
            }
            return this.notificacao.listaNotificacoes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private boolean validarInclusaoFilial(FilialModel filial) {
        if (!validarCnpj(filial.getCnpj())) {
            this.notificacao.adicionaNotificacao("cnpj", "CNPJ inválido, por favor digite um CNPJ válido");
        }
        
        validarCnpjExistente(filial.getCnpj());

        return this.notificacao.quantidadeNotificacoes() == 0;
    }

    private boolean validarAlteracaoFilial(FilialModel filial) {
        validarCnpjExistente(filial.getCnpj(), filial.getIdFilial());

        return this.notificacao.quantidadeNotificacoes() == 0;
    }

    public List<FilialModel> obterListaFiliais() {
        return filialDao.obterTodas();
    }

    public FilialModel obterPorId(int id) {
        return filialDao.obterPorId(id);
    }

    public void limparNotificacoes() {
        this.notificacao.limparLista();
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
