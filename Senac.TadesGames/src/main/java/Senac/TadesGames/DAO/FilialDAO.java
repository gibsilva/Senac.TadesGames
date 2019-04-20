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
                    + "FROM FILIAL WHERE IDFILIAL = ?");

            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                filial = new FilialModel(
                        rs.getInt("IdCliente"),
                        rs.getString("Nome"),
                        rs.getString("Cnpj")
                );
            }

            return filial;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    @Override
    public List<FilialModel> obterTodas() {
        Connection conn = conexao.getConnection();
        FilialModel filial = null;
        List<FilialModel> filiais = new ArrayList<FilialModel>();

        try {
            stmt = conn.prepareStatement("SELECT IDFILIAL, NOME FROM FILIAL");

            rs = stmt.executeQuery();
            while (rs.next()) {
                filial = new FilialModel(
                        rs.getInt("IdFilial"),
                        rs.getString("Nome"),
                        rs.getString("Cnpj"));

                filiais.add(filial);
            }

            return filiais;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    @Override
    public void inserir(FilialModel filial) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("INSERT INTO FILIAL(NOME, CNPJ) VALUES (?, ?)");
            stmt.setString(1, filial.getNome());
            stmt.setString(2, filial.getCnpj());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void alterar(FilialModel filial) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("UPTADE FILIAL SET NOME, ? WHERE IDCATEGORIA = ?");
            stmt.setString(1, filial.getNome());
            

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void excluir(FilialModel filial) {
         Connection conn = conexao.getConnection();
        
        try {
            stmt = conn.prepareStatement("DELETE FROM FILIAL WHERE IDFILIAL = ?");
            stmt.setInt(1, filial.getIdFilial());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }

}
