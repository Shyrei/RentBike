package by.shyrei.rentbike.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * Project RentBikeAction
 * Created on 14.07.2017.
 * author Shyrei Uladzimir
 * <p>
 * Filter to prevent user return to cached page content  by pressing back/forward button of browser
 */

@WebFilter(urlPatterns = {"/controller", "/ajax"})
public class EncodingFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}
