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
    private String cnpj;
    private int qtdPedidos;
    private Date dataUltimoPedido;
    private double totalComprado;
    private boolean ativo;
    
    public RelatorioClienteModel(int idCliente, String nomeCliente, String cpf, String cnpj,
            int qtdPedidos, Date dataUltimoPedido, double totalComprado, boolean ativo){
        
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
    public Date getDataUltimoPedido() {
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

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
