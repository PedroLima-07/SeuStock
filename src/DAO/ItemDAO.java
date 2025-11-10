package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Item;

public class ItemDAO {

    public void inserir(Item it) throws SQLException {
        String sql = "INSERT INTO Item (nome, descricao, categoria, unidade_de_medida) VALUES (?, ?, ?, ?)";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, it.getNome());
            ps.setString(2, it.getDescricao());
            ps.setString(3, it.getCategoria());
            ps.setString(4, it.getUnidadeDeMedida());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) it.setId(rs.getInt(1));
            }
        }
    }

    public void atualizar(Item it) throws SQLException {
        String sql = "UPDATE Item SET nome=?, descricao=?, categoria=?, unidade_de_medida=? WHERE id_item=?";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setString(1, it.getNome());
            ps.setString(2, it.getDescricao());
            ps.setString(3, it.getCategoria());
            ps.setString(4, it.getUnidadeDeMedida());
            ps.setInt(5, it.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM Item WHERE id_item=?";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public Item buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Item WHERE id_item=?";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Item it = new Item();
                    it.setId(rs.getInt("id_item"));
                    it.setNome(rs.getString("nome"));
                    it.setDescricao(rs.getString("descricao"));
                    it.setCategoria(rs.getString("categoria"));
                    it.setUnidadeDeMedida(rs.getString("unidade_de_medida"));
                    return it;
                }
            }
        }
        return null;
    }

    public List<Item> listarTodos() throws SQLException {
        List<Item> lista = new ArrayList<>();
        String sql = "SELECT * FROM Item ORDER BY nome";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Item it = new Item();
                it.setId(rs.getInt("id_item"));
                it.setNome(rs.getString("nome"));
                it.setDescricao(rs.getString("descricao"));
                it.setCategoria(rs.getString("categoria"));
                it.setUnidadeDeMedida(rs.getString("unidade_de_medida"));
                lista.add(it);
            }
        }
        return lista;
    }
}

