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
public class ClienteFisicoModel extends ClienteModel{
    
    private String cpf;

    public ClienteFisicoModel(int idCliente, String nome,String cpf, Date dataNasc, String email, String telefone, String celular, String sexo, boolean ativo) {
        super(idCliente, nome, dataNasc, email, telefone, celular, sexo, ativo);
        
        this.cpf = cpf;
    }


    

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
