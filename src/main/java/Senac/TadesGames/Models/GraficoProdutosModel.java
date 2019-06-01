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
public class GraficoProdutosModel {
    private final String produto;
    private final int qtdVendido;
    
    public GraficoProdutosModel(String produto, int qtdVendido){
        this.produto = produto;
        this.qtdVendido = qtdVendido;
    }

    /**
     * @return the produto
     */
    public String getProduto() {
        return produto;
    }

    /**
     * @return the qtdVendido
     */
    public int getQtdVendido() {
        return qtdVendido;
    }
}
