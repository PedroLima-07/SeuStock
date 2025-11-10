import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import DAO.ControleMovimentacoesDAO;
import DAO.EstoqueDAO;
import DAO.FornecedorDAO;
import DAO.ItemDAO;
import DAO.ItemFornecedorDAO;
import DAO.LocalizacaoDAO;
import DAO.UsuarioDAO;
import model.ControleMovimentacoes;
import model.Estoque;
import model.Fornecedor;
import model.Item;
import model.ItemFornecedor;
import model.Localizacao;
import model.Usuario;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final ItemDAO itemDAO = new ItemDAO();
    private static final LocalizacaoDAO locDAO = new LocalizacaoDAO();
    private static final FornecedorDAO fornDAO = new FornecedorDAO();
    private static final UsuarioDAO userDAO = new UsuarioDAO();
    private static final EstoqueDAO estDAO = new EstoqueDAO();
    private static final ControleMovimentacoesDAO movDAO = new ControleMovimentacoesDAO();
    private static final ItemFornecedorDAO ifDAO = new ItemFornecedorDAO();

    public static void main(String[] args) {
        while (true) {
            showMainMenu();
            String opt = sc.nextLine().trim();
            try {
                switch (opt) {
                    case "1": menuItens(); break;
                    case "2": menuLocalizacoes(); break;
                    case "3": menuFornecedores(); break;
                    case "4": menuUsuarios(); break;
                    case "5": menuEstoque(); break;
                    case "6": menuMovimentacao(); break;
                    case "7": menuItemFornecedor(); break;
                    case "0": System.out.println("Saindo..."); return;
                    default: System.out.println("Opção inválida.");
                }
            } catch (SQLException e) {
                System.err.println("Erro de banco: " + e.getMessage());
            } catch (NumberFormatException nfe) {
                System.err.println("Entrada numérica inválida.");
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("\n=== SeuStock ===");
        System.out.println("1 - Itens");
        System.out.println("2 - Localizações");
        System.out.println("3 - Fornecedores");
        System.out.println("4 - Usuários");
        System.out.println("5 - Estoque (ajustar qtd / listar)");
        System.out.println("6 - Movimentações (registrar / listar)");
        System.out.println("7 - Item_Fornecedor");
        System.out.println("0 - Sair");
        System.out.print("Escolha: ");
    }

    // --- Itens ---
    private static void menuItens() throws SQLException {
        while (true) {
            System.out.println("\n-- Itens --");
            System.out.println("1-Cadastrar 2-Listar 3-Atualizar 4-Excluir 0-Voltar");
            String o = sc.nextLine().trim();
            if (o.equals("0")) return;
            switch (o) {
                case "1": cadastrarItem(); break;
                case "2": listarItens(); break;
                case "3": atualizarItem(); break;
                case "4": excluirItem(); break;
                default: System.out.println("Inválido");
            }
        }
    }

    private static void cadastrarItem() throws SQLException {
        System.out.print("Nome: "); String nome = sc.nextLine();
        System.out.print("Descricao: "); String desc = sc.nextLine();
        System.out.print("Categoria: "); String cat = sc.nextLine();
        System.out.print("Unidade: "); String uni = sc.nextLine();
        Item it = new Item();
        it.setNome(nome); it.setDescricao(desc); it.setCategoria(cat); it.setUnidadeDeMedida(uni);
        itemDAO.inserir(it);
        System.out.println("Inserido com id: " + it.getId());
    }

    private static void listarItens() throws SQLException {
        List<Item> lista = itemDAO.listarTodos();
        if (lista.isEmpty()) System.out.println("Nenhum item.");
        else lista.forEach(System.out::println);
    }

    private static void atualizarItem() throws SQLException {
        System.out.print("ID do item: "); int id = Integer.parseInt(sc.nextLine());
        Item it = itemDAO.buscarPorId(id);
        if (it == null) { System.out.println("Item não encontrado."); return; }
        System.out.print("Nome ("+it.getNome()+"): "); String s = sc.nextLine(); if (!s.isEmpty()) it.setNome(s);
        System.out.print("Descricao ("+it.getDescricao()+"): "); s = sc.nextLine(); if (!s.isEmpty()) it.setDescricao(s);
        System.out.print("Categoria ("+it.getCategoria()+"): "); s = sc.nextLine(); if (!s.isEmpty()) it.setCategoria(s);
        System.out.print("Unidade ("+it.getUnidadeDeMedida()+"): "); s = sc.nextLine(); if (!s.isEmpty()) it.setUnidadeDeMedida(s);
        itemDAO.atualizar(it);
        System.out.println("Atualizado.");
    }

    private static void excluirItem() throws SQLException {
        System.out.print("ID do item a excluir: "); int id = Integer.parseInt(sc.nextLine());
        itemDAO.excluir(id);
        System.out.println("Excluído (se existia).");
    }

    // --- Localizações ---
    private static void menuLocalizacoes() throws SQLException {
        while (true) {
            System.out.println("\n-- Localizações --");
            System.out.println("1-Cadastrar 2-Listar 3-Atualizar 4-Excluir 0-Voltar");
            String o = sc.nextLine().trim();
            if (o.equals("0")) return;
            switch (o) {
                case "1": cadastrarLocalizacao(); break;
                case "2": listarLocalizacoes(); break;
                case "3": atualizarLocalizacao(); break;
                case "4": excluirLocalizacao(); break;
                default: System.out.println("Inválido");
            }
        }
    }

    private static void cadastrarLocalizacao() throws SQLException {
        System.out.print("Nome: "); String nome = sc.nextLine();
        Localizacao l = new Localizacao();
        l.setNome(nome);
        locDAO.inserir(l);
        System.out.println("Inserida id: " + l.getId());
    }

    private static void listarLocalizacoes() throws SQLException {
        List<Localizacao> lista = locDAO.listarTodos();
        if (lista.isEmpty()) System.out.println("Nenhuma localização.");
        else lista.forEach(System.out::println);
    }

    private static void atualizarLocalizacao() throws SQLException {
        System.out.print("ID: "); int id = Integer.parseInt(sc.nextLine());
        Localizacao l = locDAO.buscarPorId(id);
        if (l==null) { System.out.println("Não encontrado."); return; }
        System.out.print("Nome ("+l.getNome()+"): "); String s = sc.nextLine(); if (!s.isEmpty()) l.setNome(s);
        locDAO.atualizar(l);
        System.out.println("Atualizado.");
    }

    private static void excluirLocalizacao() throws SQLException {
        System.out.print("ID: "); int id = Integer.parseInt(sc.nextLine());
        locDAO.excluir(id);
        System.out.println("Excluído.");
    }

    // --- Fornecedores ---
    private static void menuFornecedores() throws SQLException {
        while (true) {
            System.out.println("\n-- Fornecedores --");
            System.out.println("1-Cadastrar 2-Listar 3-Atualizar 4-Excluir 0-Voltar");
            String o = sc.nextLine().trim();
            if (o.equals("0")) return;
            switch (o) {
                case "1": cadastrarFornecedor(); break;
                case "2": listarFornecedores(); break;
                case "3": atualizarFornecedor(); break;
                case "4": excluirFornecedor(); break;
                default: System.out.println("Inválido");
            }
        }
    }

    private static void cadastrarFornecedor() throws SQLException {
        System.out.print("Nome: "); String nome = sc.nextLine();
        Fornecedor f = new Fornecedor();
        f.setNome(nome);
        fornDAO.inserir(f);
        System.out.println("Inserido id: " + f.getId());
    }

    private static void listarFornecedores() throws SQLException {
        List<Fornecedor> lista = fornDAO.listarTodos();
        if (lista.isEmpty()) System.out.println("Nenhum fornecedor.");
        else lista.forEach(System.out::println);
    }

    private static void atualizarFornecedor() throws SQLException {
        System.out.print("ID: "); int id = Integer.parseInt(sc.nextLine());
        Fornecedor f = fornDAO.buscarPorId(id);
        if (f==null) { System.out.println("Não encontrado."); return; }
        System.out.print("Nome ("+f.getNome()+"): "); String s = sc.nextLine(); if (!s.isEmpty()) f.setNome(s);
        fornDAO.atualizar(f);
        System.out.println("Atualizado.");
    }

    private static void excluirFornecedor() throws SQLException {
        System.out.print("ID: "); int id = Integer.parseInt(sc.nextLine());
        fornDAO.excluir(id);
        System.out.println("Excluído.");
    }

    // --- Usuários ---
    private static void menuUsuarios() throws SQLException {
        while (true) {
            System.out.println("\n-- Usuários --");
            System.out.println("1-Cadastrar 2-Listar 3-Atualizar 4-Excluir 0-Voltar");
            String o = sc.nextLine().trim();
            if (o.equals("0")) return;
            switch (o) {
                case "1": cadastrarUsuario(); break;
                case "2": listarUsuarios(); break;
                case "3": atualizarUsuario(); break;
                case "4": excluirUsuario(); break;
                default: System.out.println("Inválido");
            }
        }
    }

    private static void cadastrarUsuario() throws SQLException {
        System.out.print("Nome: "); String nome = sc.nextLine();
        System.out.print("Senha: "); String pass = sc.nextLine();
        System.out.print("Tipo: "); String tipo = sc.nextLine();
        Usuario u = new Usuario();
        u.setNome(nome); u.setSenha(pass); u.setTipoDeUsuario(tipo);
        userDAO.inserir(u);
        System.out.println("Inserido id: " + u.getId());
    }

    private static void listarUsuarios() throws SQLException {
        List<Usuario> lista = userDAO.listarTodos();
        if (lista.isEmpty()) System.out.println("Nenhum usuário.");
        else lista.forEach(System.out::println);
    }

    private static void atualizarUsuario() throws SQLException {
        System.out.print("ID: "); int id = Integer.parseInt(sc.nextLine());
        Usuario u = userDAO.buscarPorId(id);
        if (u==null) { System.out.println("Não encontrado."); return; }
        System.out.print("Nome ("+u.getNome()+"): "); String s = sc.nextLine(); if (!s.isEmpty()) u.setNome(s);
        System.out.print("Senha (enter para manter): "); s = sc.nextLine(); if (!s.isEmpty()) u.setSenha(s);
        System.out.print("Tipo ("+u.getTipoDeUsuario()+"): "); s = sc.nextLine(); if (!s.isEmpty()) u.setTipoDeUsuario(s);
        userDAO.atualizar(u);
        System.out.println("Atualizado.");
    }

    private static void excluirUsuario() throws SQLException {
        System.out.print("ID: "); int id = Integer.parseInt(sc.nextLine());
        userDAO.excluir(id);
        System.out.println("Excluído.");
    }

    // --- Estoque ---
    private static void menuEstoque() throws SQLException {
        while (true) {
            System.out.println("\n-- Estoque --");
            System.out.println("1-Cadastrar 2-ListarTodos 3-ListarPorItem 4-AjustarQuantidade 5-Atualizar 6-Excluir 0-Voltar");
            String o = sc.nextLine().trim();
            if (o.equals("0")) return;
            switch (o) {
                case "1": cadastrarEstoque(); break;
                case "2": listarEstoques(); break;
                case "3": listarPorItem(); break;
                case "4": ajustarQuantidade(); break;
                case "5": atualizarEstoque(); break;
                case "6": excluirEstoque(); break;
                default: System.out.println("Inválido");
            }
        }
    }

    private static void cadastrarEstoque() throws SQLException {
        System.out.print("id_item: "); int idItem = Integer.parseInt(sc.nextLine());
        System.out.print("id_localizacao: "); int idLoc = Integer.parseInt(sc.nextLine());
        System.out.print("quantidade_atual: "); int qtd = Integer.parseInt(sc.nextLine());
        System.out.print("estoque_minimo: "); int min = Integer.parseInt(sc.nextLine());
        System.out.print("estoque_maximo: "); int max = Integer.parseInt(sc.nextLine());
        Estoque e = new Estoque();
        e.setIdItem(idItem); e.setIdLocalizacao(idLoc); e.setQuantidadeAtual(qtd); e.setEstoqueMinimo(min); e.setEstoqueMaximo(max);
        estDAO.inserir(e);
        System.out.println("Inserido id: " + e.getId());
    }

    private static void listarEstoques() throws SQLException {
        List<Estoque> lista = estDAO.listarTodos();
        if (lista.isEmpty()) System.out.println("Nenhum estoque.");
        else lista.forEach(System.out::println);
    }

    private static void listarPorItem() throws SQLException {
        System.out.print("id_item: "); int idItem = Integer.parseInt(sc.nextLine());
        List<Estoque> lista = estDAO.listarPorItem(idItem);
        if (lista.isEmpty()) System.out.println("Nenhum estoque para esse item.");
        else lista.forEach(System.out::println);
    }

    private static void ajustarQuantidade() throws SQLException {
        System.out.print("id_estoque: "); int id = Integer.parseInt(sc.nextLine());
        System.out.print("Nova quantidade atual: "); int nova = Integer.parseInt(sc.nextLine());
        estDAO.ajustarQuantidade(id, nova);
        System.out.println("Quantidade atualizada.");
    }

    private static void atualizarEstoque() throws SQLException {
        System.out.print("id_estoque: "); int id = Integer.parseInt(sc.nextLine());
        Estoque e = estDAO.buscarPorId(id);
        if (e==null) { System.out.println("Não encontrado."); return; }
        System.out.print("id_item ("+e.getIdItem()+"): "); String s = sc.nextLine(); if (!s.isEmpty()) e.setIdItem(Integer.parseInt(s));
        System.out.print("id_localizacao ("+e.getIdLocalizacao()+"): "); s = sc.nextLine(); if (!s.isEmpty()) e.setIdLocalizacao(Integer.parseInt(s));
        System.out.print("quantidade_atual ("+e.getQuantidadeAtual()+"): "); s = sc.nextLine(); if (!s.isEmpty()) e.setQuantidadeAtual(Integer.parseInt(s));
        System.out.print("estoque_minimo ("+e.getEstoqueMinimo()+"): "); s = sc.nextLine(); if (!s.isEmpty()) e.setEstoqueMinimo(Integer.parseInt(s));
        System.out.print("estoque_maximo ("+e.getEstoqueMaximo()+"): "); s = sc.nextLine(); if (!s.isEmpty()) e.setEstoqueMaximo(Integer.parseInt(s));
        estDAO.atualizar(e);
        System.out.println("Atualizado.");
    }

    private static void excluirEstoque() throws SQLException {
        System.out.print("id_estoque: "); int id = Integer.parseInt(sc.nextLine());
        estDAO.excluir(id);
        System.out.println("Excluído.");
    }

    // --- Movimentações ---
    private static void menuMovimentacao() throws SQLException {
        while (true) {
            System.out.println("\n-- Movimentações --");
            System.out.println("1-Registrar movimentação 2-Listar 0-Voltar");
            String o = sc.nextLine().trim();
            if (o.equals("0")) return;
            switch (o) {
                case "1": registrarMovimentacao(); break;
                case "2": listarMovimentacoes(); break;
                default: System.out.println("Inválido");
            }
        }
    }

    private static void registrarMovimentacao() throws SQLException {
        System.out.print("id_usuario: "); int idUser = Integer.parseInt(sc.nextLine());
        System.out.print("tipo_movimentacao (entrada/saida): "); String tipo = sc.nextLine();
        System.out.print("data (YYYY-MM-DD): "); String dataS = sc.nextLine();
        System.out.print("quantidade_movimentada: "); int qtd = Integer.parseInt(sc.nextLine());
        System.out.print("responsavel (opcional): "); String resp = sc.nextLine();

        ControleMovimentacoes m = new ControleMovimentacoes();
        m.setIdUsuario(idUser);
        m.setTipoMovimentacao(tipo);
        m.setDataMovimentacao(Date.valueOf(dataS));
        m.setQuantidadeMovimentada(qtd);
        m.setResponsavel(resp.isEmpty()?null:resp);
        movDAO.inserir(m);

        System.out.println("Movimentação registrada com id: " + m.getId());
    }

    private static void listarMovimentacoes() throws SQLException {
        List<ControleMovimentacoes> lista = movDAO.listarTodos();
        if (lista.isEmpty()) System.out.println("Nenhuma movimentação.");
        else lista.forEach(System.out::println);
    }

    // --- Item_Fornecedor ---
    private static void menuItemFornecedor() throws SQLException {
        while (true) {
            System.out.println("\n-- Item_Fornecedor --");
            System.out.println("1-Cadastrar 2-Listar 3-Atualizar 4-Excluir 0-Voltar");
            String o = sc.nextLine().trim();
            if (o.equals("0")) return;
            switch (o) {
                case "1": cadastrarItemFornecedor(); break;
                case "2": listarItemFornecedor(); break;
                case "3": atualizarItemFornecedor(); break;
                case "4": excluirItemFornecedor(); break;
                default: System.out.println("Inválido");
            }
        }
    }

    private static void cadastrarItemFornecedor() throws SQLException {
        System.out.print("id_item: "); int idItem = Integer.parseInt(sc.nextLine());
        System.out.print("id_fornecedor: "); int idForn = Integer.parseInt(sc.nextLine());
        System.out.print("tipo_fornecedor: "); String tipo = sc.nextLine();
        System.out.print("custo_unitario (ex: 10.50): "); String custoS = sc.nextLine();
        System.out.print("data_entrega (YYYY-MM-DD): "); String de = sc.nextLine();
        System.out.print("data_vigencia (YYYY-MM-DD): "); String dv = sc.nextLine();

        ItemFornecedor ifo = new ItemFornecedor();
        ifo.setIdItem(idItem); ifo.setIdFornecedor(idForn); ifo.setTipoFornecedor(tipo);
        if (!custoS.isEmpty()) ifo.setCustoUnitario(new java.math.BigDecimal(custoS));
        if (!de.isEmpty()) ifo.setDataEntrega(Date.valueOf(de));
        if (!dv.isEmpty()) ifo.setDataVigencia(Date.valueOf(dv));
        ifDAO.inserir(ifo);
        System.out.println("Inserido id: " + ifo.getId());
    }

    private static void listarItemFornecedor() throws SQLException {
        List<ItemFornecedor> lista = ifDAO.listarTodos();
        if (lista.isEmpty()) System.out.println("Nenhum registro.");
        else lista.forEach(System.out::println);
    }

    private static void atualizarItemFornecedor() throws SQLException {
        System.out.print("id_item_fornecedor: "); int id = Integer.parseInt(sc.nextLine());
        ItemFornecedor ifo = ifDAO.buscarPorId(id);
        if (ifo==null) { System.out.println("Não encontrado."); return; }
        System.out.print("id_item ("+ifo.getIdItem()+"): "); String s = sc.nextLine(); if (!s.isEmpty()) ifo.setIdItem(Integer.parseInt(s));
        System.out.print("id_fornecedor ("+ifo.getIdFornecedor()+"): "); s = sc.nextLine(); if (!s.isEmpty()) ifo.setIdFornecedor(Integer.parseInt(s));
        System.out.print("tipo_fornecedor ("+ifo.getTipoFornecedor()+"): "); s = sc.nextLine(); if (!s.isEmpty()) ifo.setTipoFornecedor(s);
        System.out.print("custo_unitario ("+ifo.getCustoUnitario()+"): "); s = sc.nextLine(); if (!s.isEmpty()) ifo.setCustoUnitario(new java.math.BigDecimal(s));
        System.out.print("data_entrega ("+ifo.getDataEntrega()+"): "); s = sc.nextLine(); if (!s.isEmpty()) ifo.setDataEntrega(Date.valueOf(s));
        System.out.print("data_vigencia ("+ifo.getDataVigencia()+"): "); s = sc.nextLine(); if (!s.isEmpty()) ifo.setDataVigencia(Date.valueOf(s));
        ifDAO.atualizar(ifo);
        System.out.println("Atualizado.");
    }

    private static void excluirItemFornecedor() throws SQLException {
        System.out.print("id_item_fornecedor: "); int id = Integer.parseInt(sc.nextLine());
        ifDAO.excluir(id);
        System.out.println("Excluído.");
    }
}

