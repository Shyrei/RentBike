<%--@elvariable id="userOne" type="by.shyrei.rentbike.entity.User"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <title><fmt:message key="title.main.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet" type="text/css" >
</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-6 col-md-offset-6">
        <c:if test="${not empty emptyUser}">
        <div class="text-center">
            <h4><fmt:message key="message.empty.user"/></h4>
        </div>
        </c:if>
        <c:if test="${not empty userOne}">
        <table class="MyTableOther">
            <thead>
            <tr>
                <td><fmt:message key="table.user.id"/></td>
                <td><fmt:message key="table.user.first.name"/></td>
                <td><fmt:message key="table.user.last.name"/></td>
                <td><fmt:message key="table.user.login"/></td>
                <td><fmt:message key="table.user.password"/></td>
                <td><fmt:message key="table.user.balance"/></td>
                <td><fmt:message key="table.user.role"/></td>
            </tr>
            </thead>
            <tr>
                <td>${userOne.id}</td>
                <td>${userOne.firstName}</td>
                <td>${userOne.lastName}</td>
                <td>${userOne.login}</td>
                <td>${userOne.password}</td>
                <td>${userOne.balance}</td>
                <td>${userOne.roleId}</td>
            </tr>
        </table>
        </c:if>
        <a href="/controller?action=show_admin_page" class="btn btn-default"><fmt:message key="continue"/></a>
    </div>
    <%@ include file="../include/footer_admin.jsp" %>
</body>
</html>

