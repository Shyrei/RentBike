package by.shyrei.rentbike.action.admin.order;

import by.shyrei.rentbike.action.Action;
import by.shyrei.rentbike.controller.Router;
import by.shyrei.rentbike.entity.Order;
import by.shyrei.rentbike.exception.ServiceException;
import by.shyrei.rentbike.service.OrderService;
import by.shyrei.rentbike.util.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Project RentBike
 * Created on 03.09.2017.
 * author Shyrei Uladzimir
 */
public class ShowAllOrdersByPageAction implements Action {
    private final static int PAGE_CAPACITY = 12;
    private final static String PAGE = "page";
    private final static String LEFT_PAGE = "leftPage";
    private final static String RIGHT_PAGE = "rightPage";
    private final static String LEFT_PAGE_CLASS = "leftPageClass";
    private final static String RIGHT_PAGE_CLASS = "rightPageClass";
    private final static String GO_TO_LEFT_PAGE = "controller?action=show_orders_page&page=";
    private final static String GO_TO_RIGHT_PAGE = "controller?action=show_orders_page&page=";
    private final static String DISABLED_BUTTON = " disabled";
    private final static String NOT_ACTION = "";
    private final static String ORDERS_LIST = "ordersList";
    private final static String MESSAGE = "message";
    private int pageNumber = 1;
    private OrderService orderService = new OrderService();


    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        String leftPage;
        String leftPageClass;
        String rightPage;
        String rightPageClass;
        ArrayList<Order> ordersList;
        if (request.getParameter(PAGE) != null) {
            pageNumber = Integer.parseInt(request.getParameter(PAGE));
        }
        try {
            ordersList = orderService.findAllByPage(PAGE_CAPACITY, pageNumber);
            int orderCount = orderService.findAll().size();
            if (pageNumber > 1) {
                leftPage = GO_TO_LEFT_PAGE + (pageNumber - 1);
                leftPageClass = NOT_ACTION;
            } else {
                leftPage = NOT_ACTION;
                leftPageClass = DISABLED_BUTTON;
            }
            if (orderCount > pageNumber * PAGE_CAPACITY) {
                rightPage = GO_TO_RIGHT_PAGE + (pageNumber + 1);
                rightPageClass = NOT_ACTION;
            } else {
                rightPage = NOT_ACTION;
                rightPageClass = DISABLED_BUTTON;
            }
            request.setAttribute(ORDERS_LIST, ordersList);
            request.setAttribute(LEFT_PAGE, leftPage);
            request.setAttribute(LEFT_PAGE_CLASS, leftPageClass);
            request.setAttribute(RIGHT_PAGE, rightPage);
            request.setAttribute(RIGHT_PAGE_CLASS, rightPageClass);
            router.setPagePath(PageConstant.ALL_ORDERS_PAGE);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
