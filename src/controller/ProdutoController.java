package controller;

import model.Fornecedor;
import model.Produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoController {

    private List<Produto> produtos;

    public ProdutoController() {
        this.produtos = new ArrayList<>();
    }

    public void cadastrarProduto(Produto produto) throws Exception {
        if (produto.getFornecedor() == null) {
            throw new Exception("Fornecedor deve ser informado para cadastrar o produto.");
        }

        produtos.add(produto);
    }

    public void editarProduto(int id, String nome, String descricao, double preco, String categoria, int quantidadeEstoque, Fornecedor fornecedor) throws Exception {
        Produto produto = buscarPorId(id);

        if (produto == null) {
            throw new Exception("Produto não encontrado.");
        }

        if (fornecedor == null) {
            throw new Exception("Fornecedor deve ser informado.");
        }

        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produto.setCategoria(categoria);
        produto.setQuantidadeEstoque(quantidadeEstoque);
        produto.setFornecedor(fornecedor);
    }

    public void deletarProduto(int id) throws Exception {
        Produto produto = buscarPorId(id);

        if (produto == null) {
            throw new Exception("Produto não encontrado.");
        }

        produtos.remove(produto);
    }

    public List<Produto> listarProdutos() {
        return produtos;
    }

    private Produto buscarPorId(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}
