import model.*;
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
public class CustomerFilterServiceTest {
    private final Customer mockCustomer = Mockito.mock(Customer.class);
    @Rule
    public ExpectedException exception = ExpectedException.none();
    private CustomerFilterService customerFilterService;
    List<Customer> customerListOfPupa = new ArrayList<>();
    List<Customer> customerListOfLupa = new ArrayList<>();
    List<Customer> customerListOfPupaAndLupa  = new ArrayList<>();
    List<AbstractMap.SimpleEntry<NameOfMonths, Integer>> testlistOfPupa = new ArrayList<>();
    List<AbstractMap.SimpleEntry<NameOfMonths, Integer>> testlistOfLupa = new ArrayList<>();
    List<AbstractMap.SimpleEntry<NameOfMonths, Integer>> testlistOfPupaAndLupa = new ArrayList<>();
    @Mock
    private CustomerService mockCustomerService;
    private final CustomerFilterService customerServiceJSONForTesting = new CustomerFilterService(new CustomerService());
    @Before
    public void setUp() throws Exception {
        customerFilterService = new CustomerFilterService(mockCustomerService);
        creatingListOfCustomers();
    }
    public void creatingListOfCustomers() {

        Operation operation1 = Operation.builder().currency(NameOfCurrency.USD).value(1471).build();
        List<Operation> operationList = Arrays.asList(new Operation[]{operation1});
        Month june = Month.builder().monthName(NameOfMonths.ИЮНЬ).saldoOfMonth(1471).numberOfMonthOperations(1).operations(operationList).build();
        Operation operation33 = Operation.builder().currency(NameOfCurrency.USD).value(1).build();
        Operation operation44 = Operation.builder().currency(NameOfCurrency.USD).value(2).build();
        Operation operation55 = Operation.builder().currency(NameOfCurrency.USD).value(3).build();
        Operation operation66 = Operation.builder().currency(NameOfCurrency.USD).value(4).build();
        List<Operation> operationListOfMarch = Arrays.asList(new Operation[]{operation33,operation44,operation55,operation66});
        Month march = Month.builder().monthName(NameOfMonths.МАРТ).saldoOfMonth(10).numberOfMonthOperations(4).operations(operationListOfMarch).build();
        List<Month> listOfMonth = Arrays.asList(new Month[]{june,march});
        Customer customerPupa = Customer.builder().customerName("Пупа").saldoOfCustomer(1481).numberOfCustomerOperations(1).months(listOfMonth).build();
        List<Customer> customerListOfPupa = Arrays.asList(customerPupa);
        this.customerListOfPupa = customerListOfPupa;
        Mockito.when(mockCustomerService.getAllCustomers()).thenReturn(customerListOfPupa);

        Operation operation2 = Operation.builder().currency(NameOfCurrency.USD).value(1470).build();
        List<Operation> operationListOfJule = Arrays.asList(new Operation[]{operation2});
        Month jule = Month.builder().monthName(NameOfMonths.ИЮЛЬ).saldoOfMonth(1470).numberOfMonthOperations(1).operations(operationListOfJule).build();
        Month may = Month.builder().monthName(NameOfMonths.МАЙ).saldoOfMonth(0).numberOfMonthOperations(0).operations(Collections.emptyList()).build();
        Operation operation3 = Operation.builder().currency(NameOfCurrency.USD).value(100500).build();
        Operation operation4 = Operation.builder().currency(NameOfCurrency.USD).value(-111555).build();
        List<Operation> operationList2 = Arrays.asList(new Operation[]{operation3, operation4});
        Month december = Month.builder().monthName(NameOfMonths.ДЕКАБРЬ).saldoOfMonth(-11055).numberOfMonthOperations(2).operations(operationList2).build();
        List<Month> listOfMonth2 = Arrays.asList(new Month[]{jule,may,december});
        Customer customerLupa = Customer.builder().customerName("Лупа").saldoOfCustomer(-9585).numberOfCustomerOperations(1).months(listOfMonth2).build();
        List<Customer> customerListOfLupa = Arrays.asList(customerLupa);
        this.customerListOfLupa = customerListOfLupa;

        List<Customer> customerListOfPupaAndLupa = Arrays.asList(new Customer[]{customerPupa,customerLupa});
        this.customerListOfPupaAndLupa = customerListOfPupaAndLupa;


    }

