package model;

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

    public static NameOfMonths getNameOfMonthObject(String monthName) {
        NameOfMonths resultNameOfMonthName = NameOfMonths.INCORRECT;
        for (NameOfMonths CounterOfMonthName : NameOfMonths.values()) {
            if (CounterOfMonthName.getName().equals(monthName)) {
                resultNameOfMonthName = CounterOfMonthName;
                break;
            }
        }
        return resultNameOfMonthName;
    }

    public String getName() {
        return mnthName;
    }
}
