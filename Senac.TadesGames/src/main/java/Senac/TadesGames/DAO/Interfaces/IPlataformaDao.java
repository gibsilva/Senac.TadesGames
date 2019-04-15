/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO.Interfaces;

import Senac.TadesGames.Model.PlataformaModel;
import java.util.List;

/**
 *
 * @author Gi
 */
public interface IPlataformaDao {
    
    PlataformaModel obterPorId(int id);
    List<PlataformaModel> obterTodas();
    void inserir(PlataformaModel plataforma);
    void alterar(PlataformaModel plataforma);
    void excluir(PlataformaModel plataforma);
    
}
