package model;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public enum NameOfCurrency {
    EUR("Euro"),
    USD("United States Dollar"),
    RUB("Ruble"),
    INCORRECT("IncorrectName");

    private final String crrncName;

    NameOfCurrency(String crrncMnth) {
        this.crrncName = crrncMnth;
    }


    public static NameOfCurrency getNameOfCurrencyObject(String curName) {
        try {
            return NameOfCurrency.valueOf(curName.toUpperCase(Locale.ROOT));
        }
        catch (Exception e){
            return NameOfCurrency.INCORRECT;
        }
    }

    public String getName() {
        return crrncName;
    }
}
