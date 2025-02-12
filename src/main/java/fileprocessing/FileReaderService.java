/*
    Класс предназначен для:
    - вывода уже существующих файлов из дирректории
    - читать данные из файлов по строкам
    - вывод данных из файла по строкам
    - определять тип строки и передавать обработчику (скорее для класса, обрабатывающего аргументы с консоли)
*/

package fileprocessing;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public final class FileReaderService {

    private FileReaderService() {}

    public static void returnExistingFile(String path) {
        Path p = Paths.get(path);
        try (DirectoryStream<Path> files = Files.newDirectoryStream(p)) {
            for(Path file : files) {
                System.out.println(file);
            }
        } catch (IOException e) {
            System.out.println("Что-то прошло не так " + e.getMessage());
        }
    }

    public static List<String> readFile(String path, String fileName) {
        Path p = Paths.get(path + fileName);
        try {
            List<String> list = Files.readAllLines(p);
            return list;
        } catch (IOException e) {
            System.out.println("Что-то прошло не так " + e.getMessage());
        }
        return null;
    }
}
