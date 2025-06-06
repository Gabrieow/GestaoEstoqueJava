package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogUtil {

    private static final String LOG_FILE = "log.txt";
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void registrarLog(String mensagem) {
        String timestamp = LocalDateTime.now().format(formatter);
        String logEntry = String.format("[%s] %s", timestamp, mensagem);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE, true))) {
            writer.write(logEntry);
            writer.newLine();
        } catch (IOException e) {
            System.err.println("Erro ao escrever no log: " + e.getMessage());
        }
    }
}
