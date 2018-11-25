package main.java;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AIRLINE")
public class Airline {
    @Id
    @Column(name = "airport_id")
    private int airportID;
    @Column(name = "name")
    private String name;

    public Airline(int aid, String n) {
        this.airportID = aid;
        this.name = n;
    }

    public int getAirportID() {
        return airportID;
    }

    public void setAirportID(int airportID) { this.airportID = airportID; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }
}
