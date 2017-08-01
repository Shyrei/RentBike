package by.shyrei.rentbike.listener;

import by.shyrei.rentbike.db.ConnectionPool;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


/**
 * Project RentBike
 * Created on 24.07.2017.
 * author Shyrei Uladzimir
 */
@WebListener
public class ServletContextListenerImpl implements ServletContextListener {


    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        ConnectionPool.getInstance().closeConnectionPool();
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {

    }
}
