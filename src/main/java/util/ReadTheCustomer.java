package util;

import model.Customer;
import model.Month;

import java.util.*;

public class ReadTheCustomer {
    private final List<Month> months;
    private final String customerName;


    public ReadTheCustomer(List<Month> months, String customerName) {
        this.customerName = customerName;
        this.months = months;
    }



    public static Customer parseOperations(Map<String,Map<String, Map<String, List<Map<String, Object>>>>> jsonRead, String customerName) {

        Map<String, Map<String, List<Map<String, Object>>>> notParsedOperations = jsonRead.get(customerName);

        List<Month> listOfMonth = new ArrayList<>();
        Set<String> setOfMonths = notParsedOperations.keySet();
        for (String monthName : setOfMonths) {
            listOfMonth.add(new Month(ReadTheMonths.parseOperations(notParsedOperations,monthName)));
        }


        return new Customer(listOfMonth, customerName);
    }

}
