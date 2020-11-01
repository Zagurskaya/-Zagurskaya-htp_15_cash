<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="/include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="/include/menucashnew.jsp" %>
    <br>
    <br>
    <br>
    <br>
    <H4><fmt:message key="page.balance.title"/></H4>
    <table class="table table-bordered table-hover table-striped">
        <thead>
        <tr>
            <th><fmt:message key="page.balance.label.number"/></th>
            <th><fmt:message key="page.balance.label.received"/></th>
            <th><fmt:message key="page.balance.label.coming"/></th>
            <th><fmt:message key="page.balance.label.spending"/></th>
            <th><fmt:message key="page.balance.label.transmitted"/></th>
            <th><fmt:message key="page.balance.label.balance"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${balanceList}" var="balance">
            <tr>
                <td>
                    <c:forEach items="${currencies}" var="currency">
                        <c:if test="${balance.currencyId==currency.id && (cookie.local.value==null || cookie.local.value=='ru')}">${currency.nameRU} </c:if>
                        <c:if test="${balance.currencyId==currency.id && cookie.local.value=='en'}">${currency.nameEN} </c:if>
                    </c:forEach>
                </td>
                <td><c:out value="${balance.received}"/></td>
                <td><c:out value="${balance.coming}"/></td>
                <td><c:out value="${balance.spending}"/></td>
                <td><c:out value="${balance.transmitted}"/></td>
                <td><c:out value="${balance.balance}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
