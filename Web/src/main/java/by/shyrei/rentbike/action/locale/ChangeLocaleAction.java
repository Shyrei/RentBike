package by.shyrei.rentbike.action.locale;

import by.shyrei.rentbike.action.Action;
import by.shyrei.rentbike.controller.Router;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Project RentBikeAction
 * Created on 14.07.2017.
 * author Shyrei Uladzimir
 */
public class ChangeLocaleAction implements Action {
    private final static String LOCALE = "locale";
    private final static String REFERRER = "referrer";

    @Override
    public Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Router router = new Router();
        HttpSession session = request.getSession();
        String locale = LocaleType.getLocale(request.getParameter(LOCALE));
        session.setAttribute(LOCALE, locale);
        Cookie c = new Cookie(LOCALE, locale);
        c.setMaxAge(60 * 60 * 24 * 30);
        response.addCookie(c);
        //TODO подумать что делать с этим методом - пока тут костыль... когда идем форвардом мы не можем получить реферер страницы... (((
        String referer = request.getHeader("referer");
        if (referer.equals("http://localhost:8080/controller")) {
            router.setPagePath((String) request.getSession().getAttribute(REFERRER));
            router.setRoute(Router.RouteType.REDIRECT);
        } else {
            router.setPagePath(referer);
            router.setRoute(Router.RouteType.REDIRECT);
        }
        return router;
    }
}
