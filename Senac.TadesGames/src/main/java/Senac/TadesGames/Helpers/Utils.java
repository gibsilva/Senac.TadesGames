/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Helpers;
import Senac.TadesGames.Servlet.ClienteControllerServlet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gi
 */
public class Utils {
    private static Date date;
    private static String dateStr;

    public static Date converteStrParaDate(String dataStr) {
        DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        date = new Date();
        try {
            date = df.parse(dataStr);
        } catch (ParseException ex) {
            Logger.getLogger(ClienteControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

        return date;
    }

    public String converteDateParaStr(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dataFormatada = df.format(date);
        return dataFormatada;
    }

    public String removePontosBarraStr(String str) {
        String strFormatada = str.replaceAll("[./()-]", "");

        return strFormatada;
    }

    public String removeEspacoBranco(String str) {
        return str.replace(" ", "");
    }
    
    
}
