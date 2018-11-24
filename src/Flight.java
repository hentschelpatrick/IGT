public class Flight {
    int id;
    String arrival;
    String departure;
    String airplaneType;
    int firstClassSeats;
    int firstClassPrize;
    int ecoSeats;
    int ecoPrize;

    public Flight(int id, String a, String d, String apt, int fcs, int fcp, int es, int ep) {
        this.id = id;
        this.arrival = a;
        this.departure = d;
        this.airplaneType = apt;
        this.firstClassSeats = fcs;
        this.firstClassPrize = fcp;
        this. ecoSeats = es;
        this.ecoPrize = ep;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getAirplaneType() {
        return airplaneType;
    }

    public void setAirplaneType(String airplaneType) {
        this.airplaneType = airplaneType;
    }

    public int getFirstClassSeats() {
        return firstClassSeats;
    }

    public void setFirstClassSeats(int firstClassSeats) {
        this.firstClassSeats = firstClassSeats;
    }

    public int getFirstClassPrize() {
        return firstClassPrize;
    }

    public void setFirstClassPrize(int firstClassPrize) {
        this.firstClassPrize = firstClassPrize;
    }

    public int getEcoSeats() {
        return ecoSeats;
    }

    public void setEcoSeats(int ecoSeats) {
        this.ecoSeats = ecoSeats;
    }

    public int getEcoPrize() {
        return ecoPrize;
    }

    public void setEcoPrize(int ecoPrize) {
        this.ecoPrize = ecoPrize;
    }
}
