package by.shyrei.rentbike.dao;

import by.shyrei.rentbike.db.ConnectionPool;
import by.shyrei.rentbike.db.ProxyConnection;
import by.shyrei.rentbike.entity.Bike;
import by.shyrei.rentbike.entity.Order;
import by.shyrei.rentbike.entity.User;
import by.shyrei.rentbike.exception.DaoException;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

/**
 * Project RentBike
 * Created on 22.07.2017.
 * author Shyrei Uladzimir
 */
public class OrderDao extends AbstractDao<Order> {

    private final static String SQL_FIND_ORDER = "SELECT * FROM orders WHERE Users_Id=? ORDER BY id DESC LIMIT 1;";
    private final static String SQL_FIND_ALL_ORDERS = "SELECT * FROM orders;";
    private final static String SQL_FIND_ALL_ORDERS_BY_PAGE = "SELECT * FROM orders ORDER BY orders.Id LIMIT ? OFFSET ?;";
    private final static String SQL_FIND_UNCLOSED_ORDERS = "SELECT * FROM orders WHERE End_Date IS NULL;";
    private final static String SQL_FIND_UNCLOSED_USER_ORDER = "SELECT * FROM orders WHERE Users_Id=? AND End_Date IS NULL;";
    private final static String SQL_FIND_ALL_USER_ORDERS = "SELECT * FROM orders WHERE Users_Id=?;";
    private final static String SQL_CLOSE_ORDER = "UPDATE orders SET End_Date=now(), Value=? WHERE Users_Id=? AND End_Date IS NULL;";
    private final static String SQL_UPDATE_USER = "UPDATE users SET Balance=?, Roles_Id=2 WHERE Id=?;";
    private final static String SQL_UPDATE_VIP_USER = "UPDATE users SET Balance=?, Roles_Id=1 WHERE Id=?;";
    private final static String SQL_UPDATE_BIKE = "UPDATE bikes SET In_Rent=0 WHERE Id=?;";

    /*
    * This method return a list of all orders
    *
    */
    @Override
    public ArrayList<Order> findAll() throws DaoException {
        ArrayList<Order> ordersList = new ArrayList<>();
        Order order;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_ORDERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order = buildOrder(resultSet);
                ordersList.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAll method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return ordersList;
    }

    /*
    * Return user order
    *
    */
    @Override
    public Order findEntityById(Integer userId) throws DaoException {
        Order order = new Order();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ORDER);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order = buildOrder(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findEntityById method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return order;
    }

    /*
    * This method is implemented in the class: by.shyrei.rentbike.dao.BikeDao
    * See the method: public Bike rentBike(Integer bikeId, User user) throws DaoException
    */
    @Override
    public boolean createEntity(Order entity) throws DaoException {
        return false;
    }

    /*
    * Return unclosed user order
    *
    */
    public Order findUnclosedOrder(Integer userId) throws DaoException {
        Order order = new Order();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_UNCLOSED_USER_ORDER);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order.setId(resultSet.getInt(1));
                order.setStartRent(resultSet.getTimestamp(2).toLocalDateTime());
                order.setDiscount(resultSet.getBigDecimal(5));
                order.setUserId(resultSet.getInt(6));
                order.setBikeId(resultSet.getInt(7));
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findUnclosedOrder method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return order;
    }

    /*
    * A method that returns a list of all user orders
    *
    */
    public ArrayList<Order> findAllUserOrders(Integer userId) throws DaoException {
        ArrayList<Order> ordersList = new ArrayList<>();
        Order order;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_USER_ORDERS);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order = buildOrder(resultSet);
                ordersList.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAllUserOrders method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return ordersList;
    }

    /*
    * A method that returns a list of all unclosed orders
    *
    */
    public ArrayList<Order> findUnclosed() throws DaoException {
        ArrayList<Order> ordersList = new ArrayList<>();
        Order order;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_UNCLOSED_ORDERS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                order = buildOrder(resultSet);
                ordersList.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findUnclosed method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return ordersList;
    }

    /*
    * This method close order
    * Change the bike field inRent = false
    * Changes the user's field Role = has not rent
    * In the event of a failure, the transaction rolls back
    */
    public void closeOrder(User user, BigDecimal value, Bike bike, Order order) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            DatabaseMetaData metaData = connection.getMetaData();
            if (metaData.supportsTransactionIsolationLevel(Connection.TRANSACTION_SERIALIZABLE)) {
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            }
            preparedStatement = connection.prepareStatement(SQL_CLOSE_ORDER);
            preparedStatement.setBigDecimal(1, value);
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
            if(order.getDiscount().intValue() == 1) {
                preparedStatement = connection.prepareStatement(SQL_UPDATE_USER);
            } else {
                preparedStatement = connection.prepareStatement(SQL_UPDATE_VIP_USER);
            }
            preparedStatement.setBigDecimal(1, user.getBalance().subtract(value));
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
            preparedStatement = connection.prepareStatement(SQL_UPDATE_BIKE);
            preparedStatement.setInt(1, bike.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException("Error in closeOrder method", e);
            }
            throw new DaoException("Error in closeOrder method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }
    /*
    * A method that returns a list of orders for page-by-page rendering
    * @param pageCapacity - number of returned bicycles (Limit in the SQL)
    * @param pageNumber - number of displayed page (Offset in the SQL)
    *
    */
    public ArrayList<Order> findAllByPage(int pageCapacity, int pageNumber) throws DaoException {
        ArrayList<Order> ordersList = new ArrayList<>();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_ORDERS_BY_PAGE);
            preparedStatement.setInt(1, pageCapacity);
            preparedStatement.setInt(2, (pageNumber * pageCapacity - pageCapacity));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Order order = buildOrder(resultSet);
                ordersList.add(order);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAllByPage method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return ordersList;
    }

    /*
    * An internal method for constructing a order from ResultSet
    *
    */
    private Order buildOrder(ResultSet resultSet) throws SQLException {
        Order order = new Order();
        order.setId(resultSet.getInt(1));
        order.setStartRent(resultSet.getTimestamp(2).toLocalDateTime());
        if (!(resultSet.getTimestamp(3) == null)) {
            order.setEndRent(resultSet.getTimestamp(3).toLocalDateTime());
        }
        order.setValue(resultSet.getBigDecimal(4));
        order.setDiscount(resultSet.getBigDecimal(5));
        order.setUserId(resultSet.getInt(6));
        order.setBikeId(resultSet.getInt(7));
        return order;
    }


}
