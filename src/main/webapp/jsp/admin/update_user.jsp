<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<%@ include file="/include/head.jsp" %>
<head>
</head>
<body>
<div class="container">
    <%@ include file="/include/menuadmin.jsp" %>

    <form class="form-horizontal" method="post">
        <fieldset>
            <legend><fmt:message key="page.updateuser.title"/></legend>
            <%@ include file="/jsp/errors/errormessage.jsp" %>

            <div class="form-group">
                <div class="col-md-4">
                    <input id="id" value="${user.id}" name="id" type="hidden" placeholder=""
                           class="form-control input-md" required="">
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="login"><fmt:message
                        key="page.editusers.label.login"/>*</label>
                <div class="col-md-4">
                    <input id="login" name="login" value="${user.login}" type="text" placeholder=""
                           class="form-control input-md" title="Допустимые символы a-zA-Zа-яА-Я0-9_-"
                           required pattern="[a-zA-Zа-яА-Я0-9_-]{1,}"
                    >
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="fullname"><fmt:message
                        key="page.editusers.label.fullname"/>*</label>
                <div class="col-md-4">
                    <input id="fullname" name="fullname" value="${user.fullName}" type="text" placeholder=""
                           class="form-control input-md" title="Допустимые символы a-zA-Zа-яА-Я0-9_-"
                           required pattern="[a-zA-Zа-яА-Я0-9\s_-]{1,}"
                    >
                </div>
            </div>

            <div class="form-group">
                <label class="col-md-4 control-label" for="role"><fmt:message
                        key="page.editusers.label.position"/></label>
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


