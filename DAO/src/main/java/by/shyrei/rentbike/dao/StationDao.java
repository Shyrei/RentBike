package by.shyrei.rentbike.dao;

import by.shyrei.rentbike.db.ConnectionPool;
import by.shyrei.rentbike.db.ProxyConnection;
import by.shyrei.rentbike.entity.Station;
import by.shyrei.rentbike.exception.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

/**
 * Project RentBike
 * Created on 25.07.2017.
 * author Shyrei Uladzimir
 */
public class StationDao extends AbstractDao<Station> {

    private final static String CHECK_BIKES_ON_STATION = "SELECT COUNT(*) FROM bikes WHERE Stations_Id = ? AND In_Rent = 0;";

    public Integer checkNumberBikeOnStation(Integer stationId) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        int bikesCount = 0;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(CHECK_BIKES_ON_STATION);
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

    @Override
    public Collection<Station> findAll() throws DaoException {
        return null;
    }

    @Override
    public Station findEntityById(Integer id) throws DaoException {
        return null;
    }

    @Override
    public boolean deleteEntity(Station entity) {
        return false;
    }

    @Override
    public boolean createEntity(Station entity) throws DaoException {
        return false;
    }

    @Override
    public Station updateEntity(Station entity) {
        return null;
    }
}
