/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO.Interfaces;

import Senac.TadesGames.Models.GeneroModel;
import java.util.List;

/**
 *
 * @author Gi
 */
public interface IGeneroDao {
    GeneroModel obterPorId(int id);
    List<GeneroModel> obterTodas();
    void inserir(GeneroModel genero);
    void alterar(GeneroModel genero);
    void excluir(GeneroModel genero); 
    
}
