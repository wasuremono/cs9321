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
<style>
th{
	text-align:left;
}
</style>
<%@ include file="header.html" %>
</head>
<body>
${bookingError}
<div class="container">

	<c:choose>
		<c:when test="${empty cart}">
		                      <h1>Your booking has been cancelled</h1>    
		</c:when>
		<c:otherwise>
			<br>
			<table width = "900" class="table_view">
				<col width = "150">
				<col width = "150">
				<col width = "150">
				<col width = "150">
				<col width = "100">
				<col width = "100">
				<col width= "100">
				<tr>
					<th>Location</th>
					<th>Room Type</th>
					<th>Checkin Date</th>
					<th>Checkout Date</th>
					<th>Number of rooms</th>
					<th>Extra Beds</th>
					<th>Cost</th>
					<th>Extra Room</th>
			
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
				    <form action="ManageBooking" method = "get">
				    <input type="text" size ="1" name="extraBed" value=${cart.extraBed}>
				    <input type="hidden" name="booking_ID" value=${cart.id}>				    
				    <input type="hidden" name="ref_id" value=${referenceID}>
				    <input type="hidden" name="action" value="update">
				    <Input Type = "submit" Value = "Change">
				    </form>
					<!--<a href="CartServlet?action=update&booking_ID=${cart.id}&extraBed=${extraBed}">Change--></td>
				</c:when>
					
				<c:otherwise>
					<td>N/A</td>
				</c:otherwise>
			</c:choose>				
			<td>${cart.cost}</td>	
			<td><a href="ManageBooking?action=add&bookingID=${cart.id}&location=${cart.location}&roomType=${cart.roomType}&currRoom=${cart.numRooms}&refid=${referenceID}">Add Room</a>	
			</c:forEach>
			</table>
			<p> Total Cost : $${cost}</p>
			<a href="ManageBooking?action=remove&referenceID=${referenceID}">Cancel Booking </a>
			<c:if test="${not empty add}">
			<c:choose>
			<c:when test="${add == true}">	
			Keep changes or revert
			</c:when>
			<c:otherwise>
			No additional rooms available
			</c:otherwise>
			</c:choose>			
			</c:if>
			
		</c:otherwise>
	</c:choose>
	</div>
	<input type="hidden" name="bookingid" value="${bookingID}">
	<input type="hidden" name="add" value="${add}">
</body>

</html>