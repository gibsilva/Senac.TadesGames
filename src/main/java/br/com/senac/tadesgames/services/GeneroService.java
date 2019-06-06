/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.services;

import br.com.senac.tadesgames.dao.GeneroDAO;
import br.com.senac.tadesgames.helpers.Notificacao;
import br.com.senac.tadesgames.models.GeneroModel;
import java.util.List;

/**
 *
 * @author Marcel
 */
public class GeneroService {
     private final GeneroDAO generoDao;
    private final Notificacao notificacao;

    public GeneroService() {
        this.generoDao = new GeneroDAO();
        this.notificacao = new Notificacao();
    }

    private void validaNomeExistente(String nome) {
        if (generoDao.obterPorNome(nome) != null) {
            this.notificacao.adicionaNotificacao("nome", "Nome já está cadastrado");
        }
    }

    private void validaNomeExistente(String nome, int id) {
        if (generoDao.obterPorNome(nome, id) != null) {
            this.notificacao.adicionaNotificacao("nome", "Nome já está cadastrado");
        }
    }

    private boolean validarGenero(GeneroModel genero) {
        validaNomeExistente(genero.getNome());

        return this.notificacao.quantidadeNotificacoes() == 0;
    }

    private boolean validarGeneroAlteracao(GeneroModel genero) {
        validaNomeExistente(genero.getNome(), genero.getIdGenero());
        return this.notificacao.quantidadeNotificacoes() == 0;
    }

    public List<Notificacao> incluirGenero(GeneroModel genero) throws Exception {
        try {
            if (validarGenero(genero)) {
                generoDao.inserir(genero);
            }
            return this.notificacao.listaNotificacoes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Notificacao> alterarGenero(GeneroModel genero) throws Exception {
        try {
            if (validarGeneroAlteracao(genero)) {
                generoDao.alterar(genero);
            }
            return this.notificacao.listaNotificacoes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
    
    public void excluirGenero(GeneroModel genero) {
        generoDao.excluir(genero);
    }

    public GeneroModel obterGeneroPorId(int id) {
        return generoDao.obterPorId(id);
    }

    public List<GeneroModel> obterListaGenero() {
        return generoDao.obterTodas();
    }

    public void limparNotificacoes() {
        this.notificacao.limparLista();
    }
}
    
