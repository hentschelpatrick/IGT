package de.hsma.jens.controllers;

import de.hsma.jens.models.Custom_Customer;
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

public class CustomCustomerController {
    private static Logger logger = Logger.getRootLogger();
    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);


    public Custom_Customer getCustomer(String customerEmail) {
        Custom_Customer cust = null;
        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();


            cust = em.find(Custom_Customer.class, customerEmail);
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


    public List<String> getAllCustomerMails(){

        List<Custom_Customer> cIDs = new ArrayList<>();
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

            for (Custom_Customer c : cIDs) {
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
