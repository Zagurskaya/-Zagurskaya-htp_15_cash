<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/tag/rateTag.tld" prefix="seostella" %>
<html>
<%@ include file="/include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="/include/menucontroller.jsp" %>
    <br>
    <seostella:rateTag rateValue="Rate Value"/>
    <br>
    <%@ include file="/jsp/errors/errormessage.jsp" %>
    <br>
    <br>
    <br>
    <H4><fmt:message key="page.controller.message"/></H4>
</div>
</body>
</html>
