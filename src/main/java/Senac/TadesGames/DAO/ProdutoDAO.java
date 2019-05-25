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

    private final ConexaoDB conexao;
    private final GeneroDAO generoDao;
    private final PlataformaDAO plataformaDao;
    private final CategoriaDAO categoriaDao;

    public ProdutoDAO() {
        this.conexao = new ConexaoDB();
        this.generoDao = new GeneroDAO();
        this.plataformaDao = new PlataformaDAO();
        this.categoriaDao = new CategoriaDAO();
    }

    private PreparedStatement stmt = null;
    private ResultSet rs = null;

    @Override
    public ProdutoModel obterPorId(int id) {
        Connection conn = conexao.getConnection();
        ProdutoModel produto = null;

        try {
            stmt = conn.prepareStatement("SELECT "
                    + "IDPRODUTO, "
                    + "NOME, "
                    + "DESCRICAO, "
                    + "PRECOCOMPRA, "
                    + "PRECOVENDA, "
                    + "IDCATEGORIA, "
                    + "IDGENERO, "
                    + "ATIVO, "
                    + "IDFILIAL, "
                    + "IDPLATAFORMA, "
                    + "QUANTIDADEESTOQUE "
                    + " FROM produto WHERE IDPRODUTO = ?");
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
                        rs.getInt("IdPlataforma"),
                        rs.getInt("QuantidadeEstoque")
                );
                produto.setCategoria(categoriaDao.obterPorId(produto.getIdCategoria()));
                produto.setPlataforma(plataformaDao.obterPorId(produto.getIdPlataforma()));
                produto.setGenero(generoDao.obterPorId(produto.getIdGenero()));
            }

            return produto;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public List<ProdutoModel> obterTodas() {
        Connection conn = conexao.getConnection();
        ProdutoModel produto = null;
        List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();

        try {
            stmt = conn.prepareStatement("SELECT IDPRODUTO, "
                    + "NOME, "
                    + "DESCRICAO, "
                    + "PRECOCOMPRA, "
                    + "PRECOVENDA, "
                    + "IDCATEGORIA, "
                    + "IDGENERO, "
                    + "ATIVO, "
                    + "IDFILIAL, "
                    + "IDPLATAFORMA, "
                    + "QUANTIDADEESTOQUE "
                    + " FROM produto");

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
                        rs.getInt("IdPlataforma"),
                        rs.getInt("QuantidadeEstoque")
                );
                produto.setCategoria(categoriaDao.obterPorId(produto.getIdCategoria()));
                produto.setPlataforma(plataformaDao.obterPorId(produto.getIdPlataforma()));
                produto.setGenero(generoDao.obterPorId(produto.getIdGenero()));

                produtos.add(produto);
            }

            return produtos;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public List<ProdutoModel> obterPorIdCategoria(int id) {
        Connection conn = conexao.getConnection();
        ProdutoModel produto = null;
        List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();

        try {
            stmt = conn.prepareStatement("SELECT IDPRODUTO, "
                    + "NOME, "
                    + "DESCRICAO, "
                    + "PRECOCOMPRA, "
                    + "PRECOVENDA, "
                    + "IDCATEGORIA, "
                    + "IDGENERO, "
                    + "ATIVO, "
                    + "IDFILIAL, "
                    + "IDPLATAFORMA, "
                    + "QUANTIDADEESTOQUE "
                    + " FROM produto WHERE IDCATEGORIA = ?");

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
                        rs.getInt("IdPlataforma"),
                        rs.getInt("QuantidadeEstoque")
                );
                produto.setPlataforma(plataformaDao.obterPorId(produto.getIdPlataforma()));
                produto.setGenero(generoDao.obterPorId(produto.getIdGenero()));

                produtos.add(produto);
            }

            return produtos;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public List<ProdutoModel> obterPorIdGenero(int id) {
        Connection conn = conexao.getConnection();
        ProdutoModel produto = null;
        List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();

        try {
            stmt = conn.prepareStatement("SELECT IDPRODUTO, "
                    + "NOME, "
                    + "DESCRICAO, "
                    + "PRECOCOMPRA, "
                    + "PRECOVENDA, "
                    + "IDCATEGORIA, "
                    + "IDGENERO, "
                    + "ATIVO, "
                    + "IDFILIAL, "
                    + "IDPLATAFORMA, "
                    + "QUANTIDADEESTOQUE "
                    + " FROM produto WHERE IDGENERO = ?");

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
                        rs.getInt("IdPlataforma"),
                        rs.getInt("QuantidadeEstoque")
                );

                produtos.add(produto);
            }

            return produtos;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public List<ProdutoModel> obterPorIdPlataforma(int id) {
        Connection conn = conexao.getConnection();
        ProdutoModel produto = null;
        List<ProdutoModel> produtos = new ArrayList<ProdutoModel>();

        try {
            stmt = conn.prepareStatement("SELECT IDPRODUTO, "
                    + "NOME, "
                    + "DESCRICAO, "
                    + "PRECOCOMPRA, "
                    + "PRECOVENDA, "
                    + "IDCATEGORIA, "
                    + "IDGENERO, "
                    + "ATIVO, "
                    + "IDFILIAL, "
                    + "IDPLATAFORMA, "
                    + "QUANTIDADEESTOQUE "
                    + " FROM produto WHERE IDPLATAFORMA = ?");

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
                        rs.getInt("IdPlataforma"),
                        rs.getInt("QuantidadeEstoque")
                );

                produtos.add(produto);
            }

            return produtos;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

    @Override
    public void inserir(ProdutoModel produto) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("INSERT INTO produto("
                    + "NOME, "
                    + "DESCRICAO, "
                    + "PRECOCOMPRA, "
                    + "PRECOVENDA, "
                    + "IDCATEGORIA, "
                    + "IDGENERO, "
                    + "ATIVO, "
                    + "IDFILIAL, "
                    + "IDPLATAFORMA, "
                    + "QUANTIDADEESTOQUE) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPrecoCompra());
            stmt.setDouble(4, produto.getPrecoVenda());
            stmt.setInt(5, produto.getIdCategoria());
            stmt.setInt(6, produto.getIdGenero());
            stmt.setBoolean(7, produto.getAtivo());
            stmt.setInt(8, produto.getIdFilial());
            stmt.setInt(9, produto.getIdPlataforma());
            stmt.setInt(10, produto.getQuantidadeEstoque());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        } finally {
            conexao.closeConnection(conn, stmt);
        }
    }

    @Override
    public void alterar(ProdutoModel produto) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("UPDATE produto SET "
                    + "NOME = ?, "
                    + "DESCRICAO = ?, "
                    + "PRECOCOMPRA = ?, "
                    + "PRECOVENDA = ?, "
                    + "IDCATEGORIA = ?, "
                    + "IDGENERO = ?, "
                    + "ATIVO = ?, "
                    + "IDPLATAFORMA = ?, "
                    + "QUANTIDADEESTOQUE = ? "
                    + "WHERE IDPRODUTO = ?");
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setDouble(3, produto.getPrecoCompra());
            stmt.setDouble(4, produto.getPrecoVenda());
            stmt.setInt(5, produto.getIdCategoria());
            stmt.setInt(6, produto.getIdGenero());
            stmt.setBoolean(7, produto.getAtivo());
            stmt.setInt(8, produto.getIdPlataforma());
            stmt.setInt(9, produto.getQuantidadeEstoque());
            stmt.setInt(10, produto.getIdProduto());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        } finally {
            conexao.closeConnection(conn, stmt);
        }
    }

    public void atualizarEstoque(ProdutoModel produto, int quantidade) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("UPDATE produto SET "
                    + "QUANTIDADEESTOQUE =  ? "
                    + "WHERE IDPRODUTO = ? AND IDFILIAL = ?");

            stmt.setInt(1, quantidade);
            stmt.setInt(2, produto.getIdProduto());
            stmt.setInt(3, produto.getIdFilial());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        } finally {
            conexao.closeConnection(conn, stmt);
        }
    }

    @Override
    public void desativar(ProdutoModel produto) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("UPDATE produto SET "
                    + "ATIVO = 0"
                    + "WHERE IDPRODUTO = ?");
            stmt.setInt(1, produto.getIdProduto());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        } finally {
            conexao.closeConnection(conn, stmt);
        }
    }

    @Override
    public void ativar(ProdutoModel produto) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("UPDATE produto SET "
                    + "ATIVO = 1"
                    + "WHERE IDPRODUTO = ?");
            stmt.setInt(1, produto.getIdProduto());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        } finally {
            conexao.closeConnection(conn, stmt);
        }
    }
}
