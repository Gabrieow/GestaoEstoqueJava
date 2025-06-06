package model;

import java.io.*;
import java.util.List;

public class MovimentacaoDAO {
    private static final String FILE_NAME = "movimentacoes.dat";

    public static void salvarMovimentacoes(List<Movimentacao> movimentacoes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(movimentacoes);
        } catch (IOException e) {
            System.err.println("Erro ao salvar movimentações: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Movimentacao> carregarMovimentacoes() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Movimentacao>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao carregar movimentações: " + e.getMessage());
            return null;
        }
    }
}