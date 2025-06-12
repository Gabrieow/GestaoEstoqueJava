package controller;

import java.util.ArrayList;
import java.util.List;
import model.Movimentacao;
import model.MovimentacaoDAO;
import model.Produto;
import model.TipoMovimentacao;

public class MovimentacaoController {
    private List<Movimentacao> movimentacoes;
    private ProdutoController produtoController;

    public MovimentacaoController(ProdutoController produtoController) {
        this.produtoController = produtoController;
        movimentacoes = MovimentacaoDAO.carregarMovimentacoes();
        if (movimentacoes == null) { 
            movimentacoes = new ArrayList<>(); 
        }
    }

    public String cadastrarMovimentacao(Movimentacao movimentacao) {
        if (movimentacao.getProduto() == null) {
            return "Erro: Produto não pode ser nulo na movimentação.";
        }
        if (movimentacao.getQuantidade() <= 0) {
            return "Erro: Quantidade da movimentação deve ser maior que zero.";
        }

        try {
            Produto produtoParaAtualizar = produtoController.buscarPorId(movimentacao.getProduto().getId());

            if (produtoParaAtualizar == null) {
                return "Erro: Produto não encontrado para atualização de estoque.";
            }

            String resultadoEstoque;

            if (movimentacao.getTipo() == TipoMovimentacao.ENTRADA) {
                resultadoEstoque = produtoParaAtualizar.adicionarEstoque(movimentacao.getQuantidade());
            } else { 
                resultadoEstoque = produtoParaAtualizar.removerEstoque(movimentacao.getQuantidade());
            }

            if (resultadoEstoque.startsWith("Erro")) {
                return resultadoEstoque;
            }

            produtoController.salvarProdutosInternamente(); 

            movimentacoes.add(movimentacao);
            MovimentacaoDAO.salvarMovimentacoes(movimentacoes);
            return "Movimentação cadastrada com sucesso! " + movimentacao;
        } catch (IllegalArgumentException e) {
            return "Erro ao cadastrar movimentação: " + e.getMessage();
        } catch (Exception e) {
            return "Erro inesperado ao cadastrar movimentação: " + e.getMessage();
        }
    }

    public List<Movimentacao> listarMovimentacoes() {
        return new ArrayList<>(movimentacoes);
    }

    public String removerMovimentacao(Movimentacao movimentacao) {
        try {
            if (movimentacoes.remove(movimentacao)) {
                Produto produto = movimentacao.getProduto();
                int quantidade = movimentacao.getQuantidade();

                if (movimentacao.getTipo() == TipoMovimentacao.ENTRADA) {
                    String resultadoRemocao = produto.removerEstoque(quantidade);
                    if (resultadoRemocao.startsWith("Erro")) {
                        return "Erro ao reverter estoque após remoção da movimentação de entrada: " + resultadoRemocao;
                    }
                } else if (movimentacao.getTipo() == TipoMovimentacao.SAIDA) {
                    produto.adicionarEstoque(quantidade);
                }

                MovimentacaoDAO.salvarMovimentacoes(movimentacoes);
                return "Movimentação removida com sucesso. ID: " + movimentacao.getId();
            } else {
                return "Movimentação não encontrada.";
            }
        } catch (IllegalArgumentException e) {
            return "Erro ao remover movimentação: " + e.getMessage();
        } catch (Exception e) {
            return "Erro inesperado ao remover movimentação: " + e.getMessage();
        }
    }

    public List<Movimentacao> buscarPorProduto(Produto produto) {
        List<Movimentacao> resultado = new ArrayList<>();
        for (Movimentacao m : movimentacoes) {
            if (m.getProduto() != null && m.getProduto().getId() == produto.getId()) {
                resultado.add(m);
            }
        }
        return resultado;
    }
}