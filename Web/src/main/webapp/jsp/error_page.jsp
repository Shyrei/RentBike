<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../include/uselocale.jsp" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <title><fmt:message key="title.error.page"/></title>
</head>
<body>
<%@ include file="../include/navbar.jsp"%>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <p><c:out value="${message}"/></p>
            <c:remove var="message" scope="session"/>
        </div>
    </div>
</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>
