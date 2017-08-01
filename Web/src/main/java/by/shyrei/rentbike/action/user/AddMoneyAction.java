package by.shyrei.rentbike.action.user;

import by.shyrei.rentbike.action.Action;
import by.shyrei.rentbike.controller.Router;
import by.shyrei.rentbike.entity.User;
import by.shyrei.rentbike.exception.ServiceException;
import by.shyrei.rentbike.service.UserService;
import by.shyrei.rentbike.util.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Project RentBike
 * Created on 24.07.2017.
 * author Shyrei Uladzimir
 */
public class AddMoneyAction implements Action {
    private final static String USER_BALANCE = "balance";
    private final static String USER = "user";
    private final static String MESSAGE = "message";
    private UserService userService = new UserService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       Router router = new Router();
       User user = (User) request.getSession().getAttribute(USER);
       BigDecimal balance = new BigDecimal(request.getParameter(USER_BALANCE));
        try {
            user = userService.updateBalance(user, balance);
            request.getSession().setAttribute(USER, user);
            router.setPagePath(PageConstant.FIRST_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
