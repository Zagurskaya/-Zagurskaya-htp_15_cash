<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="/include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="/include/menucashnew.jsp" %>
    <br>

    <H4><fmt:message key="page.useroperations.title"/></H4>
    <table class="table table-bordered table-hover table-striped">
        <thead>
        <tr>
            <th><fmt:message key="page.useroperations.label.number"/></th>
            <th><fmt:message key="page.useroperations.label.operation"/></th>
            <th><fmt:message key="page.useroperations.label.sum"/></th>
            <th><fmt:message key="page.useroperations.label.currency"/></th>
            <th><fmt:message key="page.useroperations.label.time"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${userOperations}" var="operation">
            <tr>
                <td><c:out value="${operation.id}"/></td>
                <td>
                    <c:forEach items="${sprOperations}" var="sprOperation">
                        <c:if test="${operation.operationId==sprOperation.id}">
                            <c:if test="${cookie.local.value==null || cookie.local.value=='ru'}">${sprOperation.nameRU}</c:if>
                            <c:if test="${cookie.local.value=='en'}">${sprOperation.nameEN}</c:if>
                        </c:if>
                    </c:forEach>
                </td>
                <td><c:out value="${operation.sum}"/></td>
                <td>
                    <c:forEach items="${currencies}" var="currency">
                        <c:if test="${operation.currencyId==currency.id}">
                            <c:if test="${cookie.local.value==null || cookie.local.value=='ru'}">${currency.nameRU}</c:if>
                            <c:if test="${cookie.local.value=='en'}">${currency.nameEN}</c:if></c:if>
                    </c:forEach>
                </td>
                <td><c:out value="${operation.localDateTime.toString().substring(0,10)} ${operation.localDateTime.toString().substring(11,19)}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div align="center" style="text-align: center">
        <c:if test="${currentPage != 1}">
            <a href="do?command=User_Operations&page=${currentPage - 1}"><fmt:message key="tabulation.previous"/></a>
        </c:if>

        <%--For displaying Page numbers.
        The when condition does not display a link for the current page--%>

        <c:forEach begin="1" end="${numberOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    ${i}
                </c:when>
                <c:otherwise>
                    <a href="do?command=User_Operations&page=${i}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <%--For displaying Next link --%>
        <c:if test="${currentPage lt numberOfPages}">
            <a href="do?command=User_Operations&page=${currentPage + 1}"><fmt:message key="tabulation.next"/></a>
        </c:if>
    </div>

</div>
</body>
</html>
