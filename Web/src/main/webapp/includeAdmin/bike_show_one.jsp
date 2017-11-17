<%--@elvariable id="bike" type="by.shyrei.rentbike.entity.Bike"--%>
<%--@elvariable id="emptyBike" type="java.lang.String"--%>
<%--@elvariable id="bikeInRent" type="java.lang.String"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/include/uselocale.jsp" %>
<html>
<head>
    <title><fmt:message key="title.show.bike.page"/></title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" >
</head>
<body>
<%@ include file="/include/navbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-6 col-md-offset-6">
        <c:if test="${not empty emptyBike}">
            <div class="text-center">
                <h4><fmt:message key="message.empty.bike"/></h4>
            </div>
            <a href="${pageContext.request.contextPath}/controller?action=show_admin_page" class="btn btn-default"><fmt:message key="continue"/></a>
        </c:if>
        <c:if test="${not empty bikeInRent}">
            <div class="text-center">
                <h4><fmt:message key="message.bike.in.rent"/></h4>
            </div>
            <a href="${pageContext.request.contextPath}/controller?action=show_admin_page" class="btn btn-default"><fmt:message key="continue"/></a>
        </c:if>
        <c:if test="${not empty bike and empty bikeInRent}">
            <table class="MyTableOther">
                <thead>
                <tr>
                    <td><fmt:message key="table.number"/></td>
                    <td><fmt:message key="table.rent.type"/></td>
                    <td><fmt:message key="table.rent.price"/></td>
                    <td><fmt:message key="table.rent.city"/></td>
                    <td><fmt:message key="table.rent.location"/></td>
                    <td><fmt:message key="table.rent.photo"/></td>
                    <td><fmt:message key="table.bike.status"/></td>
                </tr>
                </thead>
                <tr>
                    <td>${bike.id}</td>
                    <td>${bike.type}</td>
                    <td>${bike.pricePerHour}</td>
                    <td>${bike.city}</td>
                    <td>${bike.location}</td>
                    <td><img src="data:image/jpeg;base64,${bike.picture}"/></td>
                    <td>${bike.available}</td>
                </tr>
            </table>
            <c:set var="bikeId" scope="session" value="${bike.id}"/>
            <a href="${pageContext.request.contextPath}/controller?action=change_bike" class="btn btn-default"><fmt:message key="update.bike.submit" /></a>
        </c:if>
    </div>
    <%@ include file="/include/footer_admin.jsp" %>
</body>
</html>

