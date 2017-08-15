<%--@elvariable id="stationsList" type="java.util.ArrayList"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <title><fmt:message key="title.show.all.stations.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet" type="text/css" >
</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-6 col-md-offset-6">
        <div class="row">
        <table class="MyTableOther">
            <thead>
            <tr>
                <td><fmt:message key="table.number"/></td>
                <td><fmt:message key="table.station.city"/></td>
                <td><fmt:message key="table.station.location"/></td>
            </tr>
            </thead>
            <c:forEach items="${stationsList}" varStatus="сounter">
                <tr>
                    <td>${stationsList[сounter.count-1].id}</td>
                    <td>${stationsList[сounter.count-1].city}</td>
                    <td>${stationsList[сounter.count-1].location}</td>

                </tr>
            </c:forEach>
        </table>
        <a href="/controller?action=show_admin_page" class="btn btn-default"><fmt:message key="continue"/></a>
    </div>
</div>
<%@ include file="../include/footer_admin.jsp" %>
</body>
</html>



