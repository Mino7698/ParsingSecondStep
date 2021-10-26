package util;

import model.NameOfMonths;

import java.util.AbstractMap;
import java.util.Comparator;

public class MyComparatorForTopOfMonthInCustomerFilterService implements Comparator<AbstractMap.SimpleEntry<NameOfMonths, Double>> {
    @Override
    public int compare(AbstractMap.SimpleEntry o1, AbstractMap.SimpleEntry o2) {
        return (int)((Double) o2.getValue() - (Double) (o1.getValue()));
    }
}
