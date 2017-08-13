package by.shyrei.rentbike.action.admin.station;

import by.shyrei.rentbike.action.Action;
import by.shyrei.rentbike.controller.Router;
import by.shyrei.rentbike.entity.Station;
import by.shyrei.rentbike.exception.ServiceException;
import by.shyrei.rentbike.service.StationService;
import by.shyrei.rentbike.util.PageConstant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Project RentBike
 * Created on 04.08.2017.
 * author Shyrei Uladzimir
 */
public class AddStationAction implements Action {
    private final static String STATION_CITY = "city";
    private final static String STATION_LOCATION = "location";
    private final static String MESSAGE = "message";
    private StationService stationService = new StationService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        Station station = new Station();
        try {
            station.setCity(request.getParameter(STATION_CITY));
            station.setLocation(request.getParameter(STATION_LOCATION));
            stationService.createStation(station);
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