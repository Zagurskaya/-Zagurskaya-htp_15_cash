<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="/include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="/include/menucashnew.jsp" %>
    <br>
    <br>
    <H4><c:choose>
        <c:when test="${'201' == messageDuties.substring(0,3)}">
            <p><span style="color:#1c28ff">
                <fmt:message key="duties.message.201"/> ${messageDuties.substring(4,messageDuties.length())}
            </span></p>
        </c:when>
        <c:when test="${'202' == messageDuties.substring(0,3)}">
            <p><span style="color:#1c28ff">
                <fmt:message key="duties.message.202"/>${messageDuties.substring(4,messageDuties.length())}
            </span></p>
            </p>
        </c:when>
        <c:when test="${'203' == messageDuties.substring(0,3)}">
            <p><span style="color:#1c28ff">
                <fmt:message key="duties.message.203"/>${messageDuties.substring(4,messageDuties.length())}
            </span></p>
            </p>
        </c:when>
        <c:when test="${'204' == messageDuties.substring(0,3)}">
            <p><span style="color:#1c28ff"><fmt:message key="duties.message.204"/></span></p>
            </p>
        </c:when>
        <c:otherwise>${messageDuties}</c:otherwise>
    </c:choose>
    </H4>
    <table class="table table-bordered table-hover table-striped">
        <thead>
        <tr>
            <th><fmt:message key="page.duties.label.user"/></th>
            <th><fmt:message key="page.duties.label.open_time"/></th>
            <th><fmt:message key="page.duties.label.number"/></th>
            <th><fmt:message key="page.duties.label.is_close"/></th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td>
                <c:forEach items="${users}" var="users">
                    <c:if test="${duties.userId==users.id}">${users.login} </c:if>
                </c:forEach>
            </td>
            <td><c:out
                    value="${duties.localDateTime.toString().substring(0,10)} ${duties.localDateTime.toString().substring(11,16)}"/></td>
            <td><c:out value="${duties.number}"/></td>
            <td><c:out value="${duties.isClose}"/></td>
        </tr>
        </tbody>
    </table>
    <br>
    <br>
    <form class="form-horizontal" actionType="do?command=Duties" method="post">
        <fieldset>
            <div class="form-group">
                <div class="col-md-8">
                    <button id="open" name="open" class="btn btn-success">
                        <fmt:message key="page.duties.button.open"/></button>
                    <button id="close" name="close" class="btn btn-danger">
                        <fmt:message key="page.duties.button.close"/></button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>
