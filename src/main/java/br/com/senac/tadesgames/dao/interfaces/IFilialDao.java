/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.dao.interfaces;

import br.com.senac.tadesgames.models.FilialModel;
import java.util.List;

/**
 *
 * @author Gi
 */
public interface IFilialDao {
    FilialModel obterPorId(int id);
    List<FilialModel> obterTodas();
    List<FilialModel> obterTodasAtivas();
    void inserir(FilialModel filial);
    void alterar(FilialModel filial);
    FilialModel obterPorCnpj(String cnpj);
}
