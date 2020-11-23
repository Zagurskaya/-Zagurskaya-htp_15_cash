<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../../include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="../../include/menuadmin.jsp" %>
    <form class="form-horizontal" method="post">
        <fieldset>
            <legend><fmt:message key="page.createuser.label.description"/></legend>
            <%@ include file="/jsp/errors/errormessage.jsp" %>

            <div class="form-group">
                <label class="col-md-4 control-label" for="login">
                    <fmt:message key="page.createuser.label.login"/>*</label>
                <div class="col-md-4">
                    <input id="login" value="${user.login}" name="login" type="text" placeholder=""
                           class="form-control input-md" title="Допустимые символы a-zA-Zа-яА-Я0-9_-"
                           required pattern="[a-zA-Zа-яА-Я0-9_-]{1,}"
                    >
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="password">
                    <fmt:message key="page.createuser.label.password"/>*</label>
                <div class="col-md-4">
                    <input id="password" name="password" value="" type="password" placeholder=""
                           class="form-control input-md" title="Допустимые символы a-zA-Zа-яА-Я0-9_-"
                           required pattern="[a-zA-Zа-яА-Я0-9_-]{1,}"
                    >
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="reiteratepassword">
                    <fmt:message key="page.createuser.label.reiteratepassword"/>*</label>
                <div class="col-md-4">
                    <input id="reiteratepassword" name="reiteratepassword" value="" type="password" placeholder=""
                           class="form-control input-md" title="Допустимые символы a-zA-Zа-яА-Я0-9_-"
                           required pattern="[a-zA-Zа-яА-Я0-9_-]{1,}"
                    >
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="fullname">
                    <fmt:message key="page.createuser.label.fullname"/>*</label>
                <div class="col-md-4">
                    <input id="fullname" value="${user.fullName}" name="fullname" type="text" placeholder=""
                           class="form-control input-md" title="Допустимые символы a-zA-Zа-яА-Я0-9_-"
                           required pattern="[a-zA-Zа-яА-Я0-9\s_-]{1,}"
                    >
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="role">
                    <fmt:message key="page.createuser.label.position"/></label>
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
                    <button id="save" name="save" class="btn btn-success">
                        <fmt:message key="page.createuser.button.save"/></button>
                    <button class="btn btn-danger" onclick="location.href = 'do?command=Edit_Users'">
                        <fmt:message key="page.createuser.button.cancel"/></button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>


