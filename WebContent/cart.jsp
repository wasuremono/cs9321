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
${bookingError}
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
					<th>Number of rooms</th>
					<th>Extra Beds</th>
					<th>Cost</th>
			
			<c:forEach items="${cart}" var="cart">
			<tr>
			<td>${cart.location}</td>
			<td>${cart.roomType}</td> 
			<td><fmt:formatDate value="${cart.checkin}" pattern="dd-MM-yyyy" /></td> 
			<td><fmt:formatDate value="${cart.checkout}" pattern="dd-MM-yyyy" /></td> 
			<td>${cart.numRooms}</td>
			<c:choose>
				<c:when test="${cart.roomType != 'Single'}">
				    <td>
				    <form action="CartServlet" method = "get">
				    <input type="text" size ="1" name="extraBed" value=${cart.extraBed}>
				    <input type="hidden" name="booking_ID" value=${cart.id}>
				    <input type="hidden" name="action" value="update">
				    <Input Type = "submit" Value = "Change">
				    </form>
					<!--<a href="CartServlet?action=update&booking_ID=${cart.id}&extraBed=${extraBed}">Change--></td>
				</c:when>
					
				<c:otherwise>
					<td>N/A</td>
				</c:otherwise>
			</c:choose>	
			<td>SomePrice</td>			
			</c:forEach>
			</table>
			<p> Total Cost :</p>
			<!-- Display different buttons if its a modify cart -->
			<c:choose>
				<c:when test="${m = 1}">
				<!-- back to unique url? -->
				<a href="index.jsp">Keep existing Booking</a>
				<a href="CartServlet?action=remove">Cancel Booking</a>
				<a href="CheckoutServlet?action=checkout&modify=1">Checkout</a>
				</c:when>
			</c:choose>
			<c:otherwise>
			<a href="CartServlet?action=remove">Cancel Booking</a>
			<a href="CheckoutServlet?action=checkout">Checkout</a>
			</c:otherwise>
		</c:otherwise>
	</c:choose>
	</div>
</body>

</html>