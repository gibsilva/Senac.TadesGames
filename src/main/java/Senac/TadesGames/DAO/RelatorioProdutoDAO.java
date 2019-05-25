/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO;

import Senac.TadesGames.Data.ConexaoDB;
import Senac.TadesGames.Helpers.Utils;
import Senac.TadesGames.Models.RelatorioProdutoModel;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Gi
 */
public class RelatorioProdutoDAO {

    private final ConexaoDB conexao = new ConexaoDB();
    private PreparedStatement stmt = null;
    ResultSet rs = null;

    public List<RelatorioProdutoModel> obterPorData(Date dataInicio, Date dataFim) {
        Connection conn = conexao.getConnection();
        RelatorioProdutoModel relatorioProduto = null;
        Utils util = new Utils();
        List<RelatorioProdutoModel> lista = new ArrayList<RelatorioProdutoModel>();
        //ainda não foi testado de fato por não haver os dados na base das vendas
        try {
            stmt = conn.prepareStatement("SELECT   \n"
                    + "	produto.IDPRODUTO,\n"
                    + "	produto.NOME,\n"
                    + "	(SELECT SUM(I.QUANTIDADE) FROM itenspedido I WHERE I.IDPRODUTO = produto.IDPRODUTO) AS QTDPRODUTO,\n"
                    + "	SUM(itenspedido.VALORUNITARIO * itenspedido.QUANTIDADE) AS TOTALVENDIDO,\n"
                    + "	categoria.NOME AS CATEGORIA,\n"
                    + "	produto.ATIVO,\n"
                    + "	DATE_FORMAT((select i.datahoracriacao from itenspedido i where i.idproduto = produto.idproduto limit 1), '%d/%m/%Y') AS DataUltimaVenda,\n"
                    + "	DATE_FORMAT(produto.DATAHORACRIACAO, '%d/%m/%Y') AS DATAHORACRIACAO\n"
                    + "FROM\n"
                    + "    produto\n"
                    + "INNER JOIN itenspedido\n"
                    + "    ON itenspedido.IDPRODUTO = produto.IDPRODUTO\n"
                    + "INNER JOIN categoria\n"
                    + "    ON categoria.IDCATEGORIA = produto.IDCATEGORIA\n"
                    + "WHERE\n"
                    + "    DATE(produto.DATAHORACRIACAO) BETWEEN ? AND ?\n"
                    + "GROUP BY\n"
                    + "    produto.IDPRODUTO,\n"
                    + "    produto.DataHoraCriacao");

            stmt.setString(1, util.converteDateParaStr(dataInicio));
            stmt.setString(2, util.converteDateParaStr(dataFim));

            rs = stmt.executeQuery();
            while (rs.next()) {
                relatorioProduto = new RelatorioProdutoModel(
                        rs.getInt("IdProduto"),
                        rs.getString("nome"),
                        rs.getInt("qtdProduto"),
                        rs.getDouble("totalVendido"),
                        rs.getString("categoria"),
                        rs.getString("DataUltimaVenda"),
                        rs.getBoolean("ativo")
                );
                lista.add(relatorioProduto);
            }

            return lista;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }
}
