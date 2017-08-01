package by.shyrei.rentbike.action;

import by.shyrei.rentbike.action.admin.*;
import by.shyrei.rentbike.action.admin.bike.ChangeTypeAction;
import by.shyrei.rentbike.action.admin.bike.CreateTypeAction;
import by.shyrei.rentbike.action.admin.bike.GetTypeDataAction;
import by.shyrei.rentbike.action.admin.bike.ShowAllTypesAction;
import by.shyrei.rentbike.action.admin.order.ShowAllOrdersAction;
import by.shyrei.rentbike.action.admin.order.ShowUnclosedOrdersAction;
import by.shyrei.rentbike.action.admin.order.ShowUserOrdersAction;
import by.shyrei.rentbike.action.admin.user.*;
import by.shyrei.rentbike.action.bike.ReturnBikeAction;
import by.shyrei.rentbike.action.bike.RentBikeAction;
import by.shyrei.rentbike.action.bike.ShowAllBikesAction;
import by.shyrei.rentbike.action.bike.ShowAllBikesByPageAction;
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
    ADD_TYPE(new CreateTypeAction()),
    ADD_ROLE(new CreateRoleAction()),
    CHANGE_LOCALE (new ChangeLocaleAction()),
    CHANGE_USER(new ChangeUserAction()),
    CHANGE_TYPE(new ChangeTypeAction()),

    SHOW_ORDERS(new ShowOrdersAction()),
    SHOW_USER_ORDERS(new ShowUserOrdersAction()),
    SHOW_ALL_BIKES (new ShowAllBikesAction()),
    SHOW_ALL_USERS (new ShowAllUserAction()),
    SHOW_ALL_TYPES (new ShowAllTypesAction()),
    SHOW_ALL_ROLES (new ShowAllRoleAction()),
    SHOW_ALL_ORDERS (new ShowAllOrdersAction()),
    SHOW_ALL_UNCLOSED_ORDERS (new ShowUnclosedOrdersAction()),
    SHOW_ADMIN_PAGE(new ShowAdminPageAction()),
    SHOW_BIKES_PAGE (new ShowAllBikesByPageAction()),

    FIND_USER(new FindUserAction()),
    GET_USER_DATA (new GetUserDataAction()),
    GET_TYPE_DATA (new GetTypeDataAction());

    Action action;

    Action getAction(){
        return action;
    }

    ActionType(Action action){
        this.action = action;
    }

}
