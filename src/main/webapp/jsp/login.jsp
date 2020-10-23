<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="../include/menu.jsp" %>
    <br>
    <%@ include file="errors/errormessage.jsp" %>
    <br>
    <form class="form-horizontal" actionType="do?command=Login" method="post">
        <fieldset>
            <legend><fmt:message key="page.login.label.description"/></legend>

            <div class="form-group">
                <label class="col-md-4 control-label" for="login"><fmt:message key="page.login.label.login"/></label>
                <div class="col-md-4">
                    <input id="login" name="login" value="testLogin" type="text" placeholder=""
                           class="form-control input-md" required="">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="password"><fmt:message
                        key="page.login.label.password"/></label>
                <div class="col-md-4">
                    <input id="password" name="password" value="testPassword" type="password" placeholder=""
                           class="form-control input-md" required="">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="loginButton"></label>
                <div class="col-md-4">
                    <button id="loginButton" name="loginButton" class="btn btn-primary"><fmt:message
                            key="page.login.button.login"/></button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>

