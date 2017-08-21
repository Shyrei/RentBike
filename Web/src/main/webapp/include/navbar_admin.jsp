<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="ctg" uri="customtags" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<nav class="navbar navbar-default navbar-static-top" style="background: #ADB9C9">
    <div class="container-fluid">
        <ul class="nav navbar-nav">

            <%--пользователь--%>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#"
                   style="font-family: Comic Sans MS, cursive, sans-serif; color: #333333"><fmt:message key="nav.user"/>
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/controller?action=show_all_users"><fmt:message key="nav.user.show.all"/></a></li>
                    <li><a href="/controller?action=get_user_data"><fmt:message key="nav.user.change"/></a></li>
                    <li class="divider"></li>
                    <li><a href="#"><fmt:message key="search.form.user"/></a>
                        <form action="/controller" method="post" class="form-horizontal">
                            <input type="hidden" name="action" value="find_user">
                            <div class="form-group">
                                <label for="user" class="col-sm-offset-1 col-sm-2 control-label"><fmt:message key="search.form.user.id"/></label>
                                <div class="col-sm-8">
                                    <input type="text" id="user" name="userId" class="form-control" placeholder="<fmt:message key="search.form.user.id.placeholder"/>" maxlength="3" required pattern="[0-9]{1,3}">
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-8">
                                    <button type="submit" class="btn btn-primary"><fmt:message key="search.form.submit"/></button>
                                </div>
                            </div>
                        </form>
                    </li>
                </ul>
            </li>

            <%--роль--%>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#"
                   style="font-family: Comic Sans MS, cursive, sans-serif; color: #333333"><fmt:message key="nav.role"/>
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="../includeAdmin/role_add.jsp"><fmt:message key="nav.role.add"/></a></li>
                    <li><a href="/controller?action=show_all_roles"><fmt:message key="nav.role.show.all"/></a>
                </ul>
            </li>

            <%--заказ--%>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#"
                   style="font-family: Comic Sans MS, cursive, sans-serif; color: #333333"><fmt:message
                        key="nav.order"/>
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/controller?action=show_all_orders"><fmt:message key="nav.order.show.all"/></a>
                    <li><a href="/controller?action=show_unclosed_orders"><fmt:message key="nav.order.unclosed.orders"/></a>
                    <li class="divider"></li>
                    <li><a href="#"><fmt:message key="search.form.order"/></a>
                        <form action="/controller" method="post" class="form-horizontal">
                            <input type="hidden" name="action" value="show_user_orders">
                            <div class="form-group">
                                <label for="userForm" class="col-sm-offset-1 col-sm-2 control-label"><fmt:message key="nav.user"/></label>
                                <div class="col-sm-8">
                                    <select class="form-control" id="userForm" name="userId">
                                        <c:forEach items="${usersList}" var="user">
                                            <option value="${user.id}"><fmt:message key="update.user.id"/> ${user.id},
                                                <fmt:message key="update.user.role"/> ${user.roleId}, <fmt:message key="update.user.first.name"/> ${user.firstName}, <fmt:message key="update.user.login"/> ${user.login}, <fmt:message key="update.user.balance"/> ${user.balance}</option></c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-3 col-sm-8">
                                    <button type="submit" class="btn btn-primary"><fmt:message key="search.form.submit"/></button>
                                </div>
                            </div>
                        </form>
                    </li>
                </ul>
            </li>

            <%--тип--%>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#"
                   style="font-family: Comic Sans MS, cursive, sans-serif; color: #333333"><fmt:message key="nav.type"/>
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="../includeAdmin/type_add.jsp"><fmt:message key="nav.type.add"/></a></li>
                    <li><a href="/controller?action=get_type_data"><fmt:message key="nav.type.edit.price"/></a></li>
                    <li><a href="/controller?action=show_all_types"><fmt:message key="nav.type.show.all"/></a></li>
                </ul>
            </li>

            <%--станция--%>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#"
                   style="font-family: Comic Sans MS, cursive, sans-serif; color: #333333"><fmt:message
                        key="nav.station"/>
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/controller?action=show_all_stations"><fmt:message key="nav.station.show.all"/></a></li>
                    <li><a href="../includeAdmin/station_add.jsp"><fmt:message key="nav.station.add"/></a></li>
                </ul>
            </li>

            <%--велосипед--%>
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#" style="font-family: Comic Sans MS, cursive, sans-serif; color: #333333"><fmt:message key="nav.bike"/>
                    <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="/controller?action=get_bike_data"><fmt:message key="nav.bike.add"/></a></li>
                    <li><a href="/controller?action=show_bikes_on_station"><fmt:message key="nav.bike.show.on.station"/></a></li>
                    <li class="divider"></li>
                    <li><a href="#"><fmt:message key="nav.bike.edit"/></a>
                    <form action="/controller" method="post" class="form-horizontal">
                        <input type="hidden" name="action" value="find_bike">
                        <div class="form-group">
                            <label for="bike" class="col-sm-offset-1 col-sm-2 control-label"><fmt:message key="search.form.bike.id"/></label>
                            <div class="col-sm-8">
                                <input type="text" id="bike" name="bikeId" class="form-control" placeholder="<fmt:message key="search.form.bike.id.placeholder"/>" maxlength="3" required pattern="[0-9]{1,3}">
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-3 col-sm-8">
                                <button type="submit" class="btn btn-primary"><fmt:message key="search.form.submit"/></button>
                            </div>
                        </div>
                    </form>
                    </li>
                </ul>
            </li>
        </ul>

        <%--выход/страница админа--%>
        <c:if test="${not empty user and user.roleId == 5}">
            <form action="/controller" class="navbar-form navbar-right"><fmt:message key="nav.welcome"/><ctg:info user="${user}"/>
                <input type="hidden" name="action" value="logout">
                <button type="submit" class="btn btn-default" style="font-family: Comic Sans MS, cursive, sans-serif; color: #333333"><fmt:message key="nav.signout"/></button>
            </form>
            <form action="/controller" method="post" class="navbar-form navbar-right"><input type="hidden" name="action" value="show_admin_page">
                <button type="submit" class="btn btn-default" style="font-family: Comic Sans MS, cursive, sans-serif; color: #333333"><fmt:message key="nav.admin"/></button>
            </form>
        </c:if>
    </div>
</nav>


