// GABRIEL HENRIQUE VAZ DOS SANTOS | RGM 37340611
// EDSON VALERIO VELOSO | RGM 38014335
// CAEL DAVID SOARES DA COSTA | RGM 38625253 
// VINICIUS MULLING | RGM 37996622
// ANDERSON TORRES JUNIOR | RGM 37108182

import controller.FornecedorController;
import controller.MovimentacaoController;
import controller.ProdutoController;
import view.FornecedorView;
import view.MovimentacaoView;
import view.ProdutoView;
import util.DataSeeder;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {

        FornecedorController fornecedorController = new FornecedorController();
        ProdutoController produtoController = new ProdutoController();
        MovimentacaoController movimentacaoController = new MovimentacaoController(produtoController); 

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