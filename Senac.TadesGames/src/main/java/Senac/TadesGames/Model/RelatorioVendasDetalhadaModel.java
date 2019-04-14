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
public class RelatorioVendasDetalhadaModel {
    private int idProduto;
    private String nomeProduto;
    private String nomeCliente;
    private String cpfCliente;
    private int idPedido;
    private Date dataVenda;
    private int cnpjFilial;
    private double valorTotal;
    private int qtdProduto;
    private double valorUniProduto;
    
    public RelatorioVendasDetalhadaModel(int idProduto, String nomeProduto, 
            String nomeCliente, String cpfCliente, int idPedido, Date dataVenda,
            int cnpjFilial, double valorTotal, int qtdProduto, double valorUniProduto){
        
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.idPedido = idPedido;
        this.dataVenda = dataVenda;
        this.cnpjFilial = cnpjFilial;
        this.valorTotal = valorTotal;
        this.qtdProduto = qtdProduto;
        this.valorUniProduto = valorUniProduto;  
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
     * @return the nomeCliente
     */
    public String getNomeCliente() {
        return nomeCliente;
    }

    /**
     * @return the cpfCliente
     */
    public String getCpfCliente() {
        return cpfCliente;
    }

    /**
     * @return the idPedido
     */
    public int getIdPedido() {
        return idPedido;
    }

    /**
     * @return the dataVenda
     */
    public Date getDataVenda() {
        return dataVenda;
    }

    /**
     * @return the cnpjFilial
     */
    public int getCnpjFilial() {
        return cnpjFilial;
    }

    /**
     * @return the valorTotal
     */
    public double getValorTotal() {
        return valorTotal;
    }

    /**
     * @return the qtdProduto
     */
    public int getQtdProduto() {
        return qtdProduto;
    }

    /**
     * @return the valorUniProduto
     */
    public double getValorUniProduto() {
        return valorUniProduto;
    }

}
