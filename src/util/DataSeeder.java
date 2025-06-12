package util;

import controller.FornecedorController;
import controller.ProdutoController;
import factory.ProdutoFactory;
import model.Fornecedor;
import model.Produto;

public class DataSeeder {

    public static void popularDadosIniciais(FornecedorController fornecedorController, ProdutoController produtoController) {
        try {
            if (fornecedorController.listarFornecedores().isEmpty()) {

                fornecedorController.registrarFornecedor(
                    "Tech Distribuidora", "11999999999", "Rua A, 123", 1, "12345678000101", "Tech Distribuidora LTDA");

                fornecedorController.registrarFornecedor(
                    "Alpha Suprimentos", "11888888888", "Avenida B, 456", 2, "98765432000109", "Alpha Suprimentos SA");
            }

            if (produtoController.listarProdutos().isEmpty()) {

                Fornecedor f1 = fornecedorController.buscarFornecedorPorId(1);
                Fornecedor f2 = fornecedorController.buscarFornecedorPorId(2);

                if (f1 != null && f2 != null) {
                    Produto p1 = ProdutoFactory.criarProduto(
                        1, "Teclado Mecânico", "Teclado com RGB e switches azuis", 249.90, "Periféricos", 50, f1);

                    Produto p2 = ProdutoFactory.criarProduto(
                        2, "Mouse Gamer", "Mouse com sensor óptico de alta precisão", 179.00, "Periféricos", 30, f1);

                    Produto p3 = ProdutoFactory.criarProduto(
                        3, "Monitor Full HD", "Monitor 24'' com resolução 1080p", 799.99, "Monitores", 20, f2);

                    produtoController.cadastrarProduto(p1);
                    produtoController.cadastrarProduto(p2);
                    produtoController.cadastrarProduto(p3);
                } else {
                    System.err.println("Fornecedores iniciais não encontrados para popular produtos. Verifique o DataSeeder.");
                }
            }

        } catch (Exception e) {
            System.err.println("Erro ao popular dados iniciais: " + e.getMessage());
        }
    }
}