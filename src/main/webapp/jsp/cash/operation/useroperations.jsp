<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="/include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="/include/menucashnew.jsp" %>
    <br>
    <br>
    <br>
    <br>
    <H4>Список проведенных операций</H4>
        <div class="row">
            <div class=col-md-1>Номер</div>
            <div class=col-md-3>Операция</div>
            <div class=col-md-2>Сумма</div>
            <div class=col-md-2>Валюта</div>
            <div class=col-md-3>Время</div>
        </div>
        <c:forEach items="${userOperations}" var="operation">
            <div class="row">

                <div class="col-md-1">
                    ${operation.id}
                </div>

                <div class="col-md-3">
                  <c:forEach items="${sprOperations}" var="sprOperation">
                    <c:if test="${operation.operationId==sprOperation.id}" > ${sprOperation.name} </c:if>
                  </c:forEach>
                </div>

                <div class="col-md-2">
                    ${operation.sum}
                </div>

                <div class="col-md-2">
                    <c:forEach items="${currensies}" var="currency">
                    <c:if test="${operation.currencyId==currency.id}" > ${currency.nameRU} </c:if>
                    </c:forEach>
                </div>

                <div class="col-md-3">
                   ${operation.timestamp}
                </div>

            </div>
        </c:forEach>

</div>
</body>
</html>
