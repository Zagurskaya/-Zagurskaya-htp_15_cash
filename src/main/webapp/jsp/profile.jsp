<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="../include/menu.jsp" %>
    <br>
     <H4>Пользователь ${user.login} с правами
     <c:forEach items="${roles}" var="role">
     <c:if test="${user.roleId==role.id}" >${role.name} </c:if>
     </c:forEach></H4>
     <br>
    <form class="form-horizontal" action="do?command=Logout" method="post">
        <fieldset>

            <!-- Form Name -->
            <legend>Logout</legend>

            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="logoutButton"></label>
                <div class="col-md-4">
                    <button id="logoutButton" name="logoutButton" class="btn btn-primary">Logout</button>
                </div>
            </div>

        </fieldset>
    </form>
</div>
</body>
</html>

