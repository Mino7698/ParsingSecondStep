package util;

import model.Customer;
import model.Month;
import model.Operation;

import java.util.*;

public class JsonReadUtil {
    private final List<Operation> operations;
    private final String monthName;


    public JsonReadUtil(List<Map<String, Object>> operations, String monthName){
        this.operations = parseOfOperation(operations);
        this.monthName = monthName;
    }


    private static List<Operation> parseOfOperation(List<Map<String, Object>> notParsedOperations) {

        List<Operation> operations = new ArrayList<>();

        for (int i = 0; i < notParsedOperations.size(); i++) {
            Map<String, Object> notParsedOperation = notParsedOperations.get(i);
            String currency = (String) notParsedOperation.get("currency");
            int value = (int) notParsedOperation.get("value");
            operations.add(new Operation(value, currency));
        }

        return operations;
    }

    public static JsonReadUtil parseOfOperationList(Map<String, Map<String, List<Map<String, Object>>>> jsonRead, String monthName) {

        Map<String, List<Map<String, Object>>> notParsedOperations2 = jsonRead.get(monthName);
        List<Map<String, Object>> notParsedOperations = notParsedOperations2.get("operations");

        return new JsonReadUtil(notParsedOperations,monthName);
    }

    public static Customer parseOfMonthList(Map<String,Map<String, Map<String, List<Map<String, Object>>>>> jsonRead, String customerName) {

        Map<String, Map<String, List<Map<String, Object>>>> notParsedOperations = jsonRead.get(customerName);

        List<Month> listOfMonth = new ArrayList<>();
        Set<String> setOfMonths = notParsedOperations.keySet();
        for (String monthName : setOfMonths) {
            listOfMonth.add(new Month(JsonReadUtil.parseOfOperationList(notParsedOperations,monthName)));
        }


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
        return  monthName + "={" +
                "operations=" + operations +
                '}';
    }
}
