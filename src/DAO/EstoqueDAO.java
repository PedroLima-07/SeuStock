package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Estoque;

public class EstoqueDAO {
    public void inserir(Estoque e) throws SQLException {
        String sql = "INSERT INTO Estoque (id_item, id_localizacao, id_controle_movimentacao, quantidade_atual, estoque_minimo, estoque_maximo) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, e.getIdItem());
            ps.setInt(2, e.getIdLocalizacao());
            if (e.getIdControleMovimentacao() == null) ps.setNull(3, Types.INTEGER);
            else ps.setInt(3, e.getIdControleMovimentacao());
            ps.setInt(4, e.getQuantidadeAtual());
            ps.setInt(5, e.getEstoqueMinimo());
            ps.setInt(6, e.getEstoqueMaximo());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) e.setId(rs.getInt(1)); }
        }
    }

    public void atualizar(Estoque e) throws SQLException {
        String sql = "UPDATE Estoque SET id_item=?, id_localizacao=?, id_controle_movimentacao=?, quantidade_atual=?, estoque_minimo=?, estoque_maximo=? WHERE id_estoque=?";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, e.getIdItem());
            ps.setInt(2, e.getIdLocalizacao());
            if (e.getIdControleMovimentacao() == null) ps.setNull(3, Types.INTEGER);
            else ps.setInt(3, e.getIdControleMovimentacao());
            ps.setInt(4, e.getQuantidadeAtual());
            ps.setInt(5, e.getEstoqueMinimo());
            ps.setInt(6, e.getEstoqueMaximo());
            ps.setInt(7, e.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM Estoque WHERE id_estoque=?";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) { ps.setInt(1, id); ps.executeUpdate(); }
    }

    public Estoque buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Estoque WHERE id_estoque=?";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Estoque e = new Estoque();
                    e.setId(rs.getInt("id_estoque"));
                    e.setIdItem(rs.getInt("id_item"));
                    e.setIdLocalizacao(rs.getInt("id_localizacao"));
                    int ctrl = rs.getInt("id_controle_movimentacao");
                    if (rs.wasNull()) e.setIdControleMovimentacao(null); else e.setIdControleMovimentacao(ctrl);
                    e.setQuantidadeAtual(rs.getInt("quantidade_atual"));
                    e.setEstoqueMinimo(rs.getInt("estoque_minimo"));
                    e.setEstoqueMaximo(rs.getInt("estoque_maximo"));
                    return e;
                }
            }
        }
        return null;
    }

    public List<Estoque> listarTodos() throws SQLException {
        List<Estoque> lista = new ArrayList<>();
        String sql = "SELECT * FROM Estoque ORDER BY id_estoque";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Estoque e = new Estoque();
                e.setId(rs.getInt("id_estoque"));
                e.setIdItem(rs.getInt("id_item"));
                e.setIdLocalizacao(rs.getInt("id_localizacao"));
                int ctrl = rs.getInt("id_controle_movimentacao");
                if (rs.wasNull()) e.setIdControleMovimentacao(null); else e.setIdControleMovimentacao(ctrl);
                e.setQuantidadeAtual(rs.getInt("quantidade_atual"));
                e.setEstoqueMinimo(rs.getInt("estoque_minimo"));
                e.setEstoqueMaximo(rs.getInt("estoque_maximo"));
                lista.add(e);
            }
        }
        return lista;
    }

    public void ajustarQuantidade(int idEstoque, int novaQuantidade) throws SQLException {
        String sql = "UPDATE Estoque SET quantidade_atual=? WHERE id_estoque=?";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, novaQuantidade);
            ps.setInt(2, idEstoque);
            ps.executeUpdate();
        }
    }

    public List<Estoque> listarPorItem(int idItem) throws SQLException {
        List<Estoque> lista = new ArrayList<>();
        String sql = "SELECT * FROM Estoque WHERE id_item=? ORDER BY id_localizacao";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, idItem);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Estoque e = new Estoque();
                    e.setId(rs.getInt("id_estoque"));
                    e.setIdItem(rs.getInt("id_item"));
                    e.setIdLocalizacao(rs.getInt("id_localizacao"));
                    int ctrl = rs.getInt("id_controle_movimentacao");
                    if (rs.wasNull()) e.setIdControleMovimentacao(null); else e.setIdControleMovimentacao(ctrl);
                    e.setQuantidadeAtual(rs.getInt("quantidade_atual"));
                    e.setEstoqueMinimo(rs.getInt("estoque_minimo"));
                    e.setEstoqueMaximo(rs.getInt("estoque_maximo"));
                    lista.add(e);
                }
            }
        }
        return lista;
    }
}
