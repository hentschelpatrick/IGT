package de.igt.transactions;

import de.igt.controllers.AirportController;
import de.igt.controllers.CustomerController;
import de.igt.controllers.FlightController;
import de.igt.controllers.FlightSegmentController;
import de.igt.models.Customer;
import de.igt.models.Flight;
import de.igt.models.Status;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;
import java.util.Date;

public class RelationshipDemo {
    static TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();

    //build the EntityManagerFactory as you would build in in Hibernate ORM
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory(
            "OGM_MYSQL");


    public static void main(String[] args) throws InterruptedException {

        AirportController airportController = new AirportController();
        CustomerController customerController = new CustomerController();
        FlightController flightController = new FlightController();
        FlightSegmentController flightSegmentController = new FlightSegmentController();

        airportController.createAirport("FRA", "DE", "SFAKSD", 4, 5);
        airportController.createAirport("FRA2", "DE", "SDS", 5, 12);

        customerController.createCustomer("test@test.de", "scheiße", "nein",
                "skdoasd",14, "DE", 0, 0, Status.NONE);

        flightController.createFlight("FASD", new Date(), new Date(), "TES", 12312, 123, 21, 43);

        flightSegmentController.createFlightsegment("Flight1", airportController.getAirports("FRA"),
                airportController.getAirports("FRA2"), 19321);

        Flight flight = flightController.getFlight("FASD");
        flight.getFlightSegmentList().add(flightSegmentController.getFlightsegments("Flight1"));
        flightController.updateFlight(flight);


        Thread.sleep(10000);
        airportController.deleteAllAirports();
        customerController.deleteAllCustomers();
        System.exit(0);
    }
}
