<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<fmt:setBundle basename="properties.pagecontent"/>
<c:choose>
    <c:when test="${cookie.local.value==null}">
        <fmt:setLocale value="ru"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${cookie.local.value}"/>
    </c:otherwise>
</c:choose>
<c:choose>
    <c:when test="${'100' == error.substring(0,3)}">
        <p><span style="color:red">${error.substring(4,error.length())}</span></p>
    </c:when>
    <c:when test="${'101' == error.substring(0,3)}">
        <p><span style="color:red"><fmt:message key="error.message.101"/></span></p>
        <p><span style="color:red">${error.substring(4,error.length())}</span></p>
    </c:when>
    <c:when test="${'102' == error.substring(0,3)}">
        <p><span style="color:red"><fmt:message
                key="error.message.102"/> ${error.substring(4,error.length())}</span>
        </p>
    </c:when>
    <c:when test="${'103' == error.substring(0,3)}">
        <p><span style="color:red"><fmt:message key="error.message.103"/></span></p>
    </c:when>
    <c:when test="${'104' == error.substring(0,3)}">
        <p><span style="color:red"><fmt:message key="error.message.104"/></span></p>
    </c:when>
    <c:when test="${'108' == error.substring(0,3)}">
        <p><span style="color:red"><fmt:message key="error.message.108"/></span></p>
    </c:when>
    <c:otherwise>${error}</c:otherwise>
</c:choose>
