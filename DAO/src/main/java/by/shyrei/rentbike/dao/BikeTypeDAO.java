package by.shyrei.rentbike.dao;

import by.shyrei.rentbike.db.ConnectionPool;
import by.shyrei.rentbike.db.ProxyConnection;
import by.shyrei.rentbike.entity.BikeType;
import by.shyrei.rentbike.exception.DaoException;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;


/**
 * Project RentBike
 * Created on 28.07.2017.
 * author Shyrei Uladzimir
 */
public class BikeTypeDAO extends AbstractDao<BikeType> {
    private final static String SQL_FIND_ALL_TYPE = "SELECT * FROM types;";
    private final static String SQL_EDIT_PRICE = "UPDATE types SET Price_Per_Hour=? WHERE Id=?;";
    private final static String SQL_CREATE = "INSERT INTO types (Type, Price_per_Hour, Image) VALUES(?,?,?);";


    @Override
    public ArrayList<BikeType> findAll() throws DaoException {
        ArrayList<BikeType> typesList = new ArrayList<>();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_TYPE);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                BikeType bikeType = new BikeType();
                bikeType.setId(resultSet.getInt(1));
                bikeType.setType(resultSet.getString(2));
                bikeType.setPrice(resultSet.getBigDecimal(3));
                Blob photo = resultSet.getBlob(4);
                byte[] image = photo.getBytes(1, (int) photo.length());
                String pic = Base64.getEncoder().encodeToString(image);
                bikeType.setImage(pic);
                typesList.add(bikeType);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAll method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return typesList;
    }

    public BikeType create(BikeType entity, InputStream image) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_CREATE);
            preparedStatement.setString(1, entity.getType());
            preparedStatement.setBigDecimal(2, entity.getPrice());
            preparedStatement.setBlob(3, image);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in create method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return entity;
    }

    @Override
    public BikeType updateEntity(BikeType entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_EDIT_PRICE);
            preparedStatement.setBigDecimal(1, entity.getPrice());
            preparedStatement.setInt(2, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Error in updateEntity method", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return entity;
    }


    @Override
    public BikeType findEntityById(Integer id) throws DaoException {
        return null;
    }

    @Override
    public boolean deleteEntity(BikeType entity) {
        return false;
    }

    @Override
    public boolean createEntity(BikeType entity) throws DaoException {
        return false;
    }


}
