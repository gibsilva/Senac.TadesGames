/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.dao.interfaces;

import br.com.senac.tadesgames.models.ProdutoModel;
import java.util.List;

/**
 *
 * @author Gi
 */
public interface IProdutoDao {
    ProdutoModel obterPorId(int id);
    ProdutoModel obterPorId(int idProduto, int idFilial);
    List<ProdutoModel> obterTodas();
    List<ProdutoModel> obterPorIdFilial(int idFilial);
    void inserir(ProdutoModel produto);
    void alterar(ProdutoModel produto);
    void desativar(ProdutoModel produto);
    void ativar(ProdutoModel produto);
    List<ProdutoModel> obterPorIdCategoria(int id);
    List<ProdutoModel> obterPorIdGenero(int id);
    List<ProdutoModel> obterPorIdPlataforma(int id);
}
