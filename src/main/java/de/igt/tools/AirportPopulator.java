package de.igt.tools;

import de.igt.models.Airport;

import java.util.ArrayList;
import java.util.List;

public class AirportPopulator {

    public static List<Airport> populateAirportAsList(int numberOfAirports) {

        List<Airport> aList = new ArrayList<>();


        String NAME, COUNTRY, ADDRESS;
        int AMOUNT_INTERNATIONAL_LANDINGSITES, AMOUNT_NATIONAL_LANDINGSITES;

        try {

            for (int i = 1; i <= numberOfAirports; i++) {
                Airport myAirport = new Airport();

                if (i % 10000 == 0)
                    System.out.print(i / 10000 + " ");

                NAME = Config.DigSyl(i, 0);
                COUNTRY = Config.getRandomAString(8, 15);
                ADDRESS = Config.getRandomAString(8, 15);
                AMOUNT_INTERNATIONAL_LANDINGSITES = Config.getRandomInt(1, 2 * numberOfAirports);
                AMOUNT_NATIONAL_LANDINGSITES = Config.getRandomInt(1, 2 * numberOfAirports);

                try {// Set parameter

                    myAirport.setNAME(NAME);
                    myAirport.setCOUNTRY(COUNTRY);
                    myAirport.setADDRESS(ADDRESS);
                    myAirport.setAMOUNT_INTERNATIONAL_LANDINGSITES(AMOUNT_INTERNATIONAL_LANDINGSITES);
                    myAirport.setAMOUNT_NATIONAL_LANDINGSITES(AMOUNT_NATIONAL_LANDINGSITES);

                    aList.add(myAirport);


                } catch (java.lang.Exception ex) {
                    System.err.println("Unable to populate AIRPORTS table");
                    System.out.println("NAME=" + NAME + " COUNTRY=" + COUNTRY +
                            " ADDRESS=" + ADDRESS + " AMOUNT_INTERNATIONAL_LANDINGSITES=" +
                            AMOUNT_INTERNATIONAL_LANDINGSITES + " AMOUNT_NATIONAL_LANDINGSITES="
                            + AMOUNT_NATIONAL_LANDINGSITES);
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
            System.out.print("\n");
            ////con.commit();
        } catch (java.lang.Exception ex) {
            System.err.println("Unable to populate AIRPORT table");
            ex.printStackTrace();
            System.exit(1);
        }

        return aList;

    }

}
