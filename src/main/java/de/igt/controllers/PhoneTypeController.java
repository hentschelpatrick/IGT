package de.igt.controllers;

import de.igt.models.PhoneType;
import de.igt.tools.Config;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.*;
import java.util.ArrayList;
import java.util.List;

public class PhoneTypeController {
    private static Logger logger = Logger.getRootLogger();
    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);


    public void createPhoneType(String pId, int pnumber, String type) {
        PhoneType pt = new PhoneType();
        pt.setPHONE_ID(pId);
        pt.setPHONE_NUMBER(pnumber);
        pt.setPHONE_TYPE(type);
        try {
            logger.info("\n\nCreating PhoneType TA begins\n\n");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            em.persist(pt);
            em.flush();
            em.close();
            tm.commit();
            long queryEnd = System.currentTimeMillis();
            logger.info("\n\nCreated PhoneType TA ends\n\n");
            logger.info("\n\nCreation of PhoneType took ".concat(String.valueOf(queryEnd - queryStart)).concat("ms.\n\n"));
        } catch (SystemException | NotSupportedException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
            e.printStackTrace();
        }
    }

    public void updatePhoneType(PhoneType pt) {
        try {
            logger.info("\n\nUpdate PhoneType TA begins\n\n");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();
            long queryStart = System.currentTimeMillis();

            PhoneType psUpdate = em.find(PhoneType.class, pt.getPHONE_ID());
            logger.info("\n\nFound PhoneType: " + psUpdate.toString().concat("\n\n"));
            logger.info("\n\nUpdating PhoneType...");
            psUpdate = pt;
            em.merge(psUpdate);

            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();

            logger.info("\n\nUpdate PhoneType TA ends\n");
            long queryTime = queryEnd - queryStart;
            logger.info("\nPhoneType successfully persisted in " + queryTime + " ms.\n\n");
        } catch (NotSupportedException | SystemException | HeuristicRollbackException | RollbackException | HeuristicMixedException e) {
            e.printStackTrace();
        }
    }

    public void updatePhoneTypes(List<PhoneType> ptList) {
        try {
            EntityManager em = emf.createEntityManager();
            logger.info("\n\nUpdate PhoneType TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            for (PhoneType c : ptList) {
                em.merge(c);
            }
            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();

            logger.info("\n\nUpdate PhoneType TA ends");
            long queryTime = queryEnd - queryStart;
            logger.info("\n\nUpdates of " + ptList.size() + " PhoneType successfully persisted in " + queryTime + " ms.\n\n");
            //String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " UPDATE: " + cList.size() + " " + queryTime + "\n");
            //Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);
        } catch (NotSupportedException | SystemException | HeuristicMixedException | RollbackException | HeuristicRollbackException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllPhoneTypes() {
        List<String> pt;
        try {
            pt = getAllPhoneTypeIds();
            logger.info("\n\nDelete all PhoneTypes TA begins");
            long queryStart = System.currentTimeMillis();

            for (String id : pt) {
                deletePhoneType(id);
            }

            long queryEnd = System.currentTimeMillis();
            logger.info("\n\nDelete all PhoneTypes TA ends");
            long queryTime = queryEnd - queryStart;

            logger.info("\n\n" + pt.size() + " PhoneTypes successfully deleted in " + queryTime + " ms.\n\n");
            //String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " DELETE: " + cust.size() + " " + queryTime + "\n");
            //Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletePhoneType(String phonetypeID) {
        try {
            logger.info("\n\nDelete PhoneType TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            PhoneType phonetype = em.find(PhoneType.class, phonetypeID);
            logger.info("\n\nFound PhoneType: " + phonetype.toString());
            logger.info("\n\nDeleting PhoneType...");
            em.remove(phonetype);

            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();
            logger.info("\n\nDelete PhoneType TA ends");

            long queryTime = queryEnd - queryStart;
            logger.info("\n\nPhoneType successfully deleted in " + queryTime + " ms.\n\n");

        } catch (NotSupportedException | SystemException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
    }


    public PhoneType getPhoneTypes(String phonetypeID) {
        PhoneType cust = null;
        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();
            logger.info("\n\nDelete PhoneType TA begins");

            cust = em.find(PhoneType.class, phonetypeID);
            logger.info("\n\nFound PhoneType: " + cust.toString() + "\n\n");

            em.flush();
            em.close();
            tm.commit();
            logger.info("TA ends\n\n");
        } catch (NotSupportedException | SystemException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
        return cust;
    }


    public List<String> getAllPhoneTypeIds() {

        List<PhoneType> cIDs = new ArrayList<>();
        List<String> returnList = new ArrayList<>();

        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT E FROM PhoneType E");
            Query q = em.createQuery(queryString);

            logger.info("\n\nGet all PhoneTypeIDs TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            cIDs = q.getResultList();

            for (PhoneType c : cIDs) {
                returnList.add(c.getPHONE_ID());
            }

            long queryEnd = System.currentTimeMillis();
            em.flush();
            em.close();
            tm.commit();
            logger.info("\n\nGet all PhoneTypeIDs TA ends");
            long queryTime = queryEnd - queryStart;
            logger.info("\n\nFound " + cIDs.size() + " PhoneType IDs in " + queryTime + " ms.\n\n");
        } catch (NotSupportedException | SystemException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
        return returnList;
    }
}
