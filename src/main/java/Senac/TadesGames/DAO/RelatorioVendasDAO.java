/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO;

import Senac.TadesGames.DAO.Interfaces.IRelatorioVendasDao;
import Senac.TadesGames.Data.ConexaoDB;
import Senac.TadesGames.Helpers.Utils;
import Senac.TadesGames.Models.RelatorioVendasModel;
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
                    + "	pedido.DataPedido between ? and ? and pedido.statuspedido != 0\n"
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
                    + "	pedido.DataPedido between ? and ? and pedido.statuspedido != 0\n"
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
}
