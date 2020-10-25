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
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <c:choose>
                <c:when test="${user!=null}">
                    <a class="nav-item nav-link" href="do?command=Edit_Users"><fmt:message key="admin.users"/></a>
                    <a class="nav-item nav-link" href="do?command=Create_User"><fmt:message key="admin.addUser"/></a>
                </c:when>
            </c:choose>
        </div>
    </div>
    <a class="nav-item nav-link" href="do?command=Locale"><fmt:message key="header.localEn"/></a>
    <a class="nav-item nav-link" href="do?command=Locale"><fmt:message key="header.localRu"/></a>
    <a class="navbar-brand" href="."><fmt:message key="header.exit"/></a>

</nav>

