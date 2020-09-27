<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href=".">Главная</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <c:choose>
                <c:when test="${user!=null}">
                    <a class="nav-item nav-link" href="do?command=EditUsers">Пользователи</a>
                    <a class="nav-item nav-link" href="do?command=CreateUser">Добавить пользователя</a>
                </c:when>
            </c:choose>
        </div>
    </div>
    <a class="nav-item nav-link" href="do?command=Logout">Выход</a>
</nav>

