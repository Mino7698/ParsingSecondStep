package model;

import util.JsonReadUtil;

import java.util.List;
import java.util.Objects;

public class Month {
    private final List<Operation> operations;
    private final NameOfMonths monthName;

    public List<Operation> getOperations() {
        return operations;
    }

    public NameOfMonths getMonthName() {
        return monthName;
    }

    public Month(List<Operation> operations, String monthName){
        this.operations = operations;
        NameOfMonths flagOfMonthName = NameOfMonths.INCORRECT;
        for (NameOfMonths CounterOfMonthName : NameOfMonths.values()){
            if (CounterOfMonthName.getName().equals(monthName)) {
                flagOfMonthName = CounterOfMonthName;
                break;
            }
        }
        this.monthName = flagOfMonthName;
    }

    public Month(JsonReadUtil monthName){
        this.operations = monthName.getOperations();
        NameOfMonths flagOfMonthName = NameOfMonths.INCORRECT;
        for (NameOfMonths CounterOfMonthName : NameOfMonths.values()){
            if (CounterOfMonthName.getName().equals(monthName.getMonthName())) {
                flagOfMonthName = CounterOfMonthName;
                break;
            }
        }
        this.monthName = flagOfMonthName;
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
