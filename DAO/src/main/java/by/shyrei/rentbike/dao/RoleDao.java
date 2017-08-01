package by.shyrei.rentbike.dao;

import by.shyrei.rentbike.db.ConnectionPool;
import by.shyrei.rentbike.db.ProxyConnection;
import by.shyrei.rentbike.entity.Role;
import by.shyrei.rentbike.entity.User;
import by.shyrei.rentbike.exception.DaoException;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Project RentBike
 * Created on 25.07.2017.
 * author Shyrei Uladzimir
 */
public class RoleDao extends AbstractDao<Role> {
    private final static String SQL_FIND_ALL_ROLES = "SELECT * FROM roles";
    private final static String SQL_CREATE_ROLE = "INSERT INTO roles (Role) VALUES (?);";

    @Override
    public ArrayList<Role> findAll() throws DaoException {
        ArrayList<Role> rolesList = new ArrayList<>();
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_FIND_ALL_ROLES);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Role role = new Role();
                role.setId(resultSet.getInt(1));
                role.setRole(resultSet.getString(2));
                rolesList.add(role);
            }
        } catch (SQLException e) {
            throw new DaoException("Error in findAll", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return rolesList;
    }

    @Override
    public boolean createEntity(Role entity) throws DaoException {
        ProxyConnection connection = null;
        PreparedStatement preparedStatement = null;
        boolean isCreate;
        try {
            connection = ConnectionPool.getInstance().getConnection();
            preparedStatement = connection.prepareStatement(SQL_CREATE_ROLE);
            preparedStatement.setString(1, entity.getRole());
            preparedStatement.executeUpdate();
            isCreate = true;
        } catch (SQLException e) {
            throw new DaoException("Error in createEntity", e);
        } finally {
            close(preparedStatement);
            close(connection);
        }
        return isCreate;
    }

    @Override
    public Role findEntityById(Integer id) throws DaoException {
        return null;
    }

    @Override
    public boolean deleteEntity(Role entity) {
        return false;
    }


    @Override
    public Role updateEntity(Role entity) {
        return null;
    }
}
