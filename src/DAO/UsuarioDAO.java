package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class UsuarioDAO {
    public void inserir(Usuario u) throws SQLException {
        String sql = "INSERT INTO Usuario (nome, senha, tipo_de_usuario) VALUES (?, ?, ?)";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getSenha());
            ps.setString(3, u.getTipoDeUsuario());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) u.setId(rs.getInt(1)); }
        }
    }

    public void atualizar(Usuario u) throws SQLException {
        String sql = "UPDATE Usuario SET nome=?, senha=?, tipo_de_usuario=? WHERE id_usuario=?";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getSenha());
            ps.setString(3, u.getTipoDeUsuario());
            ps.setInt(4, u.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM Usuario WHERE id_usuario=?";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) { ps.setInt(1, id); ps.executeUpdate(); }
    }

    public Usuario buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Usuario WHERE id_usuario=?";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Usuario u = new Usuario();
                    u.setId(rs.getInt("id_usuario"));
                    u.setNome(rs.getString("nome"));
                    u.setSenha(rs.getString("senha"));
                    u.setTipoDeUsuario(rs.getString("tipo_de_usuario"));
                    return u;
                }
            }
        }
        return null;
    }

    public List<Usuario> listarTodos() throws SQLException {
        List<Usuario> lista = new ArrayList<>();
        String sql = "SELECT * FROM Usuario ORDER BY nome";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id_usuario"));
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                u.setTipoDeUsuario(rs.getString("tipo_de_usuario"));
                lista.add(u);
            }
        }
        return lista;
    }
}

