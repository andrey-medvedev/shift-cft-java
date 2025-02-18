package util.validator;

import arguments.ArgumentsParser;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * Утилитарный класс для валидации аргументов командной строки.
 * Проверяет корректность введенных аргументов, при необходимости заменяет их на значения по умолчанию.
 */
public final class ArgumentsValidatorUtil {

    private ArgumentsValidatorUtil() {}

    /**
     * Валидирует аргументы командной строки.
     * Проверяет входные файлы, путь вывода, префикс имени файла и флаги статистики.
     *
     * @param arguments объект {@link ArgumentsParser}, содержащий аргументы командной строки.
     * @return {@code true}, если все аргументы валидны, иначе {@code false}.
     */
    public static boolean validate(ArgumentsParser arguments) {
        try {
            arguments.setInputFiles(validateInputFiles(arguments.getInputFiles()));
            arguments.setOutputPath(validateOutputPath(arguments.getOutputPath()));
            arguments.setPrefix(validateFilePrefix(arguments.getPrefix()));
            validateStatisticsFlag(arguments);
            return true;
        } catch (Exception e) {
            System.out.println("Ошибка валидации: " + e.getMessage());
            return false;
        }
    }

    /**
     * Проверяет список входных файлов. Если список пустой, устанавливаются файлы по умолчанию.
     * Если файл не указан, заменяется на `in1.txt` и `in2.txt`.
     * Если файл не найден в ресурсах, заменяется на `in1.txt`.
     *
     * @param inputFiles список путей к входным файлам.
     * @return валидированный список входных файлов.
     */
    private static List<String> validateInputFiles(List<String> inputFiles) {
        if (inputFiles == null || inputFiles.isEmpty()) {
            System.out.println("""
                    Предупреждение: Не указаны входные файлы.
                    По умолчанию используются файлы: in1.txt и in2.txt.
                    """);
            assert inputFiles != null;
            inputFiles.add("in1.txt");
            inputFiles.add("in2.txt");
        }
        for (String filePath : inputFiles) {
            InputStream inputStream = ArgumentsValidatorUtil.class.getClassLoader().getResourceAsStream(filePath);
            if (inputStream == null) {
                System.out.printf("""
                        Предупреждение: Входной файл %s не найден в ресурсах.
                        По умолчанию используется файл: in1.txt.
                        """, filePath);
                inputFiles.set(inputFiles.indexOf(filePath), "in1.txt");
            }
        }
        return inputFiles;
    }

    /**
     * Проверяет корректность пути вывода.
     * Если путь не указан или некорректен, используется папка по умолчанию (`./resources/`) в target.
     *
     * @param outputPath путь для сохранения выходных файлов.
     * @return валидированный путь к выходной директории.
     */
    // сохраняет в разные места при запуске из ide или консоли
    private static String validateOutputPath(String outputPath) {
        if (outputPath == null || outputPath.isEmpty()) {
            String defaultPath = new File("resources/").getAbsolutePath();
            System.out.printf("Предупреждение: Не указан путь для вывода. Используется %s\n", defaultPath);
            return defaultPath;
        }

        File dir = new File(outputPath);
        if (!dir.exists() || !dir.isDirectory()) {
            String defaultPath = new File("resources/").getAbsolutePath();
            System.out.printf("Предупреждение: Указанный путь не является директорией. Используется %s\n", defaultPath);
            return defaultPath;
        }

        return dir.getAbsolutePath() + File.separator;
    }

    /**
     * Проверяет префикс выходного файла.
     * Если префикс пустой или не указан, используется пустая строка.
     *
     * @param prefix префикс имени выходного файла.
     * @return валидированный префикс.
     */
    private static String validateFilePrefix(String prefix) {
        if (prefix == null || prefix.trim().isEmpty()) {
            System.out.println("""
                    Предупреждение: Префикс имени файла не установлен.
                    По умолчанию используется пустой префикс.
                    """);
            return "";
        }
        return prefix;
    }

    /**
     * Проверяет флаги статистики.
     * Если активны одновременно флаги краткой и полной статистики, по умолчанию выбирается краткая.
     *
     * @param arguments объект {@link ArgumentsParser}, содержащий флаги статистики.
     */
    private static void validateStatisticsFlag(ArgumentsParser arguments) {
        if (arguments.isShortStatistics() && arguments.isFullStatistics()) {
            System.out.println("""
                    Предупреждение: Нельзя одновременно использовать флаги краткой и полной статистики.
                    По умолчанию используется краткая статистика.
                    """);
            arguments.setShortStatistics(true);
            arguments.setFullStatistics(false);
        }
    }
}