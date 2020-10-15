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
    <H4>Курсы КБ</H4>
   ---------------------------------------------------------------------------------------------------------------------------------------
   <div class="row">   <div class=col-md-2>|Приход</div>
                       <div class=col-md-2>|Расход</div>
                       <div class=col-md-3>|Дата вступления</div>
                       <div class=col-md-1>|Сумма</div>
                       <div class=col-md-1>|Обратный курс</div>
                       <div class=col-md-1>|</div></div>
   ---------------------------------------------------------------------------------------------------------------------------------------
       <c:forEach items="${rateCB}" var="rateCB" >
           <div class="row">
               <div class="col-md-2"> <c:forEach items="${currencyList}" var="currency">
                                       <c:if test="${rateCB.coming==currency.id}" >|${currency.name} </c:if>
                                       </c:forEach></div>
               <div class="col-md-2"><c:forEach items="${currencyList}" var="currency">
                                      <c:if test="${rateCB.spending==currency.id}" >|${currency.name} </c:if>
                                      </c:forEach></div>
               <div class="col-md-3">|${rateCB.timestamp}</div>
               <div class="col-md-1">|${rateCB.sum}</div>
               <div class="col-md-1">|${rateCB.isBack}</div>
               <div class="col-md-1">|</div>
           </div>
       </c:forEach>
   ---------------------------------------------------------------------------------------------------------------------------------------
</div>
</body>
</html>
