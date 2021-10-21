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
    private final Month mockMonth = Mockito.mock(Month.class);
    private final Customer mockCustomer = Mockito.mock(Customer.class);



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
        Assert.assertEquals(mockCustomer.getSaldoOfCustomer(),customerService2.getCustomersWithTransaction().get(0).getSaldoOfCustomer());
        Mockito.verify(mockCustomer).getSaldoOfCustomer();
    }

    @Test
    public void getCustomerNameTestWithMock() {
        Assert.assertEquals(mockCustomer.getCustomerName(),customerService2.getCustomersWithTransaction().get(0).getCustomerName());
        Mockito.verify(mockCustomer).getCustomerName();
    }
    @Test
    public void getNumberOfCustomerOperationsTestWithMock() {
        Assert.assertEquals(mockCustomer.getNumberOfCustomerOperations(),customerService2.getCustomersWithPositiveBalance().get(0).getNumberOfCustomerOperations());
        Mockito.verify(mockCustomer).getNumberOfCustomerOperations();
    }

    @Test
    public void getMonthNameTestWithMock23() {
        Assert.assertEquals(mockMonth.getMonthName(),customerService2.getCustomersWithWinterTransaction().get(0).getMonths().get(2).getMonthName());
        Mockito.verify(mockMonth).getMonthName();
    }

    @Test
    public void getNumberOfMonthOperationsWithMock() {
        Assert.assertEquals(mockMonth.getSaldoOfMonth(),customerService2.getCustomersWithWinterTransaction().get(0).getMonths().get(2).getSaldoOfMonth());
        Mockito.verify(mockMonth).getSaldoOfMonth();
    }

    @Test
    public void getNumberOfMonthOperationsTestWithMock() {
        Assert.assertEquals(mockMonth.getNumberOfMonthOperations(),customerService2.getCustomersWithWinterTransaction().get(0).getMonths().get(2).getNumberOfMonthOperations());
        Mockito.verify(mockMonth).getNumberOfMonthOperations();
    }

    private void mockingOfMonth(){
        Mockito.when(mockMonth.getNumberOfMonthOperations()).thenReturn(1);
        Mockito.when(mockMonth.getSaldoOfMonth()).thenReturn(1488);
        Mockito.when(mockMonth.getMonthName()).thenReturn(NameOfMonths.INCORRECT);
        Mockito.verifyNoMoreInteractions(mockMonth);
    }
    private void mockingOfCustomer(){
        Mockito.when(mockCustomer.getSaldoOfCustomer()).thenReturn(2589);
        Mockito.when(mockCustomer.getCustomerName()).thenReturn("Пупа");
        Mockito.when(mockCustomer.getNumberOfCustomerOperations()).thenReturn(3);
        Mockito.verifyNoMoreInteractions(mockCustomer);
    }

}
