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
public class RelatorioClienteModel {
    private int idCliente;
    private String nomeCliente;
    private String cpf;
    private int qtdPedidos;
    private Date dataUltimoPedido;
    private double totalComprado;
    
    public RelatorioClienteModel(int idCliente, String nomeCliente, String cpf,
            int qtdPedidos, Date dataUltimoPedido, double totalComprado){
        
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.cpf = cpf;
        this.qtdPedidos = qtdPedidos;
        this.dataUltimoPedido = dataUltimoPedido;
        this.totalComprado = totalComprado; 
    }

    /**
     * @return the idCliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * @return the nomeCliente
     */
    public String getNomeCliente() {
        return nomeCliente;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @return the qtdPedidos
     */
    public int getQtdPedidos() {
        return qtdPedidos;
    }

    /**
     * @return the dataUltimoPedido
     */
    public Date getDataUltimoPedido() {
        return dataUltimoPedido;
    }

    /**
     * @return the totalComprado
     */
    public double getTotalComprado() {
        return totalComprado;
    }
}
