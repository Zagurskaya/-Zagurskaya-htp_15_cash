<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="../include/menu.jsp" %>
    <br>
    <H4>Пользователи</H4>
    <div class="row">
        <div class=col-md-2>Логин</div>
        <div class=col-md-2>Пароль</div>
        <div class=col-md-2>Роль</div>
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
                    <select id="role" name="role" class="form-control">
                        <c:forEach items="${roles}" var="role">
                        <option value="${role.id}" ${user.role==role.id?"selected":""}>${role.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <!-- Button (Double) -->
                <div class="col-md-4">
                    <button id="update" name="update" class="btn btn-success">Обновить</button>
                    <button id="delete" name="delete" class="btn btn-danger">Удалить</button>
                </div>
            </div>
        </form>
    </c:forEach>


</div>
</body>
</html>


