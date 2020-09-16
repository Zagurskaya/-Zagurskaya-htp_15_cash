<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<head>
    <title>Test</title>
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="style.css"/>
    <style>

/*меню*/
.menu-nav {
    position:relative;
    width: 100%;
    height:56px;
    font-size:14px;
    color:#999;
    margin: 0 auto;
    margin-top: 20px;
	background: #395593;
    background: -moz-linear-gradient(top, #647db5, #395593);
    background: -webkit-gradient(linear, left top, left bottom, from(#647db5), to(#395593));
	background: -o-linear-gradient(top,rgb(100, 125, 181), rgb(57, 85, 147));
	background: -ms-linear-gradient(top, #647db5, #395593);
	background: linear-gradient(top, #647db5, #395593);
    text-shadow: 0 -1px 0 #111;
	border: 1px solid #444;
	border-radius: 8px;
   -moz-border-radius: 8px;
   -khtml-border-radius: 8px;
   -webkit-border-radius: 8px;
}

.menu-nav ul {
    list-style:none;
    padding-left: 0px;
    color: #FFF;

}

.menu-nav ul li {
     float:left;
     position:relative;
}
.menu-nav ul li a {
    border-right:1px solid #364f87;
    border-left:1px solid #7189c0;
    padding:17px;
    display:block;
    text-decoration:none;
    text-align:center;
    color:#FFF;
    background: #395593;
    background: -moz-linear-gradient(top, #647db5, #395593);
    background: -webkit-gradient(linear, left top, left bottom, from(#647db5), to(#395593));
	background-image: -o-linear-gradient(top,rgb(100, 125, 181), rgb(57, 85, 147));
    text-shadow: 0 -1px 0 #111;
}
.menu-nav li.first a {
    border-radius: 8px 0 0 8px;
   -moz-border-radius: 8px 0 0 8px;
   -khtml-border-radius: 8px 0 0 8px;
   -webkit-border-radius: 8px 0 0 8px;
}
.menu-nav li.last{
    float: right;
}
.menu-nav li.last a {
    border-radius: 0 8px 8px 0;
   -moz-border-radius: 0 8px 8px 0;
   -khtml-border-radius: 0 8px 8px 0;
   -webkit-border-radius: 0 8px 8px 0;
}

.menu-nav ul li a:hover {
    background: #647db5;
    background: -moz-linear-gradient(top, #395593, #647db5);
    background: -webkit-gradient(linear, left top, left bottom, from(#395593), to(#647db5));
	background-image: -o-linear-gradient(top,rgb(57, 85, 147), rgb(100, 125, 181));
    color:#fff;
}

.menu-nav ul li ul {
    display: none;
}

.menu-nav ul li:hover ul {
    display: block;
    position: absolute;
    min-width:200px;
    left:0;
    margin-top: -1px;
}

.menu-nav ul li:hover ul li a {
    display:block;
    color:#ffffff;
    width: 110px;
    text-align: center;
    border-bottom: 1px solid #283f74;
    border-top: 1px solid #366ea6;
    background: #364f87;
}

.menu-nav ul li ul li:first-child a {
    border-top:none;
}

.menu-nav ul li ul li:last-child a {
    border-bottom-left-radius: 5px;
    border-bottom-right-radius: 5px;
    -moz-border-radius-bottomleft: 5px;
    -moz-border-radius-bottomright: 5px;
    -webkit-border-bottom-right-radius: 5px;
    -webkit-border-bottom-left-radius: 5px;
}

.menu-nav ul li:hover ul li a:hover {
    background:#395593;
    color:#ffffff;
}
/*обратная ссылка */
a.back{
color:#777;
position:fixed;
top:5px;
right:10px;
text-decoration:none;
}

    </style>
</head>
