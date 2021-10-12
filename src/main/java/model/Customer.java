package model;

import java.util.List;
import java.util.Objects;

public class Customer {
    private final List<Month> months;
    private final String customer;

    public Customer (List<Month> months, String customer){
        this.months = months;
        this.customer = customer;
    }

    public Customer (Customer customer){
        this.months = customer.months;
        this.customer = customer.customer;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "Months=" + months +
                ", Customer='" + customer + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(months, customer.months) && Objects.equals(this.customer, customer.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(months, customer);
    }
}
