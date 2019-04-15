/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO;

import Senac.TadesGames.DAO.Interfaces.IPedidoDao;
import Senac.TadesGames.Data.ConexaoDB;
import Senac.TadesGames.Model.PedidoModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gi
 */
public class PedidoDAO implements IPedidoDao {

    private ConexaoDB conexao = new ConexaoDB();
    private PreparedStatement stmt = null;
    ResultSet rs = null;

    @Override
    public PedidoModel obterPorId(int id) {
        Connection conn = conexao.getConnection();
        PedidoModel pedido = null;

        try {
            stmt = conn.prepareStatement("SELECT IDPEDIDO, "
                    + "FORMAPAGAMENTO, "
                    + "STATUSPEDIDO, "
                    + "DATAPEDIDO, "
                    + "IDCLIENTE, "
                    + "IDFILIAL, "
                    + "IDUSUARIO,"
                    + "FROM PEDIDO WHERE IDPEDIDO = ?");

            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                pedido = new PedidoModel(
                        rs.getInt("IdPedido"),
                        rs.getInt("StatusPedido"),
                        rs.getDate("DataPedido"),
                        rs.getInt("IdCliente"),
                        rs.getInt("IdFilial"),
                        rs.getInt("IdUsuario"),
                        rs.getInt("FormaPagamento")
                );
                
                ItensPedidoDAO itensPedidoDao = new ItensPedidoDAO();
                pedido.setItensPedido(itensPedidoDao.obterPorIdPedido(pedido.getIdPedido()));
            }

            return pedido;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    @Override
    public List<PedidoModel> obterTodos() {
        Connection conn = conexao.getConnection();
        PedidoModel pedido = null;
        List<PedidoModel> pedidos = new ArrayList<PedidoModel>();

        try {
            stmt = conn.prepareStatement("SELECT IDPEDIDO, "
                    + "FORMAPAGAMENTO, "
                    + "STATUSPEDIDO, "
                    + "DATAPEDIDO, "
                    + "IDCLIENTE, "
                    + "IDFILIAL, "
                    + "IDUSUARIO,"
                    + "FROM PEDIDO WHERE IDPEDIDO = ?");

            rs = stmt.executeQuery();
            while (rs.next()) {
                pedido = new PedidoModel(
                        rs.getInt("IdPedido"),
                        rs.getInt("StatusPedido"),
                        rs.getDate("DataPedido"),
                        rs.getInt("IdCliente"),
                        rs.getInt("IdFilial"),
                        rs.getInt("IdUsuario"),
                        rs.getInt("FormaPagamento")
                );
            }

            return pedidos;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    @Override
    public void inserir(PedidoModel pedido) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void finalizarPedido() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void cancelarPedido() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void troca(PedidoModel pedido) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
