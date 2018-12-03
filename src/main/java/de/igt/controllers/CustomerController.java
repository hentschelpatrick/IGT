package de.igt.controllers;


import de.igt.models.Customer;
import de.igt.tools.Config;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.*;

import de.igt.models.Status;

import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    private static Logger logger = Logger.getRootLogger();
    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);

    public void createCustomer(String email, String firstname, String lastname, String address, String phonenumber, String country, String creditcard, long miles_flown_year, long total_miles_flown, Status status) {
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setFirstname(firstname);
        customer.setLastname(lastname);
        customer.setAddress(address);
        customer.setCountry(country);
        customer.setCreditCard(creditcard);
        customer.setMiles_flown_year(miles_flown_year);
        customer.setTotal_miles_flown(total_miles_flown);
        customer.setPhonenumber(phonenumber);
        customer.setStatus(status);

        try {
            logger.info("\n\nCreating Customer TA begins\n\n");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            em.persist(customer);
            em.flush();
            em.close();
            tm.commit();
            long queryEnd = System.currentTimeMillis();
            logger.info("\n\nCreated Customer TA ends\n\n");
            logger.info("\n\nCreation of Customer took ".concat(String.valueOf(queryEnd - queryStart)).concat("ms.\n\n"));
        } catch (SystemException | NotSupportedException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
            e.printStackTrace();
        }
    }

    public void updateCustomer(Customer c) {
        try {
            logger.info("\n\nUpdate customer TA begins\n\n");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();
            long queryStart = System.currentTimeMillis();

            Customer customerToUpdate = em.find(Customer.class, c.getEmail());
            logger.info("\n\nFound customer: " + customerToUpdate.toString().concat("\n\n"));
            logger.info("\n\nUpdating customer...");
            customerToUpdate = c;
            em.merge(customerToUpdate);

            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();

            logger.info("\n\nUpdate customer TA ends\n");
            long queryTime = queryEnd - queryStart;
            logger.info("\nCustomer successfully persisted in " + queryTime + " ms.\n\n");
        } catch (NotSupportedException | SystemException | HeuristicRollbackException | RollbackException | HeuristicMixedException e) {
            e.printStackTrace();
        }
    }

    public void updateCustomer(List<Customer> cList) {
        try {
            EntityManager em = emf.createEntityManager();
            logger.info("\n\nUpdate customers TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            for (Customer c : cList) {
                em.merge(c);
            }
            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();

            logger.info("\n\nUpdate customers TA ends");
            long queryTime = queryEnd - queryStart;
            logger.info("\n\nUpdates of " + cList.size() + " customers successfully persisted in " + queryTime + " ms.\n\n");
            //String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " UPDATE: " + cList.size() + " " + queryTime + "\n");
            //Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);
        } catch (NotSupportedException | SystemException | HeuristicMixedException | RollbackException | HeuristicRollbackException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllCustomer() {
        List<String> cust;
        try {
            cust = getAllCustomerMails();
            logger.info("\n\nDelete all customers TA begins");
            long queryStart = System.currentTimeMillis();

            for (String id : cust) {
                deleteCustomer(id);
            }

            long queryEnd = System.currentTimeMillis();
            logger.info("\n\nDelete all customers TA ends");
            long queryTime = queryEnd - queryStart;

            logger.info("\n\n" + cust.size() + " customers successfully deleted in " + queryTime + " ms.\n\n");
            //String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " DELETE: " + cust.size() + " " + queryTime + "\n");
            //Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(String customerMail) {
        try {
            logger.info("\n\nDelete customer TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            Customer cust = em.find(Customer.class, customerMail);
            logger.info("\n\nFound customer: " + cust.toString());
            logger.info("\n\nDeleting customer...");
            em.remove(cust);

            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();
            logger.info("\n\nDelete customer TA ends");

            long queryTime = queryEnd - queryStart;
            logger.info("\n\nCustomer successfully deleted in " + queryTime + " ms.\n\n");

        } catch (NotSupportedException | SystemException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
    }


    public Customer getCustomer(String customerEmail) {
        Customer cust = null;
        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();
            logger.info("\n\nDelete customer TA begins");

            cust = em.find(Customer.class, customerEmail);
            logger.info("\n\nFound customer: " + cust.toString() + "\n\n");


            em.flush();
            em.close();
            tm.commit();
            logger.info("TA ends\n\n");


        } catch (NotSupportedException | SystemException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
        return cust;
    }


    public List<String> getAllCustomerMails() {

        List<Customer> cIDs = new ArrayList<>();
        List<String> returnList = new ArrayList<>();

        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT c FROM CUSTOMERS c");
            Query q = em.createQuery(queryString);

            logger.info("\n\nGet all customerIDs TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            cIDs = q.getResultList();

            for (Customer c : cIDs) {
                returnList.add(c.getEmail());
            }

            long queryEnd = System.currentTimeMillis();
            em.flush();
            em.close();
            tm.commit();
            logger.info("\n\nGet all Custom_CustomerIDs TA ends");
            long queryTime = queryEnd - queryStart;
            logger.info("\n\nFound " + cIDs.size() + " customer IDs in " + queryTime + " ms.\n\n");
        } catch (NotSupportedException | SystemException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
        return returnList;
    }


}