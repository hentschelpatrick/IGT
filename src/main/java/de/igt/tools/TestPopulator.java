package de.igt.tools;

import de.igt.models.CustomerTest;

import java.util.ArrayList;
import java.util.List;

public class TestPopulator {

    public static List<CustomerTest> populateCustomerAsList(int numberOfCustomers) {

        List<CustomerTest> cList = new ArrayList<>();


        String EMAIL, FIRST_NAME, LAST_NAME, ADDRESS, COUNTRY;
        int AGE;
        //double MILES_FLOWN_YEAR, TOTAL_MILES_FLOWN;

        // System.out.println("Populating CUSTOMER Table with " +
        //         numberOfCustomers + " customers");
        // System.out.print("Complete (in 10,000's): ");
        try {

            for (int i = 1; i <= numberOfCustomers; i++) {
                CustomerTest myCustomer = new CustomerTest();

                if (i % 10000 == 0)
                    System.out.print(i / 10000 + " ");

                FIRST_NAME = Config.DigSyl(i, 0);
                EMAIL = FIRST_NAME + "@" + Config.getRandomAString(2, 9) + ".com";
                LAST_NAME = Config.DigSyl(i, 0);
                ADDRESS = Config.getRandomAString(8, 15);
                COUNTRY = Config.getRandomAString(8, 15);
                AGE = Config.getRandomInt(1, 2 * numberOfCustomers);

                //MILES_FLOWN_YEAR = (double) Config.getRandomInt(0, 50) / 100.0;
                //TOTAL_MILES_FLOWN = (double) Config.getRandomInt(0, 99999) / 100.0;

                try {// Set parameter


                    myCustomer.setEMAIL(EMAIL);
                    myCustomer.setFIRST_NAME(FIRST_NAME);
                    myCustomer.setLAST_NAME(LAST_NAME);
                    myCustomer.setADDRESS(ADDRESS);
                    myCustomer.setCOUNTRY(COUNTRY);
                    myCustomer.setAGE(AGE);
                    myCustomer.setMILES_FLOWN_YEAR(123142412103L);
                    myCustomer.setTOTAL_MILES_FLOWN(12314241203L);

                    cList.add(myCustomer);


                } catch (java.lang.Exception ex) {
                    System.err.println("Unable to populate CUSTOMERSTEST table");
                    System.out.println("EMAIL=" + EMAIL + " FIRST_NAME=" + FIRST_NAME +
                            " LAST_NAME=" + LAST_NAME + " ADDRESS=" +
                            ADDRESS + " COUNTRY=" + COUNTRY + " AGE="
                            + AGE + " MILES_FLOWN_YEAR=" + 123142412103L + " TOTAL_MILES_FLOWN="
                            + 12314241203L);
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
            System.out.print("\n");
            ////con.commit();
        } catch (java.lang.Exception ex) {
            System.err.println("Unable to populate CUSTOMERSTEST table");
            ex.printStackTrace();
            System.exit(1);
        }

        return cList;

    }
}
