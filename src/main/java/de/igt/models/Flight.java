package de.igt.models;


import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.*;

@Entity(name = "Flight")
@Table(name = "FLIGHTS")
public class Flight {
    @Id
    private String FLIGHT_ID;

    private Date DEPARTURE_TIME;
    private Date ARRIVAL_TIME;
    private String TYPE;
    private int PRICE_FIRST_CLASS;
    private int PRICE_ECO_CLASS;
    private int SEATS_ECO_CLASS;
    private int SEATS_FIRST_CLASS;


    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER, mappedBy = "FLIGHT")
    private List<FlightSegment> flightSegmentList = new ArrayList<>();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "CUSTOMERS_FLIGHTS",
            joinColumns = {@JoinColumn(name = "FLIGHT_ID")},
            inverseJoinColumns = {@JoinColumn(name = "EMAIL")}
    )
    public Set<Customer> customers = new HashSet<>();

    public Flight() {
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("FLIGHT_ID", FLIGHT_ID)
                .add("DEPARTURE_TIME", DEPARTURE_TIME)
                .add("ARRIVAL_TIME", ARRIVAL_TIME)
                .add("TYPE", TYPE)
                .add("PRICE_FIRST_CLASS", PRICE_FIRST_CLASS)
                .add("PRICE_ECO_CLASS", PRICE_ECO_CLASS)
                .add("SEATS_ECO_CLASS", SEATS_ECO_CLASS)
                .add("SEATS_FIRST_CLASS", SEATS_FIRST_CLASS)
                .toString();
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true
            , mappedBy = "FLIGHT", fetch = FetchType.EAGER)
    public List<FlightSegment> getFlightSegmentList() {
        return flightSegmentList;
    }

    public void setFlightSegmentList(List<FlightSegment> flightSegmentList) {
        this.flightSegmentList = flightSegmentList;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }

    public String getFLIGHT_ID() {
        return FLIGHT_ID;
    }

    public void setFLIGHT_ID(String flightID) {
        this.FLIGHT_ID = flightID;
    }

    public Date getDEPARTURE_TIME() {
        return DEPARTURE_TIME;
    }

    public void setDEPARTURE_TIME(Date DEPARTURE_TIME) {
        this.DEPARTURE_TIME = DEPARTURE_TIME;
    }

    public Date getARRIVAL_TIME() {
        return ARRIVAL_TIME;
    }

    public void setARRIVAL_TIME(Date ARRIVAL_TIME) {
        this.ARRIVAL_TIME = ARRIVAL_TIME;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }

    public int getPRICE_FIRST_CLASS() {
        return PRICE_FIRST_CLASS;
    }

    public void setPRICE_FIRST_CLASS(int PRICE_FIRST_CLASS) {
        this.PRICE_FIRST_CLASS = PRICE_FIRST_CLASS;
    }

    public int getPRICE_ECO_CLASS() {
        return PRICE_ECO_CLASS;
    }

    public void setPRICE_ECO_CLASS(int PRICE_ECO_CLASS) {
        this.PRICE_ECO_CLASS = PRICE_ECO_CLASS;
    }

    public int getSEATS_ECO_CLASS() {
        return SEATS_ECO_CLASS;
    }

    public void setSEATS_ECO_CLASS(int SEATS_ECO_CLASS) {
        this.SEATS_ECO_CLASS = SEATS_ECO_CLASS;
    }

    public int getSEATS_FIRST_CLASS() {
        return SEATS_FIRST_CLASS;
    }

    public void setSEATS_FIRST_CLASS(int SEATS_FIRST_CLASS) {
        this.SEATS_FIRST_CLASS = SEATS_FIRST_CLASS;
    }
}
