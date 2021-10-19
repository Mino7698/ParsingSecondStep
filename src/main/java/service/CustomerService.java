package service;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Customer;
import model.Month;
import model.MonthForTop;
import model.NameOfMonths;
import util.JsonReadUtil;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CustomerService {

    //возвращает всех пользователей
    public static List<Customer> getAllCustomers() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Map<String, Map<String, List<Map<String, Object>>>>> jsonRead = mapper
                .readValue(Paths.get("src/main/java/data_json/InnerData5Step.JSON").toFile(), Map.class);
        return jsonRead.keySet().stream()
                .map(customerName -> new Customer(JsonReadUtil.parseOfMonthList(jsonRead, customerName)))
                .collect(Collectors.toList());
    }

    //возвращает все месяцы всех пользователей по отдельности - объекты MonthForTop
    public static List<MonthForTop> getAllMonthOfAllCustomers() throws IOException {
        List<Customer> listOfAllCustomers = CustomerService.getAllCustomers();
        List<MonthForTop> result = new ArrayList<>();
        for (Customer customer : listOfAllCustomers) {
            for (Month currentMonth : customer.getMonths()) {
                result.add(new MonthForTop(customer, currentMonth));
            }
        }
        MonthForTop.quickSort(result, 0, result.size() - 1);
        return result;
    }

    //возвращает всех пользователей у кого есть хотя бы одна транзакция
    public static List<Customer> getCustomersWithTransaction() throws IOException {
        return CustomerService.getAllCustomers().stream()
                .filter(value -> value.getNumberOfCustomerOperations() > 0)
                .collect(Collectors.toList());
    }

    //возвращает всех пользователей у которых есть транцзакции зимой
    public static List<Customer> getCustomersWithWinterTransaction() throws IOException {
        return CustomerService.getAllCustomers().stream()
                .filter(value -> value.getNumberOfCustomerOperations() > 0)
                .filter(value -> value.getMonths().stream()
                        .anyMatch(month -> (((month.getMonthName() == NameOfMonths.ДЕКАБРЬ)
                                || (month.getMonthName() == NameOfMonths.ЯНВАРЬ)
                                || (month.getMonthName() == NameOfMonths.ФЕВРАЛЬ))
                                && (month.getNumberOfMonthOperations() > 0))))
                .collect(Collectors.toList());
    }


    //возвращает всех пользователей у которых положительный баланс
    public static List<Customer> getCustomersWithPositiveBalance() throws IOException {
        return CustomerService.getAllCustomers().stream()
                .filter(value -> value.getSaldoOfCustomer() > 0)
                .collect(Collectors.toList());
    }


}
