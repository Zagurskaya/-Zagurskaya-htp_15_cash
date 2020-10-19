<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="/include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="/include/menucashnew.jsp" %>
    <br>
    <H4>Подкрепление</H4>
    <form class="form-horizontal" action="do?command=Payment1000" method="post">
        <fieldset>
            <div class="row">
                <div class=col-md-2>Код валюты</div>
                <div class=col-md-3>Наименование валюты</div>
                <div class=col-md-2>Сумма</div>
            </div>
            <c:forEach items="${currencies}" var="currencies">
                <div class="row">

                    <div class="col-md-2">
                        <input id="id" name="id" type="text" placeholder="" class="form-control input-md"
                               required="" value="${currencies.id}">
                    </div>

                    <div class="col-md-3">
                        <input id="name" name="name" type="text" placeholder="" class="form-control input-md"
                               required=""
                        <c:if test="${cookie.local.value==null || cookie.local.value=='ru'}">
                               value="${currencies.nameRU}"
                        </c:if>
                        <c:if test="${cookie.local.value=='en'}">
                               value="${currencies.nameEN}"
                        </c:if>
                        >
                    </div>

                    <div class="col-md-2">
                        <input id="sum" name="sum" type="text" placeholder="" class="form-control input-md"
                               required="" value="1000">
                    </div>
                </div>
            </c:forEach>
            <br>
            <p>Описание:</p>
            <div class="col-md-7">
                <input id="specification" name="specification" type="text" placeholder="" class="form-control input-md"
                       required="" value="получено денежных средств по приходному кассовому ордеру">
            </div>
            <br>
            <br>
            <div class="form-group">
                <label class="col-md-4 control-label" for="paymentButton"></label>
                <div class="col-md-4">
                    <button id="paymentButton" name="paymentButton" class="btn btn-primary">Провести</button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>
