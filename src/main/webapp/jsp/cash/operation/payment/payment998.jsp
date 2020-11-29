<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="/include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="/include/menucashnew.jsp" %>
    <br>
    <H4><fmt:message key="page.payment998.title"/></H4>
    <%@ include file="/include/infomessage.jsp" %>
    <form class="form-horizontal" actionType="do?command=Payment998" method="post">
        <fieldset>
            <br>
            <p><fmt:message key="page.payment998.label.account"/>*</p>
            <div class="col-md-7">
                <input id="checkingAccount" name="checkingAccount" type="text" placeholder=""
                       class="form-control input-md"
                       required pattern="[A-ZА-Я0-9]{1,28}" value="">
<%--                       required="" value="BY53BLBB30112005500150000000">--%>
            </div>
            <br>
            <p><fmt:message key="page.payment998.label.description"/>*</p>
            <div class="col-md-7">
                <input id="specification" name="specification" type="text" placeholder="" class="form-control input-md"
                       required pattern="[a-zA-Zа-яА-Я0-9\s_-]{1,}" value="за коммунальные услуги">
            </div>
            <br>
            <p><fmt:message key="page.payment998.label.name"/>*</p>
            <div class="col-md-7">
                <input id="fullname" name="fullname" type="text" placeholder="" class="form-control input-md"
                       required pattern="[a-zA-Zа-яА-Я0-9\s_-]{1,}" value="">
            </div>
            <br>
            <div class="row">
                <div class=col-md-3><fmt:message key="page.payment998.label.currency"/></div>
                <div class=col-md-2><fmt:message key="page.payment998.label.sum"/>*</div>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <select id="id" name="id" class="form-control">
                        <c:forEach items="${currencies}" var="currency">
                            <option value="${currency.id}" }>
                                <c:if test="${cookie.local.value==null || cookie.local.value=='ru'}">
                                    ${currency.nameRU}
                                </c:if>
                                <c:if test="${cookie.local.value=='en'}">
                                    ${currency.nameEN}
                                </c:if></option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-2">
                    <input id="sum" name="sum" type="text" placeholder="" class="form-control input-md"
                           required pattern="[0-9.]{1,}" value="">
                </div>
            </div>
            <br>
            <div class="form-group">
                <label class="col-md-4 control-label" for="paymentButton"></label>
                <div class="col-md-4">
                    <button id="paymentButton" name="paymentButton" class="btn btn-primary">
                        <fmt:message key="page.payment998.button.enter"/></button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>
