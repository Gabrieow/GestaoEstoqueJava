package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FornecedorDAO {
    private static final String FILE_NAME = "fornecedores.dat";

    public static void salvarFornecedores(List<Fornecedor> fornecedores) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(fornecedores);
        } catch (IOException e) {
            System.err.println("Erro ao salvar fornecedores: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Fornecedor> carregarFornecedores() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Fornecedor>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar fornecedores: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}