import com.fasterxml.jackson.databind.ObjectMapper;
import model.Customer;
import model.Month;
import model.NameOfMonths;
import model.Operation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;
import service.CustomerFilterService;
import service.CustomerService;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Test1 {

    @Before
    public void setUp() throws Exception {
    }

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void operationTest() {
//        List<Operation> operationListNov = new ArrayList<>();
//        operationListNov.add(new Operation(100,"USD"));
//        List<Month> monthList = new ArrayList<>();
//        //добавить пару месяцев
//        Customer customer = new Customer();

//        Customer customerMock = Mockito.mock(Customer.class);
//        Assert.assertEquals(customerMock.getMonths().get(1).getOperations().get(1),new Operation(0,null));

        CustomerFilterService customerService = new CustomerFilterService(new CustomerService());

        List<AbstractMap.SimpleEntry<NameOfMonths, Integer>> testlist = new ArrayList<>();
        testlist.add(new AbstractMap.SimpleEntry<NameOfMonths, Integer>(NameOfMonths.НОЯБРЬ,100));
        testlist.add(new AbstractMap.SimpleEntry<NameOfMonths, Integer>(NameOfMonths.МАРТ,0));
        testlist.add(new AbstractMap.SimpleEntry<NameOfMonths, Integer>(NameOfMonths.АПРЕЛЬ,0));
        testlist.add(new AbstractMap.SimpleEntry<NameOfMonths, Integer>(NameOfMonths.ФЕВРАЛЬ,0));

        Assert.assertEquals(testlist,customerService.getAllMonthOfAllCustomers());

    }

}
