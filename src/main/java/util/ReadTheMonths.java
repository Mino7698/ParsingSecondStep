package util;

import model.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ReadTheMonths {
    private final List<Operation> operations;
    private final String monthName;


    public ReadTheMonths(List<Map<String, Object>> operations, String monthName){
        this.operations = parseOperations(operations);
        this.monthName = monthName;
    }


    private static List<Operation> parseOperations(List<Map<String, Object>> notParsedOperations) {

        List<Operation> operations = new ArrayList<>();

        for (int i = 0; i < notParsedOperations.size(); i++) {
            Map<String, Object> notParsedOperation = notParsedOperations.get(i);
            String currency = (String) notParsedOperation.get("currency");
            int value = (int) notParsedOperation.get("value");
            operations.add(new Operation(value, currency));
        }

        return operations;
    }

    public static ReadTheMonths parseOperations(Map<String, Map<String, List<Map<String, Object>>>> jsonRead, String monthName) {

        Map<String, List<Map<String, Object>>> notParsedOperations2 = jsonRead.get(monthName);
        List<Map<String, Object>> notParsedOperations = notParsedOperations2.get("operations");

        return new ReadTheMonths(notParsedOperations,monthName);
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
        ReadTheMonths that = (ReadTheMonths) o;
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
