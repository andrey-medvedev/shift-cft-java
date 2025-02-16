package statisticscollection;

import java.util.Comparator;
import java.util.List;

public class StatisticsCollectors {

    private int intCount;
    private int floatCount;
    private int strCount;

    private List<Integer> intList;
    private List<Float> floatList;
    private List<String> strList;

    public StatisticsCollectors(int intCount, int floatCount,
                                int strCount, List<Integer> intList,
                                List<Float> floatList, List<String> strList) {
        this.intCount = intCount;
        this.floatCount = floatCount;
        this.strCount = strCount;
        this.intList = intList;
        this.floatList = floatList;
        this.strList = strList;
    }

    public void printShortStatistics() {
        System.out.println("Короткая статистика:");
        System.out.printf("Количество целых чисел, добавленных в файл = %d\n", intCount);
        System.out.printf("Количество вещественных чисел, добавленных в файл = %d\n", floatCount);
        System.out.printf("Количество строк, добавленных в файл = %d\n", strCount);
    }

    public void printFullStatistics() {
        printIntStatistics();
        System.out.println();
        printFloatStatistics();
        System.out.println();
        printStringStatistics();
    }

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
                    intList.stream().min(Integer::compareTo).get(),
                    intList.stream().max(Integer::compareTo).get(),
                    intList.stream().mapToInt(Integer::intValue).sum(),
                    intList.stream().mapToInt(Integer::intValue).average().getAsDouble());
        }
    }

    private void printFloatStatistics() {
        System.out.println("Полная статистика для вещественных чисел:");
        System.out.printf("Количество вещественных чисел, добавленных в файл = %d\n", floatCount);
        if(floatCount > 0) {
            System.out.printf("""
                    Минимальное значение = %.2f
                    Максимальное значение = %.2f
                    Сумма чисел = %.2f
                    Среднее всех чисел = %.2f
                    """,
                    floatList.stream().min(Float::compareTo).get(),
                    floatList.stream().max(Float::compareTo).get(),
                    floatList.stream().mapToDouble(Float::floatValue).sum(),
                    floatList.stream().mapToDouble(Float::floatValue).average().getAsDouble());
        }
    }

    private void printStringStatistics() {
        System.out.println("Полная статистика для строк:");
        System.out.printf("Количество строк, добавленных в файл = %d\n", strCount);
        if(strCount > 0) {
            System.out.printf("""
                    Длинна самой короткой строки = %s
                    Длина самой длинной строки = %s
                    """,
                    strList.stream().map(String::length).min(Comparator.comparing(Integer::intValue)).get(),
                    strList.stream().map(String::length).max(Comparator.comparing(Integer::intValue)).get());
        }
    }
}
