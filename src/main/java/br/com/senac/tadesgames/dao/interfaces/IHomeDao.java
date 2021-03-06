/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.dao.interfaces;

import br.com.senac.tadesgames.models.HomeProdutoModel;
import br.com.senac.tadesgames.models.HomeVendaModel;
import br.com.senac.tadesgames.models.HomeVendedorModel;

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