    @Test
    public void operationTest() {
        testlistOfPupa.add(new AbstractMap.SimpleEntry<NameOfMonths, Integer>(NameOfMonths.ИЮНЬ, 1471));
        testlistOfPupa.add(new AbstractMap.SimpleEntry<NameOfMonths, Integer>(NameOfMonths.МАРТ, 10));
        Mockito.when(mockCustomerService.getAllCustomers()).thenReturn(customerListOfPupa);

        Assert.assertEquals(testlistOfPupa, customerFilterService.getAllMonthOfAllCustomersInRubleValue());

        Mockito.verify(mockCustomerService).getAllCustomers();
        Mockito.verifyNoMoreInteractions(mockCustomerService);
    }

    @Test
    public void operationTest2() {
        testlistOfLupa.add(new AbstractMap.SimpleEntry<NameOfMonths, Integer>(NameOfMonths.ИЮЛЬ, 1470));
        testlistOfLupa.add(new AbstractMap.SimpleEntry<NameOfMonths, Integer>(NameOfMonths.МАЙ, 0));
        testlistOfLupa.add(new AbstractMap.SimpleEntry<NameOfMonths, Integer>(NameOfMonths.ДЕКАБРЬ, -11055));
        Mockito.when(mockCustomerService.getAllCustomers()).thenReturn(customerListOfLupa);

        Assert.assertEquals(testlistOfLupa, customerFilterService.getAllMonthOfAllCustomersInRubleValue());

        Mockito.verify(mockCustomerService).getAllCustomers();
        Mockito.verifyNoMoreInteractions(mockCustomerService);
    }

    @Test
    public void operationTest3() {
        testlistOfPupaAndLupa.add(new AbstractMap.SimpleEntry<NameOfMonths, Integer>(NameOfMonths.ИЮНЬ, 1471));
        testlistOfPupaAndLupa.add(new AbstractMap.SimpleEntry<NameOfMonths, Integer>(NameOfMonths.ИЮЛЬ, 1470));
        testlistOfPupaAndLupa.add(new AbstractMap.SimpleEntry<NameOfMonths, Integer>(NameOfMonths.МАРТ, 10));
        testlistOfPupaAndLupa.add(new AbstractMap.SimpleEntry<NameOfMonths, Integer>(NameOfMonths.МАЙ, 0));
        testlistOfPupaAndLupa.add(new AbstractMap.SimpleEntry<NameOfMonths, Integer>(NameOfMonths.ДЕКАБРЬ, -11055));
        Mockito.when(mockCustomerService.getAllCustomers()).thenReturn(customerListOfPupaAndLupa);

        Assert.assertEquals(testlistOfPupaAndLupa, customerFilterService.getAllMonthOfAllCustomersInRubleValue());

        Mockito.verify(mockCustomerService).getAllCustomers();
        Mockito.verifyNoMoreInteractions(mockCustomerService);
    }

    @Test
    public void getCustomersWithTransactionTest() {
        Mockito.when(mockCustomerService.getAllCustomers()).thenReturn(customerListOfPupa);

        Assert.assertEquals(customerListOfPupa,customerFilterService.getCustomersWithTransaction());

        Mockito.verify(mockCustomerService).getAllCustomers();
        Mockito.verifyNoMoreInteractions(mockCustomerService);
    }

    @Test
    public void getCustomersWithTransactionTest2() {
        Mockito.when(mockCustomerService.getAllCustomers()).thenReturn(customerListOfLupa);

        Assert.assertEquals(customerListOfLupa,customerFilterService.getCustomersWithTransaction());

        Mockito.verify(mockCustomerService).getAllCustomers();
        Mockito.verifyNoMoreInteractions(mockCustomerService);
    }

