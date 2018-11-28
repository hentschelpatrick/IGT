package de.hsma.jens.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
    private int amount_landingsites;

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
    }

    public int getAmount_national_landingsites() {
        return amount_national_landingsites;
    }

    public void setAmount_national_landingsites(int amount_national_landingsites) {
        this.amount_national_landingsites = amount_national_landingsites;
    }

    public int getAmount_landingsites() {
        return amount_landingsites;
    }

    public void setAmount_landingsites(int amount_landingsites) {
        this.amount_landingsites = amount_landingsites;
    }
}
