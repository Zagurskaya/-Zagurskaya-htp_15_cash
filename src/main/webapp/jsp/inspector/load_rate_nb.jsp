<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="/include/head.jsp" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<body>
<div class="container">
    <%@ include file="/include/menucontroller.jsp" %>
    <br>
    <%@ include file="/jsp/errors/errormessage.jsp" %>
    <br>
    <H4><fmt:message key="controller.ratenb"/></H4>
    <H4> ${message}</H4>
    <fmt:message key="page.ratenb.label.file"/> <br/>
    <form action="upload_rate_nb" method="post"
          enctype="multipart/form-data">
        <input type="file" name="guru_file" size="50"/>
        <br/>
        <input type="submit" value="<fmt:message key="page.ratenb.button.load"/>"/>
    </form>
</div>
</body>
</html>
