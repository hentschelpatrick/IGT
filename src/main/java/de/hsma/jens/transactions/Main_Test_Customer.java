package de.hsma.jens.transactions;

import de.hsma.jens.models.OurCustomer;

import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.*;

public class Main_Test_Customer {
    //accessing JBoss's Transaction can be done differently but this one works nicely
    static TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();

    //build the EntityManagerFactory as you would build in in Hibernate ORM
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory(
            "OGM_MYSQL");


    private static Logger logger = Logger.getRootLogger();

    public static void main(String[] args) {
        try {

            logger.info("TA begins");
            EntityManager entityManager = emf.createEntityManager();
            tm.begin();

            OurCustomer customer = new OurCustomer();
            customer.setAge(12);
            customer.setEmail("test@test.de");
            customer.setCountry("DE");
            customer.setCurrent_state("NONE");
            customer.setFirst_name("first");
            customer.setLast_name("lastname");
            customer.setMiles_flown_yearly(0L);
            customer.setTotal_miles_flown(210L);

            entityManager.persist(customer);

            entityManager.flush();
            entityManager.close();
            tm.commit();

            logger.info("Update successfully persisted.");


        } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | RollbackException e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }


    }


}
