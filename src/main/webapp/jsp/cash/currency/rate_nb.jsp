<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="/include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="/include/menucashnew.jsp" %>
    <br>
    <H4><fmt:message key="page.ratenb.title"/></H4>
    <table class="table table-bordered table-hover table-striped">
        <thead>
        <tr>
            <th><fmt:message key="page.ratenb.label.name"/></th>
            <th><fmt:message key="page.ratenb.label.data"/></th>
            <th><fmt:message key="page.ratenb.label.rate"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${rateNB}" var="rateNB">
            <tr>
                <td>
                    <c:forEach items="${currencies}" var="currency">
                        <c:if test="${rateNB.currencyId==currency.id && (cookie.local.value==null || cookie.local.value=='ru')}">${currency.nameRU} </c:if>
                        <c:if test="${rateNB.currencyId==currency.id && cookie.local.value=='en'}">${currency.nameEN} </c:if>
                    </c:forEach>
                </td>
                <td><c:out value="${rateNB.date}"/></td>
                <td><c:out value="${rateNB.sum}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div align="center" style="text-align: center">
        <c:if test="${currentPage != 1}">
            <a href="do?command=Rate_NB&page=${currentPage - 1}"><fmt:message key="tabulation.previous"/></a>
        </c:if>

        <%--For displaying Page numbers.
        The when condition does not display a link for the current page--%>

        <c:forEach begin="1" end="${numberOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    ${i}
                </c:when>
                <c:otherwise>
                    <a href="do?command=Rate_NB&page=${i}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <%--For displaying Next link --%>
        <c:if test="${currentPage lt numberOfPages}">
            <a href="do?command=Rate_NB&page=${currentPage + 1}"><fmt:message key="tabulation.next"/></a>
        </c:if>
    </div>
</div>
</body>
</html>
