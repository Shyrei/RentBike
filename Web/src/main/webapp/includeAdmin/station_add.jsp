<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="/include/uselocale.jsp" %>
<html>
<head>
    <title><fmt:message key="title.add.station.page"/></title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-8 col-md-offset-2">
            <form action="${pageContext.request.contextPath}/controller" method="post" enctype="multipart/form-data" class="form-horizontal">
                <input type="hidden" name="action" value="add_station">
                <div class="form-group">
                    <label for="city" class="col-sm-4 control-label"><fmt:message key="add.station.form.city"/></label>
                    <div class="col-sm-8">
                        <input type="text" id="city" name="city" class="form-control" placeholder="<fmt:message key="add.station.form.city.placeholder"/>"
                               maxlength="10" required pattern="([А-Я]{1}[а-яё]{0,9})">
                        <b style="color: darkgray; font-size: 10px"><fmt:message key="validation.stations.city"/></b>
                    </div>
                </div>
                <div class="form-group">
                    <label for="location" class="col-sm-4 control-label"><fmt:message key="add.station.form.location"/></label>
                    <div class="col-sm-8">
                        <input type="text" id="location" name="location" class="form-control" placeholder="<fmt:message key="add.station.form.location.placeholder"/>"
                               maxlength="15" required pattern="([а-яА-Я]|[,]|[0-9]|[ ]){0,15}">
                        <b style="color: darkgray; font-size: 10px"><fmt:message key="validation.stations.location"/></b>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
                        <button type="submit" class="btn btn-primary"><fmt:message key="add.station.form.submit"/></button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<%@ include file="/include/footer_admin.jsp" %>
</body>
</html>


