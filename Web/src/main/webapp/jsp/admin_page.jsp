<%--@elvariable id="usersList" type="java.util.ArrayList"--%>
<%--@elvariable id="bikesList" type="java.util.ArrayList"--%>
<%--@elvariable id="ordersList" type="java.util.ArrayList"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <title><fmt:message key="title.admin.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet" type="text/css" >
    <style>
        body {
            background: url(../images/fon1.jpg);
        }
    </style>
</head>
<body>
<%@include file="../include/navbar_admin.jsp" %>

<div class="container-fluid">
    <div class="grid">
        <div class="grid-item grid-item1">
            <table class="MyTable">
                <thead>
                <tr>
                    <td><fmt:message key="table.user.id"/></td>
                    <td><fmt:message key="table.user.first.name"/></td>
                    <td><fmt:message key="table.user.last.name"/></td>
                    <td><fmt:message key="table.user.login"/></td>
                    <td><fmt:message key="table.user.balance"/></td>
                    <td><fmt:message key="table.user.role"/></td>
                </tr>
                </thead>
                <c:forEach items="${usersList}" varStatus="сounter">
                    <tr>
                        <td>${usersList[сounter.count-1].id}</td>
                        <td>${usersList[сounter.count-1].firstName}</td>
                        <td>${usersList[сounter.count-1].lastName}</td>
                        <td>${usersList[сounter.count-1].login}</td>
                        <td>${usersList[сounter.count-1].balance}</td>
                        <td>${usersList[сounter.count-1].roleId}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="grid-item grid-item2">
            <table class="MyTable">
                <thead>
                <tr>
                    <td><fmt:message key="table.number"/></td>
                    <td><fmt:message key="table.order.start.rent"/></td>
                    <td><fmt:message key="table.order.end.rent"/></td>
                    <td><fmt:message key="table.order.value"/></td>
                </tr>
                </thead>
                <c:forEach items="${ordersList}" varStatus="сounter">
                    <tr>
                        <td>${ordersList[сounter.count-1].id}</td>
                        <td>${ordersList[сounter.count-1].startRent}</td>
                        <td>${ordersList[сounter.count-1].endRent}</td>
                        <td>${ordersList[сounter.count-1].value}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div class="grid-item grid-item3">
            <table class="MyTable">
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
                <c:forEach items="${bikesList}" varStatus="сounter" end="6">
                    <tr>
                        <td>${bikesList[сounter.count-1].id}</td>
                        <td>${bikesList[сounter.count-1].type}</td>
                        <td>${bikesList[сounter.count-1].pricePerHour}</td>
                        <td>${bikesList[сounter.count-1].city}</td>
                        <td>${bikesList[сounter.count-1].location}</td>
                        <td><img src="data:image/jpeg;base64,${bikesList[сounter.count-1].picture}"/></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>
<%@ include file="../include/footer_admin.jsp" %>
</body>
</html>

