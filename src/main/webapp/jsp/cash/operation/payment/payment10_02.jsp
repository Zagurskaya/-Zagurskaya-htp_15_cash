<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="/include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="/include/menucashnew.jsp" %>
    <br>
    <H4><fmt:message key="page.payment10.title"/></H4>
    <%@ include file="/include/infomessage.jsp" %>
    <form class="form-horizontal" actionType="do?command=Payment10_02" method="post">
        <fieldset>
            <br>
            <div class="row">
                <div class=col-md-3><fmt:message key="page.payment10.label.receive"/></div>
                <div class=col-md-2><fmt:message key="page.payment10.label.sum"/></div>
                <div class=col-md-2><fmt:message key="page.payment10.label.rate"/></div>
            </div>

            <c:forEach items="${currencies}" var="currency">
                <c:if test="${currency.id==currencyId}">
                    <div class="row">

                        <div class="col-md-3">
                            <input id="name" name="name" type="text" readonly placeholder="" class="form-control input-md"
                                   required=""
                            <c:if test="${cookie.local.value==null || cookie.local.value=='ru'}">
                                   value="${currency.nameRU}"
                            </c:if>
                            <c:if test="${cookie.local.value=='en'}">
                                   value="${currency.nameEN}"
                            </c:if>
                            >
                        </div>

                        <div class="col-md-2">
                            <input id="sum" name="sum" type="text" readonly placeholder="" class="form-control input-md"
                                   required="" value="${currencySum}">
                        </div>

                        <div class="col-md-2">
                            <input id="rateCB" name="rateCB" type="text" readonly placeholder="" class="form-control input-md"
                                   required="" value="${rateCBPayment}">
                        </div>

                        <div class="col-md-2">
                            <input id="id" name="id" type="hidden" readonly placeholder="" class="form-control input-md"
                                   required="" value="${currency.id}">
                        </div>
                    </div>
                </c:if>
            </c:forEach>
            <br>
            <div class="row">
                <div class=col-md-3><fmt:message key="page.payment10.label.transmit"/></div>
                <div class=col-md-2><fmt:message key="page.payment10.label.sum"/></div>
            </div>
            <c:forEach items="${currencies}" var="currency">
                <c:if test="${currency.id==933}">
                    <div class="row">

                        <div class="col-md-3">
                            <input id="name" name="name" type="text" readonly placeholder="" class="form-control input-md"
                                   required=""
                            <c:if test="${cookie.local.value==null || cookie.local.value=='ru'}">
                                   value="${currency.nameRU}"
                            </c:if>
                            <c:if test="${cookie.local.value=='en'}">
                                   value="${currency.nameEN}"
                            </c:if>
                            >
                        </div>

                        <div class="col-md-2">
                            <input id="sum" name="sum" type="text" readonly placeholder="" class="form-control input-md"
                                   required="" value="${sumRateCurrencyId}">
                        </div>

                        <div class="col-md-2">
                            <input id="id" name="id" type="hidden" readonly placeholder="" class="form-control input-md"
                                   required="" value="${currency.id}">
                        </div>
                    </div>
                </c:if>
            </c:forEach>
            <br>
            <p><fmt:message key="page.payment20.label.description"/>*</p>
            <div class="col-md-7">
                <input id="specification" name="specification" type="text" placeholder="" class="form-control input-md"
                       required pattern="[a-zA-Zа-яА-Я0-9\s_-]{1,}" value="${specification}">
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
