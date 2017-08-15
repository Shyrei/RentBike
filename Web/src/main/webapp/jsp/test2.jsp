<%--@elvariable id="bikesList" type="java.util.ArrayList"--%>
<%--@elvariable id="stationsList" type="java.util.ArrayList"--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../include/uselocale.jsp" %>
<html>
<head>
    <title>Test ajax</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <script type="text/javascript" src="../js/jquery-1.6.2.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#bttTest').click(function () {
                var station = $('#station').val();
                $.ajax({
                    type: 'POST',
                    data: {station: station},
                    url: '/AjaxController',
                    success: function (result) {
                        $('#result1').html(result);
                    }
                });
            });
        });
        /* $(document).ready(function () {
         $('#updateBike').submit(function () {
         var stationId = $('#stationId').val();
         $.ajax({
         url: '/AjaxController',
         type: 'POST',
         dataType: 'json',
         data: $('updateBike').serialize(),
         success: function (data) {
         $('#result1').html(data.id + data.type + data.location);
         }
         });
         });
         });*/

    </script>
</head>
<body>
<%@ include file="../include/navbar.jsp" %>

<div class="container-fluid">
    <%-- <div class="grid">--%>
    <form>
        <div class="form-group">
            <label for="station" class="col-sm-3 control-label"><fmt:message key="add.bike.form.stations"/></label>
            <div class="col-sm-9">
                <select class="form-control" id="station" name="stationId">
                    <c:forEach items="${stationsList}" var="station">
                        <option value="${station.id}">${station.id}, ${station.city}, ${station.location}</option>
                    </c:forEach>
                </select>
            </div>
        </div>
        <input type="submit" value="hello" id="bttTest">


    </form>

    <span id="result1"></span>

    <%--  <form id="list">
      <div class="grid-item grid-item3">
          <table class="MyTable">
              <thead>
              <tr>
                  <td><fmt:message key="table.number"/></td>
                  <td><fmt:message key="table.rent.type"/></td>
                  <td><fmt:message key="table.rent.price"/></td>
                  <td><fmt:message key="table.rent.city"/></td>
                  <td><fmt:message key="table.rent.location"/></td>
                  <td><fmt:message key="table.rent.photo"/></td>
              </tr>
              </thead>
              <c:forEach items="${bikesList}" varStatus="сounter" end="6">
                  <tr>
                      <td>${bikesList[сounter.count-1].id}</td>
                      <td>${bikesList[сounter.count-1].type}</td>
                      <td>${bikesList[сounter.count-1].pricePerHour}</td>
                      <td>${bikesList[сounter.count-1].city}</td>
                      <td>${bikesList[сounter.count-1].location}</td>
                      <td><img src="data:image/jpeg;base64,${bikesList[сounter.count-1].picture}"/></td>
                  </tr>
              </c:forEach>
          </table>
          </div>
      </form>
  </div>--%>
</div>


<%@ include file="../include/footer_admin.jsp" %>
</body>
</html>



