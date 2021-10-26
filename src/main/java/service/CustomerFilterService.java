package service;

import model.Customer;
import model.Month;
import model.NameOfMonths;
import util.MyComparatorForTopOfMonthInCustomerFilterService;

import java.util.*;
import java.util.stream.Collectors;

public class CustomerFilterService {
    private final CustomerService customerService;

    public CustomerFilterService(CustomerService customerService) {
        this.customerService = customerService;
    }

    //возвращает месяцы и их доходность
    public List<AbstractMap.SimpleEntry<NameOfMonths, Double>> getAllMonthOfAllCustomersInRubleValue() {
        Map<NameOfMonths, List<Month>> collect = customerService.getAllCustomers().stream()
                .map(customer -> customer.getMonths())
                .flatMap(months -> months.stream())
                .collect(Collectors.groupingBy(Month::getMonthName));
        return collect.entrySet().stream()
                .map(entry -> getMapWithKeyAndValue(entry))
                .sorted(new MyComparatorForTopOfMonthInCustomerFilterService())
        .collect(Collectors.toList());
    }

    public AbstractMap.SimpleEntry<NameOfMonths, Double> getMapWithKeyAndValue(Map.Entry<NameOfMonths,List<Month>> innerMap){
        Double saldoOfMonth = innerMap.getValue().stream()
                .map(month -> month.getSaldoOfMonth())
                .reduce((double) 0, (acc, v) -> Double.sum(acc, v));
        return  new AbstractMap.SimpleEntry<NameOfMonths, Double>(innerMap.getKey(),saldoOfMonth);
    }

    //возвращает всех пользователей у кого есть хотя бы одна транзакция
    public List<Customer> getCustomersWithTransaction() {
        return customerService.getAllCustomers().stream()
                .filter(value -> value.getNumberOfCustomerOperations() > 0)
                .collect(Collectors.toList());
    }

    //возвращает всех пользователей у которых есть транцзакции зимой
    public List<Customer> getCustomersWithWinterTransaction() {
        return customerService.getAllCustomers().stream()
                .filter(value -> value.getNumberOfCustomerOperations() > 0)
                .filter(value -> value.getMonths().stream()
                        .anyMatch(month -> (NameOfMonths.getNameOfWinterMonths().contains(month.getMonthName())
                                && (month.getNumberOfMonthOperations() > 0))))
                .collect(Collectors.toList());
    }

    //возвращает всех пользователей у которых положительный баланс
    public List<Customer> getCustomersWithPositiveBalance() {
        return customerService.getAllCustomers().stream()
                .filter(value -> value.getSaldoOfCustomer() > 0)
                .collect(Collectors.toList());
    }
}
