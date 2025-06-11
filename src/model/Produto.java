package model;

import java.io.Serializable;

public class Produto implements Serializable { // criar produtoDAO pra salvar e carregar produtos inseridos pelo usuario anteriormente
    
    private int id;
    private String nome;
    private String descricao;
    private double preco;
    private String categoria;
    private int quantidadeEstoque;
    private Fornecedor fornecedor;

    public Produto(int id, String nome, String descricao, double preco, String categoria, int quantidadeEstoque, Fornecedor fornecedor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.categoria = categoria;
        this.quantidadeEstoque = quantidadeEstoque;
        this.fornecedor = fornecedor;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }

    public int getQuantidadeEstoque() { return quantidadeEstoque; }
    public void setQuantidadeEstoque(int quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }

    public Fornecedor getFornecedor() { return fornecedor; }
    public void setFornecedor(Fornecedor fornecedor) { this.fornecedor = fornecedor; }

    @Override
    public String toString() {
        return """
            ───────────────────────────────
            ID: %d
            Nome: %s
            Descrição: %s
            Preço: R$ %.2f
            Categoria: %s
            Quantidade em Estoque: %d
            Fornecedor: %s
            """.formatted(
                id,
                nome,
                descricao,
                preco,
                categoria,
                quantidadeEstoque,
                fornecedor != null ? fornecedor.getNome() : "Sem fornecedor"
        );
    }

    public String atualizarPreco(double novoPreco) {
        if (novoPreco <= 0) {
            throw new IllegalArgumentException("Preço inválido. Deve ser maior que zero.");
        }
        this.preco = novoPreco;
        return "Preço atualizado para R$ " + String.format("%.2f", novoPreco);
    }

    public String atualizarPreco(double novoPreco, boolean promocao) {
        String status = promocao ? "com promoção" : "sem promoção";
        atualizarPreco(novoPreco);
        return "Preço atualizado para R$ " + String.format("%.2f", novoPreco) + " " + status;
    }

    public String adicionarEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade para adicionar deve ser maior que zero.");
        }
        this.quantidadeEstoque += quantidade;
        return "Estoque atualizado. Quantidade atual: " + this.quantidadeEstoque;
    }

    public Boolean removerEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade para remover deve ser maior que zero.");
        }
        if (quantidade > this.quantidadeEstoque) {
            throw new IllegalArgumentException("Estoque insuficiente. Quantidade disponível: " + this.quantidadeEstoque);
        }
        this.quantidadeEstoque -= quantidade;
        return true;
    }
}
