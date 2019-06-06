/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.dao.interfaces;

import br.com.senac.tadesgames.models.RelatorioClienteModel;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Gi
 */
public interface IRelatorioClienteDao {
    List<RelatorioClienteModel> obterPorData(Date dataInicio, Date dataFim);
}
