package by.shyrei.rentbike.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Project RentBike
 * Created on 13.07.2017.
 * author Shyrei Uladzimir
 */
public class ConfigurationManager {
    private final static Logger LOGGER = LogManager.getLogger(ConfigurationManager.class);
    private final static String CONFIGURATION = "configuration";

    private ConfigurationManager() {
    }

    public static String getProperty(String key) {
        String value;
        try {
            value = initBundle().getString(key);
        } catch (MissingResourceException e) {
            LOGGER.log(Level.FATAL, e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return value;
    }

    /*
    * Initialize the configuration file
    *
    */
    private static ResourceBundle initBundle() {
        ResourceBundle resourceBundle;
        try {
            resourceBundle = ResourceBundle.getBundle(CONFIGURATION);
        }  catch (MissingResourceException e) {
            LOGGER.log(Level.FATAL, e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
        return resourceBundle;
    }
}
