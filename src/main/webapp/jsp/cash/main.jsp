<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/tag/rateTag.tld" prefix="seostella" %>
<html>
<%@ include file="/include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="/include/menucashnew.jsp" %>
    <br>
    <seostella:rateTag rateValue="Rate Value"/>
    <br>
    <%@ include file="../errors/errormessage.jsp" %>
    <br>
    <br>
    <br>
    <br>
    <H4> ${messageDuties}</H4>
</div>
</body>
</html>
