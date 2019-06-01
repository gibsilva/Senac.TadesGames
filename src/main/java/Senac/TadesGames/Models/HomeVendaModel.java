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
public class HomeVendaModel {
    private final int qtdVendas;
    private final double totalVendido;
    
    public HomeVendaModel(int qtdVendas, double totalVendido){
        this.qtdVendas = qtdVendas;
        this.totalVendido = totalVendido;
    }

    /**
     * @return the qtdVendas
     */
    public int getQtdVendas() {
        return qtdVendas;
    }

    /**
     * @return the totalVendido
     */
    public double getTotalVendido() {
        return totalVendido;
    }
}
