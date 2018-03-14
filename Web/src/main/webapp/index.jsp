<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ taglib prefix="egr" tagdir="/WEB-INF/tags" %>


<html>
<head>
    <title>Index</title>
</head>
<body>

<h2>Hello world!</h2>


<%--This code doesn't work (((--%>
<%--<egr:surveyTag var2="${surveyCat}"/>--%>


<%--This code working --%>
<egr:surveyTag>
    <jsp:attribute name="doSurveyCat">
    ${surveyCat}
    </jsp:attribute>
</egr:surveyTag>


</body>
</html>