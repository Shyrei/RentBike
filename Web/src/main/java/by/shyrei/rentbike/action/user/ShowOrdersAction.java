package by.shyrei.rentbike.action.user;

import by.shyrei.rentbike.action.Action;
import by.shyrei.rentbike.controller.Router;
import by.shyrei.rentbike.entity.Order;
import by.shyrei.rentbike.entity.User;
import by.shyrei.rentbike.exception.ServiceException;
import by.shyrei.rentbike.service.OrderService;
import by.shyrei.rentbike.util.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Project RentBike
 * Created on 24.07.2017.
 * author Shyrei Uladzimir
 */
public class ShowOrdersAction implements Action {
    private final static String USER = "user";
    private final static String ORDERS_LIST = "ordersList";
    private final static String EMPTY_ORDER = "emptyOrder";
    private final static String MESSAGE = "message";
    private OrderService orderService = new OrderService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        User user = (User) request.getSession().getAttribute(USER);
        try {
            ArrayList<Order> ordersList = orderService.findAllUserOrders(user.getId());
            if (!ordersList.isEmpty()) {
                request.setAttribute(ORDERS_LIST, ordersList);
            } else {
                request.setAttribute(EMPTY_ORDER, Boolean.TRUE);
            }
            router.setPagePath(PageConstant.USER_ORDERS);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
