/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.dao;

import br.com.senac.tadesgames.dao.interfaces.IRelatorioProdutoDao;
import br.com.senac.tadesgames.data.ConexaoDB;
import br.com.senac.tadesgames.helpers.Utils;
import br.com.senac.tadesgames.models.GraficoProdutosModel;
import br.com.senac.tadesgames.models.RelatorioProdutoModel;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gi
 */
public class RelatorioProdutoDAO implements IRelatorioProdutoDao {

    private final ConexaoDB conexao;
    private final ProdutoDAO produtoDao;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public RelatorioProdutoDAO() {
        this.conexao = new ConexaoDB();
        this.produtoDao = new ProdutoDAO();
    }

    @Override
    public List<RelatorioProdutoModel> obterPorData(Date dataInicio, Date dataFim) {
        Connection conn = conexao.getConnection();
        RelatorioProdutoModel relatorioProduto = null;
        Utils util = new Utils();
        List<RelatorioProdutoModel> lista = new ArrayList<RelatorioProdutoModel>();

        try {
            stmt = conn.prepareStatement("SELECT   \n"
                    + "	produto.IDPRODUTO,\n"
                    + "	produto.NOME,\n"
                    + "	(SELECT SUM(I.QUANTIDADE) FROM itenspedido I WHERE I.IDPRODUTO = produto.IDPRODUTO) AS QTDPRODUTO,\n"
                    + "	SUM(itenspedido.VALORUNITARIO * itenspedido.QUANTIDADE) AS TOTALVENDIDO,\n"
                    + "	categoria.NOME AS CATEGORIA,\n"
                    + "	produto.ATIVO,\n"
                    + "	DATE_FORMAT((select i.datahoracriacao from itenspedido i where i.idproduto = produto.idproduto order by i.datahoracriacao desc limit 1), '%d/%m/%Y') AS DataUltimaVenda,\n"
                    + "	DATE_FORMAT(produto.DATAHORACRIACAO, '%d/%m/%Y') AS DATAHORACRIACAO\n"
                    + "FROM\n"
                    + "    produto\n"
                    + "INNER JOIN itenspedido\n"
                    + "    ON itenspedido.IDPRODUTO = produto.IDPRODUTO\n"
                    + "INNER JOIN categoria\n"
                    + "    ON categoria.IDCATEGORIA = produto.IDCATEGORIA\n"
                    + "INNER JOIN pedido\n"
                    + "    ON pedido.idpedido = itenspedido.idpedido\n"
                    + "WHERE\n"
                    + "    DATE(itenspedido.datahoracriacao) BETWEEN ? AND ?\n"
                    + "    and pedido.statuspedido = 1\n"
                    + "GROUP BY\n"
                    + "    produto.IDPRODUTO,\n"
                    + "    produto.DataHoraCriacao");

            stmt.setString(1, util.converteDateParaStr(dataInicio));
            stmt.setString(2, util.converteDateParaStr(dataFim));

            rs = stmt.executeQuery();
            while (rs.next()) {
                relatorioProduto = new RelatorioProdutoModel(
                        rs.getInt("IdProduto"),
                        rs.getString("nome"),
                        rs.getInt("qtdProduto"),
                        rs.getDouble("totalVendido"),
                        rs.getString("categoria"),
                        rs.getString("DataUltimaVenda"),
                        rs.getBoolean("ativo")
                );
                relatorioProduto.setProduto(produtoDao.obterPorId(relatorioProduto.getIdProduto()));
                lista.add(relatorioProduto);
            }

            return lista;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public List<RelatorioProdutoModel> obterPorData(Date dataInicio, Date dataFim, int idFilial) {
        Connection conn = conexao.getConnection();
        RelatorioProdutoModel relatorioProduto = null;
        Utils util = new Utils();
        List<RelatorioProdutoModel> lista = new ArrayList<RelatorioProdutoModel>();

        try {
            stmt = conn.prepareStatement("SELECT   \n"
                    + "	produto.IDPRODUTO,\n"
                    + "	produto.NOME,\n"
                    + "	(SELECT SUM(I.QUANTIDADE) FROM itenspedido I WHERE I.IDPRODUTO = produto.IDPRODUTO) AS QTDPRODUTO,\n"
                    + "	SUM(itenspedido.VALORUNITARIO * itenspedido.QUANTIDADE) AS TOTALVENDIDO,\n"
                    + "	categoria.NOME AS CATEGORIA,\n"
                    + "	produto.ATIVO,\n"
                    + "	DATE_FORMAT((select i.datahoracriacao from itenspedido i where i.idproduto = produto.idproduto order by i.datahoracriacao desc limit 1), '%d/%m/%Y') AS DataUltimaVenda,\n"
                    + "	DATE_FORMAT(produto.DATAHORACRIACAO, '%d/%m/%Y') AS DATAHORACRIACAO\n"
                    + "FROM\n"
                    + "    produto\n"
                    + "INNER JOIN itenspedido\n"
                    + "    ON itenspedido.IDPRODUTO = produto.IDPRODUTO\n"
                    + "INNER JOIN categoria\n"
                    + "    ON categoria.IDCATEGORIA = produto.IDCATEGORIA\n"
                    + "INNER JOIN pedido\n"
                    + "    ON pedido.idpedido = itenspedido.idpedido\n"
                    + "WHERE\n"
                    + "    DATE(itenspedido.datahoracriacao) BETWEEN ? AND ?\n"
                    + "    and produto.idfilial = ?\n"
                    + "    and pedido.statuspedido = 1\n"
                    + "GROUP BY\n"
                    + "    produto.IDPRODUTO,\n"
                    + "    produto.DataHoraCriacao");

            stmt.setString(1, util.converteDateParaStr(dataInicio));
            stmt.setString(2, util.converteDateParaStr(dataFim));
            stmt.setInt(3, idFilial);

            rs = stmt.executeQuery();
            while (rs.next()) {
                relatorioProduto = new RelatorioProdutoModel(
                        rs.getInt("IdProduto"),
                        rs.getString("nome"),
                        rs.getInt("qtdProduto"),
                        rs.getDouble("totalVendido"),
                        rs.getString("categoria"),
                        rs.getString("DataUltimaVenda"),
                        rs.getBoolean("ativo")
                );
                relatorioProduto.setProduto(produtoDao.obterPorId(relatorioProduto.getIdProduto()));
                lista.add(relatorioProduto);
            }

            return lista;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    public List<GraficoProdutosModel> obterProdutosVendidos(Date dataInicio, Date dataFim) {
        Connection conn = conexao.getConnection();
        GraficoProdutosModel relatorioProduto = null;
        List<GraficoProdutosModel> lista = new ArrayList<GraficoProdutosModel>();
        Utils util = new Utils();
        
        try {
            stmt = conn.prepareStatement("select \n"
                    + "	produto.nome as produto,\n"
                    + "    count(pedido.idpedido) as qtdVendido\n"
                    + "from produto\n"
                    + "inner join itenspedido\n"
                    + "	on itenspedido.IdProduto = produto.IdProduto\n"
                    + "inner join pedido\n"
                    + "	on pedido.IdPedido = itenspedido.IdPedido\n"
                    + "where\n"
                    + "	pedido.StatusPedido = 1 and pedido.datapedido between ? and ?\n"
                    + "    and produto.Ativo = 1\n"
                    + "group by\n"
                    + "	produto.nome");

            stmt.setString(1, util.converteDateParaStr(dataInicio));
            stmt.setString(2, util.converteDateParaStr(dataFim));

            rs = stmt.executeQuery();
            while (rs.next()) {
                relatorioProduto = new GraficoProdutosModel(
                        rs.getString("produto"),
                        rs.getInt("qtdVendido")
                );
                lista.add(relatorioProduto);
            }

            return lista;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }
}
