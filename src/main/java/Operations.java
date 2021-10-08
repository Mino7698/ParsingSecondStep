import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.Objects;

@JsonAutoDetect
public class Operations {
    private int value;
    private String currency;

    public Operations(int value, String currency) {
        this.value = value;
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Operations={" +
                "value=" + value +
                ", currency='" + currency + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Operations that = (Operations) o;
        return value == that.value && Objects.equals(currency, that.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currency);
    }

}
