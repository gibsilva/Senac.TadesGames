/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Service;

import Senac.TadesGames.DAO.GeneroDAO;
import Senac.TadesGames.Helpers.Notificacao;
import Senac.TadesGames.Models.GeneroModel;
import java.util.List;

/**
 *
 * @author Marcel
 */
public class GeneroService {

    private final GeneroDAO GeneroDao;
    private final Notificacao notificacao;

    public GeneroService() {
        this.GeneroDao = new GeneroDAO();
        this.notificacao = new Notificacao();
    }

    private void validaNomeExistente(String nome) {
        if (GeneroDao.obterPoNome(nome) != null) {
            this.notificacao.adicionaNotificacao("nome", "Gênero já está cadastrado");
        }
    }

    private void validaNomeExistente(String nome, int id) {
        if (GeneroDao.obterPoNome(nome, id) != null) {
            this.notificacao.adicionaNotificacao("nome", "Gênero já está cadastrado");
        }
    }

    public List<GeneroModel> obterListaGeneros() {
        return GeneroDao.obterTodas();
    }

    public List<Notificacao> incluirGenero(GeneroModel genero) throws Exception {
        try {
            if (validarInclusaoGenero(genero)) {
                GeneroDao.inserir(genero);
            }
            return this.notificacao.listaNotificacoes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private boolean validarInclusaoGenero(GeneroModel genero) {
        validaNomeExistente(genero.getNome());

        return this.notificacao.quantidadeNotificacoes() == 0;
    }

    private boolean validarGeneroAlteracao(GeneroModel genero) {
        validaNomeExistente(genero.getNome(), genero.getIdGenero());
        return this.notificacao.quantidadeNotificacoes() == 0;
    }

    public List<Notificacao> alterarGenero(GeneroModel genero) throws Exception {
        try {
            if (validarGeneroAlteracao(genero)) {
                GeneroDao.alterar(genero);
            }
            return this.notificacao.listaNotificacoes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public GeneroModel obterPorId(int id) {
        return GeneroDao.obterPorId(id);
    }

    public void limparNotificacoes() {
        this.notificacao.limparLista();
    }
}
