/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Service;

import Senac.TadesGames.DAO.CategoriaDAO;
import Senac.TadesGames.Helpers.Notificacao;
import Senac.TadesGames.Models.CategoriaModel;
import java.util.List;

/**
 *
 * @author Marcel
 */
public class CategoriaService {

    private final CategoriaDAO categoriaDao;
    private final Notificacao notificacao;

    public CategoriaService() {
        this.categoriaDao = new CategoriaDAO();
        this.notificacao = new Notificacao();
    }

    private void validaNomeExistente(String nome) {
        if (categoriaDao.obterPoNome(nome) != null) {
            this.notificacao.adicionaNotificacao("nome", "nome já está cadastrado");
        }
    }

    private void validaNomeExistente(String nome, int id) {
        if (categoriaDao.obterPoNome(nome, id) != null) {
            this.notificacao.adicionaNotificacao("nome", "nome já está cadastrado");
        }
    }

    private boolean validarCategoria(CategoriaModel categoria) {
        validaNomeExistente(categoria.getNome());

        return this.notificacao.quantidadeNotificacoes() == 0;
    }

    private boolean validarCategoriaAlteracao(CategoriaModel categoria) {
        validaNomeExistente(categoria.getNome(), categoria.getIdCategoria());
        return this.notificacao.quantidadeNotificacoes() == 0;
    }

    public List<Notificacao> incluirCategoria(CategoriaModel categoria) throws Exception {
        try {
            if (validarCategoria(categoria)) {
                categoriaDao.inserir(categoria);
            }
            return this.notificacao.listaNotificacoes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<Notificacao> alterarCategoria(CategoriaModel categoria) throws Exception {
        try {
            if (validarCategoriaAlteracao(categoria)) {
                categoriaDao.alterar(categoria);
            }
            return this.notificacao.listaNotificacoes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public CategoriaModel obterCategoriaPorId(int id) {
        return categoriaDao.obterPorId(id);
    }
    
    public void excluirCategoria(CategoriaModel categoria){
        categoriaDao.excluir(categoria);
    }

    public List<CategoriaModel> obterListaCategoria() {
        return categoriaDao.obterTodas();
    }

    public void limparNotificacoes() {
        this.notificacao.limparLista();
    }
}
