<%--@elvariable id="stationsList" type="java.util.ArrayList"--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <title>Ajax2 TEST</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="../js/jquery-1.6.2.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#station').change(function () {
                var station = $('#station').val();
                $('#bike').attr('disabled', true).html('<option>загрузка...</option>');
                function myAjax() {
                $.ajax({
                        type: 'GET',
                        data: {station: station},
                        url: 'ajax',
                        success: function (result) {
                            var bikeList = $.parseJSON(result);
                            var s = '';
                            for (var i = 0; i < bikeList.length; i++) {
                                s += '<option value="' + bikeList[i].id + '">' + '<fmt:message key="rent.bike.form.id"/> ' + bikeList[i].id + ', <fmt:message key="rent.bike.form.type"/> ' + bikeList[i].type + ', <fmt:message key="rent.bike.form.price"/>  ' + bikeList[i].pricePerHour + ', <fmt:message key="rent.bike.form.city"/> ' + bikeList[i].city + ', <fmt:message key="rent.bike.form.location"/> ' + bikeList[i].location + '</option>';
                            }
                            $('#bike').attr('disabled', false);
                            document.getElementById('bike').innerHTML = s;
                        }
                    });
                }
                setTimeout(myAjax, 1000);
            });
        });
    </script>
</head>
<body>
<%@ include file="../include/navbar.jsp" %>
<div class="container-fluid">
    <div class="col-md-6 col-md-offset-6">
        <form action="/controller" method="post" class="form-horizontal">
            <input type="hidden" name="action" value="rent_bike">
            <div class="form-group">
                <label for="station" class="col-sm-3 col-sm-offset-1 control-label"><fmt:message key="show.bike.form.station"/></label>
                <div class="col-sm-8">
                    <select class="form-control" id="station" name="stationId">
                        <c:forEach items="${stationsList}" var="station">
                            <option value="${station.id}">${station.id}, ${station.city}, ${station.location}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label for="bike" class="col-sm-3 col-sm-offset-1 control-label"><fmt:message key="show.bike.form.bike"/></label>
                <div class="col-sm-8">
                    <select class="form-control" id="bike" name="bikeId" disabled="disabled">
                        <option value=""><fmt:message key="show.bike.form.station.placeholder"/></option>
                    </select>
                </div>
            </div>
                <div class="form-group">
                    <div class="col-sm-offset-4 col-sm-8">
                        <button type="submit" class="btn btn-default"><fmt:message key="rent.bike.form.submit" /></button>
                    </div>
                </div>
            </form>
    </div>
</div>
<%@ include file="../include/footer.jsp" %>
</body>
</html>



