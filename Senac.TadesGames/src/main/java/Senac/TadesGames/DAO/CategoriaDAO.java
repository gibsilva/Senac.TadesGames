/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO;

import Senac.TadesGames.DAO.Interfaces.ICategoriaDao;
import Senac.TadesGames.Data.ConexaoDB;
import Senac.TadesGames.Models.CategoriaModel;
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
    private PreparedStatement stmt = null;
    ResultSet rs = null;

    @Override
    public CategoriaModel obterPorId(int id) {
        Connection conn = conexao.getConnection();
        CategoriaModel categoria = null;

        try {
            stmt = conn.prepareStatement("SELECT IDCATEGORIA, NOME FROM CATEGORIA WHERE IDCATEGORIA = ?");
            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                categoria = new CategoriaModel(
                        rs.getInt("IdCategoria"),
                        rs.getString("Nome"));
            }

            return categoria;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    @Override
    public List<CategoriaModel> obterTodas() {
        Connection conn = conexao.getConnection();
        CategoriaModel categoria = null;
        List<CategoriaModel> categorias = new ArrayList<CategoriaModel>();

        try {
            stmt = conn.prepareStatement("SELECT IDCATEGORIA, NOME FROM CATEGORIA");

            rs = stmt.executeQuery();
            while (rs.next()) {
                categoria = new CategoriaModel(
                        rs.getInt("IdCategoria"),
                        rs.getString("Nome"));

                categorias.add(categoria);
            }

            return categorias;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    @Override
    public void inserir(CategoriaModel categoria) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("INSERT INTO CATEGORIA(NOME) VALUES (?)");
            stmt.setString(1, categoria.getNome());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void alterar(CategoriaModel categoria) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("UPTADE CATEGORIA SET NOME = ? WHERE IDCATEGORIA = ?");
            stmt.setString(1, categoria.getNome());
            stmt.setInt(2, categoria.getIdCategoria());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void excluir(CategoriaModel categoria) {
        Connection conn = conexao.getConnection();
        
        try {
            stmt = conn.prepareStatement("DELETE FROM CATEGORIA WHERE IDCATEGORIA = ?");
            stmt.setInt(1, categoria.getIdCategoria());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }

    }

}
