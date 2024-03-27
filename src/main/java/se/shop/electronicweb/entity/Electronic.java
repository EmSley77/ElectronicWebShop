package se.shop.electronicweb.entity;
//Emanuel sleyman
//2024-03-04
import jakarta.persistence.*;

@Entity
public class Electronic {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idelectronic", nullable = false)
    private int idelectronic;
    @Basic
    @Column(name = "company", nullable = false, length = 45)
    private String company;
    @Basic
    @Column(name = "categori", nullable = false, length = 45)
    private String categori;
    @Basic
    @Column(name = "name", nullable = false, length = 45)
    private String name;
    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    private double price;
    @Basic
    @Column(name = "color", nullable = false, length = 45)
    private String color;
    @Basic
    @Column(name = "size", nullable = false, length = 45)
    private String size;
    @Basic
    @Column(name = "available", nullable = false, length = 45)
    private int available;

    @Override
    public String toString() {
        return "Electronic{" +
                "idelectronic=" + idelectronic +
                ", company='" + company + '\'' +
                ", categori='" + categori + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", available=" + available +
                '}';
    }

    public Electronic(String company, String categori, String name, double price, String color, String size, int available) {
        this.company = company;
        this.categori = categori;
        this.name = name;
        this.price = price;
        this.color = color;
        this.size = size;
        this.available = available;
    }

    public Electronic() {
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getIdelectronic() {
        return idelectronic;
    }

    public void setIdelectronic(int idelectronic) {
        this.idelectronic = idelectronic;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCategori() {
        return categori;
    }

    public void setCategori(String categori) {
        this.categori = categori;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}
