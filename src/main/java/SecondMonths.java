import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class SecondMonths {
    List<Operations> operations;
    String monthName;


    public SecondMonths(List<Map<String, Object>> operations, String monthName){
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecondMonths that = (SecondMonths) o;
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
