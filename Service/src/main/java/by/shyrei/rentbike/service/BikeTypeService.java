package by.shyrei.rentbike.service;

import by.shyrei.rentbike.dao.BikeTypeDAO;
import by.shyrei.rentbike.entity.BikeType;
import by.shyrei.rentbike.exception.DaoException;
import by.shyrei.rentbike.exception.ServiceException;

import java.io.InputStream;
import java.util.ArrayList;

/**
 * Project RentBike
 * Created on 28.07.2017.
 * author Shyrei Uladzimir
 */
public class BikeTypeService {
    private BikeTypeDAO bikeTypeDAO = new BikeTypeDAO();

    public ArrayList<BikeType> findAll() throws ServiceException {
        ArrayList<BikeType> typesList;
        try {
            typesList = bikeTypeDAO.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findAll method", e);
        }
        return typesList;
    }

    public void editPrice(BikeType bikeType) throws ServiceException {
        try {
            bikeTypeDAO.updateEntity(bikeType);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in editPrice method", e);
        }
    }

    public void create(BikeType bikeType, InputStream inputStream) throws ServiceException {
        try {
            bikeTypeDAO.create(bikeType, inputStream);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in create method", e);
        }
    }
}
