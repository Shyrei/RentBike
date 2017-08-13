package by.shyrei.rentbike.action.admin.bike;

import by.shyrei.rentbike.action.Action;
import by.shyrei.rentbike.controller.Router;
import by.shyrei.rentbike.entity.BikeType;
import by.shyrei.rentbike.entity.Station;
import by.shyrei.rentbike.exception.ServiceException;
import by.shyrei.rentbike.service.BikeTypeService;
import by.shyrei.rentbike.service.StationService;
import by.shyrei.rentbike.util.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Project RentBike
 * Created on 04.08.2017.
 * author Shyrei Uladzimir
 */
public class GetBikeDataAction implements Action {
    private final static String TYPES_LIST = "typesList";
    private final static String STATIONS_LIST = "stationsList";
    private final static String MESSAGE = "message";
    private BikeTypeService bikeTypeService = new BikeTypeService();
    private StationService stationService = new StationService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        ArrayList<BikeType> typesList;
        ArrayList<Station> stationsList;

        try {
            typesList = bikeTypeService.findAll();
            stationsList = stationService.findAll();
            request.setAttribute(TYPES_LIST, typesList);
            request.setAttribute(STATIONS_LIST, stationsList);
            router.setPagePath(PageConstant.ADD_BIKE);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
