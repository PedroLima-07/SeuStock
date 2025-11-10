-- ===========================================
-- BANCO DE DADOS: SeuStock
-- ===========================================

CREATE DATABASE IF NOT EXISTS SeuStock;
USE SeuStock;

-- ===========================================
-- TABELA: Localizacao
-- ===========================================
CREATE TABLE Localizacao (
    id_localizacao INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

-- ===========================================
-- TABELA: Item
-- ===========================================
CREATE TABLE Item (
    id_item INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    categoria VARCHAR(50),
    unidade_de_medida VARCHAR(50)
);

-- ===========================================
-- TABELA: Fornecedor
-- ===========================================
CREATE TABLE Fornecedor (
    id_fornecedor INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

-- ===========================================
-- TABELA: Usuario
-- ===========================================
CREATE TABLE Usuario (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    tipo_de_usuario VARCHAR(50)
);

-- ===========================================
-- TABELA: ControleMovimentacoes
-- ===========================================
CREATE TABLE ControleMovimentacoes (
    id_controle_movimentacoes INT AUTO_INCREMENT PRIMARY KEY,
    id_usuario INT NOT NULL,
    tipo_movimentacao VARCHAR(50) NOT NULL,
    data_movimentacao DATE NOT NULL,
    quantidade_movimentada INT NOT NULL,
    responsavel_pela_retirada VARCHAR(100),
    FOREIGN KEY (id_usuario) REFERENCES Usuario(id_usuario)
);

-- ===========================================
-- TABELA: Estoque
-- ===========================================
CREATE TABLE Estoque (
    id_estoque INT AUTO_INCREMENT PRIMARY KEY,
    id_item INT NOT NULL,
    id_localizacao INT NOT NULL,
    id_controle_movimentacao INT,
    quantidade_atual INT DEFAULT 0,
    estoque_minimo INT DEFAULT 0,
    estoque_maximo INT DEFAULT 0,
    FOREIGN KEY (id_item) REFERENCES Item(id_item),
    FOREIGN KEY (id_localizacao) REFERENCES Localizacao(id_localizacao),
    FOREIGN KEY (id_controle_movimentacao) REFERENCES ControleMovimentacoes(id_controle_movimentacoes)
);

-- ===========================================
-- TABELA: Item_Fornecedor
-- ===========================================
CREATE TABLE Item_Fornecedor (
    id_item_fornecedor INT AUTO_INCREMENT PRIMARY KEY,
    id_item INT NOT NULL,
    id_fornecedor INT NOT NULL,
    tipo_fornecedor VARCHAR(50),
    custo_unitario DECIMAL(10,2),
    data_entrega DATE,
    data_vigencia DATE,
    FOREIGN KEY (id_item) REFERENCES Item(id_item),
    FOREIGN KEY (id_fornecedor) REFERENCES Fornecedor(id_fornecedor)
);

-- ===========================================
-- VIEWS OPCIONAIS (exemplo)
-- ===========================================
-- Exemplo de view para consulta rápida de estoque
CREATE VIEW vw_estoque_detalhado AS
SELECT 
    e.id_estoque,
    i.nome AS item,
    l.nome AS localizacao,
    e.quantidade_atual,
    e.estoque_minimo,
    e.estoque_maximo
FROM Estoque e
JOIN Item i ON e.id_item = i.id_item
JOIN Localizacao l ON e.id_localizacao = l.id_localizacao;
