package de.hsma.jens.models;

import com.google.common.base.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name = "Customer")
@Table(name = "CUSTOMERS")
public class Custom_Customer implements Serializable {
    @Id
    @Column
    private String email;

    private String first_name;
    private String last_name;
    private String address;
    private String country;
    private String creditCard;
    private String phonenumber;
    private long miles_flown_year;
    private long total_miles_flown;

    public Custom_Customer() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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
                .add("first_name", first_name)
                .add("last_name", last_name)
                .add("address", address)
                .add("country", country)
                .add("creditCard", creditCard)
                .add("phonenumber", phonenumber)
                .add("miles_flown_year", miles_flown_year)
                .add("total_miles_flown", total_miles_flown)
                .toString();
    }


}
