/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.dao;

import br.com.senac.tadesgames.dao.interfaces.IGeneroDao;
import br.com.senac.tadesgames.data.ConexaoDB;
import br.com.senac.tadesgames.models.GeneroModel;
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

    private final ConexaoDB conexao;
    private ProdutoDAO produtoDao;
    private PreparedStatement stmt = null;
    ResultSet rs = null;

    public GeneroDAO() {
        this.conexao = new ConexaoDB();
    }

    public GeneroModel obterPorNome(String nome) {
        Connection conn = conexao.getConnection();
        GeneroModel genero = null;

        try {
            stmt = conn.prepareStatement("SELECT IDGENERO, NOME FROM genero WHERE NOME = ?");

            stmt.setString(1, nome);

            rs = stmt.executeQuery();
            while (rs.next()) {
                genero = new GeneroModel(
                        rs.getInt("IdGenero"),
                        rs.getString("Nome"));
                produtoDao = new ProdutoDAO();
                genero.setProdutos(produtoDao.obterPorIdGenero(genero.getIdGenero()));
            }

            return genero;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    public GeneroModel obterPorNome(String nome, int id) {
        Connection conn = conexao.getConnection();
        GeneroModel genero = null;

        try {
            stmt = conn.prepareStatement("SELECT IDGENERO, NOME FROM genero WHERE NOME = ? AND IDGENERO != ?");

            stmt.setString(1, nome);
            stmt.setInt(2, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                genero = new GeneroModel(
                        rs.getInt("IdGenero"),
                        rs.getString("Nome"));
                produtoDao = new ProdutoDAO();
                genero.setProdutos(produtoDao.obterPorIdGenero(genero.getIdGenero()));
            }

            return genero;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public GeneroModel obterPorId(int id) {
        Connection conn = conexao.getConnection();
        GeneroModel genero = null;

        try {
            stmt = conn.prepareStatement("SELECT IDGENERO, NOME FROM genero WHERE IDGENERO = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                genero = new GeneroModel(
                        rs.getInt("IdGenero"),
                        rs.getString("Nome"));
                produtoDao = new ProdutoDAO();
                genero.setProdutos(produtoDao.obterPorIdGenero(genero.getIdGenero()));
            }

            return genero;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }

    }

    @Override
    public List<GeneroModel> obterTodas() {
        Connection conn = conexao.getConnection();
        GeneroModel genero = null;
        List<GeneroModel> generos = new ArrayList<GeneroModel>();

        try {
            stmt = conn.prepareStatement("SELECT IDGENERO, NOME FROM genero");

            rs = stmt.executeQuery();
            while (rs.next()) {
                genero = new GeneroModel(
                        rs.getInt("IdGenero"),
                        rs.getString("Nome"));

                produtoDao = new ProdutoDAO();
                genero.setProdutos(produtoDao.obterPorIdGenero(genero.getIdGenero()));

                generos.add(genero);
            }

            return generos;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public void inserir(GeneroModel genero) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("INSERT INTO genero(NOME) VALUES (?)");
            stmt.setString(1, genero.getNome());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        } finally {
            conexao.closeConnection(conn, stmt);
        }
    }

    @Override
    public void alterar(GeneroModel genero) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("UPDATE genero SET NOME = ? WHERE IDGENERO = ?");
            stmt.setString(1, genero.getNome());
            stmt.setInt(2, genero.getIdGenero());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        } finally {
            conexao.closeConnection(conn, stmt);
        }
    }

    @Override
    public void excluir(GeneroModel genero) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("DELETE FROM genero WHERE IDGENERO = ?");
            stmt.setInt(1, genero.getIdGenero());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        } finally {
            conexao.closeConnection(conn, stmt);
        }
    }
}
