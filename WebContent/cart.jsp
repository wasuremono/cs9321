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
<style>
th{
	text-align:left;
}
</style>
<title>Insert title here</title>
</head>
<body>
<div class="container">

	<c:choose>
		<c:when test="${empty cart}">
		                      <h1>Your cart is empty</h1>    
		</c:when>
		<c:otherwise>
			<br>
			<table width = "800" class="table_view">
				<col width = "150">
				<col width = "150">
				<col width = "150">
				<col width = "150">
				<col width = "100">
				<col width = "100">
				<tr>
					<th>Location</th>
					<th>Room Type</th>
					<th>Checkin Date</th>
					<th>Checkout Date</th>
					<th>Extra Bed</th>
					<th>Cost</th>
			
			<c:forEach items="${cart}" var="cart">
			<tr>
			<td>Sydney</td>
			<td>${cart.roomType}</td> 
			<td><fmt:formatDate value="${cart.checkin}" pattern="dd-MM-yyyy" /></td> 
			<td><fmt:formatDate value="${cart.checkout}" pattern="dd-MM-yyyy" /></td> 
			<c:choose>
				<c:when test="${not cart.extraBed}">
					<td><a href="CartServlet?action=update&booking_ID=${cart.id}">Add</td>
				</c:when>
					
				<c:otherwise>
					<td><a href="CartServlet?action=update&booking_ID=${cart.id}">Remove</td>
				</c:otherwise>
			</c:choose>	
			<td>SomePrice</td>			
			</c:forEach>
			</table>
			<p> Total Cost :</p>
			<a href="CartServlet?action=remove">Cancel Booking 
			<a href="CheckoutServlet?action=checkout">Checkout 
		</c:otherwise>
	</c:choose>
	</div>
</body>

</html>