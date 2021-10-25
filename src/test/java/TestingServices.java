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
        Mockito.when(mockCustomerService.getAllCustomers()).thenReturn(customerList);
    }
    @Test
    public void getCustomersWithTransaction() {
        System.out.println(customerServiceJSONForTesting.getCustomersWithTransaction());
        Assert.assertEquals(customerFilterService.getCustomersWithTransaction(), customerServiceJSONForTesting.getCustomersWithTransaction());
        Assert.assertEquals(customerFilterService.getCustomersWithPositiveBalance(), customerServiceJSONForTesting.getCustomersWithPositiveBalance());
        Assert.assertEquals(customerFilterService.getCustomersWithWinterTransaction(), customerServiceJSONForTesting.getCustomersWithWinterTransaction());
        Assert.assertEquals(customerFilterService.getAllMonthOfAllCustomers(), customerServiceJSONForTesting.getAllMonthOfAllCustomers());
       // Mockito.verify(mockCustomerService).getAllCustomers();
       // Mockito.verifyNoMoreInteractions(mockCustomerService);
    }
}
