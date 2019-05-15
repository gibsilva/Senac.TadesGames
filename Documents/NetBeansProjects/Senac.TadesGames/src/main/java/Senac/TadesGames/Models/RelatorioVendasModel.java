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
public class RelatorioVendasModel {
    
    private int idPedido;
    private String nomeCliente;
    private String cpfCliente;
    private Date dataVenda;
    private int cnpjFilial;
    private double valorTotal;
    
    public RelatorioVendasModel(String nomeCliente, String cpfCliente, int idPedido, Date dataVenda,
            int cnpjFilial, double valorTotal){
        
        this.nomeCliente = nomeCliente;
        this.cpfCliente = cpfCliente;
        this.idPedido = idPedido;
        this.dataVenda = dataVenda;
        this.cnpjFilial = cnpjFilial;
        this.valorTotal = valorTotal;  
    }

    /**
     * @return the idPedido
     */
    public int getIdPedido() {
        return idPedido;
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
    
}
