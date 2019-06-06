/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.services;

import br.com.senac.tadesgames.dao.RelatorioClienteDAO;
import br.com.senac.tadesgames.dao.RelatorioProdutoDAO;
import br.com.senac.tadesgames.dao.RelatorioVendasDAO;
import br.com.senac.tadesgames.models.GraficoMelhoresVendedoresModel;
import br.com.senac.tadesgames.models.GraficoProdutosModel;
import br.com.senac.tadesgames.models.GraficoVendasFilialModel;
import br.com.senac.tadesgames.models.RelatorioClienteModel;
import br.com.senac.tadesgames.models.RelatorioProdutoModel;
import br.com.senac.tadesgames.models.RelatorioVendasModel;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Gi
 */
public class RelatorioService {

    private final RelatorioVendasDAO relatorioVendasDao;
    private final RelatorioProdutoDAO relatorioProdutoDao;
    private final RelatorioClienteDAO relatorioClienteDao;

    public RelatorioService() {
        this.relatorioVendasDao = new RelatorioVendasDAO();
        this.relatorioClienteDao = new RelatorioClienteDAO();
        this.relatorioProdutoDao = new RelatorioProdutoDAO();
    }

    public List<RelatorioVendasModel> obterPorDataRelatorioVendas(Date dataInicio, Date dataFim) {
        return relatorioVendasDao.obterPorData(dataInicio, dataFim);
    }

    public List<RelatorioVendasModel> obterPorDataRelatorioVendas(Date dataInicio, Date dataFim, int idFilial) {
        return relatorioVendasDao.obterPorData(dataInicio, dataFim, idFilial);
    }

    public List<RelatorioProdutoModel> obterPorDataRelatorioProduto(Date dataInicio, Date dataFim) {
        return relatorioProdutoDao.obterPorData(dataInicio, dataFim);
    }

    public List<RelatorioProdutoModel> obterPorDataRelatorioProduto(Date dataInicio, Date dataFim, int idFilial) {
        return relatorioProdutoDao.obterPorData(dataInicio, dataFim, idFilial);
    }

    public List<RelatorioClienteModel> obterPorDataRelatorioCliente(Date dataInicio, Date dataFim) {
        return relatorioClienteDao.obterPorData(dataInicio, dataFim);
    }

    public List<GraficoVendasFilialModel> vendasPorFilial(Date dataInicio, Date dataFim) {
        return relatorioVendasDao.vendasPorFilial(dataInicio, dataFim);
    }

    public List<GraficoMelhoresVendedoresModel> vendasPorVendedor(Date dataInicio, Date dataFim) {
        return relatorioVendasDao.vendasPorVendedor(dataInicio, dataFim);
    }

    public List<GraficoProdutosModel> obterProdutosVendidos(Date dataInicio, Date dataFim) {
        return relatorioProdutoDao.obterProdutosVendidos(dataInicio, dataFim);
    }
}
