package model;
public class Usuario {
    private int id;
    private String nome;
    private String senha;
    private String tipoDeUsuario;

    public Usuario() {}
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public String getTipoDeUsuario() { return tipoDeUsuario; }
    public void setTipoDeUsuario(String tipoDeUsuario) { this.tipoDeUsuario = tipoDeUsuario; }
    @Override public String toString() { return String.format("[%d] %s (%s)", id, nome, tipoDeUsuario==null?"":tipoDeUsuario); }
}
