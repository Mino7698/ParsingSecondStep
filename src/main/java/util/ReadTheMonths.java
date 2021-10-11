package util;

import model.Operations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ReadTheMonths {
    private List<Operations> operations;
    private String monthName;


    public ReadTheMonths(List<Map<String, Object>> operations, String monthName){
        this.operations = parseOperations(operations);
        this.monthName = monthName;
    }


    private static List<Operations> parseOperations(List<Map<String, Object>> notParsedOperations) {

        List<Operations> operations = new ArrayList<>();

        for (int i = 0; i < notParsedOperations.size(); i++) {
            Map<String, Object> notParsedOperation = (Map<String, Object>) notParsedOperations.get(i);
            String currency = (String) notParsedOperation.get("currency");
            int value = (int) notParsedOperation.get("value");
            operations.add(new Operations(value, currency));
        }

        return operations;
    }

    public static ReadTheMonths parseOperations(Map<String, Map<String, List<Map<String, Object>>>> jsonRead, String monthName) {

        Map<String, List<Map<String, Object>>> notParsedOperations2 = jsonRead.get(monthName);
        List<Map<String, Object>> notParsedOperations = notParsedOperations2.get("operations");
        ReadTheMonths months = new ReadTheMonths(notParsedOperations,monthName);

        return months;
    }

    public List<Operations> getOperations() {
        return operations;
    }

    public void setOperations(List<Operations> operations) {
        this.operations = operations;
    }

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
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