package by.shyrei.rentbike.service;

import by.shyrei.rentbike.dao.RoleDao;
import by.shyrei.rentbike.entity.Role;
import by.shyrei.rentbike.entity.User;
import by.shyrei.rentbike.exception.DaoException;
import by.shyrei.rentbike.exception.ServiceException;

import java.util.ArrayList;

/**
 * Project RentBike
 * Created on 25.07.2017.
 * author Shyrei Uladzimir
 */
public class RoleService {
    private RoleDao roleDao = new RoleDao();

    public ArrayList<Role> findAll() throws ServiceException {
        try {
            return roleDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findAll method", e);
        }
    }

    public boolean createRole(Role role) throws ServiceException {
        try {
            return roleDao.createEntity(role);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in createRole method", e);
        }
    }
}
