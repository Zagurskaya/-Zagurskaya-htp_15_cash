<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<%@ include file="../include/head.jsp" %>
<body>
<div class="container">
    <%@ include file="../include/menu.jsp" %>

    <form class="form-horizontal" method="post" action="do?command=CreateAd">
        <fieldset>

            <!-- Form Name -->
            <legend>Signup</legend>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="description">Description</label>
                <div class="col-md-4">
                    <input id="description" value="TestValue description" name="description" type="text" placeholder="" class="form-control input-md" required="">

                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="address">Address</label>
                <div class="col-md-4">
                    <input id="address" name="address"  value="TestValue address" type="text" placeholder="" class="form-control input-md" required="">

                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="floor">Floor</label>
                <div class="col-md-4">
                    <input id="floor" name="floor"  value="3" type="text" placeholder="" class="form-control input-md" required="">

                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="rooms">Rooms</label>
                <div class="col-md-4">
                    <input id="rooms" name="rooms"  value="2" type="text" placeholder="" class="form-control input-md" required="">

                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="floors">Floors</label>
                <div class="col-md-4">
                    <input id="floors" name="floors"  value="5" type="text" placeholder="" class="form-control input-md" required="">

                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="price">Price</label>
                <div class="col-md-4">
                    <input id="price" name="price"  value="55555" type="text" placeholder="" class="form-control input-md" required="">

                </div>
            </div>

            <!-- Text input-->
            <div class="form-group">
                <label class="col-md-4 control-label" for="area">Area</label>
                <div class="col-md-4">
                    <input id="area" name="area"  value="55" type="text" placeholder="" class="form-control input-md" required="">

                </div>
            </div>

            <!-- Button -->
            <div class="form-group">
                <label class="col-md-4 control-label" for="createad"></label>
                <div class="col-md-4">
                    <button id="createad" name="createad" class="btn btn-success">Create Ad</button>
                </div>
            </div>

        </fieldset>
    </form>


</div>
</body>
</html>


