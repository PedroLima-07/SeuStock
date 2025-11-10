package model;
public class Item {
    private int id;
    private String nome;
    private String descricao;
    private String categoria;
    private String unidadeDeMedida;

    public Item() {}

    public Item(int id, String nome, String descricao, String categoria, String unidadeDeMedida) {
        this.id = id; this.nome = nome; this.descricao = descricao;
        this.categoria = categoria; this.unidadeDeMedida = unidadeDeMedida;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public String getUnidadeDeMedida() { return unidadeDeMedida; }
    public void setUnidadeDeMedida(String unidadeDeMedida) { this.unidadeDeMedida = unidadeDeMedida; }

    @Override
    public String toString() {
        return String.format("[%d] %s - %s (categoria: %s, unidade: %s)", id, nome, descricao==null?"":descricao, categoria==null?"":categoria, unidadeDeMedida==null?"":unidadeDeMedida);
    }
}

