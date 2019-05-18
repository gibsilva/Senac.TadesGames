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
public class RelatorioProdutoModel {
    private int idProduto;
    private String nomeProduto;
    private int qtdProduto;
    private double totalVendido;
    private String categoria;
    private Date dataUltimaVenda;
    private boolean ativo;
    
    public void RelatorioProdutoModel(int idProduto, String nomeProduto, int qtdProduto,
            double totalVendido, String categoria, Date dataUltimaVenda, boolean ativo){
        
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
     *
     * @param IdProduto
     */
    public void setIdProduto(int IdProduto){
        this.idProduto = IdProduto;
    }
    /**
     * @return the nomeProduto
     */
    public String getNomeProduto() {
        return nomeProduto;
    }
    
    public void setNomeProduto(String nomeProduto){
        this.nomeProduto = nomeProduto;
    }

    /**
     * @return the qtdProduto
     */
    public int getQtdProduto() {
        return qtdProduto;
    }
    public void setQtdProduto(int qtdProduto){
        this.qtdProduto = qtdProduto;
    }

    /**
     * @return the totalVendido
     */
    public double getTotalVendido() {
        return totalVendido;
    }
    public void setTotalVendido(Double totalVendido){
        this.totalVendido = totalVendido;
    }
    /**
     * @return the categoria
     */
    public String getCategoria() {
        return categoria;
    }
    public void setCategoria(String categoria){
        this.categoria = categoria;
    }
    /**
     * @return the dataUltimaVenda
     */
    public Date getDataUltimaVenda() {
        return dataUltimaVenda;
    }
    public void setDataUltimaVenda(Date dataUltimaVenda){
        this.dataUltimaVenda = dataUltimaVenda;
    }
    /**
     * @return the ativo
     */
    public boolean isAtivo() {
        return ativo;
    }
    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}

