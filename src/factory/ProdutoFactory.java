package factory;

import exception.StringVaziaException;
import model.Fornecedor;
import model.Produto;

public class ProdutoFactory {

    public static Produto criarProduto(int id, String nome, String descricao, double preco,
                                        String categoria, int quantidadeEstoque, Fornecedor fornecedor) throws Exception {

        if (fornecedor == null) {
            throw new StringVaziaException("Fornecedor não pode ser nulo.", 13);
        }

        if (nome == null || nome.isBlank()) {
            throw new StringVaziaException("Nome do produto não pode ser vazio.", 14);
        }

        if (preco < 0) {
            throw new StringVaziaException("Preço não pode ser negativo.", 15);
        }

        if (quantidadeEstoque < 0) {
            throw new StringVaziaException("Quantidade em estoque não pode ser negativa.", 16);
        }

        return new Produto(id, nome, descricao, preco, categoria, quantidadeEstoque, fornecedor);
    }
}
