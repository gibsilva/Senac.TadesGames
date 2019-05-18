/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO;

import Senac.TadesGames.Data.ConexaoDB;
import Senac.TadesGames.Models.RelatorioClienteModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

/**
 *
 * @author Gi
 */
public class RelatorioClienteDAO {

    private final ConexaoDB conexao = new ConexaoDB();
    private PreparedStatement stmt = null;
    ResultSet rs = null;

    public RelatorioClienteModel obterPorData(Date dataInicio, Date dataFim) {
        Connection conn = conexao.getConnection();
        RelatorioClienteModel relatorioCliente = null;
        //ainda não foi testado de fato por não haver os dados na base das vendas
        try {
            stmt = conn.prepareStatement("SELECT \n"
                    + "	       CLIENTE.IDCLIENTE,\n"
                    + "        CLIENTE.NOME,\n"
                    + "        CLIENTE.CPF,\n"
                    + "        CLIENTE.CNPJ,\n"
                    + "        COUNT(PEDIDO.IDPEDIDO) AS QTDPEDIDOS,\n"
                    + "        (SELECT DATAPEDIDO FROM PEDIDO P WHERE P.IDCLIENTE = CLIENTE.IDCLIENTE ORDER BY P.DATAPEDIDO DESC LIMIT 1) AS DATAULTIMOPEDIDO,\n"
                    + "        SUM(ITENSPEDIDO.VALORUNITARIO * QUANTIDADE) AS TOTALCOMPRADO,\n"
                    + "        CLIENTE.ATIVO\n"
                    + "FROM\n"
                    + "     CLIENTE\n"
                    + "INNER JOIN PEDIDO\n"
                    + "     ON PEDIDO.IDCLIENTE = CLIENTE.IDCLIENTE\n"
                    + "INNER JOIN ITENSPEDIDO\n"
                    + "     ON ITENSPEDIDO.IDPEDIDO = PEDIDO.IDPEDIDO\n"
                    + "WHERE\n"
                    + "	CLIENTE.DATAHORACRIACAO BETWEEN ? AND ?");

            stmt.setDate(1, (Date) dataInicio);
            stmt.setDate(2, (Date) dataFim);

            rs = stmt.executeQuery();
            while (rs.next()) {
                relatorioCliente = new RelatorioClienteModel(
                        rs.getInt("IdCliente"),
                        rs.getString("Nome"),
                        rs.getString("cpf"),
                        rs.getString("cnpj"),
                        rs.getInt("qdtPedidos"),
                        rs.getDate("dataUltimoPedido"),
                        rs.getDouble("totalComprado"),
                        rs.getBoolean("ativo")
                );
            }

            return relatorioCliente;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

}
