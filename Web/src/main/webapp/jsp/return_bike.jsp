<%--@elvariable id="order" type="by.shyrei.rentbike.entity.Order"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/include/uselocale.jsp" %>
<html>
<head>
    <title><fmt:message key="title.return.bike.page"/></title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" >
</head>
<body>
<%@ include file="/include/navbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-6 col-md-offset-6">
        <div class="text-center">
            <h4><fmt:message key="message.ok.return"/></h4>
        </div>
        <table class="MyTableOther">
            <thead>
            <tr>
                <td><fmt:message key="table.number"/></td>
                <td><fmt:message key="table.order.start.rent"/></td>
                <td><fmt:message key="table.order.end.rent"/></td>
                <td><fmt:message key="table.order.value"/></td>
            </tr>
            </thead>
            <tr>
                <td>${order.id}</td>
                <td>${order.startRent}</td>
                <td>${order.endRent}</td>
                <td>${order.value}</td>
            </tr>
        </table>
        <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-default"><fmt:message key="continue"/></a>
    </div>
    <%@ include file="/include/footer.jsp" %>
</body>
</html>




