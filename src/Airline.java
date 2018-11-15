public class Airline {
    private String destination_airport;
    private String arrival_airport;
    private String departure_time;
    private String arrival_time;
    //private String connecting_flight;
    private int prize;

    private Customer customer;
    private int customerID;

    public Airline(String da, String aa, String dt, String at, int prize, Customer customer) {
        this.destination_airport = da;
        this.arrival_airport = aa;
        this.departure_time = dt;
        this.arrival_time = at;
        this.prize = prize;
        this.customer = customer;
        this.customerID = customer.getId();
    }


    public String getDestination_airport() {
        return destination_airport;
    }

    public void setDestination_airport(String destination_airport) {
        this.destination_airport = destination_airport;
    }

    public String getArrival_airport() {
        return arrival_airport;
    }

    public void setArrival_airport(String arrival_airport) {
        this.arrival_airport = arrival_airport;
    }

    public String getDeparture_time() {
        return departure_time;
    }

    public void setDeparture_time(String departure_time) {
        this.departure_time = departure_time;
    }

    public String getArrival_time() {
        return arrival_time;
    }

    public void setArrival_time(String arrival_time) {
        this.arrival_time = arrival_time;
    }

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}
