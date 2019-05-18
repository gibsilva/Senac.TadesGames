/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO;

import Senac.TadesGames.DAO.Interfaces.IFilialDao;
import Senac.TadesGames.Data.ConexaoDB;
import Senac.TadesGames.Models.FilialModel;
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
public class FilialDAO implements IFilialDao {

    private ConexaoDB conexao = new ConexaoDB();
    private PreparedStatement stmt = null;
    ResultSet rs = null;

    @Override
    public FilialModel obterPorId(int id) {
        Connection conn = conexao.getConnection();
        FilialModel filial = null;

        try {
            stmt = conn.prepareStatement("SELECT IDFILIAL, "
                    + "NOME, "
                    + "CNPJ, "
                    + "CEP, "
                    + "LOGRADOURO, "
                    + "NUMERO, "
                    + "COMPLEMENTO, "
                    + "BAIRRO, "
                    + "CIDADE, "
                    + "ESTADO, "
                    + "ATIVO "
                    + "FROM FILIAL WHERE IDFILIAL = ?");

            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                filial = new FilialModel(
                        rs.getInt("IdFilial"),
                        rs.getString("Cnpj"),
                        rs.getString("Nome"),
                        rs.getString("cep"),
                        rs.getString("logradouro"),
                        rs.getInt("numero"),
                        rs.getString("complemento"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getBoolean("Ativo")
                        
                );
            }

            return filial;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally{
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public List<FilialModel> obterTodas() {
        Connection conn = conexao.getConnection();
        FilialModel filial = null;
        List<FilialModel> filiais = new ArrayList<FilialModel>();

        try {
             stmt = conn.prepareStatement("SELECT IDFILIAL, "
                    + "NOME, "
                    + "CNPJ, "
                    + "CEP, "
                    + "LOGRADOURO, "
                    + "NUMERO, "
                    + "COMPLEMENTO, "
                    + "BAIRRO, "
                    + "CIDADE, "
                    + "ESTADO "
                    + "ATIVO "
                    + "FROM FILIAL");

            rs = stmt.executeQuery();
            while (rs.next()) {
                filial = new FilialModel(
                        rs.getInt("IdFilial"),
                        rs.getString("Cnpj"),
                        rs.getString("Nome"),
                        rs.getString("cep"),
                        rs.getString("logradouro"),
                        rs.getInt("numero"),
                        rs.getString("complemento"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getBoolean("Ativo")
                );

                filiais.add(filial);
            }

            return filiais;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally{
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public void inserir(FilialModel filial) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("INSERT INTO FILIAL("
                    + "NOME, "
                    + "CNPJ, "
                    + "CEP, "
                    + "LOGRADOURO, "
                    + "NUMERO, "
                    + "COMPLEMENTO, "
                    + "BAIRRO, "
                    + "CIDADE, "
                    + "ESTADO) "
                    + "ATIVO "
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, filial.getNome());
            stmt.setString(2, filial.getCnpj());
            stmt.setString(3, filial.getCep());
            stmt.setString(4, filial.getLogradouro());
            stmt.setInt(5, filial.getNumero());
            stmt.setString(6, filial.getComplemento());
            stmt.setString(7, filial.getBairro());
            stmt.setString(8, filial.getCidade());
            stmt.setString(9, filial.getEstado());
            stmt.setBoolean(10, filial.getAtivo());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        } finally{
            conexao.closeConnection(conn, stmt);
        }
    }

    @Override
    public void alterar(FilialModel filial) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("UPDATE FILIAL SET "
                    + "NOME = ?, "
                    + "CEP = ?, "
                    + "LOGRADOURO = ?, "
                    + "NUMERO = ?, "
                    + "COMPLEMENTO = ?, "
                    + "BAIRRO = ?, "
                    + "CIDADE = ?, "
                    + "ESTADO = ?, "
                    + "ATIVO = ? "
                    + " WHERE IDFILIAL = ?");
            stmt.setString(1, filial.getNome());
            stmt.setString(2, filial.getCep());
            stmt.setString(3, filial.getLogradouro());
            stmt.setInt(4, filial.getNumero());
            stmt.setString(5, filial.getComplemento());
            stmt.setString(6, filial.getBairro());
            stmt.setString(7, filial.getCidade());
            stmt.setString(8, filial.getEstado());
            stmt.setInt(9, filial.getIdFilial());
            stmt.setBoolean(10, filial.getAtivo());
            
            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        } finally{
            conexao.closeConnection(conn, stmt);
        }
    }
    
    @Override
    public FilialModel obterPorCnpj(String cnpj) {
        Connection conn = conexao.getConnection();
        FilialModel filial = null;

        try {
            stmt = conn.prepareStatement("SELECT IDFILIAL, "
                    + "NOME, "
                    + "CNPJ, "
                    + "CEP, "
                    + "LOGRADOURO, "
                    + "NUMERO, "
                    + "COMPLEMENTO, "
                    + "BAIRRO, "
                    + "CIDADE, "
                    + "ESTADO, "
                    + "ATIVO "
                    + "FROM FILIAL WHERE CNPJ = ?");

            stmt.setString(1, cnpj);

            rs = stmt.executeQuery();
            while (rs.next()) {
                filial = new FilialModel(
                        rs.getInt("IdFilial"),
                        rs.getString("Nome"),
                        rs.getString("Cnpj"),
                        rs.getString("cep"),
                        rs.getString("logradouro"),
                        rs.getInt("numero"),
                        rs.getString("complemento"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getBoolean("ativo")
                );
            }

            return filial;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally{
            conexao.closeConnection(conn, stmt, rs);
        }
    }
    
    public FilialModel obterPorCnpj(String cnpj, int id) {
        Connection conn = conexao.getConnection();
        FilialModel filial = null;

        try {
            stmt = conn.prepareStatement("SELECT IDFILIAL, "
                    + "NOME, "
                    + "CNPJ, "
                    + "CEP, "
                    + "LOGRADOURO, "
                    + "NUMERO, "
                    + "COMPLEMENTO, "
                    + "BAIRRO, "
                    + "CIDADE, "
                    + "ESTADO, "
                    + "ATIVO"
                    + "FROM FILIAL WHERE CNPJ = ? AND IDFILIAL != ?");

            stmt.setString(1, cnpj);
            stmt.setInt(2, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                filial = new FilialModel(
                        rs.getInt("IdFilial"),
                        rs.getString("Nome"),
                        rs.getString("Cnpj"),
                        rs.getString("cep"),
                        rs.getString("logradouro"),
                        rs.getInt("numero"),
                        rs.getString("complemento"),
                        rs.getString("bairro"),
                        rs.getString("cidade"),
                        rs.getString("estado"),
                        rs.getBoolean("ativo")
                );
            }

            return filial;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally{
            conexao.closeConnection(conn, stmt, rs);
        }
    }
}
