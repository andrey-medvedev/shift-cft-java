package fileprocessing;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Утилитарный класс для работы с файлами.
 * <p>
 * Содержит методы для чтения и записи данных в файлы.
 * </p>
 */
public final class FileServiceUtil {

    /**
     * Приватный конструктор, предотвращающий создание экземпляров класса.
     * <p>
     * Класс является утилитарным и не предназначен для создания объектов.
     * </p>
     */
    private FileServiceUtil() {}

    /**
     * Записывает данные в файл.
     *
     * @param path      путь к директории, где будет создан файл
     * @param fileName  имя выходного файла
     * @param data      список данных для записи
     * @param appendMode  флаг, указывающий режим дозаписи (если {@code true}, данные добавляются в конец файла)
     */
    public static void writeData(String path, String fileName, List<?> data, boolean appendMode) {
        File file = new File(path, fileName);

        // Проверка существования директории
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs(); // Создает все отсутствующие директории
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, appendMode))) {
            List<String> stringData = data.stream().map(Object::toString).toList();
            for (String line : stringData) {
                writer.write(line);
                writer.newLine();
            }
            System.out.printf("Данные записаны в файл %s.\n", fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
            e.printStackTrace(); // Выводит стек ошибки
        }
    }

    /**
     * Считывает содержимое файла построчно.
     *
     * @param fileName имя входного файла
     * @return список строк, содержащих данные из файла, или пустой список в случае ошибки
     */
    public static List<String> readFile(String fileName) {
        // Получаем ClassLoader
        ClassLoader classLoader = FileServiceUtil.class.getClassLoader();

        // Пробуем получить ресурс
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
            // Проверка, если файл не найден
            if (inputStream == null) {
                System.out.println("Файл не найден: " + fileName);
                return Collections.emptyList();
            }

            // Чтение данных из файла
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                System.out.println("Чтение файла прошло успешно");
                return reader.lines().collect(Collectors.toList());
            }

        } catch (IOException e) {
            // Обработка ошибок при чтении
            System.out.println("Ошибка при чтении файла: " + fileName);
            e.printStackTrace(); // Можно использовать логгер вместо printStackTrace для реальных проектов
            return Collections.emptyList();
        }
    }
}