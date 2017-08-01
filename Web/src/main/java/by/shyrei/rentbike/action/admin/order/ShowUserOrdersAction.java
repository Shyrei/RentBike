package by.shyrei.rentbike.action.admin.order;

import by.shyrei.rentbike.action.Action;
import by.shyrei.rentbike.controller.Router;
import by.shyrei.rentbike.entity.Order;
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
 * Created on 01.08.2017.
 * author Shyrei Uladzimir
 */
public class ShowUserOrdersAction implements Action {
    private final static String USER_ID = "userId";
    private final static String ORDERS_LIST = "ordersList";
    private final static String EMPTY_ORDER = "emptyOrder";
    private final static String MESSAGE = "message";
    private OrderService orderService = new OrderService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        try {
            /*int userId = (int) request.getAttribute(USER_ID);*/
            ArrayList<Order> ordersList = orderService.findAllUserOrders(Integer.parseInt(request.getParameter(USER_ID)));
            if (!ordersList.isEmpty()) {
                request.setAttribute(ORDERS_LIST, ordersList);
            } else {
                request.setAttribute(EMPTY_ORDER, Boolean.TRUE);
            }
            router.setPagePath(PageConstant.USER_ORDER);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
