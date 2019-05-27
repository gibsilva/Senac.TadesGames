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
public class ProdutoModel {
    private int idProduto;
    private String nome;
    private String descricao;
    private double precoCompra;
    private double precoVenda;
    private int idCategoria;
    private CategoriaModel categoria;
    private int idGenero;
    private GeneroModel genero;
    private boolean ativo;
    private int idFilial;
    private FilialModel filial;
    private int idPlataforma;
    private PlataformaModel plataforma;
    private int quantidadeEstoque;

    public ProdutoModel(int idProduto, String nome, String descricao, 
           double precoCompra, double precoVenda, int idCategoria,
           int idGenero, boolean ativo, int idFilial, int idPlataforma, int quantidadeEstoque){        
        this.idProduto = idProduto;
        this.nome = nome;
        this.descricao = descricao;
        this.precoCompra = precoCompra;
        this.precoVenda = precoVenda;
        this.idCategoria = idCategoria;
        this.idGenero = idGenero;
        this.ativo = ativo;
        this.idFilial = idFilial;
        this.idPlataforma = idPlataforma;
        this.quantidadeEstoque = quantidadeEstoque;
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
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    private void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return the precoCompra
     */
    public double getPrecoCompra() {
        return precoCompra;
    }

    /**
     * @param precoCompra the precoCompra to set
     */
    private void setPrecoCompra(double precoCompra) {
        this.precoCompra = precoCompra;
    }

    /**
     * @return the precoVenda
     */
    public double getPrecoVenda() {
        return precoVenda;
    }

    /**
     * @param precoVenda the precoVenda to set
     */
    private void setPrecoVenda(double precoVenda) {
        this.precoVenda = precoVenda;
    }
    
    /**
     * @return the idCategoria
     */
    public int getIdCategoria() {
        return idCategoria;
    }

    /**
     * @param idCategoria the idCategoria to set
     */
    private void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    /**
     * @return the categoria
     */
    public CategoriaModel getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(CategoriaModel categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the idGenero
     */
    public int getIdGenero() {
        return idGenero;
    }

    /**
     * @param idGenero the idGenero to set
     */
    private void setIdGenero(int idGenero) {
        this.idGenero = idGenero;
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

    /**
     * @return the idFilial
     */
    public int getIdFilial() {
        return idFilial;
    }

    /**
     * @param idFilial the filial to set
     */
    private void setIdFilial(int idFilial) {
        this.idFilial = idFilial;
    }

    /**
     * @return the idPlataforma
     */
    public int getIdPlataforma() {
        return idPlataforma;
    }

    /**
     * @param idPlataforma the idPlataforma to set
     */
    private void setIdPlataforma(int idPlataforma) {
        this.idPlataforma = idPlataforma;
    }

    /**
     * @return the plataforma
     */
    public PlataformaModel getPlataforma() {
        return plataforma;
    }

    /**
     * @param plataforma the plataforma to set
     */
    public void setPlataforma(PlataformaModel plataforma) {
        this.plataforma = plataforma;
    }
    
    public void setGenero(GeneroModel genero){
        this.genero = genero;
    }
    
    public GeneroModel getGenero(){
        return this.genero;
    }

    /**
     * @return the quantidadeEstoque
     */
    public int getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    /**
     * @param quantidadeEstoque the quantidadeEstoque to set
     */
    private void setQuantidadeEstoque(int quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
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
}
