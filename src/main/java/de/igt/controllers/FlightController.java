package de.igt.controllers;

import de.igt.models.Airport;
import de.igt.models.Flight;
import de.igt.tools.Config;
import de.igt.tools.FlightPopulator;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlightController implements CRUD_Interface<Flight, String>{
    private static Logger logger = Logger.getRootLogger();
    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);


    public Flight createFlight(String flightID, Date departureTime, Date arrivalTime, String type, int priceFirstClass,
                             int priceEcoClass, int seatsEco, int seatsFirstClass) {
        Flight flight = new Flight();
        flight.setFLIGHT_ID(flightID);
        flight.setDEPARTURE_TIME(departureTime);
        flight.setARRIVAL_TIME(arrivalTime);
        flight.setTYPE(type);
        flight.setPRICE_FIRST_CLASS(priceFirstClass);
        flight.setPRICE_ECO_CLASS(priceEcoClass);
        flight.setSEATS_ECO_CLASS(seatsEco);
        flight.setSEATS_FIRST_CLASS(seatsFirstClass);
        return flight;
    }

    public List<Flight> createFlights() {
        List<Flight> fList = FlightPopulator.populateFlightAsList(Config.NUMBER_OF_FLIGHTS);
        return fList;
    }


    public List<String> getAllFlightIDs() {
        List<Flight> cIDs = new ArrayList<>();
        List<String> returnList = new ArrayList<>();

        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT E FROM Flight E");
            Query q = em.createQuery(queryString);

            logger.info("\n\nGet all FlightIDs TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            cIDs = q.getResultList();

            for (Flight c : cIDs) {
                returnList.add(c.getFLIGHT_ID());
            }

            long queryEnd = System.currentTimeMillis();
            em.flush();
            em.close();
            tm.commit();
            logger.info("\n\nGet all FlightIDs TA ends");
            long queryTime = queryEnd - queryStart;
            logger.info("\n\nFound " + cIDs.size() + " flightIDs in " + queryTime + " ms.\n\n");
        } catch (NotSupportedException | SystemException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
        return returnList;
    }


    @Override
    public void create(Flight object) {
        try {
            logger.info("\n\nCreating Flight TA begins\n\n");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();
            long queryStart = System.currentTimeMillis();

            em.persist(object);
            em.flush();
            em.close();
            tm.commit();
            long queryEnd = System.currentTimeMillis();
            logger.info("\n\nCreated flight TA ends\n\n");
            logger.info("\n\nCreation of flight took ".concat(String.valueOf(queryEnd - queryStart)).concat("ms.\n\n"));
        } catch (SystemException | NotSupportedException | RollbackException | HeuristicMixedException | HeuristicRollbackException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create_demo(List<Flight> objects) {
        try {
            logger.info("Create flights TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            for (Flight c : objects) {
                em.persist(c);
            }

            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();

            logger.info("Create flights TA ends");

            long queryTime = queryEnd - queryStart;

            logger.info(objects.size() + " customers flights in DB in " + queryTime + " ms.");

        } catch (NotSupportedException | SystemException | HeuristicMixedException | HeuristicRollbackException | RollbackException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Flight object) {
        try {
            logger.info("\n\nUpdate flight TA begins\n\n");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();
            long queryStart = System.currentTimeMillis();

            Flight flightUpdate = em.find(Flight.class, object.getFLIGHT_ID());
            logger.info("\n\nFound flight: " + flightUpdate.toString().concat("\n\n"));
            logger.info("\n\nUpdating flight...");
            flightUpdate = object;
            em.merge(flightUpdate);

            long queryEnd = System.currentTimeMillis();
            em.flush();
            em.close();
            tm.commit();

            logger.info("\n\nUpdate flight TA ends\n");
            long queryTime = queryEnd - queryStart;
            logger.info("\nflight successfully persisted in " + queryTime + " ms.\n\n");
        } catch (NotSupportedException | SystemException | HeuristicRollbackException | RollbackException | HeuristicMixedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(List<Flight> objects) {
        try {
            EntityManager em = emf.createEntityManager();
            logger.info("\n\nUpdate flight TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();
            long queryStart = System.currentTimeMillis();

            for (Flight c : objects) {
                em.merge(c);
            }

            long queryEnd = System.currentTimeMillis();
            em.flush();
            em.close();
            tm.commit();

            logger.info("\n\nUpdate flight TA ends");
            long queryTime = queryEnd - queryStart;
            logger.info("\n\nUpdates of " + objects.size() + " flights successfully persisted in " + queryTime + " ms.\n\n");
        } catch (NotSupportedException | SystemException | HeuristicMixedException | RollbackException | HeuristicRollbackException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Flight object) {
        try {
            logger.info("\n\nDelete flight TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            Flight test = em.find(Flight.class, object.getFLIGHT_ID());
            logger.info("\n\nFound flight: " + object.toString());
            logger.info("\n\nDeleting flight...");
            em.remove(test);

            long queryEnd = System.currentTimeMillis();

            em.flush();
            em.close();
            tm.commit();
            logger.info("\n\nDelete flight TA ends");

            long queryTime = queryEnd - queryStart;
            logger.info("\n\nflight successfully deleted in " + queryTime + " ms.\n\n");

        } catch (NotSupportedException | SystemException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        List<Flight> flights;
        try {
            flights = readAll();
            logger.info("\n\nDelete all airport TA begins");
            long queryStart = System.currentTimeMillis();

            for (Flight flight : flights) {
                delete(flight);
            }

            long queryEnd = System.currentTimeMillis();
            logger.info("\n\nDelete all airport TA ends");
            long queryTime = queryEnd - queryStart;

            logger.info("\n\n" + flights.size() + " airport successfully deleted in " + queryTime + " ms.\n\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Flight read(String Key) {
        Flight flight = null;
        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();
            logger.info("\n\nDelete flight TA begins");

            flight = em.find(Flight.class, Key);
            logger.info("\n\nFound flight: " + flight.toString() + "\n\n");

            em.flush();
            em.close();
            tm.commit();
            logger.info("TA ends\n\n");
        } catch (NotSupportedException | SystemException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
        return flight;
    }

    @Override
    public List<Flight> readAll() {
        List<Flight> cIDs = new ArrayList<>();

        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT E FROM Flight E");
            Query q = em.createQuery(queryString);

            //logger.info("\n\nGet all Flightsegment TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            cIDs = q.getResultList();

            long queryEnd = System.currentTimeMillis();
            em.flush();
            em.close();
            tm.commit();
            //logger.info("\n\nGet all Flightsegment TA ends");
            long queryTime = queryEnd - queryStart;
            //logger.info("\n\nFound " + cIDs.size() + " Flightsegment names in " + queryTime + " ms.\n\n");
        } catch (NotSupportedException | SystemException | HeuristicRollbackException | HeuristicMixedException | RollbackException e) {
            e.printStackTrace();
        }
        return cIDs;
    }
}
