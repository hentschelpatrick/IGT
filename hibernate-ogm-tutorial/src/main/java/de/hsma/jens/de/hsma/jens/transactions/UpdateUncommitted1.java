package de.hsma.jens.de.hsma.jens.transactions;

import de.hsma.jens.controllers.CustomerController;
import de.hsma.jens.models.Customer;
import org.apache.log4j.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.*;

/**
 * Created by jenskohler on 06.12.17.
 */
public class UpdateUncommitted1 {
    //accessing JBoss's Transaction can be done differently but this one works nicely
    static TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();

    //build the EntityManagerFactory as you would build in in Hibernate ORM
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory(
            "OGM_MYSQL");


    private static Logger logger = Logger.getRootLogger();


    public static void main(String[] args) throws InterruptedException {

        CustomerController customerController = new CustomerController();
        customerController.createCustomer("controllerGenerated@test.de", "a", "b", "Straße", "01762321123", "DE", "0129123102", 0, 0);
        Thread.sleep(10000);

        Customer customer = customerController.getCustomer("controllerGenerated@test.de");
        System.out.println(customer.toString());

        customer.setMiles_flown_year(1000L);
        customerController.updateCustomer(customer);
        Thread.sleep(10000);
        customerController.deleteCustomer("controllerGenerated@test.de");
        Thread.sleep(10000);
        System.exit(0);
    }
}
