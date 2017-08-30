package by.shyrei.rentbike.filter;

import by.shyrei.rentbike.entity.User;
import by.shyrei.rentbike.util.PageConstant;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import java.io.IOException;

/**
 * Project RentBike
 * Created on 29.08.2017.
 * author Shyrei Uladzimir
 */
@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/includeAdmin/*", "/jsp/admin_page.jsp"})
public class SecurityFilter implements Filter {
    private final static String USER = "user";
    private final static String MESSAGE = "message";

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        User user = (User) httpRequest.getSession().getAttribute(USER);
        if (user == null ) {
            httpRequest.getSession().setAttribute(MESSAGE, "Access denied!!!");
            request.getRequestDispatcher(PageConstant.ERROR_PAGE).forward(request, response);
        } else {
            if (user.getRoleId() != 5) {
                httpRequest.getSession().setAttribute(MESSAGE, "Access denied!!!");
                request.getRequestDispatcher(PageConstant.ERROR_PAGE).forward(request, response);
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void destroy() {

    }
}
