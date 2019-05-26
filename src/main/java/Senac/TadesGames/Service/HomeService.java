/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Service;

import Senac.TadesGames.DAO.HomeDAO;
import Senac.TadesGames.Models.HomeModel;

/**
 *
 * @author Gi
 */
public class HomeService {
    private final HomeDAO homeDao;
    
    public HomeService(){
        this.homeDao = new HomeDAO();
    }
    
    public HomeModel obterHomeVendedor(){
        return this.homeDao.obterHomeVendedor();
    }
}
