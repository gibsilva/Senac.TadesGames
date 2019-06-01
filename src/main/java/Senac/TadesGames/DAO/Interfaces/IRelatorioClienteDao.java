/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO.Interfaces;

import Senac.TadesGames.Models.RelatorioClienteModel;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Gi
 */
public interface IRelatorioClienteDao {
    List<RelatorioClienteModel> obterPorData(Date dataInicio, Date dataFim);
}
