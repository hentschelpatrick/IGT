package de.igt.tools;

import de.igt.models.Flight;

import java.util.*;

public class FlightPopulator {

    public static List<Flight> populateFlightAsList(int numberOfFlights) {

        List<Flight> fList = new ArrayList<>();

        String FLIGHT_ID;
        Date DEPARTURE_TIME, ARRIVAL_TIME;
        String TYPE;
        int PRICE_FIRST_CLASS, PRICE_ECO_CLASS, SEATS_ECO_CLASS, SEATS_FIRST_CLASS;

        try {

            for (int i = 1; i <= numberOfFlights; i++) {
                Flight myFlight = new Flight();

                if (i % 10000 == 0)
                    System.out.print(i / 10000 + " ");

                GregorianCalendar cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_YEAR, -1 * Config.getRandomInt(1, 730));

                FLIGHT_ID = Config.DigSyl(i, 0);
                DEPARTURE_TIME = new Date(cal.getTime().getTime());
                ARRIVAL_TIME = new Date(cal.getTime().getTime());
                TYPE = Config.getRandomAString(8, 15);
                PRICE_FIRST_CLASS = Config.getRandomInt(1, 2 * numberOfFlights);
                PRICE_ECO_CLASS = Config.getRandomInt(1, 2 * numberOfFlights);
                SEATS_ECO_CLASS = Config.getRandomInt(1, 2 * numberOfFlights);
                SEATS_FIRST_CLASS = Config.getRandomInt(1, 2 * numberOfFlights);

                try {// Set parameter

                    myFlight.setFLIGHT_ID(FLIGHT_ID);
                    myFlight.setDEPARTURE_TIME(DEPARTURE_TIME);
                    myFlight.setARRIVAL_TIME(ARRIVAL_TIME);
                    myFlight.setTYPE(TYPE);
                    myFlight.setPRICE_FIRST_CLASS(PRICE_FIRST_CLASS);
                    myFlight.setPRICE_ECO_CLASS(PRICE_ECO_CLASS);
                    myFlight.setSEATS_ECO_CLASS(SEATS_ECO_CLASS);
                    myFlight.setSEATS_FIRST_CLASS(SEATS_FIRST_CLASS);

                    fList.add(myFlight);


                } catch (java.lang.Exception ex) {
                    System.err.println("Unable to populate FLIGHTS table");
                    System.out.println("FLIGHT_ID=" + FLIGHT_ID + " DEPARTURE_TIME=" + DEPARTURE_TIME +
                            " ARRIVAL_TIME=" + ARRIVAL_TIME + " TYPE=" +  TYPE + " PRICE_FIRST_CLASS=" + PRICE_FIRST_CLASS
                            + " PRICE_ECO_CLASS=" + PRICE_ECO_CLASS + " SEATS_ECO_CLASS=" + SEATS_ECO_CLASS
                            + " SEATS_FIRST_CLASS=" + SEATS_FIRST_CLASS);
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
            System.out.print("\n");
            ////con.commit();
        } catch (java.lang.Exception ex) {
            System.err.println("Unable to populate FLIGHT table");
            ex.printStackTrace();
            System.exit(1);
        }

        return fList;

    }

}