    @Test
    public void getCustomersWithTransactionTest3() {
        Mockito.when(mockCustomerService.getAllCustomers()).thenReturn(customerListOfPupaAndLupa);

        Assert.assertEquals(customerListOfPupaAndLupa,customerFilterService.getCustomersWithTransaction());

        Mockito.verify(mockCustomerService/*,Mockito.times(12)*/).getAllCustomers();
        Mockito.verifyNoMoreInteractions(mockCustomerService);
    }

    @Test
    public void getCustomersWithPositiveBalanceTest() {
        Mockito.when(mockCustomerService.getAllCustomers()).thenReturn(customerListOfPupa);

        Assert.assertEquals(customerListOfPupa,customerFilterService.getCustomersWithPositiveBalance());

        Mockito.verify(mockCustomerService).getAllCustomers();
        Mockito.verifyNoMoreInteractions(mockCustomerService);
    }

    @Test
    public void getCustomersWithPositiveBalanceTest2() {
        Mockito.when(mockCustomerService.getAllCustomers()).thenReturn(customerListOfPupaAndLupa);

        Assert.assertEquals(customerListOfPupa,customerFilterService.getCustomersWithPositiveBalance());

        Mockito.verify(mockCustomerService).getAllCustomers();
        Mockito.verifyNoMoreInteractions(mockCustomerService);
    }

    @Test
    public void getCustomersWithPositiveBalanceTest3() {
        Mockito.when(mockCustomerService.getAllCustomers()).thenReturn(customerListOfLupa);

        Assert.assertEquals(Collections.emptyList(),customerFilterService.getCustomersWithPositiveBalance());

        Mockito.verify(mockCustomerService).getAllCustomers();
        Mockito.verifyNoMoreInteractions(mockCustomerService);
    }

    @Test
    public void getCustomersWithWinterTransactionTest() {
        Mockito.when(mockCustomerService.getAllCustomers()).thenReturn(customerListOfPupa);

        Assert.assertEquals(Collections.emptyList(), customerFilterService.getCustomersWithWinterTransaction());

        Mockito.verify(mockCustomerService).getAllCustomers();
        Mockito.verifyNoMoreInteractions(mockCustomerService);
    }

    @Test
    public void getCustomersWithWinterTransactionTest2() {
        Mockito.when(mockCustomerService.getAllCustomers()).thenReturn(customerListOfPupaAndLupa);

        Assert.assertEquals(customerListOfLupa, customerFilterService.getCustomersWithWinterTransaction());

        Mockito.verify(mockCustomerService).getAllCustomers();
        Mockito.verifyNoMoreInteractions(mockCustomerService);
    }

    @Test
    public void getCustomersWithWinterTransactionTest3() {
        Mockito.when(mockCustomerService.getAllCustomers()).thenReturn(customerListOfLupa);

        Assert.assertEquals(customerListOfLupa, customerFilterService.getCustomersWithWinterTransaction());

        Mockito.verify(mockCustomerService).getAllCustomers();
        Mockito.verifyNoMoreInteractions(mockCustomerService);
    }

//    @Test
//    public void getAllCustomersTest() {
//        Mockito.when(mockCustomerService.getAllCustomers()).thenReturn(customerListOfPupa);
//
//        Assert.assertEquals(customerListOfPupa,mockCustomerService.getAllCustomers());
//
//        Mockito.verify(mockCustomerService).getAllCustomers();
//        Mockito.verifyNoMoreInteractions(mockCustomerService);
//    }
//
//    @Test
//    public void getAllCustomersTest2() {
//        Mockito.when(mockCustomerService.getAllCustomers()).thenReturn(customerListOfPupaAndLupa);
//
//        Assert.assertEquals(customerListOfPupaAndLupa,mockCustomerService.getAllCustomers());
//
//        Mockito.verify(mockCustomerService).getAllCustomers();
//        Mockito.verifyNoMoreInteractions(mockCustomerService);
//    }
//
//    @Test
//    public void getAllCustomersTest3() {
//        Mockito.when(mockCustomerService.getAllCustomers()).thenReturn(customerListOfLupa);
//
//        Assert.assertEquals(customerListOfLupa,mockCustomerService.getAllCustomers());
//
//        Mockito.verify(mockCustomerService).getAllCustomers();
//        Mockito.verifyNoMoreInteractions(mockCustomerService);
//    }

}
