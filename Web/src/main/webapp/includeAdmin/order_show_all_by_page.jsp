<%--@elvariable id="ordersList" type="java.util.ArrayList"--%>
<%--@elvariable id="emptyOrder" type="java.lang.String"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <title><fmt:message key="title.show.all.orders.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet" type="text/css" >
</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-6 col-md-offset-6">
        <div class="row">
            <c:if test="${not empty ordersList}">
            <table class="MyTableOther">
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
                <nav aria-label="...">
                    <ul class="pager">
                        <li class="previous${leftPageClass}"><a href="${leftPage}"><fmt:message key="page.previous"/></a></li>
                        <li class="next${rightPageClass}"><a href="${rightPage}"><fmt:message key="page.next"/></a></li>
                    </ul>
                </nav>
        </div>
        </c:if>
        <c:if test="${not empty emptyOrder}">
            <div class="text-center">
                <h3>
                    <fmt:message key="message.empty.order"/>
                </h3>
            </div>
        </c:if>
    </div>
</div>
<%@ include file="../include/footer_admin.jsp" %>
</body>
</html>



