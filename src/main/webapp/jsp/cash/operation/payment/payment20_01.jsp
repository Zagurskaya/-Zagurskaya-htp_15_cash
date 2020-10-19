<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="/include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="/include/menucashnew.jsp" %>
    <br>
    <H4>Продажа валюты</H4>
    <form class="form-horizontal" action="do?command=Payment20_01" method="post">
        <fieldset>
        <br>
        <div class="row">
            <div class=col-md-3>Продать валюту</div>
            <div class=col-md-2>В сумме</div>
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
                       required="" value="100">
            </div>
        </div>
        <br>
       <br>
          <p>Описание:</p>
       <div class="col-md-7">
           <input id="specification" name="specification" type="text" placeholder="" class="form-control input-md"
                  required="" value="продажа валюты в личное пользование">
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
