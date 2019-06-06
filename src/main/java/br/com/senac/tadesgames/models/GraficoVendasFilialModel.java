/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.models;

/**
 *
 * @author Gi
 */
public class GraficoVendasFilialModel {
    private final String filial;
    private final double total;
    
    public GraficoVendasFilialModel(String filial, double total){
        this.filial = filial;
        this.total = total;
    }

    /**
     * @return the filial
     */
    public String getFilial() {
        return filial;
    }

    /**
     * @return the total
     */
    public double getTotal() {
        return total;
    }
}
