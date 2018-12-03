package de.igt.models;


import com.google.common.base.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "Flightsegments")
@Table(name = "FLIGHTSEGMENTS")
public class FlightSegment {
    @Id
    private String name;
    private String departure_airport;
    private String arrival_airport;
    private int distance_in_miles;

    public FlightSegment() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeparture_airport() {
        return departure_airport;
    }

    public void setDeparture_airport(String departure_airport) {
        this.departure_airport = departure_airport;
    }

    public String getArrival_airport() {
        return arrival_airport;
    }

    public void setArrival_airport(String arrival_airport) {
        this.arrival_airport = arrival_airport;
    }

    public int getDistance_in_miles() {
        return distance_in_miles;
    }

    public void setDistance_in_miles(int distance_in_miles) {
        this.distance_in_miles = distance_in_miles;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("name", name)
                .add("departure_airport", departure_airport)
                .add("arrival_airport", arrival_airport)
                .add("distance_in_miles", distance_in_miles)
                .toString();
    }
}