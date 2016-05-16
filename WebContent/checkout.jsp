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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Checkout</title>
</head>
<body>
<form action="CheckoutServlet" method = "get">
<p><input type="text" size ="10" name="firstName" value=${u.firstName}>
<p><input type="text" size ="10" name="lastName" value=${u.lastName}>
<p><input type="text" size ="30" name="address" value=${u.address}>
<P><input type="text" size ="30" name="email" value=${u.email}>
<input type="hidden" name="action" value="confirm">
<Input Type = "submit" Value = "Confirm">
</body>
</html>