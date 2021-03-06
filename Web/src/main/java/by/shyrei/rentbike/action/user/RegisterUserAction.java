package by.shyrei.rentbike.action.user;
import by.shyrei.rentbike.action.Action;
import by.shyrei.rentbike.controller.Router;
import by.shyrei.rentbike.util.PageConstant;
import by.shyrei.rentbike.entity.User;
import by.shyrei.rentbike.exception.ServiceException;
import by.shyrei.rentbike.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * Project RentBikeAction
 * Created on 14.07.2017.
 * author Shyrei Uladzimir
 */
public class RegisterUserAction implements Action {
    private final static String FIRST_NAME = "first_name";
    private final static String LAST_NAME = "last_name";
    private final static String LOGIN = "login";
    private final static String PASSWORD = "password";
    private final static String BALANCE = "balance";
    private final static String USER = "user";
    private final static String WRONG_REGISTER_DATA = "wrongRegisterData";
    private final static String WRONG_USER = "wrongUser";
    private final static String MESSAGE = "message";
    private final static String REFERRER = "referrer";
    private UserService userService = new UserService();

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        HttpSession session = request.getSession();
        /*User user = new User();
        HttpSession session = request.getSession();
        user.setFirstName(request.getParameter(FIRST_NAME));
        user.setLastName(request.getParameter(LAST_NAME));
        user.setLogin(request.getParameter(LOGIN));
        user.setPassword(request.getParameter(PASSWORD));
        user.setBalance(new BigDecimal(request.getParameter(BALANCE)));*/
        User user = getUserDataFromRequest(request);
        try {
            if (userService.validateUser(user) != null){
                request.setAttribute(WRONG_REGISTER_DATA, userService.validateUser(user));
                router.setPagePath(PageConstant.REGISTER_PAGE);
                request.getSession().setAttribute(REFERRER, PageConstant.REGISTER_PAGE);
            } else if (userService.registerUser(user)) {
                User newUser = userService.findUserByLoginAndPassword(user.getLogin(), request.getParameter(PASSWORD));
                session.setAttribute(USER, newUser);
                router.setPagePath(PageConstant.FIRST_PAGE);
                router.setRoute(Router.RouteType.REDIRECT);
            } else {
                request.setAttribute(WRONG_USER, Boolean.TRUE);
                router.setPagePath(PageConstant.REGISTER_PAGE);
                request.getSession().setAttribute(REFERRER, PageConstant.REGISTER_PAGE);
            }
        } catch (ServiceException e) {
            session.setAttribute(MESSAGE, e.getMessage());
            router.setPagePath(PageConstant.ERROR_PAGE);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }

    private User getUserDataFromRequest(HttpServletRequest request) {
        User user = new User();
        user.setFirstName(request.getParameter(FIRST_NAME));
        user.setLastName(request.getParameter(LAST_NAME));
        user.setLogin(request.getParameter(LOGIN));
        user.setPassword(request.getParameter(PASSWORD));
        user.setBalance(new BigDecimal(request.getParameter(BALANCE)));
        return user;
    }
}
