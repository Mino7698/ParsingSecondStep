import java.util.List;
import java.util.Objects;

public class Months {
    List<Operations> operations;
    NamesOfMonths monthName;


    public Months(List<Operations> operations, String monthName){
        this.operations = operations;
        NamesOfMonths N = NamesOfMonths.INCORRECT;
        for (NamesOfMonths iN : NamesOfMonths.values()){
            if (iN.getName() == monthName) {
                N = iN;
                break;
            }
        }
        this.monthName = N;
    }

    public Months(ReadTheMonths monthName){
        this.operations = monthName.operations;
        NamesOfMonths N = NamesOfMonths.INCORRECT;
        for (NamesOfMonths iN : NamesOfMonths.values()){
            if (iN.getName() == monthName.monthName) {
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
