import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.NameOfCurrency;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import service.CustomerFilterService;
import service.CustomerService;
import util.JsonReadUtil;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class ParsingAndMapping {
    public static void main(String[] args) throws IOException {
       CustomerFilterService customerService = new CustomerFilterService(new CustomerService());
       System.out.println(customerService.getAllMonthOfAllCustomersInRubleValue());
       System.out.println(customerService.getCustomersWithTransaction());
    }
}
