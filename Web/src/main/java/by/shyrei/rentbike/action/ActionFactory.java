package by.shyrei.rentbike.action;

import javax.servlet.http.HttpServletRequest;

/**
 * Project RentBikeAction
 * Created on 13.07.2017.
 * author Shyrei Uladzimir
 */
public class ActionFactory {

    public static Action getAction(HttpServletRequest request){
        ActionType entry = ActionType.valueOf(request.getParameter("action").toUpperCase());
        return entry.getAction();
    }
}
