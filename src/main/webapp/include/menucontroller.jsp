<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<c:choose>
    <c:when test="${cookie.local.value==null}">
        <fmt:setLocale value="ru"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${cookie.local.value}"/>
    </c:otherwise>
</c:choose>
<fmt:setBundle basename="properties.pagecontent"/>
<div class="menu-nav">
    <ul>
        <li class="first"><a href="do?command=Load_Rate_NB"><fmt:message key="controller.ratenb"/></a></li>
        <li><a href="do?command=Load_Rate_CB"><fmt:message key="controller.ratecb"/></a></li>
        <li><a href="do?command=Unload_Entries"><fmt:message key="controller.entries"/></a></li>
        <li><a href="do?command=Send_Email"><fmt:message key="controller.email"/></a></li>
        <li class="last"><a href="."><fmt:message key="header.exit"/></a></li>
        <li style="float: right;"><a href="do?command=Locale"><fmt:message key="header.localEn"/></a></li>
        <li style="float: right;"><a href="do?command=Locale"><fmt:message key="header.localRu"/></a></li>
    </ul>
</div>
