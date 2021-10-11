import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class ProbaMapperaWithHandsThirdVersion {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Map<String, List<Map<String, Object>>>> jsonRead = mapper.readValue(Paths.get("src/main/java/InnerData4Step.JSON").toFile(), Map.class);
//вот результат мапы

        var setOfMonths = jsonRead.keySet();
        for (var k : setOfMonths) {
            System.out.println(parseOperations(jsonRead,k));
        }


    }

    private static SecondMonths parseOperations(Map<String,Map<String, List<Map<String, Object>>>> jsonRead, String monthName) {

        Map<String, List<Map<String, Object>>> notParsedOperations2 = jsonRead.get(monthName);
        List<Map<String, Object>> notParsedOperations = notParsedOperations2.get("operations");
        SecondMonths months = new SecondMonths(notParsedOperations,monthName);

        return months;
    }
}
