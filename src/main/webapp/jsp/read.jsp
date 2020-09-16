<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Read</title>
</head>
<body>
<h1>Read text</h1>
<h2>Text:${text}</h2>
<form method="post" action="/read">
    <input type="submit" name="read">
</form>
</body>
</html>




