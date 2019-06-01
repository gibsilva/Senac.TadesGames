/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO;

import Senac.TadesGames.DAO.Interfaces.IPedidoDao;
import Senac.TadesGames.Data.ConexaoDB;
import Senac.TadesGames.Helpers.Utils;
import Senac.TadesGames.Models.PedidoModel;
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

    private final ConexaoDB conexao = new ConexaoDB();
    private PreparedStatement stmt = null;
    private final ItensPedidoDAO itensPedidoDao = new ItensPedidoDAO();
    private final UsuarioDAO usuarioDao = new UsuarioDAO();
    private final FilialDAO filialDao = new FilialDAO();
    private final ClienteDAO clienteDao = new ClienteDAO();

    ResultSet rs = null;

    @Override
    public PedidoModel obterPorId(int id) {
        Connection conn = conexao.getConnection();
        PedidoModel pedido = null;

        try {
            stmt = conn.prepareStatement("SELECT "
                    + "IDPEDIDO, "
                    + "FORMAPAGAMENTO, "
                    + "STATUSPEDIDO, "
                    + "DATAPEDIDO, "
                    + "IDCLIENTE, "
                    + "IDFILIAL, "
                    + "IDUSUARIO,"
                    + "PARCELA, "
                    + "VALORRECEBIDO "
                    + " FROM pedido WHERE IDPEDIDO = ?");

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
                        rs.getInt("FormaPagamento"),
                        rs.getInt("Parcela"),
                        rs.getDouble("ValorRecebido")
                );
                pedido.setItensPedido(itensPedidoDao.obterPorIdPedido(pedido.getIdPedido()));
                pedido.setCliente(clienteDao.obterPorId(pedido.getIdCliente()));
                pedido.setFilial(filialDao.obterPorId(pedido.getIdFilial()));
                pedido.setUsuario(usuarioDao.obterPorId(pedido.getIdUsuario()));
            }

            return pedido;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
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
                    + "PARCELA, "
                    + "VALORRECEBIDO "
                    + " FROM pedido");

            rs = stmt.executeQuery();
            while (rs.next()) {
                pedido = new PedidoModel(
                        rs.getInt("IdPedido"),
                        rs.getInt("StatusPedido"),
                        rs.getDate("DataPedido"),
                        rs.getInt("IdCliente"),
                        rs.getInt("IdFilial"),
                        rs.getInt("IdUsuario"),
                        rs.getInt("FormaPagamento"),
                        rs.getInt("Parcela"),
                        rs.getDouble("ValorRecebido")
                );
                pedido.setItensPedido(itensPedidoDao.obterPorIdPedido(pedido.getIdPedido()));
                pedido.setCliente(clienteDao.obterPorId(pedido.getIdCliente()));
                pedido.setFilial(filialDao.obterPorId(pedido.getIdFilial()));
                pedido.setUsuario(usuarioDao.obterPorId(pedido.getIdUsuario()));

                pedidos.add(pedido);
            }

            return pedidos;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public List<PedidoModel> obterTodosPorIdFilial(int idFilial) {
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
                    + "PARCELA, "
                    + "VALORRECEBIDO "
                    + " FROM pedido WHERE IDFILIAL = ?");

            stmt.setInt(1, idFilial);

            rs = stmt.executeQuery();
            while (rs.next()) {
                pedido = new PedidoModel(
                        rs.getInt("IdPedido"),
                        rs.getInt("StatusPedido"),
                        rs.getDate("DataPedido"),
                        rs.getInt("IdCliente"),
                        rs.getInt("IdFilial"),
                        rs.getInt("IdUsuario"),
                        rs.getInt("FormaPagamento"),
                        rs.getInt("Parcela"),
                        rs.getDouble("ValorRecebido")
                );
                pedido.setItensPedido(itensPedidoDao.obterPorIdPedido(pedido.getIdPedido()));
                pedido.setCliente(clienteDao.obterPorId(pedido.getIdCliente()));
                pedido.setFilial(filialDao.obterPorId(pedido.getIdFilial()));
                pedido.setUsuario(usuarioDao.obterPorId(pedido.getIdUsuario()));

                pedidos.add(pedido);
            }

            return pedidos;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public List<PedidoModel> obterTodosConcluidos() {
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
                    + "PARCELA, "
                    + "VALORRECEBIDO "
                    + " FROM pedido WHERE STATUSPEDIDO = 1");

            rs = stmt.executeQuery();
            while (rs.next()) {
                pedido = new PedidoModel(
                        rs.getInt("IdPedido"),
                        rs.getInt("StatusPedido"),
                        rs.getDate("DataPedido"),
                        rs.getInt("IdCliente"),
                        rs.getInt("IdFilial"),
                        rs.getInt("IdUsuario"),
                        rs.getInt("FormaPagamento"),
                        rs.getInt("Parcela"),
                        rs.getDouble("ValorRecebido")
                );
                pedido.setItensPedido(itensPedidoDao.obterPorIdPedido(pedido.getIdPedido()));
                pedido.setCliente(clienteDao.obterPorId(pedido.getIdCliente()));
                pedido.setFilial(filialDao.obterPorId(pedido.getIdFilial()));
                pedido.setUsuario(usuarioDao.obterPorId(pedido.getIdUsuario()));

                pedidos.add(pedido);
            }

            return pedidos;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public List<PedidoModel> obterTodosPorIdUsuario(int id) {
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
                    + "PARCELA, "
                    + "VALORRECEBIDO "
                    + " FROM pedido WHERE IdUsuario = ?");

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
                        rs.getInt("FormaPagamento"),
                        rs.getInt("Parcela"),
                        rs.getDouble("ValorRecebido")
                );
                pedido.setItensPedido(itensPedidoDao.obterPorIdPedido(pedido.getIdPedido()));
                pedido.setCliente(clienteDao.obterPorId(pedido.getIdCliente()));
                pedido.setFilial(filialDao.obterPorId(pedido.getIdFilial()));
                pedido.setUsuario(usuarioDao.obterPorId(pedido.getIdUsuario()));

                pedidos.add(pedido);
            }

            return pedidos;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public List<PedidoModel> pesquisar(int id, String dataInicio, String dataFim) {
        Connection conn = conexao.getConnection();
        PedidoModel pedido = null;
        List<PedidoModel> pedidos = new ArrayList<PedidoModel>();

        try {
            String sql = "SELECT IDPEDIDO, "
                    + "FORMAPAGAMENTO, "
                    + "STATUSPEDIDO, "
                    + "DATAPEDIDO, "
                    + "IDCLIENTE, "
                    + "IDFILIAL, "
                    + "IDUSUARIO,"
                    + "PARCELA, "
                    + "VALORRECEBIDO "
                    + " FROM pedido WHERE ";

            if (id != 0 && dataInicio.equals("") && dataFim.equals("")) {
                sql += "IDPEDIDO = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(1, id);
            } else if (id == 0 && !dataInicio.equals("") && dataFim.equals("")) {
                sql += "DATAPEDIDO >= ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, dataInicio);
            } else if (id == 0 && dataInicio.equals("") && !dataFim.equals("")) {
                sql += "DATAPEDIDO <= ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, dataFim);
            } else {
                sql += "DATAPEDIDO BETWEEN ? AND ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, dataInicio);
                stmt.setString(2, dataFim);
            }

            rs = stmt.executeQuery();
            while (rs.next()) {
                pedido = new PedidoModel(
                        rs.getInt("IdPedido"),
                        rs.getInt("StatusPedido"),
                        rs.getDate("DataPedido"),
                        rs.getInt("IdCliente"),
                        rs.getInt("IdFilial"),
                        rs.getInt("IdUsuario"),
                        rs.getInt("FormaPagamento"),
                        rs.getInt("Parcela"),
                        rs.getDouble("ValorRecebido")
                );
                pedido.setItensPedido(itensPedidoDao.obterPorIdPedido(pedido.getIdPedido()));
                pedido.setCliente(clienteDao.obterPorId(pedido.getIdCliente()));
                pedido.setFilial(filialDao.obterPorId(pedido.getIdFilial()));
                pedido.setUsuario(usuarioDao.obterPorId(pedido.getIdUsuario()));

                pedidos.add(pedido);
            }

            return pedidos;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public List<PedidoModel> pesquisar(int id, String dataInicio, String dataFim, int idFilial) {
        Connection conn = conexao.getConnection();
        PedidoModel pedido = null;
        List<PedidoModel> pedidos = new ArrayList<PedidoModel>();

        try {
            String sql = "SELECT IDPEDIDO, "
                    + "FORMAPAGAMENTO, "
                    + "STATUSPEDIDO, "
                    + "DATAPEDIDO, "
                    + "IDCLIENTE, "
                    + "IDFILIAL, "
                    + "IDUSUARIO,"
                    + "PARCELA, "
                    + "VALORRECEBIDO "
                    + " FROM pedido WHERE IDFILIAL = ? ";
            
            stmt.setInt(1, idFilial);

            if (id != 0 && dataInicio.equals("") && dataFim.equals("")) {
                sql += "AND IDPEDIDO = ?";
                stmt = conn.prepareStatement(sql);
                stmt.setInt(2, id);
            } else if (id == 0 && !dataInicio.equals("") && dataFim.equals("")) {
                sql += "AND DATAPEDIDO >= ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(2, dataInicio);
            } else if (id == 0 && dataInicio.equals("") && !dataFim.equals("")) {
                sql += "AND DATAPEDIDO <= ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(2, dataFim);
            } else {
                sql += "AND DATAPEDIDO BETWEEN ? AND ?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(2, dataInicio);
                stmt.setString(3, dataFim);
            }

            rs = stmt.executeQuery();
            while (rs.next()) {
                pedido = new PedidoModel(
                        rs.getInt("IdPedido"),
                        rs.getInt("StatusPedido"),
                        rs.getDate("DataPedido"),
                        rs.getInt("IdCliente"),
                        rs.getInt("IdFilial"),
                        rs.getInt("IdUsuario"),
                        rs.getInt("FormaPagamento"),
                        rs.getInt("Parcela"),
                        rs.getDouble("ValorRecebido")
                );
                pedido.setItensPedido(itensPedidoDao.obterPorIdPedido(pedido.getIdPedido()));
                pedido.setCliente(clienteDao.obterPorId(pedido.getIdCliente()));
                pedido.setFilial(filialDao.obterPorId(pedido.getIdFilial()));
                pedido.setUsuario(usuarioDao.obterPorId(pedido.getIdUsuario()));

                pedidos.add(pedido);
            }

            return pedidos;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public void inserir(PedidoModel pedido) {
        Connection conn = conexao.getConnection();
        Utils util = new Utils();

        try {
            stmt = conn.prepareStatement("INSERT INTO pedido ("
                    + "STATUSPEDIDO, "
                    + "DATAPEDIDO, "
                    + "IDCLIENTE, "
                    + "IDFILIAL, "
                    + "IDUSUARIO, "
                    + "FORMAPAGAMENTO,"
                    + "PARCELA,"
                    + "VALORRECEBIDO)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, pedido.getStatus());
            stmt.setString(2, util.converteDateParaStr(pedido.getDataPedido()));
            stmt.setInt(3, pedido.getIdCliente());
            stmt.setInt(4, pedido.getIdFilial());
            stmt.setInt(5, pedido.getIdUsuario());
            stmt.setInt(6, pedido.getFormaPagamento());
            stmt.setInt(7, pedido.getParcela());
            stmt.setDouble(8, pedido.getValorRecebido());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        } finally {
            conexao.closeConnection(conn, stmt);
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
            stmt = conn.prepareStatement("UPDATE pedido SET STATUS = 1 WHERE IDPEDIDO = ?");
            stmt.setInt(1, pedido.getIdPedido());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        } finally {
            conexao.closeConnection(conn, stmt);
        }
    }

    @Override
    public void CancelarPedido(PedidoModel pedido) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("UPDATE pedido SET STATUSPEDIDO = 0 WHERE IDPEDIDO = ?");
            stmt.setInt(1, pedido.getIdPedido());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        } finally {
            conexao.closeConnection(conn, stmt);
        }
    }

    public int ultimoIdInserido() {
        Connection conn = conexao.getConnection();
        int ultimoId = 0;

        try {
            stmt = conn.prepareStatement("select max(idpedido) as ultimoId from pedido;");

            rs = stmt.executeQuery();
            while (rs.next()) {
                ultimoId = rs.getInt("ultimoid");
            }

            return ultimoId;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return 0;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

}
