package by.shyrei.rentbike.controller;

/**
 * Project RentBike
 * Created on 17.07.2017.
 * author Shyrei Uladzimir
 */
public class Router {
    public enum RouteType {
        FORWARD, REDIRECT
    }
    private String pagePath;
    private RouteType route = RouteType.FORWARD;


    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public RouteType getRoute() {
        return route;
    }

    public void setRoute(RouteType route) {
        if (route == null) {
            this.route = RouteType.FORWARD;
        }
        this.route = route;
    }
}
