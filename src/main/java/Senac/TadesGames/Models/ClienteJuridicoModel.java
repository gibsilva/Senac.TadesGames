/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Models;

import java.util.Date;

/**
 *
 * @author Marcel
 */
public class ClienteJuridicoModel extends ClienteModel {
    
    private String cnpj;

    public ClienteJuridicoModel(int idCliente, String nome, String cnpj, Date dataNasc, String email, String telefone, String celular, String sexo, boolean ativo) {
        super(idCliente, nome, dataNasc, email, telefone, celular, sexo, ativo);
        
        this.cnpj = cnpj;
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
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
