package de.igt.models;


import com.google.common.base.Objects;

import javax.persistence.*;

@Entity(name = "Flight")
@Table(name = "FLIGHTS")
public class Flight {
    @Id
    private String flightID;

    private String departureTime;
    private String timeOfArrival;
    private String type;
    private int priceFirstClass;
    private int priceEcoClass;
    private int seatsEco;
    private int seatsFirstClass;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "airport_id")
    private Airport airport;

    public Flight() {
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

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("flightID", flightID)
                .add("departureTime", departureTime)
                .add("timeOfArrival", timeOfArrival)
                .add("type", type)
                .add("priceFirstClass", priceFirstClass)
                .add("priceEcoClass", priceEcoClass)
                .add("seatsEco", seatsEco)
                .add("seatsFirstClass", seatsFirstClass)
                .toString();
    }

}
