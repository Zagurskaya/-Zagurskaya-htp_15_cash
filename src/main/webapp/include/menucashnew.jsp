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
        <li class="first"><a href="do?command=Main"><fmt:message key="header.home"/></a></li>
        <li><a href="do?command=Duties"><fmt:message key="cash.duties"/></a></li>
        <li><a href="#" style="padding-right:43px"><fmt:message key="cash.currency"/></a>
            <ul>
                <li><a href="do?command=All_Currency"><fmt:message key="cash.currencyDirectory"/></a></li>
                <li><a href="do?command=Rate_NB"><fmt:message key="cash.rateNB"/></a></li>
                <li><a href="do?command=Rate_CB"><fmt:message key="cash.rateCB"/></a></li>
            </ul>
        </li>
        <li><a href="#" style="padding-right:26px"><fmt:message key="cash.operations"/></a>
            <ul>
                <li><a href="do?command=Payment"><fmt:message key="cash.payments"/></a></li>
                <li><a href="do?command=Balance"><fmt:message key="cash.balance"/></a></li>
                <li><a href="do?command=User_Operations"><fmt:message key="cash.userOperations"/></a></li>
            </ul>
        </li>
        <li><a href="#" style="padding-right:18px"><fmt:message key="cash.reporting"/></a>
            <ul>
                <li><a href="do?command=Entries"><fmt:message key="cash.entries"/></a></li>
            </ul>
        </li>
        <li><a href="#" style="padding-right:44px"><fmt:message key="cash.reports"/></a>
            <ul>
                <li><a href="do?command=Report1"><fmt:message key="cash.report1"/></a></li>
                <li><a href="do?command=Report2"><fmt:message key="cash.report2"/></a></li>
                <li><a href="do?command=Report3"><fmt:message key="cash.report3"/></a></li>
            </ul>
        </li>
        <li class="last"><a href="."><fmt:message key="header.exit"/></a></li>
        <li style="float: right;"><a href="do?command=Locale"><fmt:message key="header.localEn"/></a></li>
        <li style="float: right;"><a href="do?command=Locale"><fmt:message key="header.localRu"/></a></li>
    </ul>
</div>
