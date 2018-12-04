package de.igt.models;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Airport")
@Table(name = "AIRPORTS")
public class Airport {
    @Id
    private String NAME;
    private String COUNTRY;
    private String ADDRESS;
    private int AMOUNT_INTERNATIONAL_LANDINGSITES;
    private int AMOUNT_NATIONAL_LANDINGSITES;

    public Airport() {
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getCOUNTRY() {
        return COUNTRY;
    }

    public void setCOUNTRY(String COUNTRY) {
        this.COUNTRY = COUNTRY;
    }

    public String getADDRESS() {
        return ADDRESS;
    }

    public void setADDRESS(String ADDRESS) {
        this.ADDRESS = ADDRESS;
    }

    public int getAMOUNT_INTERNATIONAL_LANDINGSITES() {
        return AMOUNT_INTERNATIONAL_LANDINGSITES;
    }

    public void setAMOUNT_INTERNATIONAL_LANDINGSITES(int AMOUNT_INTERNATIONAL_LANDINGSITES) {
        this.AMOUNT_INTERNATIONAL_LANDINGSITES = AMOUNT_INTERNATIONAL_LANDINGSITES;
    }

    public int getAMOUNT_NATIONAL_LANDINGSITES() {
        return AMOUNT_NATIONAL_LANDINGSITES;
    }

    public void setAMOUNT_NATIONAL_LANDINGSITES(int AMOUNT_NATIONAL_LANDINGSITES) {
        this.AMOUNT_NATIONAL_LANDINGSITES = AMOUNT_NATIONAL_LANDINGSITES;
    }


    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("NAME", NAME)
                .add("COUNTRY", COUNTRY)
                .add("ADDRESS", ADDRESS)
                .add("AMOUNT_INTERNATIONAL_LANDINGSITES", AMOUNT_INTERNATIONAL_LANDINGSITES)
                .add("AMOUNT_NATIONAL_LANDINGSITES", AMOUNT_NATIONAL_LANDINGSITES)
                .toString();
    }
}
