import com.fasterxml.jackson.databind.ObjectMapper;
import service.CustomerService;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Map;

public class ParsingAndMapping {
    public static void main(String[] args) throws IOException {
        System.out.println(CustomerService.getAllMonthOfAllCustomers());
    }

}
