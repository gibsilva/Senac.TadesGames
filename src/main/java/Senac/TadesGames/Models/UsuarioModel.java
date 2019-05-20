/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Models;

import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author Gi
 */
public final class UsuarioModel extends PessoaModel {

    private int idUsuario;
    private String setor;
    private String cargo;
    private PermissaoModel permissao;
    private String login;
    private String senha;
    private int idFilial;
    private FilialModel filial;

    public UsuarioModel(int idUsuario, String nome, String cpf,
            String email, String setor, String cargo,
            String login, String senha, int idFilial, String sexo, boolean ativo) {

        this.idUsuario = idUsuario;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.setor = setor;
        this.cargo = cargo;
        this.login = login;
        if(senha.length() == 60)
            this.senha = senha;
        else
            setSenhaEncriptada(senha);
        this.idFilial = idFilial;
        this.sexo = sexo;
        this.ativo = ativo;
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
        return this.senha;
    }

    private void setSenhaEncriptada(String senha) {
        this.senha = BCrypt.hashpw(senha, BCrypt.gensalt(12));
    }
    
    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean validarSenha(String senha) {
        boolean senhaValida = BCrypt.checkpw(senha, this.getSenha());
        return senhaValida;
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

    /**
     * @return the setor
     */
    public String getSetor() {
        return setor;
    }

    /**
     * @param setor the setor to set
     */
    public void setSetor(String setor) {
        this.setor = setor;
    }

    /**
     * @return the filial
     */
    public FilialModel getFilial() {
        return filial;
    }

    /**
     * @param filial the filial to set
     */
    public void setFilial(FilialModel filial) {
        this.filial = filial;
    }

}
