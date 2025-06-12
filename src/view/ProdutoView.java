package view;

import controller.FornecedorController;
import controller.ProdutoController;
import model.Fornecedor;
import model.Produto;

import java.util.List;
import java.util.Scanner;

public class ProdutoView {

    private ProdutoController produtoController;
    private FornecedorController fornecedorController;
    private Scanner scanner;

    public ProdutoView(ProdutoController produtoController, FornecedorController fornecedorController) {
        this.produtoController = produtoController;
        this.fornecedorController = fornecedorController;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao;

        while (true) {
            System.out.println("\n=== MENU PRODUTOS ===");
            System.out.println("1 - Cadastrar Produto");
            System.out.println("2 - Atualizar Produto");
            System.out.println("3 - Excluir Produto");
            System.out.println("4 - Listar Produtos");
            System.out.println("0 - Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            
            switch (opcao) {
                case 1 -> cadastrarProduto();
                case 2 -> atualizarProduto();
                case 3 -> excluirProduto();
                case 4 -> listarProdutos();
                case 0 -> {
                    System.out.println("Retornando ao menu principal...");
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private void cadastrarProduto() {
        System.out.println("\n=== Cadastrar Produto ===");

        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nome: ");
        String nome = scanner.nextLine();

        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        System.out.print("Preço: ");
        double preco = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();

        System.out.print("Quantidade em estoque: ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        Fornecedor fornecedor = escolherFornecedor();
        if (fornecedor == null) {
            System.out.println("Cadastro cancelado. É necessário escolher um fornecedor válido.");
            return;
        }

        Produto produto = new Produto(id, nome, descricao, preco, categoria, quantidade, fornecedor);
        try {
            String resultado = produtoController.cadastrarProduto(produto);
            System.out.println(resultado);
        } catch (Exception e) {
            System.out.println("Erro inesperado ao cadastrar produto: " + e.getMessage());
        }
    }

    private void atualizarProduto() {
        System.out.println("\n=== Atualizar Produto ===");

        System.out.print("ID do produto a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Produto produtoExistente = produtoController.buscarPorId(id);
        if (produtoExistente == null) {
            System.out.println("Produto com ID " + id + " não encontrado.");
            return;
        }

        System.out.print("Novo nome (atual: " + produtoExistente.getNome() + "): ");
        String nome = scanner.nextLine();

        System.out.print("Nova descrição (atual: " + produtoExistente.getDescricao() + "): ");
        String descricao = scanner.nextLine();

        System.out.print("Novo preço (atual: " + produtoExistente.getPreco() + "): ");
        double preco = scanner.nextDouble();
        scanner.nextLine();

        System.out.print("Nova categoria (atual: " + produtoExistente.getCategoria() + "): ");
        String categoria = scanner.nextLine();

        System.out.print("Nova quantidade em estoque (atual: " + produtoExistente.getQuantidadeEstoque() + "): ");
        int quantidade = scanner.nextInt();
        scanner.nextLine();

        Fornecedor fornecedor = escolherFornecedor();
        if (fornecedor == null) {
            System.out.println("Atualização cancelada. É necessário escolher um fornecedor válido.");
            return;
        }

        try {
            String resultado = produtoController.editarProduto(id, nome, descricao, preco, categoria, quantidade, fornecedor);
            System.out.println(resultado);
        } catch (Exception e) {
            System.out.println("Erro inesperado ao atualizar produto: " + e.getMessage());
        }
    }

    private void excluirProduto() {
        System.out.println("\n=== Excluir Produto ===");
        System.out.print("ID do produto a excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            String resultado = produtoController.deletarProduto(id);
            System.out.println(resultado);
        } catch (Exception e) {
            System.out.println("Erro inesperado ao excluir produto: " + e.getMessage());
        }
    }

    private void listarProdutos() {
        System.out.println("\n=== Lista de Produtos ===");
        List<Produto> produtos = produtoController.listarProdutos();

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
        } else {
            for (Produto produto : produtos) {
                System.out.println(produto);
            }
        }
    }

    private Fornecedor escolherFornecedor() {
        System.out.println("\n--- Fornecedores disponíveis ---");
        List<Fornecedor> fornecedores = fornecedorController.listarFornecedores();

        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor cadastrado.");
            return null;
        }

        for (Fornecedor fornecedor : fornecedores) {
            System.out.println("ID: " + fornecedor.getId() + " | Nome: " + fornecedor.getNome() + " | CNPJ: " + fornecedor.getCnpj());
        }

        System.out.print("Digite o ID do fornecedor desejado: ");
        int idFornecedor = scanner.nextInt();
        scanner.nextLine();

        return fornecedorController.buscarFornecedorPorId(idFornecedor);
    }
}