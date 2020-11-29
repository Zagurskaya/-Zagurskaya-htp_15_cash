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
<c:choose>
    <c:when test="${'105'== message.substring(0,3)}">
        <p><span style="color:#08ff11">
                <fmt:message key="error.message.105"/> ${message.substring(4,message.length())}
            </span></p>
    </c:when>
    <c:when test="${'106' == message.substring(0,3)}">
        <p><span style="color:#08ff11">
                <fmt:message key="error.message.106"/> ${message.substring(4,message.length())}
            </span></p>
    </c:when>
    <c:when test="${'107' == message.substring(0,3)}">
        <p><span style="color:#08ff11">
                <fmt:message key="error.message.107"/> ${message.substring(4,message.length())}
            </span></p>
    </c:when>
    <c:when test="${'109' == message.substring(0,3)}">
        <p><span style="color:#08ff11"><fmt:message key="error.message.109"/></span></p>
    </c:when>
    <c:when test="${'110' == message.substring(0,3)}">
        <p><span style="color:#08ff11"><fmt:message key="error.message.110"/></span></p>
    </c:when>
    <c:otherwise><p><span style="color:#08ff11">${message}</span></p></c:otherwise>
</c:choose>


