package se.shop.electronicweb.entity;
//Emanuel sleyman
//2024-03-04
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Orderdetails {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idorderdetails", nullable = false)
    private int idorderdetails;
    @Basic
    @Column(name = "customerid", nullable = false)
    private int customerid;
    @Basic
    @Column(name = "time", nullable = false)
    private Timestamp time;
    @Basic
    @Column(name = "totalcost", nullable = false, precision = 0)
    private double totalcost;

    public Orderdetails() {
    }

    public Orderdetails(int customerid, Timestamp time, double totalcost) {
        this.customerid = customerid;
        this.time = time;
        this.totalcost = totalcost;
    }

    public int getIdorderdetails() {
        return idorderdetails;
    }

    public void setIdorderdetails(int idorderdetails) {
        this.idorderdetails = idorderdetails;
    }

    public int getCustomerid() {
        return customerid;
    }

    public void setCustomerid(int customerid) {
        this.customerid = customerid;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public double getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(double totalcost) {
        this.totalcost = totalcost;
    }


}
