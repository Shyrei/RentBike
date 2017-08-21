package by.shyrei.rentbike.util;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Project RentBike
 * Created on 16.08.2017.
 * author Shyrei Uladzimir
 */
public class EmailManager {
    private final static Logger LOGGER = LogManager.getLogger(EmailManager.class);
    private final static String EMAIL = "email";

    private EmailManager() {
    }

    public static String getProperty(String key){
        String value;
        try {
            value = initBundle().getString(key);
        }catch (MissingResourceException e) {
            LOGGER.log(Level.FATAL, e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return value;
    }

    private static ResourceBundle initBundle(){
        ResourceBundle resourceBundle;
        try {
            resourceBundle = ResourceBundle.getBundle(EMAIL);
        }  catch (MissingResourceException e) {
            LOGGER.log(Level.FATAL, e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return resourceBundle;
    }
}
