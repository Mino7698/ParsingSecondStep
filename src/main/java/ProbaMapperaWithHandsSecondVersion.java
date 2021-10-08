import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ProbaMapperaWithHandsSecondVersion {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, List<Map<String, Object>>> jsonRead = mapper.readValue(Paths.get("src/main/java/InnerDataThirdStep.JSON").toFile(), Map.class);
//вот результат мапы
        parseOperations(jsonRead);
        System.out.println(parseOperations(jsonRead));

    }

    private static List<Months> parseOperations(Map<String, List<Map<String, Object>>> jsonRead) {

        List<Map<String, Object>> notParsedOperations = jsonRead.get("months");
        List<Months> months = new ArrayList<>();

            for (int i = 0; i < notParsedOperations.size(); i++) {
                Map<String, Object> notParsedOperation = notParsedOperations.get(i);
                String month = (String) notParsedOperation.get("month");
                List<Map<String, Object>> operations = (List<Map<String, Object>>) notParsedOperation.get("operations");

                months.add(new Months(month, operations));

            }


        return months;
    }

}