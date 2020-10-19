<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="/include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="/include/menucashnew.jsp" %>
    <br>
    <H4>Коммунальный платеж</H4>
    <form class="form-horizontal" action="do?command=Payment998" method="post">
        <fieldset>
        <br>
        <p>Расчетный счет получателя:</p>
        <div class="col-md-7">
            <input id="checkingAccount" name="checkingAccount" type="text" placeholder="" class="form-control input-md"
                   required="" value="BY53BLBB30112005500150000000">
        </div>
        <br>
        <p>Описание:</p>
        <div class="col-md-7">
            <input id="specification" name="specification" type="text" placeholder="" class="form-control input-md"
                   required="" value="за коммунальные услуги">
        </div>
        <br>
        <p>Фамилия Имя Отчество:</p>
        <div class="col-md-7">
            <input id="fio" name="fio" type="text" placeholder="" class="form-control input-md"
                   required="" value="Иванов Иван Иванович">
        </div>
        <br>
        <div class="row">
            <div class=col-md-3>Выбор валюты</div>
            <div class=col-md-2>Сумма</div>
        </div>
            <div class="row">
                <div class="col-md-3">
                <select id="id" name="id" class="form-control">
                <c:forEach items="${currencies}" var="currency">
                     <option value="${currency.id}" }>${currency.name}</option>
                     </c:forEach>
                 </select>
                </div>

                <div class="col-md-2">
                    <input id="sum" name="sum" type="text" placeholder="" class="form-control input-md"
                           required="" value="390">
                </div>
            </div>
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
