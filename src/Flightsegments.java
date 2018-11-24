public class Flightsegments {
    private  String name;
    private String destinationAirport;
    private String arrivalAirport;
    private int distanceInMile;

    public Flightsegments(String n, String da, String aa, int dim) {
        this.name = n;
        this.destinationAirport = da;
        this.arrivalAirport = aa;
        this.distanceInMile = dim;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestinationAirport() {
        return destinationAirport;
    }

    public void setDestinationAirport(String destinationAirport) {
        this.destinationAirport = destinationAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public int getDistanceInMile() {
        return distanceInMile;
    }

    public void setDistanceInMile(int distanceInMile) {
        this.distanceInMile = distanceInMile;
    }
}
