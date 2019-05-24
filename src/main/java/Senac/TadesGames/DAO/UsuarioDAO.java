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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gi
 */
public class UsuarioDAO implements IUsuarioDao {

    private ConexaoDB conexao;
    private final FilialDAO filialDao;
    private PreparedStatement stmt = null;
    ResultSet rs = null;
    
    public UsuarioDAO(){
        this.conexao = new ConexaoDB();
        this.filialDao = new FilialDAO();
    }

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
                    + "SETOR, "
                    + "CARGO, "
                    + "LOGIN, "
                    + "SENHA, "
                    + "SEXO, "
                    + "ATIVO "
                    + "FROM usuario WHERE IDUSUARIO = ?");

            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                usuario = new UsuarioModel(
                        rs.getInt("IdUsuario"),
                        rs.getString("Nome"),
                        rs.getString("cpf"),
                        rs.getString("Email"),
                        rs.getString("setor"),
                        rs.getString("cargo"),
                        rs.getString("Login"),
                        rs.getString("Senha"),
                        rs.getInt("IdFilial"),
                        rs.getString("sexo"),
                        rs.getBoolean("ativo")
                );
                usuario.setFilial(filialDao.obterPorId(usuario.getIdFilial()));
            }

            return usuario;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    public UsuarioModel obterPorEmail(String email) {
        Connection conn = conexao.getConnection();
        UsuarioModel usuario = null;

        try {
            stmt = conn.prepareStatement("SELECT IDUSUARIO, "
                    + "NOME, "
                    + "CPF, "
                    + "EMAIL, "
                    + "IDFILIAL, "
                    + "SETOR, "
                    + "CARGO,"
                    + "LOGIN, "
                    + "SENHA, "
                    + "SEXO, "
                    + "ATIVO "
                    + "FROM usuario WHERE EMAIL = ?");

            stmt.setString(1, email);

            rs = stmt.executeQuery();
            while (rs.next()) {
                usuario = new UsuarioModel(
                        rs.getInt("IdUsuario"),
                        rs.getString("Nome"),
                        rs.getString("cpf"),
                        rs.getString("Email"),
                        rs.getString("setor"),
                        rs.getString("cargo"),
                        rs.getString("Login"),
                        rs.getString("Senha"),
                        rs.getInt("IdFilial"),
                        rs.getString("sexo"),
                        rs.getBoolean("ativo")
                );
                
                usuario.setFilial(filialDao.obterPorId(usuario.getIdFilial()));
            }

            return usuario;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    public UsuarioModel obterPorEmail(String email, int id) {
        Connection conn = conexao.getConnection();
        UsuarioModel usuario = null;

        try {
            stmt = conn.prepareStatement("SELECT IDUSUARIO, "
                    + "NOME, "
                    + "CPF, "
                    + "EMAIL, "
                    + "IDFILIAL, "
                    + "SETOR, "
                    + "CARGO, "
                    + "LOGIN, "
                    + "SENHA, "
                    + "SEXO, "
                    + "ATIVO "
                    + "FROM usuario WHERE EMAIL = ? AND IDUSUARIO != ?");

            stmt.setString(1, email);
            stmt.setInt(2, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                usuario = new UsuarioModel(
                        rs.getInt("IdUsuario"),
                        rs.getString("Nome"),
                        rs.getString("cpf"),
                        rs.getString("Email"),
                        rs.getString("setor"),
                        rs.getString("cargo"),
                        rs.getString("Login"),
                        rs.getString("Senha"),
                        rs.getInt("IdFilial"),
                        rs.getString("sexo"),
                        rs.getBoolean("ativo")
                );
                
                usuario.setFilial(filialDao.obterPorId(usuario.getIdFilial()));
            }

            return usuario;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    public UsuarioModel obterPorLogin(String login) {
        Connection conn = conexao.getConnection();
        UsuarioModel usuario = null;

        try {
            stmt = conn.prepareStatement("SELECT IDUSUARIO, "
                    + "NOME, "
                    + "CPF, "
                    + "EMAIL, "
                    + "IDFILIAL, "
                    + "SETOR, "
                    + "CARGO, "
                    + "LOGIN, "
                    + "SENHA, "
                    + "SEXO, "
                    + "ATIVO "
                    + "FROM usuario WHERE LOGIN = ?");

            stmt.setString(1, login);

            rs = stmt.executeQuery();
            while (rs.next()) {
                usuario = new UsuarioModel(
                        rs.getInt("IdUsuario"),
                        rs.getString("Nome"),
                        rs.getString("cpf"),
                        rs.getString("Email"),
                        rs.getString("setor"),
                        rs.getString("cargo"),
                        rs.getString("Login"),
                        rs.getString("Senha"),
                        rs.getInt("IdFilial"),
                        rs.getString("sexo"),
                        rs.getBoolean("ativo")
                );
                
                usuario.setFilial(filialDao.obterPorId(usuario.getIdFilial()));
            }

            return usuario;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    public UsuarioModel obterPorLogin(String login, int id) {
        Connection conn = conexao.getConnection();
        UsuarioModel usuario = null;

        try {
            stmt = conn.prepareStatement("SELECT IDUSUARIO, "
                    + "NOME, "
                    + "CPF, "
                    + "EMAIL, "
                    + "IDFILIAL, "
                    + "SETOR, "
                    + "CARGO, "
                    + "LOGIN, "
                    + "SENHA, "
                    + "SEXO, "
                    + "ATIVO "
                    + "FROM usuario WHERE LOGIN = ? AND IDUSUARIO != ?");

            stmt.setString(1, login);
            stmt.setInt(2, id);

            rs = stmt.executeQuery();
            while (rs.next()) {
                usuario = new UsuarioModel(
                        rs.getInt("IdUsuario"),
                        rs.getString("Nome"),
                        rs.getString("cpf"),
                        rs.getString("Email"),
                        rs.getString("setor"),
                        rs.getString("cargo"),
                        rs.getString("Login"),
                        rs.getString("Senha"),
                        rs.getInt("IdFilial"),
                        rs.getString("sexo"),
                        rs.getBoolean("ativo")
                );
                
                usuario.setFilial(filialDao.obterPorId(usuario.getIdFilial()));
            }

            return usuario;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    public UsuarioModel obterPorCpf(String cpf) {
        Connection conn = conexao.getConnection();
        UsuarioModel usuario = null;

        try {
            stmt = conn.prepareStatement("SELECT IDUSUARIO, "
                    + "NOME, "
                    + "CPF, "
                    + "EMAIL, "
                    + "IDFILIAL, "
                    + "SETOR, "
                    + "CARGO, "
                    + "LOGIN, "
                    + "SENHA, "
                    + "SEXO, "
                    + "ATIVO "
                    + "FROM usuario WHERE CPF = ?");

            stmt.setString(1, cpf);

            rs = stmt.executeQuery();
            while (rs.next()) {
                usuario = new UsuarioModel(
                        rs.getInt("IdUsuario"),
                        rs.getString("Nome"),
                        rs.getString("cpf"),
                        rs.getString("Email"),
                        rs.getString("setor"),
                        rs.getString("cargo"),
                        rs.getString("Login"),
                        rs.getString("Senha"),
                        rs.getInt("IdFilial"),
                        rs.getString("sexo"),
                        rs.getBoolean("ativo")
                );
                
                usuario.setFilial(filialDao.obterPorId(usuario.getIdFilial()));
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
            stmt = conn.prepareStatement("SELECT "
                    + "IDUSUARIO, "
                    + "NOME, "
                    + "CPF, "
                    + "EMAIL, "
                    + "IDFILIAL, "
                    + "SETOR, "
                    + "CARGO, "
                    + "LOGIN, "
                    + "SENHA, "
                    + "SEXO, "
                    + "ATIVO"
                    + " FROM usuario");

            rs = stmt.executeQuery();
            while (rs.next()) {
                usuario = new UsuarioModel(
                        rs.getInt("IdUsuario"),
                        rs.getString("Nome"),
                        rs.getString("cpf"),
                        rs.getString("Email"),
                        rs.getString("setor"),
                        rs.getString("cargo"),
                        rs.getString("Login"),
                        rs.getString("Senha"),
                        rs.getInt("IdFilial"),
                        rs.getString("sexo"),
                        rs.getBoolean("ativo")
                );
                usuario.setFilial(filialDao.obterPorId(usuario.getIdFilial()));
                usuarios.add(usuario);
            }

            return usuarios;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    public List<UsuarioModel> obterTodosPorCargo(String cargo) {
        Connection conn = conexao.getConnection();
        UsuarioModel usuario = null;
        List<UsuarioModel> usuarios = new ArrayList<UsuarioModel>();

        try {
            stmt = conn.prepareStatement("SELECT "
                    + "IDUSUARIO, "
                    + "NOME, "
                    + "CPF, "
                    + "EMAIL, "
                    + "IDFILIAL, "
                    + "SETOR, "
                    + "CARGO, "
                    + "LOGIN, "
                    + "SENHA, "
                    + "SEXO, "
                    + "ATIVO"
                    + " FROM usuario WHERE CARGO = ?");

            stmt.setString(1, cargo);

            rs = stmt.executeQuery();
            while (rs.next()) {
                usuario = new UsuarioModel(
                        rs.getInt("IdUsuario"),
                        rs.getString("Nome"),
                        rs.getString("cpf"),
                        rs.getString("Email"),
                        rs.getString("setor"),
                        rs.getString("cargo"),
                        rs.getString("Login"),
                        rs.getString("Senha"),
                        rs.getInt("IdFilial"),
                        rs.getString("sexo"),
                        rs.getBoolean("ativo")
                );
                usuario.setFilial(filialDao.obterPorId(usuario.getIdFilial()));
                usuarios.add(usuario);
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
            stmt = conn.prepareStatement("INSERT INTO usuario("
                    + "NOME, "
                    + "CPF, "
                    + "EMAIL, "
                    + "IDFILIAL, "
                    + "SETOR, "
                    + "CARGO, "
                    + "LOGIN, "
                    + "SENHA, "
                    + "SEXO)"
                    + " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getCpf());
            stmt.setString(3, usuario.getEmail());
            stmt.setInt(4, usuario.getIdFilial());
            stmt.setString(5, usuario.getSetor());
            stmt.setString(6, usuario.getCargo());
            stmt.setString(7, usuario.getLogin());
            stmt.setString(8, usuario.getSenha());
            stmt.setString(9, usuario.getSexo());

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
            stmt = conn.prepareStatement("UPDATE usuario SET "
                    + "NOME = ?,"
                    + "EMAIL = ?,"
                    + "IDFILIAL = ?,"
                    + "SETOR = ?,"
                    + "CARGO = ?,"
                    + "SEXO = ?,"
                    + "ATIVO = ?"
                    + " WHERE IDUSUARIO = ?");
            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setInt(3, usuario.getIdFilial());
            stmt.setString(4, usuario.getSetor());
            stmt.setString(5, usuario.getCargo());
            stmt.setString(6, usuario.getSexo());
            stmt.setBoolean(7, usuario.isAtivo());
            stmt.setInt(8, usuario.getIdUsuario());

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
            stmt = conn.prepareStatement("UPTADE usuario SET ATIVO = 0 WHERE IDUSUARIO = ?");
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
                    + "SETOR, "
                    + "CARGO, "
                    + "LOGIN, "
                    + "SENHA, "
                    + "SEXO, "
                    + "ATIVO "
                    + "FROM usuario WHERE LOGIN = ? AND SENHA = ?");

            stmt.setString(1, nomeUsuario);
            stmt.setString(2, senha);

            rs = stmt.executeQuery();
            while (rs.next()) {
                usuario = new UsuarioModel(
                        rs.getInt("IdUsuario"),
                        rs.getString("Nome"),
                        rs.getString("cpf"),
                        rs.getString("Email"),
                        rs.getString("setor"),
                        rs.getString("cargo"),
                        rs.getString("Login"),
                        rs.getString("Senha"),
                        rs.getInt("IdFilial"),
                        rs.getString("sexo"),
                        rs.getBoolean("ativo")
                );
            }

            return usuario;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

    @Override
    public boolean validarLogin(String login, String senha) {
        Connection conn = conexao.getConnection();
        boolean condicao = false;

        try {
            stmt = conn.prepareStatement("SELECT LOGIN, "
                    + "SENHA "
                    + "FROM usuario WHERE LOGIN = ? AND SENHA = ?");

            stmt.setString(1, login);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            if (rs.next()) {
                condicao = true;
            }

        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);

        }
        return condicao;
    }
}
