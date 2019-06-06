/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.dao.interfaces;

import br.com.senac.tadesgames.models.PlataformaModel;
import java.util.List;

/**
 *
 * @author Gi
 */
public interface IPlataformaDao {
    
    PlataformaModel obterPorId(int id);
    List<PlataformaModel> obterTodas();
    void inserir(PlataformaModel plataforma);
    void alterar(PlataformaModel plataforma);
    void excluir(PlataformaModel plataforma);
    
}
