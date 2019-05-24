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
 * @author Giovanni.Carignato
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
                    + "	PRODUTO.IDPRODUTO,\n"
                    + "	PRODUTO.NOME,\n"
                    + "	(SELECT SUM(QUANTIDADE) FROM ITENSPEDIDO I WHERE I.IDPRODUTO = PRODUTO.IDPRODUTO) AS QTDPRODUTO,\n"
                    + "	SUM(ITENSPEDIDO.VALORUNITARIO * QUANTIDADE) AS TOTALVENDIDO,\n"
                    + "	CATEGORIA.NOME AS CATEGORIA,\n"
                    + "	DATE_FORMAT((SELECT DATE(ITENSPEDIDO.DATAHORACRIACAO) ORDER BY ITENSPEDIDO.DATAHORACRIACAO DESC LIMIT 1), '%d/%m/%Y') AS DATAULTIMAVENDA,\n"
                    + "	PRODUTO.ATIVO,\n"
                    + "	DATE(PRODUTO.DATAHORACRIACAO) AS DATAHORACRIACAO\n"
                    + "FROM\n"
                    + "	PRODUTO\n"
                    + "INNER JOIN ITENSPEDIDO\n"
                    + "	ON ITENSPEDIDO.IDPRODUTO = PRODUTO.IDPRODUTO\n"
                    + "INNER JOIN CATEGORIA\n"
                    + "	ON CATEGORIA.IDCATEGORIA = PRODUTO.IDCATEGORIA\n"
                    + "WHERE\n"
                    + "	DATE(PRODUTO.DATAHORACRIACAO) BETWEEN ? AND ?\n"
                    + "GROUP BY\n"
                    + "	PRODUTO.IDPRODUTO,\n"
                    + "    PRODUTO.NOME");

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
