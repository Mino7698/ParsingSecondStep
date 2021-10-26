package model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.experimental.SuperBuilder;
import service.ExchangeRatesAgainstTheRubleService;

import java.util.Objects;

@JsonAutoDetect@SuperBuilder
public class Operation {
    private final Double value;
    private final Double rubleValue;
    private final Currency currency;

    public Operation(Double value, String currency) {
        this.value = value;
        this.currency = Currency.getNameOfCurrencyObject(currency);
        this.rubleValue = value* ExchangeRatesAgainstTheRubleService.getRates(this.currency);
    }

    public Double getValue() {
        return value;
    }

    public Double getRubleValue() {
        return rubleValue;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "{" +
                "value=" + value +
                ", currency='" + currency.getName() + "', RubleValue='" +
                rubleValue +
                "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operation that = (Operation) o;
        return value.equals(that.value) && Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }

}
