package Senac.TadesGames.Models;

/**
 *
 * @author Gi
 */
public class GraficoMelhoresVendedoresModel {
    private final String vendedor;
    private final int qtdVendas;
    private final String filial;
    
    public GraficoMelhoresVendedoresModel(String vendedor, int qtdVendas, String filial){
        this.vendedor = vendedor;
        this.qtdVendas = qtdVendas;
        this.filial = filial;
    }

    public String getVendedor() {
        return vendedor;
    }

    public int getQtdVendas() {
        return qtdVendas;
    }
    
    public String getFilial(){
        return this.filial;
    }
}
