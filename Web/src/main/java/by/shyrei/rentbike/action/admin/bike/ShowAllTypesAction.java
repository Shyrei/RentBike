package by.shyrei.rentbike.action.admin.bike;

import by.shyrei.rentbike.action.Action;
import by.shyrei.rentbike.controller.Router;
import by.shyrei.rentbike.entity.BikeType;
import by.shyrei.rentbike.exception.ServiceException;
import by.shyrei.rentbike.service.BikeTypeService;
import by.shyrei.rentbike.util.PageConstant;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Project RentBike
 * Created on 28.07.2017.
 * author Shyrei Uladzimir
 */
public class ShowAllTypesAction implements Action {
    private final static String TYPES_LIST = "typesList";
    private final static String MESSAGE = "message";
    private BikeTypeService bikeTypeService = new BikeTypeService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        ArrayList<BikeType> typesList;
        try {
            typesList = bikeTypeService.findAll();
            request.setAttribute(TYPES_LIST, typesList);
            router.setPagePath(PageConstant.ALL_TYPES);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
