import com.fasterxml.jackson.annotation.JsonAutoDetect;

import java.util.List;
import java.util.Objects;

@JsonAutoDetect
public class JsonReadOperations {
    public List<Operations> operations;

    @Override
    public String toString() {
        return "JsonRead{" +
                "operations=" + operations +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        JsonReadOperations jsonRead = (JsonReadOperations) o;
        return Objects.equals(operations, jsonRead.operations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operations);
    }

}


