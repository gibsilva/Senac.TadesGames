/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Models;

/**
 *
 * @author Giovanni.Carignato
 */
public class RelatorioProdutoModel {
    private final int idProduto;
    private final String nomeProduto;
    private final int qtdProduto;
    private final double totalVendido;
    private final String categoria;
    private final String dataUltimaVenda;
    private final boolean ativo;
    private ProdutoModel produto;
    
    public RelatorioProdutoModel(int idProduto, String nomeProduto, int qtdProduto,
            double totalVendido, String categoria, String dataUltimaVenda, boolean ativo){
        
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.qtdProduto = qtdProduto;
        this.totalVendido = totalVendido;
        this.categoria = categoria;
        this.dataUltimaVenda = dataUltimaVenda;
        this.ativo = ativo;
    }

    /**
     * @return the idProduto
     */
    public int getIdProduto() {
        return idProduto;
    }

    /**
     * @return the nomeProduto
     */
    public String getNomeProduto() {
        return nomeProduto;
    }

    /**
     * @return the qtdProduto
     */
    public int getQtdProduto() {
        return qtdProduto;
    }

    /**
     * @return the totalVendido
     */
    public double getTotalVendido() {
        return totalVendido;
    }

    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }

    /**
     * @return the dataUltimaVenda
     */
    public String getDataUltimaVenda() {
        return dataUltimaVenda;
    }

    /**
     * @return the ativo
     */
    public boolean isAtivo() {
        return ativo;
    }

    public ProdutoModel getProduto(){
        return this.produto;
    }
    
    public void setProduto(ProdutoModel produto){
        this.produto = produto;
    }
}

