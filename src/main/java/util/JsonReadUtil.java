package util;

import model.Customer;
import model.Month;
import model.Operation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonReadUtil {

    public static List<Operation> parseOfOperation(List<Map<String, Object>> notParsedOperations) {
        return notParsedOperations.stream()
                .map(operation -> new Operation((int) operation.get("value"), (String) operation.get("currency")))
                .collect(Collectors.toList());
    }

    public static Month parseOfOperationList(Map<String, Map<String, List<Map<String, Object>>>> jsonRead, String monthName) {
        Map<String, List<Map<String, Object>>> notParsedOperations2 = jsonRead.get(monthName);
        List<Map<String, Object>> notParsedOperations = notParsedOperations2.get("operations");
        return new Month(notParsedOperations, monthName);
    }

    public static Customer parseOfMonthList(Map<String, Map<String, Map<String, List<Map<String, Object>>>>> jsonRead, String customerName) {
        Map<String, Map<String, List<Map<String, Object>>>> notParsedMonths = jsonRead.get(customerName);
        List<Month> listOfMonth = notParsedMonths.keySet().stream()
                .map(monthName -> new Month(JsonReadUtil.parseOfOperationList(notParsedMonths, monthName)))
                .collect(Collectors.toList());
        return new Customer(listOfMonth, customerName);
    }

}
