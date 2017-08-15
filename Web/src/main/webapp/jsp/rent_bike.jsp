<%--@elvariable id="bike" type="by.shyrei.rentbike.entity.Bike"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <title><fmt:message key="title.rent.bike.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet" type="text/css" >
</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-6 col-md-offset-6">
        <div class="text-center">
            <h4><fmt:message key="message.ok.rent"/></h4>
        </div>
        <table class="MyTableOther">
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
            <tr>
                <td>${bike.id}</td>
                <td>${bike.type}</td>
                <td>${bike.pricePerHour}</td>
                <td>${bike.city}</td>
                <td>${bike.location}</td>
                <td><img src="data:image/jpeg;base64,${bike.picture}"/>
                </td>
            </tr>
        </table>
        <a href="../index.jsp" class="btn btn-default"><fmt:message key="continue"/></a>
    </div>
    <%@ include file="../include/footer.jsp" %>
</body>
</html>




