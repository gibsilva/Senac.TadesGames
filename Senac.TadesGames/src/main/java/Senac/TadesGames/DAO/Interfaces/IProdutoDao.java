/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO.Interfaces;

import Senac.TadesGames.Model.ProdutoModel;
import java.util.List;

/**
 *
 * @author Gi
 */
public interface IProdutoDao {
    ProdutoModel obterPorId(int id);
    List<ProdutoModel> obterTodas();
    void inserir(ProdutoModel cliente);
    void alterar(ProdutoModel cliente);
    
}
