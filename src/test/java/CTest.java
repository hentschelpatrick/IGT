import de.igt.controllers.CustomerTestController;
import de.igt.models.CustomerTest;
import de.igt.tools.Config;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CTest {

    @Before
    public void setUp() throws Exception {

        CustomerTestController custController = new CustomerTestController();

        custController.deleteAllCustomers();
        custController.createCustomers();


    }

    @After
    public void tearDown() throws Exception {

        CustomerTestController custController = new CustomerTestController();

        custController.deleteAllCustomers();


    }


    @Test
    public void testB_readCustomerTest() {
        CustomerTestController custController = new CustomerTestController();

        CustomerTest cTest = custController.getCustomer("test");

        assertEquals("test", cTest.getEMAIL(), 0.0001);


    }


    @Test
    public void testC_getAllCustomerTest() {
        CustomerTestController custController = new CustomerTestController();

        ArrayList<CustomerTest> cList = (ArrayList<CustomerTest>) custController.getAllCustomers();


        assertEquals(Config.NUMBER_OF_CUSTOMERS, cList.size(), 0.0001);


    }


    @Test
    public void testD_updateCustomerTest() {
        CustomerTestController custController = new CustomerTestController();
        CustomerTest custTest = null;

        List<String> cIDs = custController.getAllCustomerMails();
        List<CustomerTest> customersToUpdate = new ArrayList<CustomerTest>();

        for (String email : cIDs) {
            //find a customer to update
            //Customer cTest = custController.getCustomer(email);
            CustomerTest cTest = new CustomerTest();

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


        //retrieve an updated customertest from the datastore
        custTest = custController.getCustomer("email_1");

        assertEquals("fname_1", custTest.getFIRST_NAME(), 0.0001);


    }


    @Test(expected = NullPointerException.class)
    public void testF_deleteCustomerTest() {
        CustomerTestController custController = new CustomerTestController();


        CustomerTest cTest = custController.getCustomer("test");

        custController.deleteCustomer("test");

        cTest = custController.getCustomer("test");

        cTest.getEMAIL();

        //fail("NullPointerException");

    }


    @Test
    public void testG_deleteAllCustomerTest() {
        CustomerTestController custController = new CustomerTestController();

        custController.deleteAllCustomers();

    }

}
