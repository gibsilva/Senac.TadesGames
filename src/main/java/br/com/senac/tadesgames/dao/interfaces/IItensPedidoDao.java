/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.dao.interfaces;

import br.com.senac.tadesgames.models.ItensPedidoModel;
import java.util.List;

/**
 *
 * @author Gi
 */
public interface IItensPedidoDao {
    ItensPedidoModel obterPorId(int id);
    List<ItensPedidoModel> obterPorIdPedido(int id);
    List<ItensPedidoModel> obterTodas();
    void inserir(ItensPedidoModel itensPedido);
    
}
