package de.igt.models;


import com.google.common.base.Objects;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Customer")
@Table(name = "CUSTOMERS")
public class Customer implements Serializable {
    @Id
    @Column
    private String EMAIL;
    @Column
    private String FIRST_NAME;
    @Column
    private String LAST_NAME;
    @Column
    private String ADDRESS;
    @Column
    private String COUNTRY;
    @Column
    private String PHONE_TYPE;
    @Column
    private int AGE;
    @Column
    private long MILES_FLOWN_YEAR;
    @Column
    private long TOTAL_MILES_FLOWN;


    @ManyToMany
    public Set<Flight> FLIGHTS = new HashSet<Flight>();


    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Customer() {
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public int getAGE() {
        return AGE;
    }

    public void setAGE(int AGE) {
        this.AGE = AGE;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getFIRST_NAME() {
        return FIRST_NAME;
    }

    public void setFIRST_NAME(String FIRST_NAME) {
        this.FIRST_NAME = FIRST_NAME;
    }

    public String getLAST_NAME() {
        return LAST_NAME;
    }

    public void setLAST_NAME(String LAST_NAME) {
        this.LAST_NAME = LAST_NAME;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public String getCOUNTRY() {
        return COUNTRY;
    }

    public void setCOUNTRY(String COUNTRY) {
        this.COUNTRY = COUNTRY;
    }

    public String getPHONE_TYPE() {
        return PHONE_TYPE;
    }

    public void setPHONE_TYPE(String PHONE_TYPE) {
        this.PHONE_TYPE = PHONE_TYPE;
    }

    public long getMILES_FLOWN_YEAR() {
        return MILES_FLOWN_YEAR;
    }

    public void setMILES_FLOWN_YEAR(long MILES_FLOWN_YEAR) {
        this.MILES_FLOWN_YEAR = MILES_FLOWN_YEAR;
    }

    public long getTOTAL_MILES_FLOWN() {
        return TOTAL_MILES_FLOWN;
    }

    public void setTOTAL_MILES_FLOWN(long TOTAL_MILES_FLOWN) {
        this.TOTAL_MILES_FLOWN = TOTAL_MILES_FLOWN;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<Flight> getFLIGHTS() {
        return FLIGHTS;
    }

    public void setFLIGHTS(Set<Flight> FLIGHTS) {
        this.FLIGHTS = FLIGHTS;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("EMAIL", EMAIL)
                .add("FIRST_NAME", FIRST_NAME)
                .add("LAST_NAME", LAST_NAME)
                .add("ADDRESS", ADDRESS)
                .add("COUNTRY", COUNTRY)
                .add("PHONE_TYPE", PHONE_TYPE)
                .add("MILES_FLOWN_YEAR", MILES_FLOWN_YEAR)
                .add("TOTAL_MILES_FLOWN", TOTAL_MILES_FLOWN)
                .add("status", status)
                .add("Flights: ", this.FLIGHTS)
                .toString();
    }
}
