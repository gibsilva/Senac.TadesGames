/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Service;

import Senac.TadesGames.DAO.PlataformaDAO;
import Senac.TadesGames.Helpers.Notificacao;
import Senac.TadesGames.Models.PlataformaModel;
import java.util.List;

/**
 *
 * @author Gi
 */
public class PlataformaService {

    private final PlataformaDAO plataformaDao;
    private final Notificacao notificacao;

    public PlataformaService() {
        this.plataformaDao = new PlataformaDAO();
        this.notificacao = new Notificacao();
    }

    private void validaNomeExistente(String nome) {
        if (plataformaDao.obterPoNome(nome) != null) {
            this.notificacao.adicionaNotificacao("nome", "nome já está cadastrado");
        }
    }

    private void validaNomeExistente(String nome, int id) {
        if (plataformaDao.obterPoNome(nome, id) != null) {
            this.notificacao.adicionaNotificacao("nome", "nome já está cadastrado");
        }
    }

    private boolean validarPlataforma(PlataformaModel plataforma) {
        validaNomeExistente(plataforma.getNome());

        return this.notificacao.quantidadeNotificacoes() == 0;
    }

    private boolean validarPlataformaAlteracao(PlataformaModel plataforma) {
        validaNomeExistente(plataforma.getNome(), plataforma.getIdPlataforma());
        return this.notificacao.quantidadeNotificacoes() == 0;
    }

    public List<Notificacao> incluirPlataforma(PlataformaModel plataforma) throws Exception {
        try {
            if (validarPlataforma(plataforma)) {
                plataformaDao.inserir(plataforma);
            }
            return this.notificacao.listaNotificacoes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Notificacao> alterarPlataforma(PlataformaModel plataforma) throws Exception {
        try {
            if (validarPlataformaAlteracao(plataforma)) {
                plataformaDao.alterar(plataforma);
            }
            return this.notificacao.listaNotificacoes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public PlataformaModel obterPlataformaPorId(int id) {
        return plataformaDao.obterPorId(id);
    }

    public void excluirPlataforma(PlataformaModel plataforma) {
        plataformaDao.excluir(plataforma);
    }

    public List<PlataformaModel> obterListaPlataforma() {
        return plataformaDao.obterTodas();
    }

    public void limparNotificacoes() {
        this.notificacao.limparLista();
    }
}
