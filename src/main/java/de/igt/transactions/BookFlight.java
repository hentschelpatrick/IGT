package de.igt.transactions;

import de.igt.controllers.AirportController;
import de.igt.controllers.CustomerController;
import de.igt.controllers.FlightController;
import de.igt.controllers.FlightSegmentController;
import de.igt.models.*;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BookFlight {


    public static void main(String[] args) {
        try {

            FlightController flightController = new FlightController();
            AirportController airportController = new AirportController();
            FlightSegmentController flightSegmentController = new FlightSegmentController();
            CustomerController customerController = new CustomerController();
            Scanner scanner = new Scanner(System.in);

            System.out.println("Möchten Sie eine Flugbuchung durchführen?");
            String temp = scanner.nextLine();

            //Create a flight
            flightController.create(flightController.createFlight("1", new Date(), new Date(), "TES", 450,
                    250, 62, 31));
            flightController.create(flightController.createFlight("2", new Date(), new Date(), "FRL", 350,
                    150, 52, 41));

            //Create two airports
            airportController.create(airportController.createAirport("FRA", "DE", "SFAKSD",
                    4, 5));
            airportController.create(airportController.createAirport("FRA2", "DE", "SDS",
                    5, 12));


            Airport a1 = airportController.read("FRA");
            Airport a2 = airportController.read("FRA2");


            //Create first flightsegment
            flightSegmentController.create(flightSegmentController.createFlightsegment("Flight1", a1,
                    a2, 19321));

            //Create second flightsegment
            //flightSegmentController2.createFlightsegment("Flight1", a2, a1, 19321);


            System.out.println("Wo möchten Sie Ihre Reise starten?");
            String departure_airport = scanner.nextLine();

            System.out.println("Wo möchten Sie Ihre Reise beenden?");
            String arrival_airport = scanner.nextLine();


            List<Flight> listOfFlight = flightController.readAll();
            for (Flight c : listOfFlight) {
                System.out.println(c.toString());
            }

            System.out.println("Wählen Sie eine der verfügbaren Flüge aus.");
            int flug = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Geben Sie Ihre Email an?");
            String email = scanner.nextLine();
            System.out.println("Geben Sie Ihren Vorname an?");
            String vornamen = scanner.nextLine();
            System.out.println("Geben Sie Ihren Nachname an?");
            String nachnamen = scanner.nextLine();
            System.out.println("Geben Sie Ihre Adresse an?");
            String adresse = scanner.nextLine();
            System.out.println("Geben Sie Ihr Alter an?");
            int alter = scanner.nextInt();

            System.out.println("Ihre Flugbuchung wird durchgeführt!");

            customerController.create(customerController.createCustomer(email, vornamen, nachnamen,
                    adresse, alter, "DE", 0, 0, Status.NONE));

            Customer customer = customerController.read(email);
            customer.getFLIGHTS().add(flightController.read(String.valueOf(flug)));
            customerController.update(customer);

            FlightSegment flightSegment = flightSegmentController.read("Flight1");
            Flight flight = flightController.read(String.valueOf(flug));
            flight.getFlightSegmentList().add(flightSegment);
            flightController.update(flight);
            flightSegment.setFLIGHT(flight);
            flightSegmentController.update(flightSegment);


        } finally {
            System.out.println("Finished");
        }
    }
}
