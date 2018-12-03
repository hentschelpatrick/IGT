package de.igt.controllers;

import de.igt.models.Airport;
import de.igt.models.Flight;
import de.igt.tools.Config;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.transaction.*;
import java.util.ArrayList;
import java.util.List;

public class FlightController {
    private static Logger logger = Logger.getRootLogger();
    //accessing JBoss's Transaction can be done differently but this one works nicely
    TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();
    //build the EntityManagerFactory as you would build in in Hibernate ORM
    EntityManagerFactory emf = Persistence.createEntityManagerFactory(Config.PERSISTENCE_UNIT_NAME);


    public void createFlight(String flightID, String departureTime, String arrivalTime, String type, int priceFirstClass,
                             int priceEcoClass, int seatsEco, int seatsFirstClass, Airport airport) {
        Flight flight = new Flight();

        flight.setFlightID(flightID);
        flight.setDepartureTime(departureTime);
        flight.setTimeOfArrival(arrivalTime);
        flight.setType(type);
        flight.setPriceFirstClass(priceFirstClass);
        flight.setPriceEcoClass(priceEcoClass);
        flight.setSeatsEco(seatsEco);
        flight.setSeatsFirstClass(seatsFirstClass);
        flight.setAirport(airport);

        try {
            logger.info("\n\nCreating Flight TA begins\n\n");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();
            long queryStart = System.currentTimeMillis();

            em.persist(flight);
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

    public void updateFlight(Flight flight) {
        try {
            logger.info("\n\nUpdate flight TA begins\n\n");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();
            long queryStart = System.currentTimeMillis();

            Flight flightUpdate = em.find(Flight.class, flight.getFlightID());
            logger.info("\n\nFound flight: " + flightUpdate.toString().concat("\n\n"));
            logger.info("\n\nUpdating flight...");
            flightUpdate = flight;
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

    public void updateFlight(List<Flight> flightList) {
        try {
            EntityManager em = emf.createEntityManager();
            logger.info("\n\nUpdate flight TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();
            long queryStart = System.currentTimeMillis();

            for (Flight c : flightList) {
                em.merge(c);
            }

            long queryEnd = System.currentTimeMillis();
            em.flush();
            em.close();
            tm.commit();

            logger.info("\n\nUpdate flight TA ends");
            long queryTime = queryEnd - queryStart;
            logger.info("\n\nUpdates of " + flightList.size() + " flights successfully persisted in " + queryTime + " ms.\n\n");
            //String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " UPDATE: " + cList.size() + " " + queryTime + "\n");
            //Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);
        } catch (NotSupportedException | SystemException | HeuristicMixedException | RollbackException | HeuristicRollbackException e) {
            e.printStackTrace();
        }
    }

    public void deleteAllFlights() {
        List<String> flights;
        try {
            flights = getAllFlightIDs();
            logger.info("\n\nDelete all airport TA begins");
            long queryStart = System.currentTimeMillis();

            for (String id : flights) {
                deleteFlight(id);
            }

            long queryEnd = System.currentTimeMillis();
            logger.info("\n\nDelete all airport TA ends");
            long queryTime = queryEnd - queryStart;

            logger.info("\n\n" + flights.size() + " airport successfully deleted in " + queryTime + " ms.\n\n");
            //String writeToFile = new String(Config.PERSISTENCE_UNIT_NAME + " DELETE: " + cust.size() + " " + queryTime + "\n");
            //Files.write(Paths.get(Config.LOG_STORAGE_LOCATION), writeToFile.getBytes(), CREATE, APPEND);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteFlight(String flightID) {
        try {
            logger.info("\n\nDelete flight TA begins");
            EntityManager em = emf.createEntityManager();
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            Flight flight = em.find(Flight.class, flightID);
            logger.info("\n\nFound flight: " + flight.toString());
            logger.info("\n\nDeleting flight...");
            em.remove(flight);

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

    public Flight getFlight(String flightID) {
        Flight flight = null;
        try {
            EntityManager em = emf.createEntityManager();
            tm.begin();
            logger.info("\n\nDelete flight TA begins");

            flight = em.find(Flight.class, flightID);
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


    public List<String> getAllFlightIDs() {
        List<Flight> cIDs = new ArrayList<>();
        List<String> returnList = new ArrayList<>();

        try {
            EntityManager em = emf.createEntityManager();

            String queryString = new String("SELECT c FROM FLIGHTS c");
            Query q = em.createQuery(queryString);

            logger.info("\n\nGet all FlightIDs TA begins");
            tm.setTransactionTimeout(Config.TRANSACTION_TIMEOUT);
            tm.begin();

            long queryStart = System.currentTimeMillis();

            cIDs = q.getResultList();

            for (Flight c : cIDs) {
                returnList.add(c.getFlightID());
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


}
