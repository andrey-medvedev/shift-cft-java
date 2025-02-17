/*
    Класс предназначен для:
    - создания текстовых файлов с целыми/вещественными числа и/или строками
    - дозаписывание данных в уже существующие файлы
*/

package fileprocessing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public final class FileService {

    private FileService() {}

    public static void createFile(String path, String fileName) {
        try {
            Path createFile = Files.createFile(Paths.get(path + fileName + ".txt"));
            System.out.printf("Был ли файл успешно создан? Ответ: %s\n", Files.exists(createFile));
        } catch (IOException e) {
            System.out.println("Что-то прошло не так " + e.getMessage());
        }
    }

    public static void writeData(String path, String fileName, List<?> data, boolean appendMode) {
        Path p = Paths.get(path + fileName);
        List<String> stringData = new ArrayList<>();
        for (Object obj : data) {
            stringData.add(obj.toString());  // Преобразуем объект в строку
        }
        try {
            if(appendMode) {
                Files.write(p, stringData, StandardOpenOption.APPEND, StandardOpenOption.CREATE);
            } else {
                Files.write(p, stringData, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
            }
            System.out.println("Создание файла прошло успешно");
        } catch (IOException e) {
            System.out.println("Что-то прошло не так при записи в файл " + e.getMessage());
        }
    }

    public static List<String> readFile(String fileName) {

        ClassLoader classLoader = FileService.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName);
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            System.out.println("Чтение файла прошло успешно");
            return reader.lines().collect(Collectors.toList());
        } catch (IOException | NullPointerException e) {
            System.out.println("Ошибка при чтении файла: " + fileName);
            return Collections.emptyList(); // Возвращаем пустой список вместо null
        }

        // Path p = Paths.get(path + fileName);
        // try {
        //     List<String> list = Files.readAllLines(p);
        //     return list;
        // } catch (IOException e) {
        //     System.out.println("Что-то прошло не так при чтении файла " + e.getMessage());
        // }
        // return null;
    }
}