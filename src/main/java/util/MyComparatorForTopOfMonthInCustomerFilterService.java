package util;

import model.NameOfMonths;

import java.util.AbstractMap;
import java.util.Comparator;

public class MyComparatorForTopOfMonthInCustomerFilterService implements Comparator<AbstractMap.SimpleEntry<NameOfMonths, Integer>> {
    @Override
    public int compare(AbstractMap.SimpleEntry o1, AbstractMap.SimpleEntry o2) {
        return (int) o2.getValue() - (int) (o1.getValue());
    }
}
