/*
    Класс предназначен для:
    - создания текстовых файлов с целыми/вещественными числа и/или строками
    - дозаписывание данных в уже существующие файлы
*/

package fileprocessing;

import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public final class FileWriterService {

    private FileWriterService() {}

    public static void createFile(String path, String fileName) {
        try {
            Path createFile = Files.createFile(Paths.get(path + fileName + ".txt"));
            System.out.printf("Был ли файл успешно создан? Ответ: %s\n", Files.exists(createFile));
        } catch (IOException e) {
            System.out.println("Что-то прошло не так " + e.getMessage());
        }
    }

    public static void writeData(String path, String fileName, List<?> data) {
        Path p = Paths.get(path + fileName);
        try {
            for (Object element : data) {
                Files.writeString(p, String.format("%s\n", element), StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            }
        } catch (IOException e) {
            System.out.println("Что-то прошло не так " + e.getMessage());
        }
    }
}