import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class ParsingAndMapping {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Map<String, List<Map<String, Object>>>> jsonRead = mapper.readValue(Paths.get("src/main/java/InnerData4Step.JSON").toFile(), Map.class);
        List<Months> ListOfMonth = new ArrayList<>();

        Set<String> setOfMonths = jsonRead.keySet();
        for (String k : setOfMonths) {
            ListOfMonth.add(new Months(parseOperations(jsonRead,k)));
        }

        System.out.println(ListOfMonth);

    }

    private static ReadTheMonths parseOperations(Map<String,Map<String, List<Map<String, Object>>>> jsonRead, String monthName) {

        Map<String, List<Map<String, Object>>> notParsedOperations2 = jsonRead.get(monthName);
        List<Map<String, Object>> notParsedOperations = notParsedOperations2.get("operations");
        ReadTheMonths months = new ReadTheMonths(notParsedOperations,monthName);

        return months;
    }
}
