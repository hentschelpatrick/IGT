package de.igt.tools;

import de.igt.models.Airport;
import de.igt.models.FlightSegment;

import java.util.*;

public class FlightSegmentPopulator {

    public static List<FlightSegment> populateAirportAsList(int numberOfFlightSegments) {

        List<FlightSegment> fsList = new ArrayList<>();

        String NAME;
        //long DISTANCE_MILES;
        Airport DEPARTURE_AIRPORT, ARRIVAL_AIRPORT;

        try {

            for (int i = 1; i <= numberOfFlightSegments; i++) {
                FlightSegment myFlightSegment = new FlightSegment();

                if (i % 10000 == 0)
                    System.out.print(i / 10000 + " ");


                NAME = Config.DigSyl(i, 0);
                //DISTANCE_MILES = (double) Config.getRandomInt(0, 99999) / 100.0;
                DEPARTURE_AIRPORT = new Airport(Config.DigSyl(i, 0), Config.getRandomAString(8, 15), Config.getRandomAString(8, 15),
                        Config.getRandomInt(1, 2 * numberOfFlightSegments),
                        Config.getRandomInt(1, 2 * numberOfFlightSegments));
                ARRIVAL_AIRPORT = new Airport(Config.DigSyl(i, 0), Config.getRandomAString(8, 15), Config.getRandomAString(8, 15),
                        Config.getRandomInt(1, 2 * numberOfFlightSegments),
                        Config.getRandomInt(1, 2 * numberOfFlightSegments));

                try {// Set parameter

                    myFlightSegment.setNAME(NAME);
                    myFlightSegment.setDEPARTURE_AIRPORT(DEPARTURE_AIRPORT);
                    myFlightSegment.setARRIVAL_AIRPORT(ARRIVAL_AIRPORT);
                    myFlightSegment.setDISTANCE_MILES(12314241203L);

                    fsList.add(myFlightSegment);


                } catch (java.lang.Exception ex) {
                    System.err.println("Unable to populate FLIGHTSEGMENTS table");
                    System.out.println("NAME=" + NAME + " DISTANCE_MILES=" + 12314241203L +
                            " DEPARTURE_AIRPORT=" + DEPARTURE_AIRPORT + " ARRIVAL_AIRPORT=" +  ARRIVAL_AIRPORT);
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
            System.out.print("\n");
            ////con.commit();
        } catch (java.lang.Exception ex) {
            System.err.println("Unable to populate FLIGHTSEGMENT table");
            ex.printStackTrace();
            System.exit(1);
        }

        return fsList;

    }
}
