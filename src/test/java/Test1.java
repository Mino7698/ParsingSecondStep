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
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import service.CustomerFilterService;
import service.CustomerService;
import util.JsonReadUtil;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class Test1 {
    @Rule
    public ExpectedException exception = ExpectedException.none();

    private CustomerFilterService customerService;
    private CustomerFilterService customerService2 = new CustomerFilterService(new CustomerService());
    @Mock
    private CustomerService mockCustomerService;
    private Month month = Mockito.mock(Month.class);
    private Customer customer = Mockito.mock(Customer.class);



    @Before
    public void setUp() throws Exception {
        customerService = new CustomerFilterService(mockCustomerService);
        mockingOfMonth();
        mockingOfCustomer();
    }

    @Test
    public void errorClassicTest(){
        //Assert.assertEquals(new IllegalStateException("Utility class"), new JsonReadUtil());
    }

    @Test
    public void operationTest() {
        MockitoAnnotations.initMocks(this);
        List<AbstractMap.SimpleEntry<NameOfMonths, Integer>> testlist = new ArrayList<>();
        testlist.add(new AbstractMap.SimpleEntry<NameOfMonths, Integer>(NameOfMonths.INCORRECT, 1488));
        testlist.add(new AbstractMap.SimpleEntry<NameOfMonths, Integer>(NameOfMonths.НОЯБРЬ, 1001));
        testlist.add(new AbstractMap.SimpleEntry<NameOfMonths, Integer>(NameOfMonths.ДЕКАБРЬ, 100));
        testlist.add(new AbstractMap.SimpleEntry<NameOfMonths, Integer>(NameOfMonths.МАРТ, 0));
        Assert.assertEquals(testlist, customerService2.getAllMonthOfAllCustomers());
    }

    @Test
    public void operationClassicTest() {
        Operation operation = new Operation(1488, "USD");
        Assert.assertEquals(operation,customerService2.getCustomersWithTransaction().get(0).getMonths().get(2).getOperations().get(0));
    }

    @Test
    public void monthClassicTest() {
        List<Operation> operationListNov = new ArrayList<>();
        operationListNov.add(new Operation(1488, "USD"));
        Month month2 = Month.builder().monthName(NameOfMonths.INCORRECT).saldoOfMonth(1488).numberOfMonthOperations(1).operations(operationListNov).build();
        Assert.assertEquals(month2,customerService2.getCustomersWithTransaction().get(0).getMonths().get(2));
    }

    @Test
    public void getSaldoTestWithMock() {
        Assert.assertEquals(customer.getSaldoOfCustomer(),customerService2.getCustomersWithTransaction().get(0).getSaldoOfCustomer());
    }

    @Test
    public void getCustomerNameTestWithMock() {
        Assert.assertEquals(customer.getCustomerName(),customerService2.getCustomersWithTransaction().get(0).getCustomerName());
    }
    @Test
    public void getNumberOfCustomerOperationsTestWithMock() {
        Assert.assertEquals(customer.getNumberOfCustomerOperations(),customerService2.getCustomersWithPositiveBalance().get(0).getNumberOfCustomerOperations());
    }

    @Test
    public void getMonthNameTestWithMock23() {
        Assert.assertEquals(month.getMonthName(),customerService2.getCustomersWithWinterTransaction().get(0).getMonths().get(2).getMonthName());
    }

    @Test
    public void getSaldoOfMonthWithMock() {
        Assert.assertEquals(month.getSaldoOfMonth(),customerService2.getCustomersWithWinterTransaction().get(0).getMonths().get(2).getSaldoOfMonth());
    }

    @Test
    public void getMonthNameTestWithMock2322() {
        Assert.assertEquals(month.getNumberOfMonthOperations(),customerService2.getCustomersWithWinterTransaction().get(0).getMonths().get(2).getNumberOfMonthOperations());
    }

    private void mockingOfMonth(){
        Mockito.when(month.getNumberOfMonthOperations()).thenReturn(1);
        Mockito.when(month.getSaldoOfMonth()).thenReturn(1488);
        Mockito.when(month.getMonthName()).thenReturn(NameOfMonths.INCORRECT);
    }
    private void mockingOfCustomer(){
        Mockito.when(customer.getSaldoOfCustomer()).thenReturn(2589);
        Mockito.when(customer.getCustomerName()).thenReturn("Пупа");
        Mockito.when(customer.getNumberOfCustomerOperations()).thenReturn(3);
    }

}
