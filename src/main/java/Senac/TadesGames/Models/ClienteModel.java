/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Models;

import java.util.Date;

/**
 *
 * @author Gi
 */
public class ClienteModel extends PessoaModel {

    private int idCliente;
    private Date dataNasc;
    private String telefone;
    private String celular;
    
    public ClienteModel(int idCliente, String nome, Date dataNasc,
            String email, String telefone, String celular, String sexo, boolean ativo) {
        
        this.idCliente = idCliente;
        this.nome = nome;
        this.dataNasc = dataNasc;
        this.email = email;
        this.telefone = telefone;
        this.celular = celular;
        this.sexo = sexo;
        this.ativo = ativo;
    }


    /**
     * @return the idCliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the dataNasc
     */
    public Date getDataNasc() {
        return dataNasc;
    }

    /**
     * @param dataNasc the dataNasc to set
     */
    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    /**
     * @return the celular
     */
    public String getCelular() {
        return celular;
    }

    /**
     * @param celular the celular to set
     */
    public void setCelular(String celular) {
        this.celular = celular;
    }

}