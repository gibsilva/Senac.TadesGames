/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.dao;

import br.com.senac.tadesgames.dao.interfaces.IHomeDao;
import br.com.senac.tadesgames.data.ConexaoDB;
import br.com.senac.tadesgames.models.HomeProdutoModel;
import br.com.senac.tadesgames.models.HomeVendaModel;
import br.com.senac.tadesgames.models.HomeVendedorModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Gi
 */
public class HomeDAO implements IHomeDao {

    private final ConexaoDB conexao;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public HomeDAO() {
        this.conexao = new ConexaoDB();
    }

    @Override
    public HomeVendedorModel obterHomeVendedor() {
        Connection conn = conexao.getConnection();
        HomeVendedorModel home = null;

        try {
            stmt = conn.prepareStatement("SELECT\n"
                    + "	(select count(p.idpedido) from pedido p where p.idusuario = usuario.idusuario and p.statuspedido = 1 and month(now()) = month(p.datapedido)) as QtdVendas, \n"
                    + "    usuario.IdUsuario,\n"
                    + "    usuario.nome as Nome,\n"
                    + "    sum(ValorUnitario * quantidade) as TotalVendido,\n"
                    + "    usuario.IdFilial\n"
                    + "from pedido\n"
                    + "inner join usuario\n"
                    + "    on usuario.IdUsuario = pedido.IdUsuario\n"
                    + "inner join itenspedido\n"
                    + "    on itenspedido.idpedido = pedido.idpedido\n"
                    + "where pedido.statuspedido != 0 and month(now()) = month(pedido.datapedido)\n"
                    + "group by \n"
                    + "	pedido.idusuario\n"
                    + "order by \n"
                    + "	sum(ValorUnitario * quantidade) desc\n"
                    + "	limit 1");

            rs = stmt.executeQuery();

            while (rs.next()) {
                home = new HomeVendedorModel(
                        rs.getInt("QtdVendas"),
                        rs.getString("Nome"),
                        rs.getDouble("TotalVendido"),
                        rs.getInt("IdFilial"),
                        rs.getInt("IdUsuario")
                );
            }

            return home;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public HomeVendedorModel obterHomeVendedorPorIdFilial(int idFilial) {
        Connection conn = conexao.getConnection();
        HomeVendedorModel home = null;

        try {
            stmt = conn.prepareStatement("SELECT\n"
                    + "	(select count(p.idpedido) from pedido p where p.idusuario = usuario.idusuario and p.statuspedido = 1 and month(now()) = month(p.datapedido)) as QtdVendas, \n"
                    + "    usuario.IdUsuario,\n"
                    + "    usuario.nome as Nome,\n"
                    + "    sum(ValorUnitario * quantidade) as TotalVendido,\n"
                    + "    usuario.IdFilial\n"
                    + "from pedido\n"
                    + "inner join usuario\n"
                    + "    on usuario.IdUsuario = pedido.IdUsuario\n"
                    + "inner join itenspedido\n"
                    + "    on itenspedido.idpedido = pedido.idpedido\n"
                    + "where pedido.statuspedido != 0 and pedido.idfilial = ? \n"
                    + "and month(now()) = month(pedido.datapedido)\n"
                    + "group by \n"
                    + "	pedido.idusuario\n"
                    + "order by \n"
                    + "	sum(ValorUnitario * quantidade) desc\n"
                    + "	limit 1");

            stmt.setInt(1, idFilial);

            rs = stmt.executeQuery();

            while (rs.next()) {
                home = new HomeVendedorModel(
                        rs.getInt("QtdVendas"),
                        rs.getString("Nome"),
                        rs.getDouble("TotalVendido"),
                        rs.getInt("IdFilial"),
                        rs.getInt("IdUsuario")
                );
            }

            return home;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public HomeProdutoModel obterHomeProduto() {
        Connection conn = conexao.getConnection();
        HomeProdutoModel home = null;

        try {
            stmt = conn.prepareStatement("select \n"
                    + "	count(*) as qtdVendida,\n"
                    + "    (select sum(p.quantidadeestoque) from produto p) as qtdEstoque\n"
                    + "from itenspedido \n"
                    + "inner join pedido on pedido.IdPedido = itenspedido.IdPedido\n"
                    + "where pedido.StatusPedido = 1 and month(pedido.DataPedido) = month(now())");

            rs = stmt.executeQuery();

            while (rs.next()) {
                home = new HomeProdutoModel(
                        rs.getInt("qtdVendida"),
                        rs.getInt("qtdEstoque")
                );
            }

            return home;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public HomeProdutoModel obterHomeProduto(int idFilial) {
        Connection conn = conexao.getConnection();
        HomeProdutoModel home = null;

        try {
            stmt = conn.prepareStatement("select \n"
                    + "	count(*) as qtdVendida,\n"
                    + "    (select sum(p.quantidadeestoque) from produto p where p.idfilial = pedido.idfilial) as qtdEstoque\n"
                    + "from itenspedido \n"
                    + "inner join pedido on pedido.IdPedido = itenspedido.IdPedido\n"
                    + "where pedido.StatusPedido = 1 and pedido.idfilial = ? and month(pedido.DataPedido) = month(now())");

            stmt.setInt(1, idFilial);

            rs = stmt.executeQuery();

            while (rs.next()) {
                home = new HomeProdutoModel(
                        rs.getInt("qtdVendida"),
                        rs.getInt("qtdEstoque")
                );
            }

            return home;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public HomeVendaModel obterHomeVendas() {
        Connection conn = conexao.getConnection();
        HomeVendaModel home = null;

        try {
            stmt = conn.prepareStatement("select \n"
                    + "	(select count(1) from pedido where statuspedido = 1 and month(pedido.datapedido) = month(now())) as qtdVendas,\n"
                    + "    sum((itenspedido.ValorUnitario * itenspedido.Quantidade)) as totalTendido\n"
                    + "from pedido\n"
                    + "inner join itenspedido\n"
                    + "	on itenspedido.IdPedido = pedido.IdPedido\n"
                    + "where\n"
                    + "	month(pedido.datapedido) = month(now())\n"
                    + "and pedido.statuspedido = 1");

            rs = stmt.executeQuery();

            while (rs.next()) {
                home = new HomeVendaModel(
                        rs.getInt("qtdVendas"),
                        rs.getDouble("totalTendido")
                );
            }

            return home;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public HomeVendaModel obterHomeVendas(int idFilial) {
        Connection conn = conexao.getConnection();
        HomeVendaModel home = null;

        try {
            stmt = conn.prepareStatement("select \n"
                    + "	(select count(1) from pedido where statuspedido = 1 and month(pedido.datapedido) = month(now()) and pedido.idfilial = ?) as qtdVendas,\n"
                    + "    sum((itenspedido.ValorUnitario * itenspedido.Quantidade)) as totalTendido\n"
                    + "from pedido\n"
                    + "inner join itenspedido\n"
                    + "	on itenspedido.IdPedido = pedido.IdPedido\n"
                    + "where\n"
                    + "	month(pedido.datapedido) = month(now()) and pedido.idfilial = ?\n"
                    + "and pedido.statuspedido = 1");
            
            stmt.setInt(1, idFilial);
            stmt.setInt(2, idFilial);

            rs = stmt.executeQuery();

            while (rs.next()) {
                home = new HomeVendaModel(
                        rs.getInt("qtdVendas"),
                        rs.getDouble("totalTendido")
                );
            }

            return home;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }
}
