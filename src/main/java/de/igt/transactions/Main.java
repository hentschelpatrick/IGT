package de.igt.transactions;

import de.igt.controllers.AirportController;
import de.igt.controllers.CustomerController;
import de.igt.controllers.FlightController;
import de.igt.controllers.FlightSegmentController;

public class Main {


    public static void main(String[] args) throws InterruptedException {

        FlightController flightController = new FlightController();
        AirportController airportController = new AirportController();
        FlightSegmentController flightSegmentController = new FlightSegmentController();
        CustomerController customerController = new CustomerController();

        flightController.deleteAllFlights();
        airportController.deleteAllAirports();
        flightSegmentController.deleteAllFlightsegments();
        customerController.deleteAllCustomers();

    }
}
