package by.shyrei.rentbike.action.user;

import by.shyrei.rentbike.action.Action;
import by.shyrei.rentbike.controller.Router;
import by.shyrei.rentbike.util.PageConstant;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Project RentBikeAction
 * Created on 17.07.2017.
 * author Shyrei Uladzimir
 */
public class LogoutUserAction implements Action {
    private final static String USER = "user";
    private final static String INVALIDATE = "invalidate";

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        HttpSession session = request.getSession();
        session.removeAttribute(USER);
        session.setAttribute(INVALIDATE, Boolean.TRUE);
        router.setPagePath(PageConstant.FIRST_PAGE);
        router.setRoute(Router.RouteType.REDIRECT);
        return router;
    }
}
