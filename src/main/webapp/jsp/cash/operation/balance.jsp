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
    <H4>Остатки в кассе </H4>
        <div class="row">
            <div class=col-md-1>Код валюты</div>
            <div class=col-md-2>Получено</div>
            <div class=col-md-2>Приход</div>
            <div class=col-md-2>Расход</div>
            <div class=col-md-2>Сдано</div>
            <div class=col-md-2>Остаток в кассе</div>
        </div>
        <c:forEach items="${balanceList}" var="balance">
            <div class="row">

                <div class="col-md-1">
                    <input id="currencyId" name="currencyId" type="text" placeholder="" class="form-control input-md"
                           required="" value="${balance.currencyId}">
                </div>

                <div class="col-md-2">
                    <input id="received" name="received" type="text" placeholder="" class="form-control input-md"
                           required="" value="${balance.received}">
                </div>

                <div class="col-md-2">
                    <input id="coming" name="coming" type="text" placeholder="" class="form-control input-md"
                           required="" value="${balance.coming}">
                </div>

                <div class="col-md-2">
                    <input id="spending" name="spending" type="text" placeholder="" class="form-control input-md"
                           required="" value="${balance.spending}">
                </div>

                <div class="col-md-2">
                    <input id="transmitted" name="transmitted" type="text" placeholder="" class="form-control input-md"
                           required="" value="${balance.transmitted}">
                </div>

                <div class="col-md-2">
                    <input id="balance" name="balance" type="text" placeholder="" class="form-control input-md"
                           required="" value="${balance.balance}">
                </div>

            </div>
        </c:forEach>

</div>
</body>
</html>
