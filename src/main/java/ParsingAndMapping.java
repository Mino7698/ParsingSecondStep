import service.CustomerFilterService;
import service.CustomerService;

import java.io.*;


public class ParsingAndMapping {
    public static void main(String[] args) throws IOException {
       CustomerFilterService customerService = new CustomerFilterService(new CustomerService());
       System.out.println(customerService.getAllMonthOfAllCustomersInRubleValue());
       System.out.println(customerService.getCustomersWithTransaction());
    }
}
