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

    @Override
    public String toString() {
        return "Electronic{" +
                "\n   ID: " + idelectronic +
                "\n   Company: " + company +
                "\n   Category: " + categori +
                "\n   Name: " + name +
                "\n   Price: $" + price +
                "\n   Color: " + color +
                "\n   Size: " + size +
                "\n}";
    }


    public Electronic(String company, String categori, String name, double price, String color, String size) {
        this.company = company;
        this.categori = categori;
        this.name = name;
        this.price = price;
        this.color = color;
        this.size = size;
    }

    public Electronic() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Electronic that = (Electronic) o;

        if (idelectronic != that.idelectronic) return false;
        if (Double.compare(price, that.price) != 0) return false;
        if (company != null ? !company.equals(that.company) : that.company != null) return false;
        if (categori != null ? !categori.equals(that.categori) : that.categori != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (color != null ? !color.equals(that.color) : that.color != null) return false;
        if (size != null ? !size.equals(that.size) : that.size != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = idelectronic;
        result = 31 * result + (company != null ? company.hashCode() : 0);
        result = 31 * result + (categori != null ? categori.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + (size != null ? size.hashCode() : 0);
        return result;
    }
}
