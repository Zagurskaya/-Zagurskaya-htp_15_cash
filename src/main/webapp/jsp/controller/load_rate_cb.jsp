<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="/include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="/include/menucontroller.jsp" %>
    <br>
    <%@ include file="/jsp/errors/errormessage.jsp" %>
    <br>
    <H4><fmt:message key="page.controller.message"/></H4>
             <H4> ${message}</H4>
    Файл: <br/>
    <form action="upload_rate_cb" method="post"
          enctype="multipart/form-data">
        <input type="file" name="guru_file" size="50"/>
        <br/>
        <input type="submit" value="Загрузить"/>
    </form>
</div>
</body>
</html>
