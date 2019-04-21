/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Models;

/**
 *
 * @author Gi
 */
public class UsuarioModel extends PessoaModel {

    private int idUsuario;
    private String filial;
    private String cargo;
    private PermissaoModel permissao;
    private String login;
    private String senha;
    private int idFilial;
   

    public UsuarioModel(int idUsuario, String nome, String cpf,
            String email, String filial, String cargo,
            String login, String senha, int idFilial, String sexo) {

        this.idUsuario = idUsuario;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.filial = filial;
        this.cargo = cargo;
        this.login = login;
        this.senha = senha;
        this.idFilial = idFilial;
        this.sexo = sexo;
    }

    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the filial
     */
    public String getFilial() {
        return filial;
    }

    /**
     * @param filial the filial to set
     */
    public void setFilial(String filial) {
        this.filial = filial;
    }

    /**
     * @return the cargo
     */
    public String getCargo() {
        return cargo;
    }

    /**
     * @param cargo the cargo to set
     */
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    /**
     * @return the permissao
     */
    public PermissaoModel getPermissao() {
        return permissao;
    }

    /**
     * @param permissao the permissao to set
     */
    public void setPermissao(PermissaoModel permissao) {
        this.permissao = permissao;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return the idFilial
     */
    public int getIdFilial() {
        return idFilial;
    }

    /**
     * @param idFilial the idFilial to set
     */
    public void setIdFilial(int idFilial) {
        this.idFilial = idFilial;
    }
    
}
