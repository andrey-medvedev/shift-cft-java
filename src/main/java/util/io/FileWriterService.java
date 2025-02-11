/*
    Класс предназначен для:
    - создания текстовых файлов с целыми/вещественными числа и/или строками
    - дозаписывание данных в уже существующие файлы
*/

package util.io;

import java.io.IOException;
import java.nio.file.*;

public class FileWriterService {

    private final String path;

    public FileWriterService(String path) {
        this.path = path;
    }

    public void createFile(String fileName) {
        try {
            Path createFile = Files.createFile(Paths.get(path + fileName + ".txt"));
            System.out.printf("Был ли файл успешно создан? Ответ: %s\n", Files.exists(createFile));
        } catch (IOException e) {
            System.out.println("Что-то прошло не так " + e.getMessage());
        }
    }

    public void writeData(String fileName, String data) {
        try {
            Files.writeString(Paths.get(path + fileName + ".txt"),
                    String.format("%s\n", data), StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println("Что-то прошло не так " + e.getMessage());
        }
    }
}