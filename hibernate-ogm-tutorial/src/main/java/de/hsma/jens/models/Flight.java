package de.hsma.jens.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FLIGHTS")
public class Flight{
    @ID
    private String flightID;
    private String departureTime;
    private String timeOfArrival;
    private String type;
    private int priceFirstClass;
    private int priceEcoClass;
    private int seatsEco;
    private int seatsFirstClass;

    public Flight(){

    }

    public String getFlightID() {
        return flightID;
    }

    public void setFlightID(String flightID) {
        this.flightID = flightID;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getTimeOfArrival() {
        return timeOfArrival;
    }

    public void setTimeOfArrival(String timeOfArrival) {
        this.timeOfArrival = timeOfArrival;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPriceFirstClass() {
        return priceFirstClass;
    }

    public void setPriceFirstClass(int priceFirstClass) {
        this.priceFirstClass = priceFirstClass;
    }

    public int getPriceEcoClass() {
        return priceEcoClass;
    }

    public void setPriceEcoClass(int priceEcoClass) {
        this.priceEcoClass = priceEcoClass;
    }

    public int getSeatsEco() {
        return seatsEco;
    }

    public void setSeatsEco(int seatsEco) {
        this.seatsEco = seatsEco;
    }

    public int getSeatsFirstClass() {
        return seatsFirstClass;
    }

    public void setSeatsFirstClass(int seatsFirstClass) {
        this.seatsFirstClass = seatsFirstClass;
    }
}