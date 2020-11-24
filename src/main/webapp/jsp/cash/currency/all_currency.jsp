<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="/include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="/include/menucashnew.jsp" %>
    <br>
    <H4><fmt:message key="page.allcurrency.title"/></H4>
    <table class="table table-bordered table-hover table-striped">
        <thead>
        <tr>
            <th><fmt:message key="page.allcurrency.label.number"/></th>
            <th><fmt:message key="page.allcurrency.label.iso"/></th>
            <th><fmt:message key="page.allcurrency.label.name"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${currencies}" var="currency">
            <tr>
                <td><c:out value="${currency.id}"/></td>
                <td><c:out value="${currency.iso}"/></td>
                <c:if test="${cookie.local.value==null || cookie.local.value=='ru'}">
                    <td><c:out value="${currency.nameRU}"/></td>
                </c:if>
                <c:if test="${cookie.local.value=='en'}">
                    <td><c:out value="${currency.nameEN}"/></td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div align="center" style="text-align: center">
        <c:if test="${currentPage != 1}">
            <a href="do?command=All_Currency&page=${currentPage - 1}">Previous</a>
        </c:if>

        <%--For displaying Page numbers.
        The when condition does not display a link for the current page--%>

        <c:forEach begin="1" end="${numberOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    ${i}
                </c:when>
                <c:otherwise>
                    <a href="do?command=All_Currency&page=${i}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <%--For displaying Next link --%>
        <c:if test="${currentPage lt numberOfPages}">
            <a href="do?command=All_Currency&page=${currentPage + 1}">Next</a>
        </c:if>
    </div>
</body>
</html>
