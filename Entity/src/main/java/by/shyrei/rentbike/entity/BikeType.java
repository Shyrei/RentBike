package by.shyrei.rentbike.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Project RentBike
 * Created on 15.07.2017.
 * author Shyrei Uladzimir
 */
public class BikeType extends Entity {
    private int id;
    private String type;
    private BigDecimal price;
    private String image;

    public BikeType() {
    }

    public BikeType(int id, String type, BigDecimal price, String image) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BikeType)) return false;
        BikeType bikeType = (BikeType) o;
        return getId() == bikeType.getId() &&
                Objects.equals(getType(), bikeType.getType()) &&
                Objects.equals(getPrice(), bikeType.getPrice()) &&
                Objects.equals(getImage(), bikeType.getImage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getType(), getPrice(), getImage());
    }

    @Override
    public String toString() {
        return "BikeType{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", price=" + price +
                ", image='" + image + '\'' +
                "} " + super.toString();
    }
}
