package by.shyrei.rentbike.action.bike;

import by.shyrei.rentbike.action.Action;
import by.shyrei.rentbike.controller.Router;
import by.shyrei.rentbike.entity.User;
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
public class ShowAllBikesByPageAction implements Action {

    private final static int PAGE_CAPACITY = 8;
    private final static String PAGE = "page";
    private final static String LEFT_PAGE = "leftPage";
    private final static String RIGHT_PAGE = "rightPage";
    private final static String LEFT_PAGE_CLASS = "leftPageClass";
    private final static String RIGHT_PAGE_CLASS = "rightPageClass";
    private final static String GO_TO_LEFT_PAGE = "controller?action=show_bikes_page&page=";
    private final static String GO_TO_RIGHT_PAGE = "controller?action=show_bikes_page&page=";
    private final static String DISABLED_BUTTON = " disabled";
    private final static String NOT_ACTION = "";
    private final static String USER = "user";
    private final static String BIKES_LIST = "bikesList";
    private final static String MESSAGE = "message";
    private int pageNumber = 1;
    private BikeService bikeService = new BikeService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        String leftPage;
        String leftPageClass;
        String rightPage;
        String rightPageClass;
        ArrayList<Bike> bikesList;
        if (request.getParameter(PAGE) != null) {
            pageNumber = Integer.parseInt(request.getParameter(PAGE));
        }
        try {
            bikesList = bikeService.findAllByPage(PAGE_CAPACITY, pageNumber);
            int bikeCount = bikeService.findAll().size();
            if (pageNumber > 1) {
                leftPage = GO_TO_LEFT_PAGE + (pageNumber - 1);
                leftPageClass = NOT_ACTION;
            } else {
                leftPage = NOT_ACTION;
                leftPageClass = DISABLED_BUTTON;
            }
            if (bikeCount >= pageNumber * PAGE_CAPACITY) {
                rightPage = GO_TO_RIGHT_PAGE + (pageNumber + 1);
                rightPageClass = NOT_ACTION;
            } else {
                rightPage = NOT_ACTION;
                rightPageClass = DISABLED_BUTTON;
            }
            User user = (User) request.getSession().getAttribute(USER);
            request.setAttribute(USER, user);
            request.setAttribute(BIKES_LIST, bikesList);
            request.setAttribute(LEFT_PAGE, leftPage);
            request.setAttribute(LEFT_PAGE_CLASS, leftPageClass);
            request.setAttribute(RIGHT_PAGE, rightPage);
            request.setAttribute(RIGHT_PAGE_CLASS, rightPageClass);
            router.setPagePath(PageConstant.MAIN_PAGE);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
