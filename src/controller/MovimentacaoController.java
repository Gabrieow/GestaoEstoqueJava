package controller;

import java.util.ArrayList;
import java.util.List;
import model.Movimentacao;
import model.MovimentacaoDAO;
import model.Produto;
import model.TipoMovimentacao;

public class MovimentacaoController {
    private List<Movimentacao> movimentacoes;

    public MovimentacaoController() {
        movimentacoes = MovimentacaoDAO.carregarMovimentacoes();
        if (movimentacoes == null) {
            movimentacoes = new ArrayList<>();
        }
    }

    public String cadastrarMovimentacao(Movimentacao movimentacao) {
        if (movimentacao.getProduto() == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }
        if (movimentacao.getQuantidade() <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }

        try {
            movimentacoes.add(movimentacao);
            MovimentacaoDAO.salvarMovimentacoes(movimentacoes);
            return "Movimentação cadastrada: " + movimentacao;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Erro ao cadastrar movimentação: " + e);
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
                    produto.removerEstoque(quantidade);
                } else if (movimentacao.getTipo() == TipoMovimentacao.SAIDA) {
                    produto.adicionarEstoque(quantidade);
                }

                MovimentacaoDAO.salvarMovimentacoes(movimentacoes);
                return "Movimentação removida com sucesso. ID: " + movimentacao.getId();
            } else {
                return "Movimentação não encontrada.";
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Erro ao remover movimentação" + e.getMessage());
        }
    }

    public List<Movimentacao> buscarPorProduto(Produto produto) {
        List<Movimentacao> resultado = new ArrayList<>();
        for (Movimentacao movimentacao : movimentacoes) {
            if (movimentacao.getProduto().equals(produto)) {
                resultado.add(movimentacao);
            }
        }
        return resultado;
    }
}