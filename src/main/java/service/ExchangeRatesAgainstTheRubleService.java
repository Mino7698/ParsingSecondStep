package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.NameOfCurrency;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.Map;

public class ExchangeRatesAgainstTheRubleService {
    private static Double INCORRECT = 0.0;

    public static Double getRates(NameOfCurrency curName){
        if (curName == NameOfCurrency.RUB) return 1.0;
        if (curName == NameOfCurrency.INCORRECT) return 1.0;
        try {
            final Content getResult;
            getResult = Request.Get("https://www.cbr-xml-daily.ru/daily_json.js")
                    .execute().returnContent();
            ObjectMapper mapper = new ObjectMapper();
            Map jsonRead;
            jsonRead = mapper
                    .readValue(getResult.asString(), Map.class);
            Map valute = (Map) jsonRead.get("Valute");
            Map currence = (Map) valute.get(curName.name());
            return  (Double) currence.get("Value");
        } catch (IOException e) {
            return INCORRECT;
        }
    }
}
