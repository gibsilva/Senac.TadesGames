/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gi
 */
public class FilialModel {
    private int idFilial;
    private int cnpj;
    private String nome;
    private List<ProdutoModel> produtos;
    private List<PedidoModel> pedidos;
    private List<UsuarioModel> usuarios;
    
    public FilialModel(int idFilial, int cnpj, String nome){
        this.idFilial = idFilial;
        this.cnpj = cnpj;
        this.nome = nome;
        this.produtos = new ArrayList<ProdutoModel>();
        this.pedidos = new ArrayList<PedidoModel>();
        this.usuarios = new ArrayList<UsuarioModel>();
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
     * @return the cnpj
     */
    public int getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(int cnpj) {
        this.cnpj = cnpj;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the produtos
     */
    public List<ProdutoModel> getProdutos() {
        return produtos;
    }

    /**
     * @param produtos the produtos to set
     */
    public void setProdutos(List<ProdutoModel> produtos) {
        this.produtos = produtos;
    }

    /**
     * @return the pedidos
     */
    public List<PedidoModel> getPedidos() {
        return pedidos;
    }

    /**
     * @param pedidos the pedidos to set
     */
    public void setPedidos(List<PedidoModel> pedidos) {
        this.pedidos = pedidos;
    }

    /**
     * @return the usuarios
     */
    public List<UsuarioModel> getUsuarios() {
        return usuarios;
    }

    /**
     * @param usuarios the usuarios to set
     */
    public void setUsuarios(List<UsuarioModel> usuarios) {
        this.usuarios = usuarios;
    }
    
    
}
