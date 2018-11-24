package main.java;

public class Airline {
    private String name;
    private int airportID;

    public Airline(String n, int aid) {
        this.name = n;
        this.airportID = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAirportID() {
        return airportID;
    }

    public void setAirportID(int airportID) {
        this.airportID = airportID;
    }
}
