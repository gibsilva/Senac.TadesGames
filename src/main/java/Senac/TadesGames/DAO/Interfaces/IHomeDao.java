/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO.Interfaces;

import Senac.TadesGames.Models.HomeProdutoModel;
import Senac.TadesGames.Models.HomeVendaModel;
import Senac.TadesGames.Models.HomeVendedorModel;

/**
 *
 * @author Gi
 */
public interface IHomeDao {

    HomeVendedorModel obterHomeVendedorPorIdFilial(int idFilial);

    HomeVendedorModel obterHomeVendedor();

    HomeProdutoModel obterHomeProduto();

    HomeProdutoModel obterHomeProduto(int idFilial);

    HomeVendaModel obterHomeVendas();

    HomeVendaModel obterHomeVendas(int idFilial);
}
