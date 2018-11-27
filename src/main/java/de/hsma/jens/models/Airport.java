package de.hsma.jens.models;

import javax.persistence.*;

@Entity(name = "Airport")
@Table(name = "AIRPORTS")
public class Airport {
    @Id
    @GeneratedValue
    private int airport_id;

    private String name;

    private String address;

    private String country;

    public Airport(String name, String address, String country) {
        this.name = name;
        this.address = address;
        this.country = country;
    }

    public Airport() {
    }

    public int getAirport_id() {
        return airport_id;
    }

    public void setAirport_id(int airport_id) {
        this.airport_id = airport_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
