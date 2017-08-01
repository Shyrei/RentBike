package by.shyrei.rentbike.action.admin.user;

import by.shyrei.rentbike.action.Action;
import by.shyrei.rentbike.controller.Router;
import by.shyrei.rentbike.entity.Role;
import by.shyrei.rentbike.exception.ServiceException;
import by.shyrei.rentbike.service.RoleService;
import by.shyrei.rentbike.util.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project RentBike
 * Created on 31.07.2017.
 * author Shyrei Uladzimir
 */
public class CreateRoleAction implements Action {
    private final static String ROLE = "role";
    private final static String MESSAGE = "message";
    private RoleService roleService = new RoleService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        Role role = new Role();
        try {
            role.setRole(request.getParameter(ROLE));
            roleService.createRole(role);
            router.setPagePath(PageConstant.FIRST_PAGE);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
