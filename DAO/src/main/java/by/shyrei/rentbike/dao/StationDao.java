package by.shyrei.rentbike.dao;

import by.shyrei.rentbike.db.ConnectionPool;
import by.shyrei.rentbike.db.ProxyConnection;
import by.shyrei.rentbike.entity.Station;
import by.shyrei.rentbike.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Project RentBike
 * Created on 25.07.2017.
 * author Shyrei Uladzimir
 */
public class StationDao extends AbstractDao<Station> {

    private final static String SQL_CHECK_BIKES_ON_STATION = "SELECT COUNT(*) FROM bikes WHERE Stations_Id = ? AND In_Rent = 0;";
    private final static String SQL_FIND_ALL_STATIONS = "SELECT * FROM stations;";
    private final static String SQL_CREATE_STATION = "INSERT INTO stations (City, Location) VALUES (?, ?);";
    private final static String SQL_FIND_STATION_BY_ID = "SELECT * FROM stations WHERE id = ?;";

    /*
    * This method return a list of all stations
    *
    */
    @Override
    public ArrayList<Station> findAll() throws DaoException {
        ArrayList<Station> stationsList = new ArrayList<>();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_STATIONS);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Station station = new Station();
                station.setId(resultSet.getInt(1));
                station.setCity(resultSet.getString(2));
                station.setLocation(resultSet.getString(3));
                stationsList.add(station);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAll method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return stationsList;
    }

    /*
    * This method return station
    *
    */
    @Override
    public Station findEntityById(Integer id) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        Station station = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_STATION_BY_ID);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                station = new Station();
                station.setId(resultSet.getInt(1));
                station.setCity(resultSet.getString(2));
                station.setLocation(resultSet.getString(3));
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findEntityById method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return station;
    }

    /*
    * Create new station
    *
    */
    @Override
    public boolean createEntity(Station entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        boolean isCreate;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_CREATE_STATION);
            preparedStatement.setString(1, entity.getCity());
            preparedStatement.setString(2, entity.getLocation());
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
    * This method return numbers bikes on concrete station
    * Use this method to send email if numbers bikes on station < Min
    */
    public Integer checkNumberBikeOnStation(Integer stationId) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        int bikesCount = 0;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_CHECK_BIKES_ON_STATION);
            preparedStatement.setInt(1, stationId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bikesCount = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in checkNumberBikeOnStation method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return bikesCount;
    }
}
