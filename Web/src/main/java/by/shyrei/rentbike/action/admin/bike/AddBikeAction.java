package by.shyrei.rentbike.action.admin.bike;

import by.shyrei.rentbike.action.Action;
import by.shyrei.rentbike.controller.Router;
import by.shyrei.rentbike.entity.Bike;
import by.shyrei.rentbike.exception.ServiceException;
import by.shyrei.rentbike.service.BikeService;
import by.shyrei.rentbike.util.PageConstant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Project RentBike
 * Created on 04.08.2017.
 * author Shyrei Uladzimir
 */
public class AddBikeAction implements Action {
    private final static String TYPE_ID = "typeId";
    private final static String STATION_ID = "stationId";
    private final static String MESSAGE = "message";
    private BikeService bikeService = new BikeService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        Bike bike = new Bike();
        try {
            bike.setTypeId(Integer.parseInt(request.getParameter(TYPE_ID)));
            bike.setStationId(Integer.parseInt(request.getParameter(STATION_ID)));
            bikeService.createBike(bike);
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
