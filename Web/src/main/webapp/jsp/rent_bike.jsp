<%--@elvariable id="bike" type="by.shyrei.rentbike.entity.Bike"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <title><fmt:message key="title.rent.bike.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background: url(../images/fon1.jpg);
        }
    </style>
</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-6 col-md-offset-6">
        <div class="text-center">
            <h4><fmt:message key="message.ok.rent"/></h4>
        </div>
        <table class="table table-condensed table-bordered">
            <tr>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.number"/></td>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.rent.type"/></td>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.rent.price"/></td>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.rent.city"/></td>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.rent.location"/></td>
                <td align="center" style="border-color: #dae5ff"><fmt:message key="table.rent.photo"/></td>
            </tr>
            <tr>
                <td align="center" style="vertical-align: middle; border-color: #dae5ff">${bike.id}</td>
                <td align="center" style="vertical-align: middle; border-color: #dae5ff">${bike.type}</td>
                <td align="center" style="vertical-align: middle; border-color: #dae5ff">${bike.pricePerHour}</td>
                <td align="center" style="vertical-align: middle; border-color: #dae5ff">${bike.city}</td>
                <td align="center" style="vertical-align: middle; border-color: #dae5ff">${bike.location}</td>
                <td align="center" style="vertical-align: middle; border-color: #dae5ff"><img src="data:image/jpeg;base64,${bike.picture}"/>
                </td>
            </tr>
        </table>
        <a href="../index.jsp" class="btn btn-default"><fmt:message key="continue"/></a>
    </div>
    <%@ include file="../include/footer.jsp" %>
</body>
</html>




