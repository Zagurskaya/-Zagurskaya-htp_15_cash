<%@ page language="java" isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Error page</title>
    <meta charset="utf-8">
</head>
<%@ include file="/include/head.jsp" %>
<body>
<h1>500 Internal Server Error </h1>
<br />
<p><b>Error code:</b> ${pageContext.errorData.statusCode}</p>
<p><b>Request URI:</b> ${pageContext.request.scheme}://${header.host}${pageContext.errorData.requestURI}</p>
<br />
</body>
</html>



