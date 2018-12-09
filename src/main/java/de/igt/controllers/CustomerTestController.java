package de.igt.controllers;

import de.igt.models.CustomerTest;
import de.igt.tools.Config;
import de.igt.tools.TestPopulator;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.*;

import java.util.ArrayList;
import java.util.List;

public class CustomerTestController {

    private static Logger logger = Logger.getRootLogger();
    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);


    public void createCustomer(String email, String firstname, String lastname, String address, int age, String country, long miles_flown_year, long total_miles_flown) {
        CustomerTest customer = new CustomerTest();
        customer.setEMAIL(email);
        customer.setFIRST_NAME(firstname);
        customer.setLAST_NAME(lastname);
        customer.setADDRESS(address);
        customer.setCOUNTRY(country);
        customer.setAGE(age);
        customer.setMILES_FLOWN_YEAR(miles_flown_year);
        customer.setTOTAL_MILES_FLOWN(total_miles_flown);

        try {
            logger.info("\n\nCreating CustomerTest TA begins\n\n");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            em.persist(customer);
            em.flush();
            em.close();
            tm.commit();
            long queryEnd = System.currentTimeMillis();
            logger.info("\n\nCreated CustomerTest TA ends\n\n");
            logger.info("\n\nCreation of CustomerTest took ".concat(String.valueOf(queryEnd - queryStart)).concat("ms.\n\n"));
        } catch (SystemException | NotSupportedException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
            e.printStackTrace();
        }
    }

    public void createCustomers() {

        List<CustomerTest> cList = TestPopulator.populateCustomerAsList(Config.NUMBER_OF_CUSTOMERS);


        try {
            logger.info("Create customers_test TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            for (CustomerTest c : cList) {
                em.persist(c);
            }

            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();

            logger.info("Create customers_test TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info(cList.size() + " customers_test persisted in DB in " + queryTime + " ms.");

            //String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " CREATE: " + cList.size() + " " + queryTime + "\n");

            //Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);


        } catch (SystemException | NotSupportedException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
            e.printStackTrace();
        }

    }

    public void updateCustomer(CustomerTest c) {
        try {
            logger.info("\n\nUpdate CustomerTest TA begins\n\n");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();
            long queryStart = System.currentTimeMillis();

            CustomerTest customerToUpdate = em.find(CustomerTest.class, c.getEMAIL());
            logger.info("\n\nFound CustomerTest: " + customerToUpdate.toString().concat("\n\n"));
            logger.info("\n\nUpdating CustomerTest...");
            customerToUpdate = c;
            em.merge(customerToUpdate);

            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();

            logger.info("\n\nUpdate CustomerTest TA ends\n");
            long queryTime = queryEnd - queryStart;
            logger.info("\nCustomerTest successfully persisted in " + queryTime + " ms.\n\n");
        } catch (NotSupportedException | SystemException | HeuristicRollbackException | RollbackException | HeuristicMixedException e) {
            e.printStackTrace();
        }
    }

    public void updateCustomer(List<CustomerTest> cList) {
        try {
            EntityManager em = emf.createEntityManager();
            logger.info("\n\nUpdate CustomerTest TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            for (CustomerTest c : cList) {
                em.merge(c);
            }
            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();

            logger.info("\n\nUpdate CustomerTest TA ends");
            long queryTime = queryEnd - queryStart;
            logger.info("\n\nUpdates of " + cList.size() + " CustomerTest successfully persisted in " + queryTime + " ms.\n\n");
            //String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " UPDATE: " + cList.size() + " " + queryTime + "\n");
            //Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);
        } catch (NotSupportedException | SystemException | HeuristicMixedException | RollbackException | HeuristicRollbackException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllCustomers() {
        List<String> cust;
        try {
            cust = getAllCustomerMails();
            logger.info("\n\nDelete all CustomerTest TA begins");
            long queryStart = System.currentTimeMillis();

            for (String id : cust) {
                deleteCustomer(id);
            }

            long queryEnd = System.currentTimeMillis();
            logger.info("\n\nDelete all CustomerTest TA ends");
            long queryTime = queryEnd - queryStart;

            logger.info("\n\n" + cust.size() + " CustomerTest successfully deleted in " + queryTime + " ms.\n\n");
            //String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " DELETE: " + cust.size() + " " + queryTime + "\n");
            //Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(String customerMail) {
        try {
            logger.info("\n\nDelete CustomerTest TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            CustomerTest cust = em.find(CustomerTest.class, customerMail);
            logger.info("\n\nFound CustomerTest: " + cust.toString());
            logger.info("\n\nDeleting CustomerTest...");
            em.remove(cust);

            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();
            logger.info("\n\nDelete CustomerTest TA ends");

            long queryTime = queryEnd - queryStart;
            logger.info("\n\nCustomerTest successfully deleted in " + queryTime + " ms.\n\n");

        } catch (NotSupportedException | SystemException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
    }


    public CustomerTest getCustomer(String customerEmail) {
        CustomerTest cust = null;
        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();
            logger.info("\n\nDelete CustomerTest TA begins");

            cust = em.find(CustomerTest.class, customerEmail);
            logger.info("\n\nFound CustomerTest: " + cust.toString() + "\n\n");


            em.flush();
            em.close();
            tm.commit();
            logger.info("TA ends\n\n");


        } catch (NotSupportedException | SystemException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
        return cust;
    }

    public List<CustomerTest> getAllCustomers() {

        List<CustomerTest> cIDs = new ArrayList<>();

        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT E FROM CustomerTest E");
            Query q = em.createQuery(queryString);

            logger.info("\n\nGet all CustomerTest TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            cIDs = q.getResultList();

            long queryEnd = System.currentTimeMillis();
            em.flush();
            em.close();
            tm.commit();
            logger.info("\n\nGet all CustomerTest TA ends");
            long queryTime = queryEnd - queryStart;
            logger.info("\n\nFound " + cIDs.size() + " CustomerTest in " + queryTime + " ms.\n\n");
        } catch (NotSupportedException | SystemException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
        return cIDs;
    }


    public List<String> getAllCustomerMails() {

        List<CustomerTest> cIDs = new ArrayList<>();
        List<String> returnList = new ArrayList<>();

        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT E FROM CustomerTest E");
            Query q = em.createQuery(queryString);

            logger.info("\n\nGet all CustomerMails TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            cIDs = q.getResultList();

            for (CustomerTest c : cIDs) {
                returnList.add(c.getEMAIL());
            }

            long queryEnd = System.currentTimeMillis();
            em.flush();
            em.close();
            tm.commit();
            logger.info("\n\nGet all CustomerMails TA ends");
            long queryTime = queryEnd - queryStart;
            logger.info("\n\nFound " + cIDs.size() + " customer mails in " + queryTime + " ms.\n\n");
        } catch (NotSupportedException | SystemException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
        return returnList;
    }

}
