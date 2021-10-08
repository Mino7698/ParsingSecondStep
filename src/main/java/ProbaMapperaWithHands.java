import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ProbaMapperaWithHands {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, List<Map<String, Object>>> jsonRead = mapper.readValue(Paths.get("src/main/java/InnerDataSecondStep.JSON").toFile(), Map.class);
//вот результат мапы
        parseOperations(jsonRead);
        System.out.println(parseOperations(jsonRead));

    }

    private static List<Operations> parseOperations(Map<String, List<Map<String, Object>>> jsonRead) {
        List<Map<String, Object>> notParsedOperations = jsonRead.get("operations");
        List<Operations> operations = new ArrayList<>();

        for (int i = 0; i < notParsedOperations.size(); i++) {
            Map<String, Object> notParsedOperation = notParsedOperations.get(i);
            String currency = (String) notParsedOperation.get("currency");
            int value = (int) notParsedOperation.get("value");
            operations.add(new Operations(value, currency));
        }


        return operations;
    }

}