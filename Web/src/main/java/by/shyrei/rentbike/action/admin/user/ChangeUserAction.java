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
 * Created on 25.07.2017.
 * author Shyrei Uladzimir
 */
public class ChangeUserAction implements Action {
    private final static String USER_ID = "userId";
    private final static String ROLE_ID = "roleId";
    private final static String MESSAGE = "message";
    private UserService userService = new UserService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        User user = new User();
        try {
            user.setId(Integer.parseInt(request.getParameter(USER_ID)));
            user.setRoleId(Integer.parseInt(request.getParameter(ROLE_ID)));
            userService.updateUser(user);
            router.setPagePath(PageConstant.FIRST_PAGE);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
