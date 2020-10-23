<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<%@ include file="/include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="/include/menu.jsp" %>
    <c:choose>
        <c:when test="${error.substring(0,3)!=null}">
            <%@ include file="/jsp/errors/errormessage.jsp" %>
        </c:when>
        <c:otherwise>
            <p>ERROR: ${error}</p>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>


