package by.shyrei.rentbike.controller;

import by.shyrei.rentbike.entity.Bike;
import by.shyrei.rentbike.exception.ServiceException;
import by.shyrei.rentbike.service.BikeService;
import by.shyrei.rentbike.util.PageConstant;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Project RentBike
 * Created on 14.08.2017.
 * author Shyrei Uladzimir
 */
@WebServlet(name = "ajax", urlPatterns = "/ajax")
public class AjaxController extends HttpServlet {
    private final static String STATION = "station";
    private final static String MESSAGE = "message";
    private BikeService bikeService = new BikeService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Bike> bikesList = new ArrayList<>();
        response.setContentType("text/plain");
        //response.setCharacterEncoding("UTF-8");
        int stationId = Integer.parseInt(request.getParameter(STATION));
        try {
            bikesList = bikeService.findAllOnStation(stationId);
        } catch (ServiceException e) {
            request.getSession().setAttribute(MESSAGE, e.getMessage());
            response.sendRedirect(PageConstant.ERROR_PAGE);
        }
        PrintWriter out = response.getWriter();
        out.print(new Gson().toJson(bikesList));
    }
}

