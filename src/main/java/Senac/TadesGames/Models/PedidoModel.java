/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Models;

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
    private final double valorTotal;
    private Date dataPedido;
    private int idCliente;
    private ClienteModel cliente;
    private List<ItensPedidoModel> itensPedido;
    private int idFilial;
    private FilialModel filial;
    private int idUsuario;
    private UsuarioModel usuario;
    private int formaPagamento;
    private final String descFormaPagamento;
    private int parcela;
    private double valorRecebido;

    public PedidoModel(int idPedido, int status, Date dataPedido,
            int idCliente, int idFilial, int idUsuario, int formaPagamento, int parcela, 
            double valorRecebido) {      
        this.idPedido = idPedido;
        this.status = status;
        this.dataPedido = dataPedido;
        this.idCliente = idCliente;
        this.itensPedido = new ArrayList<ItensPedidoModel>();
        this.idFilial = idFilial;
        this.idUsuario = idUsuario;
        this.formaPagamento = formaPagamento;
        this.parcela = parcela;
        this.valorRecebido = valorRecebido;
        this.descFormaPagamento = this.getDescFormaPagamento();
        this.valorTotal = getValorTotal();
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
    private void setIdPedido(int idPedido) {
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
    private void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the valorTotal
     */
    public final double getValorTotal() {
        double valor = 0;
        for(ItensPedidoModel p : this.itensPedido){
            valor += p.getQuantidade() * p.getValorUnitario();
        }

        return valor;
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
    private void setDataPedido(Date dataPedido) {
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
    private void setIdCliente(int idCliente) {
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
    private void setIdFilial(int idFilial) {
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
    private void setIdUsuario(int idUsuario) {
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
    private void setFormaPagamento(int formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

    /**
     * @return the parcela
     */
    public int getParcela() {
        return parcela;
    }

    /**
     * @param parcela the parcelas to set
     */
    private void setParcela(int parcela) {
        this.parcela = parcela;
    }

    /**
     * @return the valorRecebido
     */
    public double getValorRecebido() {
        return valorRecebido;
    }

    /**
     * @param valorRecebido the valorRecebido to set
     */
    private void setValorRecebido(double valorRecebido) {
        this.valorRecebido = valorRecebido;
    }
    
    public final String getDescFormaPagamento(){
        switch(this.formaPagamento){
            case 1:
                return "Débito";
            case 2:
                return "Crédito";
            case 3:
                return "Dinheiro";
            default:
                return "Não informado";
        }
    }
    
    public int getQtdProdutos(){
        return itensPedido.size();
    }

    /**
     * @return the cliente
     */
    public ClienteModel getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteModel cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the filial
     */
    public FilialModel getFilial() {
        return filial;
    }

    /**
     * @param filial the filial to set
     */
    public void setFilial(FilialModel filial) {
        this.filial = filial;
    }

    /**
     * @return the usuario
     */
    public UsuarioModel getUsuario() {
        return usuario;
    }

    /**
     * @param usuario the usuario to set
     */
    public void setUsuario(UsuarioModel usuario) {
        this.usuario = usuario;
    }
    
}
