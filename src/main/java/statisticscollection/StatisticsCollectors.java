package statisticscollection;

import java.util.Comparator;
import java.util.List;

/**
 * Класс для сбора и вывода статистики по обработанным данным (целым числам, вещественным числам и строкам).
 */
public class StatisticsCollectors {

    private final int intCount;
    private final int floatCount;
    private final int strCount;

    private final List<Long> intList;
    private final List<Float> floatList;
    private final List<String> strList;

    /**
     * Конструктор класса для инициализации статистики.
     *
     * @param intCount   количество целых чисел
     * @param floatCount количество вещественных чисел
     * @param strCount   количество строк
     * @param intList    список целых чисел
     * @param floatList  список вещественных чисел
     * @param strList    список строк
     */
    public StatisticsCollectors(int intCount, int floatCount,
                                int strCount, List<Long> intList,
                                List<Float> floatList, List<String> strList) {
        this.intCount = intCount;
        this.floatCount = floatCount;
        this.strCount = strCount;
        this.intList = intList;
        this.floatList = floatList;
        this.strList = strList;
    }

    /**
     * Выводит краткую статистику о количестве добавленных элементов.
     */
    public void printShortStatistics() {
        System.out.println("Короткая статистика:");
        System.out.printf("Количество целых чисел, добавленных в файл = %d\n", intCount);
        System.out.printf("Количество вещественных чисел, добавленных в файл = %d\n", floatCount);
        System.out.printf("Количество строк, добавленных в файл = %d\n", strCount);
    }

    /**
     * Выводит полную статистику по числам и строкам, если они есть.
     */
    public void printFullStatistics() {
        System.out.println();
        printIntStatistics();
        printFloatStatistics();
        printStringStatistics();
    }

    /**
     * Выводит полную статистику по целым числам.
     */
    private void printIntStatistics() {
        System.out.println("Полная статистика для целых чисел:");
        System.out.printf("Количество целых чисел, добавленных в файл = %d\n", intCount);
        if (intCount > 0) {
            System.out.printf("""
                    Минимальное значение = %d
                    Максимальное значение = %d
                    Сумма чисел = %d
                    Среднее всех чисел = %.2f
                    
                    """,
                    intList.stream().min(Long::compareTo).orElse(0L),
                    intList.stream().max(Long::compareTo).orElse(0L),
                    intList.stream().mapToLong(Long::longValue).sum(),
                    intList.stream().mapToLong(Long::longValue).average().orElse(0.0f));
        }
    }

    /**
     * Выводит полную статистику по вещественным числам.
     */
    private void printFloatStatistics() {
        System.out.println("Полная статистика для вещественных чисел:");
        System.out.printf("Количество вещественных чисел, добавленных в файл = %d\n", floatCount);
        if(floatCount > 0) {
            System.out.printf("""
                    Минимальное значение = %.4f
                    Максимальное значение = %.4f
                    Сумма чисел = %.4f
                    Среднее всех чисел = %.4f
                    
                    """,
                    floatList.stream().min(Float::compareTo).orElse(0.0f),
                    floatList.stream().max(Float::compareTo).orElse(0.0f),
                    floatList.stream().mapToDouble(Float::floatValue).sum(),
                    floatList.stream().mapToDouble(Float::floatValue).average().orElse(0.0f));
        }
    }

    /**
     * Выводит полную статистику по строкам.
     */
    private void printStringStatistics() {
        System.out.println("Полная статистика для строк:");
        System.out.printf("Количество строк, добавленных в файл = %d\n", strCount);
        if(strCount > 0) {
            System.out.printf("""
                    Длинна самой короткой строки = %d
                    Длина самой длинной строки = %d
                    
                    """,
                    strList.stream().map(String::trim).min(Comparator.comparingInt(String::length)).orElse("").length(),
                    strList.stream().map(String::trim).max(Comparator.comparingInt(String::length)).orElse("").length());
        }
    }
}
