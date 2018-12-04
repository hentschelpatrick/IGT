package de.igt.models;


import com.google.common.base.Objects;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.List;

@Entity(name = "FlightSegment")
@Table(name = "FLIGHTSEGMENTS")
public class FlightSegment {
    @Id
    private String NAME;
    private long DISTANCE_MILES;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Flight FLIGHT;

    @OneToOne
    private Airport DEPARTURE_AIRPORT;

    @OneToOne
    private Airport ARRIVAL_AIRPORT;

    public FlightSegment() {
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("NAME", NAME)
                .add("DISTANCE_MILES", DISTANCE_MILES)
                .toString();
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public long getDISTANCE_MILES() {
        return DISTANCE_MILES;
    }

    public void setDISTANCE_MILES(long DISTANCE_MILES) {
        this.DISTANCE_MILES = DISTANCE_MILES;
    }


    public Airport getDEPARTURE_AIRPORT() {
        return DEPARTURE_AIRPORT;
    }

    public void setDEPARTURE_AIRPORT(Airport DEPARTURE_AIRPORT) {
        this.DEPARTURE_AIRPORT = DEPARTURE_AIRPORT;
    }

    public void setARRIVAL_AIRPORT(Airport arrivalAirport) {
        this.ARRIVAL_AIRPORT = arrivalAirport;
    }

    public Airport getARRIVAL_AIRPORT() {
        return ARRIVAL_AIRPORT;
    }

}