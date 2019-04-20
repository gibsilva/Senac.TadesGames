/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO;

import Senac.TadesGames.DAO.Interfaces.IProdutoDao;
import Senac.TadesGames.Data.ConexaoDB;
import Senac.TadesGames.Models.ProdutoModel;
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
public class ProdutoDAO implements IProdutoDao {
    private final ConexaoDB conexao = new ConexaoDB();
    private PreparedStatement stmt = null;
    ResultSet rs = null;
    
    @Override
    public ProdutoModel obterPorId(int id) {
        Connection conn = conexao.getConnection();
        ProdutoModel produto = null;

        try {
            stmt = conn.prepareStatement("SELECT IDPRODUTO, NOME, DESCRICAO, PRECOCOMPRA, PRECOVENDA, IDCATEGORIA, IDGENERO, "
                    + "ATIVO, IDFILIAL, IDPLATAFORMA FROM PRODUTO WHERE IDPRODUTO = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                produto = new ProdutoModel(
                        rs.getInt("IdProduto"),
                        rs.getString("Nome"),
                        rs.getString("Descricao"),
                        rs.getDouble("PrecoCompra"),
                        rs.getDouble("PrecoVenda"),
                        rs.getInt("IdCategoria"),
                        rs.getInt("IdGenero"),
                        rs.getBoolean("Ativo"),
                        rs.getInt("IdFilial"),
                        rs.getInt("IdPlataforma")
                );
            }

            return produto;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    @Override
    public List<ProdutoModel> obterTodas() {
        Connection conn = conexao.getConnection();
        ProdutoModel produto = null;
        List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();

        try {
            stmt = conn.prepareStatement("SELECT IDPRODUTO, NOME, DESCRICAO, PRECOCOMPRA, PRECOVENDA, IDCATEGORIA, IDGENERO"
                    + "ATIVO, IDFILIAL, IDPLATAFORMA FROM PRODUTO");

            rs = stmt.executeQuery();
            while (rs.next()) {
                produto = new ProdutoModel(
                        rs.getInt("IdProduto"),
                        rs.getString("Nome"),
                        rs.getString("Descricao"),
                        rs.getDouble("PrecoCompra"),
                        rs.getDouble("PrecoVenda"),
                        rs.getInt("IdCategoria"),
                        rs.getInt("IdGenero"),
                        rs.getBoolean("Ativo"),
                        rs.getInt("IdFilial"),
                        rs.getInt("IdPlataforma")
                );
                produtos.add(produto);
            }

            return produtos;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    @Override
    public void inserir(ProdutoModel produto) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("INSERT INTO PRODUTO(NOME, DESCRICAO, PRECOCOMPRA, PRECOVENDA, IDCATEGORIA, "
                    + "IDGENERO, ATIVO, IDFILIAL, IDPLATAFORMA) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPrecoCompra());
            stmt.setDouble(4, produto.getPrecoVenda());
            stmt.setInt(5, produto.getIdCategoria());
            stmt.setInt(6, produto.getIdGenero());
            stmt.setBoolean(7, produto.getAtivo());
            stmt.setInt(8, produto.getIdPlataforma());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void alterar(ProdutoModel produto) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("UPDATE PRODUTO SET "
                    + "NOME = ?,"
                    + "DESCRICAO = ?,"
                    + "PRECOCOMPRA = ?,"
                    + "PRECOVENDA = ?,"
                    + "IDCATEGORIA = ?,"
                    + "IDGENERO = ?,"
                    + "ATIVO = ?,"
                    + "IDFILIAL = ?,"
                    + "IDPLATAFORMA = ? "
                    + "WHERE IDPRODUTO = ?");
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPrecoCompra());
            stmt.setDouble(4, produto.getPrecoVenda());
            stmt.setInt(5, produto.getIdCategoria());
            stmt.setInt(6, produto.getIdGenero());
            stmt.setBoolean(7, produto.getAtivo());
            stmt.setInt(8, produto.getIdPlataforma());
            stmt.setInt(9, produto.getIdProduto());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void desativar(ProdutoModel produto) {
       Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("UPDATE PRODUTO SET "
                    + "ATIVO = 0"
                    + "WHERE IDPRODUTO = ?");
            stmt.setInt(1, produto.getIdProduto());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void ativar(ProdutoModel produto) {
       Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("UPDATE PRODUTO SET "
                    + "ATIVO = 1"
                    + "WHERE IDPRODUTO = ?");
            stmt.setInt(1, produto.getIdProduto());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }
}
