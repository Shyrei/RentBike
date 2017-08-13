package by.shyrei.rentbike.action.admin;

import by.shyrei.rentbike.action.Action;
import by.shyrei.rentbike.controller.Router;
import by.shyrei.rentbike.entity.Bike;
import by.shyrei.rentbike.entity.Order;
import by.shyrei.rentbike.entity.Station;
import by.shyrei.rentbike.entity.User;
import by.shyrei.rentbike.exception.ServiceException;
import by.shyrei.rentbike.service.BikeService;
import by.shyrei.rentbike.service.OrderService;
import by.shyrei.rentbike.service.StationService;
import by.shyrei.rentbike.service.UserService;
import by.shyrei.rentbike.util.PageConstant;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Project RentBike
 * Created on 27.07.2017.
 * author Shyrei Uladzimir
 */
public class ShowAdminPageAction implements Action {
    private final static String USERS_LIST = "usersList";
    private final static String BIKES_LIST = "bikesList";
    private final static String ORDERS_LIST = "ordersList";
    private final static String STATIONS_LIST = "stationsList";
    private final static String MESSAGE = "message";
    private UserService userService = new UserService();
    private BikeService bikeService = new BikeService();
    private OrderService orderService = new OrderService();
    private StationService stationService = new StationService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        ArrayList<User> usersList;
        ArrayList<Bike> bikesList;
        ArrayList<Order> ordersList;
        ArrayList<Station> stationsList;
        try {
            usersList = userService.findAll();
            bikesList = bikeService.findAll();
            ordersList = orderService.findAll();
            stationsList = stationService.findAll();
            request.setAttribute(USERS_LIST, usersList);
            request.setAttribute(BIKES_LIST, bikesList);
            request.setAttribute(ORDERS_LIST, ordersList);
            request.setAttribute(STATIONS_LIST, stationsList);
            router.setPagePath(PageConstant.ADMIN_PAGE);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
