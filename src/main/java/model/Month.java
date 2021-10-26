package model;

import lombok.experimental.SuperBuilder;
import util.JsonReadUtil;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@SuperBuilder
public class Month {
    private final List<Operation> operations;
    private final NameOfMonths monthName;
    private final Double saldoOfMonth;
    private final int numberOfMonthOperations;

    public List<Operation> getOperations() {
        return operations;
    }

    public NameOfMonths getMonthName() {
        return monthName;
    }

    public Double getSaldoOfMonth() {
        return saldoOfMonth;
    }

    public int getNumberOfMonthOperations() {
        return numberOfMonthOperations;
    }

    public Month(List<Map<String, Object>> operations, String monthName) {
        this.operations = JsonReadUtil.parseOfOperation(operations);
        this.monthName = NameOfMonths.getNameOfMonthObject(monthName);
        this.saldoOfMonth=this.operations.stream()
                .map(value ->  value.getRubleValue())
                .reduce((double) 0, (acc, v) -> Double.sum(acc, v));
        this.numberOfMonthOperations = operations.size();
    }

    public Month(Month monthName){
        this.operations = monthName.getOperations();
        this.monthName = monthName.getMonthName();
        this.saldoOfMonth=operations.stream()
                .map(value ->  value.getRubleValue())
                .reduce((double) 0, (acc, v) -> Double.sum(acc, v));
        this.numberOfMonthOperations = operations.size();
    }

    @Override
    public String toString() {
        return monthName.getName() + ": { operations: " +
                operations +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Month oneMonth = (Month) o;
        return Objects.equals(operations, oneMonth.operations) && monthName == oneMonth.monthName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(operations, monthName);
    }


}
