/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO;

import Senac.TadesGames.DAO.Interfaces.IClienteDao;
import Senac.TadesGames.Data.ConexaoDB;
import Senac.TadesGames.Helpers.Utils;
import Senac.TadesGames.Models.ClienteModel;
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
public class ClienteDAO implements IClienteDao {

    private final ConexaoDB conexao = new ConexaoDB();
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
                    + "CNPJ, "
                    + "DATANASC, "
                    + "EMAIL, "
                    + "TELEFONE, "
                    + "CELULAR,  "
                    + "SEXO, "
                    + "ATIVO "
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
                        rs.getString("telefone"),
                        rs.getString("celular"),
                        rs.getString("sexo"),
                        rs.getBoolean("ativo")
                );
            }

            return cliente;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    @Override
    public ClienteModel obterPorEmail(String email) {
        Connection conn = conexao.getConnection();
        ClienteModel cliente = null;

        try {
            stmt = conn.prepareStatement("SELECT IDCLIENTE, "
                    + "NOME, "
                    + "CPF, "
                    + "CNPJ, "
                    + "DATANASC, "
                    + "EMAIL, "
                    + "TELEFONE, "
                    + "CELULAR, "
                    + "SEXO, "
                    + "ATIVO "
                    + "FROM CLIENTE WHERE EMAIL = ?");

            stmt.setString(1, email);

            rs = stmt.executeQuery();
            while (rs.next()) {
                cliente = new ClienteModel(
                        rs.getInt("IdCliente"),
                        rs.getString("Nome"),
                        rs.getString("cpf"),
                        rs.getString("cnpj"),
                        rs.getDate("DataNasc"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("celular"),
                        rs.getString("sexo"),
                        rs.getBoolean("ativo")
                );
            }

            return cliente;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    public ClienteModel obterPorEmail(String email, int id) {
        Connection conn = conexao.getConnection();
        ClienteModel cliente = null;

        try {
            stmt = conn.prepareStatement("SELECT IDCLIENTE, "
                    + "NOME, "
                    + "CPF, "
                    + "CNPJ, "
                    + "DATANASC, "
                    + "EMAIL, "
                    + "TELEFONE, "
                    + "CELULAR, "
                    + "SEXO, "
                    + "ATIVO "
                    + "FROM CLIENTE WHERE EMAIL = ? AND IDCLIENTE != ?");

            stmt.setString(1, email);
            stmt.setInt(2, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                cliente = new ClienteModel(
                        rs.getInt("IdCliente"),
                        rs.getString("Nome"),
                        rs.getString("cpf"),
                        rs.getString("cnpj"),
                        rs.getDate("DataNasc"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("celular"),
                        rs.getString("sexo"),
                        rs.getBoolean("ativo")
                );
            }

            return cliente;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    @Override
    public ClienteModel obterPorCpf(String cpf) {
        Connection conn = conexao.getConnection();
        ClienteModel cliente = null;

        try {
            stmt = conn.prepareStatement("SELECT IDCLIENTE, "
                    + "NOME, "
                    + "CPF, "
                    + "CNPJ, "
                    + "DATANASC, "
                    + "EMAIL, "
                    + "TELEFONE, "
                    + "CELULAR, "
                    + "SEXO, "
                    + "ATIVO "
                    + "FROM CLIENTE WHERE CPF = ?");

            stmt.setString(1, cpf);

            rs = stmt.executeQuery();
            while (rs.next()) {
                cliente = new ClienteModel(
                        rs.getInt("IdCliente"),
                        rs.getString("Nome"),
                        rs.getString("cpf"),
                        rs.getString("cnpj"),
                        rs.getDate("DataNasc"),
                        rs.getString("email"),
                        rs.getString("telefone"),
                        rs.getString("celular"),
                        rs.getString("sexo"),
                        rs.getBoolean("ativo")
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
                    + "CNPJ, "
                    + "DATANASC, "
                    + "EMAIL, "
                    + "TELEFONE, "
                    + "CELULAR, "
                    + "SEXO, "
                    + "ATIVO "
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
                        rs.getString("telefone"),
                        rs.getString("celular"),
                        rs.getString("sexo"),
                        rs.getBoolean("ativo")
                );
                clientes.add(cliente);
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
        Utils util = new Utils();

        try {
            stmt = conn.prepareStatement("INSERT INTO CLIENTE("
                    + "NOME, "
                    + "CPF, "
                    + "CNPJ, "
                    + "DATANASC, "
                    + "EMAIL, "
                    + "TELEFONE, "
                    + "CELULAR, "
                    + "SEXO, "
                    + "ATIVO) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getCnpj());
            stmt.setString(4, util.converteDateParaStr(cliente.getDataNasc()));
            stmt.setString(5, cliente.getEmail());
            stmt.setString(6, cliente.getTelefone());
            stmt.setString(7, cliente.getCelular());
            stmt.setString(8, cliente.getSexo());
            stmt.setBoolean(9, cliente.isAtivo());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void alterar(ClienteModel cliente) {
        Connection conn = conexao.getConnection();
        Utils util = new Utils();
        try {
            stmt = conn.prepareStatement("UPDATE CLIENTE SET "
                    + "NOME = ?,"
                    + "DATANASC = ?,"
                    + "EMAIL = ?,"
                    + "TELEFONE = ?,"
                    + "CELULAR = ?,"
                    + "SEXO = ?, "
                    + "ATIVO = ? "
                    + "WHERE IDCLIENTE = ?");
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, util.converteDateParaStr(cliente.getDataNasc()));
            stmt.setString(3, cliente.getEmail());
            stmt.setString(4, cliente.getTelefone());
            stmt.setString(5, cliente.getCelular());
            stmt.setString(6, cliente.getSexo());
            stmt.setBoolean(7, cliente.isAtivo());
            stmt.setInt(8, cliente.getIdCliente());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }

}
