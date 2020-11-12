<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="/include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="/include/menucontroller.jsp" %>
    <br>
    <%@ include file="/jsp/errors/errormessage.jsp" %>
    <br>
    <H4> ${message}</H4>
    <br>
    <form action="do?command=Send_Email" method="POST">
        <table>
            <tr>
                <td><fmt:message key="page.email.label.send"/></td>
                <td><input type="text" name="to"/></td>
            </tr>
            <tr>
                <td><fmt:message key="page.email.label.subject"/></td>
                <td><input type="text" name="subject"/></td>
            </tr>
        </table>
        <hr/>
        <textarea type="text" name="body" rows="5" cols="45"></textarea>
        <br/><br/>
        <input type="submit" value="<fmt:message key="page.email.button.send"/>"/>
    </form>
</div>
</body>
</html>
