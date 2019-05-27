/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gi
 */
public class GeneroModel {
    private int idGenero;
    private String nome;
    private List<ProdutoModel> produtos;
    
    public GeneroModel(int idGenero, String nome){
        this.idGenero = idGenero;
        this.nome = nome;
        this.produtos = new ArrayList<ProdutoModel>();
        
    }

    /**
     * @return the idGenero
     */
    public int getIdGenero() {
        return idGenero;
    }

    /**
     * @param idGenero the idGenero to set
     */
    private void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
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
    private void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the produtos
     */
    public List<ProdutoModel> getProdutos() {
        return produtos;
    }

    /**
     * @param produtos the produtos to set
     */
    public void setProdutos(List<ProdutoModel> produtos) {
        this.produtos = produtos;
    }
}
