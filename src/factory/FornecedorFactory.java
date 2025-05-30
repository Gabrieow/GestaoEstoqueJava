package factory;

import exception.StringVaziaException;
import model.Fornecedor;

public abstract class FornecedorFactory {

    public static Fornecedor criarFornecedor(
            String nome, 
            String telefone, 
            String endereco, 
            int id, 
            String cnpj, 
            String razaoSocial) throws Exception {

        if (nome == null || nome.trim().isEmpty()) {
            throw new StringVaziaException("Nome do fornecedor não pode ser vazio", 20);
        }
        if (telefone == null || telefone.trim().isEmpty()) {
            throw new StringVaziaException("Telefone do fornecedor não pode ser vazio", 21);
        }
        if (endereco == null || endereco.trim().isEmpty()) {
            throw new StringVaziaException("Endereço do fornecedor não pode ser vazio", 22);
        }
        if (id < 0) {
            throw new IllegalArgumentException("O ID precisa ser maior ou igual a 0");
        }
        if (cnpj == null || cnpj.trim().isEmpty()) {
            throw new StringVaziaException("CNPJ do fornecedor não pode ser vazio", 23);
        }
        if (razaoSocial == null || razaoSocial.trim().isEmpty()) {
            throw new StringVaziaException("Razão Social do fornecedor não pode ser vazia", 24);
        }

        return new Fornecedor(nome, telefone, endereco, id, cnpj, razaoSocial);
    }
}
