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
public class HomeProdutoModel {
    private final int quantidadeVendida;
    private final int quantidadeTotalEstoque;
    
    public HomeProdutoModel(int quantidadeVendida, int quantidadeTotalEstoque){
        this.quantidadeVendida = quantidadeVendida;
        this.quantidadeTotalEstoque = quantidadeTotalEstoque;
    }

    /**
     * @return the quantidadeVendida
     */
    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    /**
     * @return the quantidadeTotalEstoque
     */
    public int getQuantidadeTotalEstoque() {
        return quantidadeTotalEstoque;
    }
}
