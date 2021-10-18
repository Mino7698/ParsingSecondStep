package util;

import model.Customer;
import model.Month;
import model.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class JsonReadUtil {
    private final List<Operation> operations;
    private final String monthName;


    public JsonReadUtil(List<Map<String, Object>> operations, String monthName) {
        this.operations = parseOfOperation(operations);
        this.monthName = monthName;
    }


    private static List<Operation> parseOfOperation(List<Map<String, Object>> notParsedOperations) {
        return notParsedOperations.stream()
                .map(x -> new Operation((int)x.get("value"),(String) x.get("currency")))
                .collect(Collectors.toList());
    }

    public static JsonReadUtil parseOfOperationList(Map<String, Map<String, List<Map<String, Object>>>> jsonRead, String monthName) {

        Map<String, List<Map<String, Object>>> notParsedOperations2 = jsonRead.get(monthName);
        List<Map<String, Object>> notParsedOperations = notParsedOperations2.get("operations");

        return new JsonReadUtil(notParsedOperations, monthName);
    }

    public static Customer parseOfMonthList(Map<String, Map<String, Map<String, List<Map<String, Object>>>>> jsonRead, String customerName) {

        Map<String, Map<String, List<Map<String, Object>>>> notParsedMonths = jsonRead.get(customerName);

        List<Month> listOfMonth = notParsedMonths.keySet().stream()
                .map(monthName -> new Month(JsonReadUtil.parseOfOperationList(notParsedMonths, monthName)))
                .collect(Collectors.toList());

        return new Customer(listOfMonth, customerName);
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public String getMonthName() {
        return monthName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonReadUtil that = (JsonReadUtil) o;
        return Objects.equals(operations, that.operations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operations);
    }

    @Override
    public String toString() {
        return monthName + "={" +
                "operations=" + operations +
                '}';
    }
}
