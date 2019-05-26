/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Service;

import Senac.TadesGames.DAO.ItensPedidoDAO;
import Senac.TadesGames.Models.ItensPedidoModel;
import java.util.List;

/**
 *
 * @author Gi
 */
public class ItensPedidoService {
    private final ItensPedidoDAO itensPedidoDao;
    
    public ItensPedidoService(){
        this.itensPedidoDao = new ItensPedidoDAO();
    }
    
    public List<ItensPedidoModel> obterTodos(){
        return this.itensPedidoDao.obterTodas();
    }
}
