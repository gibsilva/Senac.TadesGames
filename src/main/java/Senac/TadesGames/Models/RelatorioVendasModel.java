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
public class RelatorioVendasModel {

    private final int idPedido;
    private PedidoModel pedido;
    private final String dataFormatada;
    private final double valorTotal;

    public RelatorioVendasModel(int idPedido, double valorTotal, String dataFormatada) {
        this.idPedido = idPedido;
        this.valorTotal = valorTotal;
        this.dataFormatada = dataFormatada;
    }

    /**
     * @return the idPedido
     */
    public int getIdPedido() {
        return idPedido;
    }
    
    public String getDataFormatada(){
        return this.dataFormatada;
    }

    public void setPedido(PedidoModel pedido) {
        this.pedido = pedido;
    }

    public double getValorTotal() {
        return valorTotal;
    }

}
