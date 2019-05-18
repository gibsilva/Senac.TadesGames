/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Helpers;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gi
 */
public class Notificacao {

    private String chave;
    private String valor;
    private List<Notificacao> notificacoes;

    public Notificacao() {
        this.notificacoes = new ArrayList<>();
    }

    public Notificacao(String chave, String valor) {
        this.chave = chave;
        this.valor = valor;
        this.notificacoes = new ArrayList<>();
    }

    public void adicionaNotificacao(String chave, String valor) {
        Notificacao notificacao = new Notificacao(chave, valor);
        this.notificacoes.add(notificacao);
    }

    public void limparLista() {
        if (!notificacoes.isEmpty()) {
            for (Notificacao n : this.notificacoes) {
                this.notificacoes.remove(n);
            }
        }
    }

    public String getValor() {
        return this.valor;
    }

    public String getChave() {
        return this.chave;
    }

    public int quantidadeNotificacoes() {
        return this.notificacoes.size();
    }

    public List<Notificacao> listaNotificacoes() {
        return this.notificacoes;
    }
}
