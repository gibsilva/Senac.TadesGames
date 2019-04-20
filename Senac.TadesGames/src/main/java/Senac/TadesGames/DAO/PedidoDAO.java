/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO;

import Senac.TadesGames.DAO.Interfaces.IPedidoDao;
import Senac.TadesGames.Data.ConexaoDB;
import Senac.TadesGames.Models.PedidoModel;
import java.sql.Connection;
import java.sql.Date;
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

    private final ConexaoDB conexao = new ConexaoDB();
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
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("INSERT INTO PEDIDO ("
                    + "STATUS, "
                    + "DATAPEDIDO, "
                    + "IDCLIENTE, "
                    + "IDFILIAL, "
                    + "IDUSUARIO, "
                    + "FORMAPAGAMENTO)"
                    + "VALUES (?,?,?,?,?,?)");
            stmt.setInt(1, pedido.getStatus());
            stmt.setDate(2, (Date) pedido.getDataPedido());
            stmt.setInt(3, pedido.getIdCliente());
            stmt.setInt(4, pedido.getIdFilial());
            stmt.setInt(5, pedido.getIdUsuario());
            stmt.setInt(6, pedido.getFormaPagamento());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void troca(PedidoModel pedido) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public void FinalizarPedido(PedidoModel pedido) {
           Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("UPDATE PEDIDO SET STATUS = 1 WHERE IDPEDIDO = ?");
            stmt.setInt(1, pedido.getIdPedido());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void CancelarPedido(PedidoModel pedido) {
         Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("UPDATE PEDIDO SET STATUS = 0 WHERE IDPEDIDO = ?");
            stmt.setInt(1, pedido.getIdPedido());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }


}
