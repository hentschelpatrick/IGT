package de.igt.tools;

import java.util.Random;

public class Config {
    public static final Integer NUMBER_OF_CUSTOMERS = 100;
    public static final String PERSISTENCE_UNIT_NAME = PERSISTENCE_UNITS.OGM_MYSQL.name();
    public static final String LOG_STORAGE_LOCATION = "./logs/" + PERSISTENCE_UNIT_NAME + "_crud_performance.txt";
    public static final Integer TRANSACTION_TIMEOUT = 900000;
    public static final String PERSIST_STORAGE_LOCATION = "./logs/customers.txt";
    public static final String PERSIST_STORAGE_OUTPUT_LOCATION = "./logs/customers_out.txt";

    public enum PERSISTENCE_UNITS {
        OGM_MYSQL, OGM_POSTGRESQL, OGM_MONGODB, OGM_NEO4J, OGM_INFINISPAN, OGM_CASSANDRA, OGM_REDIS,
    }



    //Methods used by ClassPopulator files

    private static Random rand = new Random();

    public static String getRandomAString(int min, int max) {
        String newstring = new String();
        int i;
        final char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
                'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '!', '@', '#',
                '$', '%', '^', '&', '*', '(', ')', '_', '-', '=', '+',
                '{', '}', '[', ']', '|', ':', ';', ',', '.', '?', '/',
                '~', ' '}; //79 characters
        int strlen = (int) Math.floor(rand.nextDouble() * ((max - min) + 1));
        strlen += min;
        for (i = 0; i < strlen; i++) {
            char c = chars[(int) Math.floor(rand.nextDouble() * 79)];
            newstring = newstring.concat(String.valueOf(c));
        }
        return newstring;
    }

    public static String getRandomAString(int length) {
        String newstring = new String();
        int i;
        final char[] chars = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k',
                'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
                'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
                'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '!', '@', '#',
                '$', '%', '^', '&', '*', '(', ')', '_', '-', '=', '+',
                '{', '}', '[', ']', '|', ':', ';', ',', '.', '?', '/',
                '~', ' '}; //79 characters
        for (i = 0; i < length; i++) {
            char c = chars[(int) Math.floor(rand.nextDouble() * 79)];
            newstring = newstring.concat(String.valueOf(c));
        }
        return newstring;
    }

    public static int getRandomNString(int num_digits) {
        int return_num = 0;
        for (int i = 0; i < num_digits; i++) {
            return_num += getRandomInt(0, 9) *
                    (int) java.lang.Math.pow(10.0, (double) i);
        }
        return return_num;
    }

    public static int getRandomNString(int min, int max) {
        int strlen = (int) Math.floor(rand.nextDouble() * ((max - min) + 1)) + min;
        return getRandomNString(strlen);
    }

    public static int getRandomInt(int lower, int upper) {

        int num = (int) Math.floor(rand.nextDouble() * ((upper + 1) - lower));
        if (num + lower > upper || num + lower < lower) {
            System.out.println("ERROR: Random returned value of of range!");
            System.exit(1);
        }
        return num + lower;
    }


    public static String DigSyl(int D, int N) {
        int i;
        String resultString = new String();
        String Dstr = Integer.toString(D);

        if (N > Dstr.length()) {
            int padding = N - Dstr.length();
            for (i = 0; i < padding; i++)
                resultString = resultString.concat("BA");
        }

        for (i = 0; i < Dstr.length(); i++) {
            if (Dstr.charAt(i) == '0')
                resultString = resultString.concat("BA");
            else if (Dstr.charAt(i) == '1')
                resultString = resultString.concat("OG");
            else if (Dstr.charAt(i) == '2')
                resultString = resultString.concat("AL");
            else if (Dstr.charAt(i) == '3')
                resultString = resultString.concat("RI");
            else if (Dstr.charAt(i) == '4')
                resultString = resultString.concat("RE");
            else if (Dstr.charAt(i) == '5')
                resultString = resultString.concat("SE");
            else if (Dstr.charAt(i) == '6')
                resultString = resultString.concat("AT");
            else if (Dstr.charAt(i) == '7')
                resultString = resultString.concat("UL");
            else if (Dstr.charAt(i) == '8')
                resultString = resultString.concat("IN");
            else if (Dstr.charAt(i) == '9')
                resultString = resultString.concat("NG");
        }

        return resultString;
    }

}
