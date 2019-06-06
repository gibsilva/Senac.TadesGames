/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.models;

import java.text.NumberFormat;
import java.util.Locale;

/**
 *
 * @author Gi
 */
public class ItensPedidoModel {

    private int idItensPedido;
    private int idProduto;
    private ProdutoModel produto;
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
    private void setIdItensPedido(int idItensPedido) {
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
    private void setIdProduto(int idProduto) {
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
    private void setValorUnitario(double valorUnitario) {
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
    private void setQuantidade(int quantidade) {
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

    /**
     * @return the produto
     */
    public ProdutoModel getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(ProdutoModel produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        Locale ptBr = new Locale("pt", "BR");
        String valorString = NumberFormat.getCurrencyInstance(ptBr).format(this.valorUnitario);
        return valorString;
    }
}
