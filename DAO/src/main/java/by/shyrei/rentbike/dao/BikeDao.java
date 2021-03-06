package by.shyrei.rentbike.dao;

import by.shyrei.rentbike.db.ConnectionPool;
import by.shyrei.rentbike.db.ProxyConnection;
import by.shyrei.rentbike.entity.Bike;
import by.shyrei.rentbike.entity.User;
import by.shyrei.rentbike.exception.DaoException;

import java.sql.*;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.util.*;

/**
 * Project RentBike
 * Created on 15.07.2017.
 * author Shyrei Uladzimir
 */
public class BikeDao extends AbstractDao<Bike> {
    private final static String SQL_FIND_ALL_BIKES = "SELECT bikes.Id , types.Type, types.Price_Per_Hour, stations.City, stations.Location, types.Image, bikes.Stations_Id, bikes.Is_Available, bikes.In_Rent FROM bikes JOIN types ON bikes.Types_Id = types.Id JOIN stations ON bikes.Stations_Id = stations.Id WHERE bikes.In_Rent = '0' AND bikes.Is_Available = '1' ORDER BY bikes.Id;";
    private final static String SQL_FIND_BY_PAGE = "SELECT bikes.Id , types.Type, types.Price_Per_Hour, stations.City, stations.Location, types.Image, bikes.Stations_Id, bikes.Is_Available, bikes.In_Rent FROM bikes JOIN types ON bikes.Types_Id = types.Id JOIN stations ON bikes.Stations_Id = stations.Id WHERE bikes.In_Rent = '0' AND bikes.Is_Available = '1' ORDER BY bikes.Id LIMIT ? OFFSET ?;";
    private final static String SQL_FIND_BY_ID = "SELECT bikes.Id , types.Type, types.Price_Per_Hour, stations.City, stations.Location, types.Image, bikes.Stations_Id, bikes.Is_Available, bikes.In_Rent FROM bikes JOIN types ON bikes.Types_Id = types.Id JOIN stations ON bikes.Stations_Id = stations.Id WHERE bikes.Id = ?;";
    private final static String SQL_FIND_ALL_ON_STATION = "SELECT bikes.Id, types.Type, types.Price_Per_Hour, stations.City, stations.Location, types.Image, bikes.Stations_Id, bikes.Is_Available, bikes.In_Rent FROM bikes JOIN types ON bikes.Types_Id = types.Id JOIN stations ON bikes.Stations_Id = stations.Id WHERE bikes.In_Rent = '0' AND bikes.Is_Available = '1' AND bikes.Stations_Id = ? ORDER BY bikes.Id;";
    private final static String SQL_RENT_TRUE = "UPDATE bikes SET In_Rent = '1' WHERE id = ?;";
    private final static String SQL_ADD_ORDER = "INSERT INTO orders (Start_Date, Users_Id, Bikes_Id) VALUES (now(), ?, ?);";
    private final static String SQL_ADD_ORDER_WITH_DISCOUNT = "INSERT INTO orders (Start_Date, Users_Id, Bikes_Id, Discount) VALUES (now(), ?, ?, ?);";
    private final static String SQL_SET_USER_ROLE_HAS_ORDER = "UPDATE users SET Roles_Id=3 WHERE Id=?;";
    private final static String SQL_CREATE_BIKE = "INSERT INTO bikes (Types_Id, Stations_Id) VALUES (?, ?);";
    private final static String SQL_CHANGE_BIKE_STATUS = "UPDATE bikes SET Is_Available=? WHERE Id=?;";
    private final static double DISCOUNT = 0.7;

    /*
    * A method that returns a list of all bikes in the database
    *
    */
    @Override
    public ArrayList<Bike> findAll() throws DaoException {
        ArrayList<Bike> bikesList = new ArrayList<>();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_BIKES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bike bike = buildBike(resultSet);
                bikesList.add(bike);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAll method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return bikesList;
    }

