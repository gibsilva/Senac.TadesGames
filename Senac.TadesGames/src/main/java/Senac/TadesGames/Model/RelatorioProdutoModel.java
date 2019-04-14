/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Model;

import java.util.Date;

/**
 *
 * @author Gi
 */
public class RelatorioProdutoModel {
    private int idProduto;
    private int nomeProduto;
    private int qtdProduto;
    private double totalVendido;
    private String categoria;
    private Date dataUltimaVenda; 
    
    public RelatorioProdutoModel(int idProduto, int nomeProduto, int qtdProduto,
            double totalVendido, String categoria, Date dataultimaVenda){
        
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.qtdProduto = qtdProduto;
        this.totalVendido = totalVendido;
        this.categoria = categoria;
        this.dataUltimaVenda = dataUltimaVenda;
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
    public int getNomeProduto() {
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
    public Date getDataUltimaVenda() {
        return dataUltimaVenda;
    }

}

