package by.shyrei.rentbike.action.bike;

import by.shyrei.rentbike.action.Action;
import by.shyrei.rentbike.controller.Router;
import by.shyrei.rentbike.util.PageConstant;
import by.shyrei.rentbike.entity.Bike;
import by.shyrei.rentbike.exception.ServiceException;
import by.shyrei.rentbike.service.BikeService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Project RentBikeAction
 * Created on 15.07.2017.
 * author Shyrei Uladzimir
 */
public class ShowAllBikesAction implements Action {
    private final static String BIKES_LIST = "bikesList";
    private final static String MESSAGE = "message";
    private BikeService bikeService = new BikeService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        ArrayList<Bike> bikesList;
        try {
            bikesList = bikeService.findAll();
            request.setAttribute(BIKES_LIST, bikesList);
            router.setPagePath(PageConstant.MAIN_PAGE);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
