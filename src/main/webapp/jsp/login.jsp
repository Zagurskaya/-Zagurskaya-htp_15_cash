<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="../include/menu.jsp" %>

    User: ${user}
    <br>
    ${error}
    <br>

    <form class="form-horizontal" action="do?command=Login" method="post">
        <fieldset>
            <!-- Form Name -->
            <legend>Login</legend>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="login">Логин</label>
                <div class="col-md-4">
                    <input id="login" name="login" value="testLogin" type="text" placeholder="" class="form-control input-md" required="">

                </div>
            </div>

            <!-- Password input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="password">Пароль</label>
                <div class="col-md-4">
                    <input id="password" name="password" value="testPassword" type="password" placeholder="" class="form-control input-md" required="">

                </div>
            </div>

            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="loginButton"></label>
                <div class="col-md-4">
                    <button id="loginButton" name="loginButton" class="btn btn-primary">Войти</button>
                </div>
            </div>

        </fieldset>
    </form>
</div>
</body>
</html>

