package by.shyrei.rentbike.action;

import by.shyrei.rentbike.action.admin.*;
import by.shyrei.rentbike.action.admin.bike.*;
import by.shyrei.rentbike.action.admin.order.ShowAllOrdersAction;
import by.shyrei.rentbike.action.admin.order.ShowUnclosedOrdersAction;
import by.shyrei.rentbike.action.admin.order.ShowUserOrdersAction;
import by.shyrei.rentbike.action.admin.station.AddStationAction;
import by.shyrei.rentbike.action.admin.station.ShowAllStationsAction;
import by.shyrei.rentbike.action.admin.user.*;
import by.shyrei.rentbike.action.bike.*;
import by.shyrei.rentbike.action.locale.ChangeLocaleAction;
import by.shyrei.rentbike.action.user.*;

/**
 * Project RentBikeAction
 * Created on 13.07.2017.
 * author Shyrei Uladzimir
 */
public enum ActionType {
    LOGIN (new LoginUserAction()),
    LOGOUT (new LogoutUserAction()),
    REGISTER (new RegisterUserAction()),

    RENT_BIKE(new RentBikeAction()),
    RETURN_BIKE(new ReturnBikeAction()),

    ADD_MONEY(new AddMoneyAction()),
    ADD_TYPE(new AddTypeAction()),
    ADD_STATION(new AddStationAction()),
    ADD_ROLE(new AddRoleAction()),
    ADD_BIKE(new AddBikeAction()),

    CHANGE_LOCALE (new ChangeLocaleAction()),
    CHANGE_USER(new ChangeUserAction()),
    CHANGE_TYPE(new ChangeTypeAction()),
    CHANGE_BIKE(new ChangeBikeStatusAction()),

    SHOW_ORDERS(new ShowOrdersAction()),
    SHOW_USER_ORDERS(new ShowUserOrdersAction()),
    SHOW_ALL_BIKES (new ShowAllBikesAction()),
    SHOW_ALL_USERS (new ShowAllUserAction()),
    SHOW_ALL_TYPES (new ShowAllTypesAction()),
    SHOW_ALL_ROLES (new ShowAllRoleAction()),
    SHOW_ALL_ORDERS (new ShowAllOrdersAction()),
    SHOW_ALL_STATIONS (new ShowAllStationsAction()),
    SHOW_UNCLOSED_ORDERS (new ShowUnclosedOrdersAction()),
    SHOW_ADMIN_PAGE(new ShowAdminPageAction()),
    SHOW_BIKES_PAGE (new ShowAllBikesByPageAction()),
    SELECT_BIKE (new SelectBikeAction()),
    SHOW_BIKES_ON_STATION(new ShowBikesOnStationAction()),

    FIND_USER(new FindUserAction()),
    FIND_BIKE (new FindBikeAction()),
    GET_USER_DATA (new GetUserDataAction()),
    GET_BIKE_DATA (new GetBikeDataAction()),
    GET_TYPE_DATA (new GetTypeDataAction());

    Action action;

    Action getAction(){
        return action;
    }

    ActionType(Action action){
        this.action = action;
    }

}
