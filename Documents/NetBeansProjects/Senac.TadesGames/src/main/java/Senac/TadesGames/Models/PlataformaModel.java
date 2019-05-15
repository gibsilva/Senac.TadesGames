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
public class PlataformaModel {
    private int idPlataforma;
    private String nome;
    
    public PlataformaModel(int idPlataforma, String nome){
        this.idPlataforma = idPlataforma;
        this.nome = nome;
    }

    /**
     * @return the idPlataforma
     */
    public int getIdPlataforma() {
        return idPlataforma;
    }

    /**
     * @param idPlataforma the idPlataforma to set
     */
    public void setIdPlataforma(int idPlataforma) {
        this.idPlataforma = idPlataforma;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
    
}
