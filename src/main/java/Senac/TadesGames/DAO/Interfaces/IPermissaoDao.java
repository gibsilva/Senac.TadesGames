/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO.Interfaces;

import Senac.TadesGames.Models.PermissaoModel;
import java.util.List;

/**
 *
 * @author Gi
 */
public interface IPermissaoDao {
    PermissaoModel obterPorId(int id);
    PermissaoModel obterPorIdUsuario(int id);
    List<PermissaoModel> obterTodas();
    void inserir(PermissaoModel cliente);
    void alterar(PermissaoModel cliente);
}
