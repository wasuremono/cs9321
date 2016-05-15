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
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${empty cart}">
		                      <h1>Your cart is empty</h1>    
		</c:when>
		<c:otherwise>
			<c:forEach items="${cart}" var="cart">
			${cart.id} ${cart.uid} 
			<fmt:formatDate value="${cart.checkin}" pattern="dd-MM-yyyy" /> 
			<fmt:formatDate value="${cart.checkout}" pattern="dd-MM-yyyy" /> 
			${cart.roomType}
			</c:forEach>
			<a href="modify.jsp">Modify Booking</a>
		</c:otherwise>
	</c:choose>
</body>
</html>