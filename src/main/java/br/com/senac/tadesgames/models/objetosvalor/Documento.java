/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.models.objetosvalor;

/**
 * um objeto de valor que compõe classes que irão possuir um atributo documento
 * podendo ser um CNPJ ou CPF
 * @author Marcel
 */
public class Documento {
    private final String cpf;
    private final String cnpj;
    
    public Documento(String valor){
        if(valor.length() == 11){
            this.cpf = valor;
            this.cnpj = "";
        }else{
            this.cnpj = valor;
            this.cpf = "";
        }
    }
    
    public String getCnpj(){
        return this.cnpj;
    }
    
    public String getCpf(){
        return this.cpf;
    }
    
    public String getDocumento(){
        if(this.cpf.equals(""))
            return this.cnpj;
        else
            return this.cpf;
    }
    
    @Override
    public String toString(){
        return this.getDocumento();
    }
}
