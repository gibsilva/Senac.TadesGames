/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Helpers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Gi
 */
public class Utils {

    private static Date date;
    private static String dateStr;

    public static Date converteStrParaDate(String dataStr) throws ParseException {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        Date data = simpleDateFormat.parse(dataStr);
        return data;
    }

    public String converteDateParaStr(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String dataFormatada = df.format(date);
        return dataFormatada;
    }

    public static String converteDateParaStrBR(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        String dataFormatada = df.format(date);
        return dataFormatada;
    }

    public String removePontosBarraStr(String str) {
        String strFormatada = str.replaceAll("[./()-]", "");

        return strFormatada;
    }
    
    public String formatarValor(String valor){
        String valorFormatado = valor.replace(".", "");
        return valorFormatado.replace(",", ".");
    }

    public String removeEspacoBranco(String str) {
        return str.replace(" ", "");
    }

}
