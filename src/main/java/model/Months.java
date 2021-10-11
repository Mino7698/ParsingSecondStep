package model;

import util.ReadTheMonths;

import java.util.List;
import java.util.Objects;

public class Months {
    private List<Operations> operations;
    private NamesOfMonths monthName;

    public List<Operations> getOperations() {
        return operations;
    }

    public void setOperations(List<Operations> operations) {
        this.operations = operations;
    }

    public NamesOfMonths getMonthName() {
        return monthName;
    }

    public void setMonthName(NamesOfMonths monthName) {
        this.monthName = monthName;
    }

    public Months(List<Operations> operations, String monthName){
        this.operations = operations;
        NamesOfMonths FlagOfMonthName = NamesOfMonths.INCORRECT;
        for (NamesOfMonths CounterOfMonthName : NamesOfMonths.values()){
            if (CounterOfMonthName.getName() == monthName) {
                FlagOfMonthName = CounterOfMonthName;
                break;
            }
        }
        this.monthName = FlagOfMonthName;
    }

    public Months(ReadTheMonths monthName){
        this.operations = monthName.getOperations();
        NamesOfMonths N = NamesOfMonths.INCORRECT;
        for (NamesOfMonths iN : NamesOfMonths.values()){
            if (iN.getName() == monthName.getMonthName()) {
                N = iN;
                break;
            }
        }
        this.monthName = N;
    }

    @Override
    public String toString() {
        return monthName.getName() + "={" +
                operations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Months oneMonth = (Months) o;
        return Objects.equals(operations, oneMonth.operations) && monthName == oneMonth.monthName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(operations, monthName);
    }
}
