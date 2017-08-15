<%--@elvariable id="typesList" type="java.util.ArrayList"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <title><fmt:message key="title.show.all.types.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet" type="text/css" >
</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-6 col-md-offset-6">
        <table class="MyTableOther">
            <thead>
            <tr>
                <td><fmt:message key="table.type.id"/></td>
                <td><fmt:message key="table.type.type"/></td>
                <td><fmt:message key="table.type.price"/></td>
                <td><fmt:message key="table.type.photo"/></td>
            </tr>
            </thead>
            <c:forEach items="${typesList}" varStatus="сounter">
                <tr>
                    <td>${typesList[сounter.count-1].id}</td>
                    <td>${typesList[сounter.count-1].type}</td>
                    <td>${typesList[сounter.count-1].price}</td>
                    <td><img src="data:image/jpeg;base64,${typesList[сounter.count-1].image}"/>
                </tr>
            </c:forEach>
        </table>
        <a href="/controller?action=show_admin_page" class="btn btn-default"><fmt:message key="continue"/></a>
    </div>
</div>
<%@ include file="../include/footer_admin.jsp" %>
</body>
</html>



