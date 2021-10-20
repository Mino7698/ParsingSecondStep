import service.CustomerFilterService;
import service.CustomerService;

import java.io.IOException;

public class ParsingAndMapping {
    public static void main(String[] args) throws IOException {
        CustomerFilterService customerService = new CustomerFilterService(new CustomerService());
        System.out.println(customerService.getAllMonthOfAllCustomers());
        System.out.println(customerService.getCustomersWithWinterTransaction());
    }
}
