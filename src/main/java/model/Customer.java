package model;

import java.util.List;
import java.util.Objects;

public class Customer {
    private final List<Month> months;
    private final String customerName;
    private final int saldoOfCustomer;
    private final int numberOfCustomerOperations;


    public Customer(List<Month> months, String customer) {
        this.months = months;
        this.customerName = customer;
        this.saldoOfCustomer = months.stream()
                .map(value -> value.getSaldoOfMonth())
                .reduce(0, Integer::sum);
        this.numberOfCustomerOperations = months.stream()
                .map(value -> value.getNumberOfMonthOperations())
                .reduce(0, Integer::sum);
    }

    public Customer(Customer customer) {
        this.months = customer.months;
        this.customerName = customer.customerName;
        this.saldoOfCustomer = months.stream()
                .map(value -> value.getSaldoOfMonth())
                .reduce(0, Integer::sum);
        this.numberOfCustomerOperations = months.stream()
                .map(value -> value.getNumberOfMonthOperations())
                .reduce(0, Integer::sum);
    }

    public List<Month> getMonths() {
        return months;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getSaldoOfCustomer() {
        return saldoOfCustomer;
    }

    public int getNumberOfCustomerOperations() {
        return numberOfCustomerOperations;
    }

    @Override
    public String toString() {
        return customerName + " {" +
                "Months=" + months + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(months, customer.months) && Objects.equals(this.customerName, customer.customerName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(months, customerName);
    }
}
