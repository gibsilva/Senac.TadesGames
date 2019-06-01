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
public class HomeVendedorModel {
    private final int qtdvendas;
    private final String nomeVendedor;
    private final double totalVendido;
    private final int idFilial;
    private final int idUsuario;
    
    public HomeVendedorModel(int qtdVendas, String nomeVendedor, double totalVendido, int idFilial, int idUsuario){
        this.idFilial = idFilial;
        this.nomeVendedor = nomeVendedor;
        this.totalVendido = totalVendido;
        this.qtdvendas = qtdVendas;
        this.idUsuario = idUsuario;
    }

    /**
     * @return the qtdvendas
     */
    public int getQtdvendas() {
        return qtdvendas;
    }

    /**
     * @return the nomeVendedor
     */
    public String getNomeVendedor() {
        return nomeVendedor;
    }

    /**
     * @return the totalVendido
     */
    public double getTotalVendido() {
        return totalVendido;
    }

    /**
     * @return the idFilial
     */
    public int getIdFilial() {
        return idFilial;
    }

    /**
     * @return the idUsuario
     */
    public int getIdUsuario() {
        return idUsuario;
    }
    
    
}
