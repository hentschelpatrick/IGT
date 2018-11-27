package de.hsma.jens.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * Created by jenskohler on 12.12.17.
 */

@Entity
@Table(name = "CUSTOMERS")
public class OurCustomer implements Serializable {
    @Id
    private String email;

    private long miles_flown_yearly;
    private long total_miles_flown;

    private String country;
    private String customer_address;
    private String first_name;
    private String last_name;
    private int age;
    private String current_state;

    public OurCustomer() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getMiles_flown_yearly() {
        return miles_flown_yearly;
    }

    public void setMiles_flown_yearly(long miles_flown_yearly) {
        this.miles_flown_yearly = miles_flown_yearly;
    }

    public long getTotal_miles_flown() {
        return total_miles_flown;
    }

    public void setTotal_miles_flown(long total_miles_flown) {
        this.total_miles_flown = total_miles_flown;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCurrent_state() {
        return current_state;
    }

    public void setCurrent_state(String current_state) {
        this.current_state = current_state;
    }
}