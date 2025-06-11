import controller.FornecedorController;
import controller.MovimentacaoController;
import controller.ProdutoController;
import model.Fornecedor;
import model.Produto;
import view.FornecedorView;
import view.MovimentacaoView;
import view.ProdutoView;
import util.DataSeeder;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        List<Fornecedor> fornecedores = new ArrayList<>();
        List<Produto> produtos = new ArrayList<>();

        FornecedorController fornecedorController = new FornecedorController(fornecedores); // fazer dao pra fornecedor e produto pra nao precisar instanciar as arraylist ali em cima
        ProdutoController produtoController = new ProdutoController(produtos);
        MovimentacaoController movimentacaoController = new MovimentacaoController();

        DataSeeder.popularDadosIniciais(fornecedorController, produtoController);

        FornecedorView fornecedor = new FornecedorView(fornecedorController);
        ProdutoView produto = new ProdutoView(produtoController, fornecedorController);
        MovimentacaoView movimentacao = new MovimentacaoView(produtoController, movimentacaoController);

        try (Scanner scanner = new Scanner(System.in)) {
            int opcaoMenu;
            do {
                System.out.println("=== Menu Principal ===");
                System.out.println("1. Fornecedor");
                System.out.println("2. Produto");
                System.out.println("3. Movimentação");
                System.out.println("0. Sair");
                System.out.print("Escolha uma opção: ");

                opcaoMenu = scanner.nextInt();
                scanner.nextLine();

                switch (opcaoMenu) {
                    case 1 -> fornecedor.exibirMenu();
                    case 2 -> produto.exibirMenu();
                    case 3 -> movimentacao.exibirMenu();
                    case 0 -> System.out.println("Saindo do sistema...");
                    default -> System.out.println("Opção inválida. Tente novamente.");
                }
            } while (opcaoMenu != 0);
        }
    }
}
