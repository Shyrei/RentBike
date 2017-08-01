package by.shyrei.rentbike.action.bike;

import by.shyrei.rentbike.action.Action;
import by.shyrei.rentbike.controller.Router;
import by.shyrei.rentbike.entity.Order;
import by.shyrei.rentbike.entity.User;
import by.shyrei.rentbike.exception.ServiceException;
import by.shyrei.rentbike.service.OrderService;
import by.shyrei.rentbike.service.UserService;
import by.shyrei.rentbike.util.PageConstant;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project RentBike
 * Created on 22.07.2017.
 * author Shyrei Uladzimir
 */
public class ReturnBikeAction implements Action {
    private final static String USER = "user";
    private final static String ORDER = "order";
    private final static String MESSAGE = "message";
    private OrderService orderService = new OrderService();
    private UserService userService = new UserService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        try {
            User user = (User) request.getSession().getAttribute(USER);
            if (orderService.checkBalance(user)) {
                orderService.closeOrder(user);
                User updateUser = userService.findUserById(user.getId());
                Order order = orderService.findEntityById(user.getId());
                request.getSession().setAttribute(ORDER, order);
                request.getSession().setAttribute(USER, updateUser);
                router.setPagePath(PageConstant.RETURN_BIKE);
                router.setRoute(Router.RouteType.REDIRECT);
            } else {
                router.setPagePath(PageConstant.ADD_MONEY);
                router.setRoute(Router.RouteType.FORWARD);
            }
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
