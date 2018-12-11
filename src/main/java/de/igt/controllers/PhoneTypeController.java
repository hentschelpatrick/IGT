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

public class PhoneTypeController implements CRUD_Interface<PhoneType, String> {
    private static Logger logger = Logger.getRootLogger();
    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);


    public PhoneType createPhoneType(String pId, int pnumber, String type) {
        PhoneType pt = new PhoneType();
        pt.setPHONE_ID(pId);
        pt.setPHONE_NUMBER(pnumber);
        pt.setPHONE_TYPE(type);
        return pt;
    }

    @Override
    public void create(PhoneType object) {
        try {
            logger.info("\n\nCreating PhoneType TA begins\n\n");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            em.persist(object);
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

    @Override
    public void create_demo(List<PhoneType> objects) {
    }

    @Override
    public void update(PhoneType object) {
        try {
            logger.info("\n\nUpdate PhoneType TA begins\n\n");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();
            long queryStart = System.currentTimeMillis();
            logger.info("\n\nUpdating PhoneType...");
            em.merge(object);
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

    @Override
    public void update(List<PhoneType> objects) {
        try {
            EntityManager em = emf.createEntityManager();
            logger.info("\n\nUpdate PhoneType TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            for (PhoneType c : objects) {
                em.merge(c);
            }
            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();

            logger.info("\n\nUpdate PhoneType TA ends");
            long queryTime = queryEnd - queryStart;
            logger.info("\n\nUpdates of " + objects.size() + " PhoneType successfully persisted in " + queryTime + " ms.\n\n");
            //String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " UPDATE: " + cList.size() + " " + queryTime + "\n");
            //Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);
        } catch (NotSupportedException | SystemException | HeuristicMixedException | RollbackException | HeuristicRollbackException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(PhoneType object) {
        try {
            logger.info("\n\nDelete PhoneType TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            logger.info("\n\nDeleting PhoneType...");
            em.remove(object);

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

    @Override
    public void deleteAll() {
        List<PhoneType> pt;
        try {
            pt = readAll();
            logger.info("\n\nDelete all PhoneTypes TA begins");
            long queryStart = System.currentTimeMillis();

            for (PhoneType phoneType : pt) {
                delete(phoneType);
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

    @Override
    public PhoneType read(String Key) {
        PhoneType phoneType = null;
        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();
            logger.info("\n\nDelete PhoneType TA begins");

            phoneType = em.find(PhoneType.class, Key);
            logger.info("\n\nFound PhoneType: " + phoneType.toString() + "\n\n");

            em.flush();
            em.close();
            tm.commit();
            logger.info("TA ends\n\n");
        } catch (NotSupportedException | SystemException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
        return phoneType;
    }

    @Override
    public List<PhoneType> readAll() {
        List<PhoneType> cIDs = new ArrayList<>();
        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT E FROM PhoneType E");
            Query q = em.createQuery(queryString);

            logger.info("\n\nGet all PhoneTypeIDs TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();
            long queryStart = System.currentTimeMillis();
            cIDs = q.getResultList();
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
        return cIDs;
    }
}
