<%--@elvariable id="user" type="by.shyrei.rentbike.entity.User"--%>
<%--@elvariable id="bikesList" type="java.util.ArrayList"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <title><fmt:message key="title.main.page"/></title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" >
</head>
<body>
<%@ include file="/include/navbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-6 col-md-offset-6">
    <table class="MyTableMain">
        <thead>
        <tr>
            <td><fmt:message key="table.number"/></td>
            <td><fmt:message key="table.rent.type"/></td>
            <td><fmt:message key="table.rent.price"/></td>
            <td><fmt:message key="table.rent.city"/></td>
            <td><fmt:message key="table.rent.location"/></td>
            <td><fmt:message key="table.rent.photo"/></td>
        </tr>
        </thead>
        <c:forEach items="${bikesList}" varStatus="сounter">
            <tr>
                    <%--roleId > display a list of bicycles without the possibility to rent them--%>
                    <%--users role: 1 - vip_user; 2 - user; 3 - user_has_order; 4 - blocked user; 5 - administrator--%>
                <c:if test="${empty user or (not empty user and user.roleId > 2)}">
                    <td>${bikesList[сounter.count-1].id}</td>
                </c:if>
                    <%--roleId <=2 displaying a list of bicycles with the possibility to rent them--%>
                    <%--users role: 1 - vip_user; 2 - user; 3 - user_has_order; 4 - blocked user; 5 - administrator--%>
                <c:if test="${not empty user and user.roleId <= 2}">
                    <td>
                        <form action="${pageContext.request.contextPath}/controller" method="post">
                            <input type="hidden" name="action" value="rent_bike">
                            <input type="hidden" name="bikeId" value="${bikesList[сounter.count-1].id}">
                            <button type="submit" class="btn btn-link">${bikesList[сounter.count-1].id} <fmt:message key="rent"/></button>
                        </form>
                    </td>
                </c:if>
                <td>${bikesList[сounter.count-1].type}</td>
                <td>${bikesList[сounter.count-1].pricePerHour}</td>
                <td>${bikesList[сounter.count-1].city}</td>
                <td>${bikesList[сounter.count-1].location}</td>
                <td><img src="data:image/jpeg;base64,${bikesList[сounter.count-1].picture}"/></td>
            </tr>
        </c:forEach>
    </table>
    <nav aria-label="...">
        <ul class="pager">
            <li class="previous${leftPageClass}"><a href="${leftPage}"><fmt:message key="page.previous"/></a></li>
            <li class="next${rightPageClass}"><a href="${rightPage}"><fmt:message key="page.next"/></a></li>
        </ul>
    </nav>
    </div>
</div>
<%@ include file="/include/footer.jsp" %>
</body>
</html>



