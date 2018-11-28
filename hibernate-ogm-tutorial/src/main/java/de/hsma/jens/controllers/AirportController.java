package de.hsma.jens.controllers;

import de.hsma.jens.models.Airport;
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

public class AirportController {
    private static Logger logger = Logger.getRootLogger();
    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);


    public void createAirport(String airportID, String airportName, String country, String address, int internationalLandingsites, int nationalLandingsites) {
        Airport airport = new Airport();
        airport.setAirport_id(airportID);
        airport.setName(airportName);
        airport.setCountry(country);
        airport.setAddress(address);
        airport.setAmount_international_landingsites(internationalLandingsites);
        airport.setAmount_national_landingsites(nationalLandingsites);
        airport.setAmount_landingsites(internationalLandingsites + nationalLandingsites);

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

    public void updateAirport(Airport airport) {
        try {
            logger.info("\n\nUpdate airport TA begins\n\n");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();
            long queryStart = System.currentTimeMillis();

            Airport airportUpdate = em.find(Airport.class, airport.getAirport_id());
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
            airports = getAllAirportMails();
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

    public void deleteAirport(String airportID) {
        try {
            logger.info("\n\nDelete airport TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            Airport airport = em.find(Airport.class, airportID);
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


    public Airport getAirports(String airportID) {
        Airport cust = null;
        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();
            logger.info("\n\nDelete airport TA begins");

            cust = em.find(Airport.class, airportID);
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


    public List<String> getAllAirportMails() {

        List<Airport> cIDs = new ArrayList<>();
        List<String> returnList = new ArrayList<>();

        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT c FROM AIRPORTS c");
            Query q = em.createQuery(queryString);

            logger.info("\n\nGet all AirportIDs TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            cIDs = q.getResultList();

            for (Airport c : cIDs) {
                returnList.add(c.getAirport_id());
            }

            long queryEnd = System.currentTimeMillis();
            em.flush();
            em.close();
            tm.commit();
            logger.info("\n\nGet all AirportIDs TA ends");
            long queryTime = queryEnd - queryStart;
            logger.info("\n\nFound " + cIDs.size() + " airport IDs in " + queryTime + " ms.\n\n");
        } catch (NotSupportedException | SystemException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
        return returnList;
    }


}