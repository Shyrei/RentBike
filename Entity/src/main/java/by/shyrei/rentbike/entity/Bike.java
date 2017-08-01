package by.shyrei.rentbike.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Project RentBike
 * Created on 15.07.2017.
 * author Shyrei Uladzimir
 */
public class Bike extends Entity {
    private int id;
    private boolean inRent;
    private int typeId;
    private int stationId;
    private String type;
    private BigDecimal pricePerHour;
    private String city;
    private String location;
    private String picture;

    public Bike() {
    }

    public Bike(int id, boolean inRent, int typeId, int stationId, String type, BigDecimal pricePerHour, String city, String location, String picture) {
        this.id = id;
        this.inRent = inRent;
        this.typeId = typeId;
        this.stationId = stationId;
        this.type = type;
        this.pricePerHour = pricePerHour;
        this.city = city;
        this.location = location;
        this.picture = picture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isInRent() {
        return inRent;
    }

    public void setInRent(boolean inRent) {
        this.inRent = inRent;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPricePerHour() {
        return pricePerHour;
    }

    public void setPricePerHour(BigDecimal pricePerHour) {
        this.pricePerHour = pricePerHour;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Bike)) return false;
        Bike bike = (Bike) o;
        return getId() == bike.getId() &&
                isInRent() == bike.isInRent() &&
                getTypeId() == bike.getTypeId() &&
                getStationId() == bike.getStationId() &&
                Objects.equals(getType(), bike.getType()) &&
                Objects.equals(getPricePerHour(), bike.getPricePerHour()) &&
                Objects.equals(getCity(), bike.getCity()) &&
                Objects.equals(getLocation(), bike.getLocation()) &&
                Objects.equals(getPicture(), bike.getPicture());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), isInRent(), getTypeId(), getStationId(), getType(), getPricePerHour(), getCity(), getLocation(), getPicture());
    }

    @Override
    public String toString() {
        return "Bike{" +
                "id=" + id +
                ", inRent=" + inRent +
                ", typeId=" + typeId +
                ", stationId=" + stationId +
                ", type='" + type + '\'' +
                ", pricePerHour=" + pricePerHour +
                ", city='" + city + '\'' +
                ", location='" + location + '\'' +
                ", picture='" + picture + '\'' +
                "} " + super.toString();
    }
}
