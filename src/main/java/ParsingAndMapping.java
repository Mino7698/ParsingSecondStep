import model.Currency;
import service.CustomerFilterService;
import service.CustomerService;

import java.io.*;


public class ParsingAndMapping {
    public static void main(String[] args) throws IOException {
       CustomerFilterService customerService = new CustomerFilterService(new CustomerService());
       System.out.println(customerService.getAllMonthOfAllCustomersInRubleValue());
       System.out.println(customerService.getCustomersWithTransaction());

        System.out.println(customerService.getCustomersWithTransaction().get(0)
                .universalCurrencySaldoOfCustomerGetter(Currency.AUD));
        System.out.println(customerService.getCustomersWithTransaction().get(0)
                .universalCurrencySaldoOfCustomerGetter(Currency.RUB));
        System.out.println(customerService.getCustomersWithTransaction().get(0)
                .universalCurrencySaldoOfCustomerGetter(Currency.EUR));


    }
}
