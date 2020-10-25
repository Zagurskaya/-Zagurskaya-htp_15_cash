<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../../include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="../../include/menuadmin.jsp" %>
    <br>
    <H4><fmt:message key="page.editusers.label.description"/></H4>
    <%@ include file="/include/infomessage.jsp" %>
    <table class="table table-bordered table-hover table-striped">
        <thead>
        <tr>
            <th><fmt:message key="page.editusers.label.login"/></th>
            <th><fmt:message key="page.editusers.label.fullname"/></th>
            <th><fmt:message key="page.editusers.label.position"/></th>
            <th></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${users}" var="user">
            <form class="form-horizontal-${user.id}" actionType="do?command=Edit_Users" method="post">
                <tr>
                    <input id="id" name="id" type="hidden" readonly placeholder="" class="form-control input-md"
                           required="" value="${user.id}">
                    <td><c:out value="${user.login}"/></td>
                    <td><c:out value="${user.fullName}"/></td>
                    <td><c:out value="${user.role.value}"/></td>
                    <td>
                        <button id="update" name="update" class="btn btn-success">
                            <fmt:message key="page.editusers.button.update"/></button>
                    </td>
                    <td>
                        <c:if test="${user.id!='1'}">
                            <button id="delete" name="delete" class="btn btn-danger">
                                <fmt:message key="page.editusers.button.delete"/></button>
                        </c:if>
                    </td>
                </tr>
            </form>
        </c:forEach>
        </tbody>
    </table>
    <div align="center" style="text-align: center">
        <c:if test="${currentPage != 1}">
            <a href="do?command=Edit_Users&page=${currentPage - 1}"><fmt:message key="tabulation.previous"/></a>
        </c:if>

        <%--For displaying Page numbers.
        The when condition does not display a link for the current page--%>

        <c:forEach begin="1" end="${numberOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    ${i}
                </c:when>
                <c:otherwise>
                    <a href="do?command=Edit_Users&page=${i}">${i}</a>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <%--For displaying Next link --%>
        <c:if test="${currentPage lt numberOfPages}">
            <a href="do?command=Edit_Users&page=${currentPage + 1}"><fmt:message key="tabulation.next"/></a>
        </c:if>
    </div>
</div>
</body>
</html>


