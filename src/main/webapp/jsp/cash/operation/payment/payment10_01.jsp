<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/tag/rateTag.tld" prefix="seostella" %>
<html>
<%@ include file="/include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="/include/menucashnew.jsp" %>
    <br>
    <seostella:rateTag rateValue="Rate Value"/>
    <br>
    <H4><fmt:message key="page.payment10.title"/></H4>
    <form class="form-horizontal" actionType="do?command=Payment10_01" method="post">
        <fieldset>
            <br>
            <div class="row">
                <div class=col-md-3><fmt:message key="page.payment10.label.receive"/></div>
                <div class=col-md-2><fmt:message key="page.payment10.label.sum"/>*</div>
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
                                </c:if>
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="col-md-2">
                    <input id="sum" name="sum" type="text" placeholder="" class="form-control input-md"
                           required pattern="[0-9.]{1,}" value="">
                </div>
            </div>
            <br>
            <br>
            <p><fmt:message key="page.payment10.label.description"/>*</p>
            <div class="col-md-7">
                <input id="specification" name="specification" type="text" placeholder="" class="form-control input-md"
                       required pattern="[a-zA-Zа-яА-Я0-9\s_-]{1,}" value="покупка валюты в личное пользование">
            </div>

            <br>
            <br>
            <div class="form-group">
                <label class="col-md-4 control-label" for="paymentButton"></label>
                <div class="col-md-4">
                    <button id="paymentButton" name="paymentButton" class="btn btn-primary">
                        <fmt:message key="page.payment10.button.enter"/></button>
                </div>
            </div>
        </fieldset>
    </form>
</div>
</body>
</html>
