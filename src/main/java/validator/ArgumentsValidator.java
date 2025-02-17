package validator;

import arguments.ArgumentsParser;

import java.io.File;
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
            File file = new File("cft-test-task/src/main/resources/" + filePath);
            if (!file.exists() || !file.isFile()) {
                System.out.printf("""
                        Предупреждение: Входной файл %s не найден или не является файлом.
                        По умолчанию используется файл: in1.txt.
                        
                        """, filePath );
                inputFiles.set(inputFiles.indexOf(filePath), "in1.txt");
            }
        }
        return inputFiles;
    }

    // попробовать установить абсолютный путь
    private static String validateOutputPath(String outputPath) {
        if (outputPath == null || outputPath.isEmpty()) {
            System.out.println("""
                    Предупреждение: Не указан путь для вывода.
                    По умолчанию используется папка: ./resources/
                    """);
            return "cft-test-task/src/main/resources/";
        }

        File dir = new File(outputPath);
        if (!dir.exists() || !dir.isDirectory()) {
            System.out.println("""
                    Предупреждение: Указанный путь для вывода не является директорией.
                    По умолчанию используется папка: ./resources/
                    """);
            return "cft-test-task/src/main/resources/";
        }
        return outputPath + "\\";
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

    private static void validateStatisticsFlag (ArgumentsParser arguments) {
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
