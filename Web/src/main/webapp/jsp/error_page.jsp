<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@page isErrorPage="true" %>
<%@ include file="../include/uselocale.jsp" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <title><fmt:message key="title.error.page"/></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet" type="text/css" >
</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h1>Opps...</h1>
            <h3><c:out value="${message}"/></h3>
            <c:remove var="message" scope="session"/>
        </div>
    </div>
    <div class="row">
        <table class="MyTableOther">
            <tr>
                <td width="40%"><b>Error:</b></td>
                <td>${pageContext.exception}</td>
            </tr>
            <tr>
                <td><b>URI:</b></td>
                <td>${pageContext.errorData.requestURI}</td>
            </tr>
            <tr>
                <td><b>Status code:</b></td>
                <td>${pageContext.errorData.statusCode}</td>
            </tr>
            <tr>
                <td><b>Stack trace:</b></td>
                <td>
                    <c:forEach var="trace"
                               items="${pageContext.exception.stackTrace}" end="5">
                        <p>${trace}</p>
                    </c:forEach>
                </td>
            </tr>
        </table>
    </div>
</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>
