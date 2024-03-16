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
    @Column(name = "productid", nullable = false)
    private int productid;
    @Basic
    @Column(name = "quantity", nullable = false)
    private int quantity;
    @Basic
    @Column(name = "time", nullable = false)
    private Timestamp time;
    @Basic
    @Column(name = "totalcost", nullable = false, precision = 0)
    private double totalcost;

    public Orderdetails() {
    }

    public Orderdetails(int customerid, int productid, int quantity, Timestamp time, double totalcost) {
        this.customerid = customerid;
        this.productid = productid;
        this.quantity = quantity;
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





    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orderdetails that = (Orderdetails) o;

        if (idorderdetails != that.idorderdetails) return false;
        if (customerid != that.customerid) return false;
        if (productid != that.productid) return false;
        if (quantity != that.quantity) return false;
        if (Double.compare(totalcost, that.totalcost) != 0) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idorderdetails;
        result = 31 * result + customerid;
        result = 31 * result + productid;
        result = 31 * result + quantity;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        temp = Double.doubleToLongBits(totalcost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
