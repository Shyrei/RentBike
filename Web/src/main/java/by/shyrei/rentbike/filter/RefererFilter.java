package by.shyrei.rentbike.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project RentBike
 * Created on 24.07.2017.
 * author Shyrei Uladzimir
 */
/*@WebFilter("/controller")*/
public class RefererFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        /*HttpServletRequest request = (HttpServletRequest) servletRequest;
        String ref = request.getHeader("referer");
        System.out.println(ref);
        request.setAttribute("ref", ref);*/
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
}