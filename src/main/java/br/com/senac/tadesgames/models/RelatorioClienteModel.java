/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.models;

/**
 *
 * @author Giovanni.Carignato
 */
public class RelatorioClienteModel {
    private final int idCliente;
    private final String nomeCliente;
    private final String cpf;
    private final String cnpj;
    private final int qtdPedidos;
    private final String dataUltimoPedido;
    private final double totalComprado;
    private final boolean ativo;
    
    public RelatorioClienteModel(int idCliente, String nomeCliente, String cpf, String cnpj,
            int qtdPedidos, String dataUltimoPedido, double totalComprado, boolean ativo){
        
        this.idCliente = idCliente;
        this.nomeCliente = nomeCliente;
        this.cpf = cpf;
        this.cnpj = cnpj;
        this.qtdPedidos = qtdPedidos;
        this.dataUltimoPedido = dataUltimoPedido;
        this.totalComprado = totalComprado;
        this.ativo = ativo;
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
    public String getDataUltimoPedido() {
        return dataUltimoPedido;
    }

    /**
     * @return the totalComprado
     */
    public double getTotalComprado() {
        return totalComprado;
    }

    /**
     * @return the ativo
     */
    public boolean isAtivo() {
        return ativo;
    }
}
