import com.fasterxml.jackson.databind.ObjectMapper;
import service.CustomerService;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

public class ParsingAndMapping {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map/*<String, Map<String, Map<String, List<Map<String, Object>>>>>*/ jsonRead = mapper.readValue(Paths.get("src/main/java/data_json/InnerData5Step.JSON").toFile(), Map.class);

        System.out.println(CustomerService.getCustomersWithPositiveBalance());



    }


}
