<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<div class="menu-nav">
   <ul>
      <li class="first"><a href="do?command=Main">Главная</a></li>
            <li><a href="do?command=Duties">Смены</a></li>
            <li><a href="#" style="padding-right:43px">Валюта</a>
               <ul>
                   <li><a href="do?command=AllCurrency">Справочник валют</a></li>
                   <li><a href="do?command=RateNB">Курсы НБ</a></li>
                   <li><a href="do?command=RateCB">Курсы КБ</a></li>
               </ul>
            </li>
            <li><a href="#" style="padding-right:26px" >Операции</a>
               <ul>
                   <li><a href="do?command=Payment">Платежи</a></li>
                   <li><a href="do?command=Balance">Остатки</a></li>
                   <li><a href="do?command=UserOperations">Проведенные операции</a></li>
               </ul>
            </li>
            <li><a href="#" style="padding-right:18px">Отчетность</a>
               <ul>
                   <li><a href="do?command=Entries">Итоговые проводки</a></li>
               </ul>
            </li>
            <li><a href="#" style="padding-right:44px">Отчеты</a>
               <ul>
                  <li><a href="do?command=Report1">Реестр покупки</a></li>
                  <li><a href="do?command=Report2">Реестр продажи</a></li>
                  <li><a href="do?command=Report3">Реестр коммунальных платежей</a></li>
              </ul>
           </li>
        <li class="last"><a href="do?command=Exit">Выход</a></li>
   </ul>
</div>
