package model;

import java.util.List;
import java.util.Objects;

public class MonthForTop {
    private final String customerName;
    private final NameOfMonths monthName;
    private final int saldoOfMonth;

    public MonthForTop (Customer customer,Month months){
        this.customerName = customer.getCustomerName();
        this.monthName = months.getMonthName();
        this.saldoOfMonth = months.getSaldoOfMonth();
    }

    public String getCustomerName() {
        return customerName;
    }

    public NameOfMonths getMonthName() {
        return monthName;
    }

    public int getSaldoOfMonth() {
        return saldoOfMonth;
    }

    public static void quickSort(List<MonthForTop> source, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        MonthForTop pivot = source.get((leftMarker + rightMarker) / 2);
        do {
            // Двигаем левый маркер слева направо пока элемент меньше, чем pivot
            while (source.get(leftMarker).getSaldoOfMonth() > pivot.getSaldoOfMonth()) {
                leftMarker++;
            }
            // Двигаем правый маркер, пока элемент больше, чем pivot
            while (source.get(rightMarker).getSaldoOfMonth() < pivot.getSaldoOfMonth()) {
                rightMarker--;
            }
            // Проверим, не нужно обменять местами элементы, на которые указывают маркеры
            if (leftMarker <= rightMarker) {
                // Левый маркер будет меньше правого только если мы должны выполнить swap
                if (leftMarker < rightMarker) {
                    MonthForTop tmp = source.get(leftMarker);
                    source.set(leftMarker,source.get(rightMarker));
                    source.set(rightMarker,tmp);
                }
                // Сдвигаем маркеры, чтобы получить новые границы
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);

        // Выполняем рекурсивно для частей
        if (leftMarker < rightBorder) {
            quickSort(source, leftMarker, rightBorder);
        }
        if (leftBorder < rightMarker) {
            quickSort(source, leftBorder, rightMarker);
        }
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthForTop that = (MonthForTop) o;
        return saldoOfMonth == that.saldoOfMonth && Objects.equals(customerName, that.customerName) && monthName == that.monthName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerName, monthName, saldoOfMonth);
    }

    @Override
    public String toString() {
        return '\n' + "{" +
                "customerName='" + customerName + '\'' +
                ", monthName=" + monthName +
                ", saldoOfMonth=" + saldoOfMonth +
                '}' ;
    }
}
