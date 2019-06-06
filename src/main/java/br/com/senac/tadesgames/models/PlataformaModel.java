/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gi
 */
public class PlataformaModel {
    private int idPlataforma;
    private String nome;
    private List<ProdutoModel> produtos;
    
    public PlataformaModel(int idPlataforma, String nome){
        this.idPlataforma = idPlataforma;
        this.nome = nome;
        this.produtos = new ArrayList<ProdutoModel>();
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
    private void setIdPlataforma(int idPlataforma) {
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
