/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO;

import Senac.TadesGames.DAO.Interfaces.IPlataformaDao;
import Senac.TadesGames.Data.ConexaoDB;
import Senac.TadesGames.Model.PlataformaModel;
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
public class PlataformaDAO implements IPlataformaDao{
    private ConexaoDB conexao = new ConexaoDB();
    private PreparedStatement stmt = null;
    ResultSet rs = null;

    @Override
    public PlataformaModel obterPorId(int id) {
        Connection conn = conexao.getConnection();
        PlataformaModel plataforma = null;

        try {
            stmt = conn.prepareStatement("SELECT IDPLATAFORMA, NOME FROM CATEGORIA WHERE IDPLATAFORMA = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                plataforma = new PlataformaModel(
                        rs.getInt("IdPlataforma"),
                        rs.getString("Nome"));
            }

            return plataforma;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    @Override
    public List<PlataformaModel> obterTodas() {
        Connection conn = conexao.getConnection();
        PlataformaModel plataforma = null;
        List<PlataformaModel> plataformas = new ArrayList<PlataformaModel>();

        try {
            stmt = conn.prepareStatement("SELECT IDPLATAFORMA, NOME FROM PLATAFORMA");

            rs = stmt.executeQuery();
            while (rs.next()) {
                plataforma = new PlataformaModel(
                        rs.getInt("IdPlataforma"),
                        rs.getString("Nome"));

                plataformas.add(plataforma);
            }

            return plataformas;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    @Override
    public void inserir(PlataformaModel plataforma) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("INSERT INTO PLATAFORMA (NOME) VALUES (?)");
            stmt.setString(1, plataforma.getNome());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void alterar(PlataformaModel plataforma) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("UPTADE PLATAFORMA SET NOME = ? WHERE IDPLATAFORMA = ?");
            stmt.setString(1, plataforma.getNome());
            stmt.setInt(2, plataforma.getIdPlataforma());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void excluir(PlataformaModel plataforma) {
        Connection conn = conexao.getConnection();
        
        try {
            stmt = conn.prepareStatement("DELETE FROM PLATAFORMA WHERE IDPLATAFORMA = ?");
            stmt.setInt(1, plataforma.getIdPlataforma());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }

    }
    
}
