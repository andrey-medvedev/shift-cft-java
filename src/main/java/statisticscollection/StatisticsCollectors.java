package statisticscollection;

import java.util.List;
import java.util.Collections;

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

    // еще подумать над реализацией
    public void printFullStatistics() {
        Collections.sort(intList);
        Collections.sort(floatList);
        Collections.sort(strList);

        System.out.println("Полная статистика:");
        System.out.printf("Количество целых чисел, добавленных в файл = %d\n", intCount);
        System.out.println(intList);
        System.out.println(floatList);
        System.out.println(strList);
    }
}
