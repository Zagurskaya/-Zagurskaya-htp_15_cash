<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Error page</title>
    <meta charset="utf-8">
</head>
<body>
<h1> Forbidden</h1>
<br />
<p><b>Error code:</b> ${pageContext.errorData.statusCode}</p>
<br />
</body>
</html>



