package controller;

import model.Fornecedor;
import java.util.ArrayList;
import java.util.List;

import factory.FornecedorFactory;

public class FornecedorController {
    private List<Fornecedor> fornecedores;

    public FornecedorController() {
        this.fornecedores = new ArrayList<>();
    }

    public String registrarFornecedor(String nome, String telefone, String endereco, int id, String cnpj, String razaoSocial) {
        try {
            Fornecedor fornecedor = FornecedorFactory.criarFornecedor(nome, telefone, endereco, id, cnpj, razaoSocial);
            fornecedores.add(fornecedor);
            return fornecedor.registrar();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criar fornecedor: " + e.getMessage(), e);
        }
    }

    public String atualizarFornecedor(int id, String nome, String telefone, String endereco, String cnpj, String razaoSocial) {
        try {
            Fornecedor fornecedorExistente = null;
            for (Fornecedor fornecedor : fornecedores) {
                if (fornecedor.getId() == id) {
                    fornecedorExistente = fornecedor;
                    break;
                }
            }
            if (fornecedorExistente == null) {
                throw new IllegalArgumentException("Fornecedor com ID " + id + " não encontrado.");
            }
            Fornecedor fornecedorAtualizado = FornecedorFactory.criarFornecedor(
                nome, telefone, endereco, id, cnpj, razaoSocial
            );
            if (fornecedorAtualizado == null) {
                throw new NullPointerException("Fornecedor atualizado não pode ser nulo.");
            }
            
            int index = fornecedores.indexOf(fornecedorExistente);
            fornecedores.set(index, fornecedorAtualizado);
            return fornecedorAtualizado.atualizar();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao atualizar fornecedor: " + e.getMessage(), e);
        }
    }

    public String excluirFornecedor(int id) {
        Fornecedor fornecedor = fornecedores.stream()
                .filter(f -> f.getId() == id)
                .findFirst()
                .orElse(null);

        if (fornecedor == null) {
            throw new IllegalArgumentException("Fornecedor com id " + id + " não encontrado.");
        }
        
        fornecedores.remove(fornecedor);
        return fornecedor.excluir();
    }

    public List<Fornecedor> listarFornecedores() {
        return new ArrayList<>(fornecedores);
    }
}
