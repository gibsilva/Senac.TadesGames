/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO.Interfaces;

import Senac.TadesGames.Models.RelatorioProdutoModel;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Gi
 */
public interface IRelatorioProdutoDao {

    List<RelatorioProdutoModel> obterPorData(Date dataInicio, Date dataFim);
    List<RelatorioProdutoModel> obterPorData(Date dataInicio, Date dataFim, int idFilial);
}
