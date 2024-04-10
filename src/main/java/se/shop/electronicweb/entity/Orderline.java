package se.shop.electronicweb.entity;
//Emanuel sleyman
//2024-03-08
import jakarta.persistence.*;

@Entity
public class Orderline {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "orderlineid", nullable = false)
    private int orderlineid;
    @Basic
    @Column(name = "orderdetailid", nullable = false)
    private int orderdetailid;
    @Basic
    @Column(name = "productid", nullable = false)
    private int productid;
    @Basic
    @Column(name = "quantityamount", nullable = false)
    private int quantityamount;
    @Basic
    @Column(name = "cost", nullable = false)
    private int cost;
    @Basic
    @Column(name = "status", nullable = true, length = 45)
    private String status;


    public Orderline(int orderdetailid, int productid, int quantityamount, String status, String recieved) {
        this.orderdetailid = orderdetailid;
        this.productid = productid;
        this.quantityamount = quantityamount;
        this.status = status;

    }

    public Orderline() {

    }


    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getOrderlineid() {
        return orderlineid;
    }

    public void setOrderlineid(int orderlineid) {
        this.orderlineid = orderlineid;
    }

    public int getOrderdetailid() {
        return orderdetailid;
    }

    public void setOrderdetailid(int orderdetailid) {
        this.orderdetailid = orderdetailid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public int getQuantityamount() {
        return quantityamount;
    }

    public void setQuantityamount(int quantityamount) {
        this.quantityamount = quantityamount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
