package by.shyrei.rentbike.entity;

import java.util.Objects;

/**
 * Project RentBike
 * Created on 15.07.2017.
 * author Shyrei Uladzimir
 */
public class Station extends Entity {
    private int id;
    private String city;
    private String location;

    public Station() {
    }

    public Station(int id, String city, String location) {
        this.id = id;
        this.city = city;
        this.location = location;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Station)) return false;
        Station station = (Station) o;
        return getId() == station.getId() &&
                Objects.equals(getCity(), station.getCity()) &&
                Objects.equals(getLocation(), station.getLocation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getCity(), getLocation());
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", location='" + location + '\'' +
                '}';
    }
}
