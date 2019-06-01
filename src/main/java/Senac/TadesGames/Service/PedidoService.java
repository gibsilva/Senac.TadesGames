package Senac.TadesGames.Service;

import Senac.TadesGames.DAO.ClienteDAO;
import Senac.TadesGames.DAO.ItensPedidoDAO;
import Senac.TadesGames.DAO.PedidoDAO;
import Senac.TadesGames.DAO.ProdutoDAO;
import Senac.TadesGames.DAO.UsuarioDAO;
import Senac.TadesGames.Helpers.Notificacao;
import Senac.TadesGames.Models.ClienteModel;
import Senac.TadesGames.Models.ItensPedidoModel;
import Senac.TadesGames.Models.PedidoModel;
import Senac.TadesGames.Models.ProdutoModel;
import Senac.TadesGames.Models.UsuarioModel;
import java.util.List;

/**
 *
 * @author Gi
 */
public class PedidoService {

    private final Notificacao notificacao;
    private final PedidoDAO pedidoDao;
    private final ProdutoDAO produtoDao;
    private final ItensPedidoDAO itensPedidoDao;
    private final UsuarioDAO usuarioDao;
    private final ClienteDAO clienteDao;

    public PedidoService() {
        this.notificacao = new Notificacao();
        this.pedidoDao = new PedidoDAO();
        this.clienteDao = new ClienteDAO();
        this.produtoDao = new ProdutoDAO();
        this.itensPedidoDao = new ItensPedidoDAO();
        this.usuarioDao = new UsuarioDAO();
    }

    private void validarEstoqueDisponivel(List<ItensPedidoModel> itensPedido) {
        for (ItensPedidoModel item : itensPedido) {
            ProdutoModel p = produtoDao.obterPorId(item.getIdProduto());
            if (item.getQuantidade() > p.getQuantidadeEstoque()) {
                this.notificacao.adicionaNotificacao("Produto", "Estoque indisponível para o produto "
                        + p.getNome() + ", quantidade dispónivel: " + p.getQuantidadeEstoque());
            }
        }
    }

    private void validarQuantidadeProduto(List<ItensPedidoModel> itensPedido) {
        for (ItensPedidoModel item : itensPedido) {
            if (item.getQuantidade() <= 0) {
                this.notificacao.adicionaNotificacao("Quantidade", "Quantidade do produto não pode ser negativa ou igual a zero");
            }
        }
    }

    private void validarCliente(PedidoModel pedido) {
        ClienteModel cliente = clienteDao.obterPorId(pedido.getIdCliente());
        if (cliente == null) {
            this.notificacao.adicionaNotificacao("Cliente", "Cliente inválido para salvar o pedido");
        }
    }

    private void validarVendedor(PedidoModel pedido) {
        UsuarioModel usuario = usuarioDao.obterPorId(pedido.getIdUsuario());
        if (usuario == null) {
            this.notificacao.adicionaNotificacao("Usuario", "Usuário inválido para salvar o pedido");
        } else if (!usuario.getCargo().equals("Vendedor (a)")) {
            this.notificacao.adicionaNotificacao("Usuario", "Não é possível salvar o pedido devido a usuário não ser um vendedor");
        }
    }

    private void validarValorTotal(PedidoModel pedido) {
        if (pedido.getValorTotal() <= 0) {
            this.notificacao.adicionaNotificacao("Pedido", "O valor total do pedido é menor ou igual a zero");
        }
    }

    private boolean validarPedido(PedidoModel pedido) {
        validarCliente(pedido);
        validarEstoqueDisponivel(pedido.getItensPedido());
        validarQuantidadeProduto(pedido.getItensPedido());
        validarValorTotal(pedido);
        validarVendedor(pedido);

        return this.notificacao.quantidadeNotificacoes() == 0;
    }

    public List<Notificacao> incluirPedido(PedidoModel pedido) throws Exception {
        try {
            if (validarPedido(pedido)) {
                //salva os dados do pedido
                pedidoDao.inserir(pedido);
                for (ItensPedidoModel item : pedido.getItensPedido()) {
                    //recupera o id e set nos itens
                    item.setIdPedido(ultimoIdInserido());
                    itensPedidoDao.inserir(item);
                    //da baixa no estoque
                    atualizaEstoque(item);
                }
            }
            return this.notificacao.listaNotificacoes();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private int ultimoIdInserido() {
        return this.pedidoDao.ultimoIdInserido();
    }

    private void atualizaEstoque(ItensPedidoModel item) throws Exception {
        try {
            ProdutoModel produto = produtoDao.obterPorId(item.getIdProduto());
            this.produtoDao.atualizarEstoque(produto, produto.getQuantidadeEstoque() - item.getQuantidade());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public void cancelarPedido(PedidoModel pedido) throws Exception {
        try {
            for (ItensPedidoModel item : pedido.getItensPedido()) {
                ProdutoModel produto = produtoDao.obterPorId(item.getIdProduto());
                this.produtoDao.atualizarEstoque(produto, produto.getQuantidadeEstoque() + item.getQuantidade());
            }
            pedidoDao.CancelarPedido(pedido);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public List<PedidoModel> obterTodos() {
        return this.pedidoDao.obterTodos();
    }

    public List<PedidoModel> obterTodosPorIdFilial(int idFilial) {
        return this.pedidoDao.obterTodosPorIdFilial(idFilial);
    }

    public List<PedidoModel> obterTodosConcluidos() {
        return this.pedidoDao.obterTodosConcluidos();
    }

    public List<PedidoModel> pesquisarPedidos(int id, String dataInicio, String dataFim) {
        return this.pedidoDao.pesquisar(id, dataInicio, dataFim);
    }

    public List<PedidoModel> pesquisarPedidos(int id, String dataInicio, String dataFim, int idFilial) {
        return this.pedidoDao.pesquisar(id, dataInicio, dataFim, idFilial);
    }

    public PedidoModel obterPorId(int id) {
        return this.pedidoDao.obterPorId(id);
    }

    public void limparNotificacoes() {
        this.notificacao.limparLista();
    }

}
