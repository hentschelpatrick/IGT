package de.hsma.jens.models;


import javax.persistence.*;

@Entity
@Table(name = "FLIGHTS")
public class Flight {

    @Id
    @GeneratedValue
    private int flight_id;

    private String abflugzeit;
    private String ankunfszeit;
    private String typbezeichnung;
    private long sitzplatz_preis_eco;
    private int sitzplatz_anzahl_eco;
    private int sitzplatz_anzahl_firstclass;
    private long sitzplatz_preis_firstclass;


    public Flight() {
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public String getAbflugzeit() {
        return abflugzeit;
    }

    public void setAbflugzeit(String abflugzeit) {
        this.abflugzeit = abflugzeit;
    }

    public String getAnkunfszeit() {
        return ankunfszeit;
    }

    public void setAnkunfszeit(String ankunfszeit) {
        this.ankunfszeit = ankunfszeit;
    }

    public String getTypbezeichnung() {
        return typbezeichnung;
    }

    public void setTypbezeichnung(String typbezeichnung) {
        this.typbezeichnung = typbezeichnung;
    }

    public long getSitzplatz_preis_eco() {
        return sitzplatz_preis_eco;
    }

    public void setSitzplatz_preis_eco(long sitzplatz_preis_eco) {
        this.sitzplatz_preis_eco = sitzplatz_preis_eco;
    }

    public int getSitzplatz_anzahl_eco() {
        return sitzplatz_anzahl_eco;
    }

    public void setSitzplatz_anzahl_eco(int sitzplatz_anzahl_eco) {
        this.sitzplatz_anzahl_eco = sitzplatz_anzahl_eco;
    }

    public int getSitzplatz_anzahl_firstclass() {
        return sitzplatz_anzahl_firstclass;
    }

    public void setSitzplatz_anzahl_firstclass(int sitzplatz_anzahl_firstclass) {
        this.sitzplatz_anzahl_firstclass = sitzplatz_anzahl_firstclass;
    }

    public long getSitzplatz_preis_firstclass() {
        return sitzplatz_preis_firstclass;
    }

    public void setSitzplatz_preis_firstclass(long sitzplatz_preis_firstclass) {
        this.sitzplatz_preis_firstclass = sitzplatz_preis_firstclass;
    }
}
