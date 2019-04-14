/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gi
 */
public class CategoriaModel {
    private int idCategoria;
    private String nome;
    private List<ProdutoModel> produtos;

    public CategoriaModel(int idCategoria, String nome){
        this.idCategoria = idCategoria;
        this.nome = nome;
        this.produtos = new ArrayList<ProdutoModel>();
    }
    /**
     * @return the idCategoria
     */
    public int getIdCategoria() {
        return idCategoria;
    }

    /**
     * @param idCategoria the idCategoria to set
     */
    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
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

