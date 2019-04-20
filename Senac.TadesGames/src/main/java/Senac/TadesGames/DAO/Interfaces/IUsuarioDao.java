/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO.Interfaces;

import Senac.TadesGames.Models.UsuarioModel;
import java.util.List;

/**
 *
 * @author Gi
 */
public interface IUsuarioDao {
    UsuarioModel obterPorId(int id);
    List<UsuarioModel> obterTodas();
    void inserir(UsuarioModel usuario);
    void alterar(UsuarioModel usuario);
    void inativar(UsuarioModel usuario);
    UsuarioModel autenticar (String nomeUsuario, String senha);
    
}
