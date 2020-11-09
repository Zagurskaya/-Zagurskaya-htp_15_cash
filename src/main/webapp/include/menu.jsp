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
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="."><fmt:message key="header.home"/></a>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <c:choose>
                <c:when test="${user!=null}">
                    <a class="nav-item nav-link" href="do?command=Profile"><fmt:message key="header.profile"/></a>
                    <c:choose>
                        <c:when test="${user.role.value=='admin'}">
                            <a class="nav-item nav-link" href="do?command=Admin"><fmt:message key="header.admin"/></a>
                        </c:when>
                        <c:when test="${user.role.value=='kassir'}">
                            <a class="nav-item nav-link" href="do?command=Main"><fmt:message key="header.cash"/></a>
                        </c:when>
                        <c:when test="${user.role.value=='controller'}">
                            <a class="nav-item nav-link" href="do?command=Controller"><fmt:message key="header.controller"/></a>
                        </c:when>
                    </c:choose>
                </c:when>
                <c:otherwise>
                    <a class="nav-item nav-link" href="do?command=Login"><fmt:message key="header.login"/></a>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <a class="nav-item nav-link" href="do?command=Locale"><fmt:message key="header.localEn"/></a>
    <a class="nav-item nav-link" href="do?command=Locale"><fmt:message key="header.localRu"/></a>
</nav>

