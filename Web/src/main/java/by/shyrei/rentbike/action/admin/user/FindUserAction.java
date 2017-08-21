package by.shyrei.rentbike.action.admin.user;

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

/**
 * Project RentBike
 * Created on 28.07.2017.
 * author Shyrei Uladzimir
 */
public class FindUserAction implements Action {
    private final static String USER_ID = "userId";
    private final static String USER = "user";
    private final static String EMPTY_USER = "emptyUser";
    private final static String MESSAGE = "message";
    private UserService userService = new UserService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        try {
            int userId = Integer.parseInt(request.getParameter(USER_ID));
            User user = userService.findUserById(userId);
            if (user != null){
                request.setAttribute(USER, user);
            } else {
                request.setAttribute(EMPTY_USER, Boolean.TRUE);
            }
            router.setPagePath(PageConstant.ONE_USER);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
