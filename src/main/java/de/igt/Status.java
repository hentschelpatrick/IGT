package main.java;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STATUS")
public enum Status {
    @Column(name = "status")
    NONE, SILVER, GOLD, PLATINUM, SPECIAL_PLATINUM, WHITE_GOLD;

    private int customerID;

    @Id
    public int getId() {
        return customerID;
    }

    public void setId(int id) {
        this.customerID = id;
    }
}
