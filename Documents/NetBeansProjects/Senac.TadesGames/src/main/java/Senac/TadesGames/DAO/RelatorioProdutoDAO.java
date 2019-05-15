/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO;

import Senac.TadesGames.Data.ConexaoDB;
import Senac.TadesGames.Models.RelatorioProdutoModel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Gi
 */
public class RelatorioProdutoDAO {

    private final ConexaoDB conexao = new ConexaoDB();
    private PreparedStatement stmt = null;
    ResultSet rs = null;

    public RelatorioProdutoModel obterPorData(Date dataInicio, Date dataFim) {
        Connection conn = conexao.getConnection();
        RelatorioProdutoModel relatorioProduto = null;
        //ainda não foi testado de fato por não haver os dados na base das vendas
        try {
            stmt = conn.prepareStatement(""
                    + "SELECT\n"
                    + "	   PRODUTO.IDPRODUTO,\n"
                    + "    PRODUTO.NOME,\n"
                    + "    COUNT(ITENSPEDIDO.IDPRODUTO) AS QTDPRODUTO,\n"
                    + "    SUM(ITENSPEDIDO.VALORUNITARIO * QUANTIDADE) AS TOTALVENDIDO,\n"
                    + "    CATEGORIA.NOME,\n"
                    + "    (SELECT ITENSPEDIDO.IDPRODUTO ORDER BY DATAHORACRIACAO DESC LIMIT 1) AS DATAULTIMAVENDA,\n"
                    + "    PRODUTO.ATIVO\n"
                    + "FROM "
                    + "     PRODUTO\n"
                    + "INNER JOIN ITENSPEDIDO\n"
                    + "     ON ITENSPEDIDO.IDPRODUTO = PRODUTO.IDPRODUTO\n"
                    + "INNER JOIN CATEGORIA\n"
                    + "     ON CATEGORIA.IDCATEGORIA = PRODUTO.IDCATEGORIA");

            stmt.setDate(1, (Date) dataInicio);
            stmt.setDate(2, (Date) dataFim);

            rs = stmt.executeQuery();
            while (rs.next()) {
                relatorioProduto = new RelatorioProdutoModel(
                        rs.getInt("IdProduto"),
                        rs.getString("nome"),
                        rs.getInt("qtdProduto"),
                        rs.getDouble("totalVendido"),
                        rs.getString("categoria"),
                        rs.getDate("DataUltimoVenda"),
                        rs.getBoolean("ativo")
                );
            }

            return relatorioProduto;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }
}
