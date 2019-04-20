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
public class ItensPedidoModel {

    private int idItensPedido;
    private int idProduto;
    private double valorUnitario;
    private int quantidade;
    private int idPedido;

    public ItensPedidoModel(int idItensPedido, int idProduto,
            double valorUnitario, int quantidade, int idPedido) {
        
        this.idItensPedido = idItensPedido;
        this.idProduto = idProduto;
        this.valorUnitario = valorUnitario;
        this.quantidade = quantidade;
        this.idPedido = idPedido;
    }

    /**
     * @return the idItensPedido
     */
    public int getIdItensPedido() {
        return idItensPedido;
    }

    /**
     * @param idItensPedido the idItensPedido to set
     */
    public void setIdItensPedido(int idItensPedido) {
        this.idItensPedido = idItensPedido;
    }

    /**
     * @return the idProduto
     */
    public int getIdProduto() {
        return idProduto;
    }

    /**
     * @param idProduto the idProduto to set
     */
    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    /**
     * @return the valorUnitario
     */
    public double getValorUnitario() {
        return valorUnitario;
    }

    /**
     * @param valorUnitario the valorUnitario to set
     */
    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    /**
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param quantidade the quantidade to set
     */
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
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
}
