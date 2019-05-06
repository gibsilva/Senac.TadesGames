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

    private final CategoriaDAO CategoriaDao;
    private final Notificacao notificacao;

    public CategoriaService() {
        this.CategoriaDao = new CategoriaDAO();
        this.notificacao = new Notificacao();
    }

    private void validaNomeExistente(String nome) {
        if (CategoriaDao.obterPoNome(nome) != null) {
            this.notificacao.adicionaNotificacao("nome", "Categoria já está cadastrado");
        }
    }

    private void validaNomeExistente(String nome, int id) {
        if (CategoriaDao.obterPoNome(nome, id) != null) {
            this.notificacao.adicionaNotificacao("nome", "Categoria já está cadastrado");
        }
    }

    public List<CategoriaModel> obterListaCategorias() {
        return CategoriaDao.obterTodas();
    }

    public List<Notificacao> incluirCategoria(CategoriaModel categoria) throws Exception {
        try {
            if (validarInclusaoCategoria(categoria)) {
                CategoriaDao.inserir(categoria);
            }
            return this.notificacao.listaNotificacoes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private boolean validarInclusaoCategoria(CategoriaModel categoria) {
        validaNomeExistente(categoria.getNome());

        return this.notificacao.quantidadeNotificacoes() == 0;
    }

    private boolean validarCategoriaAlteracao(CategoriaModel categoria) {
        validaNomeExistente(categoria.getNome(), categoria.getIdCategoria());
        return this.notificacao.quantidadeNotificacoes() == 0;
    }

    public List<Notificacao> alterarCategoria(CategoriaModel categoria) throws Exception {
        try {
            if (validarCategoriaAlteracao(categoria)) {
                CategoriaDao.alterar(categoria);
            }
            return this.notificacao.listaNotificacoes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public CategoriaModel obterPorId(int id) {
        return CategoriaDao.obterPorId(id);
    }

    public void limparNotificacoes() {
        this.notificacao.limparLista();
    }
}
