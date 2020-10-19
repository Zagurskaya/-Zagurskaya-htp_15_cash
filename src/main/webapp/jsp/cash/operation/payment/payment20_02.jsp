<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="/include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="/include/menucashnew.jsp" %>
    <br>
    <H4>Покупка валюты</H4>
    <form class="form-horizontal" action="do?command=Payment20_02" method="post">
        <fieldset>
        <br>
        <div class="row">
            <div class=col-md-3>Продано валюты</div>
            <div class=col-md-2>В сумме</div>
            <div class=col-md-2>По курсу</div>
        </div>

        <c:forEach items="${currencies}" var="currency">
                    <c:if test="${currency.id==currencyId}" >
                            <div class="row">

                                <div class="col-md-3">
                                    <input id="name" name="name" type="text" placeholder="" class="form-control input-md"
                                           required="" value="${currency.name}">
                                </div>

                                <div class="col-md-2">
                                    <input id="sum" name="sum" type="text" placeholder="" class="form-control input-md"
                                           required="" value="${currencySum}">
                                </div>

                                <div class="col-md-2">
                                    <input id="rateCB" name="rateCB" type="text" placeholder="" class="form-control input-md"
                                           required="" value="${rateCBPayment}">
                                </div>

                                <div class="col-md-2">
                                    <input id="id" name="id" type="hidden" placeholder="" class="form-control input-md"
                                           required="" value="${currency.id}">
                                </div>
                            </div>
                    </c:if>
                </c:forEach>
         <br>
         <div class="row">
                     <div class=col-md-3>Получено </div>
                     <div class=col-md-2>В сумме</div>
                 </div>
        <c:forEach items="${currencies}" var="currency">
            <c:if test="${currency.id==933}" >
                    <div class="row">

                        <div class="col-md-3">
                            <input id="name" name="name" type="text" placeholder="" class="form-control input-md"
                                   required="" value="${currency.name}">
                        </div>

                        <div class="col-md-2">
                            <input id="sum" name="sum" type="text" placeholder="" class="form-control input-md"
                                   required="" value="${sumRateCurrencyId}">
                        </div>

                        <div class="col-md-2">
                            <input id="id" name="id" type="hidden" placeholder="" class="form-control input-md"
                                   required="" value="${currency.id}">
                        </div>
                    </div>
            </c:if>
        </c:forEach>
       <br>
          <p>Описание:</p>
       <div class="col-md-7">
           <input id="specification" name="specification" type="text" placeholder="" class="form-control input-md"
                  required="" value="${specification}">
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
