/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.dao;

import br.com.senac.tadesgames.dao.interfaces.ICategoriaDao;
import br.com.senac.tadesgames.data.ConexaoDB;
import br.com.senac.tadesgames.models.CategoriaModel;
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
public class CategoriaDAO implements ICategoriaDao {

    private ConexaoDB conexao = new ConexaoDB();
    private ProdutoDAO produtoDao;
    private PreparedStatement stmt = null;
    ResultSet rs = null;

    public CategoriaDAO() {
        this.conexao = new ConexaoDB();
    }

    public CategoriaModel obterPorNome(String nome) {
        Connection conn = conexao.getConnection();
        CategoriaModel categoria = null;

        try {
            stmt = conn.prepareStatement("SELECT IDCATEGORIA, NOME FROM categoria WHERE NOME = ?");

            stmt.setString(1, nome);

            rs = stmt.executeQuery();
            while (rs.next()) {
                categoria = new CategoriaModel(
                        rs.getInt("IdCategoria"),
                        rs.getString("Nome"));

                produtoDao = new ProdutoDAO();
                categoria.setProdutos(produtoDao.obterPorIdCategoria(categoria.getIdCategoria()));
            }

            return categoria;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    public CategoriaModel obterPorNome(String nome, int id) {
        Connection conn = conexao.getConnection();
        CategoriaModel categoria = null;

        try {
            stmt = conn.prepareStatement("SELECT IDCATEGORIA, NOME FROM categoria WHERE NOME = ? AND IDCATEGORIA != ?");

            stmt.setString(1, nome);
            stmt.setInt(2, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                categoria = new CategoriaModel(
                        rs.getInt("IdCategoria"),
                        rs.getString("Nome"));
                produtoDao = new ProdutoDAO();
                categoria.setProdutos(produtoDao.obterPorIdCategoria(categoria.getIdCategoria()));
            }

            return categoria;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public CategoriaModel obterPorId(int id) {
        Connection conn = conexao.getConnection();
        CategoriaModel categoria = null;

        try {
            stmt = conn.prepareStatement("SELECT IDCATEGORIA, NOME FROM categoria WHERE IDCATEGORIA = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                categoria = new CategoriaModel(
                        rs.getInt("IdCategoria"),
                        rs.getString("Nome"));
                produtoDao = new ProdutoDAO();
                categoria.setProdutos(produtoDao.obterPorIdCategoria(categoria.getIdCategoria()));
            }

            return categoria;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public List<CategoriaModel> obterTodas() {
        Connection conn = conexao.getConnection();
        CategoriaModel categoria = null;
        List<CategoriaModel> categorias = new ArrayList<CategoriaModel>();

        try {
            stmt = conn.prepareStatement("SELECT IDCATEGORIA, NOME FROM categoria");

            rs = stmt.executeQuery();
            while (rs.next()) {
                categoria = new CategoriaModel(
                        rs.getInt("IdCategoria"),
                        rs.getString("Nome"));

                produtoDao = new ProdutoDAO();
                categoria.setProdutos(produtoDao.obterPorIdCategoria(categoria.getIdCategoria()));

                categorias.add(categoria);
            }

            return categorias;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public void inserir(CategoriaModel categoria) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("INSERT INTO categoria(NOME) VALUES (?)");
            stmt.setString(1, categoria.getNome());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        } finally {
            conexao.closeConnection(conn, stmt);
        }
    }

    @Override
    public void alterar(CategoriaModel categoria) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("UPDATE categoria SET NOME = ? WHERE IDCATEGORIA = ?");
            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, categoria.getIdCategoria());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        } finally {
            conexao.closeConnection(conn, stmt);
        }
    }

    @Override
    public void excluir(CategoriaModel categoria) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("DELETE FROM categoria WHERE IDCATEGORIA = ?");
            stmt.setInt(1, categoria.getIdCategoria());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        } finally {
            conexao.closeConnection(conn, stmt);
        }

    }
}
