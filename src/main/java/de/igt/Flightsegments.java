package de.igt;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

//@Entity
//@Table(name = "FLIGHTSEGMENTS")
public class Flightsegments implements Serializable {
    //@Id
    //@Column(name = "name")
    private  String name;
    //@Column(name = "destination_airport")
    private String destinationAirport;
    //@Column(name = "arrival_airport")
    private String arrivalAirport;
    //@Column(name = "distance_in_mile")
    private int distanceInMile;

    public Flightsegments(String n, String da, String aa, int dim) {
        this.name = n;
        this.destinationAirport = da;
        this.arrivalAirport = aa;
        this.distanceInMile = dim;
    }

    public Flightsegments() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public int getDistanceInMile() {
        return distanceInMile;
    }

    public void setDistanceInMile(int distanceInMile) {
        this.distanceInMile = distanceInMile;
    }
}
