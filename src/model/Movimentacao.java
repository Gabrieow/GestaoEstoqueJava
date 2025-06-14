package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Movimentacao implements Serializable {

    private int id;
    private TipoMovimentacao tipo;
    private int quantidade;
    private LocalDate data;
    private Produto produto;

    public Movimentacao(int id, TipoMovimentacao tipo, int quantidade, LocalDate data, Produto produto) {
        this.id = id;
        this.tipo = tipo;
        this.quantidade = quantidade;
        this.data = data;
        this.produto = produto;

    }

    public Produto getProduto() {
        return produto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public LocalDate getData() {
        return data;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format(
            "Movimentação #%d\nTipo: %s\nQuantidade: %d\nData: %s\nProduto: %s\n",
            id,
            tipo,
            quantidade,
            data.toString(),
            produto != null ? produto.getNome() : "Produto não informado"
        );
    }

}