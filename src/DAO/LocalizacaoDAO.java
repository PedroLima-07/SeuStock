package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Localizacao;

public class LocalizacaoDAO {
    public void inserir(Localizacao l) throws SQLException {
        String sql = "INSERT INTO Localizacao (nome) VALUES (?)";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, l.getNome());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) l.setId(rs.getInt(1)); }
        }
    }

    public void atualizar(Localizacao l) throws SQLException {
        String sql = "UPDATE Localizacao SET nome=? WHERE id_localizacao=?";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, l.getNome());
            ps.setInt(2, l.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM Localizacao WHERE id_localizacao=?";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) { ps.setInt(1, id); ps.executeUpdate(); }
    }

    public Localizacao buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Localizacao WHERE id_localizacao=?";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Localizacao l = new Localizacao();
                    l.setId(rs.getInt("id_localizacao"));
                    l.setNome(rs.getString("nome"));
                    return l;
                }
            }
        }
        return null;
    }

    public List<Localizacao> listarTodos() throws SQLException {
        List<Localizacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM Localizacao ORDER BY nome";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Localizacao l = new Localizacao();
                l.setId(rs.getInt("id_localizacao"));
                l.setNome(rs.getString("nome"));
                lista.add(l);
            }
        }
        return lista;
    }
}
