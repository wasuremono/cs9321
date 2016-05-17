<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib
    prefix="c"
    uri="http://java.sun.com/jsp/jstl/core" 
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<style>
label {
    width:180px;
    clear:left;
    text-align:left;
    padding-right:10px;
    padding-left: 50px;
}

input, label {
    float:left;
}
#button{
  clear:both;
  padding-left:50px;
}
</style>
<head>
<%@ include file="header.html" %>
</head>
<body>
<form action="profile" method = "post">
<p><label for="u1">First Name</label>			<input required id ="u1" type="text" size ="30" name="firstName" value=${sessionScope.u.firstName}>
<p><label for="u2">Last Name</label>			<input required id ="u2" type="text" size ="30" name="lastName" value=${sessionScope.u.lastName}>
<P><label for="u3">Nickname</label>			<input required  id ="u3" type="text" size ="30" name="nickname" value=${sessionScope.u.nickName}>
<p><label for="u4">Address</label>			<input required id ="u4" type="text" size ="30" name="address" value=${sessionScope.u.address}>
<P><label for="u5">Email Address</label>		<input required id="u5" size="30" type="email" name="email" value=${sessionScope.u.email} >
<!-- Card details follows -->                      
<P><label for="u8">CC Number</label>			<input required id ="u8" type="text" size ="30" name="cardNumber" value=${sessionScope.u.cardNumber}>
<P><label for="u9">CC Name</label>			<input required id ="u9" type="text" size ="30" name="cardName" value=${sessionScope.u.cardName}>
<P><label for="u10">CC Expiry Date</label>		<input required id ="u10" type="text" size ="30" name="cardExpire" value=${sessionScope.u.cardExpire}>
<P><label for="u11">CVC</label>				<input required id ="u11" type="text" size ="30" name="cvc" value=${sessionScope.u.cvc}>
<input type="hidden" name="action" value="confirm">
<div id="button" ><input Type = "submit" Value = "Confirm Details"></div>
</body>
</html>