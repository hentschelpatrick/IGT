package de.igt.transactions;

import de.igt.controllers.AirportController;
import de.igt.controllers.CustomerController;
import de.igt.controllers.FlightController;
import de.igt.controllers.FlightSegmentController;
import de.igt.models.Airport;
import de.igt.models.Flight;
import de.igt.models.FlightSegment;
import de.igt.models.Status;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class BookFlight {
    public static void main(String[] args) {
        try {




            FlightController flightController = new FlightController();
            AirportController airportController1 = new AirportController();
            AirportController airportController2 = new AirportController();
            FlightSegmentController flightSegmentController1 = new FlightSegmentController();
            FlightSegmentController flightSegmentController2 = new FlightSegmentController();
            CustomerController customerController = new CustomerController();
            Scanner scanner = new Scanner(System.in);

            System.out.println("Möchten Sie eine Flugbuchung durchführen?");
            String temp = scanner.nextLine();

            //Create a flight
            flightController.createFlight("1", new Date(), new Date(), "TES", 450,
                    250, 62, 31);
            flightController.createFlight("2", new Date(), new Date(), "FRL", 350,
                    150, 52, 41);

            //Create two airports
            airportController1.createAirport("FRA", "DE", "SFAKSD", 4, 5);
            airportController2.createAirport("FRA2", "DE", "SDS", 5, 12);


            Airport a1 = airportController1.getAirports("FRA");
            Airport a2 = airportController2.getAirports("FRA2");


            //Create first flightsegment
            flightSegmentController1.createFlightsegment("Flight1", a1,
                    a2, 19321);

            //Create second flightsegment
            //flightSegmentController2.createFlightsegment("Flight1", a2, a1, 19321);



            System.out.println("Wo möchten Sie Ihre Reise starten?");
            String departure_airport = scanner.nextLine();

            System.out.println("Wo möchten Sie Ihre Reise beenden?");
            String arrival_airport = scanner.nextLine();


            List<Flight> listOfFlight = flightController.getAllFlights();
            for(Flight c : listOfFlight) {
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

            customerController.createCustomer(email, vornamen, nachnamen,
                    adresse,alter, "DE", 0, 0, Status.NONE);



        } finally {
            System.out.println("Finished");
        }
    }
}
