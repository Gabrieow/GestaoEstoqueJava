package view;

import controller.FornecedorController;
import model.Fornecedor;

import java.util.List;
import java.util.Scanner;

public class FornecedorView {

    private FornecedorController controller;
    private Scanner scanner;

    public FornecedorView(FornecedorController controller) {
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        int opcao = 0;

        do {
            System.out.println("\n=== MENU FORNECEDOR ===");
            System.out.println("1 - Cadastrar Fornecedor");
            System.out.println("2 - Atualizar Fornecedor");
            System.out.println("3 - Excluir Fornecedor");
            System.out.println("4 - Listar Fornecedores");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarFornecedor();
                case 2 -> atualizarFornecedor();
                case 3 -> excluirFornecedor();
                case 4 -> listarFornecedores();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    private void cadastrarFornecedor() {
        System.out.println("\n=== Cadastrar Fornecedor ===");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("CNPJ: ");
        String cnpj = scanner.nextLine();
        System.out.print("Razão Social: ");
        String razaoSocial = scanner.nextLine();

        try {
            controller.registrarFornecedor(nome, telefone, endereco, id, cnpj, razaoSocial);
            System.out.println("Fornecedor cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao cadastrar fornecedor: " + e.getMessage());
        }
    }

    private void atualizarFornecedor() {
        System.out.println("\n=== Atualizar Fornecedor ===");
        System.out.print("ID do fornecedor a atualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Novo nome: ");
        String nome = scanner.nextLine();
        System.out.print("Novo telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Novo endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Novo CNPJ: ");
        String cnpj = scanner.nextLine();
        System.out.print("Nova razão social: ");
        String razaoSocial = scanner.nextLine();

        try {
            controller.atualizarFornecedor(id, nome, telefone, endereco, cnpj, razaoSocial);
            System.out.println("Fornecedor atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao atualizar fornecedor: " + e.getMessage());
        }
    }

    private void excluirFornecedor() {
        System.out.println("\n=== Excluir Fornecedor ===");
        System.out.print("ID do fornecedor a excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            controller.excluirFornecedor(id);
            System.out.println("Fornecedor excluído com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao excluir fornecedor: " + e.getMessage());
        }
    }

    private void listarFornecedores() {
        System.out.println("\n=== Lista de Fornecedores ===");
        List<Fornecedor> fornecedores = controller.listarFornecedores();
        if (fornecedores.isEmpty()) {
            System.out.println("Nenhum fornecedor cadastrado.");
        } else {
            for (Fornecedor f : fornecedores) {
                System.out.println(f.exibirDados());
            }
        }
    }
}
