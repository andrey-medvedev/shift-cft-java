package datafilter;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс для фильтрации данных. Разделяет входные строки на целые числа, числа с плавающей точкой и строки.
 */
@Getter
public class DataFilter {

    private final List<Integer> intList;
    private final List<Float> floatList;
    private final List<String> strList;

    /**
     * Создает экземпляр {@code DataFilter} и инициализирует списки для хранения различных типов данных.
     */
    public DataFilter() {
        this.intList = new ArrayList<>();
        this.floatList = new ArrayList<>();
        this.strList = new ArrayList<>();
    }

    /**
     * Фильтрует переданные строки, распределяя их по типам: целые числа, числа с плавающей точкой и строки.
     *
     * @param args список строковых значений, подлежащих фильтрации.
     */
    public void filterValues(List<String> args) {
        for(String arg : args) {
            if(isInteger(arg)) {
                intList.add(Integer.parseInt(arg));
            } else if (isFloat(arg)) {
                floatList.add(Float.parseFloat(arg));
            } else {
                strList.add(arg);
            }
        }
    }

    /**
     * Проверяет, является ли переданная строка целым числом.
     *
     * @param arg строка для проверки.
     * @return {@code true}, если строка является целым числом, иначе {@code false}.
     */
    // обработка больших чисел
    private boolean isInteger(String arg) {
        try {
            Integer.parseInt(arg);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Проверяет, является ли переданная строка числом с плавающей запятой.
     *
     * @param arg строка для проверки.
     * @return {@code true}, если строка является числом с плавающей запятой, иначе {@code false}.
     */
    private boolean isFloat(String arg) {
        try {
            Float.parseFloat(arg);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

