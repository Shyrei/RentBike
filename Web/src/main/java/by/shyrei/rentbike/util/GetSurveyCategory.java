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

    private String surveyCat;

    @Override
    public int doStartTag() throws JspException {
        try {
            pageContext.getAttribute("surveyCat22");
            pageContext.setAttribute("ibal2", surveyCat);

            String ourSurveyCat = "xyi";
            pageContext.setAttribute("ibal", ourSurveyCat);

        } catch (Exception ex) {
            ex.getMessage();
        }
        return SKIP_BODY;

    }

    public void setSurveyCat(String surveyCat) {
        this.surveyCat = surveyCat;
    }
}
