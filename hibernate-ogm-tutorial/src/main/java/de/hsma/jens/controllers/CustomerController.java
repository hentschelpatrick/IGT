package de.hsma.jens.controllers;

import de.hsma.jens.models.Customer;
import de.hsma.jens.tools.Config;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    private static Logger logger = Logger.getRootLogger();
    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);

    public void createCustomer(String email, String firstname, String lastname, String address, String phonenumber, String country, String creditcard, long miles_flown_year, long total_miles_flown) {
        Customer customer = new Customer();
        customer.setEmail(email);
        customer.setFirst_name(firstname);
        customer.setLast_name(lastname);
        customer.setAddress(address);
        customer.setCountry(country);
        customer.setCreditCard(creditcard);
        customer.setMiles_flown_year(miles_flown_year);
        customer.setTotal_miles_flown(total_miles_flown);
        customer.setPhonenumber(phonenumber);

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
            logger.info("Created Customer TA ends");
            logger.info("Creation of Customer took ".concat(String.valueOf(queryEnd - queryStart)));
        } catch (SystemException | NotSupportedException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
            e.printStackTrace();
        }
    }

    public void updateCustomer(Customer c) {
        try {
            logger.info("Update customer TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();
            long queryStart = System.currentTimeMillis();

            Customer customerToUpdate = em.find(Customer.class, c.getEmail());
            logger.info("Found customer: " + customerToUpdate.toString());
            logger.info("Updating customer...");
            customerToUpdate = c;
            em.merge(customerToUpdate);

            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();

            logger.info("Update customer TA ends");
            long queryTime = queryEnd - queryStart;
            logger.info("Customer successfully persisted in " + queryTime + " ms.");
        } catch (NotSupportedException | SystemException | HeuristicRollbackException | RollbackException | HeuristicMixedException e) {
            e.printStackTrace();
        }
    }

    public void updateCustomer(List<Customer> cList) {
        try {
            EntityManager em = emf.createEntityManager();
            logger.info("Update customers TA begins");
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

            logger.info("Update customers TA ends");
            long queryTime = queryEnd - queryStart;
            logger.info("Updates of " + cList.size() + " customers successfully persisted in " + queryTime + " ms.");
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
            logger.info("Delete all customers TA begins");
            long queryStart = System.currentTimeMillis();

            for (String id : cust) {
                deleteCustomer(id);
            }

            long queryEnd = System.currentTimeMillis();
            logger.info("Delete all customers TA ends");
            long queryTime = queryEnd - queryStart;

            logger.info(cust.size() + " customers successfully deleted in " + queryTime + " ms.");
            //String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " DELETE: " + cust.size() + " " + queryTime + "\n");
            //Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCustomer(String customerMail) {
        try {
            logger.info("Delete customer TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            Customer cust = em.find(Customer.class, customerMail);
            logger.info("Found customer: " + cust.toString());
            logger.info("Deleting customer...");
            em.remove(cust);

            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();
            logger.info("Delete customer TA ends");

            long queryTime = queryEnd - queryStart;
            logger.info("Customer successfully deleted in " + queryTime + " ms.");

        } catch (NotSupportedException e) {
            e.printStackTrace();
        } catch (SystemException e) {
            e.printStackTrace();
        } catch (HeuristicMixedException e) {
            e.printStackTrace();
        } catch (HeuristicRollbackException e) {
            e.printStackTrace();
        } catch (RollbackException e) {
            e.printStackTrace();
        }
    }


    public Customer getCustomer(String customerEmail) {
        Customer cust = null;
        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();


            cust = em.find(Customer.class, customerEmail);
            logger.info("Found customer: " + cust.toString());


            em.flush();
            em.close();
            tm.commit();
            logger.info("TA ends");


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

            logger.info("Get all customerIDs TA begins");
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
            logger.info("Get all Custom_CustomerIDs TA ends");
            long queryTime = queryEnd - queryStart;
            logger.info("Found " + cIDs.size() + " customer IDs in " + queryTime + " ms.");
        } catch (NotSupportedException | SystemException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
        return returnList;
    }


}
