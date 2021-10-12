import com.fasterxml.jackson.databind.ObjectMapper;
import model.Months;
import util.ReadTheMonths;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class ParsingAndMapping {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
       // Map<String,Map<String, List<Map<String, Object>>>> jsonRead = mapper.readValue(Paths.get("src/main/java/data_json/InnerData4Step.JSON").toFile(), Map.class);
        Map jsonRead = mapper.readValue(Paths.get("src/main/java/data_json/InnerData4Step.JSON").toFile(), Map.class);
        List<Months> listOfMonth = new ArrayList<>();

        Set<String> setOfMonths = jsonRead.keySet();
        for (String k : setOfMonths) {
            listOfMonth.add(new Months(ReadTheMonths.parseOperations(jsonRead,k)));
        }

        System.out.println(listOfMonth);

    }



}
