package by.shyrei.rentbike.db;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Project RentBike
 * Created on 16.08.2017.
 * author Shyrei Uladzimir
 */
public class ConnectionPoolTest {

    @Test
    public void getInstanceBySize() throws Exception {
        int expectedSize = 10;
        int actualSize = ConnectionPool.getInstance().getSize();
        assertEquals(expectedSize, actualSize);
    }

    @Test
    public void getInstance() throws Exception {
        assertNotNull(ConnectionPool.getInstance());
    }

    @Test
    public void getConnection() throws Exception {
        ProxyConnection connection = ConnectionPool.getInstance().getConnection();
        assertNotNull(connection);
        connection.close();
    }
}