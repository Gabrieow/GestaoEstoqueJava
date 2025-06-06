package view;

import controller.MovimentacaoController;
import controller.ProdutoController;
import model.Movimentacao;
import model.Produto;
import model.TipoMovimentacao;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class MovimentacaoView {
    private ProdutoController produtoController;
    private MovimentacaoController movimentacaoController;
    private Scanner scanner;

    public MovimentacaoView(ProdutoController produtoController, MovimentacaoController movimentacaoController) {
        this.produtoController = produtoController;
        this.movimentacaoController = movimentacaoController;
        this.scanner = new Scanner(System.in);
    }


    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n===== MENU MOVIMENTAÇÕES =====");
            System.out.println("1 - Registrar movimentação");
            System.out.println("2 - Exibir histórico de movimentações");
            System.out.println("3 - Listar produtos disponíveis");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");
            opcao = lerInt();

            switch (opcao) {
                case 1 -> registrarMovimentacao();
                case 2 -> exibirHistorico();
                case 3 -> listarProdutos();
                case 0 -> System.out.println("Saindo do sistema...");
                default -> System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private void listarProdutos() {
        System.out.println("\n===== PRODUTOS DISPONÍVEIS =====");
        for (Produto p : produtoController.listarProdutos()) {
            System.out.printf("ID: %d | Nome: %s | Estoque: %d\n",
                    p.getId(), p.getNome(), p.getQuantidadeEstoque());
        }
    }

    private void registrarMovimentacao() {
        listarProdutos();
        System.out.print("Informe o ID do produto: ");
        int idProduto = lerInt();
        Produto produtoSelecionado = produtoController.buscarPorId(idProduto);

        if (produtoSelecionado == null) {
            System.out.println("Produto não encontrado.");
            return;
        }

        System.out.print("Tipo de movimentação (1=ENTRADA, 2=SAIDA): ");
        int tipoOpcao = lerInt();
        TipoMovimentacao tipo = (tipoOpcao == 1) ? TipoMovimentacao.ENTRADA : TipoMovimentacao.SAIDA;

        System.out.print("Quantidade: ");
        int quantidade = lerInt();

        if (quantidade <= 0) {
            System.out.println("Quantidade inválida.");
            return;
        }

        int idMovimentacao = movimentacaoController.listarMovimentacoes().size() + 1;
        Movimentacao m = new Movimentacao(idMovimentacao, tipo, quantidade, LocalDate.now(), produtoSelecionado);
        movimentacaoController.cadastrarMovimentacao(m);
    }

    private void exibirHistorico() {
        List<Movimentacao> lista = movimentacaoController.listarMovimentacoes();
        if (lista.isEmpty()) {
            System.out.println("Nenhuma movimentação registrada.");
        } else {
            System.out.println("\n===== HISTÓRICO DE MOVIMENTAÇÕES =====");
            for (Movimentacao m : lista) {
                System.out.println(m);;
                System.out.println("-------------------------");
            }
        }
    }

    private int lerInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Digite um número: ");
            }
        }
    }
}
