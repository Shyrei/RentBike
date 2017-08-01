package by.shyrei.rentbike.action;

import by.shyrei.rentbike.controller.Router;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Project RentBikeAction
 * Created on 13.07.2017.
 * author Shyrei Uladzimir
 */
public interface Action {
    Router execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
