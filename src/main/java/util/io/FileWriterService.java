/*
    Класс предназначен для:
    - создания текстовых файлов с целыми/вещественными числа и/или строками
    - дозаписывание данных в уже существующие файлы
*/

package util.io;

import java.io.IOException;
import java.nio.file.*;

public class FileWriterService {

    private static final String path = "src/main/resources";

    public FileWriterService() {}

    public static void createFile(String fileName) {
        try {
            Path testFile = Files.createFile(Paths.get(path + fileName));
            System.out.printf("Был ли файл успешно создан? Ответ: %s\n", Files.exists(testFile));
        } catch (FileAlreadyExistsException e) {
            System.out.printf("Файл с названием %s уже существует, введите другое имя файла\n", fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}