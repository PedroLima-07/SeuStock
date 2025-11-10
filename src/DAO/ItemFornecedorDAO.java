package DAO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.ItemFornecedor;

public class ItemFornecedorDAO {
    public void inserir(ItemFornecedor ifo) throws SQLException {
        String sql = "INSERT INTO Item_Fornecedor (id_item, id_fornecedor, tipo_fornecedor, custo_unitario, data_entrega, data_vigencia) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, ifo.getIdItem());
            ps.setInt(2, ifo.getIdFornecedor());
            ps.setString(3, ifo.getTipoFornecedor());
            if (ifo.getCustoUnitario() == null) ps.setNull(4, Types.DECIMAL);
            else ps.setBigDecimal(4, ifo.getCustoUnitario());
            ps.setDate(5, ifo.getDataEntrega());
            ps.setDate(6, ifo.getDataVigencia());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) { if (rs.next()) ifo.setId(rs.getInt(1)); }
        }
    }

    public void atualizar(ItemFornecedor ifo) throws SQLException {
        String sql = "UPDATE Item_Fornecedor SET id_item=?, id_fornecedor=?, tipo_fornecedor=?, custo_unitario=?, data_entrega=?, data_vigencia=? WHERE id_item_fornecedor=?";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, ifo.getIdItem());
            ps.setInt(2, ifo.getIdFornecedor());
            ps.setString(3, ifo.getTipoFornecedor());
            if (ifo.getCustoUnitario() == null) ps.setNull(4, Types.DECIMAL);
            else ps.setBigDecimal(4, ifo.getCustoUnitario());
            ps.setDate(5, ifo.getDataEntrega());
            ps.setDate(6, ifo.getDataVigencia());
            ps.setInt(7, ifo.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM Item_Fornecedor WHERE id_item_fornecedor=?";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) { ps.setInt(1, id); ps.executeUpdate(); }
    }

    public ItemFornecedor buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM Item_Fornecedor WHERE id_item_fornecedor=?";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    ItemFornecedor ifo = new ItemFornecedor();
                    ifo.setId(rs.getInt("id_item_fornecedor"));
                    ifo.setIdItem(rs.getInt("id_item"));
                    ifo.setIdFornecedor(rs.getInt("id_fornecedor"));
                    ifo.setTipoFornecedor(rs.getString("tipo_fornecedor"));
                    ifo.setCustoUnitario(rs.getBigDecimal("custo_unitario"));
                    ifo.setDataEntrega(rs.getDate("data_entrega"));
                    ifo.setDataVigencia(rs.getDate("data_vigencia"));
                    return ifo;
                }
            }
        }
        return null;
    }

    public List<ItemFornecedor> listarTodos() throws SQLException {
        List<ItemFornecedor> lista = new ArrayList<>();
        String sql = "SELECT * FROM Item_Fornecedor ORDER BY id_item_fornecedor";
        try (Connection c = Conexao.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                ItemFornecedor ifo = new ItemFornecedor();
                ifo.setId(rs.getInt("id_item_fornecedor"));
                ifo.setIdItem(rs.getInt("id_item"));
                ifo.setIdFornecedor(rs.getInt("id_fornecedor"));
                ifo.setTipoFornecedor(rs.getString("tipo_fornecedor"));
                ifo.setCustoUnitario(rs.getBigDecimal("custo_unitario"));
                ifo.setDataEntrega(rs.getDate("data_entrega"));
                ifo.setDataVigencia(rs.getDate("data_vigencia"));
                lista.add(ifo);
            }
        }
        return lista;
    }
}
