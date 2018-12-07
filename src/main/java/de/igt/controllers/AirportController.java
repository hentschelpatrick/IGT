package de.igt.controllers;

import de.igt.models.Airport;
import de.igt.tools.AirportPopulator;
import de.igt.tools.Config;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.*;
import java.util.ArrayList;
import java.util.List;

public class AirportController {
    private static Logger logger = Logger.getRootLogger();
    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);


    public void createAirport(String airportName, String country, String address, int internationalLandingsites, int nationalLandingsites) {
        Airport airport = new Airport();
        airport.setNAME(airportName);
        airport.setCOUNTRY(country);
        airport.setADDRESS(address);
        airport.setAMOUNT_INTERNATIONAL_LANDINGSITES(internationalLandingsites);
        airport.setAMOUNT_NATIONAL_LANDINGSITES(nationalLandingsites);

        try {
            logger.info("\n\nCreating Airport TA begins\n\n");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            em.persist(airport);
            em.flush();
            em.close();
            tm.commit();
            long queryEnd = System.currentTimeMillis();
            logger.info("\n\nCreated airport TA ends\n\n");
            logger.info("\n\nCreation of airport took ".concat(String.valueOf(queryEnd - queryStart)).concat("ms.\n\n"));
        } catch (SystemException | NotSupportedException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
            e.printStackTrace();
        }
    }

    public void createAirports() {

        List<Airport> aList = AirportPopulator.populateAirportAsList(Config.NUMBER_OF_AIRPOTS);

        try {
            logger.info("Create airpots TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            for (Airport c : aList) {
                em.persist(c);
            }

            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();

            logger.info("Create airpots TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info(aList.size() + " customers airpots in DB in " + queryTime + " ms.");

            //String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " CREATE: " + cList.size() + " " + queryTime + "\n");

            //Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);


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

    public void updateAirport(Airport airport) {
        try {
            logger.info("\n\nUpdate airport TA begins\n\n");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();
            long queryStart = System.currentTimeMillis();

            Airport airportUpdate = em.find(Airport.class, airport.getNAME());
            logger.info("\n\nFound airport: " + airportUpdate.toString().concat("\n\n"));
            logger.info("\n\nUpdating airport...");
            airportUpdate = airport;
            em.merge(airportUpdate);

            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();

            logger.info("\n\nUpdate airport TA ends\n");
            long queryTime = queryEnd - queryStart;
            logger.info("\nairport successfully persisted in " + queryTime + " ms.\n\n");
        } catch (NotSupportedException | SystemException | HeuristicRollbackException | RollbackException | HeuristicMixedException e) {
            e.printStackTrace();
        }
    }

    public void updateAirport(List<Airport> airportList) {
        try {
            EntityManager em = emf.createEntityManager();
            logger.info("\n\nUpdate airport TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            for (Airport c : airportList) {
                em.merge(c);
            }
            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();

            logger.info("\n\nUpdate airport TA ends");
            long queryTime = queryEnd - queryStart;
            logger.info("\n\nUpdates of " + airportList.size() + " airport successfully persisted in " + queryTime + " ms.\n\n");
            //String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " UPDATE: " + cList.size() + " " + queryTime + "\n");
            //Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);
        } catch (NotSupportedException | SystemException | HeuristicMixedException | RollbackException | HeuristicRollbackException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllAirports() {
        List<String> airports;
        try {
            airports = getAllAirportNames();
            logger.info("\n\nDelete all airport TA begins");
            long queryStart = System.currentTimeMillis();

            for (String id : airports) {
                deleteAirport(id);
            }

            long queryEnd = System.currentTimeMillis();
            logger.info("\n\nDelete all airport TA ends");
            long queryTime = queryEnd - queryStart;

            logger.info("\n\n" + airports.size() + " airport successfully deleted in " + queryTime + " ms.\n\n");
            //String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " DELETE: " + cust.size() + " " + queryTime + "\n");
            //Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteAirport(String airportName) {
        try {
            logger.info("\n\nDelete airport TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            Airport airport = em.find(Airport.class, airportName);
            logger.info("\n\nFound airport: " + airport.toString());
            logger.info("\n\nDeleting airport...");
            em.remove(airport);

            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();
            logger.info("\n\nDelete airport TA ends");

            long queryTime = queryEnd - queryStart;
            logger.info("\n\nairport successfully deleted in " + queryTime + " ms.\n\n");

        } catch (NotSupportedException | SystemException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
    }


    public Airport getAirports(String airportName) {
        Airport cust = null;
        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();
            logger.info("\n\nDelete airport TA begins");

            cust = em.find(Airport.class, airportName);
            logger.info("\n\nFound airport: " + cust.toString() + "\n\n");

            em.flush();
            em.close();
            tm.commit();
            logger.info("TA ends\n\n");
        } catch (NotSupportedException | SystemException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
        return cust;
    }


    public List<String> getAllAirportNames() {

        List<Airport> cIDs = new ArrayList<>();
        List<String> returnList = new ArrayList<>();

        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT A FROM Airport A");
            Query q = em.createQuery(queryString);

            logger.info("\n\nGet all AirportNames TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            cIDs = q.getResultList();

            for (Airport c : cIDs) {
                returnList.add(c.getNAME());
            }

            long queryEnd = System.currentTimeMillis();
            em.flush();
            em.close();
            tm.commit();
            logger.info("\n\nGet all AirportNames TA ends");
            long queryTime = queryEnd - queryStart;
            logger.info("\n\nFound " + cIDs.size() + " airport IDs in " + queryTime + " ms.\n\n");
        } catch (NotSupportedException | SystemException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
        return returnList;
    }
}
