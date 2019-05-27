/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gi
 */
public class FilialModel {

    private int idFilial;
    private String cnpj;
    private String nome;
    private String cep;
    private String logradouro;
    private int numero;
    private String complemento;
    private String bairro;
    private String cidade;
    private String estado;
    private boolean ativo;
    private List<ProdutoModel> produtos;
    private List<PedidoModel> pedidos;
    private List<UsuarioModel> usuarios;

    public FilialModel(int idFilial, String cnpj, String nome, String cep,
            String logradouro, int numero, String complemento, String bairro, String cidade, String estado, boolean ativo) {
        this.idFilial = idFilial;
        this.cnpj = cnpj;
        this.nome = nome;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.ativo = ativo;
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
    private void setIdFilial(int idFilial) {
        this.idFilial = idFilial;
    }

    /**
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    private void setCnpj(String cnpj) {
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
    private void setNome(String nome) {
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

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    private void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the logradouro
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * @param logradouro the logradouro to set
     */
    private void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    private void setNumero(int numero) {
        this.numero = numero;
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
    }

    /**
     * @param complemento the complemento to set
     */
    private void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    private void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    private void setCidade(String cidade) {
        this.cidade = cidade;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    private void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * @return the ativo
     */
    public boolean getAtivo() {
        return ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    private void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
