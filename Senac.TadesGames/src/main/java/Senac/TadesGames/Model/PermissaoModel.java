/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Model;

/**
 *
 * @author Gi
 */
public class PermissaoModel {

    private int idPermissao;
    private int idUsuario;
    private int relatorioVenda;
    private int relatorioProduto;
    private int relatorioCliente;
    private int cadastrarCliente;
    private int cadastrarProduto;
    private int realizarVenda;
    private int cadastrarFilial;
    private int cadastrarUsuario;

    public PermissaoModel(int idPermissao, int idUsuario, int relatorioVenda,
            int relatorioProduto, int relatorioCliente, int cadastrarCliente,
            int cadastrarProduto, int realizarVenda, int cadastrarFilial,
            int cadastrarUsuario) {
        
        this.idPermissao = idPermissao;
        this.idUsuario = idUsuario;
        this.relatorioVenda = relatorioVenda;
        this.relatorioProduto = relatorioProduto;
        this.relatorioCliente = relatorioCliente;
        this.cadastrarCliente = cadastrarCliente;
        this.cadastrarProduto = cadastrarProduto;
        this.realizarVenda = realizarVenda;
        this.cadastrarFilial = cadastrarFilial;
        this.cadastrarUsuario = cadastrarUsuario;
    }

    /**
     * @return the idPermissao
     */
    public int getIdPermissao() {
        return idPermissao;
    }

    /**
     * @param idPermissao the idPermissao to set
     */
    public void setIdPermissao(int idPermissao) {
        this.idPermissao = idPermissao;
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
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the relatorioVenda
     */
    public int getRelatorioVenda() {
        return relatorioVenda;
    }

    /**
     * @param relatorioVenda the relatorioVenda to set
     */
    public void setRelatorioVenda(int relatorioVenda) {
        this.relatorioVenda = relatorioVenda;
    }

    /**
     * @return the relatorioProduto
     */
    public int getRelatorioProduto() {
        return relatorioProduto;
    }

    /**
     * @param relatorioProduto the relatorioProduto to set
     */
    public void setRelatorioProduto(int relatorioProduto) {
        this.relatorioProduto = relatorioProduto;
    }

    /**
     * @return the relatorioCliente
     */
    public int getRelatorioCliente() {
        return relatorioCliente;
    }

    /**
     * @param relatorioCliente the relatorioCliente to set
     */
    public void setRelatorioCliente(int relatorioCliente) {
        this.relatorioCliente = relatorioCliente;
    }

    /**
     * @return the cadastrarCliente
     */
    public int getCadastrarCliente() {
        return cadastrarCliente;
    }

    /**
     * @param cadastrarCliente the cadastrarCliente to set
     */
    public void setCadastrarCliente(int cadastrarCliente) {
        this.cadastrarCliente = cadastrarCliente;
    }

    /**
     * @return the cadastrarProduto
     */
    public int getCadastrarProduto() {
        return cadastrarProduto;
    }

    /**
     * @param cadastrarProduto the cadastrarProduto to set
     */
    public void setCadastrarProduto(int cadastrarProduto) {
        this.cadastrarProduto = cadastrarProduto;
    }

    /**
     * @return the realizarVenda
     */
    public int getRealizarVenda() {
        return realizarVenda;
    }

    /**
     * @param realizarVenda the realizarVenda to set
     */
    public void setRealizarVenda(int realizarVenda) {
        this.realizarVenda = realizarVenda;
    }

    /**
     * @return the cadastrarFilial
     */
    public int getCadastrarFilial() {
        return cadastrarFilial;
    }

    /**
     * @param cadastrarFilial the cadastrarFilial to set
     */
    public void setCadastrarFilial(int cadastrarFilial) {
        this.cadastrarFilial = cadastrarFilial;
    }

    /**
     * @return the cadastrarUsuario
     */
    public int getCadastrarUsuario() {
        return cadastrarUsuario;
    }

    /**
     * @param cadastrarUsuario the cadastrarUsuario to set
     */
    public void setCadastrarUsuario(int cadastrarUsuario) {
        this.cadastrarUsuario = cadastrarUsuario;
    }
    

}
