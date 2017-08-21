package by.shyrei.rentbike.service;

import by.shyrei.rentbike.dao.BikeDao;
import by.shyrei.rentbike.dao.OrderDao;
import by.shyrei.rentbike.entity.Bike;
import by.shyrei.rentbike.entity.Order;
import by.shyrei.rentbike.entity.User;
import by.shyrei.rentbike.exception.DaoException;
import by.shyrei.rentbike.exception.ServiceException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


/**
 * Project RentBike
 * Created on 22.07.2017.
 * author Shyrei Uladzimir
 */
public class OrderService {
    private OrderDao orderDao = new OrderDao();
    private BikeDao bikeDao = new BikeDao();

    public boolean checkBalance(User user) throws ServiceException {
        BigDecimal value = calculateOrder(user);
        return user.getBalance().intValue() >= value.intValue();
    }

    private BigDecimal calculateOrder(User user) throws ServiceException {
        Order order;
        Bike bike;
        BigDecimal value;
        try {
            order = orderDao.findUnclosedOrder(user.getId());
            LocalDateTime startRent = order.getStartRent();
            LocalDateTime endRent = LocalDateTime.now();
            BigDecimal minutes = new BigDecimal(ChronoUnit.MINUTES.between(startRent, endRent));
            bike = bikeDao.findEntityById(order.getBikeId());
            BigDecimal pricePerMinutes = bike.getPricePerHour();
            value = minutes.multiply(pricePerMinutes);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in calculateOrder method", e);
        }
        return value;
    }

    public void closeOrder(User user) throws ServiceException {
        Order order;
        Bike bike;
        try {
            order = orderDao.findUnclosedOrder(user.getId());
            LocalDateTime startRent = order.getStartRent();
            LocalDateTime endRent = LocalDateTime.now();
            BigDecimal minutes = new BigDecimal(ChronoUnit.MINUTES.between(startRent, endRent));
            bike = bikeDao.findEntityById(order.getBikeId());
            BigDecimal pricePerMinutes = bike.getPricePerHour();
            BigDecimal value = minutes.multiply(pricePerMinutes);
            orderDao.closeOrder(user, value, bike);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in closeOrder method", e);
        }
    }

    public Order findEntityById(Integer id) throws ServiceException {
        try {
            return orderDao.findEntityById(id);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findEntityById method", e);
        }
    }

    public ArrayList<Order> findAllUserOrders(Integer userId) throws ServiceException {
        try {
            return orderDao.findAllUserOrders(userId);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findAllUserOrders method", e);
        }
    }

    public ArrayList<Order> findAll() throws ServiceException {
        try {
            return orderDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findAll method", e);
        }
    }

    public ArrayList<Order> findUnclosed() throws ServiceException {
        try {
            return orderDao.findUnclosed();
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findUnclosed method", e);
        }
    }
}
