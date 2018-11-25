package main.java;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "FLIGHT")
public class Flight implements Serializable {
    @Id @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "arrival")
    private Date arrival;
    @Column(name = "departure")
    private Date departure;
    @Column(name = "airplane_type")
    private String airplaneType;
    @Column(name = "first_class_seats")
    private int firstClassSeats;
    @Column(name = "first_class_prize")
    private int firstClassPrize;
    @Column(name = "eco_seats")
    private int ecoSeats;
    @Column(name = "eco_prize")
    private int ecoPrize;

    public Flight(int id, Date a, Date d, String apt, int fcs, int fcp, int es, int ep) {
        this.id = id;
        this.arrival = a;
        this.departure = d;
        this.airplaneType = apt;
        this.firstClassSeats = fcs;
        this.firstClassPrize = fcp;
        this. ecoSeats = es;
        this.ecoPrize = ep;
    }

    public Flight() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    public String getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(String airplaneType) {
        this.airplaneType = airplaneType;
    }

    public int getFirstClassSeats() {
        return firstClassSeats;
    }

    public void setFirstClassSeats(int firstClassSeats) {
        this.firstClassSeats = firstClassSeats;
    }

    public int getFirstClassPrize() {
        return firstClassPrize;
    }

    public void setFirstClassPrize(int firstClassPrize) {
        this.firstClassPrize = firstClassPrize;
    }

    public int getEcoSeats() {
        return ecoSeats;
    }

    public void setEcoSeats(int ecoSeats) {
        this.ecoSeats = ecoSeats;
    }

    public int getEcoPrize() {
        return ecoPrize;
    }

    public void setEcoPrize(int ecoPrize) {
        this.ecoPrize = ecoPrize;
    }
}
