package by.shyrei.rentbike.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import java.util.Enumeration;

/**
 * Project RentBike
 * Created on 24.07.2017.
 * author Shyrei Uladzimir
 */
@WebListener
public class HttpSessionAttributeListenerImpl implements HttpSessionAttributeListener {

    private final static String INVALIDATE = "invalidate";

    /*
    * Invalidate session when added special attribute
    *
    */
    @Override
    public void attributeAdded(HttpSessionBindingEvent httpSessionBindingEvent) {
        HttpSession session = httpSessionBindingEvent.getSession();
        Enumeration<String> list = session.getAttributeNames();
        while (list.hasMoreElements()) {
            if (list.nextElement().equals(INVALIDATE)) {
                httpSessionBindingEvent.getSession().invalidate();
            }
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent httpSessionBindingEvent) {

    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent httpSessionBindingEvent) {

    }
}
