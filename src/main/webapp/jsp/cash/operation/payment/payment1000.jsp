<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="/include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="/include/menucashnew.jsp" %>
    <br>
    <H4><fmt:message key="page.payment1000.title"/></H4>
    <form class="form-horizontal" actionType="do?command=Payment1000" method="post">
        <fieldset>
            <div class="row">
                <div class=col-md-2><fmt:message key="page.payment1000.label.number"/></div>
                <div class=col-md-3><fmt:message key="page.payment1000.label.name"/></div>
                <div class=col-md-2><fmt:message key="page.payment1000.label.sum"/>*</div>
            </div>
            <c:forEach items="${currencies}" var="currencies">
                <div class="row">

                    <div class="col-md-2">
                        <input id="id" name="id" type="text" readonly placeholder="" class="form-control input-md"
                               required="" value="${currencies.id}">
                    </div>

                    <div class="col-md-3">
                        <input id="name" name="name" type="text" readonly placeholder="" class="form-control input-md"
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
                               required pattern="[0-9.]{1,}" value="">
                    </div>
                </div>
            </c:forEach>
            <br>
            <p><fmt:message key="page.payment1000.label.description"/>*</p>
            <div class="col-md-7">
                <input id="specification" name="specification" type="text" placeholder="" class="form-control input-md"
                       required pattern="[a-zA-Zа-яА-Я0-9\s_-]{1,}" value="получено денежных средств по приходному кассовому ордеру">
            </div>
            <br>
            <br>
            <div class="form-group">
                <label class="col-md-4 control-label" for="paymentButton"></label>
                <div class="col-md-4">
                    <button id="paymentButton" name="paymentButton" class="btn btn-primary">
                        <fmt:message key="page.payment1000.button.enter"/>
                    </button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>
