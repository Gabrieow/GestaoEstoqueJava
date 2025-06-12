package model;

public interface IGerenciavelEstoque {

    String adicionarEstoque(int quantidade);

    String removerEstoque(int quantidade);

    int getQuantidadeEstoque();
}