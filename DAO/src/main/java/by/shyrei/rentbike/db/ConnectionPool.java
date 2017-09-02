package by.shyrei.rentbike.db;

import by.shyrei.rentbike.util.ConfigurationManager;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Project RentBike
 * Created on 13.07.2017.
 * author Shyrei Uladzimir
 *
 * This class is used to create a connection pool
 * The object of this class exists in a single instance
 */
public class ConnectionPool {
    private final static Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
    private final static AtomicBoolean CREATE_INSTANCE = new AtomicBoolean(false);
    private static ReentrantLock lock = new ReentrantLock();
    private static ConnectionPool instance;
    private BlockingQueue<ProxyConnection> queue;

    /*
    * Register driver manager
    * Create connection pool with the required size
    *
    */
    private ConnectionPool() {
        String url = ConfigurationManager.getProperty("dburl");
        String user = ConfigurationManager.getProperty("dbuser");
        String password = ConfigurationManager.getProperty("dbpassword");
        int maxConnections = Integer.parseInt(ConfigurationManager.getProperty("dbmaxconnections"));
        int maxIdle = Integer.parseInt(ConfigurationManager.getProperty("dbmaxIdle"));
        queue = new ArrayBlockingQueue<>(maxConnections);
        try {
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            //Trying for the first time
            for (int i = 0; i < maxConnections; i++) {
                ProxyConnection connection = new ProxyConnection(DriverManager.getConnection(url, user, password));
                queue.put(connection);
            }
            // if you did not create the right amount try again
            if (queue.size() < maxConnections) {
                for (int i = queue.size(); i < maxConnections; i++) {
                    ProxyConnection connection = new ProxyConnection(DriverManager.getConnection(url, user, password));
                    queue.put(connection);
                }
            }
            // if less than the allowable program throw Exception
            if (queue.size() < maxIdle) {
                throw new RuntimeException("Error, when create connection pool");
            }

        } catch (InterruptedException e) {
            LOGGER.log(Level.FATAL, e.getMessage());
        } catch (SQLException e) {
            LOGGER.log(Level.FATAL, e.getMessage());
            throw new RuntimeException("Can`t register sql driver", e);
        }
    }

    /*
    * Create instance ConnectionPool
    *
    */
    public static ConnectionPool getInstance() {
        if (!CREATE_INSTANCE.get()) {
            lock.lock();
            try {
                if (instance == null) {
                    instance = new ConnectionPool();
                    CREATE_INSTANCE.set(true);
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    /*
    * Take connection from ConnectionPool
    */
    public ProxyConnection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = queue.take();
        } catch (InterruptedException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
        return connection;
    }

    /*
    * Return connection to ConnectionPool
    *
    */
    void releaseConnection(ProxyConnection connection) {
        queue.add(connection);
    }

    /*
    * Deregister all driver manager drivers
    * Close all connection with DB
    */
    public void closeConnectionPool() {
        for (ProxyConnection connection : queue) {
            try {
                connection = queue.take();
                connection.connectionClose();
            } catch (SQLException | InterruptedException e) {
                LOGGER.log(Level.ERROR, e.getMessage());
            }
        }
        try {
            Enumeration<Driver> drivers = DriverManager.getDrivers();
            while (drivers.hasMoreElements()) {
                Driver driver = drivers.nextElement();
                DriverManager.deregisterDriver(driver);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
        }
    }

    public int getSize() {
        return queue.size();
    }
}
