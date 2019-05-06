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
 * @author Marcel
 */
public class PlataformaService {

    private final PlataformaDAO PlataformaDao;
    private final Notificacao notificacao;

    public PlataformaService() {
        this.PlataformaDao = new PlataformaDAO();
        this.notificacao = new Notificacao();
    }

    private void validaNomeExistente(String nome) {
        if (PlataformaDao.obterPoNome(nome) != null) {
            this.notificacao.adicionaNotificacao("nome", "Plataforma já está cadastrado");
        }
    }

    private void validaNomeExistente(String nome, int id) {
        if (PlataformaDao.obterPoNome(nome, id) != null) {
            this.notificacao.adicionaNotificacao("nome", "Plataforma já está cadastrado");
        }
    }

    public List<PlataformaModel> obterListaPlataformas() {
        return PlataformaDao.obterTodas();
    }

    public List<Notificacao> incluirPlataforma(PlataformaModel plataforma) throws Exception {
        try {
            if (validarInclusaoPlataforma(plataforma)) {
                PlataformaDao.inserir(plataforma);
            }
            return this.notificacao.listaNotificacoes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private boolean validarInclusaoPlataforma(PlataformaModel plataforma) {
        validaNomeExistente(plataforma.getNome());

        return this.notificacao.quantidadeNotificacoes() == 0;
    }

    private boolean validarPlataformaAlteracao(PlataformaModel plataforma) {
        validaNomeExistente(plataforma.getNome(), plataforma.getIdPlataforma());
        return this.notificacao.quantidadeNotificacoes() == 0;
    }

    public List<Notificacao> alterarPlataforma(PlataformaModel plataforma) throws Exception {
        try {
            if (validarPlataformaAlteracao(plataforma)) {
                PlataformaDao.alterar(plataforma);
            }
            return this.notificacao.listaNotificacoes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public PlataformaModel obterPorId(int id) {
        return PlataformaDao.obterPorId(id);
    }

    public void limparNotificacoes() {
        this.notificacao.limparLista();
    }
}
