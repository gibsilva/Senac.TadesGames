/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.tadesgames.helpers;

import java.util.Random;

/**
 * Classe para gerar senhas aleatórias, utilizada para resetar a senha de um
 * usuário
 *
 * @author Gi
 */
public class GeradorSenha {

    private static final char[] ALL_CHARS = new char[62];
    private static final Random RANDOM = new Random();

    public GeradorSenha() {
    }

    static {
        for (int i = 48, j = 0; i < 123; i++) {
            if (Character.isLetterOrDigit(i)) {
                ALL_CHARS[j] = (char) i;
                j++;
            }
        }
    }

    /**
     * @param length tamanho da senha gerada
     * @return retorna uma senha aleatória com letras e números
     */
    public static String getSenhaAleatoria(final int length) {
        final char[] result = new char[length];
        for (int i = 0; i < length; i++) {
            result[i] = ALL_CHARS[RANDOM.nextInt(ALL_CHARS.length)];
        }
        return new String(result);
    }

    //retorna uma senha com 8 caracteres
    public static String getRandomPassword() {
        return getSenhaAleatoria(8);
    }
}
