import model.Customer;
import model.Month;
import model.NameOfMonths;
import model.Operation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import service.CustomerFilterService;
import service.CustomerService;
import java.util.Arrays;
import java.util.List;

import java.util.*;

@RunWith(MockitoJUnitRunner.class)
public class TestingServices {
    private final Customer mockCustomer = Mockito.mock(Customer.class);
    @Rule
    public ExpectedException exception = ExpectedException.none();
    private CustomerFilterService customerFilterService;
    List<Customer> customerList = new ArrayList<>();
    List<AbstractMap.SimpleEntry<NameOfMonths, Integer>> testlist = new ArrayList<>();
    @Mock
    private CustomerService mockCustomerService;
    private final CustomerFilterService customerServiceJSONForTesting = new CustomerFilterService(new CustomerService());
    @Before
    public void setUp() throws Exception {
        customerFilterService = new CustomerFilterService(mockCustomerService);
        creatingListOfCustomers();
    }
    public void creatingListOfCustomers() {
        Operation operation1 = Operation.builder().currency("USD").value(1470).build();
        List<Operation> operationList = Arrays.asList(new Operation[]{operation1});
        Month june = Month.builder().monthName(NameOfMonths.ИЮНЬ).saldoOfMonth(1470).numberOfMonthOperations(1).operations(operationList).build();
        Month march = Month.builder().monthName(NameOfMonths.МАРТ).saldoOfMonth(0).numberOfMonthOperations(0).operations(Collections.emptyList()).build();
        List<Month> listOfMonth = Arrays.asList(new Month[]{june,march});
        Customer customerPupa = Customer.builder().customerName("Пупа").saldoOfCustomer(1470).numberOfCustomerOperations(1).months(listOfMonth).build();
        List<Customer> customerList = Arrays.asList(customerPupa);
        this.customerList = customerList;
        Mockito.when(mockCustomerService.getAllCustomers()).thenReturn(customerList);

        testlist.add(new AbstractMap.SimpleEntry<NameOfMonths, Integer>(NameOfMonths.ИЮНЬ, 1470));
        testlist.add(new AbstractMap.SimpleEntry<NameOfMonths, Integer>(NameOfMonths.МАРТ, 0));
    }
    @Test
    public void customerFilterServiceTest() {
        System.out.println(customerServiceJSONForTesting.getCustomersWithTransaction());
        Assert.assertEquals(customerList,customerFilterService.getCustomersWithTransaction());
        Assert.assertEquals(customerList,customerFilterService.getCustomersWithPositiveBalance());
        Assert.assertEquals(Collections.emptyList(), customerFilterService.getCustomersWithWinterTransaction());
        Assert.assertEquals(testlist,customerFilterService.getAllMonthOfAllCustomers());
       // Mockito.verify(mockCustomerService).getAllCustomers();
       // Mockito.verifyNoMoreInteractions(mockCustomerService);
    }
    @Test
    public void getAllCustomersTest() {
        Assert.assertEquals(customerList,mockCustomerService.getAllCustomers());
        Mockito.verify(mockCustomerService).getAllCustomers();
        Mockito.verifyNoMoreInteractions(mockCustomerService);
    }
}
