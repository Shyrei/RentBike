package by.shyrei.rentbike.action.admin.bike;

import by.shyrei.rentbike.action.Action;
import by.shyrei.rentbike.controller.Router;
import by.shyrei.rentbike.entity.Bike;
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
public class FindBikeAction implements Action {
    private final static String BIKE_ID = "bikeId";
    private final static String BIKE = "bike";
    private final static String EMPTY_BIKE = "emptyBike";
    private final static String BIKE_IN_RENT = "bikeInRent";
    private final static String MESSAGE = "message";
    private BikeService bikeService = new BikeService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        try {
            int bikeId = Integer.parseInt(request.getParameter(BIKE_ID));
            Bike bike = bikeService.findById(bikeId);
            if (bike!=null){
                if (bike.isInRent()){
                    request.setAttribute(BIKE_IN_RENT, Boolean.TRUE);
                } else {
                    request.setAttribute(BIKE, bike);
                }
            } else {
                request.setAttribute(EMPTY_BIKE, Boolean.TRUE);
            }
            router.setPagePath(PageConstant.ONE_BIKE);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e);
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
