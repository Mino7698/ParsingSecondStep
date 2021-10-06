import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Paths;


public class ProbaMappera {
    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonReadOperations jsonRead = mapper.readValue(Paths.get("D:\\ideaproject\\ParsingSecondStep\\src\\main\\java\\InnerDataSecondStep.JSON").toFile(), JsonReadOperations.class);
        System.out.println(jsonRead);


    }
}
