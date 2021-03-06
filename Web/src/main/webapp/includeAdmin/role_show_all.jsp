<%--@elvariable id="rolesList" type="java.util.ArrayList"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/include/uselocale.jsp" %>
<html>
<head>
    <title><fmt:message key="title.show.all.roles.page"/></title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<%@ include file="/include/navbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-6 col-md-offset-6">
        <table class="MyTableOther">
            <thead>
            <tr>
                <td><fmt:message key="table.role.id"/></td>
                <td><fmt:message key="table.role"/></td>
            </tr>
            </thead>
            <c:forEach items="${rolesList}" varStatus="сounter">
                <tr>
                    <td>${rolesList[сounter.count-1].id}</td>
                    <td>${rolesList[сounter.count-1].role}</td>
                </tr>
            </c:forEach>
        </table>
        <a href="${pageContext.request.contextPath}/controller?action=show_admin_page" class="btn btn-default"><fmt:message key="continue"/></a>
    </div>
</div>
<%@ include file="/include/footer_admin.jsp" %>
</body>
</html>