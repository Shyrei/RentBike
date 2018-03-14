<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@tag description="return the survey category for a page" pageEncoding="UTF-8" %>


<%--<jsp:directive.attribute name="var" type="java.lang.String" required="false" rtexprvalue="false" description="Optional return var name" />

&lt;%&ndash; <c:import url="/WEB-INF/Navigation.xml" var="importedDoc"/>
 &ndash;%&gt;
<x:parse doc="${importedDoc}" var="navigation"/>

<c:set var="pageUri" value="tcm:22-37835-4"/>--%>

<%-- <c:set var="surveyCat" scope="request"><x:out select="string($navigation//siteMapNode[@id=$pageUri][last()]/@surveySiteSection)"/></c:set> --%>

<c:set var="surveyCat" scope="request">zaebalo</c:set>
