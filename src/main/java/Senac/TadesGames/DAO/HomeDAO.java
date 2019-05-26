/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO;

import Senac.TadesGames.Data.ConexaoDB;
import Senac.TadesGames.Models.HomeModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Gi
 */
public class HomeDAO {

    private final ConexaoDB conexao;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public HomeDAO() {
        this.conexao = new ConexaoDB();
    }

    public HomeModel obterHomeVendedor() {
        Connection conn = conexao.getConnection();
        HomeModel home = null;

        try {
            stmt = conn.prepareStatement("SELECT\n"
                    + "	count(pedido.idUsuario) as QtdVendas, \n"
                    + "    usuario.IdUsuario,\n"
                    + "    usuario.nome as Nome,\n"
                    + "    sum(ValorUnitario * quantidade) as TotalVendido,\n"
                    + "    usuario.IdFilial\n"
                    + "from pedido\n"
                    + "inner join usuario\n"
                    + "	on usuario.IdUsuario = pedido.IdUsuario\n"
                    + "inner join itenspedido\n"
                    + "	on itenspedido.idpedido = pedido.idpedido\n"
                    + "group by pedido.idusuario\n"
                    + "order by sum(ValorUnitario * quantidade) desc\n"
                    + "limit 1");

            rs = stmt.executeQuery();

            while (rs.next()) {
                home = new HomeModel(
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
}
