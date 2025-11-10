package model;
import java.sql.Date;

public class ControleMovimentacoes {
    private int id;
    private int idUsuario;
    private String tipoMovimentacao;
    private Date dataMovimentacao;
    private int quantidadeMovimentada;
    private String responsavel;

    public ControleMovimentacoes() {}
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public String getTipoMovimentacao() { return tipoMovimentacao; }
    public void setTipoMovimentacao(String tipoMovimentacao) { this.tipoMovimentacao = tipoMovimentacao; }
    public Date getDataMovimentacao() { return dataMovimentacao; }
    public void setDataMovimentacao(Date dataMovimentacao) { this.dataMovimentacao = dataMovimentacao; }
    public int getQuantidadeMovimentada() { return quantidadeMovimentada; }
    public void setQuantidadeMovimentada(int quantidadeMovimentada) { this.quantidadeMovimentada = quantidadeMovimentada; }
    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }

    @Override
    public String toString() {
        return String.format("[%d] tipo=%s data=%s qtd=%d resp=%s", id, tipoMovimentacao, dataMovimentacao, quantidadeMovimentada, responsavel==null?"":responsavel);
    }
}

