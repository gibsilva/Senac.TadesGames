/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO;

import Senac.TadesGames.DAO.Interfaces.IUsuarioDao;
import Senac.TadesGames.Data.ConexaoDB;
import Senac.TadesGames.Models.UsuarioModel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gi
 */
public class UsuarioDAO implements IUsuarioDao{
    
    private ConexaoDB conexao = new ConexaoDB();
    private PreparedStatement stmt = null;
    ResultSet rs = null;

    @Override
    public UsuarioModel obterPorId(int id) {
        Connection conn = conexao.getConnection();
        UsuarioModel usuario = null;

        try {
            stmt = conn.prepareStatement("SELECT IDUSUARIO, "
                    + "NOME, "
                    + "CPF, "
                    + "EMAIL, "
                    + "IDFILIAL, "
                    + "CARGO,"
                    + "LOGIN,"
                    + "SENHA "
                    + "SEXO"
                    + "FROM USUARIO WHERE IDUSUARIO = ?");

            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                usuario = new UsuarioModel(
                        rs.getInt("IdUsuario"),
                        rs.getString("Nome"),
                        rs.getString("cpf"),
                        rs.getString("Email"),
                        rs.getString("filial"),
                        rs.getString("cargo"),
                        rs.getString("Login"),
                        rs.getString("Senha"),
                        rs.getInt("IdFilial"),
                        rs.getString("sexo")
                );
            }

            return usuario;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    @Override
    public List<UsuarioModel> obterTodas() {
        Connection conn = conexao.getConnection();
        UsuarioModel usuario = null;
        List<UsuarioModel> usuarios = new ArrayList<UsuarioModel>();

        try {
           stmt = conn.prepareStatement("SELECT IDUSUARIO, "
                    + "NOME, "
                    + "CPF, "
                    + "EMAIL, "
                    + "IDFILIAL, "
                    + "CARGO,"
                    + "LOGIN,"
                    + "SENHA "
                   + "SEXO"
                    + "FROM USUARIO WHERE IDUSUARIO = ?");

            rs = stmt.executeQuery();
            while (rs.next()) {
                usuario = new UsuarioModel(
                        rs.getInt("IdUsuario"),
                        rs.getString("Nome"),
                        rs.getString("cpf"),
                        rs.getString("Email"),
                        rs.getString("filial"),
                        rs.getString("cargo"),
                        rs.getString("Login"),
                        rs.getString("Senha"),
                        rs.getInt("IdFilial"),
                        rs.getString("sexo")
                );
            }

            return usuarios;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    @Override
    public void inserir(UsuarioModel usuario) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("INSERT INTO USUARIO("
                    + "NOME, "
                    + "CPF, "
                    + "EMAIL, "
                    + "IDFILIAL, "
                    + "CARGO,"
                    + "LOGIN,"
                    + "SENHA"
                    + "SEXO) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setInt(4, usuario.getIdFilial());
            stmt.setString(5, usuario.getCargo());
            stmt.setString(6, usuario.getLogin());
            stmt.setString(7, usuario.getSenha());
            stmt.setString(8, usuario.getSexo());
            

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void alterar(UsuarioModel usuario) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("UPTADE USUARIO SET "
                    + "NOME,"
                    + "EMAIL,"
                    + "IDFILIAL,"
                    + "CARGO,"
                    + "LOGIN,"
                    + "SENHA"
                    + "SEXO"
                    + " = ?, ?, ?, ?, ?, ?, ? WHERE IDUSUARIO = ?");
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setInt(3, usuario.getIdFilial());
            stmt.setString(4, usuario.getCargo());
            stmt.setString(5, usuario.getLogin());
            stmt.setString(6, usuario.getSenha());
            stmt.setInt(7, usuario.getIdUsuario());
            stmt.setString(8, usuario.getSexo());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void inativar(UsuarioModel usuario) {
        Connection conn = conexao.getConnection();

        try {
            stmt = conn.prepareStatement("UPTADE USUARIO SET ATIVO = 0 WHERE IDUSUARIO = ?");
            stmt.setInt(1, usuario.getIdUsuario());

            stmt.executeUpdate();
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public UsuarioModel autenticar(String nomeUsuario, String senha) {
        Connection conn = conexao.getConnection();
        UsuarioModel usuario = null;

        try {
            stmt = conn.prepareStatement("SELECT IDUSUARIO, "
                    + "NOME, "
                    + "CPF, "
                    + "EMAIL, "
                    + "IDFILIAL, "
                    + "CARGO,"
                    + "LOGIN,"
                    + "SENHA "
                    + "SEXO"
                    + "FROM USUARIO WHERE LOGIN = ? AND SENHA = ?");
            
            stmt.setString(1, nomeUsuario);
            stmt.setString(2, senha);

            rs = stmt.executeQuery();
            while (rs.next()) {
                usuario = new UsuarioModel(
                        rs.getInt("IdUsuario"),
                        rs.getString("Nome"),
                        rs.getString("cpf"),
                        rs.getString("Email"),
                        rs.getString("filial"),
                        rs.getString("cargo"),
                        rs.getString("Login"),
                        rs.getString("Senha"),
                        rs.getInt("IdFilial"),
                        rs.getString("sexo")
                );
            }

            return usuario;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }
    
}
