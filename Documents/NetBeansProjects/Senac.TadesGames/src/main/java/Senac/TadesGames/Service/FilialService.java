/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Service;

import Senac.TadesGames.DAO.FilialDAO;
import Senac.TadesGames.Helpers.Notificacao;
import Senac.TadesGames.Models.FilialModel;
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
}
