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

    @Override
    public String toString() {
        return "Orderline{" +
                "orderlineid=" + orderlineid +
                ", orderdetailid=" + orderdetailid +
                ", productid=" + productid +
                ", quantityamount=" + quantityamount +
                ", status='" + status + '\'' +
                '}';
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Orderline orderline = (Orderline) o;

        if (orderlineid != orderline.orderlineid) return false;
        if (orderdetailid != orderline.orderdetailid) return false;
        if (productid != orderline.productid) return false;
        if (quantityamount != orderline.quantityamount) return false;
        if (status != null ? !status.equals(orderline.status) : orderline.status != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = orderlineid;
        result = 31 * result + orderdetailid;
        result = 31 * result + productid;
        result = 31 * result + quantityamount;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
