/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO;

import Senac.TadesGames.DAO.Interfaces.IClienteDao;
import Senac.TadesGames.Data.ConexaoDB;
import Senac.TadesGames.Model.ClienteModel;
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
public class ClienteDAO implements IClienteDao {

    private ConexaoDB conexao = new ConexaoDB();
    private PreparedStatement stmt = null;
    ResultSet rs = null;

    @Override
    public ClienteModel obterPorId(int id) {
        Connection conn = conexao.getConnection();
        ClienteModel cliente = null;

        try {
            stmt = conn.prepareStatement("SELECT IDCLIENTE, "
                    + "NOME, "
                    + "CPF, "
                    + "DATANASC, "
                    + "EMAIL, "
                    + "TELEFONE, "
                    + "CELULAR  "
                    + "FROM CLIENTE WHERE IDCLIENTE = ?");

            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                cliente = new ClienteModel(
                        rs.getInt("IdCliente"),
                        rs.getString("Nome"),
                        rs.getString("cpf"),
                        rs.getString("cnpj"),
                        rs.getDate("DataNasc"),
                        rs.getString("email"),
                        rs.getInt("telefone"),
                        rs.getInt("celular")
                );
            }

            return cliente;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    @Override
    public List<ClienteModel> obterTodas() {
        Connection conn = conexao.getConnection();
        ClienteModel cliente = null;
        List<ClienteModel> clientes = new ArrayList<ClienteModel>();

        try {
            stmt = conn.prepareStatement("SELECT IDCLIENTE, "
                    + "NOME, "
                    + "CPF, "
                    + "DATANASC, "
                    + "EMAIL, "
                    + "TELEFONE, "
                    + "CELULAR  "
                    + "FROM CLIENTE");

            rs = stmt.executeQuery();
            while (rs.next()) {
                cliente = new ClienteModel(
                        rs.getInt("IdCliente"),
                        rs.getString("Nome"),
                        rs.getString("cpf"),
                        rs.getString("cnpj"),
                        rs.getDate("DataNasc"),
                        rs.getString("email"),
                        rs.getInt("telefone"),
                        rs.getInt("celular")
                );
            }

            return clientes;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    @Override
    public void inserir(ClienteModel cliente) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("INSERT INTO CLIENTE("
                    + "NOME, "
                    + "CPF, "
                    + "CNPJ, "
                    + "DATANASC, "
                    + "EMAIL, "
                    + "TELEFONE, "
                    + "CELULAR) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getCnpj());
            stmt.setDate(4, (Date) cliente.getDataNasc());
            stmt.setString(5, cliente.getEmail());
            stmt.setInt(6, cliente.getTelefone());
            stmt.setInt(7, cliente.getCelular());
            

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void alterar(ClienteModel cliente) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("UPTADE CLIENTE SET "
                    + "NOME,"
                    + "DATANASC,"
                    + "EMAIL,"
                    + "TELEFONE,"
                    + "CELULAR,"
                    + " = ?, ?, ?, ?, ?, ?, ? WHERE IDCLIENTE = ?");
            stmt.setString(1, cliente.getNome());
            stmt.setDate(2, (Date) cliente.getDataNasc());
            stmt.setString(3, cliente.getEmail());
            stmt.setInt(4, cliente.getTelefone());
            stmt.setInt(5, cliente.getCelular());
            stmt.setInt(6, cliente.getIdCliente());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }

}
