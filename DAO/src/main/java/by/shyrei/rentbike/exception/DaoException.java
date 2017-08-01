package by.shyrei.rentbike.exception;

/**
 * Project RentBike
 * Created on 13.07.2017.
 * author Shyrei Uladzimir
 */
public class DaoException extends Exception {
    public DaoException() {
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
