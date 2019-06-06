/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.dao;

import br.com.senac.tadesgames.dao.interfaces.IRelatorioVendasDao;
import br.com.senac.tadesgames.data.ConexaoDB;
import br.com.senac.tadesgames.helpers.Utils;
import br.com.senac.tadesgames.models.GraficoMelhoresVendedoresModel;
import br.com.senac.tadesgames.models.GraficoVendasFilialModel;
import br.com.senac.tadesgames.models.RelatorioVendasModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Gi
 */
public class RelatorioVendasDAO implements IRelatorioVendasDao {

    private final ConexaoDB conexao;
    private final PedidoDAO pedidoDao;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public RelatorioVendasDAO() {
        this.conexao = new ConexaoDB();
        this.pedidoDao = new PedidoDAO();
    }

    @Override
    public List<RelatorioVendasModel> obterPorData(Date dataInicio, Date dataFim) {
        Connection conn = conexao.getConnection();
        RelatorioVendasModel relatorioVenda = null;
        Utils util = new Utils();
        List<RelatorioVendasModel> lista = new ArrayList<RelatorioVendasModel>();

        try {
            stmt = conn.prepareStatement("select\n"
                    + "	pedido.IdPedido,\n"
                    + "    sum(itenspedido.Quantidade * itenspedido.ValorUnitario) as valortotal,\n"
                    + " DATE_FORMAT(pedido.datapedido,'%d/%m/%Y') AS dataFormatada\n"
                    + "from pedido\n"
                    + "inner join cliente\n"
                    + "	on cliente.IdCliente = pedido.IdCliente\n"
                    + "inner join filial\n"
                    + "	on filial.IdFilial = pedido.IdFilial\n"
                    + "inner join itenspedido\n"
                    + "	on itenspedido.idpedido = pedido.idpedido\n"
                    + "where\n"
                    + "	pedido.DataPedido between ? and ?\n"
                    + "group by\n"
                    + "	pedido.IdPedido");

            stmt.setString(1, util.converteDateParaStr(dataInicio));
            stmt.setString(2, util.converteDateParaStr(dataFim));

            rs = stmt.executeQuery();
            while (rs.next()) {
                relatorioVenda = new RelatorioVendasModel(
                        rs.getInt("IdPedido"),
                        rs.getDouble("valortotal"),
                        rs.getString("dataFormatada")
                );
                relatorioVenda.setPedido(pedidoDao.obterPorId(relatorioVenda.getIdPedido()));

                if (relatorioVenda.getIdPedido() != 0) {
                    lista.add(relatorioVenda);
                }
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
    public List<RelatorioVendasModel> obterPorData(Date dataInicio, Date dataFim, int idFilial) {
        Connection conn = conexao.getConnection();
        RelatorioVendasModel relatorioVenda = null;
        Utils util = new Utils();
        List<RelatorioVendasModel> lista = new ArrayList<RelatorioVendasModel>();

        try {
            stmt = conn.prepareStatement("select\n"
                    + "	pedido.IdPedido,\n"
                    + "    sum(itenspedido.Quantidade * itenspedido.ValorUnitario) as valortotal,\n"
                    + " DATE_FORMAT(pedido.datapedido,'%d/%m/%Y') AS dataFormatada\n"
                    + "from pedido\n"
                    + "inner join cliente\n"
                    + "	on cliente.IdCliente = pedido.IdCliente\n"
                    + "inner join filial\n"
                    + "	on filial.IdFilial = pedido.IdFilial\n"
                    + "inner join itenspedido\n"
                    + "	on itenspedido.idpedido = pedido.idpedido\n"
                    + "where\n"
                    + "	pedido.DataPedido between ? and ?\n"
                    + " and pedido.idfilial = ?\n"
                    + "group by\n"
                    + "	pedido.IdPedido");

            stmt.setString(1, util.converteDateParaStr(dataInicio));
            stmt.setString(2, util.converteDateParaStr(dataFim));
            stmt.setInt(3, idFilial);

            rs = stmt.executeQuery();
            while (rs.next()) {
                relatorioVenda = new RelatorioVendasModel(
                        rs.getInt("IdPedido"),
                        rs.getDouble("valortotal"),
                        rs.getString("dataFormatada")
                );
                relatorioVenda.setPedido(pedidoDao.obterPorId(relatorioVenda.getIdPedido()));

                if (relatorioVenda.getIdPedido() != 0) {
                    lista.add(relatorioVenda);
                }
            }

            return lista;

        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    public List<GraficoVendasFilialModel> vendasPorFilial(Date dataInicio, Date dataFim) {
        Connection conn = conexao.getConnection();
        GraficoVendasFilialModel relatorioVenda = null;
        Utils util = new Utils();
        List<GraficoVendasFilialModel> lista = new ArrayList<GraficoVendasFilialModel>();

        try {
            stmt = conn.prepareStatement("select \n"
                    + "	filial.nome as filial,\n"
                    + " sum((itenspedido.ValorUnitario * itenspedido.Quantidade)) as total\n"
                    + "from filial\n"
                    + "inner join pedido\n"
                    + "	on pedido.IdFilial = filial.IdFilial\n"
                    + "inner join itenspedido\n"
                    + "	on itenspedido.idpedido = pedido.idpedido\n"
                    + "where pedido.StatusPedido = 1 and pedido.datapedido between ? and ?\n"
                    + "group by filial.nome");

            stmt.setString(1, util.converteDateParaStr(dataInicio));
            stmt.setString(2, util.converteDateParaStr(dataFim));

            rs = stmt.executeQuery();
            while (rs.next()) {
                relatorioVenda = new GraficoVendasFilialModel(
                        rs.getString("filial"),
                        rs.getDouble("total")
                );
                lista.add(relatorioVenda);
            }

            return lista;

        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    public List<GraficoMelhoresVendedoresModel> vendasPorVendedor(Date dataInicio, Date dataFim) {
        Connection conn = conexao.getConnection();
        GraficoMelhoresVendedoresModel relatorioVenda = null;
        Utils util = new Utils();
        List<GraficoMelhoresVendedoresModel> lista = new ArrayList<GraficoMelhoresVendedoresModel>();

        try {
            stmt = conn.prepareStatement("select \n"
                    + "	usuario.nome as vendedor,\n"
                    + "    filial.Nome as filial,\n"
                    + "    count(pedido.IdPedido) as qtdvendas\n"
                    + "from usuario\n"
                    + "inner join pedido\n"
                    + "	on pedido.IdUsuario = usuario.IdUsuario\n"
                    + "inner join filial\n"
                    + "	on filial.IdFilial = usuario.IdFilial\n"
                    + "where usuario.cargo = 'Vendedor (a)'\n"
                    + "and pedido.StatusPedido = 1 and pedido.datapedido between ? and ?\n"
                    + "group by usuario.nome, filial.nome");

            stmt.setString(1, util.converteDateParaStr(dataInicio));
            stmt.setString(2, util.converteDateParaStr(dataFim));

            rs = stmt.executeQuery();
            while (rs.next()) {
                relatorioVenda = new GraficoMelhoresVendedoresModel(
                        rs.getString("vendedor"),
                        rs.getInt("qtdvendas"),
                        rs.getString("filial")
                );
                lista.add(relatorioVenda);
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
