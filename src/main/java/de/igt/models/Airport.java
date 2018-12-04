package de.igt.models;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "AIRPORTS")
public class Airport {
    @Id
    private String airport_id;
    private String name;
    private String country;
    private String address;
    private int amount_international_landingsites;
    private int amount_national_landingsites;
    private int total_amount_landingsites;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "airport", fetch = FetchType.EAGER)
    private List<Flight> flights;

    public Airport() {
    }


    public String getAirport_id() {
        return airport_id;
    }

    public void setAirport_id(String airport_id) {
        this.airport_id = airport_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAmount_international_landingsites() {
        return amount_international_landingsites;
    }

    public void setAmount_international_landingsites(int amount_international_landingsites) {
        this.amount_international_landingsites = amount_international_landingsites;
        updateTotalLandingsites();
    }

    public int getAmount_national_landingsites() {
        return amount_national_landingsites;
    }

    public void setAmount_national_landingsites(int amount_national_landingsites) {
        this.amount_national_landingsites = amount_national_landingsites;
        updateTotalLandingsites();
    }

    private void updateTotalLandingsites() {
        this.total_amount_landingsites = this.amount_national_landingsites + this.amount_international_landingsites;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public void addFlight(Flight flight) {
        this.flights.add(flight);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("airport_id", airport_id)
                .add("name", name)
                .add("country", country)
                .add("address", address)
                .add("amount_international_landingsites", amount_international_landingsites)
                .add("amount_national_landingsites", amount_national_landingsites)
                .add("total_amount_landingsites", total_amount_landingsites)
                .toString();
    }
}
