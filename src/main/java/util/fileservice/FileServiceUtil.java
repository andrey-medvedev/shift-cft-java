package util.fileservice;

import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Утилитный класс для работы с файлами. Позволяет читать и записывать данные в файлы.
 */
public final class FileServiceUtil {

    private FileServiceUtil() {}

    /**
     * Записывает данные в файл.
     *
     * @param path       путь к директории, где будет создан файл.
     * @param fileName   имя файла, в который будут записаны данные.
     * @param data       список данных, которые будут записаны в файл.
     * @param appendMode если {@code true}, данные будут добавлены в конец файла; если {@code false}, файл будет перезаписан.
     */
    public static void writeData(String path, String fileName, List<?> data, boolean appendMode) {
        File file = new File(path, fileName);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, appendMode))) {
            List<String> stringData = data.stream().map(Object::toString).toList();
            for (String line : stringData) {
                writer.write(line);
                writer.newLine();
            }
            System.out.printf("Данные записаны в файл %s.\n", fileName);
        } catch (IOException e) {
            System.err.println("Ошибка при записи в файл: " + e.getMessage());
        }
    }

    /**
     * Читает содержимое файла и возвращает список строк.
     *
     * @param fileName имя файла, который нужно прочитать.
     * @return список строк, содержащих данные из файла, или пустой список, если файл не найден или произошла ошибка.
     */
    public static List<String> readFile(String fileName) {
        ClassLoader classLoader = FileServiceUtil.class.getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
            if (inputStream == null) {
                System.out.println("Файл не найден: " + fileName);
                return Collections.emptyList();
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                System.out.printf("Чтение файла %s прошло успешно\n", fileName);
                return reader.lines().collect(Collectors.toList());
            }

        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + fileName + e.getMessage());
            return Collections.emptyList();
        }
    }
}