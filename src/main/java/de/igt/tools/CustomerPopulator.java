package de.igt.tools;

import de.igt.models.Customer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class CustomerPopulator {

    public static List<Customer> populateCustomerAsList(int numberOfCustomers) {

        List<Customer> cList = new ArrayList<>();


        String EMAIL, FIRST_NAME, LAST_NAME, ADDRESS, COUNTRY;
        int AGE;
        //double MILES_FLOWN_YEAR, TOTAL_MILES_FLOWN;

        // System.out.println("Populating CUSTOMER Table with " +
        //         numberOfCustomers + " customers");
        // System.out.print("Complete (in 10,000's): ");
        try {

            for (int i = 1; i <= numberOfCustomers; i++) {
                Customer myCustomer = new Customer();

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
                    myCustomer.setMILES_FLOWN_YEAR(12314241203L);
                    myCustomer.setTOTAL_MILES_FLOWN(12314241203L);

                    cList.add(myCustomer);


                } catch (java.lang.Exception ex) {
                    System.err.println("Unable to populate CUSTOMER table");
                    System.out.println("EMAIL=" + EMAIL + " FIRST_NAME=" + FIRST_NAME +
                            " LAST_NAME=" + LAST_NAME + " ADDRESS=" +
                            ADDRESS + " COUNTRY=" + COUNTRY + " AGE="
                            + AGE + " MILES_FLOWN_YEAR=" + 12314241203L + " TOTAL_MILES_FLOWN="
                            + 12314241203L);
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
            System.out.print("\n");
            ////con.commit();
        } catch (java.lang.Exception ex) {
            System.err.println("Unable to populate CUSTOMER table");
            ex.printStackTrace();
            System.exit(1);
        }

        return cList;

    }

    /*
    writes Customers into a file

    public static void populateCustomerAsFile(int numberOfCustomers) {
        ObjectOutputStream oos = null;
        FileOutputStream fos = null;
        String writeToFile = null;


        String C_UNAME, C_PASSWD, C_LNAME, C_FNAME;
        int C_ADDR_ID;
        String C_EMAIL, C_PHONE;
        java.sql.Date C_SINCE, C_LAST_LOGIN;
        java.sql.Timestamp C_LOGIN, C_EXPIRATION;
        double C_DISCOUNT, C_BALANCE, C_YTD_PMT;
        java.sql.Date C_BIRTHDATE;
        String C_DATA;
        int i;

        try {

            fos = new FileOutputStream(Config.PERSIST_STORAGE_LOCATION);
            oos = new ObjectOutputStream(fos);


            for (i = 1; i <= numberOfCustomers; i++) {
                Customer myCustomer = new Customer();

                C_UNAME = DigSyl(i, 0);
                C_PASSWD = C_UNAME.toLowerCase();
                C_LNAME = getRandomAString(8, 15);
                C_FNAME = getRandomAString(8, 15);
                C_ADDR_ID = getRandomInt(1, 2 * numberOfCustomers);
                C_PHONE = getRandomAString(8, 15);
                C_EMAIL = C_UNAME + "@" + getRandomAString(2, 9) + ".com";

                GregorianCalendar cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_YEAR, -1 * getRandomInt(1, 730));
                C_SINCE = new java.sql.Date(cal.getTime().getTime());
                cal.add(Calendar.DAY_OF_YEAR, getRandomInt(0, 60));
                if (cal.after(new GregorianCalendar()))
                    cal = new GregorianCalendar();

                C_LAST_LOGIN = new java.sql.Date(cal.getTime().getTime());
                C_LOGIN = new java.sql.Timestamp(System.currentTimeMillis());
                cal = new GregorianCalendar();
                cal.add(Calendar.HOUR, 2);
                C_EXPIRATION = new java.sql.Timestamp(cal.getTime().getTime());

                C_DISCOUNT = (double) getRandomInt(0, 50) / 100.0;
                C_BALANCE = 0.00;
                C_YTD_PMT = (double) getRandomInt(0, 99999) / 100.0;
                int year = getRandomInt(1880, 2000);
                int month = getRandomInt(0, 11);
                int maxday = 31;
                int day;
                if (month == 3 | month == 5 | month == 8 | month == 10)
                    maxday = 30;
                else if (month == 1)
                    maxday = 28;
                day = getRandomInt(1, maxday);
                cal = new GregorianCalendar(year, month, day);
                C_BIRTHDATE = new java.sql.Date(cal.getTime().getTime());

                C_DATA = getRandomAString(100, 120);


                myCustomer.setC_YTD_PMT(C_YTD_PMT);
                myCustomer.setC_ADDR_ID(C_ADDR_ID);
                myCustomer.setC_BALANCE(C_BALANCE);
                myCustomer.setC_BIRTHDATE(C_BIRTHDATE);
                myCustomer.setC_DATA(C_DATA);
                myCustomer.setC_DISCOUNT(C_DISCOUNT);
                myCustomer.setC_EMAIL(C_EMAIL);
                myCustomer.setC_EXPIRATION(C_EXPIRATION);
                myCustomer.setC_FNAME(C_FNAME);
                myCustomer.setC_ID(i);
                myCustomer.setC_LAST_LOGIN(C_LAST_LOGIN);
                myCustomer.setC_LNAME(C_LNAME);
                myCustomer.setC_LOGIN(C_LOGIN);
                myCustomer.setC_PASSWD(C_PASSWD);
                myCustomer.setC_PHONE(C_PHONE);
                myCustomer.setC_SINCE(C_SINCE);
                myCustomer.setC_UNAME(C_UNAME);

                oos.writeObject(myCustomer);
            }

            oos.writeObject(null);
            fos.flush();
            fos.close();
            oos.flush();
            oos.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();

        }


    }*/

}
