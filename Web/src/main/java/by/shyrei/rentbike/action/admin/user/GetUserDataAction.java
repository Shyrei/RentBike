package by.shyrei.rentbike.action.admin.user;

import by.shyrei.rentbike.action.Action;
import by.shyrei.rentbike.controller.Router;
import by.shyrei.rentbike.entity.Role;
import by.shyrei.rentbike.entity.User;
import by.shyrei.rentbike.exception.ServiceException;
import by.shyrei.rentbike.service.RoleService;
import by.shyrei.rentbike.service.UserService;
import by.shyrei.rentbike.util.PageConstant;

import javax.accessibility.Accessible;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Project RentBike
 * Created on 25.07.2017.
 * author Shyrei Uladzimir
 */
public class GetUserDataAction implements Action {
    private final static String USERS_LIST = "usersList";
    private final static String ROLES_LIST = "rolesList";
    private final static String MESSAGE = "message";
    private UserService userService = new UserService();
    private RoleService roleService = new RoleService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        ArrayList<User> usersList;
        ArrayList<Role> rolesList;
        try {
            usersList = userService.findAll();
            rolesList = roleService.findAll();
            request.setAttribute(USERS_LIST, usersList);
            request.setAttribute(ROLES_LIST, rolesList);
            router.setPagePath(PageConstant.UPDATE_USER);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
