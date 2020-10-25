<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="../include/menu.jsp" %>
    <br>
    <H4><fmt:message key="page.profile.user"/> <span style="color:#2f3dff">${user.login}</span>
        <fmt:message key="page.profile.role"/> <span style="color:#2f3dff">${user.role}</span>
    </H4>
    <br>
    <form class="form-horizontal" actionType="do?command=Logout" method="post">
        <fieldset>
            <button id="logoutButton" name="logoutButton" class="btn btn-primary">
                <fmt:message key="page.profile.button.logout"/>
            </button>
        </fieldset>
    </form>
</div>
</body>
</html>

