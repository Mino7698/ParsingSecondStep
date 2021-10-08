import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Months {
    String month;
    List<Operations> operations;

    public Months(String month, List<Map<String, Object>> operations){
        this.month = month;
        this.operations = parseOperations(operations);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Months months = (Months) o;
        return Objects.equals(month, months.month) && Objects.equals(operations, months.operations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, operations);
    }

    @Override
    public String toString() {
        return "Months={" +
                "month='" + month + '\'' +
                ", operations=" + operations +
                '}';
    }
}
