/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Gi
 */
public class PedidoModel {

    private int idPedido;
    private int status;
    private double valorTotal;
    private Date dataPedido;
    private int idCliente;
    private List<ItensPedidoModel> itensPedido;
    private int idFilial;
    private int idUsuario;
    private int formaPagamento;

    public PedidoModel(int idPedido, int status, Date dataPedido,
            int idCliente, int idFilial, int idUsuario, int formaPagamento) {
        
        this.idPedido = idPedido;
        this.status = status;
        this.dataPedido = dataPedido;
        this.idCliente = idCliente;
        this.itensPedido = new ArrayList<ItensPedidoModel>();
        this.idFilial = idFilial;
        this.idUsuario = idUsuario;
        this.formaPagamento = formaPagamento;
    }

    /**
     * @return the idPedido
     */
    public int getIdPedido() {
        return idPedido;
    }

    /**
     * @param idPedido the idPedido to set
     */
    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the valorTotal
     */
    public double getValorTotal() {
        double valor = 0;
        for(ItensPedidoModel p : itensPedido){
            valor += p.getQuantidade() * p.getValorUnitario();
        }
        
        return valor;
    }

    /**
     * @param valorTotal the valorTotal to set
     */
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    /**
     * @return the dataPedido
     */
    public Date getDataPedido() {
        return dataPedido;
    }

    /**
     * @param dataPedido the dataPedido to set
     */
    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    /**
     * @return the idCliente
     */
    public int getIdCliente() {
        return idCliente;
    }

    /**
     * @param idCliente the idCliente to set
     */
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * @return the itensPedido
     */
    public List<ItensPedidoModel> getItensPedido() {
        return itensPedido;
    }

    /**
     * @param itensPedido the itensPedido to set
     */
    public void setItensPedido(List<ItensPedidoModel> itensPedido) {
        this.itensPedido = itensPedido;
    }

    /**
     * @return the idFilial
     */
    public int getIdFilial() {
        return idFilial;
    }

    /**
     * @param idFilial the idFilial to set
     */
    public void setIdFilial(int idFilial) {
        this.idFilial = idFilial;
    }

    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }

    /**
     * @param idUsuario the idUsuario to set
     */
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the formaPagamento
     */
    public int getFormaPagamento() {
        return formaPagamento;
    }

    /**
     * @param formaPagamento the formaPagamento to set
     */
    public void setFormaPagamento(int formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    
}
