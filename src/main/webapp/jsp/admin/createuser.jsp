<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../../include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="../../include/menuadmin.jsp" %>

    <form class="form-horizontal" method="post">
        <fieldset>
            <legend>Добавить сотрудника</legend>
            <p><span style="color:red">${error}</span></p>

            <div class="form-group">
                <label class="col-md-4 control-label" for="login">Логин</label>
                <div class="col-md-4">
                    <input id="login" value="${user.login}" name="login" type="text" placeholder=""
                           class="form-control input-md" title="Допустимые символы a-zA-Zа-яА-Я0-9_-"
                    >
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="password">Пароль</label>
                <div class="col-md-4">
                    <input id="password" name="password" value="${user.password}" type="text" placeholder=""
                           class="form-control input-md" title="Допустимые символы a-zA-Zа-яА-Я0-9_-"
                    >
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="role">Должность</label>
                <div class="col-md-4">
                    <select id="role" name="role" type="text" placeholder="">
                        <option value="admin">Админ</option>
                        <option value="kassir">Кассир</option>
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


