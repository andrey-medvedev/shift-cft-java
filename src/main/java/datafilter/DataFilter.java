package datafilter;

import java.util.ArrayList;
import java.util.List;

public class DataFilter {

    private List<Integer> intList;
    private List<Float> floatList;
    private List<String> strList;

    public DataFilter() {
        this.intList = new ArrayList<>();
        this.floatList = new ArrayList<>();
        this.strList = new ArrayList<>();
    }

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

    private boolean isInteger(String arg) {
        try {
            Integer.parseInt(arg);
            return true;
        } catch (NumberFormatException e) {
            // System.out.printf("Строку %s не является целым числом\n", arg);
            return false;
        }
    }

    private boolean isFloat(String arg) {
        try {
            Float.parseFloat(arg);
            return true;
        } catch (NumberFormatException e) {
            // System.out.printf("Строку %s не является вещественным числом\n", arg);
            return false;
        }
    }

    public List<Integer> getIntList() {
        return this.intList;
    }

    public List<Float> getFloatList() {
        return this.floatList;
    }

    public List<String> getStrList() {
        return this.strList;
    }
}

