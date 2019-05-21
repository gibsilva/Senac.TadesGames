/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO.Interfaces;

import Senac.TadesGames.Models.ClienteFisicoModel;
import Senac.TadesGames.Models.ClienteJuridicoModel;
import Senac.TadesGames.Models.ClienteModel;
import java.util.List;

/**
 *
 * @author Gi
 */
public interface IClienteDao {
    ClienteModel obterPorIdFisico(int id);
    ClienteModel obterPorIdJuridico(int id);
    List<ClienteModel> obterTodasFisico();
    List<ClienteModel> obterTodasJuridico();
    void inserirFisico(ClienteFisicoModel cliente);
    void inserirJuridico(ClienteJuridicoModel cliente);
    void alterar(ClienteModel cliente); 
    ClienteModel obterPorEmailFisico(String email);
    ClienteModel obterPorEmailJuridico(String email);
    ClienteModel obterPorCpf(String cpf); 
    ClienteModel obterPorCnpj(String cpf);
}
