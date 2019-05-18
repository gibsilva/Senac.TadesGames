/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Senac.TadesGames.Service;

import Senac.TadesGames.DAO.RelatorioProdutoDAO;
import Senac.TadesGames.Data.ConexaoDB;
import Senac.TadesGames.Models.RelatorioProdutoModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author caio_
 * 
 *  private final RelatorioProdutoDAO relatorioProduto;
public RelatorioProdutoService(){
    this.relatorioProduto = new RelatorioProdutoDAO();
}    
  
 */
public class RelatorioProdutoService  {
    
    private final DataSource dataSource;
    
    public RelatorioProdutoService(DataSource theDataSource){
        dataSource = theDataSource;
    }
    
     private PreparedStatement stmt = null;
    ConexaoDB conexao = new ConexaoDB();       
        Connection conn = conexao.getConnection();
        
    public List<RelatorioProdutoModel> criaRelatorio(Date dataInicio, Date dataFim) {
        
        List<RelatorioProdutoModel> listaRelatorio = new ArrayList<>();
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

            stmt.setDate(1, new java.sql.Date(dataInicio.getTime()));
            stmt.setDate(2, new java.sql.Date(dataFim.getTime()));

           ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                RelatorioProdutoModel relatorioProduto = new RelatorioProdutoModel();
                relatorioProduto.setIdProduto(rs.getInt(1));
                relatorioProduto.setNomeProduto(rs.getString(2));
                relatorioProduto.setQtdProduto(rs.getInt(3));
                relatorioProduto.setTotalVendido(rs.getDouble(4));
                relatorioProduto.setCategoria(rs.getString(5));
                relatorioProduto.setDataUltimaVenda(rs.getDate(6));
                relatorioProduto.setAtivo(rs.getBoolean(7));
                listaRelatorio.add(relatorioProduto);
            }
            return listaRelatorio;
            
        } catch (SQLException ex) {
            conexao.closeConnection(conn, stmt);
            return null;
        }
    }
    
}
