package de.igt.transactions;

import de.igt.controllers.AirportController;
import de.igt.controllers.CustomerController;
import de.igt.controllers.FlightController;
import de.igt.controllers.FlightSegmentController;
import de.igt.models.*;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;

public class Main {
    static TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();

    //build the EntityManagerFactory as you would build in in Hibernate ORM
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory(
            "OGM_MYSQL");


    private static Logger logger = Logger.getRootLogger();


    public static void main(String[] args) throws InterruptedException {
        logger.setLevel(Level.ERROR);

        CustomerController customerController = new CustomerController();
        customerController.createCustomer("test@test.de", "Test", "asod",
                "asdasdo str. 79", "01762394201", "DE",
                "12314241203", 123131L, 0L, Status.NONE);

        AirportController airportController = new AirportController();
        airportController.createAirport("FRA", "Frankfurt Hahn", "DE",
                "Frankfurter Straße 23", 12, 24, null);
        FlightController flightController = new FlightController();
        flightController.createFlight("ADS13", "19:40", "22:12", "ECO",
                102412, 21321, 12, 42, null);

        FlightSegmentController flightSegmentController = new FlightSegmentController();
        flightSegmentController.createFlightsegment("FRA123", "FRA", "BER", 21312);

        System.out.println("Created FlightSegment: ".concat(flightSegmentController.toString()));


        Airport airport = airportController.getAirports("FRA");
        System.out.println(airport.toString());

        FlightSegment flightSegment = flightSegmentController.getFlightsegments("FRA123");
        System.out.println(flightSegment.toString());

        Flight flight = flightController.getFlight("ADS13");
        System.out.println(flight.toString());
        Thread.sleep(10000);
        System.out.println("Updating Airport");
        airport.setName("NEUER NAME");
        airportController.updateAirport(airport);

        System.out.println("Updating FlightSegment");
        flightSegment.setDistance_in_miles(999999999);
        flightSegmentController.updateFlightsegment(flightSegment);

        System.out.println("Updating Flight");
        flight.setType("LLLLLLLLLLLLLLLLLLLLLLLL");
        flightController.updateFlight(flight);


        System.out.println("Created Customer");
        Thread.sleep(10000);
        Customer customer = customerController.getCustomer("test@test.de");
        System.out.println(customer.toString());
        Thread.sleep(10000);
        System.out.println("Deleting Customer");

        customerController.deleteCustomer("test@test.de");
        airportController.deleteAirport("FRA");
        flightController.deleteFlight(flight.getFlightID());
        flightSegmentController.deleteFlightsegment(flightSegment.getName());

        System.out.println("Deleted Flight: ".concat(flight.toString()));
        System.out.println("Deleted Airport: ".concat(airport.toString()));
        System.out.println("Deleted Customer: ".concat(customer.toString()));
        System.out.println("Deleted FlightSegment: ".concat(flightSegment.toString()));
        System.exit(0);
    }
}
