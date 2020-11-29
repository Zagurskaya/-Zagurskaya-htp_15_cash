<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="/include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="/include/menucontroller.jsp" %>
    <br>
    <%@ include file="/jsp/errors/errormessage.jsp" %>
    <br>
    <br>
    <br>
    <br>
    <H4><fmt:message key="page.controller.message"/></H4>
             <H4> ${message}</H4>
    <form class="form-horizontal" actionType="do?command=UnloadEntries" method="post">
        <fieldset>
            <div class="form-group">
                <div class="col-md-8">
                    <button id="unload" name="unload" class="btn btn-success">
                        <fmt:message key="page.entries.button.unload"/></button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>
