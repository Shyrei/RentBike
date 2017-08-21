<%--@elvariable id="stationsList" type="java.util.ArrayList"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <title><fmt:message key="title.show.bikes.on.station" /></title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="../js/jquery-1.6.2.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#station').change(function () {
                var station = $('#station').val();
                $.ajax({
                    type: 'GET',
                    data: {station: station},
                    url: 'ajax',
                    success: function (result) {
                        var bikeList = $.parseJSON(result);
                        var s = '';
                        for (var i = 0; i < bikeList.length; i++) {
                            s += '<tr>' + '<td>' + bikeList[i].id + '</td>' + '<td>' + bikeList[i].type + '</td>' + '<td>' + bikeList[i].pricePerHour + '</td>' + '<td>' + bikeList[i].city + '</td>' + '<td>' + bikeList[i].location + '</td>' + '<td>' + '<img src="data:image/jpeg;base64,' + bikeList[i].picture + '"/>' + '</td>' + '</tr>';
                        }
                        s = '<table class="MyTableMain"><thead><tr><td><fmt:message key="table.number"/></td><td><fmt:message key="table.rent.type"/></td><td><fmt:message key="table.rent.price"/></td><td><fmt:message key="table.rent.city"/></td><td><fmt:message key="table.rent.location"/></td><td><fmt:message key="table.rent.photo"/></td></tr></thead>' + s;
                        document.getElementById('result').innerHTML = s;
                    }
                });
            });
        });
    </script>
</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-6 col-md-offset-6">

        <form class="form-horizontal">
            <div class="form-group">
                <label for="station" class="col-sm-3 col-sm-offset-5 control-label"><fmt:message key="show.bike.form.station"/></label>
                <div class="col-sm-4">
                    <select class="form-control" id="station" name="stationId">
                        <c:forEach items="${stationsList}" var="station">
                            <option value="${station.id}">${station.id}, ${station.city}, ${station.location}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
        </form>
        <span id="result"></span>
    </div>
    <%@ include file="../include/footer_admin.jsp" %>
</body>
</html>



