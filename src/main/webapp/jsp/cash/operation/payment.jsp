<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="/include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="/include/menucashnew.jsp" %>
    <br>
    <br>
    <br>
    <H4 style="color:#ff0000">${message}</H4>
    <br>
    <br>
    <form class="form-horizontal" action="do?command=Payment" method="post">
        <fieldset>
            <legend><fmt:message key="page.payment.label.select"/></legend>
            <br>
            <div class="col-md-4">
                <select id="SprOperationsId" name="SprOperationsId" class="form-control">
                    <c:forEach items="${sprOperations}" var="sprOperation">
                        <c:if test="${cookie.local.value==null || cookie.local.value=='ru'}">
                            <option value="${sprOperation.id}">${sprOperation.nameRU}</option>
                        </c:if>
                        <c:if test="${cookie.local.value=='en'}">
                            <option value="${sprOperation.id}">${sprOperation.nameEN}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            <br>
            <br>
            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="enterButton"></label>
                <div class="col-md-4">
                    <button id="enterButton" name="enterButton" class="btn btn-primary">
                        <fmt:message key="page.payment.button.forward"/>
                    </button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>
