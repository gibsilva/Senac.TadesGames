/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Service;

import Senac.TadesGames.DAO.ProdutoDAO;
import Senac.TadesGames.Helpers.Notificacao;
import Senac.TadesGames.Models.ProdutoModel;
import java.util.List;

/**
 *
 * @author Gi
 */
public class ProdutoService {

    private final ProdutoDAO produtoDao;
    private final Notificacao notificacao;

    public ProdutoService() {
        this.notificacao = new Notificacao();
        this.produtoDao = new ProdutoDAO();
    }

    private void validarQuantidade(int quantidade) {
        if (quantidade < 0) {
            this.notificacao.adicionaNotificacao("Quantidade", "Não é possível inserir produto com quantidade negativa");
        }
    }

    private void validarValores(double valorCompra, double valorVenda) {
        if (valorCompra < 0 || valorVenda < 0) {
            this.notificacao.adicionaNotificacao("Valores", "Valor de compra ou venda inválido");
        }
    }

    private boolean validarProduto(ProdutoModel produto) {
        validarQuantidade(produto.getQuantidadeEstoque());
        validarValores(produto.getPrecoCompra(), produto.getPrecoVenda());

        return this.notificacao.quantidadeNotificacoes() == 0;
    }

    public List<Notificacao> incluirOuAlterarProduto(ProdutoModel produto) throws Exception {
        try {
            if (validarProduto(produto)) {
                if (produto.getIdProduto() == 0) {
                    produtoDao.inserir(produto);
                } else {
                    produtoDao.alterar(produto);
                }
            }
            return this.notificacao.listaNotificacoes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public ProdutoModel obterPorId(int id) {
        return this.produtoDao.obterPorId(id);
    }

    public ProdutoModel obterPorId(int idProduto, int idFilial) {
        return this.produtoDao.obterPorId(idProduto, idFilial);
    }

    public List<ProdutoModel> obterTodos() {
        return this.produtoDao.obterTodas();
    }

    public List<ProdutoModel> obterTodosPorIdFilial(int idFilial) {
        return this.produtoDao.obterPorIdFilial(idFilial);
    }

    public void limparNotificacoes() {
        this.notificacao.limparLista();
    }
}
