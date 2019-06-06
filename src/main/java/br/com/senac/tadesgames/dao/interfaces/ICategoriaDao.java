/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.dao.interfaces;

import br.com.senac.tadesgames.models.CategoriaModel;
import java.util.List;

/**
 *
 * @author Gi
 */
public interface ICategoriaDao {
    CategoriaModel obterPorId(int id);
    List<CategoriaModel> obterTodas();
    void inserir(CategoriaModel categoria);
    void alterar(CategoriaModel categoria);
    void excluir(CategoriaModel categoria);    
}
