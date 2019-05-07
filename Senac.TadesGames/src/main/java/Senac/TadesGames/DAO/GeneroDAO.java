/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO;

import Senac.TadesGames.DAO.Interfaces.IGeneroDao;
import Senac.TadesGames.Data.ConexaoDB;
import Senac.TadesGames.Models.GeneroModel;
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
public class GeneroDAO implements IGeneroDao {

    private ConexaoDB conexao = new ConexaoDB();
    private PreparedStatement stmt = null;
    ResultSet rs = null;

    public GeneroModel obterPoNome(String nome) {
        Connection conn = conexao.getConnection();
        GeneroModel genero = null;

        try {
            stmt = conn.prepareStatement("SELECT IDGENERO, NOME FROM GENERO WHERE NOME = ?");

            stmt.setString(1, nome);

            rs = stmt.executeQuery();
            while (rs.next()) {
                genero = new GeneroModel(
                        rs.getInt("IdGenero"),
                        rs.getString("Nome"));
            }

            return genero;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    public GeneroModel obterPoNome(String nome, int id) {
        Connection conn = conexao.getConnection();
        GeneroModel genero = null;

        try {
            stmt = conn.prepareStatement("SELECT IDGENERO, NOME FROM GENERO WHERE NOME = ? AND IDGENERO != ?");

            stmt.setString(1, nome);
            stmt.setInt(2, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                genero = new GeneroModel(
                        rs.getInt("IdGenero"),
                        rs.getString("Nome"));
            }

            return genero;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    @Override
    public GeneroModel obterPorId(int id) {
        Connection conn = conexao.getConnection();
        GeneroModel genero = null;

        try {
            stmt = conn.prepareStatement("SELECT IDGENERO, NOME FROM GENERO WHERE IDGENERO = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                genero = new GeneroModel(
                        rs.getInt("IdGenero"),
                        rs.getString("Nome"));
            }

            return genero;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }

    }

    @Override
    public List<GeneroModel> obterTodas() {
        Connection conn = conexao.getConnection();
        GeneroModel genero = null;
        List<GeneroModel> generos = new ArrayList<GeneroModel>();

        try {
            stmt = conn.prepareStatement("SELECT IDGENERO, NOME FROM GENERO");

            rs = stmt.executeQuery();
            while (rs.next()) {
                genero = new GeneroModel(
                        rs.getInt("IdGenero"),
                        rs.getString("Nome"));

                generos.add(genero);
            }

            return generos;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    @Override
    public void inserir(GeneroModel genero) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("INSERT INTO GENERO(NOME) VALUES (?)");
            stmt.setString(1, genero.getNome());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void alterar(GeneroModel genero) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("UPDATE GENERO SET NOME = ? WHERE IDGENERO = ?");
            stmt.setString(1, genero.getNome());
            stmt.setInt(2, genero.getIdGenero());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void excluir(GeneroModel genero) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("DELETE FROM GENERO WHERE IDGENERO = ?");
            stmt.setInt(1, genero.getIdGenero());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }
}
