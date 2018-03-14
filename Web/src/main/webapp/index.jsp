<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld"%>

<html>
<head>
    <title>Index</title>
</head>
<body>

<h2>Hello world!</h2>

<ctg:survey>

</ctg:survey>


<c:import url="/WEB-INF/Navigation.xml" var="importedDoc"/>
<x:parse doc="${importedDoc}" var="navigation"/>
<c:set var="pageUri" value="tcm:22-37835-4"/>
<c:set var="surveyCat22" scope="request"><x:out select="string($navigation//siteMapNode[@id=$pageUri][last()]/@surveySiteSection)"/></c:set>

<h1>${surveyCat22}</h1>


<h2>${ibal}</h2>
<h2>${ibal2}</h2>


</body>
</html>