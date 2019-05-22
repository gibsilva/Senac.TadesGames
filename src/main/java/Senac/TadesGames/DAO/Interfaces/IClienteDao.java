/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO.Interfaces;

import Senac.TadesGames.Models.ClienteModel;
import java.util.List;

/**
 *
 * @author Gi
 */
public interface IClienteDao {
    ClienteModel obterPorId(int id);
    List<ClienteModel> obterTodas();
    void inserir(ClienteModel cliente);
    void alterar(ClienteModel cliente); 
    ClienteModel obterPorEmail(String email);
    ClienteModel obterPorDocumento(String documento); 
    
}
