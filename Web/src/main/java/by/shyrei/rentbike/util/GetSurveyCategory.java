package by.shyrei.rentbike.util;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.JspTag;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * Project RentBike
 * Created on 14.03.2018.
 * author Shyrei Uladzimir
 */
public class GetSurveyCategory extends TagSupport {


    private static final long serialVersionUID = 1L;


    @Override
    public int doStartTag() throws JspException {
        try {

            String ourSurveyCat = "xyi";
            pageContext.setAttribute("ibal", ourSurveyCat);

        } catch (Exception ex) {
            ex.getMessage();
        }
        return SKIP_BODY;

    }


}
