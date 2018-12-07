package de.igt.models;

import javax.persistence.*;

@Entity(name = "PhoneType")
@Table(name = "PHONETYPE")
public class PhoneType {
    @Id
    @Column
    private String PHONE_ID;
    @Column
    private int PHONE_NUMBER;
    @Column
    private String PHONE_TYPE;

    @ManyToOne
    private Customer COSTUMER;

    public PhoneType() {
    }


    public String getPHONE_ID() {
        return PHONE_ID;
    }

    public void setPHONE_ID(String PHONE_ID) {
        this.PHONE_ID = PHONE_ID;
    }

    public int getPHONE_NUMBER() {
        return PHONE_NUMBER;
    }

    public void setPHONE_NUMBER(int PHONE_NUMBER) {
        this.PHONE_NUMBER = PHONE_NUMBER;
    }

    public String getPHONE_TYPE() {
        return PHONE_TYPE;
    }

    public void setPHONE_TYPE(String PHONE_TYPE) {
        this.PHONE_TYPE = PHONE_TYPE;
    }

    public Customer getCOSTUMER() {
        return COSTUMER;
    }

    public void setCOSTUMER(Customer COSTUMER) {
        this.COSTUMER = COSTUMER;
    }

    @Override
    public String toString() {
        return "PhoneType{" +
                "PHONE_ID='" + PHONE_ID + '\'' +
                ", PHONE_NUMBER=" + PHONE_NUMBER +
                ", PHONE_TYPE='" + PHONE_TYPE + '\'' +
                ", COSTUMER=" + COSTUMER +
                '}';
    }
}
