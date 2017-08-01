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
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;


/**
 * Project RentBike
 * Created on 28.07.2017.
 * author Shyrei Uladzimir
 */
public class CreateTypeAction implements Action {
    private final static String TYPE_TYPE = "type";
    private final static String TYPE_PRICE = "price";
    private final static String TYPE_PHOTO = "photo";
    private final static String MESSAGE = "message";
    private BikeTypeService bikeTypeService = new BikeTypeService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) {
        Router router = new Router();
        BikeType bikeType = new BikeType();
        try {
            bikeType.setType(request.getParameter(TYPE_TYPE));
            bikeType.setPrice(new BigDecimal(request.getParameter(TYPE_PRICE)));
            Part filePart = request.getPart(TYPE_PHOTO);
            InputStream inputStream = filePart.getInputStream();
            bikeTypeService.create(bikeType, inputStream);
            router.setPagePath(PageConstant.FIRST_PAGE);
        } catch (ServiceException | ServletException | IOException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
