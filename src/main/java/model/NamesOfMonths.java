package model;

public enum NamesOfMonths {
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

    private final String MnthName;

    NamesOfMonths (String NameMnth) {
        this.MnthName = NameMnth;
    }

    public String getName() {
        return MnthName;
    }
}
