/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.DAO;

import Senac.TadesGames.DAO.Interfaces.IRelatorioClienteDao;
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
 * @author Gi
 */
public class RelatorioClienteDAO implements IRelatorioClienteDao {

    private final ConexaoDB conexao = new ConexaoDB();
    private PreparedStatement stmt = null;
    ResultSet rs = null;

    @Override
    public List<RelatorioClienteModel> obterPorData(Date dataInicio, Date dataFim) {
        Connection conn = conexao.getConnection();
        RelatorioClienteModel relatorioCliente = null;
        List<RelatorioClienteModel> lista = new ArrayList<RelatorioClienteModel>();
        Utils util = new Utils();

        try {
            //devido ao banco de dados no google cloud utilizar o mysql 5.7 desativar o modo only_full_group_by
            stmt = conn.prepareStatement("SET @@SESSION.sql_mode =(SELECT REPLACE(@@SESSION.sql_mode, 'ONLY_FULL_GROUP_BY', ''));");
            stmt.executeUpdate();

            stmt = conn.prepareStatement("SELECT\n"
                    + "	cliente.IDCLIENTE,\n"
                    + "	cliente.NOME,\n"
                    + "	cliente.CPF,\n"
                    + "	cliente.CNPJ,\n"
                    + "	(select count(1) from pedido p where p.idcliente = cliente.idcliente) AS QTDPEDIDOS,\n"
                    + "	DATE_FORMAT(pedido.DATAHORACRIACAO, '%d/%m/%Y') AS DATAULTIMOPEDIDO,\n"
                    + "	SUM(itenspedido.VALORUNITARIO * QUANTIDADE) AS TOTALCOMPRADO,\n"
                    + "	cliente.ATIVO\n"
                    + "FROM cliente \n"
                    + "INNER JOIN pedido \n"
                    + "	ON pedido.IDCLIENTE = cliente.IDCLIENTE \n"
                    + "INNER JOIN itenspedido\n"
                    + "	ON itenspedido.IDPEDIDO = pedido.IDPEDIDO \n"
                    + "WHERE\n"
                    + "	DATE(cliente.DATAHORACRIACAO) BETWEEN ? AND ?\n"
                    + " and pedido.statuspedido = 1\n"
                    + "GROUP BY \n"
                    + "	cliente.IDCLIENTE");

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
        } finally {
            conexao.closeConnection(conn, stmt, rs);
        }
    }

}
