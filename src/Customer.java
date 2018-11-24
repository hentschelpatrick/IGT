public class Customer {
    private int id;
    private int phoneType;
    private String address;
    private int overallFlownMiles;
    private int currentFlownMiles;
    private Status status;

    public Customer(int id, int pt, String a, int ofm, int cfm) {
        this.id = id;
        this.phoneType = pt;
        this.address = a;
        this.overallFlownMiles = ofm;
        this.currentFlownMiles = cfm;
        this.status = Status.NONE;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPhoneType() {
        return phoneType;
    }

    public void setPhoneType(int phoneType) {
        this.phoneType = phoneType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getOverallFlownMiles() {
        return overallFlownMiles;
    }

    public void setOverallFlownMiles(int overallFlownMiles) {
        this.overallFlownMiles = overallFlownMiles;
    }

    public int getCurrentFlownMiles() {
        return currentFlownMiles;
    }

    public void setCurrentFlownMiles(int currentFlownMiles) {
        this.currentFlownMiles = currentFlownMiles;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
