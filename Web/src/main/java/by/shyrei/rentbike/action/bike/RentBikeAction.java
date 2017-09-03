package by.shyrei.rentbike.action.bike;

import by.shyrei.rentbike.action.Action;
import by.shyrei.rentbike.controller.Router;
import by.shyrei.rentbike.entity.Bike;
import by.shyrei.rentbike.entity.User;
import by.shyrei.rentbike.exception.ServiceException;
import by.shyrei.rentbike.service.BikeService;
import by.shyrei.rentbike.service.UserService;
import by.shyrei.rentbike.util.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project RentBikeAction
 * Created on 17.07.2017.
 * author Shyrei Uladzimir
 */
public class RentBikeAction implements Action {
    private final static String BIKE_ID = "bikeId";
    private final static String BIKE = "bike";
    private final static String USER = "user";
    private final static String MESSAGE = "message";
    private BikeService bikeService = new BikeService();
    private UserService userService = new UserService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        try {
            User user = (User) request.getSession().getAttribute(USER);
            if (user != null && user.getBalance().intValue() > 0){
                Bike bike = bikeService.rentBike(Integer.parseInt(request.getParameter(BIKE_ID)), user);
                //Bike bike = bikeService.rentBike(Integer.parseInt(request.getParameter(BIKE_ID)), user.getId());
                request.getSession().setAttribute(BIKE, bike);
                User updateUser = userService.findUserById(user.getId());
                request.getSession().setAttribute(USER, updateUser);
                router.setPagePath(PageConstant.RENT_BIKE);
                router.setRoute(Router.RouteType.REDIRECT);
            }   else {
                router.setPagePath(PageConstant.ADD_MONEY);
            }
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
