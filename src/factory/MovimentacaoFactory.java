package factory;

import java.time.LocalDate;
import model.Movimentacao;
import model.Produto;
import model.TipoMovimentacao;

public class MovimentacaoFactory {

    public static Movimentacao criarMovimentacao(
            int id,
            TipoMovimentacao tipo,
            int quantidade,
            LocalDate data,
            Produto produto
    ) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser nulo.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        }
        if (data == null) {
            data = LocalDate.now();
        }

        return new Movimentacao(id, tipo, quantidade, data, produto);
    }
}
