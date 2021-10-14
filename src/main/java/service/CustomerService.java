package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Customer;
import model.Month;
import model.NameOfMonths;
import model.Operation;
import util.JsonReadUtil;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerService {
    public static List<Customer> getAllCustomers() throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        Map<String, Map<String, Map<String, List<Map<String, Object>>>>> jsonRead = mapper
                .readValue(Paths.get("src/main/java/data_json/InnerData5Step.JSON")
                .toFile(), Map.class);

        return jsonRead.keySet().stream()
                .map(customerName -> new Customer(JsonReadUtil.parseOfMonthList(jsonRead, customerName)))
                .collect(Collectors.toList());

        /*List<Customer> listOfCustomers = new ArrayList<>();
        Set<String> setOfCustomers = jsonRead.keySet();
        for (String customerName : setOfCustomers) {
            listOfCustomers.add(new Customer(JsonReadUtil.parseOfMonthList(jsonRead, customerName)));
        }
        return listOfCustomers;*/
    }

    //возвращает всех пользователей у кого есть хотя бы одна транзакция
    public static List<Customer> getCustomersWithTransaction() throws IOException {
        List<Customer> listOfCustomersWithTransaction = new ArrayList<>();
        List<Customer> listOfAllCustomers = CustomerService.getAllCustomers();
        if (listOfAllCustomers == null || listOfAllCustomers.isEmpty()) {
            System.err.println("List of customers is Null");
            return null;
        }
        Customer customer = listOfAllCustomers.get(0);
        Iterator<Customer> iteratorOfCustomers = listOfAllCustomers.iterator();
        while (iteratorOfCustomers.hasNext()) {
            customer = iteratorOfCustomers.next();
            List<Month> listOfMonths = customer.getMonths();
            if (listOfMonths != null && !listOfMonths.isEmpty()) {
                Iterator<Month> iteratorOfMonths = listOfMonths.iterator();
                LABEL:
                while (iteratorOfMonths.hasNext()) {
                        List<Operation> listOfOperations = iteratorOfMonths.next().getOperations();
                        if (listOfOperations != null && !listOfOperations.isEmpty()) {
                            listOfCustomersWithTransaction.add(customer);
                            break LABEL;
                        }
                }
            }
        }
        return listOfCustomersWithTransaction;
    }

    //возвращает всех пользователей у которых есть транцзакции зимой
    public static List<Customer> getCustomersWithWinterTransaction() throws IOException {
        List<Customer> listOfCustomersWithWinterTransaction = new ArrayList<>();
        List<Customer> listOfAllCustomers = CustomerService.getAllCustomers();
        if (listOfAllCustomers == null || listOfAllCustomers.isEmpty()) {
            System.err.println("List of customers is Null");
            return null;
        }
        Iterator<Customer> iteratorOfCustomers = listOfAllCustomers.iterator();
        while (iteratorOfCustomers.hasNext()) {
            Customer customer = iteratorOfCustomers.next();
            List<Month> listOfMonths = customer.getMonths();
            if (listOfMonths != null && !listOfMonths.isEmpty()) {
                Iterator<Month> iteratorOfMonths = listOfMonths.iterator();
                LABEL:
                while (iteratorOfMonths.hasNext()) {
                    Month currentMonth = iteratorOfMonths.next();
                    if ((currentMonth.getMonthName().equals(NameOfMonths.ДЕКАБРЬ))
                    || (currentMonth.getMonthName().equals(NameOfMonths.ЯНВАРЬ))
                    || (currentMonth.getMonthName().equals(NameOfMonths.ФЕВРАЛЬ))){
                        List<Operation> listOfOperations = currentMonth.getOperations();
                        if (listOfOperations != null && !listOfOperations.isEmpty()) {
                            listOfCustomersWithWinterTransaction.add(customer);
                            break LABEL;
                        }
                    }
                }
            }
        }
        return listOfCustomersWithWinterTransaction;
    }


    //возвращает всех пользователей у которых положительный баланс
    public static List<Customer> getCustomersWithPositiveBalance() throws IOException {
        List<Customer> listOfCustomersWithWinterTransaction = new ArrayList<>();
        List<Customer> listOfAllCustomers = CustomerService.getAllCustomers();
        if (listOfAllCustomers == null || listOfAllCustomers.isEmpty()) {
            System.err.println("List of customers is Null");
            return null;
        }
        Iterator<Customer> iteratorOfCustomers = listOfAllCustomers.iterator();
        while (iteratorOfCustomers.hasNext()) {
            int profit = 0;
            Customer customer = iteratorOfCustomers.next();
            List<Month> listOfMonths = customer.getMonths();
            if (listOfMonths != null && !listOfMonths.isEmpty()) {
                Iterator<Month> iteratorOfMonths = listOfMonths.iterator();
                LABEL:
                while (iteratorOfMonths.hasNext()) {
                    Month currentMonth = iteratorOfMonths.next();
                        List<Operation> listOfOperations = currentMonth.getOperations();
                        if (listOfOperations != null && !listOfOperations.isEmpty()) {
                            Iterator<Operation> iteratorOfOperations = listOfOperations.iterator();
                            while (iteratorOfOperations.hasNext()) {
                                Operation currentOperation = iteratorOfOperations.next();
                                profit+=currentOperation.getValue();
                            }
                        }

                }
            }
            if (profit>0) listOfCustomersWithWinterTransaction.add(customer);
        }
        return listOfCustomersWithWinterTransaction;
    }



    //вернуть самые прибальные месяцы в порядке возрастания в формате мап<месяц,сумма>

}
