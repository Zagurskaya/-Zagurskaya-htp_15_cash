<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="/include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="/include/menucashnew.jsp" %>
    <br>
    <H4>Инкассация</H4>
    <form class="form-horizontal" action="do?command=Payment1100" method="post">
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
                               required="" value="${currencies.name}">
                    </div>

                    <div class="col-md-2">
                        <input id="sum" name="sum" type="text" placeholder="" class="form-control input-md"
                               required="" value="500">
                    </div>

                </div>
        </c:forEach>
        <br>
            <p>Описание:</p>
        <div class="col-md-7">
            <input id="specification" name="specification" type="text" placeholder="" class="form-control input-md"
                   required="" value="сдано денежных средств по расходному кассовому ордеру">
        </div>
        <br>
        <br>
        <div class="form-group">
            <div class="col-md-8">
                <button id="enter" name="enter" class="btn btn-primary">Провести</button>
                <button id="balance" name="balance" class="btn btn-primary">Получить остатки</button>
            </div>
        </div>
        </fieldset>
     </form>
</div>
</body>
</html>
