package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Customer;
import util.JsonReadUtil;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerService {
    //возвращает всех пользователей
    public List<Customer> getAllCustomers() {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Map<String, Map<String, List<Map<String, Object>>>>> jsonRead;
        try {
            jsonRead = mapper
                    .readValue(Paths.get("src/main/java/data_json/InnerData.JSON").toFile(), Map.class);
        } catch (IOException e) {
            return Collections.emptyList();
        }
        return jsonRead.keySet().stream()
                .map(customerName -> new Customer(JsonReadUtil.parseOfMonthList(jsonRead, customerName)))
                .collect(Collectors.toList());
    }
}