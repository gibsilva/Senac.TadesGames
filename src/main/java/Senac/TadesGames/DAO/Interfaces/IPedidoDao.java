/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO.Interfaces;

import Senac.TadesGames.Models.PedidoModel;
import java.util.List;

/**
 *
 * @author Gi
 */
public interface IPedidoDao {
    PedidoModel obterPorId(int id);
    List<PedidoModel> obterTodos();
    List<PedidoModel> obterTodosPorIdFilial(int idFilial);
    void inserir(PedidoModel pedido);
    void troca(PedidoModel pedido);
    void FinalizarPedido (PedidoModel pedido);
    void CancelarPedido (PedidoModel pedido);
    List<PedidoModel> pesquisar(int id, String dataInicio, String dataFim);
    List<PedidoModel> pesquisar(int id, String dataInicio, String dataFim, int idFilial);
    List<PedidoModel> obterTodosPorIdUsuario(int id);
    List<PedidoModel> obterTodosConcluidos();
    
}
