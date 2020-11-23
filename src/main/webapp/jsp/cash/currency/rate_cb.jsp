<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="/include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="/include/menucashnew.jsp" %>
    <br>
    <H4><fmt:message key="page.ratecb.title"/></H4>
    <table class="table table-bordered table-hover table-striped">
        <thead>
        <tr>
            <th><fmt:message key="page.ratecb.label.coming"/></th>
            <th><fmt:message key="page.ratecb.label.spending"/></th>
            <th><fmt:message key="page.ratecb.label.timestamp"/></th>
            <th><fmt:message key="page.ratecb.label.sum"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${rateCB}" var="rateCB">
            <tr>
                <td>
                    <c:forEach items="${currencies}" var="currency">
                        <c:if test="${rateCB.coming==currency.id && (cookie.local.value==null || cookie.local.value=='ru')}">${currency.nameRU} </c:if>
                        <c:if test="${rateCB.coming==currency.id && cookie.local.value=='en'}">${currency.nameEN} </c:if>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach items="${currencies}" var="currency">
                        <c:if test="${rateCB.spending==currency.id && (cookie.local.value==null || cookie.local.value=='ru')}">${currency.nameRU} </c:if>
                        <c:if test="${rateCB.spending==currency.id && cookie.local.value=='en'}">${currency.nameEN} </c:if>
                    </c:forEach>
                </td>
                <td><c:out value="${rateCB.localDateTime.toString().substring(0,10)} ${rateCB.localDateTime.toString().substring(11,16)}"/></td>
                <td><c:out value="${rateCB.sum}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div align="center" style="text-align: center">
        <c:if test="${currentPage != 1}">
            <a href="do?command=Rate_CB&page=${currentPage - 1}"><fmt:message key="tabulation.previous"/></a>
        </c:if>

        <%--For displaying Page numbers.
        The when condition does not display a link for the current page--%>

        <c:forEach begin="1" end="${numberOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    ${i}
                </c:when>
                <c:otherwise>
                    <a href="do?command=Rate_CB&page=${i}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <%--For displaying Next link --%>
        <c:if test="${currentPage lt numberOfPages}">
            <a href="do?command=Rate_CB&page=${currentPage + 1}"><fmt:message key="tabulation.next"/></a>
        </c:if>
    </div>
</div>
</body>
</html>
