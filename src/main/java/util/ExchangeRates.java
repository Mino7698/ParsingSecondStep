package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.NameOfCurrency;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.Map;

public class ExchangeRates {
    private static int RUB = 1;
    private static int USD;
    private static int EUR;
    private static int INCORRECT = 0;

    public static void getActualRates (){
        try {
        final Content getResult;
            getResult = Request.Get("https://www.cbr-xml-daily.ru/daily_json.js")
                    .execute().returnContent();
        ObjectMapper mapper = new ObjectMapper();
        Map jsonRead;
        jsonRead = mapper
                .readValue(getResult.asString(), Map.class);
        Map valute = (Map) jsonRead.get("Valute");
        Map usd = (Map) valute.get("USD");
        USD = (int) Math.round((Double) usd.get("Value"));
        Map eur = (Map) valute.get("EUR");
        EUR = (int) Math.round((Double) eur.get("Value"));
        } catch (IOException e) {
            USD = 0;
            EUR = 0;
        }
    }

    public static int getRates(NameOfCurrency curName){
        getActualRates ();
        switch (curName){
            case EUR: return EUR;
            case RUB: return RUB;
            case USD: return USD;
        }
        return INCORRECT;
    }
}
