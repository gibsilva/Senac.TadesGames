/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.dao;

import br.com.senac.tadesgames.dao.interfaces.IPlataformaDao;
import br.com.senac.tadesgames.data.ConexaoDB;
import br.com.senac.tadesgames.models.PlataformaModel;
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
public class PlataformaDAO implements IPlataformaDao {

    private final ConexaoDB conexao;
    private ProdutoDAO produtoDao;
    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    public PlataformaDAO() {
        this.conexao = new ConexaoDB();
    }

    public PlataformaModel obterPorNome(String nome) {
        Connection conn = conexao.getConnection();
        PlataformaModel plataforma = null;

        try {
            stmt = conn.prepareStatement("SELECT IDPLATAFORMA, NOME FROM plataforma WHERE NOME = ?");

            stmt.setString(1, nome);

            rs = stmt.executeQuery();
            while (rs.next()) {
                plataforma = new PlataformaModel(
                        rs.getInt("IdPlataforma"),
                        rs.getString("Nome"));
                produtoDao = new ProdutoDAO();
                plataforma.setProdutos(produtoDao.obterPorIdPlataforma(plataforma.getIdPlataforma()));
            }

            return plataforma;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    public PlataformaModel obterPorNome(String nome, int id) {
        Connection conn = conexao.getConnection();
        PlataformaModel plataforma = null;

        try {
            stmt = conn.prepareStatement("SELECT IDPLATAFORMA, NOME FROM plataforma WHERE NOME = ? AND IDPLATAFORMA != ?");

            stmt.setString(1, nome);
            stmt.setInt(2, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                plataforma = new PlataformaModel(
                        rs.getInt("IdPlataforma"),
                        rs.getString("Nome"));
                produtoDao = new ProdutoDAO();
                plataforma.setProdutos(produtoDao.obterPorIdPlataforma(plataforma.getIdPlataforma()));
            }

            return plataforma;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public PlataformaModel obterPorId(int id) {
        Connection conn = conexao.getConnection();
        PlataformaModel plataforma = null;

        try {
            stmt = conn.prepareStatement("SELECT IDPLATAFORMA, NOME FROM plataforma WHERE IDPLATAFORMA = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                plataforma = new PlataformaModel(
                        rs.getInt("IdPlataforma"),
                        rs.getString("Nome"));
                produtoDao = new ProdutoDAO();
                plataforma.setProdutos(produtoDao.obterPorIdPlataforma(plataforma.getIdPlataforma()));
            }

            return plataforma;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public List<PlataformaModel> obterTodas() {
        Connection conn = conexao.getConnection();
        PlataformaModel plataforma = null;
        List<PlataformaModel> plataformas = new ArrayList<PlataformaModel>();

        try {
            stmt = conn.prepareStatement("SELECT IDPLATAFORMA, NOME FROM plataforma");

            rs = stmt.executeQuery();
            while (rs.next()) {
                plataforma = new PlataformaModel(
                        rs.getInt("IdPlataforma"),
                        rs.getString("Nome"));

                produtoDao = new ProdutoDAO();
                plataforma.setProdutos(produtoDao.obterPorIdPlataforma(plataforma.getIdPlataforma()));

                plataformas.add(plataforma);
            }

            return plataformas;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public void inserir(PlataformaModel plataforma) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("INSERT INTO plataforma (NOME) VALUES (?)");
            stmt.setString(1, plataforma.getNome());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        } finally {
            conexao.closeConnection(conn, stmt);
        }
    }

    @Override
    public void alterar(PlataformaModel plataforma) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("UPDATE plataforma SET NOME = ? WHERE IDPLATAFORMA = ?");
            stmt.setString(1, plataforma.getNome());
            stmt.setInt(2, plataforma.getIdPlataforma());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        } finally {
            conexao.closeConnection(conn, stmt);
        }
    }

    @Override
    public void excluir(PlataformaModel plataforma) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("DELETE FROM plataforma WHERE IDPLATAFORMA = ?");
            stmt.setInt(1, plataforma.getIdPlataforma());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        } finally {
            conexao.closeConnection(conn, stmt);
        }

    }

}
