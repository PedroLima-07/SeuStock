package model;
import java.math.BigDecimal;
import java.sql.Date;

public class ItemFornecedor {
    private int id;
    private int idItem;
    private int idFornecedor;
    private String tipoFornecedor;
    private BigDecimal custoUnitario;
    private Date dataEntrega;
    private Date dataVigencia;

    public ItemFornecedor() {}
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdItem() { return idItem; }
    public void setIdItem(int idItem) { this.idItem = idItem; }
    public int getIdFornecedor() { return idFornecedor; }
    public void setIdFornecedor(int idFornecedor) { this.idFornecedor = idFornecedor; }
    public String getTipoFornecedor() { return tipoFornecedor; }
    public void setTipoFornecedor(String tipoFornecedor) { this.tipoFornecedor = tipoFornecedor; }
    public BigDecimal getCustoUnitario() { return custoUnitario; }
    public void setCustoUnitario(BigDecimal custoUnitario) { this.custoUnitario = custoUnitario; }
    public Date getDataEntrega() { return dataEntrega; }
    public void setDataEntrega(Date dataEntrega) { this.dataEntrega = dataEntrega; }
    public Date getDataVigencia() { return dataVigencia; }
    public void setDataVigencia(Date dataVigencia) { this.dataVigencia = dataVigencia; }

    @Override public String toString() { return String.format("[%d] item=%d fornecedor=%d custo=%s", id, idItem, idFornecedor, custoUnitario==null?"":custoUnitario.toString()); }
}
