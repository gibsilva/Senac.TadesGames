/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO;

import Senac.TadesGames.Data.ConexaoDB;
import Senac.TadesGames.Helpers.Utils;
import Senac.TadesGames.Models.RelatorioClienteModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Giovanni.Carignato
 */
public class RelatorioClienteDAO {

    private final ConexaoDB conexao = new ConexaoDB();
    private PreparedStatement stmt = null;
    ResultSet rs = null;

    public List<RelatorioClienteModel> obterPorData(Date dataInicio, Date dataFim) {
        Connection conn = conexao.getConnection();
        RelatorioClienteModel relatorioCliente = null;
        List<RelatorioClienteModel> lista = new ArrayList<RelatorioClienteModel>();
        Utils util = new Utils();
        //ainda não foi testado de fato por não haver os dados na base das vendas
        try {
            stmt = conn.prepareStatement("SELECT\n"
                    + "	CLIENTE.IDCLIENTE,\n"
                    + "	CLIENTE.NOME,\n"
                    + "	CLIENTE.CPF,\n"
                    + "	CLIENTE.CNPJ,\n"
                    + "	(select count(1) from pedido p where p.idcliente = cliente.idcliente) AS QTDPEDIDOS,\n"
                    + "	DATE_FORMAT(PEDIDO.DATAHORACRIACAO, '%d/%m/%Y') AS DATAULTIMOPEDIDO,\n"
                    + "	SUM(ITENSPEDIDO.VALORUNITARIO * QUANTIDADE) AS TOTALCOMPRADO,\n"
                    + "	CLIENTE.ATIVO\n"
                    + "FROM CLIENTE \n"
                    + "INNER JOIN PEDIDO \n"
                    + "	ON PEDIDO.IDCLIENTE = CLIENTE.IDCLIENTE \n"
                    + "INNER JOIN ITENSPEDIDO\n"
                    + "	ON ITENSPEDIDO.IDPEDIDO = PEDIDO.IDPEDIDO \n"
                    + "WHERE\n"
                    + "	DATE(CLIENTE.DATAHORACRIACAO) BETWEEN ? AND ?\n"
                    + "GROUP BY \n"
                    + "	CLIENTE.IDCLIENTE");

            stmt.setString(1, util.converteDateParaStr(dataInicio));
            stmt.setString(2, util.converteDateParaStr(dataFim));

            rs = stmt.executeQuery();
            while (rs.next()) {
                relatorioCliente = new RelatorioClienteModel(
                        rs.getInt("IdCliente"),
                        rs.getString("Nome"),
                        rs.getString("cpf"),
                        rs.getString("cnpj"),
                        rs.getInt("qtdPedidos"),
                        rs.getString("dataUltimoPedido"),
                        rs.getDouble("totalComprado"),
                        rs.getBoolean("ativo")
                );
                lista.add(relatorioCliente);
            }

            return lista;
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt, rs);
            return null;
        }
    }

}
