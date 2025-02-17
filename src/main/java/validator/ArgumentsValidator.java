package validator;

import arguments.ArgumentsParser;

import java.io.File;
import java.io.InputStream;
import java.util.List;

public class ArgumentsValidator {

    public static void validate(ArgumentsParser arguments) {
        arguments.setInputFiles(validateInputFiles(arguments.getInputFiles()));
        arguments.setOutputPath(validateOutputPath(arguments.getOutputPath()));
        arguments.setPrefix(validateFilePrefix(arguments.getPrefix()));
        validateStatisticsFlag(arguments);
    }

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
            // Использование ClassLoader для загрузки файла из resources
            InputStream inputStream = ArgumentsValidator.class.getClassLoader().getResourceAsStream(filePath);
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

    private static String validateOutputPath(String outputPath) {
        if (outputPath == null || outputPath.isEmpty()) {
            System.out.println("""
                    Предупреждение: Не указан путь для вывода.
                    По умолчанию используется папка: ./resources/
                    """);
            return "./resources/";
        }

        File dir = new File(outputPath);
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("""
                    Предупреждение: Указанный путь для вывода не является директорией.
                    По умолчанию используется папка: ./resources/
                    """);
            return "./resources/";
        }
        return outputPath + File.separator;
    }

    private static String validateFilePrefix(String prefix) {
        if (prefix == null || prefix.trim().isEmpty()) {
            System.out.println("""
                    Предупреждение: Префикс имени файла не должен быть пустым.
                    По умолчанию используется пустой префикс.
                    """);
            return "";
        }
        return prefix;
    }

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