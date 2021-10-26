package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Currency;
import model.Month;
import model.Operation;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class InternalCalculationsUtil {
    public static Double saldoOfMonth(List<Operation> list){
        return list.stream()
                .map(value ->  value.getRubleValue())
                .reduce((double) 0, (acc, v) -> Double.sum(acc, v));
    }

    public static Double saldoOfCustomer(List<Month> list){
        return list.stream()
                .map(value -> value.getSaldoOfMonth())
                .reduce((double) 0, Double::sum);
    }

    public static int numberOfCustomerOperations(List<Month> list){
        return list.stream()
                .map(value -> value.getNumberOfMonthOperations())
                .reduce(0, Integer::sum);
    }

    public static Double getRates(Currency curName){
        if (curName == Currency.RUB) return 1.0;
        if (curName == Currency.INCORRECT) return 1.0;
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
            return ((Double)currence.get("Value")/(int)currence.get("Nominal"));
        } catch (IOException e) {
            return 1.0;
        }
    }
}
