package de.igt.controllers;

import de.igt.models.Airport;
import de.igt.models.FlightSegment;
import de.igt.tools.Config;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.*;
import java.util.ArrayList;
import java.util.List;


public class FlightSegmentController {
    private static Logger logger = Logger.getRootLogger();
    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);


    public void createFlightsegment(String name, Airport departureAirport, Airport arrivalAirport, int distanceInMiles) {
        FlightSegment flightsegments = new FlightSegment();
        flightsegments.setNAME(name);
        flightsegments.setDEPARTURE_AIRPORT(departureAirport);
        flightsegments.setARRIVAL_AIRPORT(arrivalAirport);
        flightsegments.setDISTANCE_MILES(distanceInMiles);
        try {
            logger.info("\n\nCreating Flightsegments TA begins\n\n");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            em.persist(flightsegments);
            em.flush();
            em.close();
            tm.commit();
            long queryEnd = System.currentTimeMillis();
            logger.info("\n\nCreated Flightsegments TA ends\n\n");
            logger.info("\n\nCreation of Flightsegments took ".concat(String.valueOf(queryEnd - queryStart)).concat("ms.\n\n"));
        } catch (SystemException | NotSupportedException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
            e.printStackTrace();
        }
    }

    public void updateFlightsegment(FlightSegment flightsegment) {
        try {
            logger.info("\n\nUpdate flightsegment TA begins\n\n");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();
            long queryStart = System.currentTimeMillis();

            FlightSegment fsUpdate = em.find(FlightSegment.class, flightsegment.getNAME());
            logger.info("\n\nFound flightsegment: " + fsUpdate.toString().concat("\n\n"));
            logger.info("\n\nUpdating flightsegment...");
            fsUpdate = flightsegment;
            em.merge(fsUpdate);

            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();

            logger.info("\n\nUpdate flightsegment TA ends\n");
            long queryTime = queryEnd - queryStart;
            logger.info("\nflightsegment successfully persisted in " + queryTime + " ms.\n\n");
        } catch (NotSupportedException | SystemException | HeuristicRollbackException | RollbackException | HeuristicMixedException e) {
            e.printStackTrace();
        }
    }

    public void updateFlightsegments(List<FlightSegment> flightsegmentsList) {
        try {
            EntityManager em = emf.createEntityManager();
            logger.info("\n\nUpdate FlightSegment TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            for (FlightSegment c : flightsegmentsList) {
                em.merge(c);
            }
            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();

            logger.info("\n\nUpdate FlightSegment TA ends");
            long queryTime = queryEnd - queryStart;
            logger.info("\n\nUpdates of " + flightsegmentsList.size() + " FlightSegment successfully persisted in " + queryTime + " ms.\n\n");
            //String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " UPDATE: " + cList.size() + " " + queryTime + "\n");
            //Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);
        } catch (NotSupportedException | SystemException | HeuristicMixedException | RollbackException | HeuristicRollbackException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllFlightsegments() {
        List<String> flightsegments;
        try {
            flightsegments = getAllFlightSegmentNames();
            logger.info("\n\nDelete all FlightSegment TA begins");
            long queryStart = System.currentTimeMillis();

            for (String id : flightsegments) {
                deleteFlightsegment(id);
            }

            long queryEnd = System.currentTimeMillis();
            logger.info("\n\nDelete all FlightSegment TA ends");
            long queryTime = queryEnd - queryStart;

            logger.info("\n\n" + flightsegments.size() + " flightsegments successfully deleted in " + queryTime + " ms.\n\n");
            //String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " DELETE: " + cust.size() + " " + queryTime + "\n");
            //Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFlightsegment(String flightsegmentID) {
        try {
            logger.info("\n\nDelete flightsegment TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            FlightSegment flightsegment = em.find(FlightSegment.class, flightsegmentID);
            logger.info("\n\nFound flightsegment: " + flightsegment.toString());
            logger.info("\n\nDeleting flightsegment...");
            em.remove(flightsegment);

            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();
            logger.info("\n\nDelete flightsegment TA ends");

            long queryTime = queryEnd - queryStart;
            logger.info("\n\nflightsegment successfully deleted in " + queryTime + " ms.\n\n");

        } catch (NotSupportedException | SystemException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
    }


    public FlightSegment getFlightsegments(String flightsegmentID) {
        FlightSegment cust = null;
        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();
            logger.info("\n\nDelete flightsegment TA begins");

            cust = em.find(FlightSegment.class, flightsegmentID);
            logger.info("\n\nFound flightsegment: " + cust.toString() + "\n\n");

            em.flush();
            em.close();
            tm.commit();
            logger.info("TA ends\n\n");
        } catch (NotSupportedException | SystemException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
        return cust;
    }


    public List<String> getAllFlightSegmentNames() {

        List<FlightSegment> cIDs = new ArrayList<>();
        List<String> returnList = new ArrayList<>();

        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT E FROM FlightSegment E");
            Query q = em.createQuery(queryString);

            logger.info("\n\nGet all FlightsegmentNames TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            cIDs = q.getResultList();

            for (FlightSegment c : cIDs) {
                returnList.add(c.getNAME());
            }

            long queryEnd = System.currentTimeMillis();
            em.flush();
            em.close();
            tm.commit();
            logger.info("\n\nGet all FlightSegmentNames TA ends");
            long queryTime = queryEnd - queryStart;
            logger.info("\n\nFound " + cIDs.size() + " FlightSegment names in " + queryTime + " ms.\n\n");
        } catch (NotSupportedException | SystemException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
        return returnList;
    }

}