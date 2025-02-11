/*
    Класс предназначен для:
    - вывода уже существующих файлов из дирректории
    - читать данные из файлов по строкам
    - вывод данных из файла по строкам
    - определять тип строки и передавать обработчику (скорее для класса, обрабатывающего аргументы с консоли)
*/

package util.io;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class FileReaderService {

    private final String path;

    public FileReaderService(String path) {
        this.path = path;
    }

    public void returnExistingFile() {
        Path p = Paths.get(path);
        try (DirectoryStream<Path> files = Files.newDirectoryStream(p)) {
            for(Path file : files) {
                System.out.println(file);
            }
        } catch (IOException e) {
            System.out.println("Что-то прошло не так " + e.getMessage());
        }
    }

    public void readFile(String fileName) {
        Path p = Paths.get(path + fileName + ".txt");
        try {
            List<String> list = Files.readAllLines(p);
            for(String str : list) {
                System.out.println(str);
            }
        } catch (IOException e) {
            System.out.println("Что-то прошло не так " + e.getMessage());
        }
    }
}
