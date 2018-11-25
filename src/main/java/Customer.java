package main.java;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "CUSTOMER")
public class Customer implements Serializable {
    @Id @GeneratedValue
    @Column(name = "id")
    private int id;
    @Column(name = "phone_type")
    private int phoneType;
    @Column(name = "address")
    private String address;
    @Column(name = "overall_flown_miles")
    private int overallFlownMiles;
    @Column(name = "current_flown_miles")
    private int currentFlownMiles;
    @Column(name = "status")
    private Status status;

    public Customer(int id, int pt, String a, int ofm, int cfm) {
        this.id = id;
        this.phoneType = pt;
        this.address = a;
        this.overallFlownMiles = ofm;
        this.currentFlownMiles = cfm;
        this.status = Status.NONE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(int phoneType) {
        this.phoneType = phoneType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOverallFlownMiles() {
        return overallFlownMiles;
    }

    public void setOverallFlownMiles(int overallFlownMiles) {
        this.overallFlownMiles = overallFlownMiles;
    }

    public int getCurrentFlownMiles() {
        return currentFlownMiles;
    }

    public void setCurrentFlownMiles(int currentFlownMiles) {
        this.currentFlownMiles = currentFlownMiles;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
