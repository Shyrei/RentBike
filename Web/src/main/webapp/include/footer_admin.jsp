<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<br>
<%--<nav class="navbar navbar-default navbar-fixed-bottom" style="background: #8381eb" >--%>
<nav class="navbar navbar-default navbar-fixed-bottom" style="background: #dae5ff" >
    <div class="container-fluid">
        <form class="navbar-form navbar-right" action="/controller">
                <input type="hidden" name="action" value="show_bikes_page">
                <button type="submit" class="btn btn-default"><fmt:message key="footer.admin"/></button>

        </form>
    </div>
</nav>
<script src="../js/jquery-3.2.0.min.js"></script>
<script src="../js/bootstrap.min.js"></script>

