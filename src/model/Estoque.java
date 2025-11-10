package model;
public class Estoque {
    private int id;
    private int idItem;
    private int idLocalizacao;
    private Integer idControleMovimentacao;
    private int quantidadeAtual;
    private int estoqueMinimo;
    private int estoqueMaximo;

    public Estoque() {}
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getIdItem() { return idItem; }
    public void setIdItem(int idItem) { this.idItem = idItem; }
    public int getIdLocalizacao() { return idLocalizacao; }
    public void setIdLocalizacao(int idLocalizacao) { this.idLocalizacao = idLocalizacao; }
    public Integer getIdControleMovimentacao() { return idControleMovimentacao; }
    public void setIdControleMovimentacao(Integer idControleMovimentacao) { this.idControleMovimentacao = idControleMovimentacao; }
    public int getQuantidadeAtual() { return quantidadeAtual; }
    public void setQuantidadeAtual(int quantidadeAtual) { this.quantidadeAtual = quantidadeAtual; }
    public int getEstoqueMinimo() { return estoqueMinimo; }
    public void setEstoqueMinimo(int estoqueMinimo) { this.estoqueMinimo = estoqueMinimo; }
    public int getEstoqueMaximo() { return estoqueMaximo; }
    public void setEstoqueMaximo(int estoqueMaximo) { this.estoqueMaximo = estoqueMaximo; }

    @Override
    public String toString() {
        return String.format("[%d] itemId=%d locId=%d qtd=%d min=%d max=%d", id, idItem, idLocalizacao, quantidadeAtual, estoqueMinimo, estoqueMaximo);
    }
}

