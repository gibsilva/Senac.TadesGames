/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO.Interfaces;

import Senac.TadesGames.Models.FilialModel;
import java.util.List;

/**
 *
 * @author Gi
 */
public interface IFilialDao {
    FilialModel obterPorId(int id);
    List<FilialModel> obterTodas();
    void inserir(FilialModel filial);
    void alterar(FilialModel filial); 
}
