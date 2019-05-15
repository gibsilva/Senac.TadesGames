/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO;

import Senac.TadesGames.DAO.Interfaces.IPermissaoDao;
import Senac.TadesGames.Data.ConexaoDB;
import Senac.TadesGames.Models.PermissaoModel;
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
public class PermissaoDAO implements IPermissaoDao {

    private ConexaoDB conexao = new ConexaoDB();
    private PreparedStatement stmt = null;
    ResultSet rs = null;

    @Override
    public PermissaoModel obterPorId(int id) {
        Connection conn = conexao.getConnection();
        PermissaoModel permissao = null;

        try {
            stmt = conn.prepareStatement("SELECT "
                    + "IDPERMISSAO, "
                    + "IDUSUARIO, "
                    + "CADASTROPRODUTO, "
                    + "CADASTROCLIENTE, "
                    + "CADASTROFILIAL, "
                    + "REALIZAVENDA, "
                    + "RELATORIOPRODUTO, "
                    + "RELATORIOCLIENTE, "
                    + "RELATORIOVENDA, "
                    + "CADASTROUSUARIO "
                    + " FROM PERMISSAO WHERE IDPERMISSAO = ?");

            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                permissao = new PermissaoModel(
                        rs.getInt("IDPERMISSAO"),
                        rs.getInt("IDUSUARIO"),
                        rs.getInt("RELATORIOVENDA"),
                        rs.getInt("RELATORIOPRODUTO"),
                        rs.getInt("RELATORIOCLIENTE"),
                        rs.getInt("CADASTROCLIENTE"),
                        rs.getInt("CADASTROPRODUTO"),
                        rs.getInt("REALIZAVENDA"),
                        rs.getInt("CADASTROFILIAL"),
                        rs.getInt("CADASTROUSUARIO")
                );

            }

            return permissao;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    @Override
    public PermissaoModel obterPorIdUsuario(int id) {
        Connection conn = conexao.getConnection();
        PermissaoModel permissao = null;

        try {
            stmt = conn.prepareStatement("SELECT "
                    + "IDPERMISSAO, "
                    + "IDUSUARIO, "
                    + "CADASTROPRODUTO, "
                    + "CADASTROCLIENTE, "
                    + "CADASTROFILIAL, "
                    + "REALIZAVENDA, "
                    + "RELATORIOPRODUTO, "
                    + "RELATORIOCLIENTE, "
                    + "RELATORIOVENDA, "
                    + "CADASTROUSUARIO "
                    + " FROM PERMISSAO WHERE IDUSUARIO = ?");

            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                permissao = new PermissaoModel(
                        rs.getInt("IDPERMISSAO"),
                        rs.getInt("IDUSUARIO"),
                        rs.getInt("RELATORIOVENDA"),
                        rs.getInt("RELATORIOPRODUTO"),
                        rs.getInt("RELATORIOCLIENTE"),
                        rs.getInt("CADASTROCLIENTE"),
                        rs.getInt("CADASTROPRODUTO"),
                        rs.getInt("REALIZAVENDA"),
                        rs.getInt("CADASTROFILIAL"),
                        rs.getInt("CADASTROUSUARIO")
                );
            }
            return permissao;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }

    }

    @Override
    public List<PermissaoModel> obterTodas() {
        Connection conn = conexao.getConnection();
        PermissaoModel permissao = null;
        List<PermissaoModel> permissoes = new ArrayList<PermissaoModel>();

        try {
            stmt = conn.prepareStatement("SELECT "
                    + "IDPERMISSAO, "
                    + "IDUSUARIO, "
                    + "CADASTROPRODUTO, "
                    + "CADASTROCLIENTE, "
                    + "CADASTROFILIAL, "
                    + "REALIZAVENDA, "
                    + "RELATORIOPRODUTO, "
                    + "RELATORIOCLIENTE, "
                    + "RELATORIOVENDA, "
                    + "CADASTROUSUARIO "
                    + " FROM PERMISSAO");

            rs = stmt.executeQuery();
            while (rs.next()) {
                permissao = new PermissaoModel(
                        rs.getInt("IDPERMISSAO"),
                        rs.getInt("IDUSUARIO"),
                        rs.getInt("RELATORIOVENDA"),
                        rs.getInt("RELATORIOPRODUTO"),
                        rs.getInt("RELATORIOCLIENTE"),
                        rs.getInt("CADASTROCLIENTE"),
                        rs.getInt("CADASTROPRODUTO"),
                        rs.getInt("REALIZAVENDA"),
                        rs.getInt("CADASTROFILIAL"),
                        rs.getInt("CADASTROUSUARIO")
                );
            }

            return permissoes;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    @Override
    public void inserir(PermissaoModel permissao) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("INSERT INTO PERMISSAO("
                    + "IDPERMISSAO, "
                    + "IDUSUARIO, "
                    + "RELATORIOVENDA, "
                    + "RELATORIOPRODUTO, "
                    + "RELATORIOCLIENTE, "
                    + "CADASTROCLIENTE, "
                    + "CADASTROPRODUTO, "
                    + "REALIZAVENDA, "
                    + "CADASTROFILIAL, "
                    + "CADASTROUSUARIO)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setInt(1, permissao.getIdPermissao());
            stmt.setInt(2, permissao.getIdUsuario());
            stmt.setInt(3, permissao.getRelatorioVenda());
            stmt.setInt(4, permissao.getRelatorioProduto());
            stmt.setInt(5, permissao.getRelatorioCliente());
            stmt.setInt(6, permissao.getCadastrarCliente());
            stmt.setInt(7, permissao.getCadastrarProduto());
            stmt.setInt(8, permissao.getRealizarVenda());
            stmt.setInt(9, permissao.getCadastrarFilial());
            stmt.setInt(10, permissao.getCadastrarUsuario());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void alterar(PermissaoModel permissao) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("UPDATE PERMISSAO SET "
                    + "IDPERMISSAO = ?,"
                    + "IDUSUARIO = ?,"
                    + "RELATORIOVENDA = ?,"
                    + "RELATORIOPRODUTO = ?,"
                    + "RELATORIOCLIENTE = ?,"
                    + "CADASTROCLIENTE = ?,"
                    + "CADASTROPRODUTO = ?,"
                    + "REALIZAVENDA = ?,"
                    + "CADASTROFILIAL = ?,"
                    + "CADASTROUSUARIO = ?"
                    + " WHERE IDCLIENTE = ?");
            stmt.setInt(1, permissao.getIdPermissao());
            stmt.setInt(2, permissao.getIdUsuario());
            stmt.setInt(3, permissao.getRelatorioVenda());
            stmt.setInt(4, permissao.getRelatorioProduto());
            stmt.setInt(5, permissao.getRelatorioCliente());
            stmt.setInt(6, permissao.getCadastrarCliente());
            stmt.setInt(7, permissao.getCadastrarProduto());
            stmt.setInt(8, permissao.getRealizarVenda());
            stmt.setInt(9, permissao.getCadastrarFilial());
            stmt.setInt(10, permissao.getCadastrarUsuario());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }

}
