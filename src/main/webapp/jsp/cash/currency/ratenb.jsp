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
    <H4>Курсы НБ</H4>
   ------------------------------------------------------------------------------------------
   <div class="row">   <div class=col-md-3>|Наименование</div>
                       <div class=col-md-2>|Дата вступления</div>
                       <div class=col-md-1>|Курс НБ</div>
                       <div class=col-md-1>|</div></div>
   ------------------------------------------------------------------------------------------
       <c:forEach items="${rateNB}" var="rateNB" >
           <div class="row">
               <div class="col-md-3">
                   <c:forEach items="${currencyList}" var="currency">
                   <c:if test="${rateNB.currencyId==currency.id}" >|${currency.name} </c:if>
                   </c:forEach>
               </div>
               <div class="col-md-2">|${rateNB.date}</div>
               <div class="col-md-1">|${rateNB.sum}</div>
               <div class="col-md-1">|</div>
           </div>
       </c:forEach>
   ------------------------------------------------------------------------------------------
</div>
</body>
</html>
