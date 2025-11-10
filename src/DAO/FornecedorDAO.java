package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Fornecedor;

public class FornecedorDAO {
    public void inserir(Fornecedor f) throws SQLException {
        String sql = "INSERT INTO Fornecedor (nome) VALUES (?)";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, f.getNome());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) f.setId(rs.getInt(1)); }
        }
    }

    public void atualizar(Fornecedor f) throws SQLException {
        String sql = "UPDATE Fornecedor SET nome=? WHERE id_fornecedor=?";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, f.getNome());
            ps.setInt(2, f.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM Fornecedor WHERE id_fornecedor=?";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) { ps.setInt(1, id); ps.executeUpdate(); }
    }

    public Fornecedor buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Fornecedor WHERE id_fornecedor=?";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Fornecedor f = new Fornecedor();
                    f.setId(rs.getInt("id_fornecedor"));
                    f.setNome(rs.getString("nome"));
                    return f;
                }
            }
        }
        return null;
    }

    public List<Fornecedor> listarTodos() throws SQLException {
        List<Fornecedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM Fornecedor ORDER BY nome";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Fornecedor f = new Fornecedor();
                f.setId(rs.getInt("id_fornecedor"));
                f.setNome(rs.getString("nome"));
                lista.add(f);
            }
        }
        return lista;
    }
}
