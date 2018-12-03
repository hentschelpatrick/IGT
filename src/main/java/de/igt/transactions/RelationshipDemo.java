package de.igt.transactions;

import de.igt.controllers.AirportController;
import de.igt.controllers.FlightController;
import de.igt.models.Airport;
import de.igt.models.Flight;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.TransactionManager;

public class RelationshipDemo {
    static TransactionManager tm = com.arjuna.ats.jta.TransactionManager.transactionManager();

    //build the EntityManagerFactory as you would build in in Hibernate ORM
    static EntityManagerFactory emf = Persistence.createEntityManagerFactory(
            "OGM_MYSQL");


    public static void main(String[] args) {

        Flight flight1 = new Flight();
        flight1.setType("??");
        flight1.setDepartureTime("11:12");
        flight1.setPriceEcoClass(1321);
        flight1.setPriceFirstClass(12312);
        flight1.setSeatsEco(12);
        flight1.setSeatsFirstClass(131);
        flight1.setTimeOfArrival("14:12");
        flight1.setFlightID("A1");


        AirportController airportController = new AirportController();
        airportController.createAirport("FRA", "FFF", "DE", "asdasd asd",
                4, 1, flight1);
        Airport airport = airportController.getAirports("FRA");

        FlightController flightController = new FlightController();
        flightController.createFlight("FAAD", "9142", "13123", "SDA",
                1312, 41241, 12, 32, airport);
        Flight flight = flightController.getFlight("FAAD");
        System.out.println("Flight: ".concat(flight.toString()));

        System.out.println();

        airport.addFlight(flight);

        System.out.println("Airport flights from: ".concat(airport.getFlights().toString()));


    }
}
