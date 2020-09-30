<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../../include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="../../include/menuadmin.jsp" %>
    <br>
    <H4><fmt:message key="page.editusers.label.description"/></H4>
    <div class="row">
        <div class=col-md-2><fmt:message key="page.editusers.label.login"/></div>
        <div class=col-md-2><fmt:message key="page.editusers.label.password"/></div>
        <div class=col-md-2><fmt:message key="page.editusers.label.position"/></div>
    </div>
    <c:forEach items="${users}" var="user">
        <form class="form-horizontal-${user.id}" action="do?command=EditUsers" method="post">
            <div class="row">
                <input id="id" name="id" type="hidden" placeholder="" class="form-control input-md"
                       required="" value="${user.id}">

                <div class="col-md-2">
                    <input id="login" name="login" type="text" placeholder="" class="form-control input-md"
                           required="" value="${user.login}">

                </div>

                <div class="col-md-2">
                    <input id="password" name="password" type="password" placeholder="" class="form-control input-md"
                           required="" value="${user.password}">

                </div>

                <div class="col-md-2">
                    <input id="role" name="role" type="role" placeholder="" class="form-control input-md"
                           required="" value="${user.role}">
                </div>

                <div class="col-md-4">
                    <button id="update" name="update" class="btn btn-success"><fmt:message
                            key="page.editusers.button.update"/></button>
                    <button id="delete" name="delete" class="btn btn-danger"><fmt:message
                            key="page.editusers.button.delete"/></button>
                </div>
            </div>
        </form>
    </c:forEach>
</div>
</body>
</html>


