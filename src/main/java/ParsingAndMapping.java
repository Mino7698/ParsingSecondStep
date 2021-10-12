import com.fasterxml.jackson.databind.ObjectMapper;
import model.Customer;
import util.JsonReadUtil;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;

public class ParsingAndMapping {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String,Map<String, Map<String, List<Map<String, Object>>>>> jsonRead = mapper.readValue(Paths.get("src/main/java/data_json/InnerData5Step.JSON").toFile(), Map.class);



        List<Customer> listOfCustomers = new ArrayList<>();

        Set<String> setOfCustomers = jsonRead.keySet();
       for (String customerName : setOfCustomers) {
            listOfCustomers.add(new Customer(JsonReadUtil.parseOfMonthList(jsonRead, customerName)));
        }

        System.out.println(listOfCustomers);

    }



}
