package de.hsma.jens.models;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Customer")
@Table(name = "CUSTOMERS")
public class Customer implements Serializable {
    @Id
    @Column
    private String email;

    private String firstname;
    private String lastname;
    private String address;
    private String country;
    private String creditCard;
    private String phonenumber;
    private long miles_flown_year;
    private long total_miles_flown;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Customer() {
    }

    public Status getStatus() { return status; }

    public void setStatus(Status status) { this.status = status; }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public long getMiles_flown_year() {
        return miles_flown_year;
    }

    public void setMiles_flown_year(long miles_flown_year) {
        this.miles_flown_year = miles_flown_year;
    }

    public long getTotal_miles_flown() {
        return total_miles_flown;
    }

    public void setTotal_miles_flown(long total_miles_flown) {
        this.total_miles_flown = total_miles_flown;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("email", email)
                .add("firstname", firstname)
                .add("lastname", lastname)
                .add("address", address)
                .add("country", country)
                .add("creditCard", creditCard)
                .add("phonenumber", phonenumber)
                .add("miles_flown_year", miles_flown_year)
                .add("total_miles_flown", total_miles_flown)
                .add("status", status)
                .toString();
    }
}
