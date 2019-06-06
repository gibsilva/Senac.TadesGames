/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.dao.interfaces;

import br.com.senac.tadesgames.models.RelatorioVendasModel;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Gi
 */
public interface IRelatorioVendasDao {
    List<RelatorioVendasModel> obterPorData(Date dataInicio, Date dataFim);
    List<RelatorioVendasModel> obterPorData(Date dataInicio, Date dataFim, int idFilial);
}
