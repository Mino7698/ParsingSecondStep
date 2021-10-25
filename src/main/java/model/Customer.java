package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Objects;

@Getter
@EqualsAndHashCode
@SuperBuilder
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

    @Override
    public String toString() {
        return '\n' + customerName + " {" +
                "Months=" + months + '\'' +
                '}';
    }
}
