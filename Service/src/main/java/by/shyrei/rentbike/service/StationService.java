package by.shyrei.rentbike.service;

import by.shyrei.rentbike.dao.StationDao;
import by.shyrei.rentbike.entity.Station;
import by.shyrei.rentbike.exception.DaoException;
import by.shyrei.rentbike.exception.ServiceException;

import java.util.ArrayList;

/**
 * Project RentBike
 * Created on 03.08.2017.
 * author Shyrei Uladzimir
 */
public class StationService {

    private StationDao stationDao = new StationDao();

    public ArrayList<Station> findAll() throws ServiceException {
        try {
            return stationDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findAll method", e);
        }
    }

    public void createStation(Station station) throws ServiceException {
        try {
            stationDao.createEntity(station);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in createStation method", e);
        }
    }
}
