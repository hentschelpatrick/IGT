import de.igt.controllers.CustomerController;
import de.igt.models.Customer;
import de.igt.tools.Config;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CustomerControllerTest {
    long queryStart, queryEnd, queryTime;

    @Before
    public void setUp() throws Exception {

        this.queryStart = System.currentTimeMillis();

        CustomerController custController = new CustomerController();

        custController.deleteAllCustomers();
        custController.createCustomers();


    }

    @After
    public void tearDown() throws Exception {

        CustomerController custController = new CustomerController();

        custController.deleteAllCustomers();

        this.queryEnd = System.currentTimeMillis();
        this.queryTime = queryEnd - queryStart;
        System.out.println("++++++++++QUERY_TIME FOR OPERATIONS: " + this.queryTime + "++++++++++");
    }


    /*
    @Test
    public void testB_readCustomerTest() {
        CustomerController custController = new CustomerController();

        Customer cTest = custController.getCustomer("test");

        assertEquals("test", cTest.getEMAIL(), 0.0001);


    }*/


    @Test
    public void testC_getAllCustomerTest() {
        CustomerController custController = new CustomerController();

        ArrayList<Customer> cList = (ArrayList<Customer>) custController.getAllCustomers();


        assertEquals(Config.NUMBER_OF_CUSTOMERS, cList.size(), 0.0001);


    }


    /*
    @Test
    public void testD_updateCustomerTest() {
        CustomerController custController = new CustomerController();
        Customer custTest = null;

        List<String> cIDs = custController.getAllCustomerMails();
        List<Customer> customersToUpdate = new ArrayList<Customer>();

        for (String email : cIDs) {
            //find a customer to update
            //Customer cTest = custController.getCustomer(email);
            Customer cTest = new Customer();

            cTest.setEMAIL("email_1");
            cTest.setFIRST_NAME("fname_1");
            cTest.setLAST_NAME("lname-1");
            cTest.setADDRESS("address-1");
            cTest.setCOUNTRY("country-1");
            cTest.setAGE(12);
            cTest.setMILES_FLOWN_YEAR(12314241203L);
            cTest.setTOTAL_MILES_FLOWN(12314241203L);

            customersToUpdate.add(cTest);
        }

        custController.updateCustomer(customersToUpdate);


        //retrieve an updated customer from the datastore
        custTest = custController.getCustomer("email_1");

        assertEquals("fname_1", custTest.getFIRST_NAME(), 0.0001);


    }*/


    @Test(expected = NullPointerException.class)
    public void testF_deleteCustomerTest() {
        CustomerController custController = new CustomerController();


        Customer cTest = custController.getCustomer("test");

        custController.deleteCustomer("test");

        cTest = custController.getCustomer("test");

        cTest.getEMAIL();

        //fail("NullPointerException");

    }


    @Test
    public void testG_deleteAllCustomerTest() {
        CustomerController custController = new CustomerController();

        custController.deleteAllCustomers();

    }

}
