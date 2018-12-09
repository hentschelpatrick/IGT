package de.igt.models;

import org.hibernate.search.annotations.Indexed;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Indexed
@Table(name = "CUSTOMERSTEST")
public class CustomerTest {
    @Id
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
    private int AGE;
    @Column
    private long MILES_FLOWN_YEAR;
    @Column
    private long TOTAL_MILES_FLOWN;

    public CustomerTest() {
    }

    public String getEMAIL() {
        return EMAIL;
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

    public int getAGE() {
        return AGE;
    }

    public void setAGE(int AGE) {
        this.AGE = AGE;
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

    @Override
    public String toString() {
        return "CustomerTest{" +
                "EMAIL='" + EMAIL + '\'' +
                ", FIRST_NAME='" + FIRST_NAME + '\'' +
                ", LAST_NAME='" + LAST_NAME + '\'' +
                ", ADDRESS='" + ADDRESS + '\'' +
                ", COUNTRY='" + COUNTRY + '\'' +
                ", AGE=" + AGE +
                ", MILES_FLOWN_YEAR=" + MILES_FLOWN_YEAR +
                ", TOTAL_MILES_FLOWN=" + TOTAL_MILES_FLOWN +
                '}';
    }
}
