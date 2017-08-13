package by.shyrei.rentbike.service;

import by.shyrei.rentbike.dao.BikeDao;
import by.shyrei.rentbike.dao.StationDao;
import by.shyrei.rentbike.entity.Bike;
import by.shyrei.rentbike.exception.DaoException;
import by.shyrei.rentbike.exception.ServiceException;
import by.shyrei.rentbike.util.MailSender;

import java.util.ArrayList;

/**
 * Project RentBike
 * Created on 15.07.2017.
 * author Shyrei Uladzimir
 */
public class BikeService {
    private final static Integer MIN_NUMBER_BIKE_ON_STATION = 5;
    private final static String MAIL_TO = "lis2002_2002@mail.ru";
    private final static String MAIL_SUBJECT = "Warning: RentBike";
    private final static String MAIL_TEXT = "The number of bicycles at the station is less than";

    private BikeDao bikeDao = new BikeDao();

    public ArrayList<Bike> findAll() throws ServiceException {
        ArrayList<Bike> bikesList;
        try {
            bikesList = bikeDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findAll method", e);
        }
        return bikesList;
    }

    public ArrayList<Bike> findAllByPage(int pageCapacity, int pageNumber) throws ServiceException {
        ArrayList<Bike> bikesList;
        try {
            bikesList = bikeDao.findAllByPage(pageCapacity, pageNumber);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in findAllByPage method", e);
        }
        return bikesList;
    }

    public Bike rentBike(Integer bikeId, Integer userId) throws ServiceException {
        Bike bike;
        try {
            bike = bikeDao.rentBike(bikeId, userId);
            Integer stationId = bike.getStationId();
            if (checkNumberOfBikes(stationId)) {
                MailSender.send(MAIL_SUBJECT, MAIL_TEXT, MAIL_TO);
            }
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in rentBike method", e);
        }
        return bike;
    }

    public boolean createBike(Bike bike) throws ServiceException {
        try {
            return bikeDao.createEntity(bike);
        } catch (DaoException e) {
            throw new ServiceException("Transaction failed in createBike method", e);
        }
    }

    private boolean checkNumberOfBikes(Integer stationId) throws DaoException {
        StationDao stationDao = new StationDao();
        int bikesCount = stationDao.checkNumberBikeOnStation(stationId);
        return bikesCount < MIN_NUMBER_BIKE_ON_STATION;
    }
}
