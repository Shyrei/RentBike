package by.shyrei.rentbike.dao;

import by.shyrei.rentbike.entity.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.*;

/**
 * Project RentBike
 * Created on 22.07.2017.
 * author Shyrei Uladzimir
 */
public class OrderDaoTest {
    @Test
    public void findEntityById() throws Exception {
        /*int userId = 31;
        OrderDao test = new OrderDao();
        Order order = test.calculateOrder(userId);
        int orderId = order.getId();

        LocalDateTime first = order.getStartRent();
        LocalDateTime second = LocalDateTime.now();
        long minutes = ChronoUnit.MINUTES.between(first, second);*/
        BigDecimal price = new BigDecimal("0.3");
        System.out.println(price);
        BigDecimal minutes = new BigDecimal(160);
        System.out.println(minutes);
        BigDecimal value = price.multiply(minutes);
        System.out.println(value);

    }

}