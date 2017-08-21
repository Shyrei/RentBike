package by.shyrei.rentbike.action.admin.bike;

import by.shyrei.rentbike.action.Action;
import by.shyrei.rentbike.controller.Router;
import by.shyrei.rentbike.exception.ServiceException;
import by.shyrei.rentbike.service.BikeService;
import by.shyrei.rentbike.util.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project RentBike
 * Created on 21.08.2017.
 * author Shyrei Uladzimir
 */
public class ChangeBikeStatusAction implements Action {
    private final static String BIKE_ID = "bikeId";
    private final static String MESSAGE = "message";
    private BikeService bikeService = new BikeService();
    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        try {
            int bikeId = (int) request.getSession().getAttribute(BIKE_ID);
            bikeService.changeBike(bikeId);
            request.getSession().removeAttribute(BIKE_ID);
            router.setPagePath(PageConstant.FIRST_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e);
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
