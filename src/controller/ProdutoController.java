package controller;

import model.Fornecedor;
import model.Produto;
import model.ProdutoDAO;
import java.util.ArrayList;
import java.util.List;

public class ProdutoController {

    private List<Produto> produtos;

    public ProdutoController() {
        this.produtos = ProdutoDAO.carregarProdutos();
    }

    public void salvarProdutosInternamente() {
        ProdutoDAO.salvarProdutos(produtos);
    }

    public String cadastrarProduto(Produto produto) throws Exception {
        if (produto.getFornecedor() == null) {
            return "Erro: Fornecedor deve ser informado para cadastrar o produto.";
        }
        if (produtos.stream().anyMatch(p -> p.getId() == produto.getId())) {
            return "Erro: Produto com ID " + produto.getId() + " já existe.";
        }

        produtos.add(produto);
        salvarProdutosInternamente();
        return "Produto cadastrado com sucesso!";
    }

    public String editarProduto(int id, String nome, String descricao, double preco, String categoria, int quantidadeEstoque, Fornecedor fornecedor) throws Exception {
        Produto produto = buscarPorId(id);

        if (produto == null) {
            return "Erro: Produto não encontrado.";
        }

        if (fornecedor == null) {
            return "Erro: Fornecedor deve ser informado.";
        }

        produto.setNome(nome);
        produto.setDescricao(descricao);
        produto.setPreco(preco);
        produto.setCategoria(categoria);
        produto.setQuantidadeEstoque(quantidadeEstoque);
        produto.setFornecedor(fornecedor);

        salvarProdutosInternamente();
        return "Produto atualizado com sucesso!";
    }

    public String deletarProduto(int id) throws Exception {
        Produto produto = buscarPorId(id);

        if (produto == null) {
            return "Erro: Produto não encontrado.";
        }

        produtos.remove(produto);
        salvarProdutosInternamente();
        return "Produto excluído com sucesso!";
    }

    public List<Produto> listarProdutos() {
        return new ArrayList<>(produtos);
    }

    public Produto buscarPorId(int id) {
        for (Produto p : produtos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }
}