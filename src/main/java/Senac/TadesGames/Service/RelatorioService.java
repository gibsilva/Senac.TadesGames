/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Service;

import Senac.TadesGames.DAO.RelatorioClienteDAO;
import Senac.TadesGames.DAO.RelatorioProdutoDAO;
import Senac.TadesGames.DAO.RelatorioVendasDAO;
import Senac.TadesGames.Models.RelatorioClienteModel;
import Senac.TadesGames.Models.RelatorioProdutoModel;
import Senac.TadesGames.Models.RelatorioVendasModel;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Giovanni.Carignato
 */
public class RelatorioService {

    private final RelatorioVendasDAO relatorioVendasDao;
    private final RelatorioProdutoDAO relatorioProdutoDao;
    private final RelatorioClienteDAO relatorioClienteDao;
    
    public RelatorioService(){
        this.relatorioVendasDao = new RelatorioVendasDAO();
        this.relatorioClienteDao = new RelatorioClienteDAO();
        this.relatorioProdutoDao = new RelatorioProdutoDAO();
    }
    
    public List<RelatorioVendasModel> obterPorDataRelatorioVendas(Date dataInicio, Date dataFim){
        return relatorioVendasDao.obterPorData(dataInicio, dataFim);
    }
    
    public List<RelatorioProdutoModel> obterPorDataRelatorioProduto(Date dataInicio, Date dataFim){
        return relatorioProdutoDao.obterPorData(dataInicio, dataFim);
    }
    
    public List<RelatorioClienteModel> obterPorDataRelatorioCliente(Date dataInicio, Date dataFim){
        return relatorioClienteDao.obterPorData(dataInicio, dataFim);
    }
}
