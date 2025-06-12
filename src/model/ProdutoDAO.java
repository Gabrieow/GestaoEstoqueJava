package model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {
    private static final String FILE_NAME = "produtos.dat";

    public static void salvarProdutos(List<Produto> produtos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(produtos);
        } catch (IOException e) {
            System.err.println("Erro ao salvar produtos: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Produto> carregarProdutos() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Produto>) ois.readObject();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar produtos: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}