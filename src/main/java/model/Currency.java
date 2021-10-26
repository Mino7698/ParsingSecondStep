package model;

import java.util.Locale;

public enum Currency {
    AUD("Австралийский доллар"),
    AZN("Азербайджанский манат"),
    GBP("Фунт стерлингов Соединенного королевства"),
    AMD("Армянских драмов"),
    BYN("Белорусский рубль"),
    BGN("Болгарский лев"),
    BRL("Бразильский реал"),
    HUF("Венгерских форинтов"),
    HKD("Гонконгских долларов"),
    DKK("Датская крона"),
    USD("Доллар США"),
    EUR("Евро"),
    INR("Индийских рупий"),
    KZT("Казахстанских тенге"),
    CAD("Канадский доллар"),
    KGS("Киргизских сомов"),
    CNY("Китайский юань"),
    MDL("Молдавских леев"),
    NOK("Норвежских крон"),
    PLN("Польский злотый"),
    RON("Румынский лей"),
    XDR("СДР (специальные права заимствования)"),
    SGD("Сингапурский доллар"),
    TJS("Таджикских сомони"),
    TRY("Турецких лир"),
    TMT("Новый туркменский манат"),
    UZS("Узбекских сумов"),
    UAH("Украинских гривен"),
    CZK("Чешских крон"),
    SEK("Шведских крон"),
    CHF("Швейцарский франк"),
    ZAR("Южноафриканских рэндов"),
    KRW("Вон Республики Корея"),
    JPY("Японских иен"),
    RUB("Российский рубль"),
    INCORRECT("IncorrectName");

    private final String crrncName;

    Currency(String crrncMnth) {
        this.crrncName = crrncMnth;
    }


    public static Currency getNameOfCurrencyObject(String curName) {
        try {
            return Currency.valueOf(curName.toUpperCase(Locale.ROOT));
        }
        catch (Exception e){
            return Currency.INCORRECT;
        }
    }

    public String getName() {
        return crrncName;
    }
}
