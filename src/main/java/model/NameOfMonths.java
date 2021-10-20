package model;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public enum NameOfMonths {
    ЯНВАРЬ("Январь"),
    ФЕВРАЛЬ("Февраль"),
    МАРТ("Март"),
    АПРЕЛЬ("Апрель"),
    МАЙ("Май"),
    ИЮНЬ("Июнь"),
    ИЮЛЬ("Июль"),
    АВГУСТ("Август"),
    СЕНТЯБРЬ("Сентябрь"),
    ОКТЯБРЬ("Октябрь"),
    НОЯБРЬ("Ноябрь"),
    ДЕКАБРЬ("Декабрь"),
    INCORRECT("IncorrectName");

    private final String mnthName;

    NameOfMonths(String nameMnth) {
        this.mnthName = nameMnth;
    }


    // TODO: 19.10.2021 добиться О(1)
    public static NameOfMonths getNameOfMonthObject(String monthName) {
        try {
            return NameOfMonths.valueOf(monthName.toUpperCase(Locale.ROOT));
        }
        catch (Exception e){
            return NameOfMonths.INCORRECT;
        }
    }

    // TODO: 19.10.2021 return aslist winter month
    public static List<NameOfMonths> NameOfWinterMonths(){
        return Arrays.asList(ДЕКАБРЬ, ЯНВАРЬ, ФЕВРАЛЬ);
    }

    public String getName() {
        return mnthName;
    }
}
