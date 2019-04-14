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
    private boolean relatorioVenda;
    private boolean relatorioProduto;
    private boolean relatorioCliente;
    private boolean cadastrarCliente;
    private boolean cadastrarProduto;
    private boolean realizarVenda;
    private boolean cadastrarFilial;
    private boolean cadastrarUsuario;

    public PermissaoModel(int idPermissao, int idUsuario, boolean relatorioVenda,
            boolean relatorioProduto, boolean relatorioCliente, boolean cadastrarCliente,
            boolean cadastrarProduto, boolean realizarVenda, boolean cadastrarFilial,
            boolean cadastrarUsuario) {
        
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
    public boolean isRelatorioVenda() {
        return relatorioVenda;
    }

    /**
     * @param relatorioVenda the relatorioVenda to set
     */
    public void setRelatorioVenda(boolean relatorioVenda) {
        this.relatorioVenda = relatorioVenda;
    }

    /**
     * @return the relatorioProduto
     */
    public boolean isRelatorioProduto() {
        return relatorioProduto;
    }

    /**
     * @param relatorioProduto the relatorioProduto to set
     */
    public void setRelatorioProduto(boolean relatorioProduto) {
        this.relatorioProduto = relatorioProduto;
    }

    /**
     * @return the relatorioCliente
     */
    public boolean isRelatorioCliente() {
        return relatorioCliente;
    }

    /**
     * @param relatorioCliente the relatorioCliente to set
     */
    public void setRelatorioCliente(boolean relatorioCliente) {
        this.relatorioCliente = relatorioCliente;
    }

    /**
     * @return the cadastrarCliente
     */
    public boolean isCadastrarCliente() {
        return cadastrarCliente;
    }

    /**
     * @param cadastrarCliente the cadastrarCliente to set
     */
    public void setCadastrarCliente(boolean cadastrarCliente) {
        this.cadastrarCliente = cadastrarCliente;
    }

    /**
     * @return the cadastrarProduto
     */
    public boolean isCadastrarProduto() {
        return cadastrarProduto;
    }

    /**
     * @param cadastrarProduto the cadastrarProduto to set
     */
    public void setCadastrarProduto(boolean cadastrarProduto) {
        this.cadastrarProduto = cadastrarProduto;
    }

    /**
     * @return the realizarVenda
     */
    public boolean isRealizarVenda() {
        return realizarVenda;
    }

    /**
     * @param realizarVenda the realizarVenda to set
     */
    public void setRealizarVenda(boolean realizarVenda) {
        this.realizarVenda = realizarVenda;
    }

    /**
     * @return the cadastrarFilial
     */
    public boolean isCadastrarFilial() {
        return cadastrarFilial;
    }

    /**
     * @param cadastrarFilial the cadastrarFilial to set
     */
    public void setCadastrarFilial(boolean cadastrarFilial) {
        this.cadastrarFilial = cadastrarFilial;
    }

    /**
     * @return the cadastrarUsuario
     */
    public boolean isCadastrarUsuario() {
        return cadastrarUsuario;
    }

    /**
     * @param cadastrarUsuario the cadastrarUsuario to set
     */
    public void setCadastrarUsuario(boolean cadastrarUsuario) {
        this.cadastrarUsuario = cadastrarUsuario;
    }
    

}
