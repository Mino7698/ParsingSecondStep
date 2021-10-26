package model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import util.InternalCalculationsUtil;

import java.util.List;

@Getter
@EqualsAndHashCode
@SuperBuilder
public class Customer {
    private final List<Month> months;
    private final String customerName;
    private final Double saldoOfCustomer;
    private final int numberOfCustomerOperations;


    public Customer(List<Month> months, String customer) {
        this.months = months;
        this.customerName = customer;
        this.saldoOfCustomer = InternalCalculationsUtil.saldoOfCustomer(months);
        this.numberOfCustomerOperations = InternalCalculationsUtil.numberOfCustomerOperations(months);
    }

    public Customer(Customer customer) {
        this.months = customer.months;
        this.customerName = customer.customerName;
        this.saldoOfCustomer = InternalCalculationsUtil.saldoOfCustomer(months);
        this.numberOfCustomerOperations = InternalCalculationsUtil.numberOfCustomerOperations(months);
    }

    public double universalCurrencySaldoOfCustomerGetter(Currency currency){
        return saldoOfCustomer/InternalCalculationsUtil.getRates(currency);
    }

    @Override
    public String toString() {
        return '\n' + customerName + " {" +
                "Months=" + months + '\'' +
                '}';
    }

}