    /*
    * The method that searches for a bike on Id
    *
    */
    @Override
    public Bike findEntityById(Integer id) throws DaoException {
        Bike bike = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bike = buildBike(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findEntityByID method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return bike;
    }

    /*
    *Add a new bike to the station
    *
    */
    @Override
    public boolean createEntity(Bike entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        boolean isCreate;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_CREATE_BIKE);
            preparedStatement.setInt(1, entity.getTypeId());
            preparedStatement.setInt(2, entity.getStationId());
            preparedStatement.executeUpdate();
            isCreate = true;
        } catch (SQLException e) {
            throw new DaoException("Error in createEntity method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return isCreate;
    }

    /*
    * A method that returns a list of bikes for page-by-page rendering
    * @param pageCapacity - number of returned bicycles (Limit in the SQL)
    * @param pageNumber - number of displayed page
    */
    public ArrayList<Bike> findAllByPage(int pageCapacity, int pageNumber) throws DaoException {
        ArrayList<Bike> bikesList = new ArrayList<>();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_PAGE);
            preparedStatement.setInt(1, pageCapacity);
            preparedStatement.setInt(2, (pageNumber * pageCapacity - pageCapacity));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bike bike = buildBike(resultSet);
                bikesList.add(bike);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAllByPage method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return bikesList;
    }

    /*
    * Return list of bikes on concrete station
    *
    */
    public ArrayList<Bike> findAllOnStation(int stationId) throws DaoException {
        ArrayList<Bike> bikesList = new ArrayList<>();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_ON_STATION);
            preparedStatement.setInt(1, stationId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Bike bike = buildBike(resultSet);
                bikesList.add(bike);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAllOnStation method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return bikesList;
    }

    /*
    * The method that rents a bike
    * Change the bike field inRent = True
    * Changes the user's field Role = hasRent
    * Creates a new record in the table orders
    * In the event of a failure, the transaction rolls back
    */
    public Bike rentBike(Integer bikeId, User user) throws DaoException {
        Bike bike = null;
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            connection.setAutoCommit(false);
            DatabaseMetaData metaData = connection.getMetaData();
            if (metaData.supportsTransactionIsolationLevel(Connection.TRANSACTION_SERIALIZABLE)) {
                connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            }
            preparedStatement = connection.prepareStatement(SQL_FIND_BY_ID);
            preparedStatement.setInt(1, bikeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                bike = buildBike(resultSet);
                bike.setInRent(true);
                preparedStatement = connection.prepareStatement(SQL_RENT_TRUE);
                preparedStatement.setInt(1, bike.getId());
                preparedStatement.executeUpdate();
                if (user.getRoleId() == 1) {
                    preparedStatement = connection.prepareStatement(SQL_ADD_ORDER_WITH_DISCOUNT);
                    preparedStatement.setDouble(3, DISCOUNT);
                } else {
                    preparedStatement = connection.prepareStatement(SQL_ADD_ORDER);
                }
                preparedStatement.setInt(1, user.getId());
                preparedStatement.setInt(2, bike.getId());
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement(SQL_SET_USER_ROLE_HAS_ORDER);
                preparedStatement.setInt(1, user.getId());
                preparedStatement.executeUpdate();
                connection.commit();
                connection.setAutoCommit(true);
                connection.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException e1) {
                throw new DaoException("Error in rentBike method", e);
            }
            throw new DaoException("Error in rentBike method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return bike;
    }

    /*
    * Change the bike field isAvailable = False
    *
    */
    public void changeEntity(Bike bike) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_CHANGE_BIKE_STATUS);
            preparedStatement.setBoolean(1, bike.isAvailable());
            preparedStatement.setInt(2, bike.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in changeEntity method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    /*
    * An internal method for constructing a bike from ResultSet
    *
    */
    private Bike buildBike(ResultSet resultSet) throws SQLException {
        Bike bike = new Bike();
        bike.setId(resultSet.getInt(1));
        bike.setType(resultSet.getString(2));
        bike.setPricePerHour(resultSet.getBigDecimal(3));
        bike.setCity(resultSet.getString(4));
        bike.setLocation(resultSet.getString(5));
        Blob photo = resultSet.getBlob(6);
        bike.setStationId(resultSet.getInt(7));
        if (photo != null) {
            byte[] image = photo.getBytes(1, (int) photo.length());
            String pic = Base64.getEncoder().encodeToString(image);
            bike.setPicture(pic);
        }
        bike.setAvailable(resultSet.getBoolean(8));
        bike.setInRent(resultSet.getBoolean(9));
        return bike;
    }
}
