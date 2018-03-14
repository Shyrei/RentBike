<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld"%>
<%@ taglib prefix="egr" tagdir="/WEB-INF/tags"%>


<html>
<head>
    <title>Index</title>
</head>
<body>

<h2>Hello world!</h2>

<egr:surveyTag>
    <jsp:attribute name="doSurveyCat">
    <h2>${surveyCat}</h2>
    </jsp:attribute>
</egr:surveyTag>

<%--<ctg:survey>
</ctg:survey>


<h2>${ibal}</h2>

<h2>${doSurveyCat}</h2>--%>


</body>
</html>