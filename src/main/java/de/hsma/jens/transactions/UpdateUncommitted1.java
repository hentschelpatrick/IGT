package de.hsma.jens.transactions;

import de.hsma.jens.models.Airport;
import de.hsma.jens.models.Flight;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.*;

/**
 * Created by jenskohler on 06.12.17.
 */
public class UpdateUncommitted1 {
    //accessing JBoss's Transaction can be done differently but this one works nicely
    static TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();

    //build the EntityManagerFactory as you would build in in Hibernate ORM
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory(
            "OGM_MYSQL");


    private static Logger logger = Logger.getRootLogger();


    public static void main(String[] args) {
        Airport airport = new Airport();
        airport.setAddress("dsad");
        airport.setCountry("DE");
        airport.setName("FRA");

        Flight flight = new Flight();
        flight.setAbflugzeit("12:23:20");
        flight.setAnkunfszeit("X");
        flight.setSitzplatz_anzahl_eco(200);
        flight.setSitzplatz_preis_eco(213212L);
        flight.setSitzplatz_anzahl_firstclass(50);
        flight.setSitzplatz_preis_firstclass(400123L);

        try {

            logger.info("TA begins");
            EntityManager em_airports = emf.createEntityManager();
            tm.begin();


            //Customer customerToUpdate = em.find(Customer.class, 1);


            em_airports.persist(airport);
            em_airports.flush();
            em_airports.close();

            EntityManager em_flights = emf.createEntityManager();
            em_flights.persist(flight);
            em_flights.flush();
            em_flights.close();

            tm.commit();
            logger.info("Update successfully persisted.");


        } catch (NotSupportedException | SystemException | HeuristicMixedException | RollbackException | HeuristicRollbackException e) {
            e.printStackTrace();
        }

        System.exit(0);
    }
}
