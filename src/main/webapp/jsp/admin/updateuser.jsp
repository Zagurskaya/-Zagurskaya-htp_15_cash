<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<%@ include file="/include/head.jsp" %>
<head>
    <title>Сотрудники</title>
</head>
<body>
<div class="container">
    <%@ include file="/include/menuadmin.jsp" %>

    <form class="form-horizontal" method="post">
        <fieldset>

            <legend>Изменить сотрудника</legend>

            <p><span style="color:red">${error}</span></p>

            <div class="form-group">
                <div class="col-md-4">
                    <input id="id" value="${user.id}" name="id" type="hidden" placeholder=""
                           class="form-control input-md" required="">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="login">Логин</label>
                <div class="col-md-4">
                    <input id="login" name="login" value="${user.login}" type="text" placeholder=""
                           class="form-control input-md" title="Допустимые символы a-zA-Zа-яА-Я0-9_-"
                    >
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="password">Пароль</label>
                <div class="col-md-4">
                    <input id="password" name="password" value="${user.password}" type="password"
                           class="form-control input-md" title="Допустимые символы a-zA-Zа-яА-Я0-9_-"
                    >
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="role">Должность</label>
                <div class="col-md-4">
                    <select id="role" name="role" type="text" placeholder="">
                        <option value="admin"><fmt:message key="page.createuser.label.position.admin"/></option>
                        <option value="kassir"><fmt:message key="page.createuser.label.position.cashier"/></option>
                        <option value="controller"><fmt:message
                                key="page.createuser.label.position.controller"/></option>
                    </select>
                </div>
            </div>


            <div class="form-group">
                <div class="col-md-4">
                    <button id="save" name="save" class="btn btn-success">Сохранить</button>
                    <button id="cancel" name="cancel" class="btn btn-danger">Отмена</button>
                </div>
            </div>
        </fieldset>
    </form>

</div>
</body>
</html>


