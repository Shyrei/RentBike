package by.shyrei.rentbike.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Project RentBike
 * Created on 22.07.2017.
 * author Shyrei Uladzimir
 */
public class Order extends Entity {
    private int id;
    private int bikeId;
    private int userId;
    private LocalDateTime startRent;
    private LocalDateTime endRent;
    private BigDecimal value;
    private BigDecimal discount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBikeId() {
        return bikeId;
    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getStartRent() {
        return startRent;
    }

    public void setStartRent(LocalDateTime startRent) {
        this.startRent = startRent;
    }

    public LocalDateTime getEndRent() {
        return endRent;
    }

    public void setEndRent(LocalDateTime endRent) {
        this.endRent = endRent;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getId() == order.getId() &&
                getBikeId() == order.getBikeId() &&
                getUserId() == order.getUserId() &&
                Objects.equals(getStartRent(), order.getStartRent()) &&
                Objects.equals(getEndRent(), order.getEndRent()) &&
                Objects.equals(getValue(), order.getValue()) &&
                Objects.equals(getDiscount(), order.getDiscount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBikeId(), getUserId(), getStartRent(), getEndRent(), getValue(), getDiscount());
    }
}
