package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.ControleMovimentacoes;

public class ControleMovimentacoesDAO {
    public void inserir(ControleMovimentacoes m) throws SQLException {
        String sql = "INSERT INTO ControleMovimentacoes (id_usuario, tipo_movimentacao, data_movimentacao, quantidade_movimentada, responsavel_pela_retirada) VALUES (?, ?, ?, ?, ?)";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, m.getIdUsuario());
            ps.setString(2, m.getTipoMovimentacao());
            ps.setDate(3, m.getDataMovimentacao());
            ps.setInt(4, m.getQuantidadeMovimentada());
            ps.setString(5, m.getResponsavel());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) m.setId(rs.getInt(1)); }
        }
    }

    public ControleMovimentacoes buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM ControleMovimentacoes WHERE id_controle_movimentacoes=?";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ControleMovimentacoes m = new ControleMovimentacoes();
                    m.setId(rs.getInt("id_controle_movimentacoes"));
                    m.setIdUsuario(rs.getInt("id_usuario"));
                    m.setTipoMovimentacao(rs.getString("tipo_movimentacao"));
                    m.setDataMovimentacao(rs.getDate("data_movimentacao"));
                    m.setQuantidadeMovimentada(rs.getInt("quantidade_movimentada"));
                    m.setResponsavel(rs.getString("responsavel_pela_retirada"));
                    return m;
                }
            }
        }
        return null;
    }

    public List<ControleMovimentacoes> listarTodos() throws SQLException {
        List<ControleMovimentacoes> lista = new ArrayList<>();
        String sql = "SELECT * FROM ControleMovimentacoes ORDER BY data_movimentacao DESC";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ControleMovimentacoes m = new ControleMovimentacoes();
                m.setId(rs.getInt("id_controle_movimentacoes"));
                m.setIdUsuario(rs.getInt("id_usuario"));
                m.setTipoMovimentacao(rs.getString("tipo_movimentacao"));
                m.setDataMovimentacao(rs.getDate("data_movimentacao"));
                m.setQuantidadeMovimentada(rs.getInt("quantidade_movimentada"));
                m.setResponsavel(rs.getString("responsavel_pela_retirada"));
                lista.add(m);
            }
        }
        return lista;
    }
}
