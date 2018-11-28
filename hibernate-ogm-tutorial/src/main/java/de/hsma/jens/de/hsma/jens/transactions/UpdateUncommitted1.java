package de.hsma.jens.de.hsma.jens.transactions;

import de.hsma.jens.controllers.CustomerController;
import de.hsma.jens.models.Custom_Customer;
import de.hsma.jens.models.Customer;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.*;
import java.util.Date;

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


    public static void main(String[] args) {

        Custom_Customer custom_customer = new Custom_Customer();

        custom_customer.setEmail("test@test.de");
        custom_customer.setAddress("test");
        custom_customer.setCountry("DE");
        custom_customer.setCreditCard("132031232");
        custom_customer.setLast_name("asd");
        custom_customer.setFirst_name("2asdaso");
        custom_customer.setTotal_miles_flown(0);
        custom_customer.setMiles_flown_year(0);

        try {
            tm.begin();

            logger.info("TA begins");
            EntityManager em = emf.createEntityManager();

            em.persist(custom_customer);

            em.flush();
            em.close();
            tm.commit();
            logger.info("Update successfully persisted.");

        } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | RollbackException e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }


    }
}
